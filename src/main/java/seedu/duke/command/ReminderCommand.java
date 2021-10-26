package seedu.duke.command;

import seedu.duke.exception.*;
import seedu.duke.task.TaskManager;
import seedu.duke.task.reminder.ReminderManager;

import java.util.Map;

public class ReminderCommand extends Command{
    private static final CommandEnum COMMAND = CommandEnum.REMINDER;

    public ReminderCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = "";
        try {
            message = ReminderManager.customizeReminder(commandArguments);
        } catch (NumberFormatException nfe) {
            message = "Please use integer values for time and index";
        } catch (MissingUserTimeException mute) {
            message = mute.getMessage();
        } catch (MissingUserMessageException mume) {
            message = mume.getMessage();
        } catch (MissingReminderFieldException mrfe) {
            message = mrfe.getMessage();
        }
        return new CommandResult(message, true, false);
    }
}
