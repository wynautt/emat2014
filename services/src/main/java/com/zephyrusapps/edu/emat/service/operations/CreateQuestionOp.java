package com.zephyrusapps.edu.emat.service.operations;

import java.util.List;

public class CreateQuestionOp {
    int year;
    String phase;
    String number;
    String text;
    List<String> themes;
    List<String> answers;
    int validAnswerIndex;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getValidAnswerIndex() {
        return validAnswerIndex;
    }

    public void setValidAnswerIndex(int validAnswerIndex) {
        this.validAnswerIndex = validAnswerIndex;
    }
}
