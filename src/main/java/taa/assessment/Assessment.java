package taa.assessment;

import taa.ClassChecker;

public class Assessment implements ClassChecker {
    private static final double[] WEIGHTAGE_RANGE = {0, 100};

    private String name;
    private double weightage;

    public Assessment(String name, double weightage) {
        this.name = name;
        this.weightage = weightage;
    }

    public static boolean isWeightageWithinRange(double weightage) {
        return (weightage >= WEIGHTAGE_RANGE[0] && weightage <= WEIGHTAGE_RANGE[1]);
    }

    public static double[] getWeightageRange() {
        return WEIGHTAGE_RANGE;
    }

    public String getName() {
        return name;
    }

    public double getWeightage() {
        return weightage;
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
        return String.format("%s (%,.2f%%)", name, weightage);
    }

    @Override
    public boolean verify() {
        if (name.isEmpty() || !isWeightageWithinRange(weightage)) {
            return false;
        }

        return true;
    }
}
