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

import java.util.ArrayList;


public class DoctorListAdapter extends ListAdapter<Doctor, DoctorListAdapter.CountryListViewHolder> {

    DoctorListItemClickListener doctorListItemClickListener;

    public void setCountryListModels(ArrayList<Doctor> countryListModels) {
        submitList(countryListModels);
    }

    public DoctorListAdapter(@NonNull DiffUtil.ItemCallback<Doctor> diffCallback, DoctorListItemClickListener countryListItemClickListener) {
        super(diffCallback);
        this.doctorListItemClickListener = countryListItemClickListener;
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item_row, parent, false);
        return new CountryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        holder.bind(getItem(position));

    }

    public class CountryListViewHolder extends RecyclerView.ViewHolder {

        View itemLayout;
        ImageView ivCountryFlag;
        TextView tvDoctorName;
        TextView tvDoctorTitle;
        TextView tvDoctorSpecial;

        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemLayout = itemView;
            ivCountryFlag = itemView.findViewById(R.id.ivDoctor);
            tvDoctorName = itemView.findViewById(R.id.tvDoctorName);
            tvDoctorTitle = itemView.findViewById(R.id.tvDoctorTitle);
            tvDoctorSpecial = itemView.findViewById(R.id.tvSpecial);
        }

        public void bind(final Doctor item) {
            tvDoctorName.setText(item.getName());
            tvDoctorSpecial.setText(item.getSpecializedIn());
            tvDoctorTitle.setText(item.getDegree());
            itemLayout.setOnClickListener(v -> doctorListItemClickListener.onClick(item));
        }
    }
}
