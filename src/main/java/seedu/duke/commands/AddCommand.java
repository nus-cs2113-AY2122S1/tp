package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    protected static final String TASK_FLAG = "-t";
    protected static final String EVENT_FLAG = "-e";
    protected static final String MEMBER_FLAG = "-m";
    protected static final String TITLE_FLAG = "n/";
    protected static final String DATE_FLAG = "d/";
    protected static final String VENUE_FLAG = "v/";
    protected static final String BUDGET_FLAG = "b/";

    protected static String itemType;
    protected static String itemTitle;
    protected static String itemDescription;
    protected static LocalDateTime itemDateTime;

    protected static String eventVenue;
    protected static double eventBudget;

    protected static String memberName;

    // Indicates if an error occurs due to the wrong format typed by the user
    protected static boolean isCorrectFormat;

    public AddCommand(String[] command, String response) {

        isCorrectFormat = true;
        assert command[0].equals("add") : "Command must be 'add'";

        try {
            if (command.length == 1) {
                throw new DukeException("Please specify what to add. ");
            }
            itemType = command[1];
            if (itemType.equalsIgnoreCase(TASK_FLAG)) {
                prepareTask(response);
            } else if (itemType.equalsIgnoreCase(EVENT_FLAG)) {
                prepareEvent(response);
            } else if (itemType.equalsIgnoreCase(MEMBER_FLAG)) {
                prepareMember(response);
            } else {
                throw new DukeException("Invalid item flag entered. Please specify task '-t' or event '-e'"
                        + "after the 'add' command. ");
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

    private void initializeDateTime(String taskDeadline) throws DukeException {
        try {
            itemDateTime = Parser.convertDateTime(taskDeadline);
            if (itemDateTime.isBefore(LocalDateTime.now())) {
                throw new DukeException("Unfortunately, we cannot travel back in time. Please "
                        + "enter a valid date and time in the format 'dd-MM-yyyy HHmm'. ");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use this format for the deadline 'dd-MM-yyyy HHmm'. ");
        }
    }

    private double retrieveEventBudget(String response) {
        int startOfEventBudget = response.indexOf(BUDGET_FLAG) + 2;
        int endOfEventBudget = response.indexOf("/", startOfEventBudget) - 2;
        double budget;
        try {
            if (endOfEventBudget < 0) {
                budget = Double.parseDouble(response.trim().substring(startOfEventBudget));
            } else {
                budget = Double.parseDouble(response.trim().substring(startOfEventBudget, endOfEventBudget));
            }
            if (budget < 0) {
                System.out.print("Event budget needs to be a positive number. ");
                // Returns -1 to signify that the budget entered was not a positive number
                return -1;
            }
            return budget;
        } catch (NumberFormatException e) {
            System.out.print("Event budget needs to be a number. ");
            // Returns -1 to signify that the budget entered was not a valid number
            return -1;
        }
    }

    private void prepareTask(String response) throws DukeException {
        if (Duke.eventCatalog.isEmpty()) {
            throw new DukeException("There is no event to assign this task to! Please add "
                    + "an event using the flag '-e'. ");
        }
        if (Duke.memberRoster.isEmpty()) {
            throw new DukeException("There are no members in your roster to assign this task to!");
        }
        if (!response.contains(TITLE_FLAG)) {
            throw new DukeException("Please add a title for your task using 'n/<title>'. ");
        }
        if (!response.contains(DATE_FLAG)) {
            throw new DukeException("Please add a deadline for your task using 'd/<deadline>' in the "
                    + "format dd-MM-yyyy HHmm. ");
        }

        itemTitle = retrieveItemAttribute(response, TITLE_FLAG);
        if (itemTitle.isBlank()) {
            throw new DukeException("Task title cannot be empty, please re-enter your task. ");
        }

        String taskDeadline = retrieveItemAttribute(response, DATE_FLAG);
        if (taskDeadline.isBlank()) {
            throw new DukeException("Task deadline cannot be empty, please re-enter your task. ");
        }
        initializeDateTime(taskDeadline);
    }

    private void prepareEvent(String response) throws DukeException {
        if (!response.contains(TITLE_FLAG)) {
            throw new DukeException("Please add a title for your event using 'n/<title>'. ");
        }
        if (!response.contains(DATE_FLAG)) {
            throw new DukeException("Please add a date for your event using 'd/<date>' in the "
                    + "format dd-MM-yyyy HHmm. ");
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

        String eventDateTime = retrieveItemAttribute(response, DATE_FLAG);
        if (eventDateTime.isBlank()) {
            throw new DukeException("Event date and time cannot be empty, please re-enter your task. ");
        }
        initializeDateTime(eventDateTime);

        eventVenue = retrieveItemAttribute(response, VENUE_FLAG);
        if (eventVenue.isBlank()) {
            throw new DukeException("Event venue cannot be empty, please re-enter your event. ");
        }

        eventBudget = retrieveEventBudget(response);
        if (eventBudget == -1) {
            throw new DukeException("Please re-enter your event. ");
        }
        assert eventBudget >= 0 : "Event budget cannot be a negative number";
        if (BigDecimal.valueOf(eventBudget).scale() > 2) {
            throw new DukeException("Event budget cannot have more than 2 decimal places. Please re-enter "
                    + "your event. ");
        }
    }

    private void prepareMember(String response) {
        memberName = response.substring(response.indexOf(MEMBER_FLAG) + 3).trim().toUpperCase();
    }

    private static void addToEventCatalog(Event event) {
        Duke.eventCatalog.add(event);
        Duke.eventCatalog.sortCatalog();
    }

    private static void addToMemberRoster(Member member) {
        Duke.memberRoster.add(member);
        Duke.memberRoster.sortRoster();
    }

    private void addTaskToEvent(int eventIndex, Task task) throws DukeException {
        try {
            Duke.eventCatalog.get(eventIndex - 1).addToTaskList(task);
            Duke.eventCatalog.sortCatalog();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such event. Please enter a valid event for your task. ");
        }
    }

    private void addTaskToMember(int memberIndex, Task task) throws DukeException {
        try {
            Duke.memberRoster.get(memberIndex - 1).addToAssignedTasks(task);
            Duke.memberRoster.get(memberIndex - 1).sortTasks();
            task.addMember(Duke.memberRoster.get(memberIndex - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This member does not exist. Please enter the index corresponding to "
                    + "the correct member. ");
        }
    }

    private int getEventForTask(Task task) {
        Ui.promptForEventIndex();
        int eventIndex = 0;
        boolean isCorrectEvent = false;
        while (!isCorrectEvent) {
            try {
                assert !Duke.eventCatalog.isEmpty() : "The event catalog should not be empty";
                Ui.printEventCatalog();
                Ui.printLineBreak();
                eventIndex = Integer.parseInt(Ui.readInput());
                addTaskToEvent(eventIndex, task);
                isCorrectEvent = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number corresponding to the event "
                        + "you want to add to. ");
                Ui.printLineBreak();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printLineBreak();
            }
        }
        assert eventIndex > 0 : "Event index should be valid";
        return eventIndex;
    }

    private void getMemberForTask(Task task) {
        Ui.promptForMemberIndex();
        boolean isCorrectMember = false;
        while (!isCorrectMember) {
            try {
                Ui.printMemberRoster();
                Ui.printLineBreak();
                int memberIndex = Integer.parseInt(Ui.readInput());
                addTaskToMember(memberIndex, task);
                isCorrectMember = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number corresponding to the member "
                        + "you want to assign this task to. ");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printLineBreak();
            }
        }
    }

    public CommandResult execute() {
        if (isCorrectFormat) {
            if (itemType.equalsIgnoreCase(MEMBER_FLAG)) {
                Member member = new Member(memberName);
                addToMemberRoster(member);
                return new CommandResult(Ui.getMemberAddedMessage(member));
            }
            Ui.promptForDescription();
            itemDescription = Ui.readInput();
            Ui.printLineBreak();
            if (itemType.equalsIgnoreCase(TASK_FLAG)) {
                Task task = new Task(itemTitle, itemDescription, itemDateTime);
                int eventIndex = getEventForTask(task);
                getMemberForTask(task);
                return new CommandResult(Ui.getTaskAddedMessage(eventIndex, task));
            }

            if (itemType.equalsIgnoreCase(EVENT_FLAG)) {
                Event event = new Event(itemTitle, itemDescription, itemDateTime, eventVenue, eventBudget);
                addToEventCatalog(event);
                return new CommandResult(Ui.getEventAddedMessage(event));
            }
        }
        return new CommandResult("Item unable to be added!");
    }
}
