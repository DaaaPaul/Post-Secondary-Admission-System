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
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        
        sb.append(highSchoolName); sb.append('\n');
        sb.append(birthday); sb.append('\n');

        return sb.toString();
    }
}
