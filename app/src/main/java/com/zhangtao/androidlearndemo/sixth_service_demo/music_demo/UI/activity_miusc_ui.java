package com.zhangtao.androidlearndemo.sixth_service_demo.music_demo.UI;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.zhangtao.androidlearndemo.R;
import com.zhangtao.androidlearndemo.sixth_service_demo.music_demo.service.PlayerService;
import com.zhangtao.androidlearndemo.interfaces.IPlayerControl;
import com.zhangtao.androidlearndemo.interfaces.IPlayerViewControl;

public class activity_miusc_ui extends Activity {

    private Button btnClose;
    private Button btnSwitch;
    private TextView tvInfo;
    private SeekBar mSeekBar;
    private static String TAG = "activity_music_ui";
    private IPlayerControl iPlayerControl;
    private PlayerServiceConnection playerServiceConnection;
    boolean isTouchSeekBar = false;

    private NotificationManager notificationManager1;
    private final static int notificationId = 111;
    private static final String CHANNEL_ID = "id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic_ui);
        //初始化控件
        initView();
        //初始化控件事件
        initListener();
        //申请权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        //开启服务
        initService();
        //绑定服务
        initBind();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mSeekBar = findViewById(R.id.sb_progess);
        btnSwitch = findViewById(R.id.btn_switch);
        btnClose = findViewById(R.id.btn_close);
        tvInfo = findViewById(R.id.tv_info);
    }

    /**
     * 初始化控件监听事件
     */
    private void initListener() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //进度条发送变化

                Log.d(TAG,"进度条发送变化" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //触摸到进度条
                isTouchSeekBar = true;
                Log.d(TAG,"触摸到进度条");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //停止拖动
                isTouchSeekBar = false;
                int touchProgress = seekBar.getProgress();
                Log.d(TAG,"停止拖动-->" + touchProgress);
                if (iPlayerControl != null) {
                    iPlayerControl.seekTo(touchProgress);
                }
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击关闭
                iPlayerControl.stopPlay();
            }
        });
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击暂停\播放
                if (iPlayerControl != null) {
                    iPlayerControl.palyOrPause();

                }
            }
        });
    }


    /**
     * 初始化服务
     */
    private void initService() {
        Log.d(TAG, " initService...");
        Intent intent = new Intent(this, PlayerService.class);
        startService(intent);
    }

    /**
     * 绑定服务
     */
    private void initBind() {
        Log.d(TAG, " initBind...");

        Intent intent = new Intent(this, PlayerService.class);
        if (playerServiceConnection == null) {
            playerServiceConnection = new PlayerServiceConnection();
        }
        bindService(intent, playerServiceConnection, BIND_AUTO_CREATE);
    }

    /**
     * 连接服务类
     */
    private class PlayerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, " onServiceConnected...");
            iPlayerControl = (IPlayerControl)service;
            iPlayerControl.registerViewController(mIPlayerViewControl);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, " onServiceDisconnected...");
            iPlayerControl = null;
        }
    }



    @Override
    protected void onDestroy() {
        Log.d(TAG, " onDestroy...");
        super.onDestroy();
        iPlayerControl.unRegisterViewController();
        if (playerServiceConnection != null) {
            unbindService(playerServiceConnection);
        }
    }

    private IPlayerViewControl mIPlayerViewControl = new IPlayerViewControl(){


        @Override
        public void onPlayerStateChange(int state) {
            switch (state){
                case PLAY_STATE_PLAYER:
                    Log.d(TAG, "onPlayerStateChange:  开始播放");

                    btnSwitch.setText("暂停");
                    break;
                case PLAY_STATE_PAUSE:
                case PLAY_STATE_STOP:
                    btnSwitch.setText("播放");
                    break;
            }
        }

        @Override
        public void onSeekChange(final int seek, final String info) {
//            Log.d(TAG,"This Thread on outside name:" + Thread.currentThread().getName());

            //当用户触摸进度条，不修改进度
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!isTouchSeekBar){
//                iPlayerControl.unRegisterViewController();
                        mSeekBar.setProgress(seek);
                        tvInfo.setText(info);
                    }
                }
            });

        }
        };
}
