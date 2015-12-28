package com.example.test;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    static {
        System.loadLibrary("package_uninstall_watcher");
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JNIBridge.nativeStartWatch(getFilesDir().getAbsolutePath(), "http://www.baidu.com");
    }
}
