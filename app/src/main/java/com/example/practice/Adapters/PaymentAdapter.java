package com.example.practice.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Models.Category;
import com.example.practice.Models.Courses;
import com.example.practice.Models.Payment;
import com.example.practice.R;

import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    List<Payment> payment_list;
    Context context;
    public interface OnPaymentItemClickListener {
        void onClick(Payment payment, int position);
    }
    private OnPaymentItemClickListener mClickListener;

    public PaymentAdapter(Context context, OnPaymentItemClickListener mClickListener, List<Payment> payment_list) {
        this.context = context;
        this.mClickListener = mClickListener;
        this.payment_list = payment_list;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_item, parent, false);
        return new PaymentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Payment payments = payment_list.get(position);
        holder.name_payment.setText(payments.getMethod());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(payments, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return payment_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_payment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_payment = itemView.findViewById(R.id.name_payment);
        }
    }
}

