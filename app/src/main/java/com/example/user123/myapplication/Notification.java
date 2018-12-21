package com.example.user123.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Notification extends BaseActivity {


    TextView notifytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_notification, contentFrameLayout);

        Toolbar tb=getToolBar();
        notifytxt=(TextView)tb.findViewById(R.id.appname);
        notifytxt.setText("Notification");
    }
}
