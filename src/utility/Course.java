package src.utility;

/**
 * Course
 * Represents an academic course with a code and corresponding midterm and final grades.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class Course {
    public static final int NULL_GRADE = -1;

    private final String courseCode;
    private int midtermGrade;
    private int finalGrade;

    /**
     * Gets the course code.
     * @return The string representation of the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Initializes a Course with a code and its midterm/final grades.
     * @param courseCode the identifying code for the course
     * @param midtermGrade the midterm grade received
     * @param finalGrade the final grade received
     */
    public Course(String courseCode, int midtermGrade, int finalGrade) {
        this.courseCode = courseCode;
        this.midtermGrade = midtermGrade;
        this.finalGrade = finalGrade;
    }

    /**
     * Retrieves the most recent grade, prioritizing the final grade over the midterm.
     * @return The most recent grade, or NULL_GRADE if neither exists
     */
    public int getRecentGrade() {
        int recentGrade = NULL_GRADE;

        if(finalGrade != NULL_GRADE) {
            recentGrade = finalGrade;
        } else if(midtermGrade != NULL_GRADE) {
            recentGrade = midtermGrade;
        }

        return recentGrade;
    }

    /**
     * Updates the midterm grade.
     * @param midtermGrade the new midterm grade
     */
    public void setMidterm(int midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    /**
     * Updates the final grade.
     * @param finalGrade the new final grade
     */
    public void setFinal(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    /**
     * Compares this course's code to a provided string.
     * @param courseCode the string course code to compare against
     * @return An integer representing the lexicographical comparison result
     */
    public int compareCourseCode(String courseCode) {
        return this.courseCode.compareTo(courseCode);
    }

    /**
     * Returns a string representation of the course and its grades.
     * @return A formatted string of the course data
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(courseCode); 
        if(midtermGrade != NULL_GRADE) {
            sb.append(' '); sb.append(midtermGrade);
        }
        if(finalGrade != NULL_GRADE) {
            sb.append(' '); sb.append(finalGrade);
        }

        return sb.toString();
    }
}