package medbot.storage;

import medbot.Scheduler;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import static medbot.list.ListItemType.APPOINTMENT;
import static medbot.list.ListItemType.PATIENT;
import static medbot.list.ListItemType.STAFF;

public class StorageManager {
    private static PatientStorage patientStorage;
    private static StaffStorage staffStorage;
    private static AppointmentStorage appointmentStorage;
    protected static final String ERROR_INVALID_STORAGE_LINE_INSTRUCTION = "\n"
            + "Please decide if you wish to:" + "\n"
            + "1. Enter 'exit' to exit MedBot to correct the storage files" + "\n"
            + "2. Enter other valid commands to OVERWRITE all invalid data!" + "\n";
    protected static final String ERROR_LOAD_STORAGE = "ERROR: MedBot has issues finding some or "
            + "all of the storage files to load!";

    public StorageManager() {
    }

    /**
     * Initializes all storage classes to be used for MedBot, and prints out the line number of invalid lines in
     * the storage files and the name of the respective storage file.
     *
     * @param scheduler instance of scheduler class
     * @param ui        instance of ui class
     * @throws MedBotException if unable to load any of the storage files
     */
    public void initializeStorages(Scheduler scheduler, Ui ui) throws MedBotException {
        patientStorage = new PatientStorage();
        staffStorage = new StaffStorage();
        appointmentStorage = new AppointmentStorage();

        try {
            String loadStorageErrorMessage = loadStoragesAndGetErrorMessage(scheduler);
            if (!loadStorageErrorMessage.isBlank()) {
                loadStorageErrorMessage += ERROR_INVALID_STORAGE_LINE_INSTRUCTION;
                ui.printOutput("");
                ui.printOutput(loadStorageErrorMessage);
            }
        } catch (FileNotFoundException e) {
            throw new MedBotException(ERROR_LOAD_STORAGE);
        }
    }


    /**
     * Save all relevant data from MedBot program to storage text files.
     *
     * @param scheduler instance of Scheduler class
     * @throws IOException if unable to save data to any of the storage text files
     */
    public void saveToStorage(Scheduler scheduler) throws IOException {
        patientStorage.saveData(scheduler.getPatientStorageString());
        staffStorage.saveData(scheduler.getStaffStorageString());
        appointmentStorage.saveData(scheduler.getAppointmentStorageString());
    }

    /**
     * Load storage text file data into MedBot program. Print any errors in the storage text file data.
     *
     * @param scheduler instance of Scheduler class
     * @return error message of the relevant lines with errors in the storage text files
     * @throws FileNotFoundException if storage text files cannot be found
     */
    private String loadStoragesAndGetErrorMessage(Scheduler scheduler) throws FileNotFoundException {

        return patientStorage.loadStorage(PATIENT, scheduler)
                + staffStorage.loadStorage(STAFF, scheduler)
                + appointmentStorage.loadStorage(APPOINTMENT, scheduler);
    }
}
