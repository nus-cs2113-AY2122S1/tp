package taa.attendance;

import org.junit.jupiter.api.Test;
import taa.attendance.Attendance;
import taa.command.SetAttendanceCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttendanceTest {


    @Test
    void testAttendance() {
        assertEquals("CS2113T 2 3 true",
                new Attendance("CS2113T", "2", "3", true).toString());
    }


}