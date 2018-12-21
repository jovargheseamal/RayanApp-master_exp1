package com.example.user123.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends BaseActivity {

    TextView switchtxt,dashtxt;

    LinearLayout btncmpny,btnprofile,btnnotify,btnnews,btncontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_home_edit, contentFrameLayout);


        switchtxt = (TextView)findViewById(R.id.txtswitchaccnt);
        switchtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        dashtxt = (TextView)findViewById(R.id.txtdashboard);
        dashtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });


        btncmpny = (LinearLayout)findViewById(R.id.cmpnylay);
        btncmpny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        btnprofile = (LinearLayout)findViewById(R.id.proflay);
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnnotify = (LinearLayout)findViewById(R.id.notflay);
        btnnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Notification.class);
                startActivity(intent);
            }
        });
        btnnews = (LinearLayout)findViewById(R.id.newslay);
        btnnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewsFeed.class);
                startActivity(intent);
            }
        });
        btncontact = (LinearLayout)findViewById(R.id.contactlay);
        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Contact.class);
                startActivity(intent);
            }
        });


    }
}
