package com.example.broadcastreceivertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        setContentView(R.layout.activity_main);
    }
}
