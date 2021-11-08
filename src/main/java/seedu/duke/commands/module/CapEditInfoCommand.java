package seedu.duke.commands.module;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ClickException;
import seedu.duke.exceptions.module.IllegalCurrentCapException;
import seedu.duke.exceptions.module.IllegalTotalMcTakenException;
import seedu.duke.module.ModuleManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

//@@author nvbinh15

/**
 * A representation of the command for editing information related to CAP.
 */
public class CapEditInfoCommand extends Command {

    public static final String GET_CAP_QUESTION = "What is your current cumulative average point (CAP)?";
    public static final String GET_MC_QUESTION = "How many modular credits contributing to CAP you have taken?";
    public static final String CAP_EDIT_CONFIRM_MESSAGE = "Thank you for your information. "
            + "You can view your expected CAP by keying in cap expected.";

    ModuleManager moduleManager = new ModuleManager();

    /**
     * Class constructor.
     */
    public CapEditInfoCommand() {
        helpMessage = "Get user's information to calculate expected CAP";
        syntax = "cap edit";
    }

    /**
     * Executes the CapEditInfoCommand.
     *
     * @param ui      The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws ClickException If there is an exception of type ClickException occurs.
     * @throws IOException If there is an error while saving data to file.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws ClickException, IOException {
        ui.printLine();
        ui.printMessage(GET_CAP_QUESTION);
        Scanner scanner = new Scanner(System.in);
        double currentCap;
        try {
            currentCap = Double.parseDouble(ui.getUserInput(scanner));
            if (currentCap < 0.0 || currentCap > 5.0) {
                throw new IllegalCurrentCapException();
            }
        } catch (Exception e) {
            throw new IllegalCurrentCapException();
        }

        ui.printMessage(GET_MC_QUESTION);
        int totalMcTaken;
        try {
            totalMcTaken = Integer.parseInt(ui.getUserInput(scanner));
            if (totalMcTaken <= 0) {
                throw new IllegalTotalMcTakenException();
            }
        } catch (Exception e) {
            throw new IllegalTotalMcTakenException();
        }
        ui.printMessage(CAP_EDIT_CONFIRM_MESSAGE);
        ui.printLine();
        moduleManager.setCapInfo(currentCap, totalMcTaken);
    }
}
