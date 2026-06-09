package src.system;

import java.util.ArrayList;
import java.io.*;
import src.student.Student;
import src.postSecondary.PostSecondary;
import src.application.Application;

public class AdmissionSystem {
    private ArrayList<Student> students;
    private ArrayList<PostSecondary> postSecondarys;
    private ArrayList<Application> applications;

    public AdmissionSystem() {
        students = new ArrayList();
        postSecondarys = new ArrayList();
        applications = new ArrayList();
    }

    public void loadData() {
        ArrayList<File> applicationData = txtFilesUnder(new File("data/applications"));
        
        ArrayList<File> universityData = txtFilesUnder(new File("data/postSecondarys/universities"));
        ArrayList<File> collegeData = txtFilesUnder(new File("data/postSecondarys/colleges"));

        ArrayList<File> groupAData = txtFilesUnder(new File("data/students/groupA"));
        ArrayList<File> groupBData = txtFilesUnder(new File("data/students/groupB"));
    }






    /*
    private static ArrayList<Application> retrieveApplications(File data) {
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
            System.err.println("retrieveApplications: IOException");
        }

        return applications;
    }
     */






    public void saveData() {

    }

    void addStudent(Student student) {

    }

    boolean removeStudent(int id) {

    }

    void addPostSecondary(PostSecondary postSecondary) {

    }

    boolean removePostSecondary(int id) {

    }

    void createApplication() {

    }

    Student searchStudentById(int id) {

    }

    Student searchStudentByName(String name) {

    }

    PostSecondary searchPostSecondaryById(int id) {

    }

    PostSecondary searchPostSecondaryByName(String name) {

    }

    Application searchApplicationById(int id) {

    }

    Application searchApplicationByStudent(int studentId) {

    }

    Application searchApplicationByProgram(int programId) {

    }

    void sortStudentsByMerit() {

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
