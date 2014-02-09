package com.zephyrusapps.edu.emat.service.domain;

import com.googlecode.objectify.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Cache
public class Exam {

    private @Id String id;
    private @Index String course;
    private @Index int year;
    private @Index String phase;

    @EmbedMap
    private Map<String, Question> questions = new HashMap<String, Question>();

    public static String genId(String course, int year, String phase) {
        return course + "." + year + "." + phase;
    }

    public Exam init() {
        this.id = genId(course, year, phase);
        return this;
    }

    public Exam addQuestion(Question question) {
        questions.put(question.getNumber(), question);
        question.setExam(this);
        return this;
    }

    public boolean hasQuestion(String number) {
        return questions.containsKey(number);
    }

    public Question getQuestion(String number) {
        //Hack: objectify does not support? bidirectional relations with embedded classes.
        //To mitigate that we need to set one of part of the relation manually.
        //Here we are setting the Exam entity whenever we retrieve one of the Exam's questions.

        Question q = questions.get(number);
        if(q != null) {
            q.setExam(this);
        }

        return q;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public void setQuestions(Map<String, Question> questions) {
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public String getPhase() {
        return phase;
    }

    public Map<String, Question> getQuestions() {
        for(Question q: questions.values()) {
            q.setExam(this);
        }
        return questions;
    }

    public List<Question> getQuestionList() {
        for(Question q: questions.values()) {
            q.setExam(this);
        }
        return new ArrayList<Question>(questions.values());
    }
}
