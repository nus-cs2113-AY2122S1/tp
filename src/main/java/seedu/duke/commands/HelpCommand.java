package seedu.duke.commands;


import seedu.duke.constants.CommandConstants;
import seedu.duke.constants.Messages;
import seedu.duke.help.ClassPackageReader;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author ngnigel99
import java.lang.reflect.InvocationTargetException;

public class HelpCommand extends Command {
    private static String runtimeKeyword = "rt";
    public static boolean runTime = false;

    public HelpCommand() {
        syntax = "help";
    }

    /**
     * Constructor if developer mode is enabled.
     * Syntax is "help rt", and should only be known
     *  by the developers.
     * @param keyword keyword to toggle runtime
     */
    public HelpCommand(String keyword)  {
        if (keyword.equals(runtimeKeyword)) {
            runTime = !runTime;
        }
    }

    /**
     * Finds all valid commands at runtime and prints syntax to user.
     * @param ui      The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws InvocationTargetException If printSyntax method fails to run.
     * @throws InstantiationException If printSyntax method fails to run.
     * @throws IllegalAccessException If printSyntax method fails to run.
     *
     * @author ngnigel99
     */
    @Override
    public void execute(Ui ui, Storage storage) throws InvocationTargetException,
            InstantiationException, IllegalAccessException {
        if (runTime) {
            Ui.printMessage(Messages.PRINT_RUNTIME_MODE);
            Ui.printLine();
            ClassPackageReader.getCommandsAndPrintSyntax(); //- to be done using source files (by developers)
            Ui.printMessage(Messages.PRINT_RUNTIME_MODE);
        } else {
            Ui.printLine();
            System.out.println(CommandConstants.HELP_MESSAGES);
        }
        Ui.printLine();
    }

}
