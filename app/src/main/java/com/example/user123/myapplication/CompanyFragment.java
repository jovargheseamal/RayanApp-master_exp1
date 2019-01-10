package com.example.user123.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {

    ImageView edit;
    String company, cp, trl, trn, lcn, icn, tncy,compID,count;
    TextView Cname, ContactP, TradeLicence, Trn, LabourCardNum, ImmigrationCardNum, tenancy,ecount;
    ProgressDialog asyncdialog;

    public CompanyFragment() {
        // Required empty public constructor//
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_company_ed, container, false);
        edit = RootView.findViewById(R.id.editcompany);
        Cname = RootView.findViewById(R.id.compname);
        ContactP = RootView.findViewById(R.id.Cp);
        TradeLicence = RootView.findViewById(R.id.TRL);
        Trn = RootView.findViewById(R.id.trn_show);
        LabourCardNum = RootView.findViewById(R.id.LCN);
        ImmigrationCardNum = RootView.findViewById(R.id.ICN);
        tenancy = RootView.findViewById(R.id.tenancy_show);
        ecount = RootView.findViewById(R.id.empCount);
        asyncdialog = new ProgressDialog(getContext());

        Bundle abBundle = this.getArguments();
        if (abBundle != null) {
            compID = abBundle.getString("CompID");




                Log.e("comingggggggg", "getttt" + compID);




        }



            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getBaseContext(), AddCompany.class);
                    intent.putExtra("ToolText","Edit Company");
                    intent.putExtra("CompID",compID);
                    startActivity(intent);
                }
            });
            // Inflate the layout for this fragment

        ViewCompany();


        return RootView;
    }


    public void ViewCompany() {




        String URL = "http://192.168.0.30:5544/api/Companyapi/GetCompById";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncdialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject o     = new JSONObject(response);
                            JSONArray json_array   = o.getJSONArray("CompanyDetails");


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
                                company =o.getString("compName");
                                cp = o.getString("contactPerson");
                                trl = o.getString("tradeLicence");
                                lcn = o.getString("trn");
                                trn = o.getString("labourCardNumber");
                                icn = o.getString("immigrationCardNumber");
                                tncy = o.getString("tenancy");
                                count = o.getString("employeeCount");

                            }





                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                        Cname.setText(company);
                        ContactP.setText(cp);
                        TradeLicence.setText(trl);
                        Trn.setText(trn);
                        LabourCardNum.setText(lcn);
                        ImmigrationCardNum.setText(icn);
                        tenancy.setText(tncy);
                        ecount.setText(count);
                    }




                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        asyncdialog.dismiss();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("CompId",compID);


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
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        // Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }



}

