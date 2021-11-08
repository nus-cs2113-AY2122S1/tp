package medbot.storage;

import medbot.Appointment;
import medbot.Scheduler;
import medbot.exceptions.MedBotException;
import medbot.list.ListItem;
import medbot.list.ListItemType;
import medbot.person.Patient;
import medbot.person.Staff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Math.max;
import static medbot.ui.Ui.VERTICAL_LINE_SPACED_ESCAPED;

public abstract class Storage {
    protected static final String ERROR_CREATE_STORAGE = "\nERROR: MedBot is unable to create/detect"
            + " its storage files.\n";
    public static final String ERROR_SAVE_STORAGE = "\nERROR: MedBot is unable to save data into its storage files.\n";
    public static final String ERROR_MOVE_STORAGE_FILES = "\nPlease manually move tp.jar to "
            + "a location where it has read and write privileges before running it again.\n\n"
            + "Exiting MedBot...\n";
    protected static final String ERROR_NOT_LIST_ITEM = "Not a list item";

    protected File dataFile;
    protected String dataPath;

    /**
     * Generic Constructor with creates a storage text file if it doesn't already exist.
     *
     * @param dataPathString String that is the path of the storage file
     * @throws MedBotException if storage file cannot be created and does not exist
     */
    public Storage(String dataPathString) throws MedBotException {
        try {
            dataPath = dataPathString;
            dataFile = new File(dataPath);
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();

        } catch (IOException e) {
            throw new MedBotException(ERROR_CREATE_STORAGE + ERROR_MOVE_STORAGE_FILES);
        }
    }


    /**
     * Reads in storage file, parses each line and adds the data into MedBot
     * returns all line numbers of a storage file that are invalid.
     *
     * @param listItemType enum of ListItem type
     * @return Error message if there are formatting errors in storage file
     * @throws FileNotFoundException if storage file cannot be found
     */
    public String loadStorage(ListItemType listItemType, Scheduler scheduler) throws FileNotFoundException {
        int lineNumber = 1;
        Scanner s = new Scanner(dataFile);
        String loadStorageErrorMessage = "";

        while (s.hasNext()) {
            try {
                String storageLine = s.nextLine();
                addListItemFromStorageLine(listItemType, scheduler, storageLine);

            } catch (Exception e) {
                loadStorageErrorMessage += loadStorageLineErrorMessage(lineNumber);
            }
            lineNumber++;
        }

        return loadStorageErrorMessage;
    }


    /**
     * Add a ListItem object to the corresponding list,
     * from the text in a storage line in a storage file.
     *
     * @param listItemType enum of ListItem type
     * @param storageLine  a line in storage file
     * @throws MedBotException if fail to add a ListItem to the list
     */
    protected void addListItemFromStorageLine(ListItemType listItemType, Scheduler scheduler, String storageLine)
            throws MedBotException {
        ListItem listItem = createListItem(storageLine, listItemType);

        switch (listItemType) {
        case PATIENT:
            scheduler.addPatient((Patient) listItem);
            int lastPatientId = max(listItem.getId(), scheduler.getLastPatientId());
            scheduler.setLastPatientId(lastPatientId);
            break;
        case STAFF:
            scheduler.addStaff((Staff) listItem);
            int lastStaffId = max(listItem.getId(), scheduler.getLastStaffId());
            scheduler.setLastStaffId(lastStaffId);
            break;
        case APPOINTMENT:
            scheduler.addAppointment((Appointment) listItem);
            int lastAppointmentId = max(listItem.getId(), scheduler.getLastAppointmentId());
            scheduler.setLastAppointmentId(lastAppointmentId);
            break;
        default:
            throw new MedBotException(ERROR_NOT_LIST_ITEM);

        }
    }

    /**
     * Writes the storageString to storage file.
     *
     * @param storageString String containing the data of the list.
     * @throws IOException if unable to write to storage text file.
     */
    public void saveData(String storageString) throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        fw.write(storageString);
        fw.close();
    }


    /**
     * String split a line with " | " as the delimiters.
     *
     * @param storageLine a line in storage file
     * @return String[] with the parameters separated in different indexes in the array
     */
    protected static String[] splitStorageLine(String storageLine) {
        return storageLine.split(VERTICAL_LINE_SPACED_ESCAPED);
    }

    /**
     * True if "X", which means the parameter is null, false otherwise.
     *
     * @param parameter a parameter in a line of storage/data.txt
     * @return true if "X", which means the parameter is null, false otherwise
     */
    protected static boolean isStorageParameterNull(String parameter) {
        return parameter.equals("X");
    }

    /**
     * Error message that shows the line number of a line in storage file with improper formatting.
     *
     * @param lineNumber the line number with improper formatting in storage/data.txt
     * @return Error message
     */
    protected String loadStorageLineErrorMessage(int lineNumber) {
        return "Error: Line " + lineNumber + " of " + dataPath + " is invalid!\n";
    }

    /**
     * Template Method. Instantiates a ListItem interfaced object.
     *
     * @param storageLine  a line in storage file
     * @param listItemType enum of ListItem type
     * @return a ListItem interfaced object
     * @throws MedBotException if a ListItem object fails to be created
     */
    protected abstract ListItem createListItem(String storageLine, ListItemType listItemType) throws MedBotException;
}

