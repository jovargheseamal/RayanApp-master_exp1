package com.example.user123.myapplication;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class AddEmployee extends BaseActivity {

    TextView txtempl;
    // test

    String array_day[] = {"Day", "1", "2", "3", "4", "5", "6","7","8","9","10",
            "11", "12", "13", "14", "15", "16","17","18","19","20","21", "22", "23", "24", "25", "26","27","28","29","30",
            "31"};

    String array_Mnth[] = {"Month","January","February","March","April","May","June","July","August","September","October","November","December"};

    String array_Year[] = {"2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035","2036","20237","2038","2039","2040"};


    Spinner day,month,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_add_employee_ed, contentFrameLayout);


  ///spinner intialization//////////

        day = (Spinner) findViewById(R.id.emp_DB_day);
        month = (Spinner) findViewById(R.id.emp_DB_mnth);
        year = (Spinner) findViewById(R.id.emp_DB_year);


        ///day////////

        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(spinner_adapter);

        ////month////////

        ArrayAdapter<String> _adaptermnth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(_adaptermnth);
       ////year////////


        ArrayAdapter<String> _adapteryear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Year);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(_adapteryear);

        Toolbar tb=getToolBar();
        txtempl=(TextView)tb.findViewById(R.id.appname);
        txtempl.setText("New Employee");


    }
}
