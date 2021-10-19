package medbot;

import medbot.list.PatientList;
import medbot.person.Patient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    public static final String ENDLINE = System.lineSeparator();

    @Test
    public void testPrintAddPatientMessage() {
        int patientId = 2;
        Ui ui = new Ui();

        String expectedOutput = "Added patient with patient ID: 2" + ENDLINE;
        assertEquals(ui.getAddPatientMessage(patientId), expectedOutput);
    }

    @Test
    public void testPrintDeletePatientMessage() {
        int patientId = 2;
        Ui ui = new Ui();

        String expectedOutput = "Patient with id 2 deleted from system." + ENDLINE;
        assertEquals(ui.getDeletePatientMessage(patientId), expectedOutput);
    }

    @Test
    public void testEditPatientMessage() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");

        patientList.addPerson(patient);
        Ui ui = new Ui();

        patient.setName("Bob");
        String patientInfo = patient.toString();
        String expectedOutput =
                "The information of patient with ID 1 has been edited to:" + ENDLINE + ENDLINE
                + "Patient ID: 1 " + ENDLINE
                + "IC: " + ENDLINE
                + "Name: Bob" + ENDLINE
                + "H/P: " + ENDLINE
                + "Email: John@gmail.com" + ENDLINE
                + "Address: " + ENDLINE;
        int patientId = 1;
        assertEquals(ui.getEditPatientMessage(patientId, patientInfo), expectedOutput);
    }

    @Test
    public void testGetPatientInfoMessage() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");

        patientList.addPerson(patient);
        Ui ui = new Ui();

        String patientInfo = patient.toString();
        String expectedOutput = "Here's the requested patient:" + ENDLINE + ENDLINE
                + "Patient ID: 1 " + ENDLINE
                + "IC: " + ENDLINE
                + "Name: John Doe" + ENDLINE
                + "H/P: " + ENDLINE
                + "Email: John@gmail.com" + ENDLINE
                + "Address: " + ENDLINE;
        assertEquals(ui.getPatientInfo(patientInfo), expectedOutput);
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

        Ui ui = new Ui();

        String expectedOutput =
            "Here is a list of all patients:" + ENDLINE
            + "For full details of each patient, please use the command \"view PATIENT_ID\"" + ENDLINE
            + " ----------------------------------------------------------------------------------------------------- "
            + ENDLINE
            + " |  ID  | IC Number |         Name         | Phone No. |        Email         |       Address        | "
            + ENDLINE
            + " ----------------------------------------------------------------------------------------------------- "
            + ENDLINE
            + " | 1    |           | John Doe             |           | John@gmail.com       |                      | "
            + ENDLINE
            + " | 2    |           | Bob                  | 88889999  |                      |                      | "
            + ENDLINE
            + " ----------------------------------------------------------------------------------------------------- "
            + ENDLINE;

        assertEquals(ui.getAllPatientsString(patientList), expectedOutput);
    }

    @Test
    public void testGetCommandListMessage() {
        Ui ui = new Ui();

        String expectedOutput = "Here are the list of commands:" + ENDLINE + ENDLINE
                + "help" + ENDLINE + "add" + ENDLINE + "list" + ENDLINE + "view" + ENDLINE + "edit" + ENDLINE
                + "find" + ENDLINE + "delete" + ENDLINE + "exit" + ENDLINE + ENDLINE
                + "To obtain more information on each command and their respective required inputs, type:" + ENDLINE
                + "help [COMMAND]" + ENDLINE + ENDLINE
                + "*Note that all commands will remove any '|' inputs for format parsing purposes" + ENDLINE;

        assertEquals(ui.getCommandList(), expectedOutput);
    }


}