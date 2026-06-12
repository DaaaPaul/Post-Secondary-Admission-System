package src.system;

import java.io.*;
import java.time.*;
import src.student.*;
import src.postSecondary.*;
import src.application.*;
import src.utility.*;
import java.util.ArrayList;

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
        storeApplications(applicationData);
    }

    public void saveData() {
        saveStudentData();
        saveInstitutionData();
        saveApplicationData();
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

    private static ArrayList<GroupA> getGroupAs(ArrayList<File> data) { 
        ArrayList<GroupA> groupAs = new ArrayList();
        BufferedReader fin;

        try {
            for(File file : data) {
                fin = new BufferedReader(new FileReader(file));

                GroupA groupA = new GroupA(
                    Integer.parseInt(fin.readLine()),
                    fin.readLine(),
                    Integer.parseInt(fin.readLine())
                );

                addCoursesAndExtracirriculars(groupA, fin);

                groupAs.add(groupA);
            }
        } catch (IOException iox) {
            System.err.println("getGroupAs: IOException: " + iox);
        }

        return groupAs;
    }

    private static ArrayList<GroupB> getGroupBs(ArrayList<File> data) { 
        ArrayList<GroupB> groupBs = new ArrayList();
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

                addCoursesAndExtracirriculars(groupB, fin);

                groupBs.add(groupB);
            }
        } catch (IOException iox) {
            System.err.println("getGroupBs: IOException: " + iox);
        }

        return groupBs;
    }

    private static void addCoursesAndExtracirriculars(Student student, BufferedReader fin) {
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
                            System.err.println("addCoursesAndExtracirriculars: invalid string items on line");
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
                            System.err.println("addCoursesAndExtracirriculars: invalid string items on line");
                            break;
                    }

                    student.addExtracurricular(ec);
                } else {
                    System.err.println("addCoursesAndExtracirriculars: character is not letter nor digit");
                }
            }
        } catch (IOException iox) {
            System.err.println("addCoursesAndExtracirriculars: IOException: " + iox);
        }
    }

    private void storeApplications(ArrayList<File> applicationData) {
        ArrayList<Application> applications = new ArrayList();
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
            for(PostSecondary institution : postSecondarys) {
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
