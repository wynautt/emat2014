package com.zephyrusapps.edu.emat.service.rest.domain;

import com.zephyrusapps.edu.emat.service.domain.Exam;

public class ExamData {

    String course;
    int year;
    String phase;

    public ExamData(Exam exam) {
        this.course = exam.getCourse();
        this.year = exam.getYear();
        this.phase = exam.getPhase();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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
}
