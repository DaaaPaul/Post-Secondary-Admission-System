package src.postSecondary;

import src.application.*;
import src.student.Student;

public class University extends PostSecondary {

    private int annualResearchFunding;
    private int qsRanking;

    public University(int id, String name, int annualResearchFunding, int qsRanking) {
        super(id, name);
        this.annualResearchFunding = annualResearchFunding;
        this.qsRanking = qsRanking;
    }

    @Override
    public boolean evaluateApplication(Application a) {

        Student student = a.getStudent();
        Program program = a.getProgram();

        for (String course : program.getRequiredCourses()) {
            if (student.searchCourseByCourseCode(course) == null) return false;
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

    @Override
    public String toString() {
        return "Institution Type: University\n" + super.toString();
    }
}
