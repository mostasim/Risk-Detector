package com.example.riskyarea_test1.helper;

import java.util.ArrayList;

public class RiskFactorCalculation {

    private ArrayList<GenericQuestion> questionSets;

    public void loadQuestion() {
        questionSets = new ArrayList<GenericQuestion>();
        GenericQuestion<Integer> age = new GenericQuestion<>(1, "অনুগ্রহ করে আপনার বয়স উল্লেখ করুন", 56);
        GenericQuestion<Boolean> q_2 = new GenericQuestion<Boolean>(2, "আপনার কি জ্বর আছে বা জ্বরজ্বর অনুভব করছেন ?\nঅর্থাৎ, শরীরের তাপমাত্রা ৩৭.৫°C অথবা, ৯৮.৪°F থেকে বেশি ?", true);
        GenericQuestion<Boolean> q_3 = new GenericQuestion<Boolean>(3, "আপনার কি কাশি বা গলাব্যথা বা দুইটাই আছে ?", true);
        GenericQuestion<Boolean> q_4 = new GenericQuestion<Boolean>(4, "আপনার কি শ্বাসকষ্ট আছে বা শ্বাস নিতে বা ফেলতে কষ্ট হচ্ছে ?", true);
        GenericQuestion<Boolean> q_5 = new GenericQuestion<Boolean>(5, "আপনি কি বিগত ১৪ দিনের ভিতরে বিদেশ হতে এসেছেন?", true);
        GenericQuestion<Boolean> q_6 = new GenericQuestion<Boolean>(6, "আপনি কি বিগত ১৪ দিনের ভিতরে করোনা ভাইরাসে ( কোবিড-১৯) আক্রান্ত এরকম কোন ব্যক্তির সংস্পর্শে এসেছিলেন ( একই স্থানে অবস্থান বা ভ্রমন ) ?", true);
        GenericQuestion<Boolean> q_7 = new GenericQuestion<Boolean>(7, "বিগত ১৪ দিনে -জর ,কাশি ,শ্বাসকষ্ট আছে - এমন কারো র সংস্পর্শে কি আপনি এসেছিলেন ( পরিবার সদস্য / অফিস সহকর্মী ) ?", true);
        GenericQuestion<Boolean> q_8 = new GenericQuestion<Boolean>(8, "   আপনি কি নিম্নের কোন অসুখে ভুগছেন? যেমন : ডায়াবেটিস, উচ্চ রক্তচাপ, হার্টের অসুখ এজমা বা হাঁপানি , দীর্ঘমেয়াদি শ্বাসকষ্টের রোগ বা সিওপিডি, কিডনি রোগ,লিভার রোগ, এইচআইভি, ক্যান্সার বা ক্যান্সারের জন্য কোন চিকিৎসা নিচ্ছেন?", true);
        questionSets.add(age);
        questionSets.add(q_2);
        questionSets.add(q_3);
        questionSets.add(q_4);
        questionSets.add(q_5);
        questionSets.add(q_6);
        questionSets.add(q_7);
        questionSets.add(q_8);
    }

    public int getMatrixPoint(ArrayList<GenericQuestion> questionSets) {
        int point = 0;
        for (GenericQuestion question : questionSets) {
            switch (question.getId()) {
                case 1:
                    int age = (Integer) question.getQuestionAnswer();
                    if (age < 10 || age > 55) {
                        point += 2;
                    } else if (age >= 35 && age < 55) {
                        point += 1;
                    } else if (age > 10 && age < 35) {
                        point += 0;
                    } else {
                        point += 0;
                    }
                    break;
                case 2:
                    if ((Boolean) question.getQuestionAnswer()) {
                        point += 1;
                    }
                    break;
                case 3:
                    if ((Boolean) question.getQuestionAnswer()) {
                        point += 3;
                    }
                    break;
                case 4:
                    if ((Boolean) question.getQuestionAnswer()) {
                        point += 2;
                    }
                    break;
                case 5:
                    if ((Boolean) question.getQuestionAnswer()) {
                        point += 1;
                    }
                    break;
                case 6:
                    if ((Boolean) question.getQuestionAnswer()) {
                        point += 3;
                    }
                    break;
                case 7:
                    if ((Boolean) question.getQuestionAnswer()) {
                        point += 1;
                    }
                    break;
                case 8:
                    if ((Boolean) question.getQuestionAnswer()) {
                        point += 2;
                    }
                    break;
                default:
                    break;
            }
        }
        return point;
    }

    public String getRiskMessage(int value) {
        String message = "";
        if (value <= 3) {
            message = "You are at low risk of getting infected.\nPlease stay safe & stay at home";
        }
        if (value <= 6 && value >= 4) {
            message = "You are at moderate risk of getting infected.\nPlease contact with IEDCR help line 10655\nas soon as possible";
        }
        if (value >= 7) {
            message = "You are at high risk of getting infected.\nPlease contact with IEDCR help line 10655\nimmediately";
        }
        return message;
    }
}
