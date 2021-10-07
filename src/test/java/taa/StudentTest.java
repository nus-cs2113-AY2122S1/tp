package taa;

import taa.student.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    @Test
    public void testStringConversion() {
        assertEquals("Jon Lim, A0217746X", new Student("Jon Lim", "A0217746X").toString());
    }
    
}
