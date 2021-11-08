package seedu.duke.common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.hospital.Hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MediumRiskElderlyTest {
    static MediumRiskElderly hotchic31;
    static Hospital hospital;

    @BeforeAll
    public static void setUp() {
        hospital = new Hospital("changi general hospital", 67888833);
        hotchic31 = new MediumRiskElderly("hotchic31", "Owin Soh", hospital,
                "cataract removed, history of stroke, obesity",
                "requires putting of eyedrops every 5 hours. blood pressure and heart rate to be measured "
                        + "daily to ensure lower possibility of reoccurrence of stroke. watch diet intake.");
    }

    @Test
    void testElderlyDetails() {
        assertEquals("hotchic31", hotchic31.getUsername());
    }

    @Test
    void testElderlyToString() {
        String expectedOutput = "[*] Risk Level : MEDIUM\n"
                + "[*] Elderly Username: hotchic31\n"
                + "[*] Elderly Name: Owin Soh\n"
                + "[*] Diet Preference of hotchic31: Not set\n"
                + "[*] Vaccination status of hotchic31: Not vaccinated\n"
                + "[*] Blood pressure of Owin Soh has not yet been set!\n"
                + "[*] Birthday of hotchic31: Not Recorded\n"
                + "[*] Medical History: \n"
                + "[*] Here are hotchic31's records:\n"
                + "\n"
                + "\n"
                + "[*] Here is the list of upcoming appointments:\n"
                + "\n"
                + "\n"
                + "[*] This is the list of medicine(s):\n"
                + "\n"
                + "\n"
                + "[*] These are NOK(s) attached to hotchic31:\n"
                + "\n"
                + "\n"
                + "Hospital Details :\n"
                + "changi general hospital (Hospital Name)\n"
                + "67888833 (Hospital Number)\n"
                + "\n"
                + "Conditions of elderly hotchic31 : cataract removed, history of stroke, obesity\n"
                + "\n"
                + "Notes on care for hotchic31 : requires putting of eyedrops every 5 hours. "
                + "blood pressure and heart rate to be measured daily to ensure lower possibility of reoccurrence of "
                + "stroke. watch diet intake.\n";
        assertEquals(expectedOutput, hotchic31.toString());
    }
}
