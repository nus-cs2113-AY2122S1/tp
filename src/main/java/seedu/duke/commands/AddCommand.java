package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;

public class AddCommand extends Command {

    protected static final String TASK_FLAG = "-t";
    protected static final String EVENT_FLAG = "-e";
    protected static final String TITLE_FLAG = "n/";
    protected static final String DATE_FLAG = "d/";
    protected static final String VENUE_FLAG = "v/";
    protected static final String BUDGET_FLAG = "b/";

    protected static String itemType;
    protected static String itemTitle;
    protected static String itemDescription;
    protected static String itemDate;

    protected static String eventVenue;
    protected static int eventBudget;

    // Indicates if an error occurs due to the wrong format typed by the user
    protected static boolean isCorrectFormat;

    public AddCommand(String[] command, String response) {

        isCorrectFormat = true;

        try {
            if (command.length == 1) {
                throw new DukeException("Please specify what to add. ");
            }
            itemType = command[1];
            if (itemType.equalsIgnoreCase(TASK_FLAG)) {
                prepareTask(response);
            } else if (itemType.equalsIgnoreCase(EVENT_FLAG)) {
                prepareEvent(response);
            } else {
                throw new DukeException("Invalid item flag entered. Please specify task '-t' or event '-e'. ");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            isCorrectFormat = false;
        }
    }

    private String retrieveItemAttribute(String response, String flag) {
        int startOfItemAttribute = response.indexOf(flag) + 2;
        int endOfItemAttribute = response.indexOf("/", startOfItemAttribute) - 2;
        if (endOfItemAttribute < 0) {
            return response.trim().substring(startOfItemAttribute);
        }
        return response.trim().substring(startOfItemAttribute, endOfItemAttribute);
    }

    private int retrieveEventBudget(String response) {
        int startOfEventBudget = response.indexOf(BUDGET_FLAG) + 2;
        int endOfEventBudget = response.indexOf("/", startOfEventBudget) - 2;
        try {
            if (endOfEventBudget < 0) {
                return Integer.parseInt(response.trim().substring(startOfEventBudget));
            }
            return Integer.parseInt(response.trim().substring(startOfEventBudget, endOfEventBudget));
        } catch (NumberFormatException e) {
            System.out.print("Budget needs to be an integer. ");
            // Returns -1 to signify that the budget entered was not a valid integer
            return -1;
        }
    }

    private void prepareTask(String response) throws DukeException {
        if (!response.contains(TITLE_FLAG)) {
            throw new DukeException("Please add a title for your task using 'n/<title>'. ");
        }
        if (!response.contains(DATE_FLAG)) {
            throw new DukeException("Please add a deadline for your task using 'd/<deadline>' in the "
                    + "format YYYY-MM-DD. ");
        }

        itemTitle = retrieveItemAttribute(response, TITLE_FLAG);
        if (itemTitle.isBlank()) {
            throw new DukeException("Task title cannot be empty, please re-enter your task. ");
        }

        itemDate = retrieveItemAttribute(response, DATE_FLAG);
        if (itemDate.isBlank()) {
            throw new DukeException("Task deadline cannot be empty, please re-enter your task. ");
        }
    }

    private void prepareEvent(String response) throws DukeException {
        if (!response.contains(TITLE_FLAG)) {
            throw new DukeException("Please add a title for your event using 'n/<title>'. ");
        }
        if (!response.contains(DATE_FLAG)) {
            throw new DukeException("Please add a date for your event using 'd/<date>' in the "
                    + "format YYYY-MM-DD. ");
        }
        if (!response.contains(VENUE_FLAG)) {
            throw new DukeException("Please add a venue for your event using 'v/<venue>'. ");
        }
        if (!response.contains(BUDGET_FLAG)) {
            throw new DukeException("Please add a budget for your event using 'b/<budget>'. ");
        }

        itemTitle = retrieveItemAttribute(response, TITLE_FLAG);
        if (itemTitle.isBlank()) {
            throw new DukeException("Event title cannot be empty, please re-enter your event. ");
        }

        itemDate = retrieveItemAttribute(response, DATE_FLAG);
        if (itemDate.isBlank()) {
            throw new DukeException("Event date cannot be empty, please re-enter your event. ");
        }

        eventVenue = retrieveItemAttribute(response, VENUE_FLAG);
        if (eventVenue.isBlank()) {
            throw new DukeException("Event venue cannot be empty, please re-enter your event. ");
        }

        eventBudget = retrieveEventBudget(response);
        if (eventBudget == -1) {
            throw new DukeException("Please re-enter your event. ");
        }
    }

    private static void addToTaskList(Task task) {
        Duke.taskList.add(task);
    }

    private static void addToEventList(Event event) {
        Duke.eventList.add(event);
    }

    public CommandResult execute() {
        if (isCorrectFormat) {
            Ui.promptForDescription();
            itemDescription = Ui.readInput();
            Ui.linebreak();
            if (itemType.equalsIgnoreCase(TASK_FLAG)) {
                Task task = new Task(itemTitle, itemDescription, itemDate);
                addToTaskList(task);
                return new CommandResult("Task added: " + itemTitle + System.lineSeparator()
                        + "Total number of tasks = " + Duke.taskList.size());
            }
            if (itemType.equalsIgnoreCase(EVENT_FLAG)) {
                Event event = new Event(itemTitle, itemDescription, itemDate, eventVenue, eventBudget);
                addToEventList(event);
                return new CommandResult("Event added: " + itemTitle + System.lineSeparator()
                        + "Total number of events = " + Duke.eventList.size());
            }
        }
        return new CommandResult("Item unable to be added! ");
    }
}
