package com.nahidislamz.cgpacalculator.ui.main;

public class Credits{
    double credits ,grades,totalCredits,totalPoints;

    public Credits() {
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Credits(double credits, double grades) {
        this.credits = credits;
        this.grades = grades;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    public double getPoints() {
        return getGrades()*getCredits();
    }

    public double getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(double totalCredits) {
        this.totalCredits = totalCredits;
    }
}
