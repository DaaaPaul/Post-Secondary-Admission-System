package src.system;

import java.util.ArrayList;
import java.io.*;
import src.student.Student;
import src.postSecondary.PostSecondary;
import src.application.Application;
import src.postSecondary.University;

public class AdmissionSystem {
    private ArrayList<Student> students;
    private ArrayList<PostSecondary> postSecondarys;
    private ArrayList<Application> applications;

    public AdmissionSystem() {
        loadData();
    }

    public void loadData() {
        applications = getApplications(txtFilesUnder(new File("data/applications")).getFirst());
        
        ArrayList<File> universityData = txtFilesUnder(new File("data/postSecondarys/universities"));
        ArrayList<File> collegeData = txtFilesUnder(new File("data/postSecondarys/colleges"));

        ArrayList<File> groupAData = txtFilesUnder(new File("data/students/groupA"));
        ArrayList<File> groupBData = txtFilesUnder(new File("data/students/groupB"));
    }

    private static ArrayList<Application> getApplications(File data) {
        ArrayList<Application> applications = new ArrayList();
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(data));

            String first;
            while((first = in.readLine()) != null) {
                applications.add(new Application(
                    Integer.parseInt(first),
                    Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()),
                    Integer.parseInt(in.readLine()),
                    in.readLine()
                ));
            }
        } catch (IOException iox) {
            System.err.println("getApplications: IOException");
        }

        return applications;
    }

    private static ArrayList<University> getUniversitys(ArrayList<File> data) {
        ArrayList<University> universitys = new ArrayList();
        BufferedReader in;

        try {
            for(File file : data) {
                in = new BufferedReader(new FileReader(file));
                University university;

                if(file.getName().equals("postSecondary.txt")) {
                    university = new University(

                    );
                } else {

                }
            }


        } catch (IOException iox) {
            System.err.println("getUniversitys: IOException");
        }

        return universitys;
    }

    public void saveData() {

    }

    public void addStudent(Student student) {

    }

    public boolean removeStudent(int id) {

    }

    public void addPostSecondary(PostSecondary postSecondary) {

    }

    public boolean removePostSecondary(int id) {

    }

    public void createApplication() {

    }

    public Student searchStudentById(int id) {

    }

    public Student searchStudentByName(String name) {

    }

    public PostSecondary searchPostSecondaryById(int id) {

    }

    public PostSecondary searchPostSecondaryByName(String name) {

    }

    public Application searchApplicationById(int id) {

    }

    public Application searchApplicationByStudent(int studentId) {

    }

    public Application searchApplicationByProgram(int programId) {

    }

    public void sortStudentsByMerit() {

    }

    private static ArrayList<File> txtFilesUnder(File folder) {
        ArrayList<File> collected = new ArrayList<File>();

        if(folder == null || !folder.exists()) {
            return collected;
        }

        if(folder.isFile() && folder.getName().endsWith(".txt")) {
            collected.add(folder);
            return collected;
        }

        if(folder.isDirectory()) {
            File files[] = folder.listFiles();

            if(files != null) {
                for(File file : files) {
                    collected.addAll(txtFilesUnder(file));
                }
            }
        }

        return collected;
    }
}
