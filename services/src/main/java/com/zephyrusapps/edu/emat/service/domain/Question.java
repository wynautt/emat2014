package com.zephyrusapps.edu.emat.service.domain;

import com.google.common.base.Joiner;

import java.util.List;

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

    private String id;
    private int year;
    private Phase phase;
    private String group;
    private String number;
    private List<Theme> themes;
    private List<String> answers;
    private int validAnswerIndex;
    private String text;

    public static String genId(int year, String phase, String number) {
        return year + "." + Phase.valueOf(phase) + "." + number;
    }

    @Override
    public String toString() {
        return id + ": " + text + ": " + Joiner.on(",").join(themes);
    }
}
