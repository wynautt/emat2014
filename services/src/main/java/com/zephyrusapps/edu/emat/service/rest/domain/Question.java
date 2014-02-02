package com.zephyrusapps.edu.emat.service.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class Question implements Serializable {

    private int year;
    private String phase;
    private String group;
    private String number;
    private List<String> themes;
    private List<String> answers;
    private String text;

    public Question() {

    }

    public Question(int year, String phase, String group, String number, List<String> answers, String text) {
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
