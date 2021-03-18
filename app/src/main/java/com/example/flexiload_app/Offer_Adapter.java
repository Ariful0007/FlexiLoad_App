package com.example.flexiload_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Offer_Adapter  extends RecyclerView.Adapter<Offer_Adapter.myview> {
    List<Offer_Model> data;

    public Offer_Adapter(List<Offer_Model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gift_list,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {

        holder.text2.setText(data.get(position).getOffer_price());
        holder.text4.setText(data.get(position).getOffer_name());
        holder.text6.setText(data.get(position).getOffer_time());
        holder.text8.setText(data.get(position).getOffer_des());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String username=data.get(position).getKey_data();
                Intent intent1=new Intent(v.getContext(),Offer_Details.class);
               intent1.putExtra("key",data.get(position).getOffer_price());
                intent1.putExtra("key1",data.get(position).getOffer_name());
                intent1.putExtra("key2",data.get(position).getOffer_time());
                intent1.putExtra("key3",data.get(position).getOffer_des());
                //intent1.putExtra("key4",data.get(position).getPhone());
                v.getContext().startActivity(intent1);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView text2,text4,text6,text8;
        public myview(@NonNull View itemView) {
            super(itemView);
            text2=itemView.findViewById(R.id.text2);
            text4=itemView.findViewById(R.id.text4);
            text6=itemView.findViewById(R.id.text6);
            text8=itemView.findViewById(R.id.text7);
        }
    }
}
