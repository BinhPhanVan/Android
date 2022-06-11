package com.example.dogapp.viewmodel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.view.OnSwipeTouchListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {

    private ArrayList<DogBreed> dogBreeds;
    private ArrayList<DogBreed> dogBreeds_save;
    public DogsAdapter(ArrayList<DogBreed> dogBreeds) {
        this.dogBreeds_save = dogBreeds;
        this.dogBreeds = dogBreeds;
    }

    @NonNull
    @Override
    public DogsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder holder, int position) {

        holder.tvName.setText(dogBreeds.get(position).getName());
        holder.tvBredFor.setText(dogBreeds.get(position).getBredFor());
        Picasso.get().load(dogBreeds.get(position).getUrl()).into(holder.ivAvatar);
        holder.tvDetailName.setText(dogBreeds.get(position).getName());
        holder.tvDetailLifeSpan.setText(dogBreeds.get(position).getLifeSpan());
        holder.tvDetailOrigin.setText(dogBreeds.get(position).getOrigin());
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvBredFor;
        public ImageView ivAvatar;
        public LinearLayout lnMain;
        public LinearLayout lnDetail;
        public TextView tvDetailName;
        public TextView tvDetailOrigin;
        public TextView tvDetailLifeSpan;
        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_name);
            tvBredFor = view.findViewById(R.id.tv_bredFor);
            ivAvatar = view.findViewById(R.id.iv_avatar);
            lnMain = view.findViewById(R.id.ln_main);
            lnDetail = view.findViewById(R.id.ln_detail);
            tvDetailName = view.findViewById(R.id.tv_detailName);
            tvDetailOrigin = view.findViewById(R.id.tv_DetailOrigin);
            tvDetailLifeSpan = view.findViewById(R.id.tv_detailLifeSpan);
            view.setOnTouchListener(new OnSwipeTouchListener(view.getContext())
            {
                public void viewDetail() {
                    if (lnMain.getVisibility() == View.VISIBLE) {
                        lnMain.setVisibility(View.INVISIBLE);
                        lnDetail.setVisibility(View.VISIBLE);
                    } else {
                        lnMain.setVisibility(View.VISIBLE);
                        lnDetail.setVisibility(View.INVISIBLE);
                    }
                }

                public void onSwipeRight() {
                    viewDetail();
                }
                public void onSwipeLeft() {
                    viewDetail();
                }
                public void onClick() {
                    DogBreed dogBreed = dogBreeds.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dogBreed", dogBreed);
                    Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    public Filter getFilter()
    {
        final Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key = constraint.toString();
                if (key.isEmpty()) {
                    dogBreeds = dogBreeds_save;
                }
                else
                {
                    ArrayList<DogBreed> list = new ArrayList<DogBreed>();
                    for (DogBreed contact : dogBreeds_save) {
                        if ((contact.getName()).toLowerCase().contains(key.toLowerCase()))
                        {
                            list.add(contact);
                        }
                    }
                    dogBreeds = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = dogBreeds;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dogBreeds = (ArrayList<DogBreed>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
