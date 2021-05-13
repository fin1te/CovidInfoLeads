package com.finite.covidinfoleads.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finite.covidinfoleads.R;
import com.finite.covidinfoleads.Model.blogmodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class blog_adapter extends FirebaseRecyclerAdapter<blogmodel,blog_adapter.blog_viewholder> {

    public blog_adapter(@NonNull FirebaseRecyclerOptions<blogmodel> options) {
        super(options);

    }

    protected void onBindViewHolder(@NonNull blog_adapter.blog_viewholder blog_viewholder, int position, @NonNull blogmodel blogmodel) {

        blog_viewholder.blogtitle.setText(blogmodel.getBlogtitle());
        blog_viewholder.blogbody.setText(blogmodel.getBlogbody());
        blog_viewholder.blogauthor.setText(blogmodel.getBlogauthor());
    }

    @NonNull
    @Override
    public blog_adapter.blog_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blogsinglerow,parent,false);
        return new blog_adapter.blog_viewholder(view);
    }

    class blog_viewholder extends RecyclerView.ViewHolder
    {
        TextView blogtitle,blogbody,blogauthor;

        public blog_viewholder(@NonNull View itemView) {
            super(itemView);
            blogtitle = itemView.findViewById(R.id.blogtitle);
            blogbody = itemView.findViewById(R.id.blogbody);
            blogauthor = itemView.findViewById(R.id.blogauthor);
        }

    }

}
