package src.application;

public class Application {
    public enum Status {
        REJECTED,
        DEFERRED,
        PENDING,
        WAITLISTED,
        ACCEPTED
    }

    private int id;
    private int studentId;
    private int postSecondaryId;
    private int programId;
    private Status status;

    public Application(int id, int studentId, int postSecondaryId, int programId, Status status) {
        this.id = id;
        this.studentId = studentId;
        this.postSecondaryId = postSecondaryId;
        this.programId = programId;
        this.status = status;
    }

    void setStatus(Status newStatus) {
        status = newStatus;
    }

    Status getStatus() {
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
        sb.append(status.name()); sb.append('\n');

        return sb.toString();
    }
}
