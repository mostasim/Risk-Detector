package com.example.riskyarea_test1.ui.activity.healthcheckup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.controller.FeedbackController;
import com.example.riskyarea_test1.database.PreferenceUtil;
import com.example.riskyarea_test1.helper.GenericQuestion;
import com.example.riskyarea_test1.ui.activity.HealthCheckUpActivity;
import com.example.riskyarea_test1.utils.InfoHubApplication;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionnairesFragment extends Fragment {
    private static final String TAG = "QuestionnairesFragment";

    private TextView txtViewQuestionNo;
    private TextView txtViewQuestion;
    private Button btnYes;
    private Button btnNo;

    private int questionNo = 1;
    private ArrayList<GenericQuestion> questionsList;

    private FeedbackController feedbackController;
    private PreferenceUtil preferenceUtil;

    public static QuestionnairesFragment newInstance() {
        return new QuestionnairesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question_select_layout, container, false);

        feedbackController = FeedbackController.getFeedbackController();
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
        btnYes.setOnClickListener(view -> {
            InfoHubApplication.getInstance().setAnswerList(new GenericQuestion(questionsList.get(questionNo).getId(), true));
            if (questionNo < questionsList.size() - 1) {
                questionNo++;
                loadData(questionNo);
            } else if (questionNo == questionsList.size() - 1) {
                ((HealthCheckUpActivity) getActivity()).replaceFragments(QuestionnairesSubmissionFragment.class);
            }
        });

        btnNo.setOnClickListener(view -> {
            InfoHubApplication.getInstance().setAnswerList(new GenericQuestion(questionsList.get(questionNo).getId(), false));
            if (questionNo < questionsList.size() - 1) {
                questionNo++;
                loadData(questionNo);
            } else if (questionNo == questionsList.size() - 1) {
                ((HealthCheckUpActivity) getActivity()).replaceFragments(QuestionnairesSubmissionFragment.class);
            }
        });
    }

    private void initViews(View rootView) {
        preferenceUtil = new PreferenceUtil(getActivity().getApplicationContext());

        txtViewQuestionNo = rootView.findViewById(R.id.tvSteps);
        txtViewQuestion = rootView.findViewById(R.id.tvQuestion);

        btnYes = rootView.findViewById(R.id.btYes);
        btnNo = rootView.findViewById(R.id.btNo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}
