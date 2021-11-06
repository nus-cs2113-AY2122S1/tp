package seedu.command;

import seedu.command.flags.ClearFlag;
import seedu.exceptions.UniModsException;
import seedu.timetable.Timetable;
import seedu.unimods.UniMods;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearCommand extends Command {

    public static final String commandSyntax = "clear";
    public static final String commandAction = "Clears the timetable of everything";

    private static final Logger logger = Logger.getLogger("");
    private final Timetable timetable;
    private ClearFlag flag;

    public ClearCommand(Timetable timetable, ClearFlag flag) {
        this.timetable = timetable;
        this.flag = flag;
    }

    public void execute() {
        if (flag == ClearFlag.TIMETABLE) {
            try {
                timetable.clearTimetable();
                logger.log(Level.INFO, "Timetable is now empty");
            } catch (UniModsException e) {
                System.out.println(e.getMessage());
                logger.log(Level.WARNING, e.getMessage());
            }
        }
        if (flag == ClearFlag.TRANSCRIPT) {
            try {
                UniMods.getProfileInUse().getRecord().clearTranscript();
                logger.log(Level.INFO, "Transcript is now empty");
            } catch (UniModsException e) {
                System.out.println(e.getMessage());
                logger.log(Level.WARNING, e.getMessage());
            }
        }
    }
}
