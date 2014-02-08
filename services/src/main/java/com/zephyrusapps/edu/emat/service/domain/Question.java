package com.zephyrusapps.edu.emat.service.domain;

import com.google.common.base.Joiner;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Arrays;
import java.util.List;

@Entity
@Cache
public class Question {

    public static enum Phase {
        Phase1,
        Phase2,
        Int1,
        Int2,
        Int3
    }

    public static enum Theme {
        Probabilities,
        Functions,
        Limits;

        private String text;

        Theme() {
            this.text = "";
        }

        Theme(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static Theme fromString(String text) {
            if (text != null) {
                for (Theme theme : Theme.values()) {
                    if (text.equalsIgnoreCase(theme.text)) {
                        return theme;
                    }
                }
            }
            throw new IllegalArgumentException(String.format("No enum const class %s.%s", Theme.class, text));
        }

    }


    private @Id String id;

    private @Index int year = -1;
    private @Index Phase phase = Phase.Phase1;
    private @Index String number = "-1";
    private List<Theme> themes;
    private List<String> answers;
    private int validAnswerIndex;
    private String text;

    public Question() {
    }

    private void init() {
        id = genId(year, phase, number);
    }

    public void setYear(int year) {
        this.year = year;
        init();
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
        init();
    }

    public void setNumber(String number) {
        this.number = number;
        init();
    }

    public void setThemes(List<Theme> themes) {
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

    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public Phase getPhase() {
        return phase;
    }

    public String getNumber() {
        return number;
    }

    public List<Theme> getThemes() {
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

    public static String genId(int year, Phase phase, String number) {
        return year + "." + phase.toString() + "." + number;
    }

    @Override
    public String toString() {
        return id + ": " + text + ": " + Joiner.on(",").join(themes);
    }
}
