package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
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

public class AddUser extends BaseActivity {
    TextView txtuser,useSbmit;
    EditText FullName,UserName,Contact,Password,CPassword;
    ProgressDialog asyncDialog;
    String Code,Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_add_user, contentFrameLayout);


        Toolbar tb=getToolBar();
        txtuser=(TextView)tb.findViewById(R.id.appname);
        txtuser.setText("New User");
        asyncDialog = new ProgressDialog(AddUser.this);
        useSbmit =findViewById(R.id.user_submit);
        UserName=findViewById(R.id.usrname);
        Contact=findViewById(R.id.usr_contact_no);
        Password=findViewById(R.id.user_pwrd);
        CPassword=findViewById(R.id.cpwrd);
        FullName=findViewById(R.id.name_of_user);
        asyncDialog = new ProgressDialog(this);





        FullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, final boolean hasfocus) {
                if (hasfocus) {

                    FullName.setError("Mandatory Field");
                }
                else {
                    FullName.clearFocus();
                }
            }
        });

        UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, final boolean hasfocus) {
                if (hasfocus) {

                    UserName.setError("Mandatory Field");
                }
                else {
                    UserName.clearFocus();


                }
            }
        });

        Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, final boolean hasfocus) {
                if (hasfocus) {

                    Password.setError("Mandatory Field");
                }
                else {
                    Password.clearFocus();


                }
            }
        });





        useSbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                asyncDialog.setTitle("User Registration");
                asyncDialog.setMessage("On Progress....");
                asyncDialog.show();

                if (TextUtils.isEmpty(FullName.getText()))
                {
                    asyncDialog.dismiss();
                   FullName.setError("Field is Mandatory");

                }
                else if(TextUtils.isEmpty(UserName.getText())){

                    asyncDialog.dismiss();
                    UserName.setError("Field is Mandatory");


                }
                else if(TextUtils.isEmpty(Password.getText())){

                    asyncDialog.dismiss();
                    Password.setError("Field is Mandatory");


                }

                else if(!Password.getText().toString().equals(CPassword.getText().toString())){

                    asyncDialog.dismiss();
                    CPassword.setError("Not Matching");


                }

                else
                {
                    asyncDialog.show();
                    UserRegister();
                }


            }
        });






    }



    public void UserRegister() {



        String URL = "http://192.168.0.30:7777/Service1.svc/userRegister";


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
                            Code= jsonObject.getString("responseCode");
                            Message=jsonObject.getString("responseMessage");
                            Log.e("uttutut", "" + Code);

                            if (Code.equals("0")) {
                                Intent intent = new Intent(AddUser.this, UserFragment.class);
                                startActivity(intent);
                                Toast.makeText(AddUser.this, "User Registered", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(AddUser.this,  Message, Toast.LENGTH_LONG).show();

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
                param.put("OwnerName",FullName.getText().toString());
                param.put("ContactNo",Contact.getText().toString());
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
        RequestQueue requestQueue= Volley.newRequestQueue(AddUser.this);
        requestQueue.add(stringRequest);
       // Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }






}
