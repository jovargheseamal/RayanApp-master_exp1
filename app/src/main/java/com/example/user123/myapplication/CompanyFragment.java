package com.example.user123.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {

    ImageView edit;
    String company, cp, trl, trn, lcn, icn, tncy,compID;
    TextView Cname, ContactP, TradeLicence, Trn, LabourCardNum, ImmigrationCardNum, tenancy;

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


        Bundle abBundle = this.getArguments();
        if (abBundle != null) {
            compID = abBundle.getString("CompanyID");
            company = abBundle.getString("CompanyName");
            cp = abBundle.getString("ContactPerson");
            trl = abBundle.getString("TradeLicence");
            lcn = abBundle.getString("LabourCNo");
            trn = abBundle.getString("TRN");
            icn = abBundle.getString("ImmigrationNo");
            tncy = abBundle.getString("Tenancy");

//            if(cp.equals("")){
//                ContactP.setText("N/A");
//            }
//            else if(trl.equals("")){
//                TradeLicence.setText("N/A");
//            }
//            else if(lcn.equals("")){
//                LabourCardNum.setText("N/A");
//            }
//            else if(trn.equals("")){
//                Trn.setText("N/A");
//
//            }
//            else if(icn.equals("")){
//
//                ImmigrationCardNum.setText("N/A");
//            }
//            else if(tncy.equals("")){
//
//                tenancy.setText("N/A");
//            }



                Log.e("comingggggggg", "getttt" + trl);

                Cname.setText(company);
                ContactP.setText(cp);
                TradeLicence.setText(trl);
                Trn.setText(trn);
                LabourCardNum.setText(lcn);
                ImmigrationCardNum.setText(icn);
                tenancy.setText(tncy);



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




        return RootView;
    }


}

