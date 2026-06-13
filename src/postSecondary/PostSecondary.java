package src.postSecondary;

import src.application.*;
import java.util.ArrayList;

/**
 * PostSecondary
 * An abstract class representing a generic post-secondary institution holding programs and applications.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public abstract class PostSecondary {
    private int id;
    private String name;

    private ArrayList<Program> programs;
    private ArrayList<Application> applications;

    /**
     * Returns a string representation of the institution.
     * @return A string containing the institution's ID and name
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id); sb.append('\n');
        sb.append(name); sb.append('\n');

        return sb.toString();
    }

    /**
     * Gets the list of programs offered by the institution.
     * @return An ArrayList of programs
     */
    public ArrayList<Program> getPrograms() {
        return programs;
    }

    /**
     * Gets the institution ID.
     * @return The integer institution ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the institution.
     * @return The institution name
     */
    public String getName() { return name; }

    /**
     * Sets the institution name.
     * @param name the new name for the institution
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Initializes a PostSecondary institution.
     * @param id the institution ID
     * @param name the institution name
     */
    public PostSecondary(int id, String name) {
        this.id = id;
        this.name = name;

        programs = new ArrayList<>();
        applications = new ArrayList<>();
    }

    /**
     * Adds a program and re-sorts the program list by benchmark.
     * @param p the program to add
     */
    public void addProgram(Program p) {
        programs.add(p);
        sortProgramsByBenchmark();
    }

    /**
     * Removes a program from the institution by ID.
     * @param id the ID of the program to remove
     * @return true if successfully removed, false otherwise
     */
    public boolean removeProgram(int id) {
        return programs.remove(searchProgramById(id));
    }

    /**
     * Searches for a program by its ID.
     * @param id the program ID to find
     * @return The Program object if found, otherwise null
     */
    public Program searchProgramById(int id) {
        for (Program program : programs) {
            if (program.getId() == id) {
                return program;
            }
        }
        return null;
    }

    /**
     * Searches for a program by its name.
     * @param name the name of the program to find
     * @return The Program object if found, otherwise null
     */
    public Program searchProgramByName(String name) {
        for (Program program : programs) {
            if (program.getName().equals(name)) {
                return program;
            }
        }
        return null;
    }

    /**
     * Searches for programs matching a specific required average using binary search.
     * @param avg the average to search for
     * @return A list of programs requiring the specified average
     */
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

    /**
     * Sorts programs in ascending order based on their required average.
     */
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

    /**
     * Adds an application to the institution's record.
     * @param a the application to add
     */
    public void addApplication(Application a) {
        applications.add(a);
    }

    /**
     * Removes an application by ID.
     * @param id the ID of the application to remove
     * @return true if successfully removed, false otherwise
     */
    public boolean removeApplication(int id) {
        return applications.removeIf(a -> a.compareId(id) == 0);
    }

    /**
     * Abstract method to evaluate an application according to the institution's rules.
     * @param a the application to evaluate
     * @return true if accepted, false if rejected
     */
    public abstract boolean evaluateApplication(Application a);
}