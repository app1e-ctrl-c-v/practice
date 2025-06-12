package com.example.practice.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Models.Category;
import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> category_list;
    Context context;

    public CategoryAdapter(List<Category> category_list, Context context) {
        this.category_list = category_list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category categories = category_list.get(position);
        holder.name_category.setText(categories.getName());
    }


    @Override
    public int getItemCount() {
        return category_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_category = itemView.findViewById(R.id.name_category);
        }
    }
}
