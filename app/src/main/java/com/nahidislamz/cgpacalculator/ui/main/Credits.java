package com.nahidislamz.cgpacalculator.ui.main;

import java.io.Serializable;

public class Credits implements Serializable {
    float credits,grades,totalCredits,totalPoints;

    public Credits() {
    }

    public float getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(float totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Credits(float credits, float grades) {
        this.credits = credits;
        this.grades = grades;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public float getGrades() {
        return grades;
    }

    public void setGrades(float grades) {
        this.grades = grades;
    }

    public float getPoints() {
        return getGrades()*getCredits();
    }

    public float getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(float totalCredits) {
        this.totalCredits = totalCredits;
    }
}
