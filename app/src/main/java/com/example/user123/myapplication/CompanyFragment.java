package com.example.user123.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {

    ImageView edit ;

    public CompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_company, container, false);
        edit=RootView.findViewById(R.id.editcompany);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity().getBaseContext(),AddCompany.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment

        return RootView;

    }

}
