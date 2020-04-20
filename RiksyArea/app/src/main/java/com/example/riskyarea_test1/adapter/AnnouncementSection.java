package com.example.riskyarea_test1.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.model.response.Announcement;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class AnnouncementSection extends Section {
    private String title;
    private List<Announcement> list;

    public AnnouncementSection(@NonNull String title, List<Announcement> list) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.announcement_item_row)
                .headerResourceId(R.layout.announcement_section_header)
                .build());
        this.title = title;
        this.list = list;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new NotificationListAdapter.AnnouncementItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        NotificationListAdapter.AnnouncementItemViewHolder holder1 = (NotificationListAdapter.AnnouncementItemViewHolder) holder;
        ((NotificationListAdapter.AnnouncementItemViewHolder) holder).tvAnnounceBody.setText(list.get(position).getDetails());
        ((NotificationListAdapter.AnnouncementItemViewHolder) holder).tvAnnounceTime.setText(list.get(position).getTime());
        ((NotificationListAdapter.AnnouncementItemViewHolder) holder).tvAnnounceTitle.setText(list.get(position).getTitle());

        if (position == list.size() - 1)
            ((NotificationListAdapter.AnnouncementItemViewHolder) holder).txtViewBottomLine.setVisibility(View.GONE);
        else
            ((NotificationListAdapter.AnnouncementItemViewHolder) holder).txtViewBottomLine.setVisibility(View.VISIBLE);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.tvTitle.setText(title);
    }
}
