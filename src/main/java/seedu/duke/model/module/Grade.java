package seedu.duke.model.module;

import seedu.duke.logic.parser.exceptions.ParseException;

public enum Grade {
    A_PLUS, A, A_MINUS, B_PLUS, B, B_MINUS, C_PLUS, C, D_PLUS, D, F, S, U, NONE;

    public static final String GRADE_A_PLUS = "A+";
    public static final String GRADE_A = "A";
    public static final String GRADE_A_MINUS = "A-";
    public static final String GRADE_B_PLUS = "B+";
    public static final String GRADE_B = "B";
    public static final String GRADE_B_MINUS = "B-";
    public static final String GRADE_C_PLUS = "C+";
    public static final String GRADE_C = "C";
    public static final String GRADE_D_PLUS = "D+";
    public static final String GRADE_D = "D";
    public static final String GRADE_F = "F";
    public static final String GRADE_S = "S";
    public static final String GRADE_U = "U";
    public static final String GRADE_NONE = "NONE";

    public static Grade deserializeGrade(String grade) {
        return Grade.valueOf(grade);
    }

    public static Grade stringToGrade(String param) throws ParseException {
        switch (param) {
        case GRADE_A_PLUS:
            return A_PLUS;
        case GRADE_A:
            return A;
        case GRADE_A_MINUS:
            return A_MINUS;
        case GRADE_B_PLUS:
            return B_PLUS;
        case GRADE_B:
            return B;
        case GRADE_B_MINUS:
            return B_MINUS;
        case GRADE_C_PLUS:
            return C_PLUS;
        case GRADE_C:
            return C;
        case GRADE_D_PLUS:
            return D_PLUS;
        case GRADE_D:
            return D;
        case GRADE_F:
            return F;
        case GRADE_S:
            return S;
        case GRADE_U:
            return U;
        case GRADE_NONE:
            return NONE;
        default:
            throw new ParseException("The grade entered is invalid.");
        }
    }

    public static String gradeToString(Grade grade) {
        switch (grade) {
        case A_PLUS:
            return GRADE_A_PLUS;
        case A:
            return GRADE_A;
        case A_MINUS:
            return GRADE_A_MINUS;
        case B_PLUS:
            return GRADE_B_PLUS;
        case B:
            return GRADE_B;
        case B_MINUS:
            return GRADE_B_MINUS;
        case C_PLUS:
            return GRADE_C_PLUS;
        case C:
            return GRADE_C;
        case D_PLUS:
            return GRADE_D_PLUS;
        case D:
            return GRADE_D;
        case F:
            return GRADE_F;
        case S:
            return GRADE_S;
        case U:
            return GRADE_U;
        default:
            return GRADE_NONE;
        }
    }
}
