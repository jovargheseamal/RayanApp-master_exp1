package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {
    JSONArray json_array;
    String code,message;
    JSONObject jObj;
     ProgressDialog asyncDialog;

   private EditText OwnerName,CompName,Address,Email,Contact,UserName,Password;

    TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        OwnerName=(EditText)findViewById(R.id.OwnerName);
        CompName=(EditText)findViewById(R.id.cmpname);
        Address=(EditText)findViewById(R.id.addrs);
        Email=(EditText)findViewById(R.id.email);
        Contact=(EditText)findViewById(R.id.contactno);
        UserName=(EditText)findViewById(R.id.uname);
        Password=(EditText)findViewById(R.id.paswd);
         asyncDialog = new ProgressDialog(RegisterActivity.this);

        button = findViewById(R.id.regbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDialog.setMessage("Registration In Progress...");
                //show dialog
                asyncDialog.show();
                AdminRegister();

            }
        });

    }

    public void AdminRegister() {



        String URL = "http://192.168.0.30:7777/Service1.svc/adminRegister";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject object     = new JSONObject(response);
                            JSONArray jsonArray   = object.getJSONArray("SaveAdminUserResult");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            code= jsonObject.getString("responseCode");
                            message=jsonObject.getString("responseMessage");
                            Log.e("uttutut", "" + code);

                            if (code.equals("0")) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(RegisterActivity.this,  message, Toast.LENGTH_LONG).show();

                            }


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
                param.put("OwnerName",OwnerName.getText().toString());
                param.put("CompanyName",CompName.getText().toString());
                param.put( "Address", Address.getText().toString());
                param.put("ContactNo",Contact.getText().toString());
                param.put("EmailId", Email.getText().toString());
                param.put("UserName", UserName.getText().toString());
                param.put("Password", Password.getText().toString());
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
        RequestQueue requestQueue= Volley.newRequestQueue(RegisterActivity.this);
        requestQueue.add(stringRequest);
        Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }




}
