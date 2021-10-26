package seedu.duke.task.reminder;

import seedu.duke.command.Command;
import seedu.duke.command.flags.ReminderFlag;
import seedu.duke.exception.*;
import seedu.duke.local.DataManager;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;

import java.time.LocalDateTime;
import java.util.Map;

public class ReminderManager {

    public ReminderManager() {

    }

    public static String sendReminder() {
        LocalDateTime now = LocalDateTime.now();
        String message = "";
        for (int i = 0; i < TaskManager.getTaskList().size(); i++) {
            if (TaskManager.getTask(i).needReminder()) {
                message += TaskManager.getTask(i).getReminder(now);
            }
        }
        return message;
    }

    public static void customizeReminderTime(long userTime, int index) throws InvalidTaskIndexException, ReminderNotRequiredException {
        if (index < 0 || index > TaskManager.getTaskListSize() - 1) {
            throw new InvalidTaskIndexException(++index);
        } else if (TaskManager.getTask(index).needReminder()) {
            TaskManager.getTask(index).updateReminderTime(userTime);
        } else {
            throw new ReminderNotRequiredException();
        }

    }

    public static void customizeReminderMessage(String message, int index) throws InvalidTaskIndexException, ReminderNotRequiredException {
        if (index < 0 || index > TaskManager.getTaskListSize() - 1) {
            throw new InvalidTaskIndexException(++index);
        } else if (TaskManager.getTask(index).needReminder()) {
            TaskManager.getTask(index).updateReminderMessage(message);
        } else {
            throw new ReminderNotRequiredException();
        }
    }

    public static String customizeReminder(Map<String, String> commandArguments) throws NumberFormatException, MissingUserTimeException, MissingUserMessageException, MissingReminderFieldException {
        int index = Integer.parseInt(commandArguments.get(Command.MAIN_ARGUMENT));
        String outMessage = "";
        if (commandArguments.containsKey(ReminderFlag.TIME_AHEAD)) {
            if (commandArguments.get(ReminderFlag.TIME_AHEAD) != null) {
                long userTime = Long.parseLong(commandArguments.get(ReminderFlag.TIME_AHEAD));
                try {
                    customizeReminderTime(userTime, index - 1);
                    DataManager.updateReminderTime( index-1, userTime);
                    outMessage += "The time for reminding before task is updated to " + userTime + " minutes.";
                } catch (InvalidTaskIndexException itie) {
                    return itie.getMessage();
                } catch (ReminderNotRequiredException rnre) {
                    return rnre.toString();
                }
            } else {
                throw new MissingUserTimeException();
            }
        }

        if (commandArguments.containsKey(ReminderFlag.REMINDER_MESSAGE)) {
            if (commandArguments.get(ReminderFlag.REMINDER_MESSAGE) != null) {
                String message = commandArguments.get(ReminderFlag.REMINDER_MESSAGE);
                try {
                    customizeReminderMessage(message, index - 1);
                    DataManager.updateReminderMessage( index-1, message);
                    outMessage += "The reminder message is updated to " + message;
                } catch (InvalidTaskIndexException itie) {
                    return itie.getMessage();
                } catch (ReminderNotRequiredException rnre) {
                    return rnre.toString();
                }
            } else {
                throw new MissingUserMessageException();
            }
        }
        if (!commandArguments.containsKey(ReminderFlag.TIME_AHEAD)
            && !commandArguments.containsKey(ReminderFlag.REMINDER_MESSAGE)) {
            throw new MissingReminderFieldException();
        }
        return outMessage;
    }

}
