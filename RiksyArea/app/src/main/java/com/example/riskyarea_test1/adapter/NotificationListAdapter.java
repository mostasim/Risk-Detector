package com.example.riskyarea_test1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.model.response.Announcement;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class NotificationListAdapter extends ListAdapter<Announcement, NotificationListAdapter.CountryListViewHolder> {

    NotificationItemClickListener notificationItemClickListener;

    public void setCountryListModels(ArrayList<Announcement> countryListModels) {
        submitList(countryListModels);
    }

    public NotificationListAdapter(@NonNull DiffUtil.ItemCallback<Announcement> diffCallback, NotificationItemClickListener notificationItemClickListener) {
        super(diffCallback);
        this.notificationItemClickListener = notificationItemClickListener;
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.passport_item_row, parent, false);
        return new CountryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public class CountryListViewHolder extends RecyclerView.ViewHolder {

        View itemLayout;
        ImageView ivCountryFlag;
        TextView tvCountryName;

        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemLayout = itemView;
            ivCountryFlag = itemView.findViewById(R.id.ivCountryFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
        }

        public void bind(final Announcement item) {
            tvCountryName.setText(item.getTitle());
            itemLayout.setOnClickListener(v -> notificationItemClickListener.onClick(item));
        }
    }
}
