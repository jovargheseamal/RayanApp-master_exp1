package com.example.user123.myapplication;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity {


    TextView tvAppName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        BottomNavigationView navigationMenu =(BottomNavigationView) findViewById(R.id.navigation);
        final CompanyFragment companyFragment = new CompanyFragment();
        final UserFragment userFragment = new UserFragment();
        final EmployeeFragment employeeFragment=new EmployeeFragment();

        Toolbar tb=getToolBar();
        tvAppName=(TextView)tb.findViewById(R.id.appname);
        tvAppName.setText("Company");

        setFragment(companyFragment);



        navigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if( id == R.id.company) {
                    tvAppName.setText("Company");
                    setFragment(companyFragment);
                    return true;

                }
                else if (id == R.id.users)
                {
                    tvAppName.setText("Users");
                    setFragment(userFragment);
                    return true;
                }
                else if (id == R.id.emp)
                {
                    tvAppName.setText("Employees");
                    setFragment(employeeFragment);
                    return true;
                }

                return false;

            }
        });

    }
    private  void  setFragment(Fragment fragment)
    {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }
}
