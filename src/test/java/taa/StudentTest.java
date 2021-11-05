package taa;

import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.student.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    @Test
    public void testStringConversion() {
        assertEquals("A1234567B - Jon Lim", new Student("A1234567B", "Jon Lim").toString());
    }

    @Test
    void setMarks_newMarksArguments_marksAddedCorrectly() {
        Student student = new Student("A0123456X", "Jim");
        AssessmentList assessments = new AssessmentList();
        assessments.addAssessment(new Assessment("Midterms", 100, 20));
        student.setMarks("Midterms", 99.9);
        assertEquals(99.9, student.getMarks("Midterms"));
    }

    @Test
    void averageMarks_userInput_marksAveragedCorrectly() {
        
    }
}
