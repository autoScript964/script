package com.script.fairy;

/**
 * Created by Administrator on 2018-07-20.
 */


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author ZMC
 */
public class MyBroadcaseReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        System.out.println("-------------" + intent.getAction());
        Toast.makeText(context, "因游戏进入防沉迷时间，游戏将在" + intent.getAction() + "分钟后重新启动" , Toast.LENGTH_SHORT).show();
    }
}