package com.example.user123.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class EmpFragment extends Fragment {


    String compID,cname;
    RecyclerView recyclerView;
    ProgressDialog p1;
    private RecyclerView.Adapter adapter;

    private ArrayList<EmployeeModelClass> Emplist;


    public EmpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_emp, container, false);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView =  RootView.findViewById(R.id.emprecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        Emplist = new ArrayList<>();
        FloatingActionButton fab = RootView.findViewById(R.id.fab2);

        Bundle bundle = this.getArguments();
        compID = bundle.getString("CompID");
        cname= bundle.getString("CompanyName");





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddEmployee.class);
                intent.putExtra("CompID",compID);
                intent.putExtra("CompanyName",cname);
                intent.putExtra("ToolText","New Employee");
                startActivity(intent);
            }
        });


        loadrecyclerviewdata();


        return RootView;


    }







    private void loadrecyclerviewdata() {
        p1= ProgressDialog.show(getContext(),"Downloading","Please wait");

        String URL = "http://192.168.0.30:5544/api/EmployeeApi/GetAllEmpByCompId";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("userrrrrrr",""+response);
                        p1.dismiss();
                        if((!response.equals("[]"))&&(!response.equals("null"))) {
                            try {

                                JSONObject o     = new JSONObject(response);
                                JSONArray json_array   = o.getJSONArray("EmpDetails");


                                int j;
                                for (j = 0; j < json_array.length(); j++) {
                                    o = json_array.getJSONObject(j);
                                    EmployeeModelClass items = new EmployeeModelClass (
                                            o.getString("EmpID"),
                                            o.getString("CompId"),
                                            o.getString("EmpName"),
                                            o.getString("Address"),
                                            o.getString("PhoneNo"),
                                            o.getString("DOB"),
                                            o.getString("EmailID"),
                                            o.getString("EmiratesID"),
                                            o.getString("PassportNO"),
                                            o.getString("PassportExpiry"),
                                            o.getString("WorkPermit"),
                                            o.getString("WorkPermitExpiry"),
                                            o.getString("VisaExpiry"),
                                            o.getString("UIDNumber"),
                                            o.getString("FileNumber"),
                                            o.getString("CompanyName"));

                                    Emplist.add(items);

                                }


                                adapter = new _EmployeeAdapter(Emplist,getActivity());
                                recyclerView.setAdapter(adapter);
                                //adapter.notifyDataSetChanged();


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            Toast.makeText(getContext(),"No Company to Display",Toast.LENGTH_LONG).show();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        p1.dismiss();
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("CompId", compID);

                Log.e("Jsonnnn",""+compID);
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
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

}
