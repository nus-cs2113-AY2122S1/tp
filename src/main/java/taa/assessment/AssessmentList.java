package taa.assessment;

import java.util.ArrayList;

public class AssessmentList {
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
     * @return tue if success, else false.
     */
    public boolean addAssessment(Assessment assessment) {
        double totalWeightage = 0;
        for (Assessment a : assessments) {
            if (a.getName().equalsIgnoreCase(assessment.getName())) {
                return false;
            }
        }

        if ((totalWeightage + assessment.getWeightage()) > Assessment.WEIGHTAGE_RANGE[1]) {
            return false;
        }

        assessments.add(assessment);
        return true;
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
        for (Assessment assessment : assessments) {
            String name = assessment.getName();
            if (name.equalsIgnoreCase(assessmentName)) {
                return assessment;
            }
        }

        return null;
    }

    public ArrayList<Assessment> getAssessments() {
        return new ArrayList<>(assessments);
    }

    public int getSize() {
        return assessments.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_ASSESSMENT_LIST_HEADER);
        for (int i = 0; i < assessments.size(); i += 1) {
            stringBuilder.append("\n");
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(assessments.get(i));
        }

        return stringBuilder.toString();
    }
}
