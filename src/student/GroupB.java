package src.student;

import java.time.LocalDate;

public class GroupB extends Student {
    private String highschoolName;
    private LocalDate birthday;

    public GroupB(int id, String name, String highschoolName, LocalDate birthday) {
        super(id, name);
        this.highschoolName = highschoolName;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        
        sb.append(highschoolName); sb.append('\n');
        sb.append(birthday); sb.append('\n');

        return sb.toString();
    }
}
