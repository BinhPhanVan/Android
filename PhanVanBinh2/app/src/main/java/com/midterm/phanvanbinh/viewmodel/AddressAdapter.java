package com.midterm.phanvanbinh.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.phanvanbinh.R;
import com.midterm.phanvanbinh.model.Address;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private ArrayList<Address> addresses;
    private ArrayList<Address> addresses_save;
    public AddressAdapter(ArrayList<Address> addresses) {
        this.addresses = addresses;
        this.addresses_save = addresses;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(addresses.get(position).getTitle());
        holder.tvDesc.setText(addresses.get(position).getDesc());
        holder.tvtimestamp.setText(addresses.get(position).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvDesc;
        public TextView tvtimestamp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvtimestamp = itemView.findViewById(R.id.tv_timestamp);
        }
    }
//  Xây dựng bộ lọc để chức năng search
    public Filter getFilter()
    {
        final Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()) {
                    addresses = addresses_save;
                }
                else
                {
                    ArrayList<Address> list = new ArrayList<Address>();
                    for (Address add : addresses_save) {
                        if ((add.getTitle()).toLowerCase().contains(key.toLowerCase()))
                        {
                            list.add(add);
                        }
                    }
                    addresses = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = addresses;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                addresses = (ArrayList<Address>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
