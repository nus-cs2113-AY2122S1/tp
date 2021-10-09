package taa.assessment;

import java.util.ArrayList;

public class AssessmentList {
    private static final double MAX_ASSESSMENT_WEIGHTAGE = 100;
    private static final String MESSAGE_ASSESSMENT_LIST_HEADER = "Assessment List:";

    private final ArrayList<Assessment> assessments;

    public AssessmentList() {
        this.assessments = new ArrayList<>();
    }

    /**
     * Adds an assessment to the list of assessments in a particular module.
     * Does not add if assessment name already exists
     * or total weightage exceed MAX_ASSESSMENT_WEIGHTAGE after adding.
     *
     * @param assessment The assessment object to be added.
     */
    public void addAssessment(Assessment assessment) {
        boolean isValidAssessment = true;
        double totalWeightage = 0;
        for (Assessment a : assessments) {
            totalWeightage += a.getWeightage();
            if (a.getAssessmentName().equalsIgnoreCase(assessment.getAssessmentName())) {
                isValidAssessment = false;
            }
        }
        if ((totalWeightage + assessment.getWeightage()) > MAX_ASSESSMENT_WEIGHTAGE) {
            isValidAssessment = false;
        }

        if (isValidAssessment) {
            assessments.add(assessment);
        }
    }

    public boolean isValidIndex(int index) {
        return (index >= 0 && index < assessments.size());
    }

    private Assessment getAssessmentAt(int index) {
        if (isValidIndex(index)) {
            return assessments.get(index);
        }

        return null;
    }

    public Assessment getAssessment(String assessmentName) {
        for (int i = 0; i < assessments.size(); i += 1) {
            Assessment assessment = getAssessmentAt(i);
            if (assessment.getAssessmentName().equalsIgnoreCase(assessmentName)) {
                return assessment;
            }
        }

        return null;
    }

    public int getSize() {
        return assessments.size();
    }

    /**
     * Format the string to print out all the assessments.
     *
     * @return Assessment list string.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_ASSESSMENT_LIST_HEADER);
        for (int i = 0; i < getSize(); i += 1) {
            stringBuilder.append("\n");
            stringBuilder.append(i + 1);
            stringBuilder.append(": ");
            stringBuilder.append(getAssessmentAt(i));
        }

        return stringBuilder.toString();
    }
}
