package src.postSecondary;

import java.util.ArrayList;

/**
 * Program
 * Represents an academic program offered by a post-secondary institution.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class Program {
    public static final int NULL_REQUIRED_POINTS = -1;

    private int id;
    private int institutionId;
    private String name;

    private ArrayList<String> requiredCourses;
    private double requiredAverage;
    private int requiredPoints;

    /**
     * Initializes a Program with specific admission requirements.
     * @param id the program ID
     * @param institutionId the ID of the hosting institution
     * @param name the program name
     * @param requiredAverage the minimum average required for admission
     * @param requiredPoints the minimum merit points required for admission
     */
    public Program(int id, int institutionId, String name, double requiredAverage, int requiredPoints) {
        this.id = id;
        this.institutionId = institutionId;
        this.name = name;

        this.requiredCourses = new ArrayList<>();
        this.requiredAverage = requiredAverage;
        this.requiredPoints = requiredPoints;
    }

    /**
     * Gets the program ID.
     * @return The program ID
     */
    public int getId() { return id; }

    /**
     * Gets the institution ID hosting the program.
     * @return The institution ID
     */
    public int getInstitutionId() { return institutionId; }

    /**
     * Gets the name of the program.
     * @return The program name
     */
    public String getName() { return name; }

    /**
     * Gets the list of required course codes.
     * @return An ArrayList of required course codes
     */
    public ArrayList<String> getRequiredCourses() { return requiredCourses; }

    /**
     * Gets the required admission average.
     * @return The required average
     */
    public double getRequiredAverage() { return requiredAverage; }

    /**
     * Gets the required admission merit points.
     * @return The required points
     */
    public int getRequiredPoints() { return requiredPoints; }

    /**
     * Sets the program ID.
     * @param id the new program ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * Sets the host institution ID.
     * @param institutionId the new institution ID
     */
    public void setInstitutionId(int institutionId) { this.institutionId = institutionId; }

    /**
     * Sets the program name.
     * @param name the new program name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Sets the required admission average.
     * @param requiredAverage the new required average
     */
    public void setRequiredAverage(double requiredAverage) { this.requiredAverage = requiredAverage; }

    /**
     * Sets the required admission merit points.
     * @param requiredPoints the new required points
     */
    public void setRequiredPoints(int requiredPoints) { this.requiredPoints = requiredPoints; }

    /**
     * Adds a required course code to the program's requirements.
     * @param courseCode the course code to add
     */
    public void addRequiredCourse(String courseCode) {
        requiredCourses.add(courseCode);
    }

    /**
     * Removes a required course code from the program's requirements.
     * @param courseCode the course code to remove
     * @return true if removed successfully, false otherwise
     */
    public boolean removeRequiredCourse(String courseCode) {
        return requiredCourses.remove(courseCode);
    }

    /**
     * Returns a string representation of the program.
     * @return A formatted string with program details and requirements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id); sb.append('\n');
        sb.append(name); sb.append('\n');
        sb.append(requiredAverage); sb.append('\n');
        if(requiredPoints != NULL_REQUIRED_POINTS) {
            sb.append(requiredPoints); sb.append('\n');
        }

        for(String courseCode : requiredCourses) {
            sb.append(courseCode); sb.append('\n');
        }

        return sb.toString();
    }
}