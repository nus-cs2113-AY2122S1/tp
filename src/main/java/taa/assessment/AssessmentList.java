package taa.assessment;

import taa.ClassChecker;

import java.util.ArrayList;

public class AssessmentList implements ClassChecker {
    private final ArrayList<Assessment> assessments;

    public AssessmentList() {
        this.assessments = new ArrayList<>();
    }

    public int getSize() {
        return assessments.size();
    }

    /**
     * Gets the list of assessments. Note: This returns a new ArrayList instance.
     *
     * @return A new ArrayList containing all the assessments.
     */
    public ArrayList<Assessment> getAssessments() {
        return new ArrayList<>(assessments);
    }

    /**
     * Adds an assessment to the list of assessments in a particular class.
     * Does not add if assessment name already exists
     * or total weightage exceed the maximum weightage after adding.
     *
     * @param assessment The assessment object to be added.
     * @return true if success, else false.
     */
    public boolean addAssessment(Assessment assessment) {
        double totalWeightage = 0;
        for (Assessment a : assessments) {
            if (a.getName().equalsIgnoreCase(assessment.getName())) {
                return false;
            }

            totalWeightage += a.getWeightage();
        }

        double newTotalWeightage = totalWeightage + assessment.getWeightage();
        boolean isWeightageValid = Assessment.isWeightageWithinRange(newTotalWeightage);
        boolean isMaximumMarksValid = assessment.getMaximumMarks() >= Assessment.MINIMUM_MARKS;
        if (!isWeightageValid || !isMaximumMarksValid) {
            return false;
        }

        assessments.add(assessment);
        return true;
    }

    /**
     * Deletes an assessment from the list of assessments in a particular module.
     *
     * @param assessmentName The name of the assessment object to be removed.
     * @return The removed assessment object.
     */
    public Assessment deleteAssessment(String assessmentName) {
        int indexOfAssessmentToRemove = 0;
        for (Assessment assessment : assessments) {
            String name = assessment.getName();
            if (name.equalsIgnoreCase(assessmentName)) {
                return assessments.remove(indexOfAssessmentToRemove);
            }
            indexOfAssessmentToRemove += 1;
        }

        return null;
    }

    /**
     * Gets an Assessment object with a particular name. Note: name is case-insensitive.
     *
     * @param assessmentName The name of the assessment.
     * @return An Assessment object if found, else null.
     */
    public Assessment getAssessment(String assessmentName) {
        for (Assessment assessment : assessments) {
            String name = assessment.getName();
            if (name.equalsIgnoreCase(assessmentName)) {
                return assessment;
            }
        }

        return null;
    }

    public double totalWeightageForEditAssessmentCommand(String assessmentName) {
        double totalWeightage = 0;
        for (Assessment a : assessments) {
            if (!a.getName().equalsIgnoreCase(assessmentName)) {
                totalWeightage += a.getWeightage();
            }
        }
        return totalWeightage;
    }

    public boolean checkRepeatedName(String newAssessmentName) {
        for (Assessment a : assessments) {
            if (a.getName().equalsIgnoreCase(newAssessmentName)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < assessments.size(); i += 1) {
            if (i > 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(assessments.get(i));
        }

        return stringBuilder.toString();
    }

    /**
     * Checks if the variables within the class are valid. Filters out duplicate assessments with the same name.
     * Also, checks if the total weightage is valid.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        ArrayList<String> assessmentNames = new ArrayList<>();
        ArrayList<Assessment> duplicatedAssessments = new ArrayList<>();
        double totalWeightage = 0;
        for (Assessment assessment : assessments) {
            assert assessment != null : "assessment should exist.";
            String name = assessment.getName();
            if (assessmentNames.contains(name.toLowerCase())) {
                duplicatedAssessments.add(assessment);
            } else {
                assessmentNames.add(name.toLowerCase());
                totalWeightage += assessment.getWeightage();
            }
        }

        for (Assessment assessment : duplicatedAssessments) {
            assessments.remove(assessment);
        }

        if (!Assessment.isWeightageWithinRange(totalWeightage)) {
            return false;
        }

        return true;
    }
}
