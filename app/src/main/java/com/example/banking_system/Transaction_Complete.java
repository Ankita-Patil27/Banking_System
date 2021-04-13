package com.example.banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Transaction_Complete extends AppCompatActivity {

    Button back;
    ImageView iv_success;
    Double remaining_amount,select_user_remaining_amount;
    String date,sender_name,sender_email,sender_balance,sender_transfer_amount,receiver_name,receiver_balance,receiver_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction__complete);
        back=findViewById(R.id.back);
        iv_success=findViewById(R.id.iv_success);
        Glide.with(this).load(R.drawable.transaction_success).into(iv_success);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
        date = simpleDateFormat.format(calendar.getTime());

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        sender_name = sharedPreferences.getString("sender_name","");
        sender_email = sharedPreferences.getString("sender_email","");
        sender_balance = sharedPreferences.getString("sender_balance","");
        sender_transfer_amount = sharedPreferences.getString("sender_transfer_amount","");
        getIntentData();
        //Toast.makeText(this, "Transaction Successful!", Toast.LENGTH_LONG).show();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_user(receiver_name);
                Intent intent=new Intent(Transaction_Complete.this,Home_Screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
    void getIntentData(){
            if(getIntent().hasExtra("receiver_name") && getIntent().hasExtra("receiver_email") && getIntent().hasExtra("receiver_balance")){
                receiver_name=getIntent().getStringExtra("receiver_name");
                receiver_email=getIntent().getStringExtra("receiver_email");
                receiver_balance=getIntent().getStringExtra("receiver_balance");
            }
            else{
                Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
            }
        }

    public void select_user(String receiver_name) {
        this.receiver_name=receiver_name;
        Cursor cursor = new myDatabase(this).read_particular_data(receiver_name);
        while(cursor.moveToNext()) {
            receiver_name = cursor.getString(1);
            Log.d("name",receiver_name);
            receiver_balance = cursor.getString(3);
            Log.d("balance",receiver_balance);
            Double select_user_balance = Double.parseDouble(receiver_balance);
            Double select_user_transfer_amount = Double.parseDouble(sender_transfer_amount);
            select_user_remaining_amount = select_user_balance + select_user_transfer_amount;
            new myDatabase(this).insertTransferData(date, sender_name, receiver_name, select_user_transfer_amount);
            new myDatabase(this).updateAmount(receiver_name, select_user_remaining_amount);
            calculateAmount();
            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }}
        public void calculateAmount() {
            Double current_amount = Double.parseDouble(sender_balance);
            Double transferamount = Double.parseDouble(sender_transfer_amount);
            remaining_amount = current_amount - transferamount;
            //r_amount = remaining_amount.toString();
            new myDatabase(this).updateAmount(sender_name, remaining_amount);
            //Toast.makeText(this, "Calculate Amount", Toast.LENGTH_SHORT).show();
        }
}