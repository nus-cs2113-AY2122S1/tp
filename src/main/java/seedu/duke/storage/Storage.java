package seedu.duke.storage;

import seedu.duke.commands.Command;
import seedu.duke.data.RecordList;
import seedu.duke.parser.Parser;
import seedu.duke.textfiletools.ReadTextFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public static String dataStorageDirectory = "./data";
    public static File directoryOfBudgetExpenditure = new File(dataStorageDirectory);

    public static int numberOfBudget = 0;
    public static int numberOfExpenditure = 0;

    public static void loadStorage(RecordList recordList) throws IOException {
        ReadTextFile newReader = new ReadTextFile("./data/BudgetList1.txt");
        ArrayList<String> commandStorage = newReader.readTextFileToString();
        Parser parser = new Parser();
        int i;
        for (i = 0; i < commandStorage.size(); i += 1) {
            String userInput = commandStorage.get(i);
            Command command = parser.parseCommand(userInput);
            command.setRecordList(recordList);
            command.execute(true);
        }

        assert i == commandStorage.size();
    }

    public static void makeStorageDirectory() {
        if (directoryOfBudgetExpenditure.exists() == false) {
            directoryOfBudgetExpenditure.mkdir();
        }
    }

    // Catch IOException!
    public static void makeBudgetFile(String budgetStorageName) throws IOException {
        String newBudgetStorageDirectory =  dataStorageDirectory + budgetStorageName;
        File newBudgetStorageFile = new File(newBudgetStorageDirectory);

        if (newBudgetStorageFile.exists() == false) {
            newBudgetStorageFile.createNewFile();
        } else {
            // Add exception here!
            System.out.println("File already exist!");
        }
    }

}
