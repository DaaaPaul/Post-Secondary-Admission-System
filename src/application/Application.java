package src.application;

public class Application {
    private int id;
    private int studentId;
    private int postSecondaryId;
    private int programId;
    private String status;

    public Application(int id, int studentId, int postSecondaryId, int programId, String status) {
        this.id = id;
        this.studentId = studentId;
        this.postSecondaryId = postSecondaryId;
        this.programId = programId;
        this.status = status;
    }

    void setStatus(String newStatus) {
        status = newStatus;
    }

    String getStatus() {
        return status;
    }

    public int compareId(int id) {
        return Integer.compare(this.id, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(id); sb.append('\n');
        sb.append(studentId); sb.append('\n');
        sb.append(postSecondaryId); sb.append('\n');
        sb.append(programId); sb.append('\n');
        sb.append(status); sb.append('\n');

        return sb.toString();
    }
}
