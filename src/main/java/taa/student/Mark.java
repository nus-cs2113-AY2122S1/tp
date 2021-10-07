package taa.student;

public class Mark {
    private String assessment_name;
    private int marks;
    private double weightage;

    public Mark(String assessment_name, int marks, double weightage) {
        this.assessment_name = assessment_name;
        this.marks = marks;
        this.weightage = weightage;
    }

    public void setAssessmentName(String assessment_name) {
        this.assessment_name = assessment_name;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }

    public String getAssessmentName() {
        return assessment_name;
    }

    public int getMarks() {
        return marks;
    }

    public double getWeightage() {
        return weightage;
    }

    @Override
    public String toString() {
        return assessment_name + ", " + marks + ", " + weightage;
    }
}
