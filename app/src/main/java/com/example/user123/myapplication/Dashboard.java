package com.example.user123.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Dashboard extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_dashboard, contentFrameLayout);


    }
    public void whatever (TextView mTextView){

        mTextView.findViewById(R.id.appname);

        mTextView.setText ("Hey I'm from class PEOPLE!");
    }
}
