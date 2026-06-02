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

    @Override
    public String toString() {
        return 
            id + '\n' +
            studentId + '\n' + 
            postSecondaryId + '\n' +
            programId + '\n' +
            status.name();
    }
}
