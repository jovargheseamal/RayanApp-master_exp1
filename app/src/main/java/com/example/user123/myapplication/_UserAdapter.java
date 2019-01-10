package com.example.user123.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class _UserAdapter extends RecyclerView.Adapter<_UserAdapter.ViewHolder>{

    private List<UserModelClass> View_List;
    private Context context;

    public _UserAdapter(List<UserModelClass> view_List, Context context) {
        View_List = view_List;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.userlist,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        final UserModelClass List = View_List.get(i);

        List<String> colors;

        colors = new ArrayList<String>();

        colors.add("#5E97F6");
        colors.add("#9CCC65");
        colors.add("#FF8A65");
        colors.add("#9E9E9E");
        colors.add("#9FA8DA");
        colors.add("#90A4AE");
        colors.add("#AED581");
        colors.add("#F6BF26");
        colors.add("#FFA726");
        colors.add("#4DD0E1");
        colors.add("#BA68C8");
        colors.add("#A1887F");

// all colors used by gmail application :) may be,

        // genrating random num from 0 to 11 because you can add more or less

        Random r = new Random();
        int i1 = r.nextInt(11 - 0) + 0;



        viewHolder.name.setText(List.getName());
        viewHolder.cv.setCardBackgroundColor(Color.parseColor(colors.get(i1)));
        viewHolder.compname.setText(List.getCompanyName());
        viewHolder.contact.setText(List.getContactNo());
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddUser.class);
                intent.putExtra("ToolText","Edit User");
                intent.putExtra("UserId",List.getUserID());
                intent.putExtra("CompID",List.getCompId());

                context.getApplicationContext().startActivity(intent);
//                ((AddUser)context).finish();
            }
        });


        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog p1 =new ProgressDialog(context);
                p1.setTitle("Removing Company");
                p1.setMessage("Removing all Details...");

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Delete");
                builder1.setMessage("Do you want to remove  "+ List.getName()+"?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {





                            public void onClick(DialogInterface dialog, int id)
                            {


                                String URL = "http://192.168.0.30:5544/api/UserApi/DeleteUser";


                                StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                p1.dismiss();
                                                Log.e("Jsonnnn",""+response);
                                                //asyncDialog.dismiss();
                                                if((!response.equals("[]"))&&(!response.equals("null"))) {
                                                    try {

                                                        JSONObject o     = new JSONObject(response);
                                                        JSONArray json_array   = o.getJSONArray("UserResponse");



                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                    Intent intent = new Intent(context, MainActivity.class);

                                                    intent.putExtra("Key","User");
                                                    intent.putExtra("CompID",List.getCompId());
                                                    Log.e("iddddddd",""+List.getCompId());
                                                    context.getApplicationContext().startActivity(intent);
                                                    ((MainActivity)context).finish();
                                                    //CMP_Name,ContactPerson,TradeLicence,TRN,LabourCard,Immigration,Tenancy;

                                                }
                                                else
                                                {
                                                    Toast.makeText(context,"No Company to Display",Toast.LENGTH_LONG).show();
                                                }




                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // asyncDialog.dismiss();
                                                Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();

                                            }
                                        }) {

                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> param = new HashMap<String, String>();
                                        param.put("UserID", List.getUserID());



                                        Log.e("userrriddddddd",""+List.getUserID());

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

                                RequestQueue requestQueue= Volley.newRequestQueue(context);
                                requestQueue.add(stringRequest);








                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }









        });




    }

    @Override
    public int getItemCount() {
        return View_List.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        TextView name,compname,contact;
        ImageView delete,edit;
        CardView cv;
        LinearLayout parent;
        public ViewHolder(@NonNull View itemView) {
               super(itemView);
            cv=itemView.findViewById(R.id.img1);
            name=itemView.findViewById(R.id.userListName);
            compname=itemView.findViewById(R.id.userListcomp);
            contact=itemView.findViewById(R.id.usecontact1);
            delete=itemView.findViewById(R.id.delete1);
            edit=itemView.findViewById(R.id.edituser);
            parent=itemView.findViewById(R.id.context);




        }
    }

}
