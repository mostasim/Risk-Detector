package com.example.riskyarea_test1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.data.model.response.Passport;

import java.util.ArrayList;


public class PassportListAdapter extends ListAdapter<Passport, PassportListAdapter.PassportListViewHolder> {

    private PassportListItemClickListener passportListItemClickListener;


    public void setCountryListModels(ArrayList<Passport> countryListModels) {

        submitList(countryListModels);
    }

    public PassportListAdapter(@NonNull DiffUtil.ItemCallback<Passport> diffCallback, PassportListItemClickListener passportListItemClickListener) {
        super(diffCallback);
        this.passportListItemClickListener = passportListItemClickListener;
    }

    @NonNull
    @Override
    public PassportListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.passport_item_row, parent, false);
        return new PassportListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassportListViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public class PassportListViewHolder extends RecyclerView.ViewHolder {

        View itemLayout;
        ImageView ivCountryFlag;
        TextView tvCountryName;

        public PassportListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemLayout = itemView;
            ivCountryFlag = itemView.findViewById(R.id.ivCountryFlag);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
        }

        public void bind(final Passport item) {
            tvCountryName.setText(item.getName());
            itemLayout.setOnClickListener(v -> passportListItemClickListener.onClick(item));
        }
    }
}
