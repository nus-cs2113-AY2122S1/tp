package seedu.duke.commands.calendar;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.calendar.DuplicateTaskException;
import seedu.duke.exceptions.calendar.InvalidDateException;
import seedu.duke.schedule.task.Task;
import seedu.duke.schedule.task.Todo;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageTasks;
import seedu.duke.ui.Ui;

import static seedu.duke.constants.Messages.INDEX_TODO_DESCRIPTION;
import static seedu.duke.constants.Messages.INDEX_TODO_DATE;

//@@author swatimahadevan

/**
 * Represents the class to execute adding of todo task.
 */
public class AddTodoCommand extends Command {
    public static final String MESSAGE_DUPLICATE_TASK = "A task by this name has already been added!";
    private ArrayList<String> arguments;

    /**
     * Class constructor providing syntax for the HelpCommand.
     */
    public AddTodoCommand() {
        syntax = "calendar todo n/ [TASK_NAME] d/ [DD-MM-YYYY]";
    }

    /**
     * Constructor for AddTodoCommand.
     *
     * @param arguments which has the todo task arguments.
     */
    public AddTodoCommand(ArrayList<String> arguments) {
        super();
        this.arguments = arguments;
        this.helpMessage = "Add a todo task to calendar";
    }

    /**
     * Checks if the date provided by the user is valid.
     *
     * @param todoDateStringFormat The date from user in string format.
     * @return True if the date is valid else False.
     */
    public static boolean isValid(String todoDateStringFormat) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(todoDateStringFormat);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Throws exception if date not valid.
     *
     * @param todoDateStringFormat The date from user in string format.
     * @throws InvalidDateException If user provides invalid date.
     */
    public static void checkIfDateValid(String todoDateStringFormat) throws InvalidDateException {
        if (!isValid(todoDateStringFormat)) {
            throw new InvalidDateException();
        }
    }

    /**
     * Checks if a task of the same name already exists.
     *
     * @param task The Task object.
     * @throws DuplicateTaskException If user tries to add a duplicate task/lecture.
     */
    public static void checkIfDuplicate(Task task) throws InvalidDateException, DuplicateTaskException {
        for (int i = 0; i < Storage.tasksList.getTaskList().size(); i++) {
            if (task.getDescription().equals(Storage.tasksList.getTaskList().get(i).getDescription())) {
                throw new DuplicateTaskException(MESSAGE_DUPLICATE_TASK);
            }
        }
    }

    /**
     * To execute adding of todo.
     *
     * @param ui      The component of CLICK that deals with the interaction with the user.
     * @param storage The component of CLICK that deals with loading tasks from the file and saving tasks in the file.
     * @throws IOException in case of writing to save file error.
     * @throws ParseException in case of error while parsing.
     * @throws InvalidDateException in case date is incorrect.
     * @throws DuplicateTaskException in case task already exists.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException,
            ParseException, DuplicateTaskException, InvalidDateException {
        String description = arguments.get(INDEX_TODO_DESCRIPTION).trim();
        String todoDateStringFormat = arguments.get(INDEX_TODO_DATE);
        Task task = new Todo(description, todoDateStringFormat);
        checkIfDuplicate(task);
        checkIfDateValid(todoDateStringFormat);
        ui.printLine();
        storage.tasksList.addTask(task);
        ui.printTaskAddedMessage();
        ui.printLine();
        StorageTasks.writeTaskList(storage.tasksList);
    }

}