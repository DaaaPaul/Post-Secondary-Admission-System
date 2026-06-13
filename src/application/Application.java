package src.application;



import src.student.*;
import src.postSecondary.*;





public class Application {
    private int id;
    private Student student;
    private PostSecondary institution;
    private Program program;
    private String status;

    public Student getStudent() { return student; }
    public PostSecondary getInstitution() { return institution; }
    public Program getProgram() { return program; }

    public Application(int id, Student student, PostSecondary institution, Program program, String status) {
        this.id = id;
        this.student = student;
        this.institution = institution;
        this.program = program;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
    }

    public String getStatus() {
        return status;
    }

    public int compareId(int id) {
        return Integer.compare(this.id, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id); sb.append('\n');
        sb.append(student.getId()); sb.append('\n');
        sb.append(institution.getId()); sb.append('\n');
        sb.append(program.getId()); sb.append('\n');
        sb.append(status); sb.append('\n');

        return sb.toString();
    }
}
