package com.finite.covidinfoleads.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finite.covidinfoleads.Model.annmodel;
import com.finite.covidinfoleads.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ann_adapter extends FirebaseRecyclerAdapter<annmodel,ann_adapter.ann_viewholder> {

    public ann_adapter(@NonNull FirebaseRecyclerOptions<annmodel> options) {
        super(options);

    }

    protected void onBindViewHolder(@NonNull ann_adapter.ann_viewholder ann_viewholder, int position, @NonNull annmodel annmodel) {

        ann_viewholder.anntitle.setText(annmodel.getAnntitle());
        ann_viewholder.annbody.setText(annmodel.getAnnbody());
        ann_viewholder.annauthor.setText(annmodel.getAnnauthor());
    }

    @NonNull
    @Override
    public ann_adapter.ann_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.annsinglerow,parent,false);
        return new ann_adapter.ann_viewholder(view);
    }

    class ann_viewholder extends RecyclerView.ViewHolder
    {
        TextView anntitle,annbody,annauthor;

        public ann_viewholder(@NonNull View itemView) {
            super(itemView);
            anntitle = itemView.findViewById(R.id.anntitle);
            annbody = itemView.findViewById(R.id.annbody);
            annauthor = itemView.findViewById(R.id.annauthor);
        }

    }

}
