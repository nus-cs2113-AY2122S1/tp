package command;

import picocli.CommandLine.Command;
import terminal.Ui;

import java.util.concurrent.Callable;

@Command(name = "exit", description = "Exit the program.")
public class ExitCommand implements Callable<Integer> {

    public Integer call() throws Exception {
        Ui ui = Ui.getUi();
        ui.printMessage("Goodbye!");
        System.exit(0);
        return 0;
    }
}
