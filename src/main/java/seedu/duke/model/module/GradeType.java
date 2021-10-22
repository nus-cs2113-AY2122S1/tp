package seedu.duke.model.module;

import seedu.duke.logic.parser.exceptions.ParseException;

public enum GradeType {
    ACTUAL, TARGET;

    public static GradeType convertToGradeType(String gradeType) throws ParseException {
        switch (gradeType) {
        case "actual":
            return ACTUAL;
        case "target":
            return TARGET;
        default:
            throw new ParseException("Invalid grade type.");
        }
    }
}
