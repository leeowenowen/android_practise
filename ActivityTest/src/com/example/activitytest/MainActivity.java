package com.example.activitytest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {

    // @Override
    // public String getPackageName() {
    // // TODO Auto-generated method stub
    // return "abc";
    // }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
    }

    @Override
    protected void onDestroy() {
        Log.v("xyz", "onDestroy");
        super.onDestroy();
    }

    public static void release(Context context, String src, String dest) {
        int size;
        byte[] buffer = new byte[1024];
        try {
            InputStream input = context.getAssets().open(src);
            FileOutputStream output = new FileOutputStream(dest);
            while ((size = input.read(buffer)) > 0) {
                output.write(buffer, 0, size);
            }
            input.close();
            output.close();
            Log.d("AssetsReleaseUtil", "拷贝文件 " + src + "到" + dest + " 成功! ");
        } catch (Exception e) {
            Log.e("AssetsReleaseUtil", "拷贝文件 " + src + "到" + dest + " 失败: " + e.getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(this);
        btn.setText("task1 activity 0");
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v("xxx", "宿主apk中：" + getPackageName());
                Log.v("xxx", "开始动态加载...");
                String apkPath = getFilesDir() + File.separator + "dextest.apk";
                String odexPath = getFilesDir() + File.separator + "odex";
                File f = new File(odexPath);
                if(!f.exists())
                {
                    f.mkdirs();
                }
                release(MainActivity.this, "dextest.apk", apkPath);
                DexClassLoader classLoader = new DexClassLoader(apkPath, odexPath, null, getClassLoader());
                String entryClass = "com.example.dextest.MainActivity";
                try {
                    Class mainClass = classLoader.loadClass(entryClass);
                    Object mainObj = mainClass.newInstance();
                    Method foo = mainClass.getMethod("foo");
                    foo.invoke(mainObj, new Object[] {});
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // Intent intent = new Intent(MainActivity.this,
                // MainActivity1.class);
                // ContextProxy proxy = new ContextProxy(MainActivity.this,
                // "asdlfkjasdlkfj");
                // proxy.startActivity(intent);
                // // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // startActivity(intent);
                // new Handler().postDelayed(new Runnable() {
                //
                // @Override
                // public void run() {
                // // Intent intent = new Intent(MainActivity.this,
                // // MainActivity2.class);
                // // // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // // startActivity(intent);
                // Dialog d = new Dialog(MainActivity.this);
                // TextView t = new TextView(MainActivity.this);
                // t.setText("adlskjasldfjad");
                // d.setContentView(t);
                // d.show();
                // }
                // }, 5000);
                catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        setContentView(btn);
    }
}
