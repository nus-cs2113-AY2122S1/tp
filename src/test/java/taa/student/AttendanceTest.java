package taa.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttendanceTest {

    @Test
    void testAttendanceStringConversion() {
        assertEquals("1 - 0",
                new Attendance(1).toString());
    }
}