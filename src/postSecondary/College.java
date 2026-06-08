package src.postSecondary;

import src.application.*;

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

    public boolean evaluateApplication(Application a) {
        return true;
    }

    @Override
    public String toString() {
        return "Institution Type: College\n" + super.toString();
    }
}
