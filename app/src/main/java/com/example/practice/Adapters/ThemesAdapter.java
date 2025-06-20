package com.example.practice.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.Models.Courses;
import com.example.practice.Models.Theme;
import com.example.practice.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

public class ThemesAdapter extends RecyclerView.Adapter<ThemesAdapter.ViewHolder> {
    List<Theme> themes;
    Context context;
    public interface OnRecyclerViewThemeClickListener {
        void onClick(Theme themes, int position);
    }
    private OnRecyclerViewThemeClickListener mClickListener;

    public ThemesAdapter(Context context, List<Theme> themes, OnRecyclerViewThemeClickListener mClickListener) {
        this.context = context;
        this.themes = themes;
        this.mClickListener = mClickListener;
    }

    @NonNull
    @Override
    public ThemesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.theme_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Theme theme = themes.get(position);
        holder.name_themes.setText(theme.getName_lessions());
        Glide.with(context)
                .load(theme.getLecture_name())
                .placeholder (R.drawable.illustration)
                .error(R.drawable.illustration5)
                .into(holder.themes_image);
        holder.progress_theme.setProgressCompat(0,true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(theme, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView themes_image;
        TextView name_themes;
        LinearProgressIndicator progress_theme;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            themes_image = itemView.findViewById(R.id.image_theme);
            name_themes = itemView.findViewById(R.id.theme_title);
            progress_theme = itemView.findViewById(R.id.progress_theme);

        }
    }
}

