package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {
    public RecyclerviewAdapter(ArrayList<display> displayArrayList, Context _context) {
        this.displayArrayList = displayArrayList;
        this._context = _context;
    }

    ArrayList<display> displayArrayList;
Context _context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        MyViewHolder my = new MyViewHolder(itemView);
        return my;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_name.setText(String.valueOf(displayArrayList.get(position).name));
        holder.txt_price.setText(String.valueOf(displayArrayList.get(position).price));
        holder.txt_brand.setText(String.valueOf(displayArrayList.get(position).brand));
    }

    @Override
    public int getItemCount() {
        return displayArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_price,txt_brand;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_brand = itemView.findViewById(R.id.txt_brand);
        }
    }
}
