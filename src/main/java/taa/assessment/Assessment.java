package taa.assessment;

import taa.ClassChecker;

public class Assessment implements ClassChecker {
    public static final double[] WEIGHTAGE_RANGE = {0, 100};
    public static final int MINIMUM_MARKS = 0;

    private String name;
    private int maximumMarks;
    private double weightage;

    public Assessment(String name, int maximumMarks, double weightage) {
        this.name = name;
        this.maximumMarks = maximumMarks;
        this.weightage = weightage;
    }

    /**
     * Checks if a weightage is valid.
     *
     * @param weightage The weightage to check.
     * @return true if valid, else false.
     */
    public static boolean isWeightageWithinRange(double weightage) {
        return (weightage > WEIGHTAGE_RANGE[0] && weightage <= WEIGHTAGE_RANGE[1]);
    }

    public String getName() {
        return name;
    }

    public double getWeightage() {
        return weightage;
    }

    public boolean isMarksValid(double marks) {
        return ((marks >= MINIMUM_MARKS) && (marks <= maximumMarks));
    }

    public int getMaximumMarks() {
        return maximumMarks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaximumMarks(int maximumMarks) {
        this.maximumMarks = maximumMarks;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }

    /**
     * Format the string to print out the assessment.
     * For Midterms with 20% weightage,
     * this will be printed: Midterms (20.0%)
     *
     * @return Assessment string.
     */
    @Override
    public String toString() {
        return String.format("%s (%d, %,.2f%%)", name, maximumMarks, weightage);
    }

    /**
     * Checks if the variables within the class are valid.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        if (name.isEmpty() || !isWeightageWithinRange(weightage) || maximumMarks < MINIMUM_MARKS) {
            return false;
        }

        return true;
    }
}
