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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class UiTest {
    public static final String END_LINE = System.lineSeparator();

    @Test
    public void testPrintAddPatientMessage_viewTypeExists_Success() {
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
            String actualPatientOutput = Ui.getAddMessage(patientInfo, ViewType.PATIENT_INFO);
            assertEquals(expectedPatientOutput, actualPatientOutput);

        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintAddStaffMessage_viewTypeExists_Success() {
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
            String actualStaffOutput = Ui.getAddMessage(staffInfo, ViewType.MEDICAL_STAFF_INFO);
            assertEquals(expectedStaffOutput, actualStaffOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintAddScheduleMessage_viewTypeExists_Success() {
        String expectedScheduleOutput = "Added appointment with Appointment Id: 3" + END_LINE
                + "Patient ID: 1" + END_LINE
                + "Staff ID: 2" + END_LINE
                + "Date/Time: 11 Dec 21 1400HRS" + END_LINE;
        String appointmentInfo = "Appointment Id: 3" + END_LINE
                + "Patient ID: 1" + END_LINE
                + "Staff ID: 2" + END_LINE
                + "Date/Time: 11 Dec 21 1400HRS" + END_LINE;

        try {
            String actualScheduleOutput = Ui.getAddMessage(appointmentInfo, ViewType.SCHEDULER);
            assertEquals(expectedScheduleOutput, actualScheduleOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintDeletePersonMessage_personDoesNotExist_Fail() {
        MedicalStaffList staffList = new MedicalStaffList();

        int id = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            staffList.deletePerson(id);
        });

        String expectedOutput = "No staff with ID 2 found." + END_LINE;

        assertEquals(expectedOutput, medBotException.getMessage());
    }

    @Test
    public void testPrintDeletePatientMessage_patientExists_Success() {
        int id = 2;
        String expectedPatientOutput = "Patient with ID 2 deleted from system." + END_LINE;

        try {
            String actualPatientOutput = Ui.getDeleteMessage(id, ViewType.PATIENT_INFO);
            assertEquals(actualPatientOutput, expectedPatientOutput);

        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintDeleteStaffMessage_staffExists_Success() {
        int id = 2;
        String expectedStaffOutput = "Staff with ID 2 deleted from system." + END_LINE;

        try {
            String actualStaffOutput = Ui.getDeleteMessage(id, ViewType.MEDICAL_STAFF_INFO);
            assertEquals(actualStaffOutput, expectedStaffOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testPrintDeleteScheduleMessage_scheduleDoesNotExist_Fail() {
        Scheduler scheduler = new Scheduler();

        int id = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            scheduler.deleteAppointment(id);
        });

        String expectedOutput = "No appointment with ID 2 found.";

        assertEquals(expectedOutput, medBotException.getMessage());
    }

    @Test
    public void testPrintDeleteScheduleMessage_scheduleExists_Success() {
        int id = 2;
        String expectedScheduleOutput = "Appointment with ID 2 deleted from system." + END_LINE;

        try {
            String actualScheduleOutput = Ui.getDeleteMessage(id, ViewType.SCHEDULER);
            assertEquals(actualScheduleOutput, expectedScheduleOutput);
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testEditPersonMessage_personDoesNotExist_Fail() {
        MedicalStaffList staffList = new MedicalStaffList();

        int id = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            staffList.editPerson(id, new Staff());
        });

        String expectedOutput = "No staff with ID 2 found." + END_LINE;

        assertEquals(expectedOutput, medBotException.getMessage());
    }

    @Test
    public void testEditPatientMessage_patientExists_Success() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");
        String expectedOutput =
                "The information of the patient with ID 1 has been edited to:" + END_LINE + END_LINE
                + "Patient ID: 1" + END_LINE
                + "IC: " + END_LINE
                + "Name: Bob" + END_LINE
                + "H/P: " + END_LINE
                + "Email: John@gmail.com" + END_LINE
                + "Address: " + END_LINE;
        int patientId = 1;
        try {
            patientList.addPerson(patient);
            patient.setName("Bob");
            String patientInfo = patient.toString();
            String actualOutput = Ui.getEditMessage(patientId, patientInfo, ViewType.PATIENT_INFO);
            assertEquals(actualOutput, expectedOutput);
        } catch (MedBotException e) {
            fail();
        }
    }

    @Test
    public void testEditStaffMessage_staffExists_Success() {
        Staff staff = new Staff();
        staff.setName("Dr Tan");
        staff.setIcNumber("S1459203K");
        staff.setEmailAddress("tan@gmail.com");
        staff.setPhoneNumber("91238456");
        staff.setResidentialAddress("25 Dover Road");
        MedicalStaffList staffList = new MedicalStaffList();
        String expectedOutput =
                "The information of the staff with ID 1 has been edited to:" + END_LINE + END_LINE
                        + "Staff ID: 1 " + END_LINE
                        + "IC: S1459203K" + END_LINE
                        + "Name: Dr Tan" + END_LINE
                        + "H/P: 91238456" + END_LINE
                        + "Email: tan@gmail.com" + END_LINE
                        + "Address: 25 Dover Road" + END_LINE;
        int staffId = 1;
        try {
            staffList.addPerson(staff);
            String staffInfo = staff.toString();
            String actualOutput = Ui.getEditMessage(staffId, staffInfo, ViewType.MEDICAL_STAFF_INFO);
            assertEquals(actualOutput, expectedOutput);
        } catch (MedBotException e) {
            fail();
        }
    }

    @Test
    public void testEditAppointmentMessage_appointmentDoesNotExist_Fail() {
        Scheduler scheduler = new Scheduler();

        int id = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            scheduler.deleteAppointment(id);
        });

        String expectedOutput = "No appointment with ID 2 found.";

        assertEquals(expectedOutput, medBotException.getMessage());
    }

    @Test
    public void testEditAppointmentMessage_appointmentExists_Success() {
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

        String expectedOutput = "The information of appointment with ID 1 has been edited to:" + END_LINE + END_LINE
                + "Appointment ID: 1" + END_LINE
                + "Patient ID: 2" + END_LINE
                + "Staff ID: 1" + END_LINE
                + "Date/Time: 01 Nov 21 0800HRS" + END_LINE
                + END_LINE;
        try {
            scheduler.addPatient(patient);
            scheduler.addStaff(staff);
            scheduler.addAppointment(appointment);
            String actualOutput = Ui.getEditMessage(1, appointment.toString(),ViewType.SCHEDULER);
            assertEquals(expectedOutput, actualOutput);
        } catch (MedBotException e) {
            fail();
        }
    }

    @Test
    public void testGetPersonInfoMessage_personDoesNotExist_Fail() {
        MedicalStaffList staffList = new MedicalStaffList();

        int id = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            staffList.getPersonInfo(id);
        });

        String expectedOutput = "No staff with ID 2 found." + END_LINE;

        assertEquals(expectedOutput, medBotException.getMessage());
    }

    @Test
    public void testGetPatientInfoMessage_patientExists_Success() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");
        String expectedOutput = "Here's the requested patient information:" + END_LINE + END_LINE
                + "Patient ID: 1" + END_LINE
                + "IC: " + END_LINE
                + "Name: John Doe" + END_LINE
                + "H/P: " + END_LINE
                + "Email: John@gmail.com" + END_LINE
                + "Address: " + END_LINE;
        try {
            patientList.addPerson(patient);
            String patientInfo = patient.toString();
            assertEquals(expectedOutput, PatientUi.getViewPatientMessage(patientInfo));

        } catch (MedBotException e) {
            fail();
        }
    }

    @Test
    public void testGetStaffInfoMessage_staffExists_Success() {
        MedicalStaffList staffList = new MedicalStaffList();
        Staff staff = new Staff();
        staff.setName("John Doe");
        staff.setEmailAddress("John@gmail.com");

        String expectedOutput = "Here's the requested staff information:" + END_LINE + END_LINE
                + "Staff ID: 1 " + END_LINE
                + "IC: " + END_LINE
                + "Name: John Doe" + END_LINE
                + "H/P: " + END_LINE
                + "Email: John@gmail.com" + END_LINE
                + "Address: " + END_LINE;
        try {
            staffList.addPerson(staff);
            String staffInfo = staff.toString();
            assertEquals(expectedOutput, StaffUi.getViewStaffMessage(staffInfo));
        } catch (MedBotException e) {
            fail();
        }
    }

    @Test
    public void testGetAllPatientsMessage() {
        PatientList patientList = new PatientList();
        Patient patient1 = new Patient();
        patient1.setName("John Doe");
        patient1.setEmailAddress("John@gmail.com");
        Patient patient2 = new Patient();
        patient2.setName("Bob");
        patient2.setPhoneNumber("88889999");


        String expectedOutput =
            "Here is a list of all not-hidden patients:" + END_LINE
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
        try {
            patientList.addPerson(patient1);
            patientList.addPerson(patient2);
            assertEquals(expectedOutput, PatientUi.getAllPatientsString(patientList.listPersons(false),
                    false));
        } catch (MedBotException e) {
            fail();
        }
    }

    @Test
    public void testGetAllStaffsMessage() {
        MedicalStaffList staffList = new MedicalStaffList();
        Staff staff1 = new Staff();
        staff1.setName("John Doe");
        staff1.setEmailAddress("John@gmail.com");

        Staff staff2 = new Staff();
        staff2.setName("Bob");
        staff2.setPhoneNumber("88889999");
        String expectedOutput =
            "Here is a list of all not-hidden staffs:" + END_LINE
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
        try {
            staffList.addPerson(staff1);
            staffList.addPerson(staff2);
            assertEquals(expectedOutput, StaffUi.getAllStaffsString(staffList.listPersons(false),
                    false));

        } catch (MedBotException e) {
            fail();
        }
    }

    @Test
    public void testGetPatientCommandListMessage() {

        String expectedOutput = "Here is the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE
                + "add" + END_LINE
                + "delete" + END_LINE
                + "edit" + END_LINE
                + "view" + END_LINE
                + "list" + END_LINE
                + "find" + END_LINE
                + "hide" + END_LINE
                + "show" + END_LINE
                + "switch" + END_LINE
                + "get view" + END_LINE
                + "exit" + END_LINE + END_LINE
                + "To view more information about each command and their respective command formats, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes." + END_LINE
                + "For expected output examples, please refer to the User Guide." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getCommandList(ViewType.PATIENT_INFO));
        } catch (MedBotException mbe) {
            fail();
        }
    }

    @Test
    public void testGetStaffCommandListMessage() {

        String expectedOutput = "Here is the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE
                + "add" + END_LINE
                + "delete" + END_LINE
                + "edit" + END_LINE
                + "view" + END_LINE
                + "list" + END_LINE
                + "find" + END_LINE
                + "hide" + END_LINE
                + "show" + END_LINE
                + "switch" + END_LINE
                + "get view" + END_LINE
                + "exit" + END_LINE + END_LINE
                + "To view more information about each command and their respective command formats, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes." + END_LINE
                + "For expected output examples, please refer to the User Guide." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getCommandList(ViewType.MEDICAL_STAFF_INFO));
        } catch (MedBotException mbe) {
            fail();
        }
    }

    @Test
    public void testGetSchedulerCommandListMessage() {

        String expectedOutput = "Here is the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE
                + "add" + END_LINE
                + "delete" + END_LINE
                + "edit" + END_LINE
                + "view" + END_LINE
                + "list" + END_LINE
                + "find" + END_LINE
                + "switch" + END_LINE
                + "get view" + END_LINE
                + "exit" + END_LINE + END_LINE
                + "To view more information about each command and their respective command formats, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes." + END_LINE
                + "For expected output examples, please refer to the actual user guide." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getCommandList(ViewType.SCHEDULER));
        } catch (MedBotException mbe) {
            fail("Can't get the command list");
        }
    }

    @Test
    public void testGetFindCommandPatientMessage() {
        PatientList patientList = new PatientList();
        Patient patient1 = new Patient();
        patient1.setName("John Doe");
        patient1.setEmailAddress("John@gmail.com");
        Patient patient2 = new Patient();
        patient2.setName("Bob");
        patient2.setPhoneNumber("88889999");
        String expectedOutput =
            "Here is a list of matched patients:" + END_LINE
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

        try {
            patientList.addPerson(patient1);
            patientList.addPerson(patient2);
            List<String> params = patientList.findPersons(parameters);
            assertEquals(Ui.getFindPersonsMessage(params, ViewType.PATIENT_INFO), expectedOutput);

        } catch (MedBotException e) {
            fail("Can't add the person to the list");
        }
        try {
            String[] newParameters = {"n/Bob", "z/test"};
            patientList.findPersons(newParameters);
        } catch (MedBotException e) {
            assertEquals("The specifier z/ is invalid.", e.getMessage());
        }
    }

    @Test
    public void testGetFindCommandStaffMessage() {
        MedicalStaffList staffList = new MedicalStaffList();
        Staff staff1 = new Staff();
        staff1.setName("John Doe");
        staff1.setEmailAddress("John@gmail.com");
        Staff staff2 = new Staff();
        staff2.setName("Bob");
        staff2.setPhoneNumber("88889999");
        String expectedOutput =
                "Here is a list of matched staff:" + END_LINE
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
        try {
            staffList.addPerson(staff1);
            staffList.addPerson(staff2);
            List<String> params = staffList.findPersons(parameters);
            assertEquals(Ui.getFindPersonsMessage(params, ViewType.MEDICAL_STAFF_INFO), expectedOutput);
        } catch (MedBotException e) {
            fail();
        }

        try {
            String[] newParameters = {"n/Bob", "z/test"};
            staffList.findPersons(newParameters);
        } catch (MedBotException e) {
            assertEquals("The specifier z/ is invalid.", e.getMessage());
        }
    }

    @Test
    public void testGetHidePatientMessage() {
        int patientId = 1;
        String expectedOutput = "The patient with ID " + patientId + " is now hidden." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getHidePersonMessage(patientId, ViewType.PATIENT_INFO));
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }

    @Test
    public void testGetShowStaffMessage() {
        int staffId = 1;
        String expectedOutput = "The staff with ID " + staffId + " is now not hidden." + END_LINE;

        try {
            assertEquals(expectedOutput, Ui.getShowPersonMessage(staffId, ViewType.MEDICAL_STAFF_INFO));
        } catch (MedBotException e) {
            assertEquals("Cannot identify the current view type" + END_LINE, e.getMessage());
        }
    }
}