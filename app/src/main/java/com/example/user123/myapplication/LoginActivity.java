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

public class LoginActivity extends AppCompatActivity {
    TextView textView;
    EditText uname,pword;
    String code,message,usertype;
    ProgressDialog asyncDialog;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView =findViewById(R.id.reglink);
        uname =(EditText) findViewById(R.id.username);
        pword =(EditText) findViewById(R.id.password);
        button = findViewById(R.id.login);
        asyncDialog = new ProgressDialog(LoginActivity.this);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               asyncDialog.setTitle("login");
               asyncDialog.setMessage("Please Wait...");
               //show dialog
               asyncDialog.show();
               Login();

           }
       });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Login() {



        String URL = "http://192.168.0.30:7777/Service1.svc/login";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject object     = new JSONObject(response);
                            JSONArray jsonArray   = object.getJSONArray("LoginResult");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            code= jsonObject.getString("responseCode");
                            message=jsonObject.getString("responseMessage");
                            usertype=jsonObject.getString("userType");
                            Log.e("uttutut", "" + code);

                            if (code.equals("0")) {
                                Intent intent =new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(LoginActivity.this,  message, Toast.LENGTH_LONG).show();

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
                param.put("UserName",uname.getText().toString());
                param.put( "Password", pword.getText().toString());

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
        RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
        Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }



}
