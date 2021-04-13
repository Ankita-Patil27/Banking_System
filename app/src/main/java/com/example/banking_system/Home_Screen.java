package com.example.banking_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home_Screen extends AppCompatActivity {
    ImageView history;
    RecyclerView recyclerView;
    myDatabase mydb;
    ArrayList<String> user_id,user_name,user_email,user_balance;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__screen);
        recyclerView = findViewById(R.id.recyclerView);
        history=findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home_Screen.this,ViewHistory.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"History Clicked",Toast.LENGTH_SHORT).show();
            }
        });
        mydb = new myDatabase(Home_Screen.this);
        user_id=new ArrayList<>();
        user_email=new ArrayList<>();
        user_name=new ArrayList<>();
        user_balance=new ArrayList<>();
        arrayData();

        customAdapter=new CustomAdapter(Home_Screen.this,user_id,user_name,user_email,user_balance);
        customAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home_Screen.this));

    }
    void arrayData(){
        user_name.clear();
        user_email.clear();
        user_balance.clear();
        Cursor cursor= mydb.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No User Data",Toast.LENGTH_SHORT).show();
        } else{
            while(cursor.moveToNext()){
                user_name.add(cursor.getString(1));
                user_email.add(cursor.getString(2));
                user_balance.add(cursor.getString(3));
                //Toast.makeText(getApplicationContext(),"All Data Fetch",Toast.LENGTH_SHORT).show();
            }
        }

    }


}
