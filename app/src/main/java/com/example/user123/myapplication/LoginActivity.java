package com.example.user123.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView =findViewById(R.id.reglink);
       button = findViewById(R.id.login);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(LoginActivity.this,HomeActivity.class);
               startActivity(intent);
           }
       });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
