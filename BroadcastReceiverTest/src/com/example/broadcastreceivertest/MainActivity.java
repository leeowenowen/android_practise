package com.example.broadcastreceivertest;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /**
     * <pre>
     * 广播发送方分为普通广播和有序广播；
     * 同步广播：发送方发出后，几乎同时到达多个广播接收者处，某个接收者不能接收到广播后进行一番处理后传给下一个接收者，
     * 并且无法终止广播继续传播；Context.sendBroadcast(intent);
     * 有序广播：广播接收者需要提前设置优先级，优先级高的先接收到广播
     * ，优先级数值为-1000~1000，在AndroidManifest.xml的<intent-filter
     * android:priority="xxx"
     * >设置；比如存在3个广播接收者A、B、C，优先级A>B>C,因此A最先收到广播，当A收到广播后，可以向广播中添加一些数据给下一个接收者
     * (intent .putExtra())，或者终止广播(abortBroadcast())；Context.sendOrderedBroadcast( intent);
     * 
     * 
     * Receiver可以在ＡndroidManifest.xml中设置，也可以在代码中register, unregister
     * </pre>
     * 
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        Button btn = new Button(this);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + "/sdcard/a.apk"), "application/vnd.android.package-archive");
                startActivityForResult(intent, 200);
            }
        });

        setContentView(btn);
        mReceiver = new MonitorReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        registerReceiver(mReceiver, intentFilter);
        new Handler().postDelayed(new Runnable() {
            
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        }, 20*1000);
    }

    private MonitorReceiver mReceiver;

    /** call system install app window */
    public static void openInstaller(Context context, String apkPath) {
        if (context == null || TextUtils.isEmpty(apkPath)) {
            return;
        }

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        File file = new File(apkPath);
        if (file.exists() && file.isAbsolute()) {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        // IntentFilter filter = new IntentFilter();
        // filter.
        // registerReceiver(new MonitorReceiver(), filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
