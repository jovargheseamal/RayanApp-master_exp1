package com.example.user123.myapplication;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
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
    ScrollView scrollView;

    @TargetApi(Build.VERSION_CODES.M)
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

        scrollView=findViewById(R.id.scrollcmp);


//
//        OwnerName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, final boolean hasfocus) {
//                if (hasfocus) {
//
//                    OwnerName.setError("Mandatory Field");
//
//                    scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                        @Override
//                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                            OwnerName.clearFocus();
//
//                        }
//                    });
//
//                }
//            }
//        });
//
//
//        CompName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, final boolean hasfocus) {
//                if (hasfocus) {
//                    OwnerName.clearFocus();
//                    CompName.setError("Mandatory Field");
//
//                    scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                        @Override
//                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                            CompName.clearFocus();
//
//                        }
//                    });
//
//                }
//            }
//        });
//
//
//
//
//
//        UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, final boolean hasfocus) {
//                if (hasfocus) {
//                    CompName.clearFocus();
//                    UserName.setError("Mandatory Field");
//
//                    scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                        @Override
//                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                            UserName.clearFocus();
//
//                        }
//                    });
//
//                }
//            }
//        });
//
//
//        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View arg0, final boolean hasfocus) {
//                if (hasfocus) {
//                    UserName.clearFocus();
//                    Password.setError("Mandatory Field");
//
//                    scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                        @Override
//                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                            Password.clearFocus();
//
//                        }
//                    });
//
//                }
//            }
//        });




        button = findViewById(R.id.regbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDialog.setMessage("Registration In Progress...");

                if ((OwnerName.getText().toString().equalsIgnoreCase("")) || (CompName.getText().toString().equalsIgnoreCase(""))
                        ||(Password.getText().toString().equalsIgnoreCase(""))|| (UserName.getText().toString().equalsIgnoreCase("")))
                {   asyncDialog.dismiss();
                    OwnerName.setError("Field is Mandatory");
                    CompName.setError("Field is Mandatory");
                    UserName.setError("Field is Mandatory");
                    Password.setError("Field is Mandatory");

                    Toast.makeText(RegisterActivity.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();


                }
                else{
                    asyncDialog.show();
                    AdminRegister();

                }

            }
        });

    }

    public void AdminRegister() {



        String URL = this.getString(R.string.Local_URL)+"/api/userapi/adminRegister";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject object     = new JSONObject(response);
                            JSONArray jsonArray   = object.getJSONArray("RegisterResponse");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            code= jsonObject.getString("responseCode");
                            message=jsonObject.getString("responseMessage");
                            Log.e("uttutut", "" + code);

                            if (code.equals("0")) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
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
                        Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

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



    @Override
    public void onBackPressed() {
       Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
       startActivity(intent);
    }


}
