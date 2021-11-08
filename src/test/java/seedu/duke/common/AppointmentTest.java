package seedu.duke.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {
    static Appointment generalAppointment;

    @BeforeAll
    public static void setUp() {
        generalAppointment = new Appointment("khoo teck puat hospital", "01012021",
                "0900", "general checkup");
    }

    @Test
    void testAppointmentDetails() {
        assertEquals("khoo teck puat hospital", generalAppointment.getLocation());
        assertEquals("01012021", generalAppointment.getDate());
        assertEquals("0900", generalAppointment.getTime());
        assertEquals("general checkup", generalAppointment.getPurpose());
    }

    @Test
    void testAppointmentToString() {
        String expectedOutput = "Location: khoo teck puat hospital, Date: 01012021, "
                + "Time: 0900, Purpose of Visit: general checkup";
        assertEquals(expectedOutput,
                generalAppointment.toString());
    }
}
