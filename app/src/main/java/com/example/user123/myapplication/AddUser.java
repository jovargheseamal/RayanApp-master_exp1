package com.example.user123.myapplication;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    String Code,Message,compID,toolbar,Name,ContactNo ,UName, Pword,userID,logUsID ,compname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_add_user, contentFrameLayout);


        SharedPreferences keys =getSharedPreferences("MyPref",MODE_PRIVATE);
        logUsID = keys.getString("LoginUserID",null);
        asyncDialog= new ProgressDialog(this);
        Bundle abBundle = getIntent().getExtras();
        if (abBundle != null) {

            compID = abBundle.getString("CompID");
            toolbar = abBundle.getString("ToolText");
            userID = abBundle.getString("UserId");
            compname= abBundle.getString("CompanyName");

            Toolbar tb = getToolBar();
            txtuser = (TextView) tb.findViewById(R.id.appname);
            txtuser.setText(toolbar);


            if(toolbar.equalsIgnoreCase("Edit User"))
            {

                UserEdit();
            }


//            asyncDialog = new ProgressDialog(AddUser.this);
            useSbmit = findViewById(R.id.user_submit);
            UserName = findViewById(R.id.usrname);
            Contact = findViewById(R.id.usr_contact_no);
            Password = findViewById(R.id.user_pwrd);
            CPassword = findViewById(R.id.cpwrd);
            FullName = findViewById(R.id.name_of_user);
            asyncDialog = new ProgressDialog(this);


            FullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View arg0, final boolean hasfocus) {
                    if (hasfocus) {

                        FullName.setError("Mandatory Field");
                    } else {
                        FullName.clearFocus();
                    }
                }
            });

            UserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View arg0, final boolean hasfocus) {
                    if (hasfocus) {

                        UserName.setError("Mandatory Field");
                    } else {
                        UserName.clearFocus();


                    }
                }
            });

//            Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View arg0, final boolean hasfocus) {
//                    if (hasfocus) {
//
//                        Password.setError("Mandatory Field");
//                    } else {
//                        Password.clearFocus();
//
//
//                    }
//                }
//            });


            useSbmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {







                    asyncDialog.setTitle("User Registration");
                    asyncDialog.setMessage("On Progress....");
                    asyncDialog.show();

                    if (TextUtils.isEmpty(FullName.getText())) {
                        asyncDialog.dismiss();
                        FullName.setError("Field is Mandatory");

                    } else if (TextUtils.isEmpty(UserName.getText())) {

                        asyncDialog.dismiss();
                        UserName.setError("Field is Mandatory");


                    } else if (TextUtils.isEmpty(Password.getText())) {

                        asyncDialog.dismiss();
                        Password.setError("Field is Mandatory");


                    } else if (!Password.getText().toString().equals(CPassword.getText().toString())) {

                        asyncDialog.dismiss();
                        CPassword.setError("Not Matching");


                    } else {
                        asyncDialog.show();
                        UserRegister();
                    }


                }
            });


        }

    }

    public void UserRegister() {


        asyncDialog.setTitle("Users");
        asyncDialog.setMessage("Fetching User Details");
        asyncDialog.show();


        String URL = this.getString(R.string.Local_URL)+"/api/UserApi/AddEditUser";


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
                            Code= jsonObject.getString("responseCode");
                            Message=jsonObject.getString("responseMessage");
                            Log.e("Addddd", "cnameee=" + compname);

                            if (Code.equals("0")) {



                                Intent intent = new Intent(AddUser.this, MainActivity.class);
                                intent.putExtra("Key","User");
                                intent.putExtra("CompID",compID);
                                intent.putExtra("CompanyName",compname);

                                startActivity(intent);
                                finish();
                                if(toolbar.equals("Edit User")) {


                                    Toast.makeText(AddUser.this, "Edit successful", Toast.LENGTH_LONG).show();


                                }
                                else {
                                    Toast.makeText(AddUser.this, "User Registered", Toast.LENGTH_LONG).show();
                                }


                            } else {

                                UserName.setError("Already Exist");

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
                        Toast.makeText(getApplicationContext(), "Unexpected Response", Toast.LENGTH_LONG).show();

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("CompId",compID);
                param.put("Name",FullName.getText().toString());
                param.put("ContactNo",Contact.getText().toString());
                param.put("UserName", UserName.getText().toString());
                param.put("Password", Password.getText().toString());
                param.put("Mode",toolbar.substring(0,1));
                param.put("LoginUserId",logUsID);
                if (toolbar.substring(0,1).equals("E"))
                {
                    param.put("UserId",userID);
                }
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


        RequestQueue requestQueue= Volley.newRequestQueue(AddUser.this);
        requestQueue.add(stringRequest);
       // Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }




    public void UserEdit() {




        String URL =this.getString(R.string.Local_URL)+"/api/UserApi/GetUserById";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject o     = new JSONObject(response);
                            JSONArray json_array   = o.getJSONArray("UserDetails");


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
                                Name=o.getString("Name");
                                ContactNo = o.getString("ContactNo");
                                UName = o.getString("UserName");
                                Pword = o.getString("Password");

//                            if (Code.equals("0")) {
// ,,
//
//
//                                Intent intent = new Intent(AddUser.this, MainActivity.class);
//                                intent.putExtra("Key","User");
//                                startActivity(intent);
//                                Toast.makeText(AddUser.this, "User Registered", Toast.LENGTH_LONG).show();
//                            } else {
//
//                                Toast.makeText(AddUser.this,  Message, Toast.LENGTH_LONG).show();
//
//                            }
                            }





                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        UserName.setText(UName);
                        Contact .setText(ContactNo);
                        Password.setText(Pword);

                        FullName.setText(Name);

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
                param.put("UserId",userID);
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

        // Volley.getInstance(this).addToRequestQueue(stringRequest);
        RequestQueue requestQueue= Volley.newRequestQueue(AddUser.this);
        requestQueue.add(stringRequest);
        // Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }






}
