package src.postSecondary;

import src.student.*;
import src.application.*;
import java.util.ArrayList;

public abstract class PostSecondary {

    private int id;
    private String name;

    private ArrayList<Program> programs;
    private ArrayList<Student> students;
    private ArrayList<Application> applications;

    public void setName(String name) {
        this.name = name;
    }

    public PostSecondary(int id, String name) {
        this.id = id;
        this.name = name;

        programs = new ArrayList<Program>();
        students = new ArrayList<Student>();
        applications = new ArrayList<Application>();
    }

    public void addProgram(Program p) {
        programs.add(p);
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
}
