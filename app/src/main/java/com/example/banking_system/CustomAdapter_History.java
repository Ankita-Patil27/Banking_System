package com.example.banking_system;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter_History extends RecyclerView.Adapter<CustomAdapter_History.mviewHolder> {

    private Context context;
    private ArrayList from_user,to_user,date_transfer,amount_total;

    CustomAdapter_History(Context context, ArrayList from_user, ArrayList to_user, ArrayList date_transfer, ArrayList amount_total){
        this.context=context;
        this.from_user=from_user;
        this.to_user=to_user;
        this.date_transfer=date_transfer;
        this.amount_total=amount_total;
    }

    @NonNull
    @Override
    public mviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_transfer, parent,false);
        return new mviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mviewHolder holder, int position) {
        holder.tv_from.setText(String.valueOf(from_user.get(position)));
        holder.tv_to.setText(String.valueOf(to_user.get(position)));
        holder.tv_date.setText(String.valueOf(date_transfer.get(position)));
        holder.tv_amount.setText(String.valueOf(amount_total.get(position)));
    }



    @Override
    public int getItemCount() {
        return to_user.size();
    }

    public class mviewHolder extends RecyclerView.ViewHolder{
        TextView tv_from,tv_to,tv_date,tv_amount;
        public mviewHolder(@NonNull View itemView) {
            super(itemView);
            tv_from=itemView.findViewById(R.id.tv_from);
            tv_to=itemView.findViewById(R.id.tv_to);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_amount=itemView.findViewById(R.id.tv_amount);
        }
    }
}
