package com.example.riskyarea_test1.ui.activity.healthcheckup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.controller.FeedbackController;
import com.example.riskyarea_test1.data.dto.FeedbackDto;
import com.example.riskyarea_test1.database.PreferenceUtil;
import com.example.riskyarea_test1.helper.GenericQuestion;
import com.example.riskyarea_test1.helper.RiskFactorCalculation;
import com.example.riskyarea_test1.utils.InfoHubApplication;

import java.util.ArrayList;

public class QuestionnairesSubmissionFragment extends Fragment {
    private static final String TAG = "QuestionnairesFragment";

    private Button btnYes;
    private TextView tvTestResult;
    private ArrayList<GenericQuestion> questionsList;

    private FeedbackController feedbackController;
    private PreferenceUtil preferenceUtil;

    public static QuestionnairesSubmissionFragment newInstance() {
        return new QuestionnairesSubmissionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.question_submission_result_layout, container, false);

        feedbackController = FeedbackController.getFeedbackController();
        questionsList = (ArrayList<GenericQuestion>) InfoHubApplication.getInstance().getQuestionList();

        initViews(rootView);
        setListener();
        return rootView;
    }

    private void setListener() {
        btnYes.setOnClickListener(view -> {
            preferenceUtil.setRiskPoint(new RiskFactorCalculation().getMatrixPoint((ArrayList<GenericQuestion>) InfoHubApplication.getInstance().getAnswerList()));
            preferenceUtil.setSubmittedDate();

            FeedbackDto feedbackDto = InfoHubApplication.getInstance().getDto(requireActivity());
            Log.e(TAG, "setListener: " + feedbackDto.toString());
            feedbackController.sendFeedback(feedbackDto);
            getActivity().onBackPressed();
        });
    }

    private void initViews(View rootView) {
        preferenceUtil = new PreferenceUtil(getActivity().getApplicationContext());

        btnYes = rootView.findViewById(R.id.btYes);
        tvTestResult = rootView.findViewById(R.id.tvTestResult);
        tvTestResult.setText(new RiskFactorCalculation().getRiskMessage(new RiskFactorCalculation().getMatrixPoint((ArrayList<GenericQuestion>) InfoHubApplication.getInstance().getAnswerList())));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }
}
