package com.example.broadcastreceivertest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tView = new TextView(this);
        tView.setBackgroundColor(Color.BLUE);
        setContentView(tView);
    }


}
