package com.example.banking_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewHistory extends AppCompatActivity {

    RecyclerView recyclerView3;
    myDatabase mydb;
    ArrayList<String> user_from,user_to,user_date,user_amount;
    CustomAdapter_History customAdapter_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_history);
        recyclerView3 = findViewById(R.id.recyclerView3);

        mydb = new myDatabase(ViewHistory.this);
        user_from=new ArrayList<>();
        user_to=new ArrayList<>();
        user_date=new ArrayList<>();
        user_amount=new ArrayList<>();
        arrayData();

    }
    void arrayData(){
        Cursor cursor= new myDatabase(this).read_transfer_data();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No User Data",Toast.LENGTH_SHORT).show();
        } else{
            while(cursor.moveToNext()){
                //user_id.add(cursor.getString(0));
                user_from.add(cursor.getString(1));
                user_to.add(cursor.getString(2));
                user_date.add(cursor.getString(0));
                user_amount.add(cursor.getString(3));
                //Toast.makeText(getApplicationContext(),"All Data Fetch",Toast.LENGTH_SHORT).show();
            }
        }
        customAdapter_history=new CustomAdapter_History(ViewHistory.this,user_from,user_to,user_date,user_amount);
        recyclerView3.setAdapter(customAdapter_history);
        recyclerView3.setLayoutManager(new LinearLayoutManager(ViewHistory.this));
        customAdapter_history.notifyDataSetChanged();
    }
}