package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.AllRecordList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.TextUi;

import static seedu.duke.common.Messages.MESSAGE_INVALID_MONTH;
import static seedu.duke.common.Messages.MESSAGE_INVALID_INPUT;

public class Duke {
    private final TextUi textUi;
    private final Parser parser;
    private final AllRecordList recordList;
    private final String recordListDirectory = "";

    public Duke() {
        recordList = new AllRecordList();
        textUi = new TextUi();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        TextUi.showWelcomeMessage();
        // System.out.println("Welcome");
        Storage budgetStorage = new Storage();
        budgetStorage.makeStorageTextFile(recordListDirectory);
        recordList.storageDirectory = budgetStorage.loadStorage(recordList, recordListDirectory);
        boolean isExit = false;
        recordList.statIntro(recordList);

        while (!isExit) {
            try {
                String userInput = textUi.getUserInput();
                Command command = parser.parseCommand(userInput);
                command.setAllRecordList(recordList);
                command.execute(false);
                isExit = command.isExit();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(MESSAGE_INVALID_INPUT);
                TextUi.printDivider();
            } catch (NullPointerException npe) {
                System.out.println(MESSAGE_INVALID_MONTH);
                TextUi.printDivider();
            }
        }
    }
}