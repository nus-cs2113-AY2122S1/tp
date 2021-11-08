package seedu.duke.list;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;

import seedu.duke.common.Elderly;
import seedu.duke.common.HighRiskElderly;
import seedu.duke.common.JsonDeserializerWithInheritance;
import seedu.duke.common.LowRiskElderly;
import seedu.duke.common.Medicine;
import seedu.duke.common.Appointment;
import seedu.duke.common.MediumRiskElderly;
import seedu.duke.common.NextOfKin;
import seedu.duke.common.Record;


import seedu.duke.exceptions.DoctorIndexException;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.DuplicateElderlyException;
import seedu.duke.exceptions.ElderlyNotFoundException;
import seedu.duke.exceptions.HospitalIndexException;
import seedu.duke.exceptions.InvalidAddMedicineFormatException;
import seedu.duke.exceptions.InvalidAddNokFormatException;
import seedu.duke.exceptions.InvalidAddRecordFormatException;
import seedu.duke.exceptions.InvalidDeleteApptFormatException;
import seedu.duke.exceptions.InvalidDeleteElderlyException;
import seedu.duke.exceptions.InvalidDeleteMedFormatException;
import seedu.duke.exceptions.InvalidDeleteNokFormatException;
import seedu.duke.exceptions.InvalidElderlyFormatException;
import seedu.duke.exceptions.InvalidLoadFromFilePathException;
import seedu.duke.exceptions.InvalidSetBloodPressureException;
import seedu.duke.exceptions.InvalidViewMedicineFormatException;
import seedu.duke.exceptions.InvalidAddAppointmentFormatException;
import seedu.duke.exceptions.InvalidViewAppointmentFormatException;
import seedu.duke.exceptions.InvalidStoreToFilePathException;
import seedu.duke.exceptions.InvalidViewByNameException;
import seedu.duke.exceptions.InvalidViewDietException;
import seedu.duke.exceptions.InvalidViewMedicineException;
import seedu.duke.exceptions.InvalidViewNokFormatException;
import seedu.duke.exceptions.InvalidViewRecordFormatException;
import seedu.duke.exceptions.InvalidRiskLevelException;
import seedu.duke.exceptions.InvalidInputException;
import seedu.duke.exceptions.InvalidViewDietCommandException;
import seedu.duke.exceptions.InvalidSetDietCommandException;
import seedu.duke.exceptions.InvalidSetVaccinationException;
import seedu.duke.exceptions.InvalidViewVaccinationException;
import seedu.duke.exceptions.InvalidSetBirthdayException;
import seedu.duke.exceptions.InvalidViewBirthdayException;
import seedu.duke.exceptions.InvalidViewBloodPressureException;
import seedu.duke.exceptions.InvalidAddMedicalHistoryException;
import seedu.duke.exceptions.InvalidDeleteMedicalHistoryException;
import seedu.duke.exceptions.InvalidViewMedicalHistoryException;

import seedu.duke.hospital.Doctor;
import seedu.duke.hospital.Hospital;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;
import java.util.Optional;

import static seedu.duke.common.MagicValues.SPLIT_ADD_NOK;
import static seedu.duke.common.MagicValues.SPLIT_ADD_RECORD;
import static seedu.duke.common.MagicValues.SPLIT_DELETE_APPOINTMENT;
import static seedu.duke.common.MagicValues.SPLIT_DELETE_NOK;
import static seedu.duke.common.MagicValues.SPLIT_DELETE_MED;
import static seedu.duke.common.MagicValues.HIGH;
import static seedu.duke.common.MagicValues.INDEX_OF_DELETE_DATE;
import static seedu.duke.common.MagicValues.INDEX_OF_DELETE_TIME;
import static seedu.duke.common.MagicValues.INDEX_OF_ELDERLY_USERNAME;
import static seedu.duke.common.MagicValues.INDEX_OF_FILE_PATH;
import static seedu.duke.common.MagicValues.INPUT_OFFSET;
import static seedu.duke.common.MagicValues.SPLIT_LOAD_FILE;
import static seedu.duke.common.MagicValues.LOW;
import static seedu.duke.common.MagicValues.MEDIUM;
import static seedu.duke.common.MagicValues.SPLIT_NAME;
import static seedu.duke.common.MagicValues.SPLIT_ADD_MEDICINE;
import static seedu.duke.common.MagicValues.SPLIT_ADD_APPOINTMENT;
import static seedu.duke.common.MagicValues.INDEX_OF_DATE;
import static seedu.duke.common.MagicValues.INDEX_OF_ELDERLY_NAME;
import static seedu.duke.common.MagicValues.INDEX_OF_RISK_LEVEL;
import static seedu.duke.common.MagicValues.INDEX_OF_ELDERLY_ADDRESS;
import static seedu.duke.common.MagicValues.INDEX_OF_FREQUENCY;
import static seedu.duke.common.MagicValues.INDEX_OF_LOCATION;
import static seedu.duke.common.MagicValues.INDEX_OF_ELDERLY_PHONE_NUMBER;
import static seedu.duke.common.MagicValues.INDEX_OF_MEDICINE_NAME;
import static seedu.duke.common.MagicValues.INDEX_OF_NOK_ADDRESS;
import static seedu.duke.common.MagicValues.INDEX_OF_NOK_EMAIL;
import static seedu.duke.common.MagicValues.INDEX_OF_NOK_PHONE_NUMBER;
import static seedu.duke.common.MagicValues.INDEX_OF_NOK_RELATIONSHIP;
import static seedu.duke.common.MagicValues.INDEX_OF_PURPOSE;
import static seedu.duke.common.MagicValues.INDEX_OF_TIME;
import static seedu.duke.common.MagicValues.LENGTH_IF_PURPOSE_EXISTS;
import static seedu.duke.common.MagicValues.INDEX_OF_NOK_NAME;
import static seedu.duke.common.MagicValues.INDEX_OF_BIRTHDAY;
import static seedu.duke.common.MagicValues.INDEX_OF_SYSTOLIC_PRESSURE;
import static seedu.duke.common.MagicValues.INDEX_OF_DIASTOLIC_PRESSURE;
import static seedu.duke.common.MagicValues.GENERAL_CHECKUP;
import static seedu.duke.common.MagicValues.INDEX_OF_SYSTOLIC_PRESSURE_IN_ARRAY;
import static seedu.duke.common.MagicValues.INDEX_OF_DIASTOLIC_PRESSURE_IN_ARRAY;
import static seedu.duke.common.MagicValues.SIMILARITY_INDEX;
import static seedu.duke.common.MagicValues.SPLIT_STORE_FILE;
import static seedu.duke.common.MagicValues.parser;
import static seedu.duke.common.MagicValues.ui;
import static seedu.duke.common.MagicValues.re;
import static seedu.duke.common.MagicValues.hospitalArrayList;


