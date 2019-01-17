package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.util.Map;

public class EmployeeEdit extends BaseActivity {

    TextView txtempl,SubmitEmp,CompName;

    String VisaExpiry,WorkPermitExpiry,PassportExpiry;

    String name,cName, contactNo, fileno, passportNum, Emid, workPermit, uidno,address,dob,email,Bday,bmnth,byr,pday,pmnth,pyear,wpday,wpmnth,wpyear,vday,vmonth,vyear,compID,cname,toolbar,empid;

    EditText EmpName,Address,Contact,Email,EMID,PassportNO,WorkPermit,UIDno,FileNo,bday,bmonth,byear,pEXP_Day,pEXP_Month,pEXP_Year,wpEXP_Day,wpEXP_Month,wpEXP_Year,vEXP_Day,vEXP_Month,vEXP_Year;
    ProgressDialog asyncDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);


        asyncDialog = new ProgressDialog(this);

        Bundle abBundle= getIntent().getExtras();
        compID = abBundle.getString("CompID");
        cname = abBundle.getString("CompanyName");
        toolbar = abBundle.getString("ToolText");
        empid = abBundle.getString("EmpID");


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
        SubmitEmp =findViewById(R.id.sbmtEMP);


        bday =  findViewById(R.id.emp_DB_day);
        bmonth =  findViewById(R.id.emp_DB_mnth);
        byear =  findViewById(R.id.emp_DB_year);


        pEXP_Day =  findViewById(R.id.pExp_Day);
        pEXP_Month =  findViewById(R.id.pExp_Month);
        pEXP_Year =  findViewById(R.id.pExp_Year);


        wpEXP_Day =  findViewById(R.id.wpExp_Day);
        wpEXP_Month =  findViewById(R.id.wpExp_Month);
        wpEXP_Year =  findViewById(R.id.wpExp_Year);


        vEXP_Day =  findViewById(R.id.vExp_Day);
        vEXP_Month =  findViewById(R.id.vExp_Month);
        vEXP_Year = findViewById(R.id.vExp_Year);


        getEmployee();


        SubmitEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeRegister();
            }
        });


    }





    public void getEmployee() {




        String URL = "http://103.228.112.79:100/api/EmployeeApi/GetEmpById";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject o     = new JSONObject(response);
                            JSONArray json_array   = o.getJSONArray("EmployeeDetails");


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
                                name =o.getString("EmpName");
                                address = o.getString("Address");
                                contactNo = o.getString("PhonetNo");
                                dob= o.getString("DOB");
                                email = o.getString("EmailID");
                                Emid = o.getString("EmiratesID");
                                passportNum = o.getString("PassportNO");
                                workPermit= o.getString("WorkPermit");
                                uidno= o.getString("UIDNumber");
                                fileno= o.getString("FileNumber");
                                cname = o.getString("CompanyName");
                                PassportExpiry=o.getString("PassportExpiry");
                                WorkPermitExpiry=o.getString("WorkPermitExpiry");
                                VisaExpiry=o.getString("VisaExpiry");

                            }






                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                        SimpleDateFormat curFormater = new SimpleDateFormat("dd-MM-yyyy");
                        Date dateObj=null;
                        try {
                            dateObj = curFormater.parse(dob);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(dateObj.getTime());


Log.e("Monthhhhhhhhhh",""+cal.get(Calendar.MONTH));

                        String passdate =PassportExpiry;


                        Bday= dob.substring(0,2);
                        bmnth=dob.substring(2,4);
                        byr=dob.substring(0,2);

//                        bday.setText(Bday);
//                        bmonth.setText(bmnth);
//                        byear.setText(byr);

                        EmpName.setText(name);
                        CompName.setText(cname);
                        Contact.setText(contactNo);
                        FileNo.setText(fileno);
                        PassportNO.setText(passportNum);
                        EMID.setText(Emid);
                        WorkPermit.setText(workPermit);
                        UIDno.setText(uidno);
                        Address.setText(address);

                        Email.setText(email);

                        //  Name,Cname, ContactNo, Fileno, PassportNum, EmID, WorkPermit, UIDno,Address,DOB,Email;

                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        asyncDialog.dismiss();
                        Toast.makeText(EmployeeEdit.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("EmpID",empid);


                Log.e("params",""+param);
                return param;


            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("Content-Type","application/x-www-form-urlencoded");
                return param;
            }
        }
                ;

        // Volley.getInstance(this).addToRequestQueue(stringRequest);
        RequestQueue requestQueue= Volley.newRequestQueue(EmployeeEdit.this);
        requestQueue.add(stringRequest);
        // Log.e("reqqqqqqqqqq",""+stringRequest.toString());


    }




    public void EmployeeRegister() {



        String URL = "http://103.228.112.79:100/api/EmployeeApi/AddEditEmployee";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject object     = new JSONObject(response);
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

                            Intent intent = new Intent(EmployeeEdit.this, MainActivity.class);
                            intent.putExtra("Key","Company");
                            intent.putExtra("CompID",compID);
                            startActivity(intent);



                        }catch (JSONException e){
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
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                // param.put("OwnerName","");

                param.put("EmpID",EmpName.getText().toString());
                param.put("EmpName",EmpName.getText().toString());
                param.put("Address",Address.getText().toString());
                param.put("PhoneNo",Contact.getText().toString());
                param.put("DOB",Bday+"-"+bmnth+"-"+byr);
                param.put("EmailID",Email.getText().toString());
                param.put("EmiratesID",EMID.getText().toString());
                param.put("PassportNO",PassportNO.getText().toString());
                param.put("PassportExpiry",pday+"-"+pmnth+"-"+pyear);
                param.put("WorkPermit",WorkPermit.getText().toString());
                param.put("WorkPermitExpiry",wpday+"-"+wpmnth+"-"+wpyear);
                param.put("VisaExpiry",vday+"-"+vmonth+"-"+vyear);
                param.put("UIDNumber",UIDno.getText().toString());
                param.put("FileNumber",FileNo.getText().toString());
                param.put("Mode",toolbar.substring(0,1));



                Log.e("params",""+param);
                return param;


            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("Content-Type","application/x-www-form-urlencoded");
                return param;
            }
        }
                ;

        RequestQueue requestQueue= Volley.newRequestQueue(EmployeeEdit.this);
        requestQueue.add(stringRequest);


    }



}
