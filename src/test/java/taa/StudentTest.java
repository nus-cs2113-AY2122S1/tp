package taa;

import taa.student.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    @Test
    public void testStringConversion() {
        assertEquals("A1234567B - Jon Lim", new Student("A1234567B", "Jon Lim").toString());
    }
}
