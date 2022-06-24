package com.example.contactapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    @NonNull
    private ArrayList<Contact> contacts;
    private ArrayList<Contact> contacts_save;
    public void SetData(ArrayList<Contact> list)
    {
        this.contacts = list;
        notifyDataSetChanged();
    }

    public ContactAdapter(@NonNull ArrayList<Contact> contacts) {
        this.contacts_save = contacts;
        this.contacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(contacts.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivAvatar;
        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            ivAvatar = view.findViewById(R.id.iv_avatar);
        }

    }
    public Filter getFilter()
    {
        final Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()) {
                    contacts = contacts_save;
                }
                else
                {
                    ArrayList<Contact> list = new ArrayList<Contact>();
                    for (Contact contact : contacts_save) {
                        if (contact.getName().toLowerCase().contains(key.toLowerCase()))
                        {
                            list.add(contact);
                        }
                    }
                    contacts = list;

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = contacts;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                contacts = (ArrayList<Contact>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
