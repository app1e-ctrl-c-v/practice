package com.example.practice;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Adapters.CoursesAdapters;
import com.example.practice.Adapters.PaymentAdapter;
import com.example.practice.Models.Courses;
import com.example.practice.Models.CoursesUser;
import com.example.practice.Models.DataBinding;
import com.example.practice.Models.Payment;
import com.example.practice.Models.PaymentUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserPaymentActivity extends AppCompatActivity {

    Button cancelButton, addButton;
    RecyclerView payment_list;
    List<Payment> paymentList = new ArrayList<>();
    List<PaymentUser> payments;
    SupabaseClient supabaseClient = new SupabaseClient();
    Context context;
    PaymentAdapter.OnPaymentItemClickListener onRecyclerViewItemClickListener;
    Intent intent;
    int id, idCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_user);
        addButton = findViewById(R.id.button_add);
        cancelButton = findViewById(R.id.button_cancel);
        payment_list = findViewById(R.id.saves_courses_list);
        context = this;
        try {
            getPayment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        onRecyclerViewItemClickListener = new PaymentAdapter.OnPaymentItemClickListener() {
            @Override
            public void onClick(Payment payment, int position) {
                Bundle arguments = getIntent().getExtras();
                idCourse = arguments.getInt("id");
                new BuyTask().execute("");
                intent = new Intent(UserPaymentActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        };
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserPaymentActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(UserPaymentActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getPayment() throws IOException {
        supabaseClient.fetchUserPaymentId(new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure (IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse (String responseBody) {
                runOnUiThread(()-> {
                    Log.e("getAllOrders: onResponse", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<PaymentUser>>() {
                    }.getType();
                    payments = gson.fromJson(responseBody, type);
                    if(!payments.isEmpty()){
                        for(int i = 0; i < payments.size(); i++){
                            PaymentUser payment = payments.get(i);
                            id = payment.getPayment();
                            try {
                                supabaseClient.fetchUserPayment (id, new SupabaseClient.SBC_Callback() {
                                    @Override
                                    public void onFailure(IOException e) {
                                        runOnUiThread(() -> {
                                            Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                                        });
                                    }

                                    @Override
                                    public void onResponse(String responseBody) {
                                        runOnUiThread(() -> {
                                            Log.e("getAllOrders: onResponse", responseBody);
                                            Gson gson = new Gson();
                                            Type type = new TypeToken<List<Payment>>() {
                                            }.getType();
                                            List<Payment> payments = gson.fromJson(responseBody, type);
                                            paymentList.addAll(payments);
                                            PaymentAdapter paymentAdapter = new PaymentAdapter(context, onRecyclerViewItemClickListener, paymentList);
                                            payment_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                            payment_list.setAdapter(paymentAdapter);
                                        });
                                    }
                                });
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
                });
            }
        });
    }
    class BuyTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{ \"user\": \""+DataBinding.getUuidUser()+"\", \"course\": \""+id+"\" }");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/buy_course")
                    .method("POST", body)
                    .addHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBzemlxaGRkZ2N6YWhxbWFieXNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk2NDI4MjMsImV4cCI6MjA2NTIxODgyM30.9K9LKwhWx4pcOis5Ta7zVfTLXrRg3IdOOahUTwsolQA")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Prefer", "return=minimal")
                    .build();
            try{
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code" + response);
                return response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String answer) {
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.buy_toast), Toast.LENGTH_SHORT).show();
        }
    }
}

