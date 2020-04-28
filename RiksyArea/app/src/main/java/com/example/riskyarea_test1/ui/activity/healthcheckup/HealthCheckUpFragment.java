package com.example.riskyarea_test1.ui.activity.healthcheckup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.ui.activity.HealthCheckUpActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HealthCheckUpFragment extends Fragment {

    private HealthCheckUpViewModel mViewModel;
    private Button btnContinue;
    private Button btnSkip;

    public static HealthCheckUpFragment newInstance() {
        return new HealthCheckUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.health_check_up_fragment, container, false);

        initViews(rootView);
        setListener();

        return rootView;
    }

    private void setListener() {
        btnContinue.setOnClickListener(view -> {
            ((HealthCheckUpActivity) getActivity()).replaceFragments(AgeSelectFragment.class);
        });

        btnSkip.setOnClickListener(view -> {
             getActivity().onBackPressed();
        });
    }

    private void initViews(View rootView) {
        btnContinue = rootView.findViewById(R.id.btContinue);
        btnSkip = rootView.findViewById(R.id.btSkip);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HealthCheckUpViewModel.class);
        // TODO: Use the ViewModel
    }
}
