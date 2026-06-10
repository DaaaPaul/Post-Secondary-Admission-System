package src.system;

import java.util.ArrayList;
import java.io.*;
import src.postSecondary.Program;
import src.student.Student;
import src.student.GroupA;
import src.student.GroupB;
import src.postSecondary.PostSecondary;
import src.postSecondary.University;
import src.postSecondary.College;
import src.application.Application;

public class AdmissionSystem {
    private ArrayList<Student> students;
    private ArrayList<PostSecondary> postSecondarys;
    private ArrayList<Application> applications;

    public AdmissionSystem() {
        ArrayList<File> universityData = txtFilesUnder(new File("data/postSecondarys/universities"));
        ArrayList<File> collegeData = txtFilesUnder(new File("data/postSecondarys/colleges"));
        postSecondarys.addAll(getUniversitys(universityData));
        postSecondarys.addAll(getColleges(collegeData));

        ArrayList<File> groupAData = txtFilesUnder(new File("data/students/groupA"));
        ArrayList<File> groupBData = txtFilesUnder(new File("data/students/groupB"));
        students.addAll(getGroupAs(groupAData));
        students.addAll(getGroupBs(groupBData));

        ArrayList<File> applicationData = txtFilesUnder(new File("data/applications"));
    }

    private static ArrayList<GroupA> getGroupAs(ArrayList<File> data) { 

    }

    private static ArrayList<GroupB> getGroupBs(ArrayList<File> data) { 

    }

    private static ArrayList<College> getColleges(ArrayList<File> data) {
        ArrayList<College> colleges = new ArrayList();
        BufferedReader fin;

        try {
            int i = 0;

            while(i < data.size()) {
                fin = new BufferedReader(new FileReader(data.get(i)));

                College college = new College(
                    Integer.parseInt(fin.readLine()),
                    fin.readLine(),
                    Boolean.parseBoolean(fin.readLine()),
                    Double.parseDouble(fin.readLine())
                );

                while(!data.get(++i).getName().equals("college.txt")) {
                    fin = new BufferedReader(new FileReader(data.get(i)));

                    Program program = new Program(
                        Integer.parseInt(fin.readLine()),
                        college.getId(),
                        fin.readLine(),
                        Double.parseDouble(fin.readLine()),
                        Program.NULL_REQUIRED_POINTS
                    );

                    String line;
                    while((line = fin.readLine()) != null) {
                        program.addRequiredCourse(line);
                    }

                    college.addProgram(program);
                }

                colleges.add(college);
            }
        } catch (IOException iox) {
            System.err.println("getColleges: IOException: " + iox);
        }

        return colleges;
    }

    private static ArrayList<University> getUniversitys(ArrayList<File> data) {
        ArrayList<University> universities = new ArrayList();
        BufferedReader fin;

        try {
            int i = 0;

            while(i < data.size()) {
                fin = new BufferedReader(new FileReader(data.get(i)));

                University university = new University(
                    Integer.parseInt(fin.readLine()),
                    fin.readLine(),
                    Integer.parseInt(fin.readLine()),
                    Integer.parseInt(fin.readLine())
                );

                while(!data.get(++i).getName().equals("university.txt")) {
                    fin = new BufferedReader(new FileReader(data.get(i)));

                    Program program = new Program(
                        Integer.parseInt(fin.readLine()),
                        university.getId(),
                        fin.readLine(),
                        Double.parseDouble(fin.readLine()),
                        Integer.parseInt(fin.readLine())
                    );

                    String line;
                    while((line = fin.readLine()) != null) {
                        program.addRequiredCourse(line);
                    }

                    university.addProgram(program);
                }

                universities.add(university);
            }
        } catch (IOException iox) {
            System.err.println("getUniversitys: IOException: " + iox);
        }

        return universities;
    }

    private static ArrayList<File> getApplications(ArrayList<File> applicationData) {
        ArrayList<Application> applications = new ArrayList();
        BufferedReader fin;

        try {
            for(File file : applicationData) {
                fin = new BufferedReader(new FileReader(file));


            }
        } catch (IOException iox) {
            System.err.println("getApplications: IOException: " + iox);
        }
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
