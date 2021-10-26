package medbot.storage;

import medbot.Scheduler;
import medbot.exceptions.MedBotException;
import medbot.list.MedicalStaffList;
import medbot.list.PatientList;
import medbot.list.SchedulerAppointmentList;
import medbot.ui.Ui;

import java.io.FileNotFoundException;

import static medbot.list.ListItemType.APPOINTMENT;
import static medbot.list.ListItemType.PATIENT;
import static medbot.list.ListItemType.STAFF;

public class StorageManager {
    private static PatientStorage patientStorage;
    private static StaffStorage staffStorage;
    private static AppointmentStorage appointmentStorage;

    /**
     * Initializes all storage classes to be used for Medbot, and prints out any errors in storage files.
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
            ui.printOutput(loadStorageErrorMessage);
        }
    }


    /**
     * Save all relevant data from Medbot program to storage text files.
     *
     * @param scheduler instance of Scheduler class
     * @throws MedBotException if unable to save data to any of the storage text files
     */
    public void saveToStorage(Scheduler scheduler) throws MedBotException {
        patientStorage.saveData(scheduler.getPatientList());
        staffStorage.saveData(scheduler.getMedicalStaffList());
        appointmentStorage.saveData(scheduler.getSchedulerAppointmentList());
    }

    /**
     * Load storage text file data into Medbot program. Print any errors in the storage text file data.
     *
     * @param scheduler instance of Scheduler class
     * @return error message of the relevant lines with errors in the storage text files
     * @throws FileNotFoundException if storage text files cannot be found
     */
    private String loadStoragesAndGetErrorMessage(Scheduler scheduler) throws FileNotFoundException {
        PatientList patientList = scheduler.getPatientList();
        MedicalStaffList medicalStaffList = scheduler.getMedicalStaffList();
        SchedulerAppointmentList schedulerAppointmentList = scheduler.getSchedulerAppointmentList();

        return patientStorage.loadStorage(PATIENT, patientList)
                + staffStorage.loadStorage(STAFF, medicalStaffList)
                + appointmentStorage.loadStorage(APPOINTMENT, schedulerAppointmentList);
    }
}
