package command;

import inventory.Medicine;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Helps to process the exit command and prints the exit message.
 */


public class ExitCommand extends Command{

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        ui.printExit();
    }
}
