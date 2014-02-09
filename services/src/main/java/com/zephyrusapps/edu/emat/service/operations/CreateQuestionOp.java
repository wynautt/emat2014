package com.zephyrusapps.edu.emat.service.operations;

import java.util.List;

public class CreateQuestionOp extends CreateExamOp {
    String number;
    String text;
    List<String> themes;
    List<String> answers;
    int validAnswerIndex;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getNumber() {
        //Hack due to lack of support for keys with '.' by objectify
        return number.replace(".",":");
    }

    public String getNumberUntouched() {
        return number;
    }

    public String getText() {
        return text;
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
}
