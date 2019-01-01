package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    TextView txtuser;
    EditText FullName,UserName,Contact,Password;
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
                                Intent intent = new Intent(AddUser.this, LoginActivity.class);
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
                param.put( "Address", "");
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
