package src.student;

import java.util.ArrayList;
import src.utility.Course;
import src.utility.Extracurricular;
import src.application.Application;

/**
 * Student
 * An abstract class representing a generic student holding courses, extracurriculars, and applications.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public abstract class Student {

    private int id;
    private String name;
    private ArrayList<Course> courses;
    private ArrayList<Extracurricular> extracurriculars;
    private ArrayList<Application> applications;

    /**
     * Gets the student ID.
     * @return The student's ID number
     */
    public int getId() { return id; }

    /**
     * Gets the student's name.
     * @return The student's name
     */
    public String getName() { return name; }

    /**
     * Initializes a generic Student with an ID and name.
     * @param id the student ID
     * @param name the student's name
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
        this.extracurriculars = new ArrayList<>();
        this.applications = new ArrayList<>();
    }

    /**
     * Returns a string representation of the student profile.
     * @return A formatted string containing the student's ID, name, courses, and extracurriculars
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id); sb.append('\n');
        sb.append(name); sb.append('\n');

        for(Course course : courses) {
            sb.append(course); sb.append('\n');
        }

        for(Extracurricular extracurricular : extracurriculars) {
            sb.append(extracurricular); sb.append('\n');
        }

        return sb.toString();
    }

    /**
     * Adds a course to the student's record.
     * @param course the course to add
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Removes courses matching the given code.
     * @param courseCode the course code to remove
     * @return true if the course was removed, false otherwise
     */
    public boolean removeCourses(String courseCode) {
        return courses.removeIf(course -> course.compareCourseCode(courseCode) == 0);
    }

    /**
     * Updates the midterm grade for a specific course.
     * @param courseCode the course code to find
     * @param newMidterm the new midterm grade
     * @return true if the course was found and updated, false otherwise
     */
    public boolean updateCourseMidterm(String courseCode, int newMidterm) {
        Course foundCourse = null;

        for(int i = 0; i < courses.size() && foundCourse == null; i++) {
            Course course = courses.get(i);
            if(course.compareCourseCode(courseCode) == 0) {
                foundCourse = course;
            }
        }

        boolean found = foundCourse != null;
        if(found) {
            foundCourse.setMidterm(newMidterm);
        }

        return found;
    }

    /**
     * Updates the final grade for a specific course.
     * @param courseCode the course code to find
     * @param newFinal the new final grade
     * @return true if the course was found and updated, false otherwise
     */
    public boolean updateCourseFinal(String courseCode, int newFinal) {
        Course foundCourse = null;

        for(int i = 0; i < courses.size() && foundCourse == null; i++) {
            Course course = courses.get(i);
            if(course.compareCourseCode(courseCode) == 0) {
                foundCourse = course;
            }
        }

        boolean found = foundCourse != null;
        if(found) {
            foundCourse.setFinal(newFinal);
        }

        return found;
    }

    /**
     * Searches for a course by its code.
     * @param code the course code to search for
     * @return The Course object if found, otherwise null
     */
    public Course searchCourseByCourseCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(code)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Adds an extracurricular activity to the student's profile.
     * @param extracurricular the activity to add
     */
    public void addExtracurricular(Extracurricular extracurricular) {
        extracurriculars.add(extracurricular);
    }

    /**
     * Removes an extracurricular activity by name.
     * @param extracurricularName the name of the activity to remove
     * @return true if the activity was removed, false otherwise
     */
    public boolean removeExtracurriculars(String extracurricularName) {
        return extracurriculars.removeIf(ec -> ec.compareName(extracurricularName) == 0);
    }

    /**
     * Adds an application to the student's profile.
     * @param application the application to add
     */
    public void addApplication(Application application) {
        applications.add(application);
    }

    /**
     * Removes an application by its ID.
     * @param id the ID of the application to remove
     * @return true if the application was removed, false otherwise
     */
    public boolean removeApplication(int id) {
        return applications.removeIf(a -> a.compareId(id) == 0);
    }

    /**
     * Searches for an application by its ID.
     * @param id the application ID to search for
     * @return The Application object if found, otherwise null
     */
    public Application searchApplicationById(int id) {
        for(Application a : applications) {
            if(a.compareId(id) == 0) {
                return a;
            }
        }
        return null;
    }

    /**
     * Calculates the student's average based on recent grades in their courses.
     * @return The double value of the student's average, or -1.0 if no grades are present
     */
    public double getAverage() {
        int gradeSum = 0;
        int availableGrades = 0;

        for(Course course : courses) {
            int grade = course.getRecentGrade();

            if(grade != Course.NULL_GRADE) {
                gradeSum += grade;
                availableGrades++;
            }
        }

        if(gradeSum == 0 && availableGrades == 0) {
            return -1.0;
        } else {
            return (double) gradeSum / availableGrades;
        }
    }

    /**
     * Calculates the total merit points from the student's extracurriculars.
     * @return The total integer points
     */
    public int getPoints() {
        int points = 0;

        for(Extracurricular extracurricular : extracurriculars) {
            points += extracurricular.getPoints();
        }

        return points;
    }

    /**
     * Utility method to insert a string into a specific newline format.
     * @param original the original string
     * @param insert the string to insert
     * @return The formatted string with the insertion applied
     */
    static protected String insertToString(String original, String insert) {
        int first = original.indexOf('\n');
        if(first == -1) {
            return original;
        }

        int second = original.indexOf('\n', first + 1);
        if(second == -1) {
            return original;
        }

        int insertIndex = second + 1;
        
        return original.substring(0, insertIndex) + insert + original.substring(insertIndex);
    }
}