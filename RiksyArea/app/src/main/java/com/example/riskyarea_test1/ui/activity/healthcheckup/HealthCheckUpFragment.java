package com.example.riskyarea_test1.ui.activity.healthcheckup;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riskyarea_test1.R;

public class HealthCheckUpFragment extends Fragment {

    private HealthCheckUpViewModel mViewModel;

    public static HealthCheckUpFragment newInstance() {
        return new HealthCheckUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.health_check_up_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HealthCheckUpViewModel.class);
        // TODO: Use the ViewModel
    }

}
