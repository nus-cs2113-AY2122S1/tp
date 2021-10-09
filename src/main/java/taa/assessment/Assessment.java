package taa.assessment;

public class Assessment {
    private String assessmentName;
    private double weightage;

    public Assessment(String assessmentName, double weightage) {
        this.assessmentName = assessmentName;
        this.weightage = weightage;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public double getWeightage() {
        return weightage;
    }

    /**
     * Format the string to print out the assessment.
     * For Midterms with 20% weightage,
     * this will be printed: Midterms
     *                       Weightage: 20.0
     *
     * @return Assessment string.
     */
    @Override
    public String toString() {
        return String.format("%s\n  Weightage: %s", assessmentName, weightage);
    }
}
