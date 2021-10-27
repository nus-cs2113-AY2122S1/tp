package medbot.storage;

import medbot.Scheduler;
import medbot.exceptions.MedBotException;
import medbot.ui.Ui;

import java.io.FileNotFoundException;

import static medbot.list.ListItemType.APPOINTMENT;
import static medbot.list.ListItemType.PATIENT;
import static medbot.list.ListItemType.STAFF;

public class StorageManager {
    private static PatientStorage patientStorage;
    private static StaffStorage staffStorage;
    private static AppointmentStorage appointmentStorage;
    protected static final String ERROR_INVALID_STORAGE_LINE_INSTRUCTION = "\n"
            + "Please decide if you wish to:" + "\n"
            + "1. Enter 'exit' to exit Medbot to correct the storage files" + "\n"
            + "2. Enter other valid commands to OVERWRITE all invalid data!" + "\n";

    /**
     * Initializes all storage classes to be used for MedBot, and prints out any errors in storage files.
     *
     * @param scheduler instance of scheduler class
     * @param ui        instance of ui class
     * @throws FileNotFoundException if any storage file not found
     * @throws MedBotException       If there are any errors in storage files
     */
    public StorageManager(Scheduler scheduler, Ui ui) throws FileNotFoundException, MedBotException {
        patientStorage = new PatientStorage();
        staffStorage = new StaffStorage();
        appointmentStorage = new AppointmentStorage();

        String loadStorageErrorMessage = loadStoragesAndGetErrorMessage(scheduler);
        if (!loadStorageErrorMessage.isBlank()) {
            loadStorageErrorMessage += ERROR_INVALID_STORAGE_LINE_INSTRUCTION;
            ui.printOutput("");
            ui.printOutput(loadStorageErrorMessage);
        }
    }


    /**
     * Save all relevant data from MedBot program to storage text files.
     *
     * @param scheduler instance of Scheduler class
     * @throws MedBotException if unable to save data to any of the storage text files
     */
    public void saveToStorage(Scheduler scheduler) throws MedBotException {
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
    public String loadStoragesAndGetErrorMessage(Scheduler scheduler) throws FileNotFoundException {

        return patientStorage.loadStorage(PATIENT, scheduler)
                + staffStorage.loadStorage(STAFF, scheduler)
                + appointmentStorage.loadStorage(APPOINTMENT, scheduler);
    }
}
