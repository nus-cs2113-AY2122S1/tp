package medbot;

import medbot.person.Patient;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    @Test
    public void testPrintAddPatientMessage() {
        int patientId = 2;
        Ui ui = new Ui();

        String expectedOutput = "Added patient with patient ID: 2";
        assertEquals(ui.getAddPatientMessage(patientId), expectedOutput);
    }

    @Test
    public void testPrintDeletePatientMessage() {
        int patientId = 2;
        Ui ui = new Ui();

        String expectedOutput = "Patient with id 2 deleted from system.";
        assertEquals(ui.getDeletePatientMessage(patientId), expectedOutput);
    }

    @Test
    public void testEditPatientMessage() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");

        patientList.addPatient(patient);
        Ui ui = new Ui();

        patient.setName("Bob");
        String patientInfo = patient.toString();
        String expectedOutput = "The information of patient with ID 1 has been edited to:"
                + System.lineSeparator()
                + "Patient ID: 1 IC:  Name: Bob H/P:  Email: John@gmail.com Address: ";
        int patientId = 1;
        assertEquals(ui.getEditPatientMessage(patientId, patientInfo), expectedOutput);
    }

    @Test
    public void testGetPatientInfoMessage() {
        PatientList patientList = new PatientList();
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setEmailAddress("John@gmail.com");

        patientList.addPatient(patient);
        Ui ui = new Ui();

        String patientInfo = patient.toString();
        String expectedOutput = "Here's the requested patient:" + System.lineSeparator()
                + "Patient ID: 1 IC:  " + "Name: John Doe H/P:  Email: John@gmail.com Address: ";
        int patientId = 1;
        assertEquals(ui.getPatientInfo(patientInfo), expectedOutput);
    }

    @Test
    public void testGetAllPatientsMessage() {
        PatientList patientList = new PatientList();

        Patient patient1 = new Patient();
        patient1.setName("John Doe");
        patient1.setEmailAddress("John@gmail.com");
        patientList.addPatient(patient1);

        Patient patient2 = new Patient();
        patient2.setName("Bob");
        patient2.setPhoneNumber("88889999");
        patientList.addPatient(patient2);

        Ui ui = new Ui();

        String patientOneInfo = patient1.toString();
        String patientTwoInfo = patient2.toString();

        String expectedOutput = "Here is a list of all patients:" + System.lineSeparator() + patientOneInfo
                + System.lineSeparator() + patientTwoInfo + System.lineSeparator();

        assertEquals(ui.getAllPatientsString(patientList), expectedOutput);
    }

    @Test
    public void testGetCommandListMessage() {
        Ui ui = new Ui();

        String expectedOutput = "Here are the list of commands:\n\n"
                + "help\n" + "add\n" + "list\n" + "view\n" + "edit\n" + "delete\n"
                + "exit\n" + "\n"
                + "To obtain more information on each command and their respective required inputs, type:\n"
                + "help [COMMAND]\n\n"
                + "*Note that all commands will remove any '|' inputs for format parsing purposes";

        assertEquals(ui.getCommandList(), expectedOutput);
    }


}