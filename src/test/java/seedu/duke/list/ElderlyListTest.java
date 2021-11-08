package seedu.duke.list;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.common.Appointment;
import seedu.duke.common.Medicine;
import seedu.duke.common.NextOfKin;
import seedu.duke.common.Record;
import seedu.duke.exceptions.ElderlyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.common.MagicValues.DEFAULT_FILE_PATH;

public class ElderlyListTest {
    static ElderlyList elderlyList = new ElderlyList(DEFAULT_FILE_PATH);
    ArrayList<Medicine> tanMedicines = new ArrayList<Medicine>();
    ArrayList<Medicine> limMedicines = new ArrayList<Medicine>();
    ArrayList<Appointment> tanAppointments = new ArrayList<Appointment>();
    ArrayList<Appointment> limAppointments = new ArrayList<Appointment>();
    ArrayList<NextOfKin> tanNoks = new ArrayList<NextOfKin>();
    ArrayList<NextOfKin> limNoks = new ArrayList<NextOfKin>();
    ArrayList<Record> tanRecords = new ArrayList<Record>();
    ArrayList<Record> limRecords = new ArrayList<Record>();

    @BeforeAll
    public static void setUp() {
        elderlyList.addElderly("addelderly u/johntan123 n/John Tan r/l");
        elderlyList.addElderly("addelderly u/limqq369 n/Lim Qi Qi r/l");
        elderlyList.addMedicine("addmed u/limqq369 m/panadol f/once a day");
        elderlyList.addAppointment("addappt u/limqq369 l/khoo teck puat hospital d/01012021 t/0900");
        elderlyList.addNok("addnok u/limqq369 k/tony p/98765432 e/tony@yahoo.com a/123 yishun st r/son");
        elderlyList.addRecord("addrec u/limqq369 p/98776542 a/234 bukit merah st");
        elderlyList.setBirthday("setbirthday u/limqq369 b/1903-06-09");
        elderlyList.setBloodPressure("setbloodpressure u/limqq369 s/169 d/108");
        elderlyList.setVaccinated("setvaccinated u/limqq369");
    }

    @Test
    void elderlyCountTest() {
    //        assertEquals(elderlyList.getElderlyCount(), 10);
    }

    @Test
    void elderlyListTest() {
    //        assertEquals(elderlyList.getConsolidatedStringOfElderly(), "Elderly Name: johntan123\n"
    //                        + "Vaccination status of johntan123: Not vaccinated\n"
    //                        + "Birthday of johntan123: Not Recorded\n"
    //                        + "Here are johntan123's records:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to johntan123:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Elderly Name: limqq369\n"
    //                        + "Vaccination status of limqq369: Vaccinated\n"
    //                        + "Birthday of limqq369: 1903-06-09\n"
    //                        + "Here are limqq369's records:\n"
    //                        + "Elderly HP: 98776542, Elderly Address: 234 bukit merah st\n"
    //                        + "Elderly HP: 98776542, Elderly Address: 234 bukit merah st\n"
    //                        + "Elderly HP: 98776542, Elderly Address: 234 bukit merah st\n"
    //                        + "Elderly HP: 98776542, Elderly Address: 234 bukit merah st\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "Location: khoo teck puat hospital, Date: 01012021, Time: 0900, Purpose of Visit: "
    //                        + "general checkup\n"
    //                        + "Location: khoo teck puat hospital, Date: 01012021, Time: 0900, Purpose of Visit: "
    //                        + "general checkup\n"
    //                        + "Location: khoo teck puat hospital, Date: 01012021, Time: 0900, Purpose of Visit: "
    //                        + "general checkup\n"
    //                        + "Location: khoo teck puat hospital, Date: 01012021, Time: 0900, Purpose of Visit: "
    //                        + "general checkup\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "Medicine Name: panadol, Frequency: once a day\n"
    //                        + "Medicine Name: panadol, Frequency: once a day\n"
    //                        + "Medicine Name: panadol, Frequency: once a day\n"
    //                        + "Medicine Name: panadol, Frequency: once a day\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to limqq369:\n"
    //                        + "NOK Name: tony, NOK HP: 98765432, NOK Email: tony@yahoo.com, NOK Address: 123 yishun
        //                        st, "
    //                        + "Relationship: son\n"
    //                        + "NOK Name: tony, NOK HP: 98765432, NOK Email: tony@yahoo.com, NOK Address: 123 yishun
        //                        st, "
    //                        + "Relationship: son\n"
    //                        + "NOK Name: tony, NOK HP: 98765432, NOK Email: tony@yahoo.com, NOK Address: 123 yishun
        //                        st, "
    //                        + "Relationship: son\n"
    //                        + "NOK Name: tony, NOK HP: 98765432, NOK Email: tony@yahoo.com, NOK Address: 123 yishun
        //                        st, "
    //                        + "Relationship: son\n"
    //                        + "\n"
    //                        + "Elderly Name: johntan123\n"
    //                        + "Vaccination status of johntan123: Not vaccinated\n"
    //                        + "Birthday of johntan123: Not Recorded\n"
    //                        + "Here are johntan123's records:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to johntan123:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Elderly Name: limqq369\n"
    //                        + "Vaccination status of limqq369: Not vaccinated\n"
    //                        + "Birthday of limqq369: Not Recorded\n"
    //                        + "Here are limqq369's records:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to limqq369:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Elderly Name: johntan123\n"
    //                        + "Vaccination status of johntan123: Not vaccinated\n"
    //                        + "Birthday of johntan123: Not Recorded\n"
    //                        + "Here are johntan123's records:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to johntan123:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Elderly Name: limqq369\n"
    //                        + "Vaccination status of limqq369: Not vaccinated\n"
    //                        + "Birthday of limqq369: Not Recorded\n"
    //                        + "Here are limqq369's records:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to limqq369:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Elderly Name: johntan123\n"
    //                        + "Vaccination status of johntan123: Not vaccinated\n"
    //                        + "Birthday of johntan123: Not Recorded\n"
    //                        + "Here are johntan123's records:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to johntan123:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Elderly Name: limqq369\n"
    //                        + "Vaccination status of limqq369: Not vaccinated\n"
    //                        + "Birthday of limqq369: Not Recorded\n"
    //                        + "Here are limqq369's records:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "Here is the list of upcoming appointments:\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "This is the list of medicine(s):\n"
    //                        + "\n"
    //                        + "\n"
    //                        + "These are NOK(s) attached to limqq369:"
    //                        + "\n"
    //                        + "\n"
    //        );
    }

