package com.example.banking_system;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList user_id,user_name,user_email,user_balance;
    CustomAdapter(Context context,ArrayList user_id,ArrayList user_name,ArrayList user_email,ArrayList user_balance){
        this.context=context;
        this.user_id=user_id;
        this.user_name=user_name;
        this.user_email=user_email;
        this.user_balance=user_balance;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.single_user,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(String.valueOf(user_name.get(position)));
        holder.tv_email.setText(String.valueOf(user_email.get(position)));
        holder.tv_balance.setText(String.valueOf(user_balance.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Transfer_Data.class);
                intent.putExtra("name",String.valueOf(user_name.get(position)));
                intent.putExtra("email",String.valueOf(user_email.get(position)));
                intent.putExtra("balance",String.valueOf(user_balance.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_balance.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_email,tv_balance;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_email=itemView.findViewById(R.id.tv_email);
            tv_balance=itemView.findViewById(R.id.tv_balance);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }

    }
}