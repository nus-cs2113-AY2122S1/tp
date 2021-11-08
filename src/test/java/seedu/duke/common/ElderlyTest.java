package seedu.duke.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ElderlyTest {
    static Elderly johnTan;

    @BeforeAll
    public static void setUp() {
        johnTan = new LowRiskElderly("johntan123", "John Tan");
    }

    @Test
    void testElderlyDetails() {
        assertEquals("johntan123", johnTan.getUsername());
    }

    @Test
    void testElderlyToString() {
        String expectedOutput = "[*] Risk Level : LOW\n"
                + "[*] Elderly Username: johntan123\n"
                + "[*] Elderly Name: John Tan\n"
                + "[*] Diet Preference of johntan123: Not set\n"
                + "[*] Vaccination status of johntan123: Not vaccinated\n"
                + "[*] Blood pressure of John Tan has not yet been set!\n"
                + "[*] Birthday of johntan123: Not Recorded\n"
                + "[*] Medical History: \n"
                + "[*] Here are johntan123's records:\n"
                + "\n"
                + "\n"
                + "[*] Here is the list of upcoming appointments:\n"
                + "\n"
                + "\n"
                + "[*] This is the list of medicine(s):\n"
                + "\n"
                + "\n"
                + "[*] These are NOK(s) attached to johntan123:\n"
                + "\n";
        assertEquals(expectedOutput, johnTan.toString());
        johnTan.setElderlyVaccinated();
        expectedOutput = "[*] Risk Level : LOW\n"
                + "[*] Elderly Username: johntan123\n"
                + "[*] Elderly Name: John Tan\n"
                + "[*] Diet Preference of johntan123: Not set\n"
                + "[*] Vaccination status of johntan123: Vaccinated\n"
                + "[*] Blood pressure of John Tan has not yet been set!\n"
                + "[*] Birthday of johntan123: Not Recorded\n"
                + "[*] Medical History: \n"
                + "[*] Here are johntan123's records:\n"
                + "\n"
                + "\n"
                + "[*] Here is the list of upcoming appointments:\n"
                + "\n"
                + "\n"
                + "[*] This is the list of medicine(s):\n"
                + "\n"
                + "\n"
                + "[*] These are NOK(s) attached to johntan123:\n"
                + "\n";
        assertEquals(expectedOutput, johnTan.toString());
    }

}
