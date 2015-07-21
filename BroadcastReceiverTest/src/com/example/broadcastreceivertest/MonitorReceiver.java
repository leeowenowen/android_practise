package com.example.broadcastreceivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MonitorReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /**
         * <pre>
         * ACTION_PACKAGE_ADDED 一个新应用包已经安装在设备上，数据包括包名（最新安装的包程序不能接收到这个广播）
         * ACTION_PACKAGE_REPLACED 一个新版本的应用安装到设备，替换之前已经存在的版本
         * ACTION_PACKAGE_CHANGED 一个已存在的应用程序包已经改变，包括包名 
         * ACTION_PACKAGE_REMOVED 一个已存在的应用程序包已经从设备上移除，包括包名（正在被安装的包程序不能接收到这个广播） 
         * ACTION_PACKAGE_RESTARTED 用户重新开始一个包，包的所有进程将被杀死，所有与其联系的运行时间状态应该被移除，包括包名（重新开始包程序不能接收到这个广播）
         * ACTION_PACKAGE_DATA_CLEARED 用户已经清楚一个包的数据，包括包名（清除包程序不能接收到这个广播）
         * </pre>
         */
        // 接收安装广播
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();
            System.out.println("NEW INSTALL:" + packageName + "包名的程序");
        }
        // 接收卸载广播
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String packageName = intent.getDataString();
            System.out.println("NEW UNINSTALL:" + packageName + "包名的程序");
        }
    }

}
