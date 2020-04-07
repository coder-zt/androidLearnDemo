package com.zhangtao.androidlearndemo.interfaces;

public interface IPlayerControl {

    /**
     * 注册
     * @param viewControl
     */
    void registerViewController(IPlayerViewControl viewControl);

    /**
     * 取消测试
     */
    void unRegisterViewController();
    /**
     * 播放或暂停
     */
    void palyOrPause();



    /**
     * 停止播放
     */
    void stopPlay();
    /**
     * 更新进度条
     * @param seek
     */
    void seekTo(int seek);
}
