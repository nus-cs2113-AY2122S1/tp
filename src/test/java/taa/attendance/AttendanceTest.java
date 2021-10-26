package taa.attendance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttendanceTest {
    @Test
    void testAttendance() {
        assertEquals("Lesson 2 (Present)",
                new Attendance(2, true).toString());
    }
}
