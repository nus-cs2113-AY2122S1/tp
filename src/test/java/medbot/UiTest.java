package medbot;

import medbot.exceptions.MedBotException;
import medbot.list.MedicalStaffList;
import medbot.list.PatientList;
import medbot.person.Patient;
import medbot.person.Staff;
import medbot.ui.PatientUi;
import medbot.ui.StaffUi;
import medbot.ui.Ui;
import medbot.utilities.ViewType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UiTest {
    public static final String END_LINE = System.lineSeparator();

    @Test
    public void testPrintAddPatientMessage() {
        String expectedPatientOutput = "Added patient with Patient ID: 1 " + END_LINE
                + "IC: " + "S8712345G" + END_LINE
                + "Name: " + "Alice Tan" + END_LINE
                + "H/P: " + "81234567" + END_LINE
                + "Email: " + "alicetan@example.com" + END_LINE
                + "Address: " + "Blk 123 Bishan St 12 #05-39" + END_LINE;
        String patientInfo = "Patient ID: 1 " + END_LINE
                + "IC: " + "S8712345G" + END_LINE
                + "Name: " + "Alice Tan" + END_LINE
                + "H/P: " + "81234567" + END_LINE
                + "Email: " + "alicetan@example.com" + END_LINE
                + "Address: " + "Blk 123 Bishan St 12 #05-39";

        try {
            String actualPatientOutput = Ui.getAddMessage(ViewType.PATIENT_INFO, patientInfo);
            assertEquals(expectedPatientOutput, actualPatientOutput);

        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintAddStaffMessage() {
        String expectedStaffOutput = "Added staff with Staff ID: 3 " + END_LINE
                + "IC: " + "S8712345G" + END_LINE
                + "Name: " + "Alice Tan" + END_LINE
                + "H/P: " + "81234567" + END_LINE
                + "Email: " + "alicetan@example.com" + END_LINE
                + "Address: " + "Blk 123 Bishan St 12 #05-39" + END_LINE;
        String staffInfo = "Staff ID: 3 " + END_LINE
                + "IC: " + "S8712345G" + END_LINE
                + "Name: " + "Alice Tan" + END_LINE
                + "H/P: " + "81234567" + END_LINE
                + "Email: " + "alicetan@example.com" + END_LINE
                + "Address: " + "Blk 123 Bishan St 12 #05-39";

        try {
            String actualStaffOutput = Ui.getAddMessage(ViewType.MEDICAL_STAFF_INFO, staffInfo);
            assertEquals(expectedStaffOutput, actualStaffOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintAddScheduleMessage() {
        String expectedScheduleOutput = "Added appointment with Appointment Id: 3" + END_LINE
                + "Patient ID: 1" + END_LINE
                + "Staff ID: 2" + END_LINE
                + "Date/Time: 11 Dec 21 1400HRS" + END_LINE;
        String appointmentInfo = "Appointment Id: 3" + END_LINE
                + "Patient ID: 1" + END_LINE
                + "Staff ID: 2" + END_LINE
                + "Date/Time: 11 Dec 21 1400HRS" + END_LINE;

        try {
            String actualScheduleOutput = Ui.getAddMessage(ViewType.SCHEDULER, appointmentInfo);
            assertEquals(expectedScheduleOutput, actualScheduleOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintDeletePatientMessage() {
        int id = 2;
        String expectedPatientOutput = "Patient with id 2 deleted from system." + END_LINE;

        try {
            String actualPatientOutput = Ui.getDeleteMessage(id, ViewType.PATIENT_INFO);
            assertEquals(actualPatientOutput, expectedPatientOutput);

        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintDeleteStaffMessage() {
        int id = 2;
        String expectedStaffOutput = "Staff with id 2 deleted from system." + END_LINE;

        try {
            String actualStaffOutput = Ui.getDeleteMessage(id, ViewType.MEDICAL_STAFF_INFO);
            assertEquals(actualStaffOutput, expectedStaffOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintDeleteScheduleMessage() {
        int id = 2;
        String expectedScheduleOutput = "Appointment with id 2 deleted from system." + END_LINE;

        try {
            String actualScheduleOutput = Ui.getDeleteMessage(id, ViewType.SCHEDULER);
            assertEquals(actualScheduleOutput, expectedScheduleOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testEditPatientMessage() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");
        patientList.addPerson(patient);
        patient.setName("Bob");
        String patientInfo = patient.toString();
        String expectedOutput =
                "The information of patient with ID 1 has been edited to:" + END_LINE + END_LINE
                + "Patient ID: 1 " + END_LINE
                + "IC: " + END_LINE
                + "Name: Bob" + END_LINE
                + "H/P: " + END_LINE
                + "Email: John@gmail.com" + END_LINE
                + "Address: " + END_LINE;
        int patientId = 1;
        try {
            String actualOutput = Ui.getEditMessage(patientId, patientInfo, ViewType.PATIENT_INFO);
            assertEquals(actualOutput, expectedOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testEditStaffMessage() {
        Staff staff = new Staff();
        staff.setName("Dr Tan");
        staff.setIcNumber("S1459203K");
        staff.setEmailAddress("tan@gmail.com");
        staff.setPhoneNumber("91238456");
        staff.setResidentialAddress("25 Dover Road");
        MedicalStaffList staffList = new MedicalStaffList();
        staffList.addPerson(staff);
        String staffInfo = staff.toString();
        String expectedOutput =
                "The information of staff with ID 1 has been edited to:" + END_LINE + END_LINE
                        + "Staff ID: 1 " + END_LINE
                        + "IC: S1459203K" + END_LINE
                        + "Name: Dr Tan" + END_LINE
                        + "H/P: 91238456" + END_LINE
                        + "Email: tan@gmail.com" + END_LINE
                        + "Address: 25 Dover Road" + END_LINE;
        int staffId = 1;
        try {
            String actualOutput = Ui.getEditMessage(staffId, staffInfo, ViewType.MEDICAL_STAFF_INFO);
            assertEquals(actualOutput, expectedOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testEditAppointmentMessage() {
        Patient patient = new Patient();
        patient.setId(2);

        Staff staff = new Staff();
        staff.setId(1);

        Appointment appointment = new Appointment();
        appointment.setPatientId(2);
        appointment.setMedicalStaffId(1);
        appointment.setId(1);
        appointment.setDateTimeCode(27262080); //1 November 2021, 0800
        Scheduler scheduler = new Scheduler();

        scheduler.addPatient(patient);
        scheduler.addStaff(staff);

        String expectedOutput = "The information of appointment with ID 1 has been edited to:" + END_LINE + END_LINE
                + "Appointment Id: 1" + END_LINE
                + "Patient ID: 2" + END_LINE
                + "Staff ID: 1" + END_LINE
                + "Date/Time: 01 Nov 21 0800HRS" + END_LINE
                + END_LINE;
        try {
            scheduler.addAppointment(appointment);
            String actualOutput = Ui.getEditMessage(1, appointment.toString(),ViewType.SCHEDULER);
            assertEquals(expectedOutput, actualOutput);
        } catch (MedBotException e) {
            assertEquals("Incomplete appointment.", e.getMessage());
        }
    }

    @Test
    public void testGetPatientInfoMessage() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");
        patientList.addPerson(patient);
        String patientInfo = patient.toString();
        String expectedOutput = "Here's the requested patient:" + END_LINE + END_LINE
                + "Patient ID: 1 " + END_LINE
                + "IC: " + END_LINE
                + "Name: John Doe" + END_LINE
                + "H/P: " + END_LINE
                + "Email: John@gmail.com" + END_LINE
                + "Address: " + END_LINE;
        assertEquals(expectedOutput, PatientUi.getPatientInfo(patientInfo));
    }

    @Test
    public void testGetStaffInfoMessage() {
        MedicalStaffList staffList = new MedicalStaffList();
        Staff staff = new Staff();
        staff.setName("John Doe");
        staff.setEmailAddress("John@gmail.com");
        staffList.addPerson(staff);
        String staffInfo = staff.toString();
        String expectedOutput = "Here's the requested staff:" + END_LINE + END_LINE
                + "Staff ID: 1 " + END_LINE
                + "IC: " + END_LINE
                + "Name: John Doe" + END_LINE
                + "H/P: " + END_LINE
                + "Email: John@gmail.com" + END_LINE
                + "Address: " + END_LINE;
        assertEquals(expectedOutput, StaffUi.getStaffInfo(staffInfo));
    }

    @Test
    public void testGetAllPatientsMessage() {
        PatientList patientList = new PatientList();
        Patient patient1 = new Patient();
        patient1.setName("John Doe");
        patient1.setEmailAddress("John@gmail.com");
        patientList.addPerson(patient1);
        Patient patient2 = new Patient();
        patient2.setName("Bob");
        patient2.setPhoneNumber("88889999");
        patientList.addPerson(patient2);
        String expectedOutput =
            "Here is a list of all patients:" + END_LINE
            + "For full details of each patient, please use the command \"view PATIENT_ID\"" + END_LINE
            + " ----------------------------------------------------------------------------------------------------- "
            + END_LINE
            + " |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | "
            + END_LINE
            + " ----------------------------------------------------------------------------------------------------- "
            + END_LINE
            + " | 1    |           | John Doe             |           | John@gmail.com       |                      | "
            + END_LINE
            + " | 2    |           | Bob                  | 88889999  |                      |                      | "
            + END_LINE
            + " ----------------------------------------------------------------------------------------------------- "
            + END_LINE;

        assertEquals(expectedOutput, PatientUi.getAllPatientsString(patientList.listPersons(false)));
    }

    @Test
    public void testGetAllStaffsMessage() {
        MedicalStaffList staffList = new MedicalStaffList();
        Staff staff1 = new Staff();
        staff1.setName("John Doe");
        staff1.setEmailAddress("John@gmail.com");
        staffList.addPerson(staff1);
        Staff staff2 = new Staff();
        staff2.setName("Bob");
        staff2.setPhoneNumber("88889999");
        staffList.addPerson(staff2);
        String expectedOutput =
            "Here is a list of all staffs:" + END_LINE
                    + "For full details of each staff, please use the command \"view STAFF_ID\"" + END_LINE
                    + " -------------------------------------------------------------"
                    + "---------------------------------------- "
                    + END_LINE
                    + " |  ID  | IC Number |         Name         | Phone No. |    "
                    + "    Email         |       Address        | "
                    + END_LINE
                    + " --------------------------------------------------------------"
                    + "--------------------------------------- "
                    + END_LINE
                    + " | 1    |           | John Doe             |           | "
                    + "John@gmail.com       |                      | "
                    + END_LINE
                    + " | 2    |           | Bob                  | 88889999  |      "
                    + "                |                      | "
                    + END_LINE
                    + " ------------------------------------------------------"
                    + "----------------------------------------------- "
                    + END_LINE;

        assertEquals(expectedOutput, StaffUi.getAllStaffsString(staffList.listPersons(false)));
    }

    @Test
    public void testGetPatientCommandListMessage() {

        String expectedOutput = "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "switch" + END_LINE + "exit" + END_LINE
                + "archive" + END_LINE + "unarchive" + END_LINE + "get view" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes. For " + END_LINE
                + "examples of the expected output, please refer to the actual user guide." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getCommandList(ViewType.PATIENT_INFO));
        } catch (MedBotException mbe) {
            fail();
        }
    }

    @Test
    public void testGetStaffCommandListMessage() {

        String expectedOutput = "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "switch" + END_LINE + "exit" + END_LINE
                + "archive" + END_LINE + "unarchive" + END_LINE + "get view" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes. For " + END_LINE
                + "examples of the expected output, please refer to the actual user guide." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getCommandList(ViewType.MEDICAL_STAFF_INFO));
        } catch (MedBotException mbe) {
            fail();
        }
    }

    @Test
    public void testGetSchedulerCommandListMessage() {

        String expectedOutput = "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "get view" + END_LINE + "switch" + END_LINE + "exit"
                + END_LINE + END_LINE + "To obtain more information on each command and their respective required "
                + "inputs, type:" + END_LINE + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes. For " + END_LINE
                + "examples of the expected output, please refer to the actual user guide." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getCommandList(ViewType.SCHEDULER));
        } catch (MedBotException mbe) {
            fail();
        }
    }

    @Test
    public void testGetFindCommandPatientMessage() {
        PatientList patientList = new PatientList();
        Patient patient1 = new Patient();
        patient1.setName("John Doe");
        patient1.setEmailAddress("John@gmail.com");
        patientList.addPerson(patient1);
        Patient patient2 = new Patient();
        patient2.setName("Bob");
        patient2.setPhoneNumber("88889999");
        patientList.addPerson(patient2);
        String expectedOutput =
            "Here is a list of all patients:" + END_LINE
            + "For full details of each patient, please use the command \"view PATIENT_ID\"" + END_LINE
            + " ----------------------------------------------------------------------------------------------------- "
            + END_LINE
            + " |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | "
            + END_LINE
            + " ----------------------------------------------------------------------------------------------------- "
            + END_LINE
            + " | 2    |           | Bob                  | 88889999  |                      |                      | "
            + END_LINE
            + " ----------------------------------------------------------------------------------------------------- "
            + END_LINE;

        String[] parameters = {"n/Bob"};
        List<String> params = patientList.findPersons(parameters);
        try {
            assertEquals(Ui.getFindPersonsMessage(params, ViewType.PATIENT_INFO), expectedOutput);
        } catch (MedBotException e) {
            assertEquals("There is no person with such attributes in this list.", e.getMessage());
        }
    }

    @Test
    public void testGetFindCommandStaffMessage() {
        MedicalStaffList staffList = new MedicalStaffList();
        Staff staff1 = new Staff();
        staff1.setName("John Doe");
        staff1.setEmailAddress("John@gmail.com");
        staffList.addPerson(staff1);
        Staff staff2 = new Staff();
        staff2.setName("Bob");
        staff2.setPhoneNumber("88889999");
        staffList.addPerson(staff2);
        String expectedOutput =
                "Here is a list of all staffs:" + END_LINE
                        + "For full details of each staff, please use the command \"view STAFF_ID\"" + END_LINE
                        + " -----------------------------------------"
                        + "------------------------------------------------------------ "
                        + END_LINE
                        + " |  ID  | IC Number |         Name         "
                        + "| Phone No. |        Email         |       Address        | "
                        + END_LINE
                        + " --------------------------------------------------"
                        + "--------------------------------------------------- "
                        + END_LINE
                        + " | 2    |           | Bob                  | 88889999  "
                        + "|                      |                      | "
                        + END_LINE
                        + " -----------------------------------------------"
                        + "------------------------------------------------------ "
                        + END_LINE;

        String[] parameters = {"n/Bob"};
        List<String> params = staffList.findPersons(parameters);
        try {
            assertEquals(Ui.getFindPersonsMessage(params, ViewType.MEDICAL_STAFF_INFO), expectedOutput);
        } catch (MedBotException e) {
            assertEquals("There is no person with such attributes in this list.", e.getMessage());
        }
    }

    @Test
    public void testGetArchivePatientMessage() {
        int patientId = 1;
        String expectedOutput = "The patient with ID: " + patientId + " is archived successfully." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getArchivePersonMessage(patientId, ViewType.PATIENT_INFO));
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testGetUnarchiveStaffMessage() {
        int staffId = 1;
        String expectedOutput = "The staff with ID: " + staffId + " is unarchived successfully." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getUnarchivePersonMessage(staffId, ViewType.MEDICAL_STAFF_INFO));
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }
}