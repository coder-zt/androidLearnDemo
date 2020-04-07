package com.zhangtao.androidlearndemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import static com.zhangtao.androidlearndemo.NotifcationUtil.EVENT_KEY;
import static com.zhangtao.androidlearndemo.NotifcationUtil.PLAY_COLLECT;
import static com.zhangtao.androidlearndemo.NotifcationUtil.PLAY_NEXT;
import static com.zhangtao.androidlearndemo.NotifcationUtil.PLAY_OR_PAUSE;
import static com.zhangtao.androidlearndemo.NotifcationUtil.PLAY_PER;
import static com.zhangtao.androidlearndemo.NotifcationUtil.PLAY_SPEED;

public class NotificationService extends Service {
    private final static String TAG = "NotificationService";
    private INotificationEvent mCallback;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new InnerController();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + intent.getIntExtra(EVENT_KEY, -1));
        handleResult(intent.getIntExtra(EVENT_KEY, -1));
        return super.onStartCommand(intent, flags, startId);
    }

    private void handleResult(int info) {
        if(info == -1){
            return;
        }
        switch (info){
            case PLAY_OR_PAUSE:

                if (mCallback != null) {
                    mCallback.playOrPauseClick();
                }
                break;
            case PLAY_COLLECT:
                if (mCallback != null) {
                    mCallback.collectClick();
                }
                break;
            case PLAY_NEXT:
                if (mCallback != null) {
                    mCallback.nextClick();
                }
                break;
            case PLAY_PER:
                if (mCallback != null) {
                    mCallback.preClick();
                }
                break;
            case PLAY_SPEED:
                if (mCallback != null) {
                    mCallback.speedClick();
                }
                break;

            default:
                break;
        }
    }

    class InnerController extends Binder {
        void registerInnerCallback(INotificationEvent event){
            registerCallback(event);
        }
    }

    private void registerCallback(INotificationEvent event) {
        mCallback = event;
    }

    public interface INotificationEvent {

        void collectClick();

        void preClick();

        void playOrPauseClick();

        void nextClick();

        void speedClick();
    }
}
