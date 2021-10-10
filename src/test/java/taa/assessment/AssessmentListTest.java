package taa.assessment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssessmentListTest {

    @Test
    void addAssessment_newAssessmentArguments_assessmentAddedCorrectly() {
        String inputString = "c/CS2113T a/Midterms w/20";
        AssessmentList assessments = new AssessmentList();
        assessments.addAssessment(new Assessment("Midterms", 20));
        assertEquals("Midterms", assessments.getAssessment("Midterms").getName());
        assertEquals(20, assessments.getAssessment("Midterms").getWeightage());
    }
}
