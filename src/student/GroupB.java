package src.student;

import java.time.LocalDate;

/**
 * GroupB
 * Represents a specific type of student (Group B) with a high school name and birthday.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class GroupB extends Student {
    private String highSchoolName;
    private LocalDate birthday;

    /**
     * Initializes a GroupB student.
     * @param id the student ID
     * @param name the student's name
     * @param highSchoolName the high school the student attends
     * @param birthday the student's date of birth
     */
    public GroupB(int id, String name, String highSchoolName, LocalDate birthday) {
        super(id, name);
        this.highSchoolName = highSchoolName;
        this.birthday = birthday;
    }

    /**
     * Returns a string representation including the GroupB-specific details.
     * @return A formatted string of the GroupB student's profile
     */
    @Override
    public String toString() {
        return insertToString(super.toString(), highSchoolName + '\n' + birthday + '\n');
    }
}