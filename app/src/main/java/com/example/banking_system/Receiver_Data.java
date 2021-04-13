package com.example.banking_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Receiver_Data extends AppCompatActivity {
    RecyclerView recyclerView2;
    ArrayList user_email;
    ArrayList user_name;
    ArrayList user_balance;
    CustomAdapter_Receiver customAdapter_receiver;

    String sender_name,sender_email,sender_balance,sender_transfer_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiver_data);
        recyclerView2 = findViewById(R.id.recyclerView2);

        user_email=new ArrayList<>();
        user_name=new ArrayList<>();
        user_balance=new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            sender_name = bundle.getString("name");
            sender_email = bundle.getString("email");
            sender_balance = bundle.getString("balance");
            sender_transfer_amount = bundle.getString("transfer_amount");
            displayData(sender_name);
        }
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("sender_name",sender_name);
        editor.putString("sender_email",sender_email);
        editor.putString("sender_balance",sender_balance);
        editor.putString("sender_transfer_amount",sender_transfer_amount);
        editor.apply();
    }

    void displayData(String sender_name){
        this.sender_name=sender_name;
        user_name.clear();
        user_email.clear();
        user_balance.clear();
        Cursor cursor= new myDatabase(this).read_selected_userdata(sender_name);
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No User Data",Toast.LENGTH_SHORT).show();
        } else{
            while(cursor.moveToNext()){
                user_name.add(cursor.getString(1));
                user_email.add(cursor.getString(2));
                user_balance.add(cursor.getString(3));
                //Toast.makeText(this,"All Data Fetch",Toast.LENGTH_SHORT).show();
            }
        }
        customAdapter_receiver = new CustomAdapter_Receiver(Receiver_Data.this, user_name, user_email, user_balance);
        customAdapter_receiver.notifyDataSetChanged();
        recyclerView2.setAdapter(customAdapter_receiver);
        recyclerView2.setLayoutManager(new LinearLayoutManager(Receiver_Data.this));
    }




}
