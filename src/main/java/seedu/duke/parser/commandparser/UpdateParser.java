package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.*;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;

import java.time.LocalDateTime;

public abstract class UpdateParser extends Parser {

    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_VENUE = 2;
    private static final int INDEX_OF_DESCRIPTION = 1;
    protected static final String TITLE_FLAG = "title/";
    protected static final String DATE_FLAG = "date/";
    protected static final String DEADLINE_FLAG = "deadline/";
    protected static final String VENUE_FLAG = "venue/";
    protected static final String BUDGET_FLAG = "budget/";
    protected static final String DESCRIPTION_FLAG = "description/";
    protected static final String TASK_FLAG = "task/";
    protected static final String MEMBER_FLAG = "member/";
    protected static final String REMOVE_FLAG = "remove/";
    protected static final String ADD_FLAG = "add/";

    public static Command getUpdateCommand(String commandDetails) {
        try {
            int eventIndex = Integer.parseInt(commandDetails) - 1;
            Event eventToBeUpdated = Duke.eventCatalog.get(eventIndex);
            Ui.printUpdateEventDetails(eventToBeUpdated);
            Ui.updateIntroMessage();
            String userInput = Ui.readInput();
            String[] userUpdates = userInput.trim().split(">+");
            Command result = parseUpdateEvent(userUpdates, eventToBeUpdated);
            Ui.printLineBreak();
            return result;

        } catch (NumberFormatException e) {
            System.out.println("Invalid index of event, please type an integer!");
        } catch (InvalidBudgetException | DukeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Command parseUpdateEvent(String[] userUpdates, Event event) throws DukeException, InvalidBudgetException {
        String[] parsedAttributes = new String[3];
        LocalDateTime dateTime = null;
        double budget = 0;
        for (String update : userUpdates) {
            String[] attribute = update.trim().split("/+");
            if (update.contains(TITLE_FLAG)) {
                parsedAttributes[INDEX_OF_TITLE] = attribute[1];
            } else if (update.contains(DATE_FLAG)) {
                dateTime = Parser.convertDateTime(attribute[1]);
            } else if (update.contains(VENUE_FLAG)) {
                parsedAttributes[INDEX_OF_VENUE] = attribute[1];
            } else if (update.contains(BUDGET_FLAG)) {
                budget = convertEventBudgetToDouble(attribute[1]);
            } else if (update.contains(DESCRIPTION_FLAG)) {
                parsedAttributes[INDEX_OF_DESCRIPTION] = attribute[1];
            } else if (update.contains(TASK_FLAG)) {
                attribute[1] = attribute[1].replaceAll("\\s", "");
               return parseUpdateTask(event, attribute[1]);
            } else {
                System.out.println("Invalid update, you have returned to the main page!");
            }
        }
        return new UpdateEventCommand(event, parsedAttributes, dateTime, budget);
    }

    protected static Command parseUpdateTask(Event eventToBeUpdated, String index) throws DukeException, InvalidBudgetException {
        int taskNum = Integer.parseInt(index) - 1;
        Task taskToBeUpdated = eventToBeUpdated.getFromTaskList(taskNum);
        String[] userUpdates = prepareTaskUpdates(taskToBeUpdated);

        String[] parsedAttributes = new String[3];
        LocalDateTime dateTime = null;
        double budget = 0;

        for (String update : userUpdates) {
            String[] attribute = update.trim().split("/+");
            if (update.contains(TITLE_FLAG)) {
                parsedAttributes[INDEX_OF_TITLE] = attribute[1];
            } else if (update.contains(DEADLINE_FLAG)) {
                dateTime = Parser.convertDateTime(attribute[1]);
            } else if (update.contains(DESCRIPTION_FLAG)) {
                parsedAttributes[INDEX_OF_DESCRIPTION] = attribute[1];
            } else if (update.contains(MEMBER_FLAG)) {
                attribute[1] = attribute[1].replaceAll("\\s", "");
                return changeMember(attribute[1], taskToBeUpdated);
            } else if (update.contains(REMOVE_FLAG)) {
                attribute[1] = attribute[1].replaceAll("\\s", "");
                return removeMember(attribute[1], taskToBeUpdated);
            } else if (update.contains(ADD_FLAG)) {
                attribute[1] = attribute[1].replaceAll("\\s", "");
                return addMember(taskToBeUpdated);
            } else {
                System.out.println("Invalid update, you have returned to the main page!");
            }
        }
        return new UpdateTaskCommand(parsedAttributes, taskToBeUpdated,  dateTime);
    }

    private static Command changeMember(String index, Task taskToBeUpdated) throws DukeException {
        int memberToBeReplaced = Integer.parseInt(index);
        int memberIndex = prepareMemberDetails();
        return new UpdateMemberCommand("change", memberIndex, taskToBeUpdated, memberToBeReplaced);
    }

    private static Command addMember(Task taskToBeUpdated) throws DukeException {
        int memberIndex = prepareMemberDetails();
        return new UpdateMemberCommand("add", memberIndex, taskToBeUpdated, -1);
    }

    private static int prepareMemberDetails() throws DukeException {
        int memberIndex = -1;
        boolean isCorrectMember = false;
        while (!isCorrectMember) {
            try {
                memberIndex = getMemberIndex();
                isCorrectMember = true;
            } catch (IndexOutOfBoundsException e) {
                Ui.printLineBreak();
                throw new DukeException("This member does not exist. Please enter the index corresponding to "
                        + "the correct member. ");
            } catch (NumberFormatException e) {
                Ui.printLineBreak();
                System.out.println("Please enter the number corresponding to the member "
                        + "you want to assign this task to. ");
            }
        }
        return memberIndex;
    }

    private static int getMemberIndex() {
        Ui.printLineBreak();
        Ui.promptForMemberIndex();
        Ui.printMemberRoster();
        Ui.printLineBreak();
        return Integer.parseInt(Ui.readInput()) - 1;
    }

    private static Command removeMember(String index, Task taskToBeUpdated) {
        Ui.printLineBreak();
        int memberIndex = Integer.parseInt(index) - 1;
        Member memberToBeRemoved = taskToBeUpdated.memberList.get(memberIndex);
        System.out.println("Are you sure you want to remove " + memberToBeRemoved.getName() + " from this task (y/n)");
        Ui.printLineBreak();
        boolean exit = false;
        while (!exit) {
            String userInput = Ui.readInput();
            Ui.printLineBreak();
            if (taskToBeUpdated.memberList.size() <= 1) {
                System.out.println("Task cannot be carried out without a member, no member removed!");
                //flag for error
                memberIndex = -1;
                exit = true;
            } else if (userInput.equalsIgnoreCase("y")) {
                System.out.println(memberToBeRemoved.getName() + " removed from Task!");
                exit = true;
            } else if (userInput.equalsIgnoreCase("n")) {
                System.out.println("No member removed from Task!");
                memberIndex = -1;
                exit = true;
            } else {
                System.out.println("please key in [y] for yes and [n] for no");
                Ui.printLineBreak();
            }
        }
        return new UpdateMemberCommand("remove", -1 , taskToBeUpdated, memberIndex);
    }

    private static String[] prepareTaskUpdates(Task taskToBeUpdated) {
        Ui.printTask(taskToBeUpdated);
        Ui.updateTaskIntroMessage();
        String userInput = Ui.readInput();
        return  userInput.trim().split(">");
    }
}