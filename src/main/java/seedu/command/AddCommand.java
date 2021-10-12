package seedu.command;

import seedu.module.Module;
import seedu.module.Semester;
import seedu.online.NusMods;
import seedu.timetable.Timetable;
import seedu.ui.AddUI;
import seedu.ui.TextUi;
import seedu.exceptions.AddException;
import java.io.IOException;

//Indicate time clashes with current timetable in milestone v2.0 when adding lesson
public class AddCommand extends Command {
    private final String moduleCode;
    private final int semester;
    private final Timetable timetable;
    public AddUI addUI = new AddUI();

    public AddCommand(String moduleCode, Timetable timetable) {
        this.moduleCode = moduleCode;
        this.semester = timetable.getSemester();
        this.timetable = timetable;
    }

    public void execute() throws AddException{
        Module module;
        try {
            module = NusMods.fetchModOnline(moduleCode);
        } catch (IOException e) {
            throw new AddException("Module Code does not exist");
        }
        TextUi.printAddMessage(moduleCode);
        Semester semesterData = module.getSemester(semester);
        addUI.getLessonDetails(semesterData, timetable, module);
    }


}
