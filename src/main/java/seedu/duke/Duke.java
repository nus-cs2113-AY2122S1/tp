package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.RecordList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.TextUi;

import java.io.File;
import java.io.IOException;


public class Duke {

    private TextUi textUi;
    private Parser parser;
    private final RecordList recordList;

    public Duke() {
        recordList = new RecordList();
        textUi = new TextUi();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        TextUi.showWelcomeMessage();
        boolean isExit = false;

        // To be placed somewhere else later
        //----------------------------------------------------------------------
        File dataDirectory = new File("./data");
        File budgetList = new File("./data/BudgetList1.txt");
        if (!(dataDirectory.exists())) {
            dataDirectory.mkdir();
            try {
                budgetList.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (budgetList.exists() == false) {
            try {
                budgetList.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //----------------------------------------------------------------------

        Storage budgetStorage = new Storage();
        try {
            budgetStorage.loadStorage(recordList);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        while (!isExit) {
            try {
                String userInput = textUi.getUserInput();
                Command command = parser.parseCommand(userInput);
                command.setRecordList(recordList);
                command.execute(false);
                isExit = command.isExit();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! Your inputs are missing or incorrect!");
            }
        }
    }
}

/*
budgetList
1. Budget Jan
 */
