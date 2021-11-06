package command;

import constants.HelpMessage;
import picocli.CommandLine.Command;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "help", description = "Displays the help message of the program.")
public class HelpCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ui.printMessage(HelpMessage.helpMsg);
        return 0;
    }
}