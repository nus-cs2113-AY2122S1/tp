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
    private static final String DATA_PATH = "storage/data.txt";
    private static final File DATA_FILE = new File(DATA_PATH);

    private static final String CREATED_NEW_FILE = "New data file created.";
    private static final String ERROR_LOAD_STORAGE = "Error: Unable to load data file.";
    private static final String ERROR_SAVE_STORAGE = "Error: Unable to save data.";

    private static final String ERROR_INVALID_STORAGE_LINE = "\n" +
            "I am done reading " + DATA_PATH + "\n" +
            "1. Enter 'exit' to exit program to correct data file " + DATA_PATH + "\n" +
            "2. Enter other valid commands to OVERWRITE all invalid data!" + "\n";

    public Storage() throws MedBotException {
        try {
            DATA_FILE.getParentFile().mkdirs();
            if (DATA_FILE.createNewFile()) {
                System.out.println(CREATED_NEW_FILE);
            }
        } catch (IOException e) {
            throw new MedBotException(ERROR_LOAD_STORAGE);
        }
    }

    public void loadStorage(PatientList patientList) throws FileNotFoundException {
        boolean hasInvalidStorageLine = false;
        int lineNumber = 1;
        int lastId = 1;
        Scanner s = new Scanner(DATA_FILE);

        while (s.hasNext()) {
            Patient patient = new Patient();
            try {
                patient = parseStorageLine(s.nextLine());
                patientList.addPatientFromStorage(patient);

            } catch (Exception e) {
                System.out.println("Error: Line " + lineNumber + " of " + DATA_PATH + " is invalid! Skipping to next line...");
                hasInvalidStorageLine = true;
            }

            lineNumber++;
            lastId = patient.getPatientId();
        }
        patientList.setLastId(lastId);

        if (hasInvalidStorageLine) {
            System.out.println(ERROR_INVALID_STORAGE_LINE);
        }
    }

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

    private static boolean isStorageParameterNull(String parameter) {
        return parameter.equals("X");
    }

    public void saveData(PatientList patientList) throws MedBotException {
        try {
            FileWriter fw = new FileWriter(DATA_PATH);
            fw.write(patientList.getStorageString());
            fw.close();
        } catch (IOException e) {
            throw new MedBotException(ERROR_SAVE_STORAGE);
        }
    }

}
