package com.finite.covidinfoleads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        holder.cityName.setText(model.getCityName());
        holder.resourcetype.setText(model.getResourcetype());
        holder.body.setText(model.getBody());
        holder.verifiedtime.setText("Time :"+model.getVerifiedtime());
        holder.verifieddate.setText("Date :"+model.getVerifieddate());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView cityName,resourcetype,body,verifiedtime,verifieddate;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            cityName = itemView.findViewById(R.id.cityName);
            resourcetype = itemView.findViewById(R.id.resourcetype);
            body = itemView.findViewById(R.id.body);
            verifiedtime = itemView.findViewById(R.id.verifiedtime);
            verifieddate = itemView.findViewById(R.id.verifieddate);

        }
    }
}
