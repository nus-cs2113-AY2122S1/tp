package seedu.duke;

import seedu.commands.Command;
import seedu.commands.ExitCommand;

import seedu.utility.DataManager;
import seedu.utility.FinancialTracker;

import seedu.utility.Ui;


public class StonksXD {
    private Ui ui;
    private FinancialTracker finances;
    private Parser parser;
    private DataManager dataManager;

    public StonksXD() {
        this.ui = new Ui();
        this.finances = new FinancialTracker();
        this.parser = new Parser();
        this.dataManager = new DataManager();
    }

    public void run() {
        dataManager.load(parser, finances, ui);
        ui.printWelcome();

        boolean exitFlag = true;
        while (exitFlag) {
            String fullCommand = ui.readCommand();
            Command command = parser.parseCommand(fullCommand);
            command.execute(finances, ui);
            if (command.isExit()) {
                assert command.getClass() == ExitCommand.class;
                exitFlag = false;
            }
            dataManager.save(parser, finances, ui);
        }
        ui.printBye();
    }

    public static void main(String[] args) {
        new StonksXD().run();
    }
}
