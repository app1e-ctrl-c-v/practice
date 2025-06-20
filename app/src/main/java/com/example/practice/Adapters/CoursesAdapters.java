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
import com.example.practice.R;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapters extends RecyclerView.Adapter<CoursesAdapters.ViewHolder> {
    List<Courses> courses;
    Context context;
    List<Courses> result = new ArrayList<>();
    List<Courses> copy = new ArrayList<>();
    int copy1 = 0;
    public interface OnRecyclerViewItemClickListener {
        void onClick(Courses courses, int position);
    }
    private OnRecyclerViewItemClickListener mClickListener;

    public CoursesAdapters(Context context, List<Courses> courses, OnRecyclerViewItemClickListener mClickListener) {
        this.context = context;
        this.courses = courses;
        this.mClickListener = mClickListener;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filter(int category) {
        result.clear();
        copy.addAll(courses);
        if(!(category == 0)){
            for(Courses item: courses){
                if(item.category == category){
                    result.add(item);
                }
            }
            courses.clear();
            courses.addAll(result);
            result.clear();
            notifyDataSetChanged();
        } else {
            courses.clear();
            courses.addAll(copy);
            notifyDataSetChanged();

        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filter(String text) {
        if(copy1==0){
        copy.addAll(courses);
        }
        if(!text.isEmpty()) {
            text = text.toLowerCase();
            for (Courses item : courses) {
                if (item.name.toLowerCase().contains(text) || item.description.toLowerCase().contains(text)) {
                    result.add(item);
                }

            }
            courses.clear();
            courses.addAll(result);
            result.clear();
            notifyDataSetChanged();
            copy1++;
        } else {
            courses.clear();
            courses.addAll(copy);
            notifyDataSetChanged();
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void restore_list(){
        courses.clear();
        result.clear();
        courses.addAll(copy);
        copy.clear();
        copy1 = 0;
        notifyDataSetChanged();

    }
    public int getLength(){
        return courses.size();
    }

    @NonNull
    @Override
    public CoursesAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.courses_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapters.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Courses courses1 = courses.get(position);
        double pr = courses1.getPrice();
        double time = courses1.getDuration();
        int hour = (int) Math.floor(time);
        double fractionalPart = time - hour;
        int min = (int) Math.round(fractionalPart * 60);
        String d = hour+" h "+min+" min", p = "$ "+pr;
        holder.short_description_courses.setText(courses1.getTitle());
        holder.title_courses.setText(courses1.getName());
        holder.duration_corses.setText(d);
        holder.price_courses.setText(p);
        String url = "https://psziqhddgczahqmabysn.supabase.co/storage/v1/object/public/imagecourses//";
        Glide.with(context)
                .load(url + courses1.getCover_name())
                .placeholder (R.drawable.illustration)
                .error(R.drawable.illustration5)
                .into(holder.courses_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(courses1, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView courses_image;
        TextView price_courses, duration_corses, title_courses, short_description_courses;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courses_image = itemView.findViewById(R.id.courses_image);
            price_courses = itemView.findViewById(R.id.price_courses);
            duration_corses = itemView.findViewById(R.id.duration);
            title_courses = itemView.findViewById(R.id.title_courses);
            short_description_courses = itemView.findViewById(R.id.short_description_courses);
        }
    }
}
