package com.zephyrusapps.edu.emat.service.rest.domain;

import com.zephyrusapps.edu.emat.service.domain.Question;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class QuestionData implements Serializable {

    private int year;
    private String phase;
    private String group;
    private String number;
    private List<String> themes;
    private List<String> answers;
    private String text;

    public QuestionData() {
    }

    public QuestionData(Question question) {
        this.year = question.getExam().getYear();
        this.phase = question.getExam().getPhase();
        this.group = "unknown";
        this.number = question.getNumber();
        this.themes = new ArrayList<String>(question.getThemes());
        this.answers = new ArrayList<String>(question.getAnswers());
        this.text = question.getText();
    }

    public QuestionData(int year, String phase, String group, String number, List<String> answers, String text) {
        this.year = year;
        this.phase = phase;
        this.group = group;
        this.number = number;
        this.answers = answers;
        this.text = text;
    }

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Question{" +
                "year=" + year +
                ", phase='" + phase + '\'' +
                ", group='" + group + '\'' +
                ", number='" + number + '\'' +
                ", themes=" + themes +
                ", answers=" + answers +
                ", text='" + text + '\'' +
                '}';
    }
}
