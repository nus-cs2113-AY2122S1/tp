package medbot.storage;

import medbot.exceptions.MedBotException;
import medbot.list.ListItem;
import medbot.list.ListItemType;
import medbot.list.MedBotList;
import medbot.utilities.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static medbot.ui.Ui.VERTICAL_LINE_SPACED_ESCAPED;

public abstract class Storage {
    protected static final String ERROR_LOAD_STORAGE = "Error: Unable to load data file.";
    protected static final String ERROR_SAVE_STORAGE = "Error: Unable to save data.";
    protected static final String ERROR_INVALID_STORAGE_LINE_INSTRUCTION = "\n\n"
            + "Please decide if you wish to:" + "\n"
            + "1. Enter 'exit' to exit Medbot to correct the storage file" + "\n"
            + "2. Enter other valid commands to OVERWRITE all invalid data!" + "\n";

    protected File dataFile;
    protected String dataPath;

    /**
     * Generic Constructor with creates a storage file if it doesn't already exist.
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
            throw new MedBotException(ERROR_LOAD_STORAGE);
        }
    }


    /**
     * Reads in storage/data.txt file, parses each line and adds the data into the program
     * returns all line numbers of storage file that are invalid.
     *
     * @param listItemType enum of ListItem type
     * @param medBotList   instance of a subclass of MedBotList
     * @return Error message if there are formatting errors in storage/data.txt
     * @throws FileNotFoundException if storage/data.txt cannot be found
     */
    public String loadStorage(ListItemType listItemType, MedBotList medBotList) throws FileNotFoundException {
        int lineNumber = 1;
        Scanner s = new Scanner(dataFile);
        String loadStorageErrorMessage = "";

        while (s.hasNext()) {
            try {
                String storageLine = s.nextLine();
                addListItemFromStorageLine(listItemType, storageLine, medBotList);

            } catch (Exception e) {
                loadStorageErrorMessage += loadStorageLineErrorMessage(lineNumber);
            }
            lineNumber++;
        }

        //TODO unify the error message
        if (!loadStorageErrorMessage.isBlank()) {
            loadStorageErrorMessage += ERROR_INVALID_STORAGE_LINE_INSTRUCTION;
        }
        return loadStorageErrorMessage;
    }


    protected void addListItemFromStorageLine(
            ListItemType listItemType, String storageLine,
            MedBotList medBotList) throws MedBotException {
        ListItem listItem = createListItem(storageLine, listItemType);
        medBotList.addListItemFromStorage(listItem);
        setListItemIdAsLastId(listItem, medBotList);
    }

    void setListItemIdAsLastId(ListItem listItem, MedBotList medBotList) {
        int lastId = listItem.getId();
        medBotList.setLastId(lastId);
    }


    /**
     * Write List's (PatientList etc.) storageString to storage file.
     *
     * @param medBotList instance of a subclass of MedBotList
     * @throws MedBotException if unable to write to storage text file.
     */
    public void saveData(MedBotList medBotList) throws MedBotException {
        try {
            FileWriter fw = new FileWriter(dataPath);
            fw.write(medBotList.getStorageString());
            fw.close();
        } catch (IOException e) {
            throw new MedBotException(ERROR_SAVE_STORAGE);
        }
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
     * Error message that shows the line number of a line in storage/data.txt with improper formatting.
     *
     * @param lineNumber the line number with improper formatting in storage/data.txt
     * @return Error message
     */
    protected String loadStorageLineErrorMessage(int lineNumber) {
        return "Error: Line " + lineNumber + " of " + dataPath
                + " is invalid!\n";
    }

    /**
     * Template Method. Instantiates a ListItem object.
     *
     * @param storageLine  a line in storage file
     * @param listItemType enum of ListItem type
     * @return a ListItem object
     * @throws MedBotException if a ListItem object fails to be created
     */
    protected ListItem createListItem(String storageLine, ListItemType listItemType) throws MedBotException {
        return new ListItem();
    }


    /**
     * Template Method. Parses a line from storage file and organises the data.
     *
     * @param storageLine a line in the storage file
     * @return a Pair containing the data required to create a ListItem type
     */
    protected Pair<Integer, ArrayList<String>> parseStorageLine(String storageLine) {
        return new Pair<>(0, new ArrayList<>());
    }
}

