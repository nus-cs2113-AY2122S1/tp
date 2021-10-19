package medbot;

import medbot.exceptions.MedBotException;
import medbot.list.PatientList;
import medbot.person.Patient;
import medbot.utilities.Pair;

import static medbot.Parser.updatePersonalInformation;
import static medbot.Ui.VERTICAL_LINE_SPACED_ESCAPED;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private static final String DEFAULT_DATA_PATH = "storage/data.txt";

    private static final String CREATED_NEW_FILE = "New data file created.";
    private static final String ERROR_LOAD_STORAGE = "Error: Unable to load data file.";
    private static final String ERROR_SAVE_STORAGE = "Error: Unable to save data.";

    private static final String ERROR_INVALID_STORAGE_LINE_INSTRUCTION = "\n\n"
            + "Please decide if you wish to:" + "\n"
            + "1. Enter 'exit' to exit Medbot to correct the storage file" + "\n"
            + "2. Enter other valid commands to OVERWRITE all invalid data!" + "\n";

    private static File dataFile;
    private static String actualDataPath;

    /**
     * Constructor
     * which creates a storage/data.txt file if it doesn't exist.
     *
     * @throws MedBotException if storage/data.txt cannot be created and does not exist
     */
    public Storage() throws MedBotException {
        this(DEFAULT_DATA_PATH);
    }

    public Storage(String dataPath) throws MedBotException {
        try {
            actualDataPath = dataPath;
            dataFile = new File(actualDataPath);
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();

        } catch (IOException e) {
            throw new MedBotException(ERROR_LOAD_STORAGE);
        }
    }


    /**
     * Reads in storage/data.txt file, parses each line and adds the data into the program
     * returns all line numbers of storage/data.txt that are invalid
     *
     * @param patientList instance of PatientList
     * @return Error message if there are formatting errors in storage/data.txt
     * @throws FileNotFoundException if storage/data.txt cannot be found
     */
    public String loadStorage(PatientList patientList) throws FileNotFoundException {
        int lineNumber = 1;
        int lastId = 1;
        Scanner s = new Scanner(dataFile);
        String loadStorageErrorMessage = "";

        while (s.hasNext()) {
            Patient patient;

            try {
                String storageLine = s.nextLine();
                Pair<Integer, ArrayList<String>> patientDetails = parseStorageLine(storageLine);
                patient = createPatient(patientDetails);
                patientList.addPersonFromStorage(patient);
                lastId = patient.getPersonId();

            } catch (Exception e) {
                loadStorageErrorMessage += loadStorageLineErrorMessage(lineNumber);
            }

            lineNumber++;
        }
        patientList.setLastId(lastId);

        if (!loadStorageErrorMessage.isBlank()) {
            loadStorageErrorMessage += ERROR_INVALID_STORAGE_LINE_INSTRUCTION;
        }
        return loadStorageErrorMessage;
    }

    /**
     * Parse a line from storage/data.txt by splitting its constituent parts.
     *
     * @param storageLine a line from storage/data.txt
     * @return patient details, consisting of patient ID and other parameters
     */
    private static Pair<Integer, ArrayList<String>> parseStorageLine(String storageLine) {
        if (storageLine.isBlank()) {
            return null;
        }

        String[] personParameters = splitStorageLine(storageLine);
        String[] parameterPrefixes = {"i/", "n/", "p/", "e/", "a/"};

        ArrayList<String> prefixPlusPersonParameters = new ArrayList<>();

        Integer patientId = Integer.parseInt(personParameters[0]);

        for (int i = 0; i < parameterPrefixes.length; i++) {
            // i + 1, since personParameters[0] is the patientId
            if (isStorageParameterNull(personParameters[i + 1])) {
                continue;
            }
            // i + 1, since personParameters[0] is the patientId
            String prefixPlusPersonParameter = parameterPrefixes[i] + personParameters[i + 1];
            prefixPlusPersonParameters.add(prefixPlusPersonParameter);
        }

        assert personParameters.length == parameterPrefixes.length + 1;

        return new Pair<>(patientId, prefixPlusPersonParameters);
    }

    /**
     * Create a patient object from the given parameters/details.
     *
     * @param patientDetails contains all the parameters of the patient, eg. i/S8712345A
     * @return a patient object with the given parameters. return null if patientDetails == null
     * @throws MedBotException if fail to update patient's personal information
     */
    private Patient createPatient(Pair<Integer, ArrayList<String>> patientDetails) throws MedBotException {
        if (patientDetails == null) {
            return null;
        }

        Patient patient = new Patient();
        int patientId = patientDetails.first;
        ArrayList<String> prefixPlusPersonParameters = patientDetails.second;

        patient.setPersonId(patientId);

        for (String prefixPlusPersonParameter : prefixPlusPersonParameters) {
            //updatePersonalInformation does error-checking of patient details
            //in addition to updating patient info
            updatePersonalInformation(patient, prefixPlusPersonParameter);
        }

        return patient;
    }

    /**
     * String split a line with " | " as the delimiters.
     *
     * @param storageLine a line in storage/data.txt
     * @return String[] with the parameters separated in different indexes in the array
     */
    private static String[] splitStorageLine(String storageLine) {
        return storageLine.split(VERTICAL_LINE_SPACED_ESCAPED);
    }

    /**
     * True if "X", which means the parameter is null, false otherwise.
     *
     * @param parameter a parameter in a line of storage/data.txt
     * @return true if "X", which means the parameter is null, false otherwise
     */
    private static boolean isStorageParameterNull(String parameter) {
        return parameter.equals("X");
    }

    /**
     * Write patientList storageString to storage/data.txt.
     *
     * @param patientList instance of PatientList
     * @throws MedBotException if unable to write to storage/data.txt
     */
    public void saveData(PatientList patientList) throws MedBotException {
        try {
            FileWriter fw = new FileWriter(actualDataPath);
            fw.write(patientList.getStorageString());
            fw.close();
        } catch (IOException e) {
            throw new MedBotException(ERROR_SAVE_STORAGE);
        }
    }


    /**
     * Error message that shows the line number of a line in storage/data.txt with improper formatting.
     *
     * @param lineNumber the line number with improper formatting in storage/data.txt
     * @return Error message
     */
    private String loadStorageLineErrorMessage(int lineNumber) {
        return "Error: Line " + lineNumber + " of " + DEFAULT_DATA_PATH
                + " is invalid!\n";
    }

}

