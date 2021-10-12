package medbot;

import medbot.person.Patient;

import static medbot.Parser.updatePersonalInformation;
import static medbot.Ui.DATA_SEPARATOR_ESCAPED;

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

    private static final String ERROR_INVALID_STORAGE_LINE = "\n\n"
            + "Please decide if you wish to:" + "\n"
            + "1. Enter 'exit' to exit Medbot to correct the data file " + DEFAULT_DATA_PATH + "\n"
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
     * prints out the line number of storage/data.txt that is invalid
     *
     * @param patientList instance of PatientList
     * @return Error message if there are errors in storage/data.txt
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
                patient = parseStorageLine(s.nextLine());
                patientList.addPatientFromStorage(patient);
                lastId = patient.getPatientId();

            } catch (Exception e) {
                loadStorageErrorMessage += loadStorageLineError(lineNumber);
            }

            lineNumber++;
        }
        patientList.setLastId(lastId);

        if (!loadStorageErrorMessage.isBlank()) {
            loadStorageErrorMessage += ERROR_INVALID_STORAGE_LINE;
        }
        return loadStorageErrorMessage;
    }

    /**
     * Create a patient instance from information in a storageLine.
     *
     * @param storageLine a line from storage/data.txt
     * @return a patient instance with the parameters specified by storageLine
     * @throws MedBotException if prefixPlusParameter is invalid
     */
    private static Patient parseStorageLine(String storageLine) throws MedBotException {
        if (storageLine.isBlank()) {
            return null;
        }

        String[] personParameters = storageLine.split(DATA_SEPARATOR_ESCAPED);

        String[] parameterPrefixes = {"i/", "n/", "p/", "e/", "a/"};
        ArrayList<String> prefixPlusPersonParameters = new ArrayList<>();

        for (int i = 0; i < parameterPrefixes.length; i++) {
            if (isStorageParameterNull(personParameters[i + 1])) {
                continue;
            }
            String prefixPlusPersonParameter = parameterPrefixes[i] + personParameters[i + 1];
            prefixPlusPersonParameters.add(prefixPlusPersonParameter);
        }

        Patient patient = new Patient();
        int patientId = Integer.parseInt(personParameters[0]);
        patient.setPatientId(patientId);

        for (String prefixPlusPersonParameter : prefixPlusPersonParameters) {
            updatePersonalInformation(patient, prefixPlusPersonParameter);
        }

        return patient;
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
     * write patientList storageString to storage/data.txt
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


    private String loadStorageLineError(int lineNumber) {
        return "Error: Line " + lineNumber + " of " + DEFAULT_DATA_PATH
                + " is invalid!";
    }

}
