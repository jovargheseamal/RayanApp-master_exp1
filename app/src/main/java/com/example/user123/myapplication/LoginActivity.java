package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class LoginActivity extends AppCompatActivity {
    TextView textView,submitlog;
    EditText uname,pword;
    String code,message,usertype,ownerId,uID,LoginName,OwnerUsername;
    ProgressDialog asyncDialog;
    SharedPreferences sp;
    CheckBox logCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView =findViewById(R.id.reglink);
        uname =(EditText) findViewById(R.id.username);
        uname.setImeOptions(EditorInfo.IME_ACTION_DONE);
        pword =(EditText) findViewById(R.id.pasword);
        pword.setImeOptions(EditorInfo.IME_ACTION_DONE);
        submitlog = findViewById(R.id.login);
        logCheck=findViewById(R.id.logcheck);
        boolean isChecked = false;
        asyncDialog = new ProgressDialog(LoginActivity.this);




       submitlog.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {




               asyncDialog.setTitle("login");
               asyncDialog.setMessage("Please Wait...");
               //show dialog
               asyncDialog.show();

               if ((uname.getText().toString().equalsIgnoreCase("")) || (pword.getText().toString().equalsIgnoreCase("")))
               {   asyncDialog.dismiss();
                   uname.setError("Field is Mandatory",getResources().getDrawable(R.drawable.erroricon));
                   pword.setError("Field is Mandatory",getResources().getDrawable(R.drawable.erroricon));




               }
            else {
                   SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
                   SharedPreferences.Editor editor1 = settings.edit();

                   if (logCheck.isChecked()) {

                       editor1.putBoolean("isChecked", true);

                       editor1.apply();


                       Log.e("boolllll", "inside" + true);
                   }
                   else{
                       editor1.putBoolean("isChecked", false);

                   }

                   asyncDialog.show();
                   Login();
               }






           }
       });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Login() {



        String URL =this.getString(R.string.Local_URL) +"/api/userapi/login";

        Log.e("uttutut", "" + R.string.Local_URL);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject object     = new JSONObject(response);
                            JSONArray jsonArray   = object.getJSONArray("LoginResponse");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);

                            code= jsonObject.getString("responseCode");
                            message=jsonObject.getString("responseMessage");



                            Log.e("uttutut", "" + code);

                            if (code.equals("0")) {

                                uID =jsonObject.getString("userId");

                                usertype=jsonObject.getString("userType");
                                ownerId=jsonObject.getString("ownerId");
                                LoginName=jsonObject.getString("Name");
                                OwnerUsername=jsonObject.getString("UserName");

                                sp =getSharedPreferences("MyPref",MODE_PRIVATE);
                                SharedPreferences.Editor editor =sp.edit();
                                editor.putString("OwnerID",ownerId);
                                editor.putString("UserType",usertype);
                                editor.putString("LoginUserID",uID);
                                editor.putString("LoginName",LoginName);
                                editor.putString("LoggedUsername",OwnerUsername);

                                editor.apply();

                                Intent intent =new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
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
                        Toast.makeText(getApplicationContext(), "No Response From Server", Toast.LENGTH_LONG).show();

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
