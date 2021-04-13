package com.example.banking_system;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class CustomAdapter_Receiver extends RecyclerView.Adapter<CustomAdapter_Receiver.MyViewHolder2> {
    private Context context2;
    private ArrayList user_id,user_name,user_email,user_balance;

    CustomAdapter_Receiver(Context context2,ArrayList user_name,ArrayList user_email,ArrayList user_balance){
        this.context2=context2;
        this.user_name=user_name;
        this.user_email=user_email;
        this.user_balance=user_balance;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context2);
        View view=inflater.inflate(R.layout.single_user,parent,false);
        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.tv_name.setText(String.valueOf(user_name.get(position)));
        holder.tv_email.setText(String.valueOf(user_email.get(position)));
        holder.tv_balance.setText(String.valueOf(user_balance.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context2,Transaction_Complete.class);
                intent.putExtra("receiver_name",String.valueOf(user_name.get(position)));
                intent.putExtra("receiver_email",String.valueOf(user_email.get(position)));
                intent.putExtra("receiver_balance",String.valueOf(user_balance.get(position)));
                context2.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_balance.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView tv_name,tv_email,tv_balance;
        LinearLayout mainLayout;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_balance = itemView.findViewById(R.id.tv_balance);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
}}