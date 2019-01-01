package com.example.user123.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ViewCompanyActivity extends BaseActivity {


    FloatingActionButton floatbttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.companylist, contentFrameLayout);

        CardView linearLayout =findViewById(R.id.list);

        floatbttn = (FloatingActionButton) findViewById(R.id.fab5);

        floatbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCompanyActivity.this, AddCompany.class);
                startActivity(intent);

            }
        });





        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ViewCompanyActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
