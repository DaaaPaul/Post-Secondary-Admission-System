package src.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Extracurricular
 * Represents a student's extracurricular activity, tracking its duration to calculate merit points.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class Extracurricular {
    public static final LocalDate NULL_DATE = null;

    private final String name;
    private final LocalDate begin;
    private final LocalDate end;

    /**
     * Initializes an Extracurricular activity with a name and active dates.
     * @param name the name of the activity
     * @param begin the start date of the activity
     * @param end the end date of the activity
     */
    public Extracurricular(String name, LocalDate begin, LocalDate end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    /**
     * Returns a string representation of the extracurricular activity.
     * @return A concatenated string of the activity's name and dates
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name); sb.append(' ');
        sb.append(begin);
        if(end != NULL_DATE) {
            sb.append(' '); sb.append(end);
        }

        return sb.toString();
    }

    /**
     * Calculates the points earned based on the number of days the activity was active.
     * @return The integer points calculated from the duration
     */
    public int getPoints() {
        return 
            (end != NULL_DATE) ? 
            (int) ChronoUnit.DAYS.between(begin, end) :
            (int) ChronoUnit.DAYS.between(begin, LocalDate.now());
    }

    /**
     * Compares this activity's name to a provided string.
     * @param name the string name to compare against
     * @return An integer representing the lexicographical comparison result
     */
    public int compareName(String name) {
        return this.name.compareTo(name);
    }
}