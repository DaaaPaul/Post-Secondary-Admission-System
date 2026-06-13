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
        institutions = new ArrayList<>();
        students = new ArrayList<>();
        applications = new ArrayList<>();

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

        while (!finished) {
            System.out.print("Enter Student ID: "); int id = Integer.parseInt(in.nextLine());
            System.out.print("Enter Student Name: "); String name = in.nextLine();

            System.out.print("Enter Student Type: ");
            String type = in.nextLine();

            if (type.equals("A")) {
                System.out.println("Enter Student Education Number: ");
                int edu = Integer.parseInt(in.nextLine());

                student = new GroupA(id, name, edu);
                students.add(student);

                System.out.println("Student added successfully.");
                finished = true;

            } else if (type.equals("B")) {
                System.out.println("Enter High School Name: ");
                String hs = in.nextLine();

                System.out.println("Enter birth year: "); int year = Integer.parseInt(in.nextLine());;
                System.out.println("Enter birth month: "); int month = Integer.parseInt(in.nextLine());;
                System.out.println("Enter birth day: "); int day = Integer.parseInt(in.nextLine());;
                LocalDate date = LocalDate.of(year, month, day);

                student = new GroupB(id, name, hs, date);
                students.add(student);

                System.out.println("Student added successfully.");
                finished = true;

            } else {
                System.out.println("Invalid input. Please retry.");
            }
        }
    }

    public void removeStudent() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(Integer.parseInt(in.nextLine()));

        if (students.remove(student)) {
            System.out.println("Student successfully removed.");
        } else {
            System.out.println("Student not found in system.");
        }
    }

    public void addCourseToStudent() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(Integer.parseInt(in.nextLine()));

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
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(Integer.parseInt(in.nextLine()));

        if (student == null) {
            System.out.println("Student not found in system.");

        } else {
            System.out.println("Enter course code.");
            String course = in.nextLine();

            if (student.searchCourseByCourseCode(course) == null) {
                System.out.println("Course not found in student record.");

            } else {
                student.removeCourses(course);
                System.out.println("Course successfully removed from student record.");
            }
        }
    }

    public void addExtracurricularToStudent() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(Integer.parseInt(in.nextLine()));

        if(student == null) {
            System.out.println("Student not found in system.");

        } else {
            System.out.print("Enter extracurricular name: ");
            String name = in.nextLine();

            System.out.print("Enter begin year: "); int year = Integer.parseInt(in.nextLine());
            System.out.print("Enter begin month: "); int month = Integer.parseInt(in.nextLine());
            System.out.print("Enter begin day: "); int day = Integer.parseInt(in.nextLine());

            LocalDate begin = LocalDate.of(year, month, day);

            System.out.print("Has the activity ended? (Y/N): ");
            char ended = Character.toUpperCase(in.next().charAt(0));

            LocalDate end = Extracurricular.NULL_DATE;
            if (ended == 'Y') {
                System.out.print("Enter end year: "); year = Integer.parseInt(in.nextLine());
                System.out.print("Enter end month: "); month = Integer.parseInt(in.nextLine());
                System.out.print("Enter end day: "); day = Integer.parseInt(in.nextLine());
                end = LocalDate.of(year, month, day);
            }

            student.addExtracurricular(new Extracurricular(name, begin, end));
            System.out.println("Extracurricular added successfully.");
        }
    }

    public void removeExtracurricularFromStudent() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(Integer.parseInt(in.nextLine()));

        if(student == null) {
            System.out.println("Student not found in system.");

        } else {
            System.out.print("Enter extracurricular name: ");
            String name = in.nextLine();

            if (student.removeExtracurriculars(name)) {
                System.out.println("Extracurricular successfully removed.");
            } else {
                System.out.println("Extracurricular not found.");
            }
        }
    }

    // I used return here because there will be way too many nested if-else otherwise.
    public void addApplicationToStudentAndInstitution() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Application ID: ");
        int applicationId = Integer.parseInt(in.nextLine());

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(Integer.parseInt(in.nextLine()));
        if(student == null) {
            System.out.println("Student not found in system.");
            return;
        }

        System.out.print("Enter Institution ID: ");
        PostSecondary institution = searchInstitutionById(Integer.parseInt(in.nextLine()));
        if(institution == null) {
            System.out.println("Institution not found in system.");
            return;
        }

        System.out.print("Enter Program ID: ");
        Program program = institution.searchProgramById(Integer.parseInt(in.nextLine()));
        if(program == null) {
            System.out.println("Program not found.");
            return;
        }

        System.out.print("Enter Application Status: ");
        String status = in.nextLine();

        Application application = new Application(applicationId, student, institution, program, status);
        student.addApplication(application);
        institution.addApplication(application);

        System.out.println("Application added successfully.");
    }

    public void removeApplicationFromStudentAndInstitution() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        Student student = searchStudentById(Integer.parseInt(in.nextLine()));
        if(student == null) {
            System.out.println("Student not found in system.");
            return;
        }

        System.out.print("Enter Application ID: ");
        int id = Integer.parseInt(in.nextLine());
        Application application = student.searchApplicationById(id);
        if(application == null) {
            System.out.println("Application not found.");
            return;
        }

        student.removeApplication(id);
        application.getInstitution().removeApplication(id);
        System.out.println("Application successfully removed.");
    }



    public void addPostSecondary(PostSecondary postSecondary) {
        Scanner in = new Scanner(System.in);
        boolean finished = false;

        while (!finished) {
            System.out.print("Enter Institution ID: "); int id = Integer.parseInt(in.nextLine());
            System.out.print("Enter Institution Name: "); String name = in.nextLine();

            System.out.print("Enter institution type ('C' for college, 'U' for university): ");
            char type = Character.toUpperCase(in.nextLine().charAt(0));

            if (type == 'C') { // college
                char offers;
                boolean offersApprenticeship = true;

                do {
                    System.out.print("Does the college offer apprenticeship program(s)? Enter 'Y' for yes, 'N' for no: ");
                    offers = Character.toUpperCase(in.nextLine().charAt(0));

                    if (offers == 'N') {
                        offersApprenticeship = false;
                    } else if (offers != 'Y') {
                        System.out.println("Invalid input.");
                    }

                } while (offers != 'Y' && offers != 'N');

                System.out.print("Enter the graduate employment rate: ");
                double employmentRate = Double.parseDouble(in.nextLine());

                College college = new College(id, name, offersApprenticeship, employmentRate);
                institutions.add(college);

                System.out.println("College successfully added.");
                finished = true;

            } else if (type == 'U') { // university
                System.out.print("Enter annual research funding: "); int arf = Integer.parseInt(in.nextLine());
                System.out.print("Enter World QS Ranking: "); int qs = Integer.parseInt(in.nextLine());

                University university = new University(id, name, arf, qs);
                institutions.add(university);

                System.out.println("University successfully added.");
                finished = true;

            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    public void removePostSecondary() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Institution ID: ");
        PostSecondary institution = searchInstitutionById(Integer.parseInt(in.nextLine()));

        if (institutions.remove(institution)) {
            System.out.println("Institution successfully removed.");
        } else {
            System.out.println("Institution not found in system.");
        }
    }

    public void addProgramToInstitution() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Institution ID: ");
        PostSecondary institution = searchInstitutionById(Integer.parseInt(in.nextLine()));

        if (institution == null) {
            System.out.println("Institution not found in system.");

        } else {
            System.out.print("Enter Program ID: "); int id = Integer.parseInt(in.nextLine());
            System.out.print("Enter Program Name: "); String name = in.nextLine();
            System.out.print("Enter Required Average: "); double avg = Double.parseDouble(in.nextLine());
            System.out.print("Enter Required Points (-1 if none): "); int points = Integer.parseInt(in.nextLine());

            Program program = new Program(id, institution.getId(), name, avg, points);

            System.out.println("Enter required courses (type 'done' to finish):");

            String course = in.nextLine();
            while (!course.equalsIgnoreCase("done")) {
                program.addRequiredCourse(course);
                course = in.nextLine();
            }

            institution.addProgram(program);
            System.out.println("Program successfully added.");
        }
    }

    public void removeProgramFromInstitution() {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Institution ID: ");
        PostSecondary institution = searchInstitutionById(Integer.parseInt(in.nextLine()));

        if (institution == null) {
            System.out.println("Institution not found in system.");

        } else {
            System.out.print("Enter Program ID: ");
            Program program = institution.searchProgramById(Integer.parseInt(in.nextLine()));

            if (institution.getPrograms().remove(program)) {
                System.out.println("Program successfully removed.");
            } else {
                System.out.println("Program not found in system.");
            }
        }
    }

    public Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> searchStudentByName(String name) {
        ArrayList<Student> ret = new ArrayList<>();
        for (Student stu : students) {
            if (stu.getName().equals(name)) {
                ret.add(stu);
            }
        }
        return ret;
    }

    public PostSecondary searchInstitutionById(int id) {
        for (PostSecondary institution : institutions) {
            if (institution.getId() == id) {
                return institution;
            }
        }
        return null;
    }

    public PostSecondary searchPostSecondaryByName(String name) {
        for (PostSecondary institution : institutions) {
            if (institution.getName().equals(name)) {
                return institution;
            }
        }
        return null;
    }

    public Application searchApplicationById(int id) {
        for (Application app : applications) {
            if (app.getId() == id) {
                return app;
            }
        }
        return null;
    }

    public ArrayList<Application> searchApplicationByStudent(int id) {
        ArrayList<Application> ret = new ArrayList<>();
        for (Application app : applications) {
            if (app.getStudent().getId() == id) {
                ret.add(app);
            }
        }
        return ret;
    }

    public ArrayList<Application> searchApplicationsByProgram(int id) {
        ArrayList<Application> ret = new ArrayList<>();
        for (Application app : applications) {
            if (app.getProgram().getId() == id) {
                ret.add(app);
            }
        }
        return ret;
    }

    public void sortStudentsByMerit() {
        for (int i = 1; i < students.size(); i++) {
            Student cur = students.get(i);
            int j = i - 1;
            while (j >= 0 && students.get(j).getAverage() < cur.getAverage()) {
                students.set(j + 1, students.get(j));
                j--;
            }
            students.set(j + 1, cur);
        }
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
                PostSecondary institution = searchInstitutionById(Integer.parseInt(fin.readLine()));
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
                    fout = new BufferedWriter(new FileWriter("data/postSecondary/universities/" + university.getId() + "/university.txt"));
                    fout.write(university.toString());

                    for(Program program : university.getPrograms()) {
                        fout = new BufferedWriter(new FileWriter("data/postSecondary/universities/" + university.getId() + '/' + program.getId() + ".txt"));
                        fout.write(program.toString());
                    }
                } else if(institution instanceof College college) {
                    fout = new BufferedWriter(new FileWriter("data/postSecondary/colleges/" + college.getId() + "/college.txt"));
                    fout.write(college.toString());

                    for(Program program : college.getPrograms()) {
                        fout = new BufferedWriter(new FileWriter("data/postSecondary/colleges/" + college.getId() + '/' + program.getId() + ".txt"));
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