package com.example.riskyarea_test1.utils;

import android.app.Activity;
import android.app.Application;

import com.example.riskyarea_test1.data.dto.FeedbackDto;
import com.example.riskyarea_test1.helper.GenericQuestion;
import com.example.riskyarea_test1.helper.RiskFactorCalculation;

import java.util.ArrayList;
import java.util.List;

public class InfoHubApplication extends Application {
    private static InfoHubApplication INSTANCE = null;
    private List<GenericQuestion> answerList = new ArrayList<>();

    private boolean isInHealthCheckUpActivityIsOn ;

    private InfoHubApplication() {
    }

    public static InfoHubApplication getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InfoHubApplication();
        }
        return (INSTANCE);
    }

    public List<GenericQuestion> getQuestionList() {

        List<GenericQuestion> questionSets = new ArrayList<>();

        questionSets.add(new GenericQuestion<>(1, "অনুগ্রহ করে আপনার বয়স উল্লেখ করুন", 56));
        questionSets.add(new GenericQuestion<Boolean>(2, "আপনার কি জ্বর আছে বা জ্বরজ্বর অনুভব করছেন ?\nঅর্থাৎ, শরীরের তাপমাত্রা ৩৭.৫°C অথবা, ৯৮.৪°F থেকে বেশি ?", true));
        questionSets.add(new GenericQuestion<Boolean>(3, "আপনার কি কাশি বা গলাব্যথা বা দুইটাই আছে ?", true));
        questionSets.add(new GenericQuestion<Boolean>(4, "আপনার কি শ্বাসকষ্ট আছে বা শ্বাস নিতে বা ফেলতে কষ্ট হচ্ছে ?", true));
        questionSets.add(new GenericQuestion<Boolean>(5, "আপনি কি বিগত ১৪ দিনের ভিতরে বিদেশ হতে এসেছেন?", true));
        questionSets.add(new GenericQuestion<Boolean>(6, "আপনি কি বিগত ১৪ দিনের ভিতরে করোনা ভাইরাসে ( কোবিড-১৯) আক্রান্ত এরকম কোন ব্যক্তির সংস্পর্শে এসেছিলেন ( একই স্থানে অবস্থান বা ভ্রমন ) ?", true));
        questionSets.add(new GenericQuestion<Boolean>(7, "বিগত ১৪ দিনে -জর ,কাশি ,শ্বাসকষ্ট আছে - এমন কারো র সংস্পর্শে কি আপনি এসেছিলেন ( পরিবার সদস্য / অফিস সহকর্মী ) ?", true));
        questionSets.add(new GenericQuestion<Boolean>(8, "   আপনি কি নিম্নের কোন অসুখে ভুগছেন? যেমন : ডায়াবেটিস, উচ্চ রক্তচাপ, হার্টের অসুখ এজমা বা হাঁপানি , দীর্ঘমেয়াদি শ্বাসকষ্টের রোগ বা সিওপিডি, কিডনি রোগ,লিভার রোগ, এইচআইভি, ক্যান্সার বা ক্যান্সারের জন্য কোন চিকিৎসা নিচ্ছেন?", true));

        return questionSets;
    }

    public List<GenericQuestion> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(GenericQuestion genericQuestion) {
        answerList.add(genericQuestion);
    }

    public FeedbackDto getDto(Activity activity) {
        List<GenericQuestion> answers = InfoHubApplication.getInstance().getAnswerList();
        FeedbackDto feedbackDto = new FeedbackDto();
        int age = (int) answers.get(0).getQuestionAnswer();
        feedbackDto.setAge(String.valueOf(age));
        feedbackDto.setHasFever((Boolean) answers.get(1).getQuestionAnswer());
        feedbackDto.setCoughThroatPain((Boolean) answers.get(2).getQuestionAnswer());
        feedbackDto.setHasBreathingProblems((Boolean) answers.get(3).getQuestionAnswer());
        feedbackDto.setRecentForeignReturn((Boolean) answers.get(4).getQuestionAnswer());
        feedbackDto.setInContactWithCovid19PositivePeopleRecently((Boolean) answers.get(5).getQuestionAnswer());
        feedbackDto.setInContactWithInfectedPeopleRecently((Boolean) answers.get(6).getQuestionAnswer());
        feedbackDto.setTakingTreatmentForOtherDisease((Boolean) answers.get(7).getQuestionAnswer());
        int result = 0;
        int value = new RiskFactorCalculation().getMatrixPoint((ArrayList<GenericQuestion>) answers);
        if (value <= 3) {
            result = 1;
        }
        if (value <= 6 && value >= 4) {
            result = 2;

        }
        if (value >= 7) {
            result = 3;
        }
        feedbackDto.setResult(String.valueOf(result));
        feedbackDto.setImei(new Utils().getDeviceUniqueID(activity.getContentResolver()));
        return feedbackDto;
    }

    public boolean isInHealthCheckUpActivityIsOn() {
        return isInHealthCheckUpActivityIsOn;
    }

    public void setInHealthCheckUpActivityIsOn(boolean inHealthCheckUpActivityIsOn) {
        isInHealthCheckUpActivityIsOn = inHealthCheckUpActivityIsOn;
    }
}
