package com.zhangtao.androidlearndemo.sixth_service_demo.music_demo.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zhangtao.androidlearndemo.NotifcationUtil;
import com.zhangtao.androidlearndemo.NotificationService;
import com.zhangtao.androidlearndemo.interfaces.IPlayerControl;
import com.zhangtao.androidlearndemo.interfaces.IPlayerViewControl;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static com.zhangtao.androidlearndemo.interfaces.IPlayerViewControl.PLAY_STATE_PAUSE;
import static com.zhangtao.androidlearndemo.interfaces.IPlayerViewControl.PLAY_STATE_PLAYER;
import static com.zhangtao.androidlearndemo.interfaces.IPlayerViewControl.PLAY_STATE_STOP;

public class PlayerService extends Service implements NotificationService.INotificationEvent {

    private static final  String  TAG = "PlayerService";
    private NotifcationUtil mNotif = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind...");
        return new handler() ;
    }

    @Override
    public void collectClick() {
        Log.d(TAG, "collectClick: " + "收藏");
    }

    @Override
    public void preClick() {
        Log.d(TAG, "preClick: " + "上一首");
    }

    @Override
    public void playOrPauseClick() {
        Log.d(TAG, "playOrPauseClick: " + "播放或暂停");
        IpalyOrPause();
    }

    @Override
    public void nextClick() {
        Log.d(TAG, "nextClick: " + "下一首");
    }

    @Override
    public void speedClick() {
        Log.d(TAG, "speedClick: " + "加速");
    }

    private class handler extends Binder implements IPlayerControl{


        @Override
        public void registerViewController(IPlayerViewControl viewControl) {
            registerController(viewControl);
        }

        @Override
        public void unRegisterViewController() {
                unRegisterController();
        }

        @Override
        public void palyOrPause() {
            IpalyOrPause();
        }

        @Override
        public void stopPlay() {
            IStopPlay();
        }

        @Override
        public void seekTo(int seek) {
            ISeekTo(seek);
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate...");
        super.onCreate();
        mNotif = NotifcationUtil.getInstance(this, this);
        if (mNotif != null) {
            mNotif.createNotificationChannel();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy...");
        super.onDestroy();
    }

    /**
     * 展示信息
     */
    void showInfo(String info){
        Toast.makeText(this, "开始服务" + info, Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ----");
        showInfo(intent.getStringExtra("DATA") + "=" + flags +"=" + startId);
        IpalyOrPause();
        return super.onStartCommand(intent, flags, startId);
    }

        private IPlayerViewControl iPlayerViewControl;
        private MediaPlayer mediaPlayer;
        private SeekTimeTask mSeekTimeTask;
        private Timer mTimer;


        private int currentStatus = PLAY_STATE_STOP;
        public void registerController(IPlayerViewControl viewControl) {
            Log.d(TAG, "registerViewController...");
            iPlayerViewControl = viewControl;
        }

        public void unRegisterController() {
            Log.d(TAG, "unRegisterViewController...");
            iPlayerViewControl = null;
        }

        public void IpalyOrPause() {
            switch (currentStatus){
                case PLAY_STATE_STOP:
                    //创建播放器
                    initPlayer();
                    //设置数据源
                    try {
                        File file = new File("/sdcard/940.mp3");//""/sdcard/940.mp3
                        if(file.exists()){
                            Log.d(TAG, "该文件存在！");
                            //检测权限
                            int result = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                            if(result == PackageManager.PERMISSION_GRANTED){
                                Log.d(TAG, "获取权限成功");
                            }else{
                                Log.d(TAG, "获取权限失败");

                            }
                            Uri uri = Uri.fromFile(file);
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mediaPlayer.setDataSource(getApplicationContext(),uri);
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            if (mNotif != null) {

                                mNotif.sendNotification("小姐姐", "刘德华", true);
                            }
                            startTimer();
                        }else{
                            Log.d(TAG, "该文件不存在！");
                        }
                        currentStatus = PLAY_STATE_PLAYER;
                        if(iPlayerViewControl != null){
                            iPlayerViewControl.onPlayerStateChange(currentStatus);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case  PLAY_STATE_PLAYER:
                    if (mNotif != null) {

                        mNotif.sendNotification("暂停", "小姐姐", false);
                    }
                    stopTimer();
                    mediaPlayer.pause();
                    currentStatus = PLAY_STATE_PAUSE;
                    if(iPlayerViewControl != null){
                        iPlayerViewControl.onPlayerStateChange(currentStatus);
                    }
                    break;
                case  PLAY_STATE_PAUSE:

                    if (mNotif != null) {

                        mNotif.sendNotification("李林璐", "张滔", true);
                    }
                    mediaPlayer.start();
                    startTimer();
                    currentStatus = PLAY_STATE_PLAYER;
                    if(iPlayerViewControl != null){
                        iPlayerViewControl.onPlayerStateChange(currentStatus);
                    }
                    break;
            }
        }


        public void IStopPlay() {
            stopTimer();
            Log.d(TAG, "stopPlay...");
            currentStatus = PLAY_STATE_STOP;
            if (iPlayerViewControl != null) {
                iPlayerViewControl.onPlayerStateChange(currentStatus);
            }
            if(mediaPlayer != null && mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }

        }

        public void ISeekTo(int seek) {
            Log.d(TAG, "seekTo..." + seek);
            if (mediaPlayer != null) {
                int targetseek = (int)(seek * 1.0f/100 * mediaPlayer.getDuration());

                mediaPlayer.seekTo(targetseek);
            }

        }
        /**
         * 初始化播放器
         */
        private void initPlayer() {
            Log.d(TAG, "initPlayer...");

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }

        private void startTimer(){
                if (mSeekTimeTask == null) {
                    mSeekTimeTask = new SeekTimeTask();
                }
                if(mTimer == null) {
                    mTimer = new Timer();
                }
                mTimer.schedule(mSeekTimeTask, 0, 500);
        }
        private void stopTimer(){
            if (mSeekTimeTask != null) {
                mSeekTimeTask.cancel();
                mSeekTimeTask = null;
            }
            if(mTimer != null) {
                mTimer.cancel();
                mTimer = null;
            }
        }



    private class SeekTimeTask extends TimerTask{

            @Override
            public void run() {
                int currentPosition = mediaPlayer.getCurrentPosition();
                int currentseek = (int)(currentPosition*1.0/mediaPlayer.getDuration()* 100);
//                Log.d(TAG, "||||" + mediaPlayer.getCurrentPosition()+ "-------" +mediaPlayer.getDuration());
                if(Math.abs(mediaPlayer.getCurrentPosition() - mediaPlayer.getDuration()) < 500){
                    String info = String.format("00:00");
                    currentseek = 0;
                    iPlayerViewControl.onSeekChange(currentseek, info);
                    IStopPlay();
                    return;
                }
                if(iPlayerViewControl != null){
                    String info = String.format("%d:%02d/%d:%02d",currentPosition/60000, currentPosition/1000%60, mediaPlayer.getDuration()/60000,mediaPlayer.getDuration()/1000%60);
//                    Log.d(TAG, "INFO:"+ info );
                    iPlayerViewControl.onSeekChange(currentseek, info);
                }
            }
        }
    }

