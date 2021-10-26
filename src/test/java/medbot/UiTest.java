package medbot;

import medbot.exceptions.MedBotException;
import medbot.list.PatientList;
import medbot.person.Patient;

import medbot.ui.PatientUi;
import medbot.ui.Ui;
import medbot.utilities.ViewType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class UiTest {
    public static final String END_LINE = System.lineSeparator();

    @Test
    public void testPrintAddPatientMessage() {
        int patientId = 2;
        String expectedOutput = "Added patient with patient ID: 2" + END_LINE;
        try {
            String actualOutput = Ui.getAddMessage(patientId, ViewType.PATIENT_INFO);
            assertEquals(actualOutput, expectedOutput);
        } catch (MedBotException e) {
            assertEquals(e.getMessage(),"Cannot identify the current view type" + END_LINE);
        }
    }

    @Test
    public void testPrintDeletePatientMessage() {
        int patientId = 2;
        String expectedOutput = "Patient with id 2 deleted from system." + END_LINE;
        try {
            String actualOutput = Ui.getDeleteMessage(patientId, ViewType.PATIENT_INFO);
            assertEquals(actualOutput, expectedOutput);
        } catch (MedBotException e) {
            assertEquals(e.getMessage(),"Cannot identify the current view type" + END_LINE);
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
            String actualOutput = Ui.getEditMessage(patientId,patientInfo, ViewType.PATIENT_INFO);
            assertEquals(actualOutput, expectedOutput);
        } catch (MedBotException e) {
            assertEquals(e.getMessage(),"Cannot identify the current view type" + END_LINE);
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
        assertEquals(PatientUi.getPatientInfo(patientInfo), expectedOutput);
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

        assertEquals(PatientUi.getAllPatientsString(patientList.listPersons(false)), expectedOutput);
    }

    @Test
    public void testGetPatientCommandListMessage() {

        String expectedOutput = "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "exit" + END_LINE + "archive" + END_LINE + "unarchive"
                + END_LINE + "get view" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes" + END_LINE;

        try {
            assertEquals(Ui.getCommandList(ViewType.PATIENT_INFO), expectedOutput);
        } catch (MedBotException mbe) {
            fail();
        }
    }

    @Test
    public void testGetStaffCommandListMessage() {

        String expectedOutput = "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "exit" + END_LINE + "archive" + END_LINE + "unarchive"
                + END_LINE + "get view" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes" + END_LINE;

        try {
            assertEquals(Ui.getCommandList(ViewType.MEDICAL_STAFF_INFO), expectedOutput);
        } catch (MedBotException mbe) {
            fail();
        }
    }

    @Test
    public void testGetSchedulerCommandListMessage() {

        String expectedOutput = "Here are the list of commands:" + END_LINE + END_LINE
                + "help" + END_LINE + "add" + END_LINE + "list" + END_LINE + "view" + END_LINE + "edit" + END_LINE
                + "find" + END_LINE + "delete" + END_LINE + "get view" + END_LINE + "exit" + END_LINE + END_LINE
                + "To obtain more information on each command and their respective required inputs, type:" + END_LINE
                + "help [COMMAND]" + END_LINE + END_LINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes" + END_LINE;

        try {
            assertEquals(Ui.getCommandList(ViewType.SCHEDULER), expectedOutput);
        } catch (MedBotException mbe) {
            fail();
        }
    }


}