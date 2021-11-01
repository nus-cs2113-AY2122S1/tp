package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;


public class NextCommand extends Command {
    protected static String nextItem;
    protected static String[] userCommand;

    public NextCommand(String[] command) {
        userCommand = command;
        if (command.length < 2 || command.length > 3) {
            nextCommandErrorMessage();
            nextItem = "others";
        } else {
            nextItem = command[1];
        }
    }

    private void nextCommandErrorMessage() {
        System.out.println("please follow the correct format"
                + System.lineSeparator()
                + "next event : View details of the upcoming events"
                + System.lineSeparator()
                + "next task [Event index]: View details of the task with the closest deadline in a particular "
                + "event"
                + System.lineSeparator()
                + Ui.getLineBreak());
    }

    public CommandResult execute() {
        String errorMessage = "This Event has no tasks!";
        try {
            if (nextItem.equalsIgnoreCase("task")) {
                if (userCommand.length != 3) {
                    nextCommandErrorMessage();
                }
                if (Integer.parseInt(userCommand[2]) - 1 > Duke.eventCatalog.size()
                        || Integer.parseInt(userCommand[2]) - 1 < 0) {
                    errorMessage = "This Event does not exist";
                }
                Ui.printTask(Duke.eventCatalog.get(Integer.parseInt(userCommand[2]) - 1).getFromTaskList(0));
            } else if (nextItem.equalsIgnoreCase("event")) {
                Ui.printEvent((Duke.eventCatalog.get(0)));
            } else {
                nextCommandErrorMessage();
            }
        } catch (NumberFormatException e) {
            nextCommandErrorMessage();
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(errorMessage);
        }
        return new CommandResult("Hope you have prepared everything!");
    }
}
