package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradePointsTest {

    GradePoints gradePoints = new GradePoints();

    @Test
    void getPoint_validRawGrade() {
        assertEquals(gradePoints.getPoint("A+"), 5.0);
        assertEquals(gradePoints.getPoint("B"), 3.5);
        assertEquals(gradePoints.getPoint("F"), 0.0);
        assertEquals(gradePoints.getPoint("NA"), -1.0);
    }

    @Test
    void isValidGrade_validRawGrade() {
        assertTrue(gradePoints.isValidGrade("A"));
        assertTrue(gradePoints.isValidGrade("a"));
        assertTrue(gradePoints.isValidGrade("b+"));
        assertTrue(gradePoints.isValidGrade("NA"));
    }

    @Test
    void isValidGrade_invalidRawGrade() {
        assertFalse(gradePoints.isValidGrade("A +"));
        assertFalse(gradePoints.isValidGrade("5.0"));
        assertFalse(gradePoints.isValidGrade("Not applicable"));
    }
}