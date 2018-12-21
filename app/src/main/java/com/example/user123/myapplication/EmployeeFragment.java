package com.example.user123.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeFragment extends Fragment {






    public EmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_employee, container, false);
        FloatingActionButton fab = RootView.findViewById(R.id.fab2);

       fab.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddEmployee.class);
              startActivity(intent);
           }
       });
return RootView;
        // Inflate the layout for this fragment

    }

}
