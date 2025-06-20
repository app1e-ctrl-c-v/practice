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
import com.example.practice.Models.DataBinding;
import com.example.practice.Models.Payment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PaymentActivity extends AppCompatActivity {

    Button cancelButton;
    RecyclerView payment_list;
    List<Payment> paymentList;
    SupabaseClient supabaseClient = new SupabaseClient();
    Context context;
    PaymentAdapter.OnPaymentItemClickListener onRecyclerViewItemClickListener;
    Intent intent;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
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
                id = payment.getId();
                new AddTask().execute("");
                intent = new Intent(PaymentActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        };
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PaymentActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getPayment() throws IOException {
        supabaseClient.fetchAllPayment (new SupabaseClient.SBC_Callback() {
            @Override
            public void onFailure (IOException e) {
                runOnUiThread(() -> {
                    Log.e("getAllOrders: onFailure", e.getLocalizedMessage());
                });
            }
            @Override
            public void onResponse (String responseBody) {
                runOnUiThread(()-> {
                    Log.e("getAllOrders: onResponse",responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Payment>>(){}.getType();
                    paymentList = gson.fromJson(responseBody, type);
                    PaymentAdapter paymentAdapter = new PaymentAdapter(context, onRecyclerViewItemClickListener, paymentList);
                    payment_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    payment_list.setAdapter(paymentAdapter);
                });
            }
        });

    }
    class AddTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{ \"user\": \""+DataBinding.getUuidUser()+"\", \"payment\": \""+id+"\" }");
            Request request = new Request.Builder()
                    .url("https://psziqhddgczahqmabysn.supabase.co/rest/v1/payplament_user")
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
            Toast.makeText(getApplicationContext(), getResources().getText(R.string.add_payplament_toast), Toast.LENGTH_SHORT).show();
        }
    }

}
