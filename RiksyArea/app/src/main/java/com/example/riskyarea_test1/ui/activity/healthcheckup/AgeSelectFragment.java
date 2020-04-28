package com.example.riskyarea_test1.ui.activity.healthcheckup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.helper.GenericQuestion;
import com.example.riskyarea_test1.ui.activity.HealthCheckUpActivity;
import com.example.riskyarea_test1.utils.InfoHubApplication;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AgeSelectFragment extends Fragment {

    private TextView txtViewQuestionNo;
    private TextView txtViewQuestion;
    private Button btnNext;
    private SeekBar seekBarAge;
    private TextView txtViewAge;

    private int questionNo = 0;
    private ArrayList<GenericQuestion> questionsList;
    private int userAge = 1;

    public static AgeSelectFragment newInstance() {
        return new AgeSelectFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.age_select_layout, container, false);

        questionsList = (ArrayList<GenericQuestion>) InfoHubApplication.getInstance().getQuestionList();

        initViews(rootView);
        setListener();
        loadData(questionNo);

        return rootView;
    }

    private void loadData(int questionNo) {
        txtViewQuestionNo.setText("Steps " + (questionNo + 1) + " of " + questionsList.size());
        txtViewQuestion.setText("" + questionsList.get(questionNo).getQuestionTitle());
    }

    private void setListener() {
        btnNext.setOnClickListener(view -> {
            InfoHubApplication.getInstance().getAnswerList().clear();
            InfoHubApplication.getInstance().setAnswerList(new GenericQuestion(questionsList.get(questionNo).getId(), userAge));
            ((HealthCheckUpActivity) getActivity()).replaceFragments(QuestionnairesFragment.class);
        });

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                userAge = progress;
                txtViewAge.setText(progress + " Years");
            }
        });
    }

    private void initViews(View rootView) {
        txtViewQuestionNo = rootView.findViewById(R.id.tvSteps);
        txtViewQuestion = rootView.findViewById(R.id.textView3);

        btnNext = rootView.findViewById(R.id.button2);
        seekBarAge = rootView.findViewById(R.id.seekBar);
        txtViewAge = rootView.findViewById(R.id.tvAge);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}
