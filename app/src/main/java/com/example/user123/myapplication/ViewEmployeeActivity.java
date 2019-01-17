package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class ViewEmployeeActivity extends BaseActivity {


    ImageView edit;
    String name,cname, contactNo, fileno, passportNum, Emid, workPermit, uidno,address,dob,email,empid,compId;
    TextView Name,Cname, ContactNo, Fileno, PassportNum, EmID, WorkPermit, UIDno,Address,DOB,Email,txtempl;
    ProgressDialog asyncdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_view_employee, contentFrameLayout);


        Toolbar tb=getToolBar();
        txtempl=(TextView)tb.findViewById(R.id.appname);
        txtempl.setText("Employee");


        Name = findViewById(R.id.Empname);
                Cname = findViewById(R.id.empComp);
                ContactNo= findViewById(R.id.empContact);
                Fileno= findViewById(R.id.empFNo);
                PassportNum = findViewById(R.id.empPPN);
                EmID= findViewById(R.id.empEMID);
                WorkPermit=  findViewById(R.id.empWP);
                UIDno= findViewById(R.id.empUIDNo);
                Address= findViewById(R.id.empAdd);
                DOB= findViewById(R.id.empDOB);
                Email= findViewById(R.id.empemail);
                edit= findViewById(R.id.editEmp);
        asyncdialog = new ProgressDialog(this);

        Bundle bundle =getIntent().getExtras();
        assert bundle != null;
        empid = bundle.getString("EmpID");

        ViewEmployee();


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewEmployeeActivity.this,AddEmployee.class);
                intent.putExtra("ToolText","Edit Employee");
                intent.putExtra("EmpID",empid);
                intent.putExtra("CompID",compId);
                startActivity(intent);
                finish();
            }
        });


    }



    public void ViewEmployee() {




        String URL = this.getString(R.string.Local_URL)+"/api/EmployeeApi/GetEmpById";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncdialog.dismiss();

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
                               compId=o.getString("CompId");
                                name =o.getString("EmpName");
                                address = o.getString("Address");
                                contactNo = o.getString("PhoneNo");
                                dob= o.getString("DOB");
                                email = o.getString("EmailID");
                                Emid = o.getString("EmiratesID");
                                passportNum = o.getString("PassportNO");
                                workPermit= o.getString("WorkPermit");
                                uidno= o.getString("UIDNumber");
                                fileno= o.getString("FileNumber");
                                cname = o.getString("CompanyName");


                            }






                        }catch (JSONException e){
                            e.printStackTrace();
                        }


                        Name.setText(name);


                        if (cname != null && !cname.isEmpty() && !cname.equals("null")) {
                            Cname.setText(cname);
                        }
                        else
                        {
                            Cname.setText("N/A");
                        }


                        if (contactNo != null && !contactNo.isEmpty() && !contactNo.equals("null")) {
                            ContactNo.setText(contactNo);
                        }
                        else
                        {
                            ContactNo.setText("N/A");
                        }


                        if (fileno != null && !fileno.isEmpty() && !fileno.equals("null")) {
                            Fileno.setText(fileno);
                        }
                        else
                        {
                            Fileno.setText("N/A");
                        }



                        if (passportNum != null && !passportNum.isEmpty() && !passportNum.equals("null")) {
                            PassportNum.setText(passportNum);
                        }
                        else
                        {
                            PassportNum.setText("N/A");
                        }

                        if (Emid != null && !Emid.isEmpty() && !Emid.equals("null")) {
                            EmID.setText(Emid);
                        }
                        else
                        {
                            EmID.setText("N/A");
                        }

                        if (workPermit != null && !workPermit.isEmpty() && !workPermit.equals("null")) {
                            WorkPermit.setText(workPermit);
                        }
                        else
                        {
                            WorkPermit.setText("N/A");
                        }

                        if (uidno != null && !uidno.isEmpty() && !uidno.equals("null")) {
                            UIDno.setText(uidno);
                        }
                        else
                        {
                            UIDno.setText("N/A");
                        }
                        if (address != null && !address.isEmpty() && !address.equals("null")) {
                            Address.setText(address);
                        }
                        else
                        {
                            Address.setText("N/A");
                        }


                        if (dob != null && !dob.isEmpty() && !dob.equals("null")) {
                            DOB.setText(dob);
                        }
                        else
                        {
                            DOB.setText("N/A");
                        }

                        if (email != null && !email.isEmpty() && !email.equals("null")) {
                            Email.setText(email);
                        }
                        else
                        {
                            Email.setText("N/A");
                        }




//                        Cname.setText(cname);
//                        ContactNo.setText(contactNo);
//                        Fileno.setText(fileno);
//                        PassportNum.setText(passportNum);
//                        EmID.setText(Emid);
//                        WorkPermit.setText(workPermit);
//                        UIDno.setText(uidno);
//                        Address.setText(address);
//                        DOB.setText(dob);
//                        Email.setText(email);

                        //  Name,Cname, ContactNo, Fileno, PassportNum, EmID, WorkPermit, UIDno,Address,DOB,Email;

                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        asyncdialog.dismiss();
                        Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue= Volley.newRequestQueue(ViewEmployeeActivity.this);
        requestQueue.add(stringRequest);
        // Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }



}
