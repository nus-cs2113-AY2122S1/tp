package seedu.duke.module;

import java.util.HashMap;

//@@author nvbinh15

/**
 * A representation of the grades and their respective points.
 */
public class GradePoints {
    static HashMap<String, Double> gradePoints = new HashMap<>();

    /**
     * Class constructor.
     * Defines the grades and their respective points.
     */
    public GradePoints() {
        gradePoints.put("A+", 5.0);
        gradePoints.put("A", 5.0);
        gradePoints.put("A-", 4.5);
        gradePoints.put("B+", 4.0);
        gradePoints.put("B", 3.5);
        gradePoints.put("B-", 3.0);
        gradePoints.put("C+", 2.5);
        gradePoints.put("C", 2.0);
        gradePoints.put("D+", 1.5);
        gradePoints.put("D", 1.0);
        gradePoints.put("F", 0.0);
        gradePoints.put("CS", -1.0);
        gradePoints.put("CU", -1.0);
        gradePoints.put("NA", -1.0);
    }

    /**
     * Returns the point of a specific grade.
     *
     * @param rawGrade The raw grade.
     * @return The respective point of the grade.
     */
    public Double getPoint(String rawGrade) {
        return gradePoints.get(rawGrade);
    }

    /**
     * Checks if a grade is valid or not.
     *
     * @param rawGrade The raw grade.
     * @return true if the raw grade is a valid grade, false otherwise.
     */
    public boolean isValidGrade(String rawGrade) {
        return gradePoints.containsKey(rawGrade.toUpperCase());
    }
}
