package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCompanyActivity extends BaseActivity  {


    FloatingActionButton floatbttn;
    RecyclerView recyclerView;
    String OwnerID,userType,code, message;
    ProgressDialog p1;
    private RecyclerView.Adapter adapter;
    private List<CompanyModelClass> Complist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_view_company, contentFrameLayout);

        CardView linearLayout =findViewById(R.id.list);

        floatbttn = (FloatingActionButton) findViewById(R.id.fab3);
        Complist = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences keys =getSharedPreferences("MyPref",MODE_PRIVATE);
        OwnerID =keys.getString("OwnerID",null);
        userType =keys.getString("UserType",null);

        loadrecyclerviewdata();

        assert userType != null;
        if (userType.equals("PRO")) {


            floatbttn.hide();

        }



        floatbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCompanyActivity.this, AddCompany.class);
                intent.putExtra("ToolText","New Company");
                startActivity(intent);
                finish();

            }
        });






    }


    private void loadrecyclerviewdata() {
        p1= ProgressDialog.show(ViewCompanyActivity.this,"Downloading","Please wait");

        String URL = this.getString(R.string.Local_URL)+"/api/Companyapi/GetAllCompByOwnerId";


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("Jsonnnn",""+response);
                        p1.dismiss();

                            try {

                                JSONObject o     = new JSONObject(response);
                                JSONArray json_array2 =o.getJSONArray("CompanyResponse");
                                JSONObject jsonObject = json_array2.getJSONObject(0);
                                code =jsonObject.getString("responseCode");
                                message=jsonObject.getString("responseMessage");

                                Log.e("resppppppp",""+code);
                                if (code.equals("0"))
                                {

                                    JSONArray json_array   = o.getJSONArray("CompanyDetails");

                                    int j;
                                    for (j = 0; j < json_array.length(); j++) {
                                        o = json_array.getJSONObject(j);
                                        CompanyModelClass items = new CompanyModelClass(o.getString("compId"),o.getString("ownerName"),
                                                o.getString("compName"), o.getString("contactPerson"),
                                                o.getString("contactNo"),o.getString("tradeLicence"),
                                                o.getString("tradeLicenceExpiry"),o.getString("trn"),
                                                o.getString("labourCardNumber"),o.getString("immigrationCardNumber"),
                                                o.getString("immigrationCardExpiry"), o.getString("tenancy"),o.getString("employeeCount"));
                                        Complist.add(items);

                                    }
                                    adapter = new CompanyAdapter(Complist, getApplicationContext());
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();

                                }


                                else {
                                    Toast.makeText(ViewCompanyActivity.this,message,Toast.LENGTH_LONG).show();
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        p1.dismiss();
                        Toast.makeText(getApplicationContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("OwnerId", OwnerID);
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
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }





}
