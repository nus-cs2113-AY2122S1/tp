package seedu.tp.command;

import seedu.tp.diary.DiaryBook;
import seedu.tp.exception.MissingDiaryMessageException;
import seedu.tp.exception.MissingDiaryFieldException;
import seedu.tp.exception.ParseDateFailedException;
import seedu.tp.exception.MissingUserTimeException;
import seedu.tp.storage.DiaryCreator;
import seedu.tp.task.taskmanager.TaskManager;

import java.time.format.DateTimeParseException;
import java.util.Map;

public class DiaryCommand extends Command {
    private static final String USAGE = "-> Writing to today's diary: diary <--write message>\n"
            + "-> Reading one day's diary: diary <--read date>\n"
            + "-> Deleting on day's diary: diary <--delete date>\n";

    public DiaryCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = "";
        try {
            message = DiaryBook.processCommand(commandArguments);
            DiaryCreator.clearFile();
            DiaryCreator.storeToFile();
        } catch (MissingDiaryMessageException mdme) {
            message = mdme.getMessage();
        } catch (ParseDateFailedException pdfe) {
            message = pdfe.getMessage();
        } catch (MissingUserTimeException mute) {
            message = mute.getMessage();
        } catch (MissingDiaryFieldException mdfe) {
            message = mdfe.getMessage();
        } catch (DateTimeParseException dtpe) {
            message = "Please use dd-MM-yyyy for date.";
        }
        return new CommandResult(message, false);
    }
}
