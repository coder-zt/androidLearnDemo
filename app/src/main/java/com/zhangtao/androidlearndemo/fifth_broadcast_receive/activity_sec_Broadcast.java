package com.zhangtao.androidlearndemo.fifth_broadcast_receive;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zhangtao.androidlearndemo.R;

public class activity_sec_Broadcast extends Activity {
    private final String TAG = "activity_sec_Broadcast";
    private TextView tvShowone, tvShowtwo;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_broadcast);
        tvShowone = findViewById(R.id.tv_level);
        tvShowtwo = findViewById(R.id.tv_status);
        setBroadcastReceiver();

    }

    /**
     * 设置广播接收者
     */
    private void setBroadcastReceiver() {
        //设置监听
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        //new 广播接收对象
        broadcastReceiver = new BatteryLevelReceiver();
        //注册广播接收器
        this.registerReceiver(broadcastReceiver, intentFilter);

    }

    /**
     * 发送自定义广播
     * @param view
     */
    public void sendbroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("this.is.a.broadcast");
        intent.setComponent(new ComponentName("com.zhangtao.androidlearndemo",
                "com.zhangtao.androidlearndemo.broadcastReceive"));
        intent.putExtra("data", "武汉加油");
        Log.d(TAG, "马上发送广播");
        sendBroadcast(intent);
    }

    /**
     * 1.创建广播->继承于BroadcastReceiver
     */
    private class BatteryLevelReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int maxLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                if (level != -1 && maxLevel != -1) {
                    tvShowone.setText(String.format("当前电量：%d,剩余电量：%3.2f",level, level/(double)maxLevel*100) + "% 。");
                }
            }else if( action.equals(Intent.ACTION_POWER_CONNECTED) ){
                tvShowtwo.setText("数据线已连接");

            }else if( action.equals(Intent.ACTION_POWER_DISCONNECTED) ){
                tvShowtwo.setText("数据线已断开");
            }else if( action.equals(Intent.ACTION_PACKAGE_ADDED) ){
                Log.d(TAG, "应用已安装==="+ intent.getDataString());
            } else if( action.equals(Intent.ACTION_PACKAGE_REMOVED) ){
            Log.d(TAG, "应用已卸载===="+ intent.getDataString());
        }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            this.unregisterReceiver(broadcastReceiver);
        }
    }

}
