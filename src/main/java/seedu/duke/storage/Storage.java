package seedu.duke.storage;

import seedu.duke.commands.AddCommand;
import seedu.duke.data.AllRecordList;
import seedu.duke.parser.Parser;
import seedu.duke.textfiletools.ReadTextFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    public static String dataStorageDirectory = "./data/";

    public String loadStorage(AllRecordList recordList, String recordListDirectory) {
        try {
            LocalDate date = LocalDate.now();
            if (recordListDirectory.equals("")) {
                recordListDirectory = dataStorageDirectory + date.getYear() + ".txt";
            }
            ReadTextFile newReader = new ReadTextFile(recordListDirectory);
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
            System.out.println("Error");
        } catch (ClassCastException cce) {
            System.out.println("Data File is corrupted");
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
}
