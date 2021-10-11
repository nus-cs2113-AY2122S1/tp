package seedu.command;

import seedu.exceptions.UniModsException;
import seedu.module.Module;
import seedu.online.NusMods;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final String moduleToBeDeleted;
    private Timetable timetable;

    public DeleteCommand(String moduleToBeDeleted, Timetable timetable) {
        this.moduleToBeDeleted = moduleToBeDeleted;
        this.timetable = timetable;
    }

    public void execute() {
        Module module;
        try {
            module = NusMods.fetchModOnline(moduleToBeDeleted);
            timetable.deleteModuleFromList(module);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(TextUi.ERROR_INVALID_MODULE_CODE);
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
        }

    }
}
