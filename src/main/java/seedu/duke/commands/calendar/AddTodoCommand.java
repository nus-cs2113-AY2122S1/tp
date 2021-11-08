package seedu.duke.commands.calendar;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.constants.CommandConstants;
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
     * Throws exception if date not valid.
     *
     * @param todoDateStringFormat The date from user in string format.
     * @throws InvalidDateException If user provides invalid date.
     */
    public static void checkIfDateValid(String todoDateStringFormat) throws InvalidDateException {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(CommandConstants.DATE_CONSTANT);
        try {
            LocalDate inputDate = LocalDate.parse(todoDateStringFormat, localDateFormatter);
        } catch (Exception e) {
            throw new InvalidDateException();
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
            ParseException, InvalidDateException {
        String description = arguments.get(INDEX_TODO_DESCRIPTION).trim();
        String todoDateStringFormat = arguments.get(INDEX_TODO_DATE);
        checkIfDateValid(todoDateStringFormat);
        Task task = new Todo(description, todoDateStringFormat);
        ui.printLine();
        storage.tasksList.addTask(task);
        ui.printTaskAddedMessage();
        ui.printLine();
        StorageTasks.writeTaskList(storage.tasksList);
    }

}