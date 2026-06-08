package src.postSecondary;

import src.application.*;

public class University extends PostSecondary {

    private int annualResearchFunding;
    private int qsRanking;

    public University(int id, String name, int annualResearchFunding, int qsRanking) {
        super(id, name);
        this.annualResearchFunding = annualResearchFunding;
        this.qsRanking = qsRanking;
    }

    public boolean evaluateApplication(Application a) {
        return true;
    }

    @Override
    public String toString() {
        return "Institution Type: University\n" + super.toString();
    }
}
