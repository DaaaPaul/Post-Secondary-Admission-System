package src.system;

import java.time.LocalDate;
import java.util.*;
import java.io.*;


import src.application.*;
import src.postSecondary.*;
import src.student.*;
import src.utility.*;

public class AdmissionSystem {
    private ArrayList<Student> students;
    private ArrayList<PostSecondary> institutions;
    private ArrayList<Application> applications;

    public AdmissionSystem() {
        ArrayList<File> universityData = txtFilesUnder(new File("data/postSecondary/universities"));
        ArrayList<File> collegeData = txtFilesUnder(new File("data/postSecondary/colleges"));
        institutions.addAll(getUniversities(universityData));
        institutions.addAll(getColleges(collegeData));

        ArrayList<File> groupAData = txtFilesUnder(new File("data/students/groupA"));
        ArrayList<File> groupBData = txtFilesUnder(new File("data/students/groupB"));
        students.addAll(getGroupAs(groupAData));
        students.addAll(getGroupBs(groupBData));

        ArrayList<File> applicationData = txtFilesUnder(new File("data/applications"));
        storeApplications(applicationData);
    }

    public void saveData() {
        saveStudentData();
        saveInstitutionData();
        saveApplicationData();
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

    boolean removeStudent() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(in.nextInt());

        boolean success = students.remove(student);
        if (success) {
            System.out.println("Student successfully removed.");
        } else {
            System.out.println("Student not found in system.");
        }
    }

    public void addCourseToStudent() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(in.nextInt());

