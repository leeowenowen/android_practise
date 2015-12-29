package com.example.broadcastreceivertest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tView = new TextView(this);
        tView.setBackgroundColor(Color.YELLOW);
        setContentView(tView);
        new Handler().postDelayed(new Runnable() {
            
            @Override
            public void run() {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        }, 20*1000);
    }


}
