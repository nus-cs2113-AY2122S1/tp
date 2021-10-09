package taa.student;

public class Mark {
    private String assessmentName;
    private int marks;
    private double weightage;

    public Mark(String assessmentName, int marks, double weightage) {
        this.assessmentName = assessmentName;
        this.marks = marks;
        this.weightage = weightage;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public int getMarks() {
        return marks;
    }

    public double getWeightage() {
        return weightage;
    }

    @Override
    public String toString() {
        return assessmentName + ", " + marks + ", " + weightage;
    }
}
