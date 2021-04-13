package com.example.banking_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Transfer_Data extends AppCompatActivity {

    TextView tv_name_display,tv_email_display,tv_balance_display;
    Button btn_transfer;
    String name,email,balance;
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_data);
        tv_name_display=findViewById(R.id.tv_name_display);
        tv_email_display=findViewById(R.id.tv_email_display);
        tv_balance_display=findViewById(R.id.tv_balance_display);
        btn_transfer=findViewById(R.id.btn_transfer);
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTransferAmount();
            }
        });
        getIntentData();
    }

    void getIntentData(){
        if(getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("balance")){
            name=getIntent().getStringExtra("name");
            email=getIntent().getStringExtra("email");
            balance=getIntent().getStringExtra("balance");
            tv_name_display.setText(name);
            tv_email_display.setText(email);
            tv_balance_display.setText(balance);
        }
        else{
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
    }
    private void setTransferAmount() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Transfer_Data.this);
        View view = getLayoutInflater().inflate(R.layout.transfer_amount, null);
        builder.setTitle("Enter amount").setView(view).setCancelable(false);
        final EditText amount = (EditText) view.findViewById(R.id.et_amount);

        builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //transactionCancel();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount.getText().toString().isEmpty()) {
                    amount.setError("Amount can't be empty");
                }else if(Double.parseDouble(amount.getText().toString()) > (Double.parseDouble(tv_balance_display.getText().toString()))){
                 amount.setError("Your account don't have enough balance");
                }
                else {
                    //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Transfer_Data.this, Receiver_Data.class);
                    intent.putExtra("name", tv_name_display.getText().toString());
                    intent.putExtra("email", tv_email_display.getText().toString());
                    intent.putExtra("balance", tv_balance_display.getText().toString());
                    intent.putExtra("transfer_amount", amount.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });


    }}