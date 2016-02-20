
package com.example.servicetest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;

public class MainActivity extends Activity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // switch1: start-stop service test
        Switch switch1 = new Switch(this);
        switch1.setText("Start_Stop");
        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    startService(new Intent(MainActivity.this, StartStopService.class));
                } else {
                    stopService(new Intent(MainActivity.this, StartStopService.class));
                }
            }
        });
        // switch2: bind-unbind service test
        Switch switch2 = new Switch(this);
        switch2.setText("Bind-Unbind");
        switch2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    bindService(new Intent(MainActivity.this, BindUnbindService.class),
                            mServiceConnection, Context.BIND_AUTO_CREATE);
                } else {
                    unbindService(mServiceConnection);
                }
            }
        });
        
        // switch2: start-stop service test from broadcast
        Switch switch3 = new Switch(this);
        switch3.setText("Bind-Unbind");
        switch3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
         
                } else {
                   
                }
            }
        });

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 200);
        container.addView(switch1, lParams);
        container.addView(switch2, lParams);
        container.addView(switch3, lParams);
        setContentView(container);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(LogTag.TAG, "onServiceDisconnected:" + name);
        }
        
        private IFooAIDLService mService;
        
        private Callback mCallback = new Callback.Stub() {
            
            @Override
            public void run(String result) throws RemoteException {
                Log.v(LogTag.TAG, "async result:" + result);
            }
        };

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(LogTag.TAG, "onServiceConnected:" + name);
            mService = IFooAIDLService.Stub.asInterface(service);
            Log.v(LogTag.TAG, "mService:" + mService);
            try {
                String result = mService.foo("abc", mCallback);
                Log.v(LogTag.TAG,"foo result:" + result);
            } catch (RemoteException e) {
                Log.v(LogTag.TAG, "Exception on foo:" + e.toString());
            }
//            Parcel requestData = Parcel.obtain();
//            requestData.writeString("my request!");
//            Parcel replayData = Parcel.obtain();
//            try {
//                /*
//                 * FLAG_ONEWAY,如果不指定这个标志位（使用0做参数），则reply会同步返回，如果指定了，则
//                 * reply不会立刻返回（一般reply会是null).
//                 */
//                service.transact(20, requestData, replayData, IBinder.FLAG_ONEWAY);
//                String reply = replayData.readString();
//                Log.v(LogTag.TAG, "transaction [reply:" + reply + "]");
//                requestData.recycle();
//                replayData.recycle();
//            } catch (RemoteException e) {
//                Log.v(LogTag.TAG, "transact exception:" + e.toString());
//            }
        }
    };
}
