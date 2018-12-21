package com.example.user123.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    public Toolbar toolbar;
    public TextView head;
    private NavigationView navigationView;

    public Toolbar getToolBar(){
        return toolbar;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        head =findViewById(R.id.appname);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout2);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                final String appPackageName = getPackageName();

                int id = item.getItemId();

                switch(id) {
                    case R.id.home:
                        Intent intent = new Intent(BaseActivity.this, HomeActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.Comp:
                        intent = new Intent(BaseActivity.this, MainActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.notify:
                        intent = new Intent(BaseActivity.this, Notification.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.Dashboard:
                        intent = new Intent(BaseActivity.this, Dashboard.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.cntct:
                        intent = new Intent(BaseActivity.this, Contact.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.Logout:
                        intent = new Intent(BaseActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawers();
                        break;

                    default:
                }



                return false;
            }
        });



    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        toggle.syncState();
    }

//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//     switch(id) {
//        case R.id.home:
//            Intent intent = new Intent(BaseActivity.this, HomeActivity.class);
//            startActivity(intent);
//
//            break;
//
//         case R.id.Comp:
//             intent = new Intent(BaseActivity.this, MainActivity.class);
//             startActivity(intent);
//             break;
//         case R.id.notify:
//             intent = new Intent(BaseActivity.this, Dashboard.class);
//             startActivity(intent);
//             break;
//
//
//    }
//        return true;
//    }
//
//    //    public void onNavigationItemSelected(MenuItem menuItem) {
////
////    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


      //if (toggle.onOptionsItemSelected(item)){
            return super.onOptionsItemSelected(item);






    }



}
