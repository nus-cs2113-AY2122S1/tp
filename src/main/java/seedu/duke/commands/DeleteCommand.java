package seedu.duke.commands;

import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.Duke.memberRoster;


public class DeleteCommand extends Command {

    protected static final String EVENT_FLAG = "-e";
    protected static final String TASK_FLAG = "-t";
    protected static final String MEMBER_FLAG = "-m";
    protected static final String DELETE_ALL_FLAG = "all";

    // input from user
    private String itemFlag;
    private int indexToDelete;

    private boolean isDeleteAll;
    private boolean isCorrectFormat;
    private static int indexOfMemberToDelete = -1;

    // create logger
    private static final Logger logger = Logger.getLogger("Logger");


    // v2.0: deleteCommand deletes purely based on index, i.e. delete -t/-e [TASK_INDEX]
    public DeleteCommand(String[] command) {

        isCorrectFormat = true;
        isDeleteAll = false;
        logger.setLevel(Level.INFO);

        try {
            logger.log(Level.INFO, "going to start processing");
            if (command.length == 1) {
                throw new DukeException("Please specify what you wish to delete.");
            }
            itemFlag = command[1].trim();
            if (isDeleteAllFlag(itemFlag)) {
                isDeleteAll = true;
            } else if (isValidFlag(itemFlag)) {
                prepareInputs(command);
            } else {
                logger.log(Level.WARNING, "processing error");
                throw new DukeException("Invalid item flag entered. Please specify event '-e' "
                        + "or task '-t'.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            isCorrectFormat = false;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number for the item index!");
            isCorrectFormat = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No such item index exists!");
            isCorrectFormat = false;
        }
    }

    public CommandResult execute() {
        if (isCorrectFormat) {
            if (isDeleteAll) {
                if (confirmDeleteAll()) {
                    deleteAllEventsAndTasks();
                    return new CommandResult("I have deleted everything!");
                } else {
                    return new CommandResult("I will not delete anything!");
                }
            }

            String deletedItem;
            if (isEventFlag(itemFlag)) {
                assert indexToDelete < eventCatalog.size();
                deletedItem = deleteEvent(indexToDelete);
                Parser.updateIndexToNoEventSelected();
                return new CommandResult(Ui.getEventDeletionMessage(deletedItem));
            } else if (isTaskFlag(itemFlag)) {
                assert indexToDelete < eventCatalog.size();
                deletedItem = deleteTask(indexToDelete);
                return new CommandResult(Ui.getTaskDeletionMessage(deletedItem));
            } else if (isMemberFlag(itemFlag)) {
                deletedItem = deleteMember(indexOfMemberToDelete);
                return new CommandResult(Ui.getMemberDeletionMessage(deletedItem));
            }
        }
        return new CommandResult("Unable to delete item!");
    }

    private void prepareInputs(String[] command) throws DukeException {
        if (command.length == 2) {
            if (isMemberFlag(itemFlag)) {
                throw new DukeException("Please give me the name of the member you wish to delete!");
            } else {
                throw new DukeException("Please give me the index of the event you wish to delete!");
            }
        } else if (isEventFlag(itemFlag) || isTaskFlag(itemFlag)) {
            indexToDelete = getIndex(command[2]);
        } else if (isMemberFlag(itemFlag)) {
            indexOfMemberToDelete = getMemberIndex(command[2]);
            if (!memberExists()) {
                throw new DukeException("No such member found!");
            }
        } else {
            throw new DukeException("Invalid item index!");
        }
    }

    private static String deleteEvent(int index) {
        String eventTitle = eventCatalog.get(index).getTitle();
        eventCatalog.remove(index);
        return eventTitle;
    }

    private static String deleteTask(int index) {
        String taskTitle = eventCatalog.get(index).getTitle();
        eventCatalog.remove(index);
        return taskTitle;
    }

    private static String deleteMember(int index) {
        String memberName = memberRoster.get(index).getName();
        memberRoster.remove(index);
        return memberName;
    }

    private static void deleteAllEventsAndTasks() {
        eventCatalog.clear();
    }

    private static int getIndex(String indexAsString) {
        return Integer.parseInt(indexAsString.trim()) - 1;
    }

    private static boolean isEventFlag(String flag) {
        return flag.trim().equalsIgnoreCase(EVENT_FLAG);
    }

    private static boolean isTaskFlag(String flag) {
        return flag.trim().equalsIgnoreCase(TASK_FLAG);
    }

    private static boolean isMemberFlag(String flag) {
        return flag.trim().equalsIgnoreCase(MEMBER_FLAG);
    }

    private static boolean isValidFlag(String flag) {
        return isEventFlag(flag) || isTaskFlag(flag) || isMemberFlag(flag);
    }

    private static boolean isDeleteAllFlag(String flag) {
        return flag.trim().equalsIgnoreCase(DELETE_ALL_FLAG);
    }

    private static boolean confirmDeleteAll() {
        System.out.println("Are you sure you want to delete all events? (Y/N)");
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        return userInput.trim().equalsIgnoreCase("y");
    }

    public static int getMemberIndex(String name) {
        for (int i = 0; i < memberRoster.size(); i++) {
            Member member = memberRoster.get(i);
            if (member.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean memberExists() {
        return indexOfMemberToDelete != -1;
    }
}

