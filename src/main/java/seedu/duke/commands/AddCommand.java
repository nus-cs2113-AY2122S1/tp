package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.items.Item;

public class AddCommand extends Command {

    protected static String itemTitle;
    protected static String itemType;

    protected static String taskDeadline;

    // Indicates if and error occurs due to the wrong format typed by the user
    protected static boolean isCorrectFormat;

    public AddCommand(String[] command, String response) {

        isCorrectFormat = true;

        try {
            if (command.length == 1) {
                throw new DukeException("Please specify what to add. ");
            }
            itemType = command[1];
            if (itemType.equalsIgnoreCase("-t")) {
                prepareTask(response);
            } else if (itemType.equalsIgnoreCase("-e")) {
                prepareEvent(response);
            } else {
                throw new DukeException("Invalid item flag entered. Please specify task '-t' or event '-e'. ");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            isCorrectFormat = false;
        }
    }

    private void prepareTask(String response) throws DukeException {
        if (!response.contains("n/")) {
            throw new DukeException("Please add a title for your task using 'n/<title>'. ");
        }
        if (!response.contains("d/")) {
            throw new DukeException("Please add a deadline for your task using 'd/<deadline>' in the " +
                    "format YYYY-MM-DD. ");
        }

        int startOfTaskTitle = response.indexOf("n/") + 2;
        int endOfTaskTitle = response.indexOf("/", startOfTaskTitle) - 1;
        if (endOfTaskTitle < 0) {
            itemTitle = response.trim().substring(startOfTaskTitle);
        } else {
            itemTitle = response.trim().substring(startOfTaskTitle, endOfTaskTitle);
        }
        if (itemTitle.equals("")) {
            throw new DukeException("Task title cannot be empty, please re-enter your task. ");
        }

        int startOfTaskDeadline = response.indexOf("d/") + 2;
        int endOfTaskDeadline = response.indexOf("/", startOfTaskDeadline) - 1;
        if (endOfTaskDeadline < 0) {
            taskDeadline = response.trim().substring(startOfTaskDeadline);
        } else {
            taskDeadline = response.trim().substring(startOfTaskDeadline, endOfTaskDeadline);
        }
        if (taskDeadline.equals("")) {
            throw new DukeException("Task deadline cannot be empty, please re-enter your task. ");
        }
    }

    private void prepareEvent(String response) {

    }

    private static void addToList(Item item) {
        Duke.itemList.add(item);
    }

    public CommandResult execute() {
        // add task with the specifics here
        // if completed properly return this commandResult
        return new CommandResult("AddCommand");
        // else return new CommandResult("Task unable to be added, you forgot the date!");
    }
}
