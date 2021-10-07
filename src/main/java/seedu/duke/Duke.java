package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.data.RecordList;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;


public class Duke {

    private TextUi textUi;
    private Parser parser;
    private RecordList budgetList;

    public Duke() {
        budgetList = new RecordList();
        textUi = new TextUi();
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        TextUi.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            String userInput = textUi.getUserInput();
            Command command = parser.parseCommand(userInput);
            command.setBudgetList(budgetList);
            command.execute();
            isExit = command.isExit();
        }
    }
}