import static seedu.duke.common.Messages.MESSAGE_NO_BLOOD_PRESSURE;
import static seedu.duke.common.Messages.MESSAGE_NUMBER_OF_ELDERLY;


public class ElderlyList {


    protected ArrayList<Elderly> elderlyArrayList = new ArrayList<>();
    protected final HashMap<String, HashSet<String>> medicineMappings = new HashMap<>();
    protected final HashMap<String, HashSet<String>> dietMappings = new HashMap<>();

    private String filePath;
    private final Gson gson;

    public ElderlyList(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Elderly.class, new JsonDeserializerWithInheritance<Elderly>()).create();
    }

    /**
     * Returns the elderly array list.
     *
     * @return elderlyArrayList is the elderly array list.
     */
    public ArrayList<Elderly> getElderlyArrayList() {
        return elderlyArrayList;
    }

    /**
     * Checks the number of elderly with a given username.
     *
     * @param inputElderlyName String containing elderly username to check with
     * @return Boolean value if elderly exists in array list
     */
    private int elderlyCount(String inputElderlyName) {
        return (int) elderlyArrayList
                .stream()
                .filter(s -> s.getUsername().equals(inputElderlyName))
                .count();
    }

    /**
     * Adds the elderly into the elderly array list, together with risk level and additional info if required.
     *
     * @param userLine Line that is inputted by the user.
     */
    public void addElderly(String userLine) {
        try {
            if (!re.isValidAddElderly(userLine)) {
                throw new InvalidElderlyFormatException();
            }
            String[] paramList = userLine.split(SPLIT_NAME);
            String userName = paramList[INDEX_OF_ELDERLY_USERNAME].toLowerCase();
            String elderlyName = paramList[INDEX_OF_ELDERLY_NAME].toLowerCase();
            String riskLevel = paramList[INDEX_OF_RISK_LEVEL].toUpperCase();
            if (!re.isValidRiskLevel(riskLevel)) {
                throw new InvalidRiskLevelException();
            }
            // If Elderly username exists, reject addition
            if (elderlyCount(userName) > 0) {
                throw new DuplicateElderlyException();
            }
            Elderly addedElderly;
            switch (riskLevel) {
            case LOW:
                addedElderly = addLowRiskElderly(userName, elderlyName);
                break;
            case MEDIUM:
                addedElderly = addMediumRiskElderly(userName, elderlyName);
                break;
            case HIGH:
                addedElderly = addHighRiskElderly(userName, elderlyName);
                break;
            default:
                throw new InvalidRiskLevelException();
            }
            ui.printAddElderlyMessage(addedElderly);
            return;
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Adds the elderly as a low risk elderly.
     *
     * @param userName    username of elderly.
     * @param elderlyName name of elderly.
     */
    private Elderly addLowRiskElderly(String userName, String elderlyName) {
        LowRiskElderly addedElderly = new LowRiskElderly(userName, elderlyName);
        elderlyArrayList.add(addedElderly);
        return addedElderly;
    }

    /**
     * Adds the elderly as a medium risk elderly.
     *
     * @param userName    username of elderly.
     * @param elderlyName name of elderly.
     * @throws HospitalIndexException if the hospital index keyed in is out of range.
     */
    private Elderly addMediumRiskElderly(String userName, String elderlyName) throws HospitalIndexException {
        hospitalArrayList.printHospitalNames();
        ui.printEnterHospitalMessage();
        String stringHospitalIndex = ui.getUserInput();
        if (re.isNotValidHospitalIndex(stringHospitalIndex)) {
            throw new HospitalIndexException();
        }
        int hospitalIndex = Integer.parseInt(stringHospitalIndex) - INPUT_OFFSET;
        Hospital hospital = hospitalArrayList.getHospital(hospitalIndex);
        ui.printEnterConditionsMessage();
        String conditions = ui.getUserInput();
        ui.printEnterNotesOnCareMessage();
        String notesOnCare = ui.getUserInput();

        MediumRiskElderly addedElderly =
                new MediumRiskElderly(userName, elderlyName, hospital, conditions, notesOnCare);
        elderlyArrayList.add(addedElderly);
        return addedElderly;
    }

    /**
     * Adds the elderly as a high risk elderly.
     *
     * @param userName    username of elderly.
     * @param elderlyName name of elderly.
     * @throws HospitalIndexException if the hospital index keyed in is out of range.
     * @throws DoctorIndexException   if the doctor index keyed in is out of range.
     */
    private Elderly addHighRiskElderly(String userName, String elderlyName) throws HospitalIndexException,
            DoctorIndexException {
        hospitalArrayList.printHospitalNames();
        ui.printEnterHospitalMessage();
        String stringHospitalIndex = ui.getUserInput();
        if (re.isNotValidHospitalIndex(stringHospitalIndex)) {
            throw new HospitalIndexException();
        }
        int hospitalIndex = Integer.parseInt(stringHospitalIndex) - INPUT_OFFSET;
        Hospital hospital = hospitalArrayList.getHospital(hospitalIndex);
        ui.printEnterConditionsMessage();
        final String conditions = ui.getUserInput();
        ui.printEnterNotesOnCareMessage();
        final String notesOnCare = ui.getUserInput();
        hospital.printDoctorNames();
        ui.printEnterDoctorMessage();
        String stringDoctorIndex = ui.getUserInput();
        if (!re.isValidDoctorIndex(stringDoctorIndex)) {
            throw new DoctorIndexException();
        }
        Doctor doctor = hospital.getDoctor(Integer.parseInt(stringDoctorIndex) - INPUT_OFFSET);
        HighRiskElderly addedElderly = new HighRiskElderly(userName, elderlyName, hospital,
                conditions, notesOnCare, doctor);
        elderlyArrayList.add(addedElderly);
        return addedElderly;
    }

    /**
     * Adds the medicine to the given elderly based on the input.
     *
     * @param userLine Line that is inputted by the user.
     */
    public void addMedicine(String userLine) {
        try {
            if (!re.isValidAddMedicine(userLine)) {
                throw new InvalidAddMedicineFormatException();
            }
            String[] paramList = userLine.split(SPLIT_ADD_MEDICINE);
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            String medicineName = paramList[INDEX_OF_MEDICINE_NAME];
            String frequency = paramList[INDEX_OF_FREQUENCY];
            assert paramList.length == 4 : "addmed input does not have all required values";
            elderly.addMedicine(new Medicine(medicineName, frequency));
            ui.printAddMedicineMessage();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Views all medicine the elderly needs to take based on the input.
     *
     * @param userLine Line that is inputted by the user.
     */
    public void viewMedicine(String userLine) {
        try {
            if (!re.isValidViewMedicine(userLine)) {
                throw new InvalidViewMedicineFormatException();
            }
            String[] paramList = userLine.split(SPLIT_NAME);
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            assert paramList.length == 2 : "Username is empty";
            Elderly elderly = getElderly(elderlyName);
            printMedicines(elderly);
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Prints all the medicine, given the elderly.
     *
     * @param elderly The elderly in question.
     */
    private void printMedicines(Elderly elderly) {
        ArrayList<Medicine> medicineList = elderly.getMedicines();
        System.out.println("Medicine of " + elderly.getUsername() + " are shown below:");
        for (int index = 1; index <= medicineList.size(); ++index) {
            System.out.format("% 3d.", index);
            System.out.println(medicineList.get(index - 1));
        }
    }

    /**
     * Adds the appointment to the given elderly based on the input.
     *
     * @param userLine Line that is inputted by the user.
     */
    public void addAppointment(String userLine) {
        try {
            if (!re.isValidAddAppointment(userLine)) {
                throw new InvalidAddAppointmentFormatException();
            }
            String[] paramList = userLine.split(SPLIT_ADD_APPOINTMENT);
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            String location = paramList[INDEX_OF_LOCATION];
            String date = paramList[INDEX_OF_DATE];
            String time = paramList[INDEX_OF_TIME];
            String purpose;
            if (paramList.length == LENGTH_IF_PURPOSE_EXISTS) {
                purpose = paramList[INDEX_OF_PURPOSE];
            } else {
                purpose = GENERAL_CHECKUP;
            }
            assert paramList.length == 5 || paramList.length == 6 : "addappt input does not have all required values";
            if (isValidDate(date) && isValidTime(time)) {
                elderly.addElderlyAppointment(new Appointment(location, date, time, purpose));
                ui.printAddAppointmentMessage();
            } else if (!isValidDate(date)) {
                ui.printInvalidDateMessage();
            } else {
                ui.printInvalidTimeMessage();
            }
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Takes in date and checks if the date is valid.
     *
     * @param date Date that is to be checked.
     * @return True if date is valid, false if the date is invalid.
     */
    private boolean isValidDate(String date) {
        char[] dateArray = date.toCharArray();
        int[] numArray = new int[8];
        for (int i = 0; i < 8; i += 1) {
            numArray[i] = dateArray[i] - '0';
        }
        int day = (numArray[0] * 10) + numArray[1];
        int month = (numArray[2] * 10) + numArray[3];
        if (day > 31 || month > 12) {
            return false;
        }
        if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        if (month == 2 && (day == 29 || day == 30 || day == 31)) {
            return false;
        }
        return true;
    }

    /**
     * Takes in the time and checks if it is valid.
     *
     * @param time Time that is to be checked.
     * @return True if the time is valid and false if the time is invalid.
     */
    private boolean isValidTime(String time) {
        char[] timeArray = time.toCharArray();
        int[] numArray = new int[4];
        for (int i = 0; i < 4; i += 1) {
            numArray[i] = timeArray[i] - '0';
        }
        int hour = (numArray[0] * 10) + numArray[1];
        int minute = (numArray[2] * 10) + numArray[3];
        if (hour > 23 || minute > 59) {
            return false;
        }
        return true;
    }

    /**
     * Views all appointments the elderly has based on the input.
     *
     * @param userLine Line that is inputted by the user.
     */
    public void viewAppointment(String userLine) {
        try {
            if (!re.isValidViewAppointment(userLine)) {
                throw new InvalidViewAppointmentFormatException();
            }
            String[] paramList = userLine.split(SPLIT_NAME);
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            assert paramList.length == 2 : "Username is empty";
            Elderly elderly = getElderly(elderlyName);
            printAppointments(elderly);
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Deletes the specified apppointment (based on date and time) from the elderly that is provided.
     *
     * @param userLine Line that is entered by the user.
     */
    public void deleteAppointment(String userLine) {
        try {
            if (!re.isValidDeleteAppointment(userLine)) {
                throw new InvalidDeleteApptFormatException();
            }
            String[] paramList = userLine.split(SPLIT_DELETE_APPOINTMENT);
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            String date = paramList[INDEX_OF_DELETE_DATE];
            String time = paramList[INDEX_OF_DELETE_TIME];
            Elderly elderly = getElderly(elderlyName);
            Optional<Appointment> deletedAppointment = elderly.removeAppointment(date, time);
            deletedAppointment.ifPresentOrElse(this::printDeletedAppointment, this::printNoAppointment);
            System.gc();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Prints the deleted appointment message with the deleted appointment for the user to verify that the
     * appointment has been deleted.
     *
     * @param deletedAppointment Appointment that has just been deleted.
     */
    public void printDeletedAppointment(Appointment deletedAppointment) {
        System.out.println("These appointment details are now deleted:");
        System.out.println(deletedAppointment);
    }

    /**
     * Prints message when there is no appointment matching what is specified by the user.
     */
    public void printNoAppointment() {
        System.out.println("No Appointment found");
    }

    /**
     * Prints all the appointments, given the elderly.
     *
     * @param elderly The elderly in question.
     */
    private void printAppointments(Elderly elderly) {
        int counter = 1;
        System.out.println("Appointments of " + elderly.getUsername() + " are shown below:");
        for (Appointment appointment : elderly.getAppointments()) {
            System.out.format("% 3d.", counter);
            System.out.println(appointment);
            counter++;
        }
    }

    /**
     * Adds Next-Of-Kin information to the given elderly based on the input.
     *
     * @param userLine Line that is inputted by user.
     */
    public void addNok(String userLine) {
        try {
            if (!re.isValidAddNok(userLine)) {
                throw new InvalidAddNokFormatException();
            }
            String[] paramList = userLine.split(SPLIT_ADD_NOK);
            assert paramList.length == 7 : "addnok input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            String nokName = paramList[INDEX_OF_NOK_NAME];
            String nokPhoneNumber = paramList[INDEX_OF_NOK_PHONE_NUMBER];
            String nokEmail = paramList[INDEX_OF_NOK_EMAIL];
            String nokAddress = paramList[INDEX_OF_NOK_ADDRESS];
            String nokRelationship = paramList[INDEX_OF_NOK_RELATIONSHIP];
            elderly.addElderlyNok(new NextOfKin(nokName, nokPhoneNumber, nokEmail, nokAddress, nokRelationship));
            ui.printAddNokMessage();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }


    /**
     * Views the Next-Of_kin information of the elderly based on input.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void viewNok(String userLine) {
        try {
            if (!re.isValidViewNok(userLine)) {
                throw new InvalidViewNokFormatException();
            }
            String[] paramList = userLine.split(SPLIT_NAME);
            assert paramList.length == 2 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            printNextOfKin(elderly);
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Prints the Next-Of-Kin information, given the elderly.
     *
     * @param elderly The elderly in question.
     */
    public void printNextOfKin(Elderly elderly) {
        System.out.println("Details of " + elderly.getUsername() + " Next-of-Kin are shown below:");
        System.out.println(elderly.getNextOfKin());
    }

    /**
     * Deletes the details of Next-Of-Kin (identified by name) that is specified by the user from the elderly
     * provided.
     *
     * @param userLine Line that is entered by the user.
     */
    public void deleteNextOfKin(String userLine) {
        try {
            if (!re.isValidDeleteNok(userLine)) {
                throw new InvalidDeleteNokFormatException();
            }
            String[] paramList = userLine.split(SPLIT_DELETE_NOK);
            assert paramList.length == 3 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            String nokName = paramList[INDEX_OF_NOK_NAME];
            Elderly elderly = getElderly(elderlyName);
            Optional<NextOfKin> deletedNok = elderly.removeElderlyNok(nokName);
            deletedNok.ifPresentOrElse(this::printDeletedNextOfKin, this::printNoNextOfKin);
            System.gc();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }


    /**
     * Prints out details of Next-Of-Kin that has just been deleted, to notify the user that the details are deleted.
     *
     * @param deletedNok Details of Next-Of-Kin that are recently deleted.
     */
    public void printDeletedNextOfKin(NextOfKin deletedNok) {
        System.out.println("These details are now deleted:");
        System.out.println(deletedNok);
    }

    /**
     * Message printed when there are no matching details of Next-Of-Kin (identified by name) found.
     */
    public void printNoNextOfKin() {
        System.out.println("No Next-Of-Kin found");
    }

    /**
     * Deletes the medicine information that is specified by the user (based on name of medicine) from the elderly
     * that is specified by the user.
     *
     * @param userLine Line that is entered by the user.
     */
    public void deleteMedicine(String userLine) {
        try {
            if (!re.isValidDeleteMedicine(userLine)) {
                throw new InvalidDeleteMedFormatException();
            }
            String[] paramList = userLine.split(SPLIT_DELETE_MED);
            assert paramList.length == 3 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            String medName = paramList[INDEX_OF_MEDICINE_NAME];
            Elderly elderly = getElderly(elderlyName);
            Optional<Medicine> deletedMed = elderly.removeElderlyMedicine(medName);
            deletedMed.ifPresentOrElse(this::printDeletedMedicine, this::printNoMedicine);
            System.gc();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Message prints when there is no medicine details found (identified by the name of medicine).
     */
    public void printNoMedicine() {
        System.out.println("No Medicine found");
    }

    /**
     * Prints out the recently deleted medicine information from the elderly.
     *
     * @param deletedMed Medicine that has been recently deleted.
     */
    public void printDeletedMedicine(Medicine deletedMed) {
        System.out.println("These medicine details are now deleted:");
        System.out.println(deletedMed);
    }

    /**
     * Adds information to the given elderly based on the input.
     *
     * @param userLine Line that is inputted by user.
     */
    public void addRecord(String userLine) {
        try {
            if (!re.isValidAddRecord(userLine)) {
                throw new InvalidAddRecordFormatException();
            }
            String[] paramList = userLine.split(SPLIT_ADD_RECORD);
            assert paramList.length == 4 : "addrec input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            String elderlyPhoneNumber = paramList[INDEX_OF_ELDERLY_PHONE_NUMBER];
            String elderlyAddress = paramList[INDEX_OF_ELDERLY_ADDRESS];
            elderly.addElderlyRecord(new Record(elderlyPhoneNumber, elderlyAddress));
            ui.printAddRecordMessage();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Views the information of the elderly based on input.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void viewRecord(String userLine) {
        try {
            if (!re.isValidViewRec(userLine)) {
                throw new InvalidViewRecordFormatException();
            }
            String[] paramList = userLine.split(SPLIT_NAME);
            assert paramList.length == 2 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            printRecord(elderly);
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Prints the information, given the elderly.
     *
     * @param elderly The elderly in question.
     */
    public void printRecord(Elderly elderly) {
        System.out.println("Record of " + elderly.getUsername() + " is shown below:");
        System.out.println(elderly.getRecord());
    }

    /**
     * Gets the Elderly object given the username.
     *
     * @param username String containing name of elderly.
     * @return Elderly object.
     * @throws ElderlyNotFoundException if the elderly with that username is not found.
     */
    public Elderly getElderly(String username) throws ElderlyNotFoundException {
        for (Elderly elderly : elderlyArrayList) {
            if (Objects.equals(elderly.getUsername(), username)) {
                return elderly;
            }
        }
        throw new ElderlyNotFoundException();
    }

    /**
     * Views the blood pressure of the elderly.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void viewBloodPressure(String userLine) {
        try {
            if (!re.isValidViewBloodPressureCommand(userLine)) {
                throw new InvalidViewBloodPressureException();
            }
            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            printBloodPressure(elderly);
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    private void printBloodPressure(Elderly elderly) {
        int[] bloodPressure = elderly.getElderlyBloodPressure();
        if (!elderly.isBloodPressureSet()) {
            System.out.printf(MESSAGE_NO_BLOOD_PRESSURE, elderly.getName());
            System.out.printf("%n");
        } else {
            System.out.printf("Blood pressure of %s is now (%d %d)%n", elderly.getName(),
                    bloodPressure[INDEX_OF_SYSTOLIC_PRESSURE_IN_ARRAY],
                    bloodPressure[INDEX_OF_DIASTOLIC_PRESSURE_IN_ARRAY]);
        }
    }

    /**
     * Sets the blood pressure of the elderly.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void setBloodPressure(String userLine) {
        try {
            if (!re.isValidSetBloodPressure(userLine)) {
                throw new InvalidSetBloodPressureException();
            }
            String[] paramList = userLine.split(" [usd]/");
            assert paramList.length == 4 : "setbloodpressure input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Integer systolicPressure = Integer.parseInt(paramList[INDEX_OF_SYSTOLIC_PRESSURE]);
            Integer diastolicPressure = Integer.parseInt(paramList[INDEX_OF_DIASTOLIC_PRESSURE]);
            Elderly elderly;
            elderly = getElderly(elderlyName);
            elderly.setElderlyBloodPressure(systolicPressure, diastolicPressure);
            ui.printSetBloodPressureMessage(elderly);
        } catch (DukeException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Views the birthday of the elderly.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void viewBirthday(String userLine) {
        try {
            if (!re.isValidViewBirthdayCommand(userLine)) {
                throw new InvalidViewBirthdayException();
            }
            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "Name is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            printBirthday(elderly);
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
            return;
        } catch (DukeException e) {
            ui.printDukeException(e);
            return;
        }
    }

    private void printBirthday(Elderly elderly) {
        try {
            String birthday = elderly.getBirthday();
            System.out.printf("Birthday of %s is %s%n", elderly.getName(), birthday);
        } catch (NullPointerException e) {
            System.out.printf("Birthday of %s has not been set.%n", elderly.getName());
        }
    }

    /**
     * Sets the birthday of the elderly.
     *
     * @param userLine Line that has been inputted by user.
     */
    public Optional<Elderly> setBirthday(String userLine) {
        Elderly elderly;
        try {
            if (!re.isValidSetBirthdayCommand(userLine)) {
                throw new InvalidSetBirthdayException();
            }
            String[] paramList = userLine.split(" [ub]/");
            assert paramList.length == 3 : "setbirthday input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            String birthday = paramList[INDEX_OF_BIRTHDAY];
            elderly = getElderly(elderlyName);
            boolean successfulSet = elderly.setElderlyBirthday(birthday);
            if (successfulSet) {
                return Optional.of(elderly);
            }

        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
        }
        return Optional.empty();
    }

    /**
     * Sets the vaccination status of the elderly to be true.
     *
     * @param userLine Line that has been inputted by user.
     */
    public Optional<Elderly> setVaccinated(String userLine) {
        try {
            if (!re.isValidSetVaccinationCommand(userLine)) {
                throw new InvalidSetVaccinationException();
            }
            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "setvaccinated input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly;

            elderly = getElderly(elderlyName);
            elderly.setElderlyVaccinated();
            return Optional.of(elderly);
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
            return Optional.empty();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
            return Optional.empty();
        }

    }

    /**
     * Gets the vaccination status of the elderly.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void getVaccinationStatus(String userLine) {
        try {
            if (!re.isValidViewVaccinationCommand(userLine)) {
                throw new InvalidViewVaccinationException();
            }
            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];

            Elderly elderly = getElderly(elderlyName);
            elderly.printVaccinationStatus();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Sets the dietary preference of the elderly according to user's selection.
     *
     * @param userLine Line that has been inputted by user.
     */
    public Optional<Elderly> setDietaryPreference(String userLine) {
        try {
            if (!re.isValidSetDietCommand(userLine)) {
                throw new InvalidSetDietCommandException();
            }

            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "setdiet input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly;

            elderly = getElderly(elderlyName);
            elderly.setElderlyDietByUserChoice();
            return Optional.of(elderly);
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
            return Optional.empty();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
            return Optional.empty();
        }
    }

    /**
     * Gets the dietary preference of the elderly.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void viewDietaryPreference(String userLine) {
        try {
            if (!re.isValidViewDietCommand(userLine)) {
                throw new InvalidViewDietCommandException();
            }
            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            elderly.printElderlyDietaryPreference();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
        }
    }

    /**
     * Add medical history of the elderly according to user's selection.
     *
     * @param userLine Line that has been inputted by user.
     */
    public Optional<Elderly> addMedicalHistory(String userLine) {
        try {
            if (!re.isValidAddMedicalHistoryCommand(userLine)) {
                throw new InvalidAddMedicalHistoryException();
            }
            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "addmedicalhistory input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly;
            elderly = getElderly(elderlyName);
            elderly.setElderlyMedicalHistory();
            return Optional.of(elderly);
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
            return Optional.empty();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
            return Optional.empty();
        }
    }

    /**
     * Prints the dietary preference of the elderly.
     *
     * @param userLine Line that has been inputted by user.
     */
    public void viewMedicalHistory(String userLine) {
        try {
            if (!re.isValidViewMedicalHistoryCommand(userLine)) {
                throw new InvalidViewMedicalHistoryException();
            }
            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "Username is empty";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly = getElderly(elderlyName);
            elderly.printElderlyMedicalHistory();
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        }
    }

    /**
     * Delete medical history of the elderly according to user's selection.
     *
     * @param userLine Line that has been inputted by user.
     */
    public Optional<Elderly> deleteMedicalHistory(String userLine) {
        try {
            if (!re.isValidDeleteMedicalHistoryCommand(userLine)) {
                throw new InvalidDeleteMedicalHistoryException();
            }

            String[] paramList = userLine.split(" u/");
            assert paramList.length == 2 : "deletemedicalhistory input does not have all required values";
            String elderlyName = paramList[INDEX_OF_ELDERLY_USERNAME];
            Elderly elderly;
            Elderly returningElderly;

            elderly = getElderly(elderlyName);
            returningElderly = elderly.deleteElderlyMedicalHistory();
            return Optional.of(returningElderly);
        } catch (ElderlyNotFoundException e) {
            ui.printDukeException(e);
            return Optional.empty();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
            return Optional.empty();
        }
    }


    /**
     * Get the number of elderly in the current list.
     */
    public int getElderlyCount() {
        return elderlyArrayList.size();
    }

    /**
     * Returns a consolidated String of all the elderly.
     *
     * @return String containing all the elderly information in the system.
     */
    public String getConsolidatedStringOfElderly() {
        return elderlyArrayList
                .stream()
                .map(Objects::toString)
                .reduce((t, u) -> t + '\n' + u)
                .orElse("");
    }

    /**
     * Prints current list of elderly along with number of elderly in system.
     */
    public void printElderly() {
        System.out.printf(MESSAGE_NUMBER_OF_ELDERLY, getElderlyCount());
        ui.printElderlyList(getConsolidatedStringOfElderly());
    }

    /**
     * Builds the different mappings between medicine name, diet preference and names of elderly.
     */
    public void updateMappings() {
        // Creation of hashmap to do mapping between medicine and list of elderly that take given medicine
        for (Elderly elderlyObject : elderlyArrayList) {
            String elderlyName = elderlyObject.getUsername().toLowerCase();
            // Update Medicine Mappings
            for (Medicine medicineObject : elderlyObject.getMedicines()) {
                String medicineName = medicineObject.getMedicineName().toLowerCase();
                // Create new Medicine name to Elderly name HashSet Mapping if it doesn't exist
                if (!medicineMappings.containsKey(medicineName)) {
                    medicineMappings.put(medicineName, new HashSet<>());
                }
                // Append Elderly name to list of medicine mapping
                medicineMappings.get(medicineName).add(elderlyName);
            }
            // Update diet mappings
            String dietPreference = elderlyObject.getElderlyDiet().toLowerCase();
            if (!dietMappings.containsKey(dietPreference)) {
                dietMappings.put(dietPreference, new HashSet<>());
            }
            dietMappings.get(dietPreference).add(elderlyName);
        }
    }

    /**
     * Returns a consolidated String of all elderly taking a given medicine.
     *
     * @param medicineQuery String containing medicine to be looked up.
     * @return String containing all the elderly names in the system that takes the medicine.
     */
    public String buildElderlyStringGivenMedicine(String medicineQuery) {
        String resultString = "";
        updateMappings();

        // Query HashSet for medicine name
        if (medicineMappings.containsKey(medicineQuery)) {
            // Gets all elderly names as a string
            resultString = String.join(System.lineSeparator(), medicineMappings.get(medicineQuery));
        }
        return resultString;
    }

    /**
     * Prints the list of elderly that is taking the searched medicine.
     *
     * @param userLine String containing medicine to be looked up.
     */
    public void getElderlyGivenMedicine(String userLine) {
        try {
            // Check if format is correct
            if (!re.isValidFindMed(userLine)) {
                throw new InvalidViewMedicineException();
            }

            String medicineQuery = parser.getMedicineFromSearchMed(userLine);
            ui.printQueryResultsIntroString(medicineQuery);
            String results = buildElderlyStringGivenMedicine(medicineQuery);
            if (results.isEmpty()) {
                checkSimilarities(medicineMappings.keySet(), medicineQuery);
            } else {
                System.out.println(results);
            }
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        }
    }

    /**
     * Returns a consolidated String of all elderly with a certain diet.
     *
     * @param dietQuery String containing diet preference to be looked up.
     * @return String containing all the elderly names in the system that has given diet preference.
     */
    public String buildElderlyStringGivenDiet(String dietQuery) {
        String resultString = "";
        updateMappings();

        // Query HashSet for diet name
        if (dietMappings.containsKey(dietQuery)) {
            // Gets all elderly names as a string
            resultString = String.join(System.lineSeparator(), dietMappings.get(dietQuery));
        }
        return resultString;
    }

    /**
     * Prints the list of elderly that is taking the searched diet preference.
     *
     * @param userLine String containing diet to be looked up.
     */
    public void getElderlyGivenDiet(String userLine) {
        try {
            // Check if format is correct
            if (!re.isValidFindDiet(userLine)) {
                throw new InvalidViewDietException();
            }

            String dietQuery = parser.getDietFromSearchMed(userLine);
            ui.printQueryResultsIntroString(dietQuery);
            String results = buildElderlyStringGivenDiet(dietQuery);
            if (results.isEmpty()) {
                checkSimilarities(dietMappings.keySet(), dietQuery);
            } else {
                System.out.println(results);
            }
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        }
    }

    /**
     * Returns a consolidated String of all elderly information given real name.
     *
     * @param realName String containing real name to be looked out.
     * @return String containing all the elderly information in the system based on filter.
     */
    public String filterElderlyInformationGivenName(String realName) {
        return elderlyArrayList
                .stream()
                .filter((t) -> t.getName().toLowerCase().contentEquals(realName))
                .map(Objects::toString)
                .reduce((t, u) -> t + '\n' + u)
                .orElse("");
    }

    /**
     * Get a list of all the real names in the system.
     *
     * @return Array of all real names.
     */
    public Set<String> getAllRealNames() {
        return elderlyArrayList
                .stream()
                .map(Elderly::getName)
                .collect(Collectors.toSet());
    }

    /**
     * Get a list of all usernames in the system.
     *
     * @return Array of all usernames.
     */
    public Set<String> getAllUserNames() {
        return elderlyArrayList
                .stream()
                .map(Elderly::getUsername)
                .collect(Collectors.toSet());
    }

    /**
     * Iterates through Strings and returns first result that has the highest similarities.
     *
     * @param listOfStrings Set of Strings to search from.
     * @param searchTerm    String with search term.
     */
    public void checkSimilarities(Set<String> listOfStrings, String searchTerm) {
        float highestResult = 0;
        HashMap<Float, String> resultToString = new HashMap<>();
        for (String stringInSet : listOfStrings) {
            float result = re.levenshteinDistance(searchTerm, stringInSet);
            if (highestResult < result) {
                highestResult = result;
            }
            resultToString.put(result, stringInSet);
        }

        // Print out the closest match if similarity more than 0.80
        if (highestResult >= SIMILARITY_INDEX) {
            ui.printClosestMatch(resultToString.get(highestResult));
        } else {
            // Say that you don't understand what the user is looking for
            ui.printCannotFindQuery(searchTerm);
        }
    }

    /**
     * Prints a list of elderly and their details given real name.
     *
     * @param userLine String containing real name to be looked out.
     */
    public void getAllElderlyDetailsByName(String userLine) {
        try {
            // Check if format is correct
            if (!re.isValidFindByName(userLine)) {
                throw new InvalidViewByNameException();
            }
            String realName = parser.getRealNameFromSearchName(userLine);
            ui.printQueryResultsIntroString(realName);
            String results = filterElderlyInformationGivenName(realName);
            if (results.isEmpty()) {
                Set<String> realNameArray = getAllRealNames();
                checkSimilarities(realNameArray, realName);
            } else {
                System.out.println(results);
            }
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        }
    }

    /**
     * Removes elderly from list given username.
     *
     * @param userLine String containing username to be deleted from list.
     */
    public void deleteElderlyByUsername(String userLine) {
        try {
            // Check if format is correct
            if (!re.isValidDeleteElderlyByUsername(userLine)) {
                throw new InvalidDeleteElderlyException();
            }
            String userName = parser.getUserNameFromDeleteElderly(userLine);
            Set<String> allUserNames = getAllUserNames();
            if (allUserNames.contains(userName)) {
                elderlyArrayList
                        .removeIf((t) -> t.getUsername().toLowerCase().contentEquals(userName));
                System.gc();
                ui.printDeleteByName(userName);
            } else {
                checkSimilarities(allUserNames, userName);
            }
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        }
    }

    /**
     * Store program's data as JSON into a file using given filepath.
     *
     * @param userLine String containing store command and filepath.
     */
    public void storeFromFilePath(String userLine) {
        try {
            // Checks if the command is issued correctly
            if (!re.isValidStoreFileFromFilePath(userLine)) {
                throw new InvalidStoreToFilePathException();
            }

            String[] paramList = userLine.split(SPLIT_STORE_FILE);
            this.filePath = paramList[INDEX_OF_FILE_PATH];

            // Writes to file path and overwrites all contents if it doesn't exist
            FileWriter fw = new FileWriter(this.filePath);
            JsonWriter jw = gson.newJsonWriter(fw);
            gson.toJson(gson.toJsonTree(elderlyArrayList), jw);
            fw.close();
            ui.printDataStoredSuccess();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (IOException e) {
            ui.printExceptionMessage(e);
        }
    }

    /**
     * Returns if file exists based on a given filepath.
     *
     * @param filePath String of the absolute/relative filepath.
     * @return Boolean value.
     */
    private boolean checkIfFileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * Checks for default elderly object to see if there are any violations.
     *
     * @param elderly Elderly object.
     * @return Boolean value.
     */
    private boolean isValidGenericElderly(Elderly elderly) {
        // Check for appointment date and time
        for (Appointment appointment : elderly.getAppointments()) {
            // If fields are incorrect
            if (!isValidDate(appointment.getDate()) || !isValidTime(appointment.getTime())) {
                return false;
            }
        }
        // Check for employee email and phone number
        for (NextOfKin nextOfKin : elderly.getNextOfKin()) {
            if (!re.isValidEmail(nextOfKin.getNokEmail()) || !re.isValidHpNumber(nextOfKin.getNokPhoneNumber())) {
                return false;
            }
        }

        // Check for phone numbers
        for (Record record : elderly.getRecord()) {
            if (!re.isValidHpNumber(record.getElderlyPhoneNumber())) {
                return false;
            }
        }

        // Check values of blood pressure
        for (int bloodPressure : elderly.getElderlyBloodPressure()) {
            if (bloodPressure < -1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if high risk elderly has the correct information.
     *
     * @param elderly High Risk Elderly object.
     * @return Boolean Value.
     */
    private boolean checkIfHighElderlyParsedCorrectly(HighRiskElderly elderly) {
        boolean getHospitalNull = elderly.getHospital() == null;
        boolean getConditionsNull = elderly.getConditions() == null;
        boolean getNotesOnCareNull = elderly.getNotesOnCare() == null;
        boolean getDoctorNull = elderly.getDoctor() == null;
        return !getHospitalNull && !getConditionsNull && !getNotesOnCareNull && !getDoctorNull;
    }

    /**
     * Checks if medium risk elderly has the correct information.
     *
     * @param elderly Medium Risk Elderly object.
     * @return Boolean Value.
     */
    private boolean checkIfMediumElderlyParsedCorrectly(MediumRiskElderly elderly) {
        boolean getHospitalNull = elderly.getHospital() == null;
        boolean getConditionsNull = elderly.getConditions() == null;
        boolean getNotesOnCareNull = elderly.getNotesOnCare() == null;
        return !getHospitalNull && !getConditionsNull && !getNotesOnCareNull;
    }

    /**
     * Check the respective fields of the elderly to see if it is valid.
     *
     * @param elderly MediumRiskElderly or HighRiskElderly object.
     * @return Boolean Value.
     */
    private boolean checkFieldsForSpecialElderly(Elderly elderly) {
        if (elderly instanceof MediumRiskElderly) {
            MediumRiskElderly mediumRiskElderly = (MediumRiskElderly) elderly;
            Hospital hospital = mediumRiskElderly.getHospital();
            return re.isValidHpNumber(hospital.getHospitalNumber().toString());
        } else if (elderly instanceof HighRiskElderly) {
            HighRiskElderly highRiskElderly = (HighRiskElderly) elderly;
            Hospital hospital = highRiskElderly.getHospital();
            Doctor assignedDoctor = highRiskElderly.getDoctor();
            return re.isValidHpNumber(hospital.getHospitalNumber().toString())
                    && re.isValidHpNumber(assignedDoctor.getDoctorNumber().toString());
        }
        return true;
    }

    /**
     * Checks if elderly list is in the correct format.
     *
     * @return Boolean value.
     */
    private boolean isElderlyListCorrectFormat() {
        // Iterate through each elderly to check for compliance
        try {
            for (Elderly elderly : elderlyArrayList) {
                if (!isValidGenericElderly(elderly)) {
                    ui.printAffectedElderlyLoadIssue(elderly.getUsername());
                    return false;
                }
                // Checks if got duplicate elderly username
                if (elderlyCount(elderly.getUsername()) > 1) {
                    throw new DuplicateElderlyException();
                }
                if (elderly instanceof HighRiskElderly) {
                    if (!checkIfHighElderlyParsedCorrectly((HighRiskElderly) elderly)) {
                        ui.printAffectedElderlyLoadIssue(elderly.getUsername());
                        return false;
                    } else if (!checkFieldsForSpecialElderly(elderly)) {
                        ui.printAffectedElderlyLoadIssue(elderly.getUsername());
                        return false;
                    }
                }

                if (elderly instanceof MediumRiskElderly) {
                    if (!checkIfMediumElderlyParsedCorrectly((MediumRiskElderly) elderly)) {
                        ui.printAffectedElderlyLoadIssue(elderly.getUsername());
                        return false;
                    } else if (!checkFieldsForSpecialElderly(elderly)) {
                        ui.printAffectedElderlyLoadIssue(elderly.getUsername());
                        return false;
                    }
                }
            }
        } catch (DukeException e) {
            ui.printDukeException(e);
            return false;
        }
        return true;
    }

    /**
     * Load program's data JSON from a file using the given filepath.
     *
     * @param userLine String containing load command and filepath.
     */
    public void loadFromFilePath(String userLine) {
        try {
            // Checks if the command is issued correctly
            if (!re.isValidLoadFileFromFilePath(userLine)) {
                throw new InvalidLoadFromFilePathException();
            }

            String[] paramList = userLine.split(SPLIT_LOAD_FILE);
            String filePath = paramList[INDEX_OF_FILE_PATH];

            // Checks if file exists
            if (checkIfFileExists(filePath)) {
                this.filePath = filePath;
                ui.printFileExists(filePath);
            } else {
                // File does not exist, leave program
                ui.printFileDoesNotExists(filePath);
                return;
            }

            // Reads from file path and loads back into elderly list
            FileReader fr = new FileReader(this.filePath);
            JsonReader jr = gson.newJsonReader(fr);
            elderlyArrayList = gson.fromJson(jr, new TypeToken<ArrayList<Elderly>>() {
            }.getType());
            fr.close();

            // Check if in correct format, if it is not, purge list and show error
            if (!isElderlyListCorrectFormat()) {
                elderlyArrayList.clear();
                throw new JsonParseException("Error");
            }

            // Check if the toStrings are null
            getConsolidatedStringOfElderly();
        } catch (InvalidInputException e) {
            ui.printInvalidInputException(e);
        } catch (IOException e) {
            ui.printExceptionMessage(e);
        } catch (JsonParseException | NullPointerException wrongFormatException) {
            ui.printWrongFileSyntax();
        }
    }
}
