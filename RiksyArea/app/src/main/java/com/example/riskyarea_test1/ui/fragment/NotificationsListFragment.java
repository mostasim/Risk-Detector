package com.example.riskyarea_test1.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.adapter.NotificationDiffCallback;
import com.example.riskyarea_test1.adapter.NotificationItemClickListener;
import com.example.riskyarea_test1.adapter.NotificationListAdapter;
import com.example.riskyarea_test1.data.model.response.Announcement;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationsListFragment extends Fragment {

    private RecyclerView rvPassportList;
    private LiveData<ArrayList<Announcement>> announcementList;
    public static NotificationsListFragment newInstance() {
        return new NotificationsListFragment();
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

        NotificationsListViewModel mViewModel = ViewModelProviders.of(this).get(NotificationsListViewModel.class);
        announcementList = mViewModel.getAnnouncementList();
        announcementList.observe(this, new Observer<ArrayList<Announcement>>() {
            @Override
            public void onChanged(ArrayList<Announcement> passportListResponses) {
                Log.e("RESPONSE", Arrays.toString(passportListResponses.toArray()));
                NotificationListAdapter notificationListAdapter = new NotificationListAdapter(new NotificationDiffCallback(), new NotificationItemClickListener() {
                    @Override
                    public void onClick(Announcement passport) {

                    }
                });
                rvPassportList.setAdapter(notificationListAdapter);
                notificationListAdapter.submitList(passportListResponses);
            }
        });

    }

}
