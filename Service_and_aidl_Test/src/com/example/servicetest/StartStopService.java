
package com.example.servicetest;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;

public class StartStopService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LogTag.TAG, "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(LogTag.TAG, "onCreate");
        super.onCreate();
    }

    @Override
    @Deprecated
    public void onStart(Intent intent, int startId) {
        Log.v(LogTag.TAG, "onStart");
        super.onStart(intent, startId);
    }

    /*
     * onStartCommand有4种返回值： 
     * START_STICKY：如果service进程被kill掉，保留service的状态为开始状态，但不保留递送的intent对象
     * 。随后系统会尝试重新创建service，由于服务状态为开始状态
     * ，所以创建服务后一定会调用onStartCommand(Intent,int,int)方法。如果在此期间没有任何启动命令被传递到service，那么参数Intent将为null。
     * START_NOT_STICKY：“非粘性的”。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统不会自动重启该服务。
     * START_REDELIVER_INTENT
     * ：重传Intent。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统会自动重启该服务，并将Intent的值传入。
     * START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，但不保证服务被kill后一定能重启。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(LogTag.TAG, "onStartCommand");
       // return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.v(LogTag.TAG, "onDestroy");
        stopSelf();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.v(LogTag.TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        Log.v(LogTag.TAG, "onLowMemory");
        super.onLowMemory();
    }

    /*
     * OnTrimMemory的参数是一个int数值，代表不同的内存状态： TRIM_MEMORY_COMPLETE：内存不足，并且该进程在后台进程列表最后一个，马上就要被清理
     * TRIM_MEMORY_MODERATE：内存不足，并且该进程在后台进程列表的中部。 TRIM_MEMORY_BACKGROUND：内存不足，并且该进程是后台进程。
     * TRIM_MEMORY_UI_HIDDEN：内存不足，并且该进程的UI已经不可见了。 以上4个是4.0增加
     * TRIM_MEMORY_RUNNING_CRITICAL：内存不足(后台进程不足3个)，并且该进程优先级比较高，需要清理内存
     * TRIM_MEMORY_RUNNING_LOW：内存不足(后台进程不足5个)，并且该进程优先级比较高，需要清理内存
     * TRIM_MEMORY_RUNNING_MODERATE：内存不足(后台进程超过5个)，并且该进程优先级比较高，需要清理内存
     */
    @SuppressLint("NewApi")
    @Override
    public void onTrimMemory(int level) {
        String strLevel = "";
        switch (level)
        {
            case TRIM_MEMORY_COMPLETE:
                strLevel = "TRIM_MEMORY_COMPLETE";
                break;
            case TRIM_MEMORY_MODERATE:
                strLevel = "TRIM_MEMORY_MODERATE";
                break;
            case TRIM_MEMORY_BACKGROUND:
                strLevel = "TRIM_MEMORY_BACKGROUND";
                break;
            case TRIM_MEMORY_UI_HIDDEN:
                strLevel = "TRIM_MEMORY_UI_HIDDEN";
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL:
                strLevel = "TRIM_MEMORY_RUNNING_CRITICAL";
                break;
            case TRIM_MEMORY_RUNNING_LOW:
                strLevel = "TRIM_MEMORY_RUNNING_LOW";
                break;
            case TRIM_MEMORY_RUNNING_MODERATE:
                strLevel = "TRIM_MEMORY_RUNNING_MODERATE";
                break;
            default:
                strLevel = "default";
                break;
        }
        Log.v(LogTag.TAG, "onTrimMemory[level:" + strLevel + "]");
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LogTag.TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LogTag.TAG, "onBind");
        super.onRebind(intent);
    }

    @SuppressLint("NewApi")
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.v(LogTag.TAG, "onTaskRemoved");
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        Log.v(LogTag.TAG, "dump");
        super.dump(fd, writer, args);
    }

}
