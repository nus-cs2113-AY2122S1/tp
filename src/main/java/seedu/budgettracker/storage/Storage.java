package seedu.budgettracker.storage;

import seedu.budgettracker.logic.commands.AddCommand;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.logic.parser.Parser;
import seedu.budgettracker.storage.textfiletools.ReadTextFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    public static String dataStorageDirectory = "./data/";
    public static final String LS = System.lineSeparator();

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

    public void makeStorageTextFile(String recordListDirectory) {
        LocalDate date = LocalDate.now();
        if (recordListDirectory.equals("")) {
            recordListDirectory = dataStorageDirectory + date.getYear() + ".txt";
        }
        File dataDirectory = new File("./data");
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

        if (containsJunk) {
            System.out.println("Your data directory contains junk files, please clean them up!");
        }
    }

}
