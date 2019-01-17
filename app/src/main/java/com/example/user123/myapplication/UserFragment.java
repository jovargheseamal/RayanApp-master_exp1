package com.example.user123.myapplication;


import android.app.ProgressDialog;
import android.content.Context;
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
public class UserFragment extends Fragment {

    // FloatingActionButton fab = getView().findViewById(R.id.fab);

    String compID,cpname;
    RecyclerView recyclerView;
    ProgressDialog p1;
    private RecyclerView.Adapter adapter;

    private List<UserModelClass> Userlist;

    public UserFragment() {
        // Required empty public constructor test
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_user, container, false);


        recyclerView = (RecyclerView) RootView.findViewById(R.id.userRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Userlist = new ArrayList<>();


        Bundle abBundle = this.getArguments();
        if (abBundle != null) {
            compID = abBundle.getString("CompID");
            cpname= abBundle.getString("CompanyName");

            FloatingActionButton fab = RootView.findViewById(R.id.fab3);



            loadrecyclerviewdata();


            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AddUser.class);
                    intent.putExtra("ToolText", "New User");
                    intent.putExtra("CompID", compID);
                    intent.putExtra("CompanyName", cpname);
                    startActivity(intent);
                    getActivity().finish();

                }
            });



        }



        return RootView;

    }


    private void loadrecyclerviewdata() {
        p1= ProgressDialog.show(getContext(),"Downloading","Please wait");

        String URL = this.getString(R.string.Local_URL)+"/api/UserApi/GetAllUsersByCompId";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("userrrrrrr",""+response);
                        p1.dismiss();
                        if((!response.equals("[]"))&&(!response.equals("null"))) {
                            try {

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
                                    UserModelClass items = new UserModelClass(o.getString("CompanyName"),o.getString("UserID"),o.getString("Name"),
                                            o.getString("UserName"), o.getString("ContactNo"),o.getString("CompId"));

                                    Userlist.add(items);

                                }
                                adapter = new _UserAdapter(Userlist, getContext());
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();


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
                        Toast.makeText(getContext(), "No Response From Server ", Toast.LENGTH_LONG).show();

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

