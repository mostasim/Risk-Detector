package com.example.riskyarea_test1.helper;

public  class GenericQuestion<T>  {
    private int id;
    private String questionTitle;
    private T questionAnswer;

    public GenericQuestion() {
    }

    public GenericQuestion(int id, String questionTitle, T questionAnswer) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.questionAnswer = questionAnswer;
    }

    public GenericQuestion(int id, T questionAnswer) {
        this.id = id;
        this.questionAnswer = questionAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public T getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(T questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
