package com.example.riskyarea_test1.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.adapter.DoctorDiffCallback;
import com.example.riskyarea_test1.adapter.DoctorListAdapter;
import com.example.riskyarea_test1.adapter.DoctorListItemClickListener;
import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.ui.view_model.DoctorListViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DoctorListFragment extends Fragment {

    DoctorListAdapter doctorListAdapter;
    DoctorListViewModel mViewModel;
    private RecyclerView rvPassportList;
    private LiveData<ArrayList<Doctor>> doctorList;
    private ArrayList<Doctor> doctorListArray;

    public static DoctorListFragment newInstance() {
        return new DoctorListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.passport_list_fragment, container, false);
        rvPassportList = rootView.findViewById(R.id.rv_passport_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rvPassportList.setLayoutManager(layoutManager);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(DoctorListViewModel.class);
        doctorList = mViewModel.getDoctorList();
        ;
        doctorList.observe(this, doctorListResponses -> {
            Log.e("RESPONSE", Arrays.toString(doctorListResponses.toArray()));
            doctorListAdapter = new DoctorListAdapter(new DoctorDiffCallback(), new DoctorListItemClickListener() {
                @Override
                public void onClick(Doctor doctor) {
                    callToDoctor(doctor.getPhone());
                }
            });
            doctorListArray = doctorListResponses;
            rvPassportList.setAdapter(doctorListAdapter);
            doctorListAdapter.submitList(doctorListResponses);
            doctorListAdapter.notifyDataSetChanged();
        });
    }

    private void callToDoctor(String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }

    private void sortDoctorList(String specializedIn) {
        ArrayList<Doctor> sortedList = new ArrayList<>();
        for (Doctor doctor : doctorListArray) {
            if (doctor.getSpecializedIn().trim().equalsIgnoreCase(specializedIn.trim())) {
                sortedList.add(doctor);
            }
        }
        doctorListAdapter.submitList(sortedList);
        doctorListAdapter.notifyDataSetChanged();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("Visible", "" + hidden);
        if (!hidden) {
            doctorListAdapter.submitList(doctorListArray);
            doctorListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.e("MENU ITEM", "" + item.getTitle().toString());
        sortDoctorList(item.getTitle().toString());
        return super.onOptionsItemSelected(item);

    }

}
