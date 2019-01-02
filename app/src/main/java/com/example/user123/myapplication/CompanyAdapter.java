package com.example.user123.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder>{

    private List<CompanyModelClass> View_List;
    private Context context;

    public CompanyAdapter(List<CompanyModelClass> view_List, Context context) {
        View_List = view_List;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.companylist,viewGroup,false);
        return new ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final CompanyModelClass List= View_List.get(i);

        viewHolder.CompanyName.setText(List.getCompanyName());
        viewHolder.Ownername.setText(List.getContactPerson());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CompID", List.getCompID());
                intent.putExtra("CompanyName", List.getCompanyName());
                intent.putExtra("ContactPerson", List.getContactPerson());
                intent.putExtra("TradeLicence", List.getTradeLicence());
                intent.putExtra("TradeLicenceEXP", List.getTradeLicenceExpiry());
                intent.putExtra("TradeLicenceEXP", List.getTradeLicenceExpiry());
                intent.putExtra("TRN", List.getTRN());
                intent.putExtra("LabourCNo", List.getLabourCardNumber());
                intent.putExtra("ImmigrationNo", List.getImmigrationCardNumber());
                intent.putExtra("ImmigrationEXP", List.getImmigrationCardNumberExpiry());
                intent.putExtra("Tenancy", List.getTenancy());
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return View_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       TextView CompanyName,Ownername;
       LinearLayout parentLayout;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        CompanyName=itemView.findViewById(R.id.CompListName);
        Ownername=itemView.findViewById(R.id.CompListOwner);
        parentLayout=itemView.findViewById(R.id.context);
    }
}

}
