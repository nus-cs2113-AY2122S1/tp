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

    private static final Logger logger = Logger.getLogger("");
    private final String moduleToBeDeleted;
    private final Timetable timetable;

    public DeleteCommand(String moduleToBeDeleted, Timetable timetable) {
        this.moduleToBeDeleted = moduleToBeDeleted;
        this.timetable = timetable;
    }

    public void execute() {
        Module module;
        try {
            module = NusMods.fetchModOnline(moduleToBeDeleted);
            timetable.deleteModuleFromList(module);
            logger.log(Level.INFO, "The module was deleted successfully!");
        } catch (FetchException e) {
            System.out.println(TextUi.ERROR_INVALID_MODULE_CODE);
            logger.log(Level.WARNING, "The module code entered is invalid!");
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, "The module you are trying to delete does not exist in the timetable!");
        }

    }
}
