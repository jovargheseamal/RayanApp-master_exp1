package com.example.user123.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
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

public class AddCompany extends BaseActivity {

    TextView txtxmpny,register;
    ProgressDialog asyncDialog;
    String TRN_Exp_Day,TRN_Exp_Month,TRN_Exp_Year,IMC_Exp_Day,IMC_Exp_Month,IMC_Exp_Year,code,message;


    String array_day[] = {"DD", "1", "2", "3", "4", "5", "6","7","8","9","10",
            "11", "12", "13", "14", "15", "16","17","18","19","20","21", "22",
            "23", "24", "25", "26","27","28","29","30", "31"};

    String array_Mnth[] = {"MM","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    String array_Year[] = {"YY","2019","2020","2021","2022","2023","2024","2025","2026","2027","2028",
                            "2029","2030","2031","2032","2033","2034","2035","2036","20237","2038","2039","2040"};


    Spinner TRday,TRmonth,TRyear,IM_Day,IM_Month,IM_Year;

    EditText CMP_Name,ContactPerson,TradeLicence,TRN,LabourCard,Immigration,Tenancy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_add_company_ed, contentFrameLayout);

// toolbar

        Toolbar tb=getToolBar();
        txtxmpny=(TextView)tb.findViewById(R.id.appname);
        txtxmpny.setText("New Company");

        //spinners
        register=findViewById(R.id.sbmt);
        TRday=findViewById(R.id.TRExp_day);
        TRmonth=findViewById(R.id.TRExp_month);
        TRyear=findViewById(R.id.TRExp_year);

        CMP_Name=findViewById(R.id.Company_Name);
        ContactPerson=findViewById(R.id.Contact_Person);
        TradeLicence=findViewById(R.id.Trade_Licence);
        TRN=findViewById(R.id.TRN);
        LabourCard=findViewById(R.id.Labour_Card_No);
        Immigration=findViewById(R.id.Immigration_Card_no);
        Tenancy=findViewById(R.id.Tenancy);


        IM_Day=findViewById(R.id.IMC_Exp_Day);
        IM_Month=findViewById(R.id.IMC_Exp_Month);
        IM_Year=findViewById(R.id.IMC_Exp_Year);

        asyncDialog = new ProgressDialog(AddCompany.this);







        ///day////////

        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_day);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TRday.setAdapter(spinner_adapter);
        IM_Day.setAdapter(spinner_adapter);

        TRday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TRN_Exp_Day =parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        IM_Day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IMC_Exp_Day =parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ////month////////

        ArrayAdapter<String> _adaptermnth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Mnth);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TRmonth.setAdapter(_adaptermnth);
        IM_Month.setAdapter(_adaptermnth);

        TRmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TRN_Exp_Month = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        IM_Month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IMC_Exp_Month = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ////year////////

        ArrayAdapter<String> _adapteryear = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array_Year);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TRyear.setAdapter(_adapteryear);
        IM_Year.setAdapter(_adapteryear);


        TRyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TRN_Exp_Year = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        IM_Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IMC_Exp_Year = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDialog.setTitle("User Registration");
                asyncDialog.setMessage("Registration In Progress...");
                //show dialog
                asyncDialog.show();

                UserRegister();
            }
        });





    }



    public void UserRegister() {



        String URL = "http://192.168.0.30:7777/Service1.svc/companyRegister";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncDialog.dismiss();

                        Log.e("uttutut", "" + response);


                        try {

                            Log.e("uttutut", "" + response);
                            JSONObject object     = new JSONObject(response);
                            JSONArray jsonArray   = object.getJSONArray("SaveCompanyResult");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            code= jsonObject.getString("responseCode");
                            message=jsonObject.getString("responseMessage");
                            Log.e("uttutut", "" + code);

                            if (code.equals("0")) {
                                Intent intent = new Intent(AddCompany.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(AddCompany.this, "Registration Success", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(AddCompany.this,  message, Toast.LENGTH_LONG).show();

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
               // param.put("OwnerName","");
                param.put("OwnerID","1");
                param.put("CompanyName",CMP_Name.getText().toString());
                param.put("ContactPerson",ContactPerson.getText().toString());
                param.put("ContactNo","rturturtur");
                param.put("TradeLicence",TradeLicence.getText().toString());
                param.put("TradeLicenceExpiry",TRN_Exp_Day+"-"+TRN_Exp_Month+"-"+TRN_Exp_Year);
                param.put("TRN",TRN.getText().toString());
                param.put("LabourCardNumber",LabourCard.getText().toString());
                param.put("ImmigrationCardNumber",Immigration.getText().toString());
                param.put("ImmigrationCardExpiry",IMC_Exp_Day+"-"+IMC_Exp_Month+"-"+IMC_Exp_Year);
                param.put("Tenancy",Tenancy.getText().toString());

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

        RequestQueue requestQueue= Volley.newRequestQueue(AddCompany.this);
        requestQueue.add(stringRequest);
       // Log.e("reqqqqqqqqqq",""+stringRequest.toString());







    }




}
