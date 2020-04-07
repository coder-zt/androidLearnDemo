package com.zhangtao.androidlearndemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.BIND_AUTO_CREATE;

public class NotifcationUtil{

    private static NotifcationUtil mNotif = null;
    private Context mContext = null;
    private static final String CHANNEL_ID = "id";
    private final static int notificationId = 111;
    private NotificationManager notificationManager;
    private NotificationManagerCompat mNotificationManagerCompat;
    private NotificationService.InnerController mController;
    private PlayerServiceConnection playerServiceConnection;
    //点击事件代码
    public final static String EVENT_KEY = "DATA";
    //播放暂停
    public final static int PLAY_OR_PAUSE = 1;
    //上一曲
    public final static int PLAY_PER = 2;
    //下一曲
    public final static int PLAY_NEXT = 3;
    //收藏
    public final static int PLAY_COLLECT = 4;
    //调速
    public final static int PLAY_SPEED = 5;
    private final static String TAG = "NotifcationUtil";
    NotificationService.INotificationEvent mCallback = null;

    private NotifcationUtil(Context context,  NotificationService.INotificationEvent event){
        mContext = context;
        mCallback = event;
        mNotificationManagerCompat = NotificationManagerCompat.from(mContext.getApplicationContext());
        initService();
        initBind();
    }

    public static NotifcationUtil getInstance(Context context, NotificationService.INotificationEvent event){
        if(mNotif == null){
            synchronized(NotifcationUtil.class){
                if(mNotif == null){
                    mNotif = new NotifcationUtil(context, event);
                }
                return mNotif;
            }
        }else{
            return mNotif;
        }
    }

    /**
     * 初始化服务
     */
    private void initService() {
        Intent intent = new Intent(mContext, NotificationService.class);
        mContext.startService(intent);
    }

    /**
     * 绑定服务
     */
    private void initBind() {

        Intent intent = new Intent(mContext, NotificationService.class);
        if (playerServiceConnection == null) {
            playerServiceConnection = new PlayerServiceConnection();
        }
        mContext.bindService(intent, playerServiceConnection, BIND_AUTO_CREATE);
    }



    /**
     * 连接服务类
     */
    private class PlayerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mController = (NotificationService.InnerController)service;
            if (mCallback != null) {
                mController.registerInnerCallback(mCallback);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void sendNotification(String title, String info, boolean isPlaying) {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        //点击事件

        Intent intentCollect = new Intent(mContext, NotificationService.class);
        intentCollect.putExtra(EVENT_KEY , PLAY_COLLECT);
        PendingIntent collectPending = PendingIntent.getService(mContext, 1, intentCollect, 0);

        Intent intentPre = new Intent(mContext, NotificationService.class);
        intentPre.putExtra(EVENT_KEY , PLAY_PER);
        PendingIntent prePending = PendingIntent.getService(mContext, 2, intentPre, 0);

        Intent intentPlayOrPause  = new Intent(mContext, NotificationService.class);
        intentPlayOrPause.putExtra(EVENT_KEY , PLAY_OR_PAUSE);
        PendingIntent playOrPausePending = PendingIntent.getService(mContext, 3, intentPlayOrPause, 0);

        Intent intentNext = new Intent(mContext, NotificationService.class);
        intentNext.putExtra(EVENT_KEY , PLAY_NEXT);
        PendingIntent nextPending = PendingIntent.getService(mContext, 4, intentNext, 0);

        Intent intentSpeed = new Intent(mContext, NotificationService.class);
        intentSpeed.putExtra(EVENT_KEY , PLAY_SPEED);
        PendingIntent speedPending = PendingIntent.getService(mContext, 5, intentSpeed, 0);



        //设置通知的布局
        RemoteViews notificationLayout = new RemoteViews(mContext.getPackageName(), R.layout.notification_small);

        //调整UI
        notificationLayout.setTextViewText( R.id.title, title);
        notificationLayout.setTextViewText( R.id.sub_info, info);
        if(isPlaying){
            notificationLayout.setImageViewResource(R.id.play_btn, R.mipmap.play);
        }else{
            notificationLayout.setImageViewResource(R.id.play_btn, R.mipmap.pause);
        }
        //点击事件
        notificationLayout.setOnClickPendingIntent(R.id.collect_btn, collectPending);
        notificationLayout.setOnClickPendingIntent(R.id.pre_btn, prePending);
        notificationLayout.setOnClickPendingIntent(R.id.play_btn, playOrPausePending);
        notificationLayout.setOnClickPendingIntent(R.id.next_btn, nextPending);
        notificationLayout.setOnClickPendingIntent(R.id.speed_btn, speedPending);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.pic)
                //添加自定义样式
                .setCustomBigContentView(notificationLayout)
                .setCustomContentView(notificationLayout)
                //内容的动作
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification mNotification = builder.build();
//        mNotification.bigContentView = notificationLayout;
//        mNotification.contentView = notificationLayout;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, mNotification);
    }

    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = mContext.getString(R.string.channel_name);
            String description = mContext.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
