package seedu.duke.commands;

import static seedu.duke.Duke.itemList;

public class DeleteCommand extends Command {

    // input from user
    public String[] taskToDelete;

    // v1.0: deleteCommand deletes purely based on index, i.e. delete [TASK_INDEX]

    public DeleteCommand(String[] command) {
        taskToDelete = command;
    }

    public CommandResult execute() {

        String removedTaskTitle;

        removedTaskTitle = deleteTask(taskToDelete);
        return new CommandResult("This task has been removed: " + removedTaskTitle
            + System.lineSeparator());
    }

    // did not throw a custom exception if taskIndex > itemList.size(), already caught by ArrayIndexOutOfBoundsException
    public static String deleteTask(String[] input) {
        try {
            int taskIndex = getTaskIndex(input);
            String taskTitle = itemList.get(taskIndex).getTitle();
            itemList.remove(taskIndex);
            return taskTitle;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "That task does not exist";
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ("Please tell me which task number to delete");
        }
    }

    public static int getTaskIndex(String[] command) {
        return Integer.parseInt(command[1]) - 1;
    }
}
