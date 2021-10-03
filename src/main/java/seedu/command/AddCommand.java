package seedu.command;

import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.module.Module;
import seedu.module.Semester;
import seedu.timetable.TimeTable;
import seedu.ui.TextUi;

import java.io.IOException;
import java.sql.Time;

public class AddCommand extends Command {
    private final String moduleCode;
    private final Integer semester;

    public AddCommand(String moduleCode, Integer semester) {
        this.moduleCode = moduleCode;
        this.semester = semester;
    }

    public void execute() {
        Module module = new Module(moduleCode);
        try {
            module = NusMods.fetchModOnline(moduleCode);
        } catch (IOException e1) {
            TextUi.printNoConnectionMessage();
            try {
                module = ModStorage.loadModInfo(moduleCode);
            } catch (IOException e2) {
                TextUi.printNotFoundMessage();
            }
        }
        Semester semesterData = module.getSemesterData(semester);
    }

    /**
     *     public Module fetchMod(String moduleCode) throws IOException {
     *         try {
     *             return NusMods.fetchModOnline(moduleCode);
     *         } catch (IOException e) {
     *             TextUi.printNoConnectionMessage();
     *             return ModStorage.loadModInfo(moduleCode);
     *         }
     *     }
     */
}
