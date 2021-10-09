package taa.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkTest {
    @Test
    public void testMarksStringConversion() {
        assertEquals("Assignment 5, 56, 0.5",
                new Mark("Assignment 5", 56, 0.5).toString());
    }
}
