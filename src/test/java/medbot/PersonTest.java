package medbot;

import org.junit.jupiter.api.Test;

import medbot.person.Patient;
import medbot.person.Person;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class PersonTest {
    private static final String END_LINE = System.lineSeparator();


    @Test
    public void testStringConversion() {
        String correctString = "Patient ID: 0 " + END_LINE
                + "IC: " + END_LINE
                + "Name: " + END_LINE
                + "H/P: " + END_LINE
                + "Email: " + END_LINE
                + "Address: ";
        Patient patient = new Patient();
        assertEquals(patient.toString(), correctString);
    }

    @Test
    public void testStringConversionFilled() {
        Patient patient = new Patient();
        patient.setIcNumber("S8712345G");
        patient.setName("Alice Tan");
        patient.setPhoneNumber("81234567");
        patient.setEmailAddress("alicetan@example.com");
        patient.setResidentialAddress("Blk 123 Bishan St 12 #05-39");
        String correctString = "Patient ID: 0 " + END_LINE
                + "IC: " + "S8712345G" + END_LINE
                + "Name: " + "Alice Tan" + END_LINE
                + "H/P: " + "81234567" + END_LINE
                + "Email: " + "alicetan@example.com" + END_LINE
                + "Address: " + "Blk 123 Bishan St 12 #05-39";
        assertEquals(correctString, patient.toString());
    }

    @Test
    public void testSetNull() {
        Patient patient = new Patient();
        patient.setPersonId(123);
        patient.setName("Ben");
        patient.setNull();
        assertEquals(patient.getPersonId(), 123);
        assertNull(patient.getName());
        assertNull(patient.getEmailAddress());
        assertNull(patient.getResidentialAddress());
        assertNull(patient.getIcNumber());
        assertNull(patient.getPhoneNumber());
    }


}
