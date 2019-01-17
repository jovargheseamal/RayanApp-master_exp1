package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddEmployee extends BaseActivity {

    TextView txtempl, SubmitEmp,CompName;


    String name, cName, contactNo, fileno, passportNum, Emid,PassExp, workPermit,workPermitEXP,VisaExp, uidno, address, dob, email,logUsID;


    String Bday, bmnth, byr, pday, pmnth, pyear, wpday, wpmnth, wpyear, vday, vmonth, vyear, compID, cname, toolbar, empid;

    ProgressDialog asyncDialog;

    EditText  EmpName, Address, Contact, Email, EMID, PassportNO, WorkPermit, UIDno, FileNo;

    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
            "31"};

   // String array_Mnth[] = {"MM", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    String array_Mnth[] = {"MM", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    String array_Year[] = {"YY", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040"};


    String array_BYear[] = {"YY", "1985", "1986", "1986", "1987", "1988", "1989", "1990", "1991", "1992",
            "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001","2002"
            , "2003", "2004", "2005", "2006","2007","2008","2009","2010","2011","2012",
            "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018"};


    Spinner bday, bmonth, byear, pEXP_Day, pEXP_Month, pEXP_Year, wpEXP_Day, wpEXP_Month, wpEXP_Year, vEXP_Day, vEXP_Month, vEXP_Year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_add_employee_ed, contentFrameLayout);

        Bundle abBundle = getIntent().getExtras();
        compID = abBundle.getString("CompID");
        cname = abBundle.getString("CompanyName");
        toolbar = abBundle.getString("ToolText");
        empid = abBundle.getString("EmpID");

        SharedPreferences keys =getSharedPreferences("MyPref",MODE_PRIVATE);
        logUsID = keys.getString("LoginUserID",null);


        Log.e("cnameeeeeee", "" + compID);


        ///spinner intialization//////////

        bday = (Spinner) findViewById(R.id.emp_DB_day);
        bmonth = (Spinner) findViewById(R.id.emp_DB_mnth);
        byear = (Spinner) findViewById(R.id.emp_DB_year);


        pEXP_Day = (Spinner) findViewById(R.id.pExp_Day);
        pEXP_Month = (Spinner) findViewById(R.id.pExp_Month);
        pEXP_Year = (Spinner) findViewById(R.id.pExp_Year);


        wpEXP_Day = (Spinner) findViewById(R.id.wpExp_Day);
        wpEXP_Month = (Spinner) findViewById(R.id.wpExp_Month);
        wpEXP_Year = (Spinner) findViewById(R.id.wpExp_Year);


        vEXP_Day = (Spinner) findViewById(R.id.vExp_Day);
        vEXP_Month = (Spinner) findViewById(R.id.vExp_Month);
        vEXP_Year = (Spinner) findViewById(R.id.vExp_Year);


        ////////////////

        CompName = findViewById(R.id.emp_comp);
        CompName.setText(cname);
        EmpName = findViewById(R.id.emp_name);
        Address = findViewById(R.id.empAddress);
        Contact = findViewById(R.id.empPh);
        Email = findViewById(R.id.empEmail);
        EMID = findViewById(R.id.empEMID);
        PassportNO = findViewById(R.id.empPasspotNO);
        WorkPermit = findViewById(R.id.EMPWorkPermit);
        UIDno = findViewById(R.id.UIDno);
        FileNo = findViewById(R.id.FileNo);
        SubmitEmp = findViewById(R.id.sbmtEMP);

        CompName.setText(cname);
        ///day////////

        final ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bday.setAdapter(spinner_adapter);
        pEXP_Day.setAdapter(spinner_adapter);
        wpEXP_Day.setAdapter(spinner_adapter);
        vEXP_Day.setAdapter(spinner_adapter);


        bday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bday = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        pEXP_Day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pday = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        wpEXP_Day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wpday = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        vEXP_Day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vday = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ////month////////

      final  ArrayAdapter<String> _adaptermnth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bmonth.setAdapter(_adaptermnth);
        pEXP_Month.setAdapter(_adaptermnth);
        wpEXP_Month.setAdapter(_adaptermnth);
        vEXP_Month.setAdapter(_adaptermnth);


        bmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bmnth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        pEXP_Month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pmnth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        wpEXP_Month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wpmnth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        vEXP_Month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vmonth = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ////year////////


       final ArrayAdapter<String> adapteryear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Year);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pEXP_Year.setAdapter(adapteryear);
        wpEXP_Year.setAdapter(adapteryear);
        vEXP_Year.setAdapter(adapteryear);


        pEXP_Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pyear = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        wpEXP_Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wpyear = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        vEXP_Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vyear = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       final ArrayAdapter<String> _adapteryear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_BYear);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        byear.setAdapter(_adapteryear);


        byear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                byr = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Toolbar tb = getToolBar();
        txtempl = (TextView) tb.findViewById(R.id.appname);
        txtempl.setText(toolbar);


        if (toolbar.equals("Edit Employee")) {


            String URL = this.getString(R.string.Local_URL)+"/api/EmployeeApi/GetEmpById";


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            asyncDialog.dismiss();

                            Log.e("uttutut", "" + response);


                            try {

                                Log.e("uttutut", "" + response);
                                JSONObject o = new JSONObject(response);
                                JSONArray json_array = o.getJSONArray("EmployeeDetails");


//                                try {
//                                    jsonarray = response.getJSONArray("Table1");
//                                    jsonarray1 = object.getJSONArray("Table1");
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }

                           /* JSONObject json_object =new JSONObject(response);
                            JSONArray json_array=json_object.getJSONArray(response);*/
//                                JSONArray json_array = new JSONArray(response);
                                int j;
                                for (j = 0; j < json_array.length(); j++) {
                                    o = json_array.getJSONObject(j);
                                    name = o.getString("EmpName");
                                    address = o.getString("Address");
                                    contactNo = o.getString("PhoneNo");
                                    dob = o.getString("DOB");
                                    email = o.getString("EmailID");
                                    Emid = o.getString("EmiratesID");
                                    passportNum = o.getString("PassportNO");
                                    workPermit = o.getString("WorkPermit");
                                    uidno = o.getString("UIDNumber");
                                    fileno = o.getString("FileNumber");
                                    cname = o.getString("CompanyName");
                                    workPermitEXP=o.getString("WorkPermitExpiry");
                                    VisaExp= o.getString("VisaExpiry");
                                    PassExp = o.getString("PassportExpiry");
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Date d = null;
                            try {
                                d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(dob);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(d);







                            Bday = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
                            try {
                                bmnth = getMonth(dob);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            byr = Integer.toString(cal.get(Calendar.YEAR));
                          //  Log.e("Monthhhhhhhhhh", "" + month_name);

                            int bd = spinner_adapter.getPosition(Bday);
                            int bm = _adaptermnth.getPosition(bmnth);
                            int by = _adapteryear.getPosition(byr);


                            try {
                                d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(VisaExp);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            cal.setTime(d);
                            Log.e("Monthhhhhhhhhh", "" + d);


                            vday = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
                            try {
                                vmonth = getMonth(VisaExp);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            vyear = Integer.toString(cal.get(Calendar.YEAR));


                            int vd = spinner_adapter.getPosition(vday);
                            int vm = _adaptermnth.getPosition(vmonth);
                            int vy = adapteryear.getPosition(vyear);



                            try {
                                d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(workPermitEXP);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            cal.setTime(d);
                            Log.e("Monthhhhhhhhhh", "" + d);


                            wpday = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
                            try {
                                wpmnth = getMonth(workPermitEXP);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            wpyear = Integer.toString(cal.get(Calendar.YEAR));


                            int wd = spinner_adapter.getPosition(wpday);
                            int wpm = _adaptermnth.getPosition(wpmnth);
                            int wpy = adapteryear.getPosition(wpyear);




                            try {
                                d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(PassExp);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            cal.setTime(d);
                            Log.e("Monthhhhhhhhhh", "" + d);


                            pday = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
                            try {
                                pmnth = getMonth(PassExp);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            pyear = Integer.toString(cal.get(Calendar.YEAR));



                            int pd = spinner_adapter.getPosition(pday);
                            int pm = _adaptermnth.getPosition(pmnth);
                            int py = adapteryear.getPosition(pyear);


                            pEXP_Day.setSelection(pd);
                            pEXP_Month.setSelection(pm);
                            pEXP_Year.setSelection(py);



                            bday.setSelection(bd);
                            bmonth.setSelection(bm);
                            byear.setSelection(by);


                            vEXP_Day.setSelection(vd);
                            vEXP_Month.setSelection(vm);
                            vEXP_Year.setSelection(vy);

                            wpEXP_Day.setSelection(wd);
                            wpEXP_Month.setSelection(wpm);
                            wpEXP_Year.setSelection(wpy);

                            EmpName.setText(name);
                            CompName.setText(cname);
                            Contact.setText(contactNo);
                            FileNo.setText(fileno);
                            PassportNO.setText(passportNum);
                            EMID.setText(Emid);
                            WorkPermit.setText(workPermit);
                            UIDno.setText(uidno);
                            Address.setText(address);
                            // DOB.setText(dob);
                            Email.setText(email);

                            //  Name,Cname, ContactNo, Fileno, PassportNum, EmID, WorkPermit, UIDno,Address,DOB,Email;

                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            asyncDialog.dismiss();
                            Toast.makeText(AddEmployee.this, error.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("EmpID", empid);


                    Log.e("params", "" + param);
                    return param;


                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> param = new HashMap<String, String>();
                    param.put("Content-Type", "application/x-www-form-urlencoded");
                    return param;
                }
            };

            // Volley.getInstance(this).addToRequestQueue(stringRequest);
            RequestQueue requestQueue = Volley.newRequestQueue(AddEmployee.this);
            requestQueue.add(stringRequest);
            // Log.e("reqqqqqqqqqq",""+stringRequest.toString());


        }


////////spinner Adapter Init end //////////////

        asyncDialog = new ProgressDialog(this);


        SubmitEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDialog.setTitle("Employee Registration");
                asyncDialog.setMessage("On Process...");
                asyncDialog.show();
                EmployeeRegister();

            }
        });


    }


    public void EmployeeRegister() {


        String URL = this.getString(R.string.Local_URL)+"/api/EmployeeApi/AddEditEmployee";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject object = new JSONObject(response);
//                            JSONArray jsonArray   = object.getJSONArray("SaveCompanyResult");
//                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                       /*     code= jsonObject.getString("responseCode");
                            message=jsonObject.getString("responseMessage");
                            Log.e("uttutut", "" + code);

                            if (code.equals("0")) {
                                Intent intent = new Intent(AddEmployee.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(AddEmployee.this, "Registration Success", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(AddEmployee.this,  message, Toast.LENGTH_LONG).show();

                            }*/

                            Intent intent = new Intent(AddEmployee.this, MainActivity.class);
                            intent.putExtra("Key", "Employee");
                            intent.putExtra("CompID", compID);
                            startActivity(intent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        asyncDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                // param.put("OwnerName","");

                param.put("CompId", compID);
                param.put("EmpName", EmpName.getText().toString());
                param.put("Address", Address.getText().toString());
                param.put("PhoneNo", Contact.getText().toString());
                param.put("DOB", Bday + "-" + bmnth + "-" + byr);
                param.put("EmailID", Email.getText().toString());
                param.put("EmiratesID", EMID.getText().toString());
                param.put("PassportNO", PassportNO.getText().toString());
                param.put("PassportExpiry", pday + "-" + pmnth + "-" + pyear);
                param.put("WorkPermit", WorkPermit.getText().toString());
                param.put("WorkPermitExpiry", wpday + "-" + wpmnth + "-" + wpyear);
                param.put("VisaExpiry", vday + "-" + vmonth + "-" + vyear);
                param.put("UIDNumber", UIDno.getText().toString());
                param.put("FileNumber", FileNo.getText().toString());
                param.put("Mode", toolbar.substring(0, 1));
                param.put("LoginUserID", logUsID);

                if (toolbar.substring(0,1).equals("E"))
                {
                    param.put("EmpID",empid);
                }

                Log.e("params", "" + param);
                return param;


            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("Content-Type", "application/x-www-form-urlencoded");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(AddEmployee.this);
        requestQueue.add(stringRequest);


    }

    private static String getMonth(String date) throws ParseException{
        Date d = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMM").format(cal.getTime());

        return monthName;

    }

}



