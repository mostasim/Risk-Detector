package com.example.riskyarea_test1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.model.response.Announcement;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


public class NotificationListAdapter extends ListAdapter<Announcement, NotificationListAdapter.AnnouncementItemViewHolder> {

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
    public AnnouncementItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item_row, parent, false);
        return new AnnouncementItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementItemViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public static class AnnouncementItemViewHolder extends RecyclerView.ViewHolder {

        View itemLayout;
        TextView tvAnnounceTitle;
        TextView tvAnnounceTime;
        TextView tvAnnounceBody;
        TextView txtViewBottomLine;

        public AnnouncementItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemLayout = itemView;
            tvAnnounceTitle = itemView.findViewById(R.id.tvAnnounceTitle);
            tvAnnounceBody = itemView.findViewById(R.id.tvAnnounceBody);
            tvAnnounceTime = itemView.findViewById(R.id.tvAnnounceTime);
            txtViewBottomLine = itemView.findViewById(R.id.view_line);
        }

        public void bind(final Announcement item) {
            tvAnnounceTitle.setText(item.getTitle());
            tvAnnounceBody.setText(item.getDetails());
//            itemLayout.setOnClickListener(v -> notificationItemClickListener.onClick(item));
        }
    }
}
