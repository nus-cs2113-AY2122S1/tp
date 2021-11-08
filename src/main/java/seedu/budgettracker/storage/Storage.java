//@@author YEOWEIHNGWHYELAB

package seedu.budgettracker.storage;

import seedu.budgettracker.logic.commands.AddCommand;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.logic.parser.Parser;
import seedu.budgettracker.storage.textfiletools.ReadTextFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is responsible for the initial loading of the data text file from the
 * data folder to the app or creating the data text file if there isn't any.
 */
public class Storage {
    public static String dataStorageDirectory = "./data/";
    public static final String LS = System.lineSeparator();

    /**
     * Loads the data text file to the app.
     *
     * @param recordList The AllRecordList object which the data from the data text file
     *                   will be loaded into.
     * @param recordListDirectory The directory of the data text file.
     * @return The directory string of the current data text file that have been loaded.
     */
    public String loadStorage(AllRecordList recordList, String recordListDirectory) {
        try {
            ReadTextFile newReader;
            LocalDate date = LocalDate.now();
            if (recordListDirectory.equals("")) {
                recordListDirectory = dataStorageDirectory + date.getYear() + ".txt";
            }
            newReader = new ReadTextFile(recordListDirectory);
            ArrayList<String> commandStorage = newReader.readTextFileToString();
            Parser parser = new Parser();
            int i;

            for (i = 0; i < commandStorage.size(); i += 1) {
                String userInput = commandStorage.get(i);
                AddCommand command = (AddCommand) parser.parseCommand(userInput);
                command.setAllRecordList(recordList);
                command.execute(true);
            }

            assert i == commandStorage.size();
        } catch (IOException i) {
            System.out.println("IOEXCEPTION Error!");
        } catch (ClassCastException cce) {
            System.out.println("Your current year data file is corrupted!");
            System.exit(0);
        }

        return recordListDirectory;
    }

    /**
     * Creates the data folder and the data text file if there isn't any.
     *
     * @param recordListDirectory The folder directory.
     */
    public void makeStorageTextFile(String recordListDirectory) {
        LocalDate date = LocalDate.now();
        if (recordListDirectory.equals("")) {
            recordListDirectory = dataStorageDirectory + date.getYear() + ".txt";
        }
        File dataDirectory = new File(dataStorageDirectory);
        File budgetList = new File(recordListDirectory);
        if (!(dataDirectory.exists())) {
            dataDirectory.mkdir();
            try {
                budgetList.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!budgetList.exists()) {
            try {
                budgetList.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * List out all the data text file in the data folder and prints warning to user
     * if there are junk files.
     */
    public void directoryListAllFiles() {
        System.out.print(LS);

        File dataDirectory = new File(dataStorageDirectory);
        File[] dataDirectoryList = dataDirectory.listFiles();

        String dataBaseFileName = "";
        String dataBaseYearName = "";
        boolean isValidName = false;
        boolean isFile = false;
        boolean containsJunk = false;
        boolean isValidFileType = true;

        containsJunk = isContainsJunk(dataDirectoryList, dataBaseFileName,
                dataBaseYearName, isValidName, isFile, containsJunk, isValidFileType);

        if (containsJunk) {
            System.out.println("Your data directory contains junk files, please clean them up!");
        }
    }

    /**
     * Check if the data folder has junk files - anything other than "YYYY.txt" file
     * format. ".csv" file created from the "csv" command is not considered junk file.
     *
     * @param dataDirectoryList List of data file names.
     * @param dataBaseFileName Placeholder for the data file name.
     * @param dataBaseYearName Placeholder for data file name without the ".txt" - "YYYY"
     * @param isValidName Placeholder to check for the validity of file name.
     * @param isFile Placeholder for checking if a directory stated is a file.
     * @param containsJunk Boolean to check if there is indeed junk files in a directory.
     * @param isValidFileType Boolean to check if the file is of ".txt"
     * @return Whether the data folder contains junk files.
     */
    private boolean isContainsJunk(File[] dataDirectoryList, String dataBaseFileName,
                                   String dataBaseYearName, boolean isValidName,
                                   boolean isFile, boolean containsJunk,
                                   boolean isValidFileType) {
        for (int i = 0; i < dataDirectoryList.length; i++) {
            if (dataDirectoryList[i].isFile()) {
                dataBaseFileName = dataDirectoryList[i].getName();
                dataBaseYearName = dataBaseFileName.split("\\.")[0];

                isFile = true;
            }

            if (isFile && dataBaseYearName.matches("^[0-9]{4}$")) {
                isValidName = true;
            }

            if (!(dataBaseFileName.split("\\.")[1].equals("txt"))) {
                isValidFileType = false;
            }

            if (isFile && isValidName && isValidFileType) {
                System.out.println(dataBaseFileName);
            } else if (!isValidFileType) {
                isValidFileType = true;
                continue;
            } else {
                containsJunk = true;
            }

            isValidName = false;
            isFile = true;
        }
        return containsJunk;
    }

}
