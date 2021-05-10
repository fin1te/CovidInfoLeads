package com.finite.covidinfoleads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class sample_adapter extends FirebaseRecyclerAdapter<samplemodel,sample_adapter.sample_viewholder> {

    public sample_adapter(@NonNull FirebaseRecyclerOptions<samplemodel> options) {
        super(options);

    }

    protected void onBindViewHolder(@NonNull sample_adapter.sample_viewholder sample_viewholder, int position, @NonNull samplemodel samplemodel) {

        sample_viewholder.samplecity.setText(samplemodel.getSamplecity());
        sample_viewholder.samplename.setText(samplemodel.getSamplename());
        sample_viewholder.sampleno.setText("Phone No : "+samplemodel.getSampleno());

    }

    @NonNull
    @Override
    public sample_adapter.sample_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.samplesinglerow,parent,false);
        return new sample_adapter.sample_viewholder(view);
    }

    class sample_viewholder extends RecyclerView.ViewHolder
    {
        TextView samplecity,samplename,sampleno;

        public sample_viewholder(@NonNull View itemView) {
            super(itemView);
            samplecity = itemView.findViewById(R.id.samplecity);
            samplename = itemView.findViewById(R.id.samplename);
            sampleno = itemView.findViewById(R.id.sampleno);
        }

    }

}
