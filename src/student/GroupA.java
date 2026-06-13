package src.student;

/**
 * GroupA
 * Represents a specific type of student (Group A) with an education number.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class GroupA extends Student {
    private int eduNum;

    /**
     * Initializes a GroupA student.
     * @param id the student ID
     * @param name the student's name
     * @param eduNum the student's education number
     */
    public GroupA(int id, String name, int eduNum) {
        super(id, name);
        this.eduNum = eduNum;
    }

    /**
     * Returns a string representation including the GroupA-specific details.
     * @return A formatted string of the GroupA student's profile
     */
    @Override
    public String toString() {
        return insertToString(super.toString(), eduNum + "\n");
    }
}