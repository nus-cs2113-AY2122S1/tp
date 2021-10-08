package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Task;

public class AddCommand extends Command {

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

    private String retrieveItemTitle(String response) {
        int startOfItemTitle = response.indexOf("n/") + 2;
        int endOfItemTitle = response.indexOf("/", startOfItemTitle) - 2;
        if (endOfItemTitle < 0) {
            return response.trim().substring(startOfItemTitle);
        }
        return response.trim().substring(startOfItemTitle, endOfItemTitle);
    }

    private String retrieveItemDate(String response) {
        int startOfItemDate = response.indexOf("d/") + 2;
        int endOfItemDate = response.indexOf("/", startOfItemDate) - 2;
        if (endOfItemDate < 0) {
            return response.trim().substring(startOfItemDate);
        }
        return response.trim().substring(startOfItemDate, endOfItemDate);
    }

    private String retrieveEventVenue(String response) {
        int startOfEventVenue = response.indexOf("v/") + 2;
        int endOfEventVenue = response.indexOf("/", startOfEventVenue) - 2;
        if (endOfEventVenue < 0) {
            return response.trim().substring(startOfEventVenue);
        }
        return response.trim().substring(startOfEventVenue, endOfEventVenue);
    }

    private int retrieveEventBudget(String response) {
        int startOfEventBudget = response.indexOf("b/") + 2;
        int endOfEventBudget = response.indexOf("/", startOfEventBudget) - 2;
        try {
            if (endOfEventBudget < 0) {
                return Integer.parseInt(response.trim().substring(startOfEventBudget));
            }
            return Integer.parseInt(response.trim().substring(startOfEventBudget, endOfEventBudget));
        } catch (NumberFormatException e) {
            System.out.print("Budget needs to be an integer. ");
            return -1;
        }
    }

    private void prepareTask(String response) throws DukeException {
        if (!response.contains("n/")) {
            throw new DukeException("Please add a title for your task using 'n/<title>'. ");
        }
        if (!response.contains("d/")) {
            throw new DukeException("Please add a deadline for your task using 'd/<deadline>' in the "
                    + "format YYYY-MM-DD. ");
        }

        itemTitle = retrieveItemTitle(response);
        if (itemTitle.equals("")) {
            throw new DukeException("Task title cannot be empty, please re-enter your task. ");
        }

        itemDate = retrieveItemDate(response);
        if (itemDate.equals("")) {
            throw new DukeException("Task deadline cannot be empty, please re-enter your task. ");
        }
    }

    private void prepareEvent(String response) throws DukeException {
        if (!response.contains("n/")) {
            throw new DukeException("Please add a title for your event using 'n/<title>'. ");
        }
        if (!response.contains("d/")) {
            throw new DukeException("Please add a date for your event using 'd/<date>' in the "
                    + "format YYYY-MM-DD. ");
        }
        if (!response.contains("v/")) {
            throw new DukeException("Please add a venue for your event using 'v/<venue>'. ");
        }
        if (!response.contains("b/")) {
            throw new DukeException("Please add a budget for your event using 'b/<budget>'. ");
        }

        itemTitle = retrieveItemTitle(response);
        if (itemTitle.equals("")) {
            throw new DukeException("Event title cannot be empty, please re-enter your event. ");
        }

        itemDate = retrieveItemDate(response);
        if (itemDate.equals("")) {
            throw new DukeException("Event date cannot be empty, please re-enter your event. ");
        }

        eventVenue = retrieveEventVenue(response);
        if (eventVenue.equals("")) {
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
            if (itemType.equalsIgnoreCase("-t")) {
                Task task = new Task(itemTitle, itemDescription, itemDate);
                addToTaskList(task);
                return new CommandResult("Task added: " + itemTitle + System.lineSeparator()
                        + "Total number of tasks = " + Duke.taskList.size());
            }
            if (itemType.equalsIgnoreCase("-e")) {
                Event event = new Event(itemTitle, itemDescription, itemDate, eventVenue, eventBudget);
                addToEventList(event);
                return new CommandResult("Event added: " + itemTitle + System.lineSeparator()
                        + "Total number of events = " + Duke.eventList.size());
            }
        }
        return new CommandResult("Item unable to be added! ");
    }
}
