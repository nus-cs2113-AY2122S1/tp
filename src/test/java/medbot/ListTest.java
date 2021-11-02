package medbot;

import medbot.exceptions.MedBotException;
import medbot.list.PatientList;
import medbot.person.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static medbot.ui.Ui.END_LINE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListTest {

    private PatientList patientList = new PatientList();
    private static final String END_LINE = System.lineSeparator();

    @BeforeEach
    public void init() {
        Patient patient = new Patient();
        patient.setName("Adam");
        patient.setIcNumber("S1234567A");
        patient.setPhoneNumber("98765432");
        patient.setEmailAddress("adam@example.com");
        patient.setResidentialAddress("ABC Street 123");
        try {
            patientList.addPerson(patient);
        } catch (Exception e) {
            //Should not have exceptions thrown
            e.printStackTrace();
        }
    }

    @Test
    public void testAddPerson_personIcAlreadyExists_ExceptionThrown() {

        Patient patientWithSameId = new Patient();
        patientWithSameId.setName("Bob");
        patientWithSameId.setIcNumber("S1234567A");
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            patientList.addPerson(patientWithSameId);
        });
        String expectedMessage = "The patient with IC S1234567A is already in the record." + END_LINE;
        String actualMessage = medBotException.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testAddPerson_personIcDoesNotExist_Success() {

        Patient patientWithDiffId = new Patient();
        patientWithDiffId.setName("Charlie");
        patientWithDiffId.setIcNumber("S1231231B");
        int actualPatientId = 0;
        int expectedPatientId = 2;
        try {
            actualPatientId = patientList.addPerson(patientWithDiffId);
        } catch (Exception e) {
            //Should not have exceptions thrown
            e.printStackTrace();
        }
        //Should be second patient in the list
        assertEquals(expectedPatientId, actualPatientId);
    }

    @Test
    public void testGetPersonInfo_personDoesNotExist_ExceptionThrown() {

        int nonExistentPatientId = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            patientList.getPersonInfo(nonExistentPatientId);
        });

        String expectedMessage = "No Patient with ID " + nonExistentPatientId + " found." + END_LINE;
        String actualMessage = medBotException.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetPersonInfo_personExists_Success() {

        int existingPatientId = 1;
        String actualMessage = "";
        try {
            actualMessage = patientList.getPersonInfo(existingPatientId);
        } catch (Exception e) {
            //No exception should be thrown
            e.printStackTrace();
        }

        String name = "Adam";
        String icNumber = "S1234567A";
        String phoneNumber = "98765432";
        String emailAddress = "adam@example.com";
        String residentialAddress = "ABC Street 123";

        String expectedMessage = "Patient ID: 1" + END_LINE
                + "IC: " + icNumber + END_LINE
                + "Name: " + name + END_LINE
                + "H/P: " + phoneNumber + END_LINE
                + "Email: " + emailAddress + END_LINE
                + "Address: " + residentialAddress;

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testHidePerson_personDoesNotExist_ExceptionThrown() {

        int nonExistentPatientId = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            patientList.hidePerson(nonExistentPatientId);
        });

        String expectedMessage = "No Patient with ID " + nonExistentPatientId + " found." + END_LINE;
        String actualMessage = medBotException.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testHidePerson_personExistsButAlreadyHidden_ExceptionThrown() {

        int existingPatientId = 1;
        try {
            patientList.hidePerson(existingPatientId);
        } catch (Exception e) {
            //No exception should be thrown
            e.printStackTrace();
        }

        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            patientList.hidePerson(existingPatientId);
        });

        String expectedMessage = "The patient with ID " + existingPatientId + " is already hidden." + END_LINE;
        String actualMessage = medBotException.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    public void testShowPerson_personDoesNotExist_ExceptionThrown() {

        int nonExistentPatientId = 2;
        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            patientList.showPerson(nonExistentPatientId);
        });

        String expectedMessage = "No Patient with ID " + nonExistentPatientId + " found." + END_LINE;
        String actualMessage = medBotException.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testShowPerson_personExistsButNotHidden_ExceptionThrown() {

        int existingPatientId = 1;

        MedBotException medBotException = assertThrows(MedBotException.class, () -> {
            patientList.showPerson(existingPatientId);
        });

        String expectedMessage = "The patient with ID " + existingPatientId + " is already not-hidden." + END_LINE;
        String actualMessage = medBotException.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testShowPerson_personExistsAndHidden_Success() {

        int existingPatientId = 1;

        //driver code to hide patient first
        try {
            patientList.hidePerson(existingPatientId);
        } catch (Exception e) {
            //No exception should be thrown
            e.printStackTrace();
        }

        try {
            patientList.showPerson(existingPatientId);
        } catch (Exception e) {
            //No exception should be thrown
            e.printStackTrace();
        }

        boolean isHidden = patientList.getPersonIsHidden(existingPatientId);
        assertEquals(false, isHidden);
    }

}
