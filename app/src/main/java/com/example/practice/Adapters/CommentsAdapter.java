package com.example.practice.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Models.Comment;
import com.example.practice.Models.Profile;
import com.example.practice.Models.Test;
import com.example.practice.R;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    List<Comment> commentList;
    Context context;


    public CommentsAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        return new CommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Comment comment = commentList.get(position);
        Profile profile = comment.getProfiles();
        holder.name.setText(profile.getUsername());
        holder.date.setText(comment.getDate());
        holder.content.setText(comment.getContent());
        holder.rating.setText(""+comment.getRating());
        Glide.with(context)
                .load(profile.avatar_url)
                .placeholder (R.drawable.illustration)
                .error(R.drawable.illustration5)
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name, content, date, rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_icon);
            name = itemView.findViewById(R.id.name_user);
            content = itemView.findViewById(R.id.content);
            date = itemView.findViewById(R.id.date);
            rating = itemView.findViewById(R.id.rating);

        }
    }
}