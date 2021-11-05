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
    void averageMarks_averageMarksArguments_marksAveragedCorrectly() {
        Student student1 = new Student("A0123456X", "Jim Ho");
        Student student2 = new Student("A0123456Y", "Kim Ho");
        Student student3 = new Student("A0123456Z", "Lim Ho");
        Student student4 = new Student("A0123456A", "Mim Ho");
        Student student5 = new Student("A0123456B", "Nim Ho");
        AssessmentList assessments = new AssessmentList();
        assessments.addAssessment(new Assessment("Midterms", 100, 20));
        student1.setMarks("Midterms", 50);
        student2.setMarks("Midterms", 60);
        student3.setMarks("Midterms", 70);
        student4.setMarks("Midterms", 80);
        student5.setMarks("Midterms", 90);
        assertEquals(70, );


    }
}
