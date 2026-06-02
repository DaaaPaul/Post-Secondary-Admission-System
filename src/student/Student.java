package src.student;

import java.util.ArrayList;
import src.utility.Course;
import src.utility.Extracurricular;
import src.application.Application;

public abstract class Student {
    private int id;
    private String name;
    private ArrayList<Course> courses;
    private ArrayList<Extracurricular> extracurriculars;
    private ArrayList<Application> applications;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        String info = 
            "id: " + id + '\n' +
            "name: " + name + '\n';

        for(Course course : courses) {
            info += course;
            info += '\n';
        }

        for(Extracurricular extracurricular : extracurriculars) {
            info += extracurricular;
            info += '\n';
        }

        for(Application application : applications) {
            info += application;
            info += '\n';
        }

        return info;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean removeCourses(String courseCode) {
        return courses.removeIf(course -> course.compareCourseCode(courseCode) == 0);
    }

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

    public void addExtracurricular(Extracurricular extracurricular) {
        extracurriculars.add(extracurricular);
    }

    public boolean removeExtracurriculars(String extracurricularName) {
        return extracurriculars.removeIf(ec -> ec.compareName(extracurricularName) == 0);
    }

    public void addApplication(Application application) {
        applications.add(application);
    }

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

    public int getPoints() {
        int points = 0;

        for(Extracurricular extracurricular : extracurriculars) {
            points += extracurricular.getPoints();
        }

        return points;
    }
}
