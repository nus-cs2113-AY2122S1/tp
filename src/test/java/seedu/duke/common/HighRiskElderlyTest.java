package seedu.duke.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.hospital.Doctor;
import seedu.duke.hospital.Hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HighRiskElderlyTest {
    static HighRiskElderly hotchic31;
    static Hospital hospital;
    static Doctor doctor;

    @BeforeAll
    public static void setUp() {
        hospital = new Hospital("changi general hospital", 67888833);
        doctor = new Doctor("peter gang", 94501442);
        hotchic31 = new HighRiskElderly("yqt69", "Yang Qing Ting", hospital,
                "amnesia, lung cancer",
                "remember to follow the dietary scheduled planned out for mr yang. amnesia may lead to memory"
                        + " loss of eating. chemotherapy is every 6th day of the month at hospital. only allow one "
                        + "stick per day (night). keep an eye on him at all times.", doctor);
    }

    @Test
    void testElderlyDetails() {
        assertEquals("yqt69", hotchic31.getUsername());
    }

    @Test
    void testElderlyToString() {
        String expectetOutput = "[*] Risk Level : HIGH\n"
                + "[*] Elderly Username: yqt69\n"
                + "[*] Elderly Name: Yang Qing Ting\n"
                + "[*] Diet Preference of yqt69: Not set\n"
                + "[*] Vaccination status of yqt69: Not vaccinated\n"
                + "[*] Blood pressure of Yang Qing Ting has not yet been set!\n"
                + "[*] Birthday of yqt69: Not Recorded\n"
                + "[*] Medical History: \n"
                + "[*] Here are yqt69's records:\n"
                + "\n"
                + "\n"
                + "[*] Here is the list of upcoming appointments:\n"
                + "\n"
                + "\n"
                + "[*] This is the list of medicine(s):\n"
                + "\n"
                + "\n"
                + "[*] These are NOK(s) attached to yqt69:\n"
                + "\n"
                + "\n"
                + "[*] Hospital Details :\n"
                + "changi general hospital (Hospital Name)\n"
                + "67888833 (Hospital Number)\n"
                + "\n"
                + "[*] Conditions of elderly yqt69 : amnesia, lung cancer\n"
                + "\n"
                + "[*] Doctor for yqt69 :\n"
                + "peter gang (Doctor Name)\n"
                + "94501442 (Doctor Number)\n"
                + "\n"
                + "[*] Notes on care for yqt69 : remember to follow the dietary scheduled planned out for mr yang. "
                + "amnesia "
                + "may lead to memory"
                + " loss of eating. chemotherapy is every 6th day of the month at hospital. only allow one "
                + "stick per day (night). keep an eye on him at all times.\n";
        assertEquals(expectetOutput, hotchic31.toString());
    }
}
