package com.finite.covidinfoleads.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finite.covidinfoleads.R;
import com.finite.covidinfoleads.Model.hospmodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class hosp_adapter extends FirebaseRecyclerAdapter<hospmodel,hosp_adapter.hosp_viewholder> {

    public hosp_adapter(@NonNull FirebaseRecyclerOptions<hospmodel> options) {
        super(options);

    }

    protected void onBindViewHolder(@NonNull hosp_adapter.hosp_viewholder hosp_viewholder, int position, @NonNull hospmodel hospmodel) {

        hosp_viewholder.hospcity.setText(hospmodel.getHospcity());
        hosp_viewholder.hospname.setText(hospmodel.getHospname());
        hosp_viewholder.hospno.setText("📞 : "+hospmodel.getHospno());

    }

    @NonNull
    @Override
    public hosp_adapter.hosp_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospsinglerow,parent,false);
        return new hosp_adapter.hosp_viewholder(view);
    }

    class hosp_viewholder extends RecyclerView.ViewHolder
    {
        TextView hospcity,hospname,hospno;

        public hosp_viewholder(@NonNull View itemView) {
            super(itemView);
            hospcity = itemView.findViewById(R.id.hospcity);
            hospname = itemView.findViewById(R.id.hospname);
            hospno = itemView.findViewById(R.id.hospno);
        }

    }

}
