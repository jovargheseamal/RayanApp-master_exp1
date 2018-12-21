package com.example.user123.myapplication;


import android.content.Context;
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
public class UserFragment extends Fragment {

   // FloatingActionButton fab = getView().findViewById(R.id.fab);


    public UserFragment() {
        // Required empty public constructor test
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_user, container, false);
        FloatingActionButton fab = RootView.findViewById(R.id.fab3);

       fab.setOnClickListener(new View.OnClickListener() {
           @Override public void onClick(View view)
           {
               Intent intent = new Intent(getActivity(),AddUser.class);
           startActivity(intent);
           }
       });

        // Inflate the layout for this fragment
        return RootView;



    }

}
