package taa;

import org.junit.jupiter.api.Test;
import taa.student.Mark;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarkTest {
    @Test
    public void testMarksStringConversion() {
        assertEquals("Assignment 5, 56, 0.5",
                new Mark("Assignment 5", 56, 0.5).toString());
    }
}
