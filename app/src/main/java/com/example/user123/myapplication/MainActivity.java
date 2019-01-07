package com.example.user123.myapplication;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    String OwnerID,userType,company, cp, trl, trn, lcn, icn, tncy,compID;

    TextView tvAppName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        SharedPreferences keys =getSharedPreferences("MyPref",MODE_PRIVATE);
        OwnerID =keys.getString("OwnerID",null);
        userType =keys.getString("UserType",null);

        Bundle abBundle= getIntent().getExtras();

        assert abBundle != null;
        compID =abBundle.getString("CompID");
        company = abBundle.getString("CompanyName");
        cp = abBundle.getString("ContactPerson");
        trl = abBundle.getString("TradeLicence");
        trn = abBundle.getString("TRN");
        lcn = abBundle.getString("LabourCNo");
        icn = abBundle.getString("ImmigrationNo");
        tncy = abBundle.getString("Tenancy");


        BottomNavigationView navigationMenu =(BottomNavigationView) findViewById(R.id.navigation);

        if(userType.equals("PRO")) {

            navigationMenu.getMenu().removeItem(R.id.users);

//            Menu menu = navigationMenu.getMenu();
//
//            MenuItem target = menu.findItem(R.id.users);
//            target.setVisible(false);
        }

        final CompanyFragment companyFragment = new CompanyFragment();
        final UserFragment userFragment = new UserFragment();
        final EmployeeFragment employeeFragment=new EmployeeFragment();

        Toolbar tb=getToolBar();
        tvAppName=(TextView)tb.findViewById(R.id.appname);
        tvAppName.setText("Company");


        Bundle bundle= new Bundle();

        bundle.putString("CompanyName",company);
        bundle.putString("CompanyID",compID);
        bundle.putString("ContactPerson",cp);
        bundle.putString("TradeLicence",trl);
        bundle.putString("LabourCNo",lcn);
        bundle.putString("LabourCNo",trn);
        bundle.putString("ImmigrationNo",icn);
        bundle.putString("Tenancy",tncy);
        companyFragment.setArguments(bundle);
        employeeFragment.setArguments(bundle);
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
