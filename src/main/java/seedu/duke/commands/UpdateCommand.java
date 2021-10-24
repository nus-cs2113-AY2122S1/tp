package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Task;
import seedu.duke.parser.Parser;
import seedu.duke.Ui;
import seedu.duke.items.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class UpdateCommand extends Command {
    protected Integer eventNumber;
    protected Event eventToBeUpdated;
    protected static final String TITLE_FLAG = "title/";
    protected static final String DATE_FLAG = "date/";
    protected static final String DEADLINE_FLAG = "deadline/";
    protected static final String VENUE_FLAG = "venue/";
    protected static final String BUDGET_FLAG = "budget/";
    protected static final String DESCRIPTION_FLAG = "description/";
    protected static final String TASK_FLAG = "task/";
    protected static final String MEMBER_FLAG = "member/";

    public UpdateCommand(String[] command) {
        this.eventNumber = Integer.parseInt(command[1]) - 1;
        this.eventToBeUpdated = Duke.eventCatalog.get(eventNumber);
        System.out.println("Here are the details of the event:" + System.lineSeparator()
                + "======================================" + System.lineSeparator());
        Ui.printEvent(Duke.eventCatalog.get(eventNumber));
        Ui.printLineBreak();
    }

    private void updateIntroMessage() {
        System.out.println("Please type the item you would like to update in the following manner "
                + System.lineSeparator() + "-----------------------------------------------------------------------   "
                + System.lineSeparator() + "title/[NEW NAME]   "
                + System.lineSeparator() + "date/[NEW DATE[d/dd-MM-yyyy HHmm]]"
                + System.lineSeparator() + "description/[NEW DESCRIPTION]"
                + System.lineSeparator() + "venue/[NEW VENUE]"
                + System.lineSeparator() + "budget/[NEW BUDGET]"
                + System.lineSeparator() + "task/[TASK NUM YOU WANT TO UPDATE]"
                + System.lineSeparator() + "You may type more then one update at a given time but separate them with a [>]"
                + System.lineSeparator() + Ui.getLineBreak());
    }

    public CommandResult execute() {
        boolean exit = false;
        while (!exit) {
            updateIntroMessage();
            String userInput = Ui.readInput();
            Ui.printLineBreak();
            if (userInput.equalsIgnoreCase("exit")) {
                return new CommandResult("returning to main page...");
            }
            String[] userUpdates = userInput.trim().split(">");
            try {
                CommandResult x = implementUpdates(userUpdates);
                if (x != null) return x;
                postUpdateMessage();
                String exitInput = Ui.readInput();
                Ui.printLineBreak();
                if (exitInput.equalsIgnoreCase("y")) {
                    Duke.eventCatalog.sortCatalog();
                    exit = true;
                }
            } catch (NullPointerException e) {
                System.out.println("please follow the error closely");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
        return new CommandResult("Item Have been updated accordingly!");
    }

    private CommandResult implementUpdates(String[] userUpdates) throws DukeException {
        for (String update : userUpdates) {
            String[] attribute = update.trim().split("/");
            if (update.contains(TITLE_FLAG)) {
                eventToBeUpdated.setTitle(attribute[1]);
            } else if (update.contains(DATE_FLAG)) {
                updateDate(attribute[1]);
            } else if (update.contains(VENUE_FLAG)) {
                eventToBeUpdated.setVenue(attribute[1]);
            } else if (update.contains(BUDGET_FLAG)) {
                updateBudget(attribute[1]);
            } else if (update.contains(DESCRIPTION_FLAG)) {
                eventToBeUpdated.setDescription(attribute[1]);
            } else if (update.contains(TASK_FLAG)) {
                updateTask(attribute[1]);
            } else {
                return new CommandResult("invalid Command, you have returned to the main page!");
            }
        }
        return null;
    }

    protected void updateTask(String index) throws DukeException {
        int taskNum = Integer.parseInt(index) - 1;
        Task taskToBeUpdated = eventToBeUpdated.getFromTaskList(taskNum);
        Ui.printTask(taskToBeUpdated);
        updateTaskIntroMessage();
        String userInput = Ui.readInput();
        String[] userUpdates = userInput.trim().split(">");
        for (String update : userUpdates) {
            String[] attribute = update.trim().split("/");
            if (update.contains(TITLE_FLAG)) {
                taskToBeUpdated.setTitle(attribute[1]);
            } else if (update.contains(DEADLINE_FLAG)) {
                updateDeadline(attribute[1], taskToBeUpdated);
            } else if (update.contains(DESCRIPTION_FLAG)) {
               taskToBeUpdated.setDescription(attribute[1]);
            } else if (update.contains(MEMBER_FLAG)) {
                updateMember(attribute[1], taskToBeUpdated);
            }else {
                System.out.println("invalid Command!");
            }
        }
        Ui.printLineBreak();
    }

    private void updateMember(String index, Task taskToBeUpdated) {
        System.out.println("Please key in the update for the Members Name");
        String userInput = Ui.readInput();
        taskToBeUpdated.getFromMemberList(Integer.parseInt(index) - 1).setName(userInput);
    }

    private void updateTaskIntroMessage() {
        Ui.printLineBreak();
        System.out.println("Please type the item for task you would like to update in the following manner "
                + System.lineSeparator() + "-----------------------------------------------------------------------   "
                + System.lineSeparator() + "title/[NEW NAME]   "
                + System.lineSeparator() + "deadline/[NEW DATE[d/dd-MM-yyyy HHmm]]"
                + System.lineSeparator() + "description/[NEW DESCRIPTION]"
                + System.lineSeparator() + "member/[MEMBER INDEX]"
                + System.lineSeparator() + "You may type more then one update at a given time but separate them with a [>]"
                + System.lineSeparator() + Ui.getLineBreak());
    }

    protected void updateDeadline(String attribute, Task taskToBeUpdated) throws DukeException {
        try {
            LocalDateTime newDate = Parser.convertDateTime(attribute);
            if (newDate.isBefore(LocalDateTime.now())) {
                throw new DukeException("sorry a mortal cannot travel to the past");
            }
            taskToBeUpdated.setDateTime(Parser.convertDateTime(attribute));
        } catch (DateTimeParseException e) {
            System.out.println("incorrect format please ensure format for date is of [d/dd-MM-yyyy HHmm]");
        }
    }

    private void postUpdateMessage() {
        System.out.println("Here is the updated Event");
        Ui.printEvent(eventToBeUpdated);
        System.out.println("If you are satisfied with your updates? y to exit, n to continue");
        Ui.printLineBreak();
    }


    protected void updateDate(String attribute) throws DukeException {
        try {
            LocalDateTime newDate = Parser.convertDateTime(attribute);
            if (newDate.isBefore(LocalDateTime.now())) {
                throw new DukeException("sorry a mortal cannot travel to the past");
            }
            eventToBeUpdated.setDateTime(Parser.convertDateTime(attribute));

        } catch (DateTimeParseException e) {
            System.out.println("incorrect format please ensure format for date is of [d/dd-MM-yyyy HHmm]");
        }

    }

    protected void updateBudget(String attribute) {
        try {
            double newBudget = Double.parseDouble(attribute);
            eventToBeUpdated.setBudget(newBudget);
        } catch (NullPointerException e) {
            System.out.println("please ensure budget is a double");
        }
    }
}
