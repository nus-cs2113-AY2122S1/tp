package medbot;

import static medbot.Parser.updatePersonalInformation;

import medbot.person.Patient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageTest {

    private static final String INVALID_TEST_DATA = "src/test/data/StorageTest/invalidData.txt";
    private static final String VALID_TEST_DATA = "src/test/data/StorageTest/validData.txt";
    private static final String SAVED_TEST_DATA = "src/test/data/StorageTest/savedData.txt";
    private static final String NON_EXISTENT_FILE_NAME = "NON_EXISTENT_FILE.txt";
    private static final String ERROR_INVALID_STORAGE_LINE_INSTRUCTION = "\n\n"
            + "Please decide if you wish to:" + "\n"
            + "1. Enter 'exit' to exit Medbot to correct the storage file" + "\n"
            + "2. Enter other valid commands to OVERWRITE all invalid data!" + "\n";

    @Test
    public void testConstructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void testConstructor_invalidFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Storage(NON_EXISTENT_FILE_NAME));
    }

    @Test
    public void testLoadStorage_partialInvalidFormat_exceptionThrown() throws MedBotException, FileNotFoundException {

        Storage storage = new Storage(INVALID_TEST_DATA);
        PatientList actualPl = new PatientList();
        PatientList expectedPl = getTestPatientList();
        String expectedLoadStorageErrorMessage =
                "Error: Line 8 of storage/data.txt is invalid!\n"
                        + "Error: Line 9 of storage/data.txt is invalid!\n"
                        + "Error: Line 10 of storage/data.txt is invalid!\n"
                        + "Error: Line 11 of storage/data.txt is invalid!\n"
                        + ERROR_INVALID_STORAGE_LINE_INSTRUCTION;

        //Test for both correct error message and correct loading of storage
        assertEquals(expectedLoadStorageErrorMessage, storage.loadStorage(actualPl));
        assertEquals(expectedPl.getStorageString(), actualPl.getStorageString());
    }

    // expected: patients added through the program
    // actual: patients added through loading storage
    @Test
    public void testLoadStorage_validFormat() throws FileNotFoundException, MedBotException {
        Storage storage = new Storage(VALID_TEST_DATA);
        PatientList actualPl = new PatientList();

        storage.loadStorage(actualPl);
        PatientList expectedPl = getTestPatientList();

        // ensure storage data is properly loaded into the program
        assertEquals(expectedPl.getStorageString(), actualPl.getStorageString());
    }

    @Test
    public void testSaveData() throws MedBotException {
        PatientList patientList = getTestPatientList();
        Storage storage = new Storage(SAVED_TEST_DATA);
        storage.saveData(patientList);

        try {
            String actualFileString = scanFileToString(SAVED_TEST_DATA);
            String expectedFileString = scanFileToString(VALID_TEST_DATA);
            assertEquals(expectedFileString, actualFileString);

            deleteSavedData();

        } catch (FileNotFoundException e) {
            throw new MedBotException("FILE ERROR");
        }
    }

    /**
     * Create a test PatientList containing Patient objects.
     *
     * @return a test PatientList
     * @throws MedBotException if any patient instantiation fails
     */
    private PatientList getTestPatientList() throws MedBotException {
        PatientList testPatientList = new PatientList();
        Patient patient1 = createPatient(1, "S7912345A", "Doge",
                "91234567", "doge@mail.com",
                "Doge Town 2nd Street");
        Patient patient2 = createPatient(2, "", "Cheems",
                "", "cheems@mail.com",
                "Doge County");
        Patient patient3 = createPatient(4, "S2312345A", "",
                "81234567", "cheems@mail.com",
                "Arken Street");
        Patient patient4 = createPatient(6, "F9123456X", "Emma",
                "", "emma@mail.com",
                "Greensville County");
        Patient patient5 = createPatient(7, "F3232145B", "Norman",
                "", "norman@mail.com",
                "Petalburg City");
        Patient patient6 = createPatient(9, "S9145645C", "Arthur",
                "67654321", "",
                "Evergrande District");
        Patient patient7 = createPatient(10, "T0123463X", "William",
                "", "will@mail.com",
                "Doge County");

        Patient[] patients = {patient1, patient2, patient3, patient4, patient5, patient6, patient7};

        for (Patient p : patients) {
            testPatientList.setLastId(p.getPatientId());
            testPatientList.addPatient(p);
        }

        return testPatientList;
    }

    /**
     * Instantiate a patient object with the given parameters.
     *
     * @param id                 patientId
     * @param icNumber           I/C number of patient
     * @param name               name of patient
     * @param phoneNumber        phone number of patient
     * @param emailAddress       email address of patient
     * @param residentialAddress residential address of patient
     * @return a patient object
     * @throws MedBotException if fail to update any patient parameters
     */
    private Patient createPatient(int id, String icNumber, String name,
                                  String phoneNumber, String emailAddress,
                                  String residentialAddress)
            throws MedBotException {

        Patient patient = new Patient();
        patient.setPatientId(id);
        String[] parameterPrefixes = {"i/", "n/", "p/", "e/", "a/"};
        String[] parameters = {icNumber, name, phoneNumber, emailAddress, residentialAddress};
        ArrayList<String> prefixPlusPersonParameters = new ArrayList<>();

        for (int i = 0; i < parameterPrefixes.length; i++) {
            if (parameters[i].isBlank()) {
                continue;
            }
            String prefixPlusPersonParameter = parameterPrefixes[i] + parameters[i];
            prefixPlusPersonParameters.add(prefixPlusPersonParameter);
        }

        for (String prefixPlusPersonParameter : prefixPlusPersonParameters) {
            updatePersonalInformation(patient, prefixPlusPersonParameter);
        }

        return patient;
    }


    /**
     * Scans a text file and converts it to a String.
     *
     * @param filePath path of the text file
     * @return String of the contents of the text file
     * @throws FileNotFoundException if text file does not exist of path invalid
     */
    private String scanFileToString(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        String output = "";

        while (s.hasNext()) {
            output += s.nextLine();
        }
        return output;
    }

    /**
     * Delete saved test data text file from the JUNIT testing.
     */
    private void deleteSavedData() {
        File savedDataFile = new File(SAVED_TEST_DATA);
        savedDataFile.delete();
    }

}