package taa.assessment;

import taa.ClassChecker;

public class Assessment implements ClassChecker {
    private static final double[] WEIGHTAGE_RANGE = {0, 100};
    private static final int MAXIMUM_MARKS_RANGE = 0;

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
        return (weightage >= WEIGHTAGE_RANGE[0] && weightage <= WEIGHTAGE_RANGE[1]);
    }

    /**
     * Checks if a maximum marks is valid.
     *
     * @param maximumMarks The maximum marks to check.
     * @return true if valid, else false.
     */
    public static boolean isMaximumMarksWithinRange(int maximumMarks) {
        return (maximumMarks >= MAXIMUM_MARKS_RANGE);
    }

    /**
     * Gets the weightage range.
     *
     * @return A double array of size 2: [0] - Min weightage, [1] Max weightage.
     */
    public static double[] getWeightageRange() {
        return WEIGHTAGE_RANGE;
    }

    public static int getMaximumMarksRange() {
        return MAXIMUM_MARKS_RANGE;
    }

    public String getName() {
        return name;
    }

    public double getWeightage() {
        return weightage;
    }

    public int getMaximumMarks() {
        return maximumMarks;
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
        if (name.isEmpty() || !isWeightageWithinRange(weightage)) {
            return false;
        }

        return true;
    }
}
