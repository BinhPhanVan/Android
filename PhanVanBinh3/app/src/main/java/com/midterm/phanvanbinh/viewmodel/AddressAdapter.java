package com.midterm.phanvanbinh.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.phanvanbinh.R;
import com.midterm.phanvanbinh.DetailActivity;
import com.midterm.phanvanbinh.model.Address;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.viewHolder>{
    ArrayList<Address> addList;
    Context context;

    public AddressAdapter(ArrayList<Address> list_data, Context context) {
        this.addList = list_data;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Address address = addList.get(position);
        holder.title_address.setText(address.getTitle());
        holder.desc_address.setText(address.getDesc());
        holder.timestamp_address.setText(address.getTimeStamp());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("item_address" , address);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                addList.remove(address);
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return addList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView title_address, desc_address, timestamp_address;
        public viewHolder(@NonNull View view) {
            super(view);
            title_address = itemView.findViewById(R.id.title_address);
            desc_address = itemView.findViewById(R.id.desc_address);
            timestamp_address = itemView.findViewById(R.id.timestamp_address);

        }
    }
}
