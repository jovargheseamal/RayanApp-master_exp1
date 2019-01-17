package com.example.user123.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends BaseActivity {

    TextView prftxt;
CardView uploadpic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);
        uploadpic=(CardView) findViewById(R.id.profup);
        Toolbar tb=getToolBar();
        prftxt=(TextView)tb.findViewById(R.id.appname);
        prftxt.setText("Profile");
        uploadpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Available soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
