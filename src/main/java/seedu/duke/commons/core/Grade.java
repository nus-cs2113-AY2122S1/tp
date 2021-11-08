package seedu.duke.commons.core;

//@@author richwill28
public class Grade {
    public static final String[] GRADES = {
            "A+",   // Point = 5.0
            "A",    // Point = 5.0
            "A-",   // Point = 4.5
            "B+",   // Point = 4.0
            "B",    // Point = 3.5
            "B-",   // Point = 3.0
            "C+",   // Point = 2.5
            "C",    // Point = 2.0
            "D+",   // Point = 1.5
            "D",    // Point = 1.0
            "F",    // Point = 0.0
            "S",    // Satisfactory
            "U",    // Unsatisfactory
            "CS",   // Completed (Satisfactory)
            "CU",   // Completed (Unsatisfactory)
            "IC",   // Incomplete
            "IP",   // In Progress
            "AUD",  // Audit
            "EXE",  // Exempted
            "W",    // Withdrawn
            "WU",   // Withdrawal from University
            "NONE"  // Custom default grade
    };

    public static boolean isValid(String param) {
        for (String grade : GRADES) {
            if (param.equals(grade)) {
                return true;
            }
        }
        return false;
    }
}
