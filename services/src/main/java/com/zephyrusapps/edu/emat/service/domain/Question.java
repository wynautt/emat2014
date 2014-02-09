package com.zephyrusapps.edu.emat.service.domain;

import com.google.common.base.Joiner;
import com.googlecode.objectify.annotation.*;

import java.util.List;

@Embed
public class Question {

    @Ignore
    private Exam exam;

    private @Index String number = "0";
    private @Index List<String> themes;
    private List<String> answers;
    private int validAnswerIndex;
    private String text;

    public Exam getExam() {
        return exam;
    }

    public String getNumber() {
        return number;
    }

    public List<String> getThemes() {
        return themes;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getValidAnswerIndex() {
        return validAnswerIndex;
    }

    public String getText() {
        return text;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public void setValidAnswerIndex(int validAnswerIndex) {
        this.validAnswerIndex = validAnswerIndex;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return exam + ": " + text + ": " + Joiner.on(",").join(themes);
    }
}