        if (student == null) {
            System.out.println("Student not found in system.");

        } else {
            String input;

            System.out.println("Enter course code."); input = in.nextLine();
            String course = input;

            System.out.println("Enter midterm grade. Enter 'x' if non-applicable."); input = in.nextLine();
            int midtermGrade = !input.equals("x") ? Integer.parseInt(input) : -1;

            System.out.println("Enter final grade. Enter 'x' if non-applicable."); input = in.nextLine();
            int finalGrade = !input.equals("x") ? Integer.parseInt(input) : -1;

            student.addCourse(new Course(course, midtermGrade, finalGrade));
            System.out.println("Course added successfully.");
        }
    }

    public void removeCourseFromStudent() {

    }


    void addPostSecondary(PostSecondary postSecondary) {

    }

    boolean removePostSecondary(int id) {

    }

    void createApplication() {

    }

    public Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
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

    private static ArrayList<University> getUniversities(ArrayList<File> data) {
        ArrayList<University> universities = new ArrayList<>();
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
            System.err.println("getUniversities: IOException: " + iox);
        }

        return universities;
    }

    private static ArrayList<College> getColleges(ArrayList<File> data) {
        ArrayList<College> colleges = new ArrayList<>();
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

    private static ArrayList<GroupA> getGroupAs(ArrayList<File> data) {
        ArrayList<GroupA> groupAs = new ArrayList<>();
        BufferedReader fin;

        try {
            for(File file : data) {
                fin = new BufferedReader(new FileReader(file));

                GroupA groupA = new GroupA(
                    Integer.parseInt(fin.readLine()),
                    fin.readLine(),
                    Integer.parseInt(fin.readLine())
                );

                addCoursesAndExtracurriculars(groupA, fin);

                groupAs.add(groupA);
            }
        } catch (IOException iox) {
            System.err.println("getGroupAs: IOException: " + iox);
        }

        return groupAs;
    }

    private static ArrayList<GroupB> getGroupBs(ArrayList<File> data) {
        ArrayList<GroupB> groupBs = new ArrayList<>();
        BufferedReader fin;

        try {
            for(File file : data) {
                fin = new BufferedReader(new FileReader(file));

                GroupB groupB = new GroupB(
                    Integer.parseInt(fin.readLine()),
                    fin.readLine(),
                    fin.readLine(),
                    LocalDate.parse(fin.readLine())
                );

                addCoursesAndExtracurriculars(groupB, fin);

                groupBs.add(groupB);
            }
        } catch (IOException iox) {
            System.err.println("getGroupBs: IOException: " + iox);
        }

        return groupBs;
    }

    private static void addCoursesAndExtracurriculars(Student student, BufferedReader fin) {
        try {
            String line;
            while((line = fin.readLine()) != null) {
                String[] items = line.split(" ");

                if(Character.isLetter(line.charAt(0))) {
                    Course course = null;

                    switch(items.length) {
                        case 1:
                            course = new Course(items[0], Course.NULL_GRADE, Course.NULL_GRADE);
                            break;
                        case 2:
                            course = new Course(items[0], Integer.parseInt(items[1]), Course.NULL_GRADE);
                            break;
                        case 3:
                            course = new Course(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2]));
                            break;
                        default:
                            System.err.println("addCoursesAndExtracurriculars: invalid string items on line");
                            break;
                    }

                    student.addCourse(course);
                } else if(Character.isDigit(line.charAt(0))) {
                    Extracurricular ec = null;

                    switch(items.length) {
                        case 2:
                            ec = new Extracurricular(items[0], LocalDate.parse(items[1]), Extracurricular.NULL_DATE);
                            break;
                        case 3:
                            ec = new Extracurricular(items[0], LocalDate.parse(items[1]), LocalDate.parse(items[2]));
                            break;
                        default:
                            System.err.println("addCoursesAndExtracurriculars: invalid string items on line");
                            break;
                    }

                    student.addExtracurricular(ec);
                } else {
                    System.err.println("addCoursesAndExtracurriculars: character is not letter nor digit");
                }
            }
        } catch (IOException iox) {
            System.err.println("addCoursesAndExtracurriculars: IOException: " + iox);
        }
    }

    private void storeApplications(ArrayList<File> applicationData) {
        ArrayList<Application> applications = new ArrayList<>();
        BufferedReader fin;

        try {
            for(File file : applicationData) {
                fin = new BufferedReader(new FileReader(file));

                int id = Integer.parseInt(fin.readLine());
                Student student = searchStudentById(Integer.parseInt(fin.readLine()));
                PostSecondary institution = searchPostSecondaryById(Integer.parseInt(fin.readLine()));
                Program program = institution.searchProgramById(Integer.parseInt(fin.readLine()));
                String status = fin.readLine();

                applications.add(new Application(id, student, institution, program, status));
            }
        } catch (IOException iox) {
            System.err.println("getApplications: IOException: " + iox);
        }

        this.applications = applications;
    }

    private void saveStudentData() {
        BufferedWriter fout;

        try {
            for(Student student : students) {
                if(student instanceof GroupA groupA) {
                    fout = new BufferedWriter(new FileWriter("data/students/groupA/" + student.getId() + ".txt"));
                    fout.write(groupA.toString());
                } else if(student instanceof GroupB groupB) {
                    fout = new BufferedWriter(new FileWriter("data/students/groupB/" + student.getId() + ".txt"));
                    fout.write(groupB.toString());
                }
            }
        } catch (IOException iox) {
            System.err.println("saveStudentData: IOException: " + iox);
        }
    }

    private void saveInstitutionData() {
        BufferedWriter fout;

        try {
            for(PostSecondary institution : institutions) {
                if(institution instanceof University university) {
                    fout = new BufferedWriter(new FileWriter("data/postSecondarys/universities/" + university.getId() + "/university.txt"));
                    fout.write(university.toString());

                    for(Program program : university.getPrograms()) {
                        fout = new BufferedWriter(new FileWriter("data/postSecondarys/universities/" + university.getId() + '/' + program.getId() + ".txt"));
                        fout.write(program.toString());
                    }
                } else if(institution instanceof College college) {
                    fout = new BufferedWriter(new FileWriter("data/postSecondarys/colleges/" + college.getId() + "/college.txt"));
                    fout.write(college.toString());

                    for(Program program : college.getPrograms()) {
                        fout = new BufferedWriter(new FileWriter("data/postSecondarys/colleges/" + college.getId() + '/' + program.getId() + ".txt"));
                        fout.write(program.toString());
                    }
                }
            }
        } catch (IOException iox) {
            System.err.println("saveInstitutionData: IOException: " + iox);
        }
    }

    private void saveApplicationData() {
        BufferedWriter fout;

        try {
            for(Application application : applications) {
                fout = new BufferedWriter(new FileWriter("data/applications/" + application.getId() + ".txt"));
                fout.write(application.toString());
            }
        } catch (IOException iox) {
            System.err.println("saveApplicationData: IOException: " + iox);
        }
    }
}
