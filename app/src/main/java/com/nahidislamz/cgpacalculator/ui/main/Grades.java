package com.nahidislamz.cgpacalculator.ui.main;

import java.io.Serializable;

public class Grades implements Serializable {

    float gpa,credit;

    public Grades(float gpa, float credit) {
        this.gpa = gpa;
        this.credit = credit;
    }

    public Grades() {

    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
}