    @Test
    void addElderlyTest() {
        final String expectedOutput = "johntan123";
        try {
            assertEquals(expectedOutput, elderlyList.getElderly("johntan123").getUsername());
        } catch (ElderlyNotFoundException e) {
            System.out.println("error");
        }
    }

    @Test
    void addMedTest() {
        try {
            assertEquals(tanMedicines, elderlyList.getElderly("johntan123").getMedicines());
            Medicine limMedicine = elderlyList.getElderly("limqq369").getMedicines().get(0);
            assertEquals("panadol", limMedicine.getMedicineName());
            assertEquals("once a day", limMedicine.getFrequency());
        } catch (ElderlyNotFoundException e) {
            System.out.println("error");
        }
    }

    @Test
    void addAppointmentTest() {
        try {
            assertEquals(tanAppointments, elderlyList.getElderly("johntan123").getAppointments());
            Appointment limAppointments = elderlyList.getElderly("limqq369").getAppointments().get(0);
            assertEquals("khoo teck puat hospital", limAppointments.getLocation());
            assertEquals("01012021", limAppointments.getDate());
            assertEquals("0900", limAppointments.getTime());
            assertEquals("general checkup", limAppointments.getPurpose());
        } catch (ElderlyNotFoundException e) {
            System.out.println("error");
        }
    }

    @Test
    void addNokTest() {
        try {
            assertEquals(tanNoks, elderlyList.getElderly("johntan123").getNextOfKin());
            NextOfKin limNoks = elderlyList.getElderly("limqq369").getNextOfKin().get(0);
            assertEquals("tony", limNoks.getNokName());
            assertEquals("98765432", limNoks.getNokPhoneNumber());
            assertEquals("tony@yahoo.com", limNoks.getNokEmail());
            assertEquals("123 yishun st", limNoks.getNokAddress());
            assertEquals("son", limNoks.getNokRelationship());
        } catch (ElderlyNotFoundException e) {
            System.out.println("error");
        }
    }

    @Test
    void addRecordTest() {
        try {
            assertEquals(tanRecords, elderlyList.getElderly("johntan123").getRecord());
            Record limRecords = elderlyList.getElderly("limqq369").getRecord().get(0);
            assertEquals("98776542", limRecords.getElderlyPhoneNumber());
            assertEquals("234 bukit merah st", limRecords.getElderlyAddress());
        } catch (ElderlyNotFoundException e) {
            System.out.println("error");
        }
    }

    @Test
    void birthdayTest() {
        try {
            final String expectedOutput = "1903-06-09";
            assertEquals(expectedOutput,
                    elderlyList.getElderly("limqq369").getBirthday());
        } catch (ElderlyNotFoundException e) {
            System.out.println("error");
        }
    }

    @Test
    void bloodPressureTest() {
        try {
            final int[] expectedOutput = new int[]{169, 108};
            assertArrayEquals(expectedOutput,
                    elderlyList.getElderly("limqq369").getElderlyBloodPressure());
        } catch (ElderlyNotFoundException e) {
            System.out.println("error");
        }
    }

    @Test
    void findByMedTest() {
        assertEquals(elderlyList.buildElderlyStringGivenMedicine("panadol"), "limqq369");
    }
}
