package com.finite.covidinfoleads.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finite.covidinfoleads.R;
import com.finite.covidinfoleads.Model.chmodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ch_adapter extends FirebaseRecyclerAdapter<chmodel,ch_adapter.ch_viewholder> {

    public ch_adapter(@NonNull FirebaseRecyclerOptions<chmodel> options) {
        super(options);

    }

    protected void onBindViewHolder(@NonNull ch_adapter.ch_viewholder ch_viewholder, int position, @NonNull chmodel chmodel) {

        ch_viewholder.chtitle.setText(chmodel.getChtitle());
        ch_viewholder.chbody.setText(chmodel.getChbody());

    }

    @NonNull
    @Override
    public ch_adapter.ch_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chsinglerow,parent,false);
        return new ch_adapter.ch_viewholder(view);
    }

    class ch_viewholder extends RecyclerView.ViewHolder
    {
        TextView chtitle,chbody;

        public ch_viewholder(@NonNull View itemView) {
            super(itemView);
            chtitle = itemView.findViewById(R.id.chtitle);
            chbody = itemView.findViewById(R.id.chbody);
        }

    }

}
