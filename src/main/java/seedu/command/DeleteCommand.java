package seedu.command;

import seedu.exceptions.FetchException;
import seedu.exceptions.UniModsException;
import seedu.module.Module;
import seedu.online.NusMods;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCommand extends Command {

    public static final String commandSyntax = "delete <MODULE_CODE/TASK>";
    public static final String commandAction =
            "Removes the module or personal task from timetable";

    private static final Logger logger = Logger.getLogger("");
    private final String itemToBeDeleted;
    private final Timetable timetable;

    public DeleteCommand(String itemToBeDeleted, Timetable timetable) {
        this.itemToBeDeleted = itemToBeDeleted;
        this.timetable = timetable;
    }

    public void execute() {
        try {
            timetable.deleteModuleFromList(itemToBeDeleted);
            timetable.deleteFromSchedules(itemToBeDeleted);
            logger.log(Level.INFO, "The module was deleted successfully!");
        }  catch (UniModsException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "The module you are trying to delete does not exist in the timetable!");
        }

    }
}
