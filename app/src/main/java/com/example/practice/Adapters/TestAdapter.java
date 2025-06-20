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
import com.example.practice.Models.Test;
import com.example.practice.Models.Theme;
import com.example.practice.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    List<Test> testList;
    Context context;
    public interface OnRecyclerViewTestClickListener {
        void onClick(Test tests, int position);
    }
    private OnRecyclerViewTestClickListener mClickListener;

    public TestAdapter(Context context, List<Test> testList, OnRecyclerViewTestClickListener mClickListener) {
        this.context = context;
        this.testList = testList;
        this.mClickListener = mClickListener;
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_item, parent, false);
        return new TestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Test test = testList.get(position);
        holder.name_test.setText(test.getName());
        holder.number_test.setText(""+(position+1));
        holder.title_test.setText(test.getDescription());
        String url = "https://psziqhddgczahqmabysn.supabase.co/storage/v1/object/public/imagelessons//";
        Glide.with(context)
                .load(url + test.getName_cover())
                .placeholder (R.drawable.illustration)
                .error(R.drawable.illustration5)
                .into(holder.test_image);
        holder.buttonBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(test, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView test_image;
        TextView name_test, number_test, title_test;
        Button buttonBegin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            test_image = itemView.findViewById(R.id.courses_image);
            name_test = itemView.findViewById(R.id.title_courses);
            number_test = itemView.findViewById(R.id.duration);
            title_test = itemView.findViewById(R.id.short_description_courses);
            buttonBegin = itemView.findViewById(R.id.buttonBegin);

        }
    }
}

