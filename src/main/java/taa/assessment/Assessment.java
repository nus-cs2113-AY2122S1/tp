package taa.assessment;

import taa.ClassChecker;

public class Assessment implements ClassChecker {
    public static final double[] WEIGHTAGE_RANGE = {0, 100};
    public static final double MINIMUM_MARKS = 0;

    private String name;
    private double maximumMarks;
    private double weightage;

    public Assessment(String name, double maximumMarks, double weightage) {
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
        int lowerBoundary = Double.compare(weightage, WEIGHTAGE_RANGE[0]);
        int upperBoundary = Double.compare(weightage, WEIGHTAGE_RANGE[1]);
        //valid lower boundary values are >=0
        //valid upper boundary values are <=0
        boolean isValidLowerBoundary = lowerBoundary >= 0;
        boolean isValidUpperBoundary = upperBoundary <= 0;
        return (isValidLowerBoundary && isValidUpperBoundary);
    }

    public static boolean isMaximumMarksValid(double maximumMarks) {
        int lowerBoundary = Double.compare(maximumMarks, MINIMUM_MARKS);
        boolean isValidLowerBoundary = lowerBoundary >= 0;
        return (isValidLowerBoundary);
    }

    public String getName() {
        return name;
    }

    public double getWeightage() {
        return weightage;
    }

    public boolean isMarksValid(double marks) {
        int lowerBoundary = Double.compare(marks, MINIMUM_MARKS);
        int upperBoundary = Double.compare(marks, maximumMarks);
        boolean isValidLowerBoundary = lowerBoundary >= 0;
        boolean isValidUpperBoundary = upperBoundary <= 0;
        return (isValidLowerBoundary && isValidUpperBoundary);
    }

    public double getMaximumMarks() {
        return maximumMarks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaximumMarks(double maximumMarks) {
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
        return String.format("%s (%,.2f, %,.2f%%)", name, maximumMarks, weightage);
    }

    /**
     * Checks if the variables within the class are valid.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        int validMaximumMarks = Double.compare(maximumMarks, MINIMUM_MARKS);
        boolean isValidMaximumMarks = validMaximumMarks < 0;
        if (name.isEmpty() || !isWeightageWithinRange(weightage) || isValidMaximumMarks) {
            return false;
        }

        return true;
    }
}
