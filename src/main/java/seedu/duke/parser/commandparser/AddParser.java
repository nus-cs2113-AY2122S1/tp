package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.addcommands.AddEventCommand;
import seedu.duke.commands.addcommands.AddMemberCommand;
import seedu.duke.commands.addcommands.AddTaskCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.AttributeNotFoundException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.items.Event;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.ItemAttribute;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import java.time.LocalDateTime;

public abstract class AddParser extends Parser {

    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_DATETIME = 1;
    private static final int INDEX_OF_VENUE = 2;
    private static final int INDEX_OF_BUDGET = 3;

    /**
     * Parses add command details from user input to determine the type of item to be added along with its respective
     * attributes and returns the respective Add Command.
     *
     * @param commandDetails User input containing the flag of the item to be added and attributes for the command
     * @return The constructed Add Command. Null otherwise.
     */
    public static Command getAddCommand(String commandDetails) {
        try {
            ItemType itemTypeToAdd = getItemType(commandDetails);
            switch (itemTypeToAdd) {
            case EVENT:
                String[] parsedAttributes = parseAddEvent(commandDetails);
                String title = parsedAttributes[INDEX_OF_TITLE];
                LocalDateTime dateTime = convertDateTime(parsedAttributes[INDEX_OF_DATETIME]);
                String venue = parsedAttributes[INDEX_OF_VENUE];
                double budget = convertEventBudgetToDouble(parsedAttributes[INDEX_OF_BUDGET]);
                Ui.promptForDescription();
                String description = Ui.readInput().trim();
                Ui.printLineBreak();
                return new AddEventCommand(title, description, dateTime, venue, budget);

            case TASK:
                parsedAttributes = parseAddTask(commandDetails);
                title = parsedAttributes[INDEX_OF_TITLE];
                dateTime = convertDateTime(parsedAttributes[INDEX_OF_DATETIME]);
                Ui.promptForDescription();
                description = Ui.readInput().trim();
                Ui.printLineBreak();

                int eventIndex = getEventForTask();
                int[] memberIndexes = getMembersForTask();

                return new AddTaskCommand(title, description, dateTime, eventIndex, memberIndexes);

            case MEMBER:
                parsedAttributes = parseAddMembers(commandDetails);
                return new AddMemberCommand(parsedAttributes);

            default:
                return null;
            }
        } catch (InvalidItemTypeException e) {
            System.out.println("Having some trouble understanding what exactly you're trying to add!\n"
                    + "TIP: Specify event '-e', task '-t', or member '-m' after the 'add' command.");
        } catch (InvalidBudgetException | DukeException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Takes in command details for an event and returns a String[] containing the parsed attributes for that event.
     *
     * @param commandDetails String that contains command attributes and a redundant event flag.
     * @return The String[] of parsed attributes for an event. Null otherwise.
     */
    private static String[] parseAddEvent(String commandDetails) throws DukeException {
        try {
            String commandAttributes = getCommandAttributes(commandDetails);
            String[] parsedAttributes = new String[4];

            String title = retrieveItemAttribute(commandAttributes, ItemAttribute.TITLE);
            parsedAttributes[INDEX_OF_TITLE] = title;
            String dateTime = retrieveItemAttribute(commandAttributes, ItemAttribute.DATE);
            parsedAttributes[INDEX_OF_DATETIME] = dateTime;
            String venue = retrieveItemAttribute(commandAttributes, ItemAttribute.VENUE);
            parsedAttributes[INDEX_OF_VENUE] = venue;
            String budget = retrieveItemAttribute(commandAttributes, ItemAttribute.BUDGET);
            parsedAttributes[INDEX_OF_BUDGET] = budget;

            return parsedAttributes;

        } catch (NoCommandAttributesException e) {
            throw new DukeException("No details about the event you're trying to add is given!\n"
                    + "Format: add -e n/TITLE d/dd-MM-yyyy HHmm v/VENUE b/BUDGET");
        } catch (AttributeNotFoundException e) {
            String attributeType = ItemAttribute.getAttributeName(e.getItemAttribute());
            String attributeFlag = ItemAttribute.getItemFlag(e.getItemAttribute());
            throw new DukeException("Please add a " + attributeType + "for your event using "
                    + attributeFlag + attributeType.toUpperCase());
        }
    }

    private static String[] parseAddTask(String commandDetails) throws DukeException {

        if (Duke.eventCatalog.isEmpty()) {
            throw new DukeException("There is no event to assign this task to! Please add "
                    + "an event using the flag '-e'. ");
        }
        if (Duke.memberRoster.isEmpty()) {
            throw new DukeException("There are no members in your roster to assign this task to!");
        }

        try {
            String commandAttributes = getCommandAttributes(commandDetails);
            String[] parsedAttributes = new String[2];

            String title = retrieveItemAttribute(commandAttributes, ItemAttribute.TITLE);
            parsedAttributes[INDEX_OF_TITLE] = title;
            String dateTime = retrieveItemAttribute(commandAttributes, ItemAttribute.DATE);
            parsedAttributes[INDEX_OF_DATETIME] = dateTime;

            return parsedAttributes;

        } catch (NoCommandAttributesException e) {
            throw new DukeException("No details about the task you're trying to add is given!\n"
                    + "Format: add -t n/TITLE d/dd-MM-yyyy HHmm");
        } catch (AttributeNotFoundException e) {
            String attributeType = ItemAttribute.getAttributeName(e.getItemAttribute());
            String attributeFlag = ItemAttribute.getItemFlag(e.getItemAttribute());
            throw new DukeException("Please add a " + attributeType + "for your task using "
                    + attributeFlag + attributeType.toUpperCase());
        }
    }

    private static String[] parseAddMembers(String commandDetails) throws DukeException {

        try {
            String[] memberNames = commandDetails.split(" +", 2);
            String[] parsedAttributes = memberNames[1].split(", +");
            for (int i = 0; i < parsedAttributes.length; i++) {
                parsedAttributes[i] = parsedAttributes[i].toUpperCase();
                if (!isValidName(parsedAttributes[i])) {
                    throw new DukeException("One or more of the member names are not valid. "
                            + "Please separate the names with ', '. ");
                }
            }
            return parsedAttributes;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Member name cannot be empty. ");
        }
    }

    private static int getEventForTask() {
        Ui.promptForEventIndex();
        int eventIndex = -1;
        boolean isCorrectEvent = false;
        while (!isCorrectEvent) {
            try {
                assert !Duke.eventCatalog.isEmpty() : "The event catalog should not be empty";
                Ui.printEventCatalog();
                Ui.printLineBreak();
                eventIndex = Integer.parseInt(Ui.readInput()) - 1;
                Event event = Duke.eventCatalog.get(eventIndex);
                assert event != null : "Event does not exist";
                isCorrectEvent = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number corresponding to the event "
                        + "you want to add to. ");
                Ui.printLineBreak();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such event. Please enter a valid event for your task. ");
                Ui.printLineBreak();
            }
        }
        assert eventIndex >= 0 : "Event index should be valid";
        return eventIndex;
    }

    private static int[] getMembersForTask() {
        Ui.printLineBreak();
        Ui.promptForMemberIndex();
        int[] memberIndexes = null;
        boolean isCorrectMember = false;
        while (!isCorrectMember) {
            try {
                assert !Duke.memberRoster.isEmpty() : "The member roster should not be empty";
                Ui.printMemberRoster();
                Ui.printLineBreak();
                memberIndexes = extractInt(Ui.readInput());
                for (int index : memberIndexes) {
                    Member member = Duke.memberRoster.get(index);
                    assert member != null : "Member does not exist";
                }
                isCorrectMember = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("One or more of these members do not exist. Please enter the "
                        + "index(es) corresponding to the correct member(s). ");
                Ui.printLineBreak();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printLineBreak();
            }
        }
        return memberIndexes;
    }

    public static boolean isValidName(String input) {
        return ((!input.equals("")) && (input.matches("^[A-Z. -]*$")));
    }
}
