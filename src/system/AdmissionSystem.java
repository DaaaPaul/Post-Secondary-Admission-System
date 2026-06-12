package src.system;

import java.time.LocalDate;
import java.util.*;
import java.io.*;

import src.student.*;
import src.postSecondary.PostSecondary;
import src.application.Application;
import src.postSecondary.University;

public class AdmissionSystem {
    private ArrayList<Student> students;
    private ArrayList<PostSecondary> institutions;
    private ArrayList<Application> applications;

    public AdmissionSystem() {
        loadData();
    }

    public void loadData() {
        applications = getApplications(txtFilesUnder(new File("data/applications")).get(0));
        
        ArrayList<File> universityData = txtFilesUnder(new File("data/institution/universities"));
        ArrayList<File> collegeData = txtFilesUnder(new File("data/institution/colleges"));

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

    public void addStudent() {
        Scanner in = new Scanner(System.in);

        Student student;
        boolean finished = false;

        System.out.print("Enter Student ID: "); int id = in.nextInt();
        System.out.print("Enter Student Name: "); String name = in.nextLine();

        while (!finished) {
            System.out.print("Enter Student Type: ");
            String type = in.nextLine();

            if (type.equals("A")) {
                System.out.println("Enter Student Education Number: ");
                int edu = in.nextInt();

                student = new GroupA(id, name, edu);
                students.add(student);
                finished = true;

            } else if (type.equals("B")) {
                System.out.println("Enter High School Name: ");
                String hs = in.nextLine();

                System.out.println("Enter birth year: "); int year = in.nextInt();
                System.out.println("Enter birth month: "); int month = in.nextInt();
                System.out.println("Enter birth day: "); int day = in.nextInt();
                LocalDate date = LocalDate.of(year, month, day);

                student = new GroupB(id, name, hs, date);
                students.add(student);
                finished = true;

            } else {
                System.out.println("Invalid input. Please retry.");
            }
        }
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
