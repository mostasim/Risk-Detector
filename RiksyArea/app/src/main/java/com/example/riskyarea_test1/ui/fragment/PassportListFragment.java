package com.example.riskyarea_test1.ui.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.adapter.DoctorDiffCallback;
import com.example.riskyarea_test1.adapter.DoctorListAdapter;
import com.example.riskyarea_test1.adapter.DoctorListItemClickListener;
import com.example.riskyarea_test1.adapter.PassportDiffCallback;
import com.example.riskyarea_test1.adapter.PassportListAdapter;
import com.example.riskyarea_test1.adapter.PassportListItemClickListener;
import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.data.model.response.Passport;

import java.util.ArrayList;
import java.util.Arrays;

public class PassportListFragment extends Fragment {

    private RecyclerView rvPassportList;
    private LiveData<ArrayList<Passport>> _passportListData ;
    public static PassportListFragment newInstance() {
        return new PassportListFragment();
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

        PassportListViewModel mViewModel = ViewModelProviders.of(this).get(PassportListViewModel.class);
        _passportListData = mViewModel.getPassportList();
        _passportListData.observe(this, new Observer<ArrayList<Passport>>() {
            @Override
            public void onChanged(ArrayList<Passport> passportListResponses) {
                Log.e("RESPONSE", Arrays.toString(passportListResponses.toArray()));
                PassportListAdapter passportListAdapter = new PassportListAdapter(new PassportDiffCallback(), new PassportListItemClickListener() {
                    @Override
                    public void onClick(Passport passport) {

                    }
                });
                rvPassportList.setAdapter(passportListAdapter);
                passportListAdapter.submitList(passportListResponses);
            }
        });

    }

}
