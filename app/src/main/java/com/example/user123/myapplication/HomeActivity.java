package com.example.user123.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeActivity extends BaseActivity implements OnMapReadyCallback {

    TextView switchtxt,dashtxt,name,uname,prof;
    private GoogleMap mMap;
    String ownerID,Name,Uname;
    LinearLayout btncmpny,btnprofile,btnnotify,btnnews,btncontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // test pulling



        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_home_edit, contentFrameLayout);


        name =findViewById(R.id.Name);
        uname =findViewById(R.id.Uname);
        prof =findViewById(R.id.profile);



        SharedPreferences keys =getSharedPreferences("MyPref",MODE_PRIVATE);
        ownerID =keys.getString("OwnerID",null);
        Name =keys.getString("LoginName",null);
        Uname =keys.getString("LoggedUsername",null);


        name.setText(Name);
        uname.setText(Uname);
        prof.setText(Name.substring(0,1));

        Log.e("cominggggggg","fdfdfdfdf"+ownerID);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



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
                Intent intent = new Intent(HomeActivity.this, ViewCompanyActivity.class);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(googleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        LatLng Rayan = new LatLng(25.432085,55.532238);
        mMap.addMarker(new MarkerOptions().position(Rayan).title("Rayan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Rayan));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Rayan,12));

    }
}
