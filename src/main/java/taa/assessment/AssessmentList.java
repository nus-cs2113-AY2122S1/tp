package taa.assessment;

import java.util.ArrayList;

public class AssessmentList {
    private static final double MAX_ASSESSMENT_WEIGHTAGE = 100;

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
        if (assessment.getWeightage() > MAX_ASSESSMENT_WEIGHTAGE) {
            return false;
        }

        double totalWeightage = 0;
        for (Assessment a : assessments) {
            if (a.getName().equalsIgnoreCase(assessment.getName())) {
                return false;
            }
        }

        if ((totalWeightage + assessment.getWeightage()) > MAX_ASSESSMENT_WEIGHTAGE) {
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
}
