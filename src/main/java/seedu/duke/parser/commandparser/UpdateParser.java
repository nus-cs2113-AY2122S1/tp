package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.updates.UpdateEventCommand;
import seedu.duke.commands.updates.UpdateMemberCommand;
import seedu.duke.commands.updates.UpdateTaskCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.AttributeNotFoundException;
import seedu.duke.exceptions.parserexceptions.ExistingMemberException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.ItemAttribute;
import seedu.duke.parser.Parser;

import java.time.LocalDateTime;

public abstract class UpdateParser extends Parser {

    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_VENUE = 2;
    private static final int INDEX_OF_DESCRIPTION = 1;
    protected static final String TITLE_FLAG = "n/";
    protected static final String DATE_FLAG = "d/";
    protected static final String VENUE_FLAG = "v/";
    protected static final String BUDGET_FLAG = "b/";
    protected static final String DESCRIPTION_FLAG = "p/";
    protected static final String TASK_FLAG = "t/";
    protected static final String CHANGE_FLAG = "change/";
    protected static final String REMOVE_FLAG = "remove/";
    protected static final String ADD_FLAG = "add";

    public static Command getUpdateCommand(String commandDetails) {
        try {
            int eventIndex = Integer.parseInt(commandDetails) - 1;
            Event eventToBeUpdated = Duke.eventCatalog.get(eventIndex);
            Ui.printUpdateEventDetails(eventToBeUpdated);
            Ui.updateIntroMessage();
            String userInput = Ui.readInput();
            Command result = parseUpdateEvent(userInput, eventToBeUpdated);
            Ui.printLineBreak();
            return result;

        } catch (NumberFormatException e) {
            System.out.println("That is not an Integer! Please key an integer value!");
        } catch (ExistingMemberException | InvalidBudgetException | DukeException e) {
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please key in your updates!");
        } catch (IndexOutOfBoundsException e) {
            Ui.printLineBreak();
            System.out.println("That selection does not exist!");
        } catch (AttributeNotFoundException e) {
            String attributeType = ItemAttribute.getAttributeName(e.getItemAttribute());
            String attributeFlag = ItemAttribute.getItemFlag(e.getItemAttribute());
            System.out.println("Please add a " + attributeType + "for your update using "
                    + attributeFlag + attributeType.toUpperCase());
        }
        return null;
    }

    private static Command parseUpdateEvent(String update, Event event)
            throws DukeException, InvalidBudgetException, AttributeNotFoundException, ExistingMemberException {
        String[] parsedAttributes = new String[3];
        LocalDateTime dateTimeToBeUpdated = null;
        double budget = 0;
        if (update.trim().startsWith(TITLE_FLAG)) {
            String title = retrieveItemAttribute(update, ItemAttribute.TITLE);
            parsedAttributes[INDEX_OF_TITLE] = title;
        } else if (update.trim().startsWith(DATE_FLAG)) {
            String dateTime = retrieveItemAttribute(update, ItemAttribute.DATE);
            dateTimeToBeUpdated = convertDateTime(dateTime);
        } else if (update.trim().startsWith(VENUE_FLAG)) {
            String venue = retrieveItemAttribute(update, ItemAttribute.VENUE);
            parsedAttributes[INDEX_OF_VENUE] = venue;
        } else if (update.trim().startsWith(BUDGET_FLAG)) {
            budget = Double.parseDouble(retrieveItemAttribute(update, ItemAttribute.BUDGET));
        } else if (update.trim().startsWith(DESCRIPTION_FLAG)) {
            String description = retrieveItemAttribute(update, ItemAttribute.DESCRIPTION);
            parsedAttributes[INDEX_OF_DESCRIPTION] = description;
        } else if (update.trim().startsWith(TASK_FLAG)) {
            String taskIndex = retrieveItemAttribute(update, ItemAttribute.TASK);
            taskIndex = taskIndex.replaceAll("\\s", "");
            return parseUpdateTask(event, taskIndex);
        } else {
            System.out.println("Invalid update");
        }
        checkForOtherUpdate(update);
        return new UpdateEventCommand(event, parsedAttributes, dateTimeToBeUpdated, budget);
    }

    private static void checkForOtherUpdate(String update) {
        long totalCharacters = update.chars().filter(ch -> ch == '/').count();
        if (totalCharacters > 2) {
            Ui.printLineBreak();
            System.out.println("Multiple updates detected, Only the first one will be implemented!"
                    + "\nPlease only key in a single update at a time!");
        }
    }

    protected static Command parseUpdateTask(Event eventToBeUpdated, String index)
            throws DukeException, AttributeNotFoundException, ExistingMemberException {
        int taskNum = Integer.parseInt(index) - 1;
        Task taskToBeUpdated = eventToBeUpdated.getFromTaskList(taskNum);
        String update = prepareTaskUpdates(taskToBeUpdated);
        String[] parsedAttributes = new String[3];
        LocalDateTime dateTimeToBeUpdated = null;
        String[] attribute = update.trim().split("/+");
        if (update.trim().startsWith(TITLE_FLAG)) {
            String title = retrieveItemAttribute(update, ItemAttribute.TITLE);
            parsedAttributes[INDEX_OF_TITLE] = title;
        } else if (update.trim().startsWith(DATE_FLAG)) {
            String dateTime = retrieveItemAttribute(update, ItemAttribute.DATE);
            dateTimeToBeUpdated = convertDateTime(dateTime);
        } else if (update.trim().startsWith(DESCRIPTION_FLAG)) {
            String description = retrieveItemAttribute(update, ItemAttribute.DESCRIPTION);
            parsedAttributes[INDEX_OF_DESCRIPTION] = description;
        } else if (update.contains(CHANGE_FLAG)) {
            attribute[1] = attribute[1].replaceAll("\\s", "");
            return changeMember(attribute[1], taskToBeUpdated);
        } else if (update.contains(REMOVE_FLAG)) {
            attribute[1] = attribute[1].replaceAll("\\s", "");
            return removeMember(attribute[1], taskToBeUpdated);
        } else if (update.contains(ADD_FLAG)) {
            return addMember(taskToBeUpdated);
        } else {
            System.out.println("Invalid update");
        }

        return new UpdateTaskCommand(parsedAttributes, taskToBeUpdated, dateTimeToBeUpdated);
    }

    private static Command changeMember(String index, Task taskToBeUpdated) throws DukeException,
            ExistingMemberException {
        int memberToBeReplaced = Integer.parseInt(index);
        int memberIndex = prepareMemberDetails();
        checkValidMember(memberIndex, taskToBeUpdated);
        return new UpdateMemberCommand("change", memberIndex, taskToBeUpdated, memberToBeReplaced);
    }

    private static Command addMember(Task taskToBeUpdated) throws DukeException, ExistingMemberException {
        int memberIndex = prepareMemberDetails();
        checkValidMember(memberIndex, taskToBeUpdated);
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

    private static void checkValidMember(int memberIndex, Task task) throws ExistingMemberException {
        if (memberIndex >= task.memberList.size() || memberIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        Member memberAdded = task.getFromMemberList(memberIndex);
        if (task.getMemberList().contains(memberAdded)) {
            throw new ExistingMemberException(Ui.getLineBreak()
                    + "\nThis member is already assigned to the task!");
        }
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
        return new UpdateMemberCommand("remove", -1, taskToBeUpdated, memberIndex);
    }

    private static String prepareTaskUpdates(Task taskToBeUpdated) {
        Ui.printLineBreak();
        Ui.printTask(taskToBeUpdated);
        Ui.updateTaskIntroMessage();
        return Ui.readInput();

    }
}