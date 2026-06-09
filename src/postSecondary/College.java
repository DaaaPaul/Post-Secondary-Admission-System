package src.postSecondary;

import src.application.*;
import src.student.*;

public class College extends PostSecondary {

    private boolean offersApprenticeship;
    private double graduateEmploymentRate;

    public boolean isOffersApprenticeship() {
        return offersApprenticeship;
    }

    public double getGraduateEmploymentRate() {
        return graduateEmploymentRate;
    }

    public void setOffersApprenticeship(boolean offersApprenticeship) {
        this.offersApprenticeship = offersApprenticeship;
    }

    public void setGraduateEmploymentRate(double graduateEmploymentRate) {
        this.graduateEmploymentRate = graduateEmploymentRate;
    }

    public College(int id, String name, boolean offersApprenticeship, double graduateEmploymentRate) {
        super(id, name);
        this.offersApprenticeship = offersApprenticeship;
        this.graduateEmploymentRate = graduateEmploymentRate;
    }





    @Override
    public boolean evaluateApplication(Application a) {

        Student student = a.getStudent();
        Program program = a.getProgram();

        for (String course : program.getRequiredCourses()) {
            if (student.searchCourseByCourseCode(course) == null) return false;
        }

        if (student.getAverage() >= program.getRequiredAverage()) {
            a.setStatus("ACCEPTED");
            return true;

        } else {
            a.setStatus("REJECTED");
            return false;
        }

    }






    @Override
    public String toString() {
        return "Institution Type: College\n" + super.toString();
    }
}
