package src.application;

import src.student.*;
import src.postSecondary.*;

/**
 * Application
 * Represents a student's application to a post-secondary program.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class Application {
    private int id;
    private Student student;
    private PostSecondary institution;
    private Program program;
    private String status;

    /**
     * Gets the student associated with the application.
     * @return The student object
     */
    public Student getStudent() { return student; }

    /**
     * Gets the institution associated with the application.
     * @return The institution object
     */
    public PostSecondary getInstitution() { return institution; }

    /**
     * Gets the program applied to.
     * @return The program object
     */
    public Program getProgram() { return program; }

    /**
     * Initializes an Application with all required relationships.
     * @param id the application ID
     * @param student the student applying
     * @param institution the institution receiving the application
     * @param program the specific program being applied to
     * @param status the current status of the application
     */
    public Application(int id, Student student, PostSecondary institution, Program program, String status) {
        this.id = id;
        this.student = student;
        this.institution = institution;
        this.program = program;
        this.status = status;
    }

    /**
     * Gets the application ID.
     * @return The integer application ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new status for the application.
     * @param newStatus the updated status string
     */
    public void setStatus(String newStatus) {
        status = newStatus;
    }

    /**
     * Gets the current status of the application.
     * @return The application status string
     */
    public String getStatus() {
        return status;
    }

    /**
     * Compares this application's ID to a provided integer ID.
     * @param id the integer ID to compare against
     * @return 0 if equal, -1 if less, 1 if greater
     */
    public int compareId(int id) {
        return Integer.compare(this.id, id);
    }

    /**
     * Returns a string representation of the application details.
     * @return A formatted string of the application IDs and status
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id); sb.append('\n');
        sb.append(student.getId()); sb.append('\n');
        sb.append(institution.getId()); sb.append('\n');
        sb.append(program.getId()); sb.append('\n');
        sb.append(status); sb.append('\n');

        return sb.toString();
    }
}