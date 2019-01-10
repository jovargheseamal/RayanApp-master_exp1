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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    String OwnerID,userType,company, cp, trl, trn, lcn, icn, tncy,compID,Key;

    TextView tvAppName;

    int id;
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
        compID = abBundle.getString("CompID");
        company = abBundle.getString("CompanyName");
        cp = abBundle.getString("ContactPerson");
        trl = abBundle.getString("TradeLicence");
        trn = abBundle.getString("TRN");
        lcn = abBundle.getString("LabourCNo");
        icn = abBundle.getString("ImmigrationNo");
        tncy = abBundle.getString("Tenancy");
        Key =  abBundle.getString("Key");



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
        final EmpFragment empFragment=new EmpFragment();

        Toolbar tb=getToolBar();
        tvAppName=(TextView)tb.findViewById(R.id.appname);
        tvAppName.setText("Company");






        if(Key.equals("Company"))
      {

          Bundle bundle= new Bundle();

//          bundle.putString("CompanyName",company);
          bundle.putString("CompID",compID);
//          bundle.putString("ContactPerson",cp);
//          bundle.putString("TradeLicence",trl);
//          bundle.putString("LabourCNo",lcn);
//          bundle.putString("LabourCNo",trn);
//          bundle.putString("ImmigrationNo",icn);
//          bundle.putString("Tenancy",tncy);
          companyFragment.setArguments(bundle);
          empFragment.setArguments(bundle);
          userFragment.setArguments(bundle);
          setFragment(companyFragment);


      }



       else if (Key.equals("User"))

       {

           Bundle bundle= new Bundle();
           bundle.putString("CompID",compID);
           userFragment.setArguments(bundle);
           setFragment(userFragment);
           companyFragment.setArguments(bundle);
           navigationMenu.getMenu().findItem(R.id.users).setChecked(true);



        }
        else if (Key.equals("Employee"))

        {

            Bundle bundle= new Bundle();
            bundle.putString("CompID",compID);
            empFragment.setArguments(bundle);
            companyFragment.setArguments(bundle);
            setFragment(empFragment);
            navigationMenu.getMenu().findItem(R.id.emp).setChecked(true);



        }








        navigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                id = menuItem.getItemId();
                if( id == R.id.company) {


                    Bundle bundle= new Bundle();
                    bundle.putString("CompID",compID);
                    tvAppName.setText("Company");
                    companyFragment .setArguments(bundle);
                    setFragment(companyFragment);


                    return true;

                }
                else if (id == R.id.users)
                {
                    Bundle bundle= new Bundle();
                    bundle.putString("CompID",compID);
                    userFragment.setArguments(bundle);


                    tvAppName.setText("Users");
                    setFragment(userFragment);
                    return true;
                }
                else if (id == R.id.emp)
                {
                    Bundle bundle= new Bundle();
                    bundle.putString("CompID",compID);
                    empFragment.setArguments(bundle);
                    tvAppName.setText("Employees");
                    setFragment(empFragment);
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
