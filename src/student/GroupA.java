package src.student;

public class GroupA extends Student {
    private int eduNum;

    public GroupA(int id, String name, int eduNum) {
        super(id, name);
        this.eduNum = eduNum;
    }

    @Override
    public String toString() {
        return insertToString(super.toString(), eduNum + "\n");
    }
}
