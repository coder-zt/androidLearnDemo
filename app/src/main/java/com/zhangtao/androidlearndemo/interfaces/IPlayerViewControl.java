package com.zhangtao.androidlearndemo.interfaces;

import android.content.Context;

public interface IPlayerViewControl {
    //播发器状态
    int PLAY_STATE_PLAYER = 1;
    int PLAY_STATE_PAUSE = 2;
    int PLAY_STATE_STOP = 3;
    /**
     * 播放状态改变通知
     * @param state
     */
    void onPlayerStateChange(int state);

    /**
     * 播放进度的改变
     * @param seek
     */
    void onSeekChange(int seek, String info);
}
