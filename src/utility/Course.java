package src.utility;

public class Course {
    public static final int NULL_GRADE = -1;

    private final String courseCode;
    private int midtermGrade;
    private int finalGrade;

    public Course(String courseCode, int midtermGrade, int finalGrade) {
        this.courseCode = courseCode;
        this.midtermGrade = midtermGrade;
        this.finalGrade = finalGrade;
    }

    public int getRecentGrade() {
        int recentGrade = NULL_GRADE;

        if(finalGrade != NULL_GRADE) {
            recentGrade = finalGrade;
        } else if(midtermGrade != NULL_GRADE) {
            recentGrade = midtermGrade;
        }

        return recentGrade;
    }

    public void setMidterm(int midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public void setFinal(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public int compareCourseCode(String courseCode) {
        return this.courseCode.compareTo(courseCode);
    }

    @Override
    public String toString() {
        return 
            courseCode + '\n' +
            "midterm: " + midtermGrade + '\n' +
            "final: " + finalGrade;
    }
}
