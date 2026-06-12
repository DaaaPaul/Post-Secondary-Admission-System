package src.postSecondary;

import src.student.*;
import src.application.*;
import java.util.ArrayList;

public abstract class PostSecondary {
    private int id;
    private String name;

    private ArrayList<Program> programs;
    private ArrayList<Application> applications;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id); sb.append('\n');
        sb.append(name); sb.append('\n');

        return sb.toString();
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostSecondary(int id, String name) {
        this.id = id;
        this.name = name;

        programs = new ArrayList<>();
        applications = new ArrayList<>();
    }

    public void addProgram(Program p) {
        programs.add(p);
        sortProgramsByBenchmark();
    }

    public boolean removeProgram(int id) {
        return programs.remove(searchProgramById(id));
    }

    public Program searchProgramById(int id) {
        for (Program program : programs) {
            if (program.getId() == id) {
                return program;
            }
        }
        return null;
    }

    public Program searchProgramByName(String name) {
        for (Program program : programs) {
            if (program.getName().equals(name)) {
                return program;
            }
        }
        return null;
    }

    public ArrayList<Program> searchProgramsByBenchmark(double avg) {
        ArrayList<Program> ret = new ArrayList<>();
        int i = 0;
        int j = programs.size() - 1;

        while (i <= j) {
            int m = (i + j) / 2;
            if (programs.get(m).getRequiredAverage() == avg) {
                ret.add(programs.get(m));

                i = m - 1;
                while (i >= 0 && programs.get(i).getRequiredAverage() == avg) {
                    ret.add(programs.get(i--));
                }

                j = m + 1;
                while (j < programs.size() && programs.get(j).getRequiredAverage() == avg) {
                    ret.add(programs.get(j++));
                }

                return ret;

            } else if (programs.get(m).getRequiredAverage() > avg) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }

        return ret;
    }

    public void sortProgramsByBenchmark() {
        for (int i = 0; i < programs.size() - 1; i++) {
            int k = i;
            for (int j = i + 1; j < programs.size(); j++) {
                if (programs.get(j).getRequiredAverage() < programs.get(k).getRequiredAverage()) {
                    k = j;
                }
            }
            if (k != i) {
                Program temp = programs.get(i);
                programs.set(i, programs.get(k));
                programs.set(k, temp);
            }
        }
    }

    public void addApplication(Application a) {
        applications.add(a);
    }

    public boolean removeApplication(int id) {
        return applications.removeIf(a -> a.compareId(id) == 0);
    }

    public abstract boolean evaluateApplication(Application a);
}