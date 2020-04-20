package com.example.riskyarea_test1.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.adapter.DoctorDiffCallback;
import com.example.riskyarea_test1.adapter.DoctorListAdapter;
import com.example.riskyarea_test1.adapter.DoctorListItemClickListener;
import com.example.riskyarea_test1.data.model.response.Doctor;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorListFragment extends Fragment {

    private RecyclerView rvPassportList;
    private LiveData<ArrayList<Doctor>> doctorList;
    public static DoctorListFragment newInstance() {
        return new DoctorListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.passport_list_fragment, container, false);
        rvPassportList =  rootView.findViewById(R.id.rv_passport_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rvPassportList.setLayoutManager(layoutManager);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DoctorListViewModel mViewModel = ViewModelProviders.of(this).get(DoctorListViewModel.class);

        doctorList = mViewModel.getDoctorList();;
        doctorList.observe(this, doctorListResponses -> {
            Log.e("RESPONSE", Arrays.toString(doctorListResponses.toArray()));
            DoctorListAdapter doctorListAdapter = new DoctorListAdapter(new DoctorDiffCallback(), new DoctorListItemClickListener() {
                @Override
                public void onClick(Doctor doctor) {
                    callToDoctor(doctor.getPhone());
                }
            });
            rvPassportList.setAdapter(doctorListAdapter);
            doctorListAdapter.submitList(doctorListResponses);
            doctorListAdapter.notifyDataSetChanged();
        });
    }
    private void callToDoctor(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
//        Toast.makeText(DoctorListFragment.this, "" + doctor.getName(), Toast.LENGTH_SHORT).show();
    }
}
