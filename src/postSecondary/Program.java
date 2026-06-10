package src.postSecondary;

import java.util.ArrayList;

public class Program {
    public static final int NULL_REQUIRED_POINTS = -1;

    private int id;
    private int institutionId;
    private String name;

    private ArrayList<String> requiredCourses;
    private double requiredAverage;
    private int requiredPoints;

    public Program(int id, int institutionId, String name, double requiredAverage, int requiredPoints) {
        this.id = id;
        this.institutionId = institutionId;
        this.name = name;

        this.requiredCourses = new ArrayList();
        this.requiredAverage = requiredAverage;
        this.requiredPoints = requiredPoints;
    }

    public int getId() {
        return id;
    }
    public int getInstitutionId() {
        return institutionId;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getRequiredCourses() {
        return requiredCourses;
    }
    public double getRequiredAverage() {
        return requiredAverage;
    }
    public int getRequiredPoints() {
        return requiredPoints;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRequiredAverage(double requiredAverage) {
        this.requiredAverage = requiredAverage;
    }
    public void setRequiredPoints(int requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public void addRequiredCourse(String courseCode) {
        requiredCourses.add(courseCode);
    }

    public boolean removeRequiredCourse(String courseCode) {
        return requiredCourses.remove(courseCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Program ID: "); sb.append(id); sb.append("\n");
        sb.append("Institution ID: "); sb.append(institutionId); sb.append("\n");
        sb.append("Program Name: "); sb.append(name); sb.append("\n");

        sb.append("Required Courses: ");
        for (String courseCode : requiredCourses) {
            sb.append(courseCode); sb.append(", ");
        } sb.append("\n");

        sb.append("Required Average: "); sb.append(requiredAverage); sb.append("\n");
        sb.append("Required Points: "); sb.append(requiredPoints); sb.append("\n");

        return sb.toString();
    }
}
