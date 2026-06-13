package src.postSecondary;

import src.application.*;
import src.student.Student;

/**
 * University
 * Represents a university institution with research funding and QS rankings.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class University extends PostSecondary {
    private int annualResearchFunding;
    private int qsRanking;

    /**
     * Initializes a University with research data and rankings.
     * @param id the university ID
     * @param name the university name
     * @param annualResearchFunding the amount of annual research funding
     * @param qsRanking the global QS ranking
     */
    public University(int id, String name, int annualResearchFunding, int qsRanking) {
        super(id, name);
        this.annualResearchFunding = annualResearchFunding;
        this.qsRanking = qsRanking;
    }

    /**
     * Returns a string representation including University-specific details.
     * @return A formatted string of the university's data
     */
    @Override
    public String toString() {
        return super.toString() + annualResearchFunding + '\n' + qsRanking;
    }

    /**
     * Evaluates a student's application based on required courses, average, and points.
     * @param a the application to evaluate
     * @return true if the application is accepted, false if rejected
     */
    @Override
    public boolean evaluateApplication(Application a) {

        Student student = a.getStudent();
        Program program = a.getProgram();

        for (String course : program.getRequiredCourses()) {
            if (student.searchCourseByCourseCode(course) == null) {
                a.setStatus("REJECTED");
                return false;
            }
        }

        if (
                student.getAverage() >= program.getRequiredAverage() &&
                student.getPoints() >= program.getRequiredPoints()
        ) {
            a.setStatus("ACCEPTED");
            return true;

        } else {
            a.setStatus("REJECTED");
            return false;
        }

    }
}