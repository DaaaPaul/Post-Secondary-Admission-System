package src.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Extracurricular {
    public static final LocalDate NULL_DATE = null;

    private final String name;
    private final LocalDate begin;
    private final LocalDate end;

    public Extracurricular(String name, LocalDate begin, LocalDate end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name); sb.append('\n');
        sb.append(begin); sb.append('\n');
        sb.append(end); sb.append('\n');

        return sb.toString();
    }

    public int getPoints() {
        return 
            (end != NULL_DATE) ? 
            (int) ChronoUnit.DAYS.between(begin, end) :
            (int) ChronoUnit.DAYS.between(begin, LocalDate.now());
    }

    public int compareName(String name) {
        return this.name.compareTo(name);
    }
}
