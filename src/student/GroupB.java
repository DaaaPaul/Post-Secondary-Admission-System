package src.student;

import java.time.LocalDate;

public class GroupB extends Student {
    private String highSchoolName;
    private LocalDate birthday;

    public GroupB(int id, String name, String highSchoolName, LocalDate birthday) {
        super(id, name);
        this.highSchoolName = highSchoolName;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return insertToString(super.toString(), highSchoolName + '\n' + birthday + '\n');
    }
}
