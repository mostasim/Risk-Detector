package com.example.riskyarea_test1.helper;

import java.util.ArrayList;

public class RiskFactorCalculation {

    public static void main(String[] args) {
        RiskFactorCalculation riskFactorCalculation = new RiskFactorCalculation();
        riskFactorCalculation.loadQuestion();
        System.out.println("Point "+riskFactorCalculation.getMatrixPoint());
    }
    private ArrayList<GenericQuestion> questionSets;

    public void loadQuestion() {
        questionSets = new ArrayList<GenericQuestion>();
        GenericQuestion<Integer> age = new GenericQuestion<>(1, "What is your age?", 56);
        GenericQuestion<Boolean> q_2 = new GenericQuestion<Boolean>(2, "What is your age?", true);
        GenericQuestion<Boolean> q_3 = new GenericQuestion<Boolean>(3, "What is your age?", true);
        GenericQuestion<Boolean> q_4 = new GenericQuestion<Boolean>(4, "What is your age?", true);
        GenericQuestion<Boolean> q_5 = new GenericQuestion<Boolean>(5, "What is your age?", true);
        GenericQuestion<Boolean> q_6 = new GenericQuestion<Boolean>(6, "What is your age?", true);
        GenericQuestion<Boolean> q_7 = new GenericQuestion<Boolean>(7, "What is your age?", true);
        GenericQuestion<Boolean> q_8 = new GenericQuestion<Boolean>(8, "What is your age?", true);
        questionSets.add(age);
        questionSets.add(q_2);
        questionSets.add(q_3);
        questionSets.add(q_4);
        questionSets.add(q_5);
        questionSets.add(q_6);
        questionSets.add(q_7);
        questionSets.add(q_8);
    }

    public int getMatrixPoint() {
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
}
