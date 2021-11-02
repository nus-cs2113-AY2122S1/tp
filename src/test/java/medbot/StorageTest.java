package medbot;

import medbot.exceptions.MedBotException;
import medbot.list.ListItemType;
import medbot.list.MedicalStaffList;
import medbot.list.PatientList;
import medbot.list.SchedulerAppointmentList;
import medbot.person.Patient;
import medbot.person.Person;
import medbot.person.PersonType;
import medbot.person.Staff;
import medbot.storage.AppointmentStorage;
import medbot.storage.PatientStorage;
import medbot.storage.StaffStorage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static medbot.parser.ParserUtils.updateAppointmentInformation;
import static medbot.parser.ParserUtils.updatePersonalInformation;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    private static final String PATIENT_DATA_INVALID = "src/test/data/StorageTest/patientData-invalid.txt";
    private static final String PATIENT_DATA_VALID = "src/test/data/StorageTest/patientData-valid.txt";
    private static final String PATIENT_DATA_SAVED = "src/test/data/StorageTest/patientData-saved.txt";
    private static final String STAFF_DATA_INVALID = "src/test/data/StorageTest/staffData-invalid.txt";
    private static final String STAFF_DATA_VALID = "src/test/data/StorageTest/staffData-valid.txt";
    private static final String STAFF_DATA_SAVED = "src/test/data/StorageTest/staffData-saved.txt";
    private static final String APPOINTMENT_DATA_INVALID = "src/test/data/StorageTest/appointmentData-invalid.txt";
    private static final String APPOINTMENT_DATA_VALID = "src/test/data/StorageTest/appointmentData-valid.txt";
    private static final String APPOINTMENT_DATA_SAVED = "src/test/data/StorageTest/appointmentData-saved.txt";
    private static final String ERROR_INVALID_STORAGE_LINE_INSTRUCTION = "\n\n"
            + "Please decide if you wish to:" + "\n"
            + "1. Enter 'exit' to exit MedBot to correct the storage file" + "\n"
            + "2. Enter other valid commands to OVERWRITE all invalid data!" + "\n";


    @Test
    public void testLoadPatientStorage_partialInvalidFormat()
            throws MedBotException, FileNotFoundException {
        PatientStorage patientStorage = new PatientStorage(PATIENT_DATA_INVALID);
        Scheduler actualScheduler = new Scheduler();

        String actualLoadStorageErrorMessage = patientStorage.loadStorage(ListItemType.PATIENT, actualScheduler);
        String expectedLoadStorageErrorMessage =
                "Error: Line 8 of src/test/data/StorageTest/patientData-invalid.txt is invalid!\n"
                        + "Error: Line 9 of src/test/data/StorageTest/patientData-invalid.txt is invalid!\n"
                        + "Error: Line 10 of src/test/data/StorageTest/patientData-invalid.txt is invalid!\n"
                        + "Error: Line 11 of src/test/data/StorageTest/patientData-invalid.txt is invalid!\n";

        String expectedStorageString = getTestPatientListStorageString();
        String actualStorageString = actualScheduler.getPatientStorageString();

        //Test for both correct error message and correct loading of storage
        assertEquals(expectedLoadStorageErrorMessage, actualLoadStorageErrorMessage);
        assertEquals(expectedStorageString, actualStorageString);
    }

    // expected: patients added through the program
    // actual: patients added through loading storage
    @Test
    public void testLoadPatientStorage_validFormat() throws FileNotFoundException, MedBotException {
        PatientStorage patientStorage = new PatientStorage(PATIENT_DATA_VALID);
        Scheduler actualScheduler = new Scheduler();

        String actualLoadStorageErrorMessage = patientStorage.loadStorage(ListItemType.PATIENT, actualScheduler);
        String expectedLoadStorageErrorMessage = "";

        String expectedStorageString = getTestPatientListStorageString();
        String actualStorageString = actualScheduler.getPatientStorageString();

        //Test for both correct error message and correct loading of storage
        assertEquals(expectedLoadStorageErrorMessage, actualLoadStorageErrorMessage);
        assertEquals(expectedStorageString, actualStorageString);

    }


    @Test
    public void testLoadStaffStorage_partialInvalidFormat() throws MedBotException, FileNotFoundException {
        StaffStorage staffStorage = new StaffStorage(STAFF_DATA_INVALID);
        Scheduler actualScheduler = new Scheduler();

        String actualLoadStorageErrorMessage = staffStorage.loadStorage(ListItemType.STAFF, actualScheduler);
        String expectedLoadStorageErrorMessage =
                "Error: Line 5 of src/test/data/StorageTest/staffData-invalid.txt is invalid!\n"
                        + "Error: Line 6 of src/test/data/StorageTest/staffData-invalid.txt is invalid!\n"
                        + "Error: Line 7 of src/test/data/StorageTest/staffData-invalid.txt is invalid!\n"
                        + "Error: Line 8 of src/test/data/StorageTest/staffData-invalid.txt is invalid!\n";

        String expectedStorageString = getTestStaffListStorageString();
        String actualStorageString = actualScheduler.getStaffStorageString();

        //Test for both correct error message and correct loading of storage
        assertEquals(expectedLoadStorageErrorMessage, actualLoadStorageErrorMessage);
        assertEquals(expectedStorageString, actualStorageString);
    }

    @Test
    public void testLoadStaffStorage_validFormat() throws MedBotException, FileNotFoundException {
        StaffStorage staffStorage = new StaffStorage(STAFF_DATA_VALID);
        Scheduler actualScheduler = new Scheduler();

        String actualLoadStorageErrorMessage = staffStorage.loadStorage(ListItemType.STAFF, actualScheduler);
        String expectedLoadStorageErrorMessage = "";

        String expectedStorageString = getTestStaffListStorageString();
        String actualStorageString = actualScheduler.getStaffStorageString();

        //Test for both correct error message and correct loading of storage
        assertEquals(expectedLoadStorageErrorMessage, actualLoadStorageErrorMessage);
        assertEquals(expectedStorageString, actualStorageString);
    }

    @Test
    public void testLoadAppointmentStorage_partialInvalidFormat() throws MedBotException, FileNotFoundException {
        PatientStorage patientStorage = new PatientStorage(PATIENT_DATA_VALID);
        StaffStorage staffStorage = new StaffStorage(STAFF_DATA_VALID);
        AppointmentStorage appointmentStorage = new AppointmentStorage(APPOINTMENT_DATA_INVALID);
        Scheduler actualScheduler = new Scheduler();

        patientStorage.loadStorage(ListItemType.PATIENT, actualScheduler);
        staffStorage.loadStorage(ListItemType.STAFF, actualScheduler);

        String actualLoadStorageErrorMessage = appointmentStorage.loadStorage(
                ListItemType.APPOINTMENT, actualScheduler);
        String expectedLoadStorageErrorMessage =
                "Error: Line 7 of src/test/data/StorageTest/appointmentData-invalid.txt is invalid!\n"
                        + "Error: Line 8 of src/test/data/StorageTest/appointmentData-invalid.txt is invalid!\n"
                        + "Error: Line 9 of src/test/data/StorageTest/appointmentData-invalid.txt is invalid!\n"
                        + "Error: Line 10 of src/test/data/StorageTest/appointmentData-invalid.txt is invalid!\n"
                        + "Error: Line 11 of src/test/data/StorageTest/appointmentData-invalid.txt is invalid!\n";

        String expectedStorageString = getTestAppointmentListStorageString();
        String actualStorageString = actualScheduler.getAppointmentStorageString();

        //Test for both correct error message and correct loading of storage
        assertEquals(expectedLoadStorageErrorMessage, actualLoadStorageErrorMessage);
        assertEquals(expectedStorageString, actualStorageString);
    }

    @Test
    public void testLoadAppointmentStorage_validFormat() throws MedBotException, FileNotFoundException {
        PatientStorage patientStorage = new PatientStorage(PATIENT_DATA_VALID);
        StaffStorage staffStorage = new StaffStorage(STAFF_DATA_VALID);
        AppointmentStorage appointmentStorage = new AppointmentStorage(APPOINTMENT_DATA_VALID);
        Scheduler actualScheduler = new Scheduler();

        patientStorage.loadStorage(ListItemType.PATIENT, actualScheduler);
        staffStorage.loadStorage(ListItemType.STAFF, actualScheduler);

        String actualLoadStorageErrorMessage = appointmentStorage.loadStorage(
                ListItemType.APPOINTMENT, actualScheduler);
        String expectedLoadStorageErrorMessage = "";

        String expectedStorageString = getTestAppointmentListStorageString();
        String actualStorageString = actualScheduler.getAppointmentStorageString();

        //Test for both correct error message and correct loading of storage
        assertEquals(expectedLoadStorageErrorMessage, actualLoadStorageErrorMessage);
        assertEquals(expectedStorageString, actualStorageString);
    }


    @Test
    public void testSaveData() throws MedBotException {
        //create test storage files
        PatientStorage patientStorage = new PatientStorage(PATIENT_DATA_SAVED);
        StaffStorage staffStorage = new StaffStorage(STAFF_DATA_SAVED);
        AppointmentStorage appointmentStorage = new AppointmentStorage(APPOINTMENT_DATA_SAVED);

        String expectedPatientStorageString = getTestPatientListStorageString();
        String expectedStaffStorageString = getTestStaffListStorageString();
        String expectedTestAppointmentString = getTestAppointmentListStorageString();

        try {
            //SAVE all storage data
            patientStorage.saveData(expectedPatientStorageString);
            staffStorage.saveData(expectedStaffStorageString);
            appointmentStorage.saveData(expectedTestAppointmentString);

            String actualPatientFileString = scanFileToString(PATIENT_DATA_SAVED);
            String actualStaffFileString = scanFileToString(STAFF_DATA_SAVED);
            String actualAppointmentFileString = scanFileToString(APPOINTMENT_DATA_SAVED);

            String expectedPatientFileString = scanFileToString(PATIENT_DATA_VALID);
            String expectedStaffFileString = scanFileToString(STAFF_DATA_VALID);
            String expectedAppointmentFileString = scanFileToString(APPOINTMENT_DATA_VALID);
            assertEquals(expectedPatientFileString, actualPatientFileString);
            assertEquals(expectedStaffFileString, actualStaffFileString);
            assertEquals(expectedAppointmentFileString, actualAppointmentFileString);

            deleteSavedData();

        } catch (IOException e) {
            throw new MedBotException("FILE ERROR");
        }
    }


    /**
     * Create a test PatientList containing Patient objects.
     *
     * @return a test PatientList
     * @throws MedBotException if any patient instantiation fails
     */
    private String getTestPatientListStorageString() throws MedBotException {
        PatientList testPatientList = new PatientList();
        Patient patient1 = (Patient) createPerson(PersonType.PATIENT, 1, "S7912345A", "Doge",
                "91234567", "doge@mail.com",
                "Doge Town 2nd Street");
        Patient patient2 = (Patient) createPerson(PersonType.PATIENT, 2, "", "Cheems",
                "", "cheems@mail.com",
                "Doge County");
        Patient patient3 = (Patient) createPerson(PersonType.PATIENT, 4, "S2312345A", "",
                "81234567", "cheems@mail.com",
                "Arken Street");
        Patient patient4 = (Patient) createPerson(PersonType.PATIENT, 6, "F9123456X", "Emma",
                "", "emma@mail.com",
                "Greensville County");
        Patient patient5 = (Patient) createPerson(PersonType.PATIENT, 7, "F3232145B", "Norman",
                "", "norman@mail.com",
                "Petalburg City");
        Patient patient6 = (Patient) createPerson(PersonType.PATIENT, 9, "S9145645C", "Arthur",
                "67654321", "",
                "Evergrande District");
        Patient patient7 = (Patient) createPerson(PersonType.PATIENT, 10, "T0123463X", "William",
                "", "will@mail.com",
                "Doge County");

        Patient[] patients = {patient1, patient2, patient3, patient4, patient5, patient6, patient7};

        for (Patient p : patients) {
            testPatientList.setLastId(p.getId());
            testPatientList.addPerson(p);
        }
        return testPatientList.getStorageString();

    }

    /**
     * Create a test PatientList containing Patient objects.
     *
     * @return a test PatientList
     * @throws MedBotException if any patient instantiation fails
     */
    private String getTestStaffListStorageString() throws MedBotException {
        MedicalStaffList testStaffList = new MedicalStaffList();
        Staff staff1 = (Staff) createPerson(PersonType.STAFF, 1, "S5123123A", "Doctor One",
                "81818181", "doctor1@mail.com",
                "American Gardens Building");
        Staff staff2 = (Staff) createPerson(PersonType.STAFF, 2, "", "Doctor Two",
                "91919191", "herrdoktor@mail.com",
                "Gotham Street");
        Staff staff3 = (Staff) createPerson(PersonType.STAFF, 6, "F6123123C", "Nurse One",
                "", "",
                "Little Root County");
        Staff staff4 = (Staff) createPerson(PersonType.STAFF, 7, "F2123123D", "Nurse Two",
                "", "nurse2@mail.com",
                "Petalburg City");

        Staff[] staff = {staff1, staff2, staff3, staff4};

        for (Staff s : staff) {
            testStaffList.setLastId(s.getId());
            testStaffList.addPerson(s);
        }
        return testStaffList.getStorageString();

    }


    /**
     * Create a test PatientList containing Patient objects.
     *
     * @return a test PatientList
     * @throws MedBotException if any patient instantiation fails
     */
    private String getTestAppointmentListStorageString() throws MedBotException {
        SchedulerAppointmentList testAppointmentList = new SchedulerAppointmentList();
        Appointment appointment1 = createAppointment("1", "1", "1", "010122 1000");
        Appointment appointment2 = createAppointment("2", "2", "1", "020122 1100");
        Appointment appointment3 = createAppointment("3", "4", "2", "030122 1200");
        Appointment appointment4 = createAppointment("6", "6", "2", "040122 1300");
        Appointment appointment5 = createAppointment("8", "7", "6", "050122 1400");
        Appointment appointment6 = createAppointment("9", "10", "7", "060122 1500");

        Appointment[] appointments = {appointment1, appointment2, appointment3, appointment4, appointment5, appointment6
        };

        for (Appointment a : appointments) {
            testAppointmentList.setLastId(a.getId());
            testAppointmentList.addAppointment(a);
        }
        return testAppointmentList.getStorageString();

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
    private Person createPerson(PersonType personType, int id, String icNumber, String name,
                                String phoneNumber, String emailAddress,
                                String residentialAddress)
            throws MedBotException {

        Person person = null;
        switch (personType) {
        case PATIENT:
            person = new Patient();
            break;
        case STAFF:
            person = new Staff();
            break;
        default:
            throw new MedBotException("Invalid personType");
        }

        person.setId(id);
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
            updatePersonalInformation(person, prefixPlusPersonParameter);
        }

        return person;
    }

    private Appointment createAppointment(String id, String patientId, String staffId, String dateTimeString)
            throws MedBotException {

        String[] parameterPrefixes = {"d/", "p/", "s/"};
        String[] parameters = {dateTimeString, patientId, staffId};
        ArrayList<String> prefixPlusAppointmentParameters = new ArrayList<>();

        for (int i = 0; i < parameterPrefixes.length; i++) {
            String prefixPlusPersonParameter = parameterPrefixes[i] + parameters[i];
            prefixPlusAppointmentParameters.add(prefixPlusPersonParameter);
        }

        Appointment appointment = new Appointment();
        appointment.setId(Integer.parseInt(id));

        for (String prefixPlusAppointmentParameter : prefixPlusAppointmentParameters) {
            updateAppointmentInformation(appointment, prefixPlusAppointmentParameter);
        }
        return appointment;
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
        File savedPatientDataFile = new File(PATIENT_DATA_SAVED);
        File savedStaffDataFile = new File(STAFF_DATA_SAVED);
        File savedAppointmentDataFile = new File(APPOINTMENT_DATA_SAVED);

        savedPatientDataFile.delete();
        savedStaffDataFile.delete();
        savedAppointmentDataFile.delete();
    }

}