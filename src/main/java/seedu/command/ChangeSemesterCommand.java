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
    public static final Integer MIN_OPTION = 1;
    public static final Integer MAX_OPTION = 5;
    public static final Integer KEEP_SEMESTER = 5;

    private static Logger logger = Logger.getLogger("");
    private Timetable timetable;

    public ChangeSemesterCommand(Timetable timetable) {
        this.timetable = timetable;
    }

    /**
     * Get semester input, changing semester only when the input given is different from the current
     * semester.
     */
    @Override
    public void execute() throws IntegerException {
        TextUi.printSemesters(timetable.getSemester());
        try {
            int newSemester = TextUi.changeSemesterCommand();
            if (newSemester < MIN_OPTION || newSemester > MAX_OPTION) {
                throw new IntegerException("Input is out of range, semester not changed");
            } else if (newSemester == KEEP_SEMESTER || newSemester == timetable.getSemester()) {
                TextUi.printCurrentSemester(timetable.getSemester());
            } else {
                changeSemester(newSemester);
            }
        } catch (NumberFormatException e) {
            throw new IntegerException("Input is not a valid number, semester not changed");
        }
    }

    /**
     * Changes semester and clears existing timetable.
     * 
     * @param semester semester to change to, between 1 and 4
     */
    public void changeSemester(int semester) {
        timetable.setSemester(semester);
        try {
            timetable.clearTimetable();
            logger.log(Level.INFO, "Timetable is now empty");
        } catch (UniModsException e) {
            logger.log(Level.INFO, "Change semester empty timetable");
        }
        TextUi.printCurrentSemester(timetable.getSemester());
    }
}
