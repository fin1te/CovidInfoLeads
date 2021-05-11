package com.finite.covidinfoleads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class doc_adapter extends FirebaseRecyclerAdapter<docmodel,doc_adapter.doc_viewholder> {
    public doc_adapter(@NonNull FirebaseRecyclerOptions<docmodel> options) {
        super(options);

    }

    protected void onBindViewHolder(@NonNull doc_viewholder doc_viewholder, int position, @NonNull docmodel docmodel) {

        doc_viewholder.docName.setText("Dr. " +docmodel.getDocName());
        doc_viewholder.docNo.setText("Phone No : "+docmodel.getDocNo());
        doc_viewholder.docrate.setText("Charges : "+docmodel.getDocrate());
        doc_viewholder.docTiming.setText("Available Time : "+docmodel.getDocTiming());

    }

    @NonNull
    @Override
    public doc_adapter.doc_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.docsinglerow,parent,false);
        return new doc_viewholder(view);
    }

    class doc_viewholder extends RecyclerView.ViewHolder
    {
        TextView docName,docNo,docrate,docTiming;

        public doc_viewholder(@NonNull View itemView) {
            super(itemView);
            docName = itemView.findViewById(R.id.docName);
            docNo = itemView.findViewById(R.id.docNo);
            docrate = itemView.findViewById(R.id.docrate);
            docTiming = itemView.findViewById(R.id.docTiming);
        }

    }
}
