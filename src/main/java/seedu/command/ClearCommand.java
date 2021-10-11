package seedu.command;

import seedu.exceptions.UniModsException;
import seedu.timetable.Timetable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearCommand extends Command {

    private static Logger logger = Logger.getLogger("");
    private Timetable timetable;

    public ClearCommand(Timetable timetable) {
        this.timetable = timetable;
    }

    public void execute() {
        try {
            timetable.clearTimetable();
            logger.log(Level.INFO, "Timetable is now empty");
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
