package com.zephyrusapps.edu.emat.service.operations;

public class ViewExamOp {
    String course;
    int year = -1;
    String phase;

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

    public boolean hasCourse() {
        return course != null && !course.isEmpty();
    }

    public boolean hasYear() {
        return year > 0;
    }

    public boolean hasPhase() {
        return phase != null && !phase.isEmpty();
    }
}
