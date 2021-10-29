package seedu.command;

import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.exceptions.IntegerException;
import seedu.exceptions.UniModsException;
import seedu.timetable.Timetable;
import seedu.ui.TextUi;

public class ChangeSemesterCommand extends Command {

    public static final String commandSyntax = "semester";
    public static final String commandAction =
            "Changes the academic semester that you wish to plan for";

    private static Logger logger = Logger.getLogger("");
    private Timetable timetable;

    public ChangeSemesterCommand(Timetable timetable) {
        this.timetable = timetable;
    }

    @Override
    public void execute() throws IntegerException {
        TextUi.printSemesters(timetable.getSemester());
        int newSemester = TextUi.changeSemesterCommand();
        if (newSemester < 1 || newSemester > 5) {
            throw new IntegerException("Input is out of range, semester not changed");
        } else if (newSemester == 5 || newSemester == timetable.getSemester()) {
            TextUi.printCurrentSemester(timetable.getSemester());
        } else {
            timetable.setSemester(newSemester);
            try {
                timetable.clearTimetable();
                logger.log(Level.INFO, "Timetable is now empty");
            } catch (UniModsException e) {
                logger.log(Level.INFO, "Change semester empty timetable");
            }
            TextUi.printCurrentSemester(timetable.getSemester());
        }
    }
}
