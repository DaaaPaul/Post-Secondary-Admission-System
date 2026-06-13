package src.postSecondary;

import src.application.*;
import src.student.*;

/**
 * College
 * Represents a college institution that tracks apprenticeship offers and employment rates.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class College extends PostSecondary {
    private boolean offersApprenticeship;
    private double graduateEmploymentRate;

    /**
     * Returns a string representation including College-specific details.
     * @return A formatted string of the college's data
     */
    @Override
    public String toString() {
        return super.toString() + offersApprenticeship + '\n' + graduateEmploymentRate;
    }

    /**
     * Checks if the college offers apprenticeship programs.
     * @return true if apprenticeships are offered, false otherwise
     */
    public boolean isOffersApprenticeship() {
        return offersApprenticeship;
    }

    /**
     * Gets the graduate employment rate.
     * @return The graduate employment rate as a double
     */
    public double getGraduateEmploymentRate() {
        return graduateEmploymentRate;
    }

    /**
     * Sets whether the college offers apprenticeships.
     * @param offersApprenticeship the boolean status of apprenticeship offerings
     */
    public void setOffersApprenticeship(boolean offersApprenticeship) {
        this.offersApprenticeship = offersApprenticeship;
    }

    /**
     * Sets the graduate employment rate.
     * @param graduateEmploymentRate the new employment rate
     */
    public void setGraduateEmploymentRate(double graduateEmploymentRate) {
        this.graduateEmploymentRate = graduateEmploymentRate;
    }

    /**
     * Initializes a College.
     * @param id the college ID
     * @param name the college name
     * @param offersApprenticeship boolean indicating if apprenticeships are offered
     * @param graduateEmploymentRate the employment rate of graduates
     */
    public College(int id, String name, boolean offersApprenticeship, double graduateEmploymentRate) {
        super(id, name);
        this.offersApprenticeship = offersApprenticeship;
        this.graduateEmploymentRate = graduateEmploymentRate;
    }

    /**
     * Evaluates a student's application based on required courses and average.
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

        if (student.getAverage() >= program.getRequiredAverage()) {
            a.setStatus("ACCEPTED");
            return true;

        } else {
            a.setStatus("REJECTED");
            return false;
        }

    }
}