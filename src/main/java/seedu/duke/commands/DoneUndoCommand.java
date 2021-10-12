package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Parser;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Item;

import java.util.ArrayList;

public class DoneUndoCommand extends Command {

    protected static final String TASK_FLAG = "-t";
    protected static final String EVENT_FLAG = "-e";
    protected static final String DONE = "done";
    protected static final String UNDO = "undo";

    protected static String action;
    protected static String[] indexes;
    protected static ArrayList<Item> sortedList = new ArrayList<>();

    // Indicates if an error occurs due to the wrong format typed by the user
    protected static boolean isCorrectFormat;

    public DoneUndoCommand(String[] command) {
        isCorrectFormat = true;
        sortedList.clear();
        try {
            if (command.length == 1) {
                throw new DukeException("Please specify the indexes of the items you want to mark done or undo. ");
            }
            action = command[0];

            if (command[1].equalsIgnoreCase(TASK_FLAG)) {
                if (command.length == 2) {
                    throw new DukeException("Please specify the indexes of the tasks you want "
                            + "to mark done or undo. ");
                }
                indexes = command[2].split(",");
                sortedList = new ArrayList<>(Duke.taskList);
                Parser.bubbleSortItems(sortedList);
            } else if (command[1].equalsIgnoreCase(EVENT_FLAG)) {
                if (command.length == 2) {
                    throw new DukeException("Please specify the indexes of the events you want to "
                            + "mark done or undo. ");
                }
                indexes = command[2].split(",");
                sortedList = new ArrayList<>(Duke.eventList);
                Parser.bubbleSortItems(sortedList);
            } else {
                indexes = command[1].split(",");
                sortedList = Parser.makeMainList();
                Parser.bubbleSortItems(sortedList);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            isCorrectFormat = false;
        }
    }

    private String done() throws DukeException {
        try {
            StringBuilder listOfItems = new StringBuilder();
            for (String index : indexes) {
                sortedList.get(Integer.parseInt(index) - 1).markAsDone();
                listOfItems.append(sortedList.get(Integer.parseInt(index) - 1)).append("\n");
            }
            return listOfItems.toString();
        } catch (NumberFormatException e) {
            throw new DukeException("Indexes can only be integers separated by commas, e.g. "
                    + "'1,4,5,9'. ");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("One or more of the items do not exist. ");
        }
    }

    private String undo() throws DukeException {
        try {
            StringBuilder listOfItems = new StringBuilder();
            for (String index : indexes) {
                sortedList.get(Integer.parseInt(index) - 1).undo();
                listOfItems.append(sortedList.get(Integer.parseInt(index) - 1)).append("\n");
            }
            return listOfItems.toString();
        } catch (NumberFormatException e) {
            throw new DukeException("Indexes can only be integers separated by commas, e.g. "
                    + "'1,4,5,9'. ");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("One or more of the items do not exist. ");
        }
    }

    public CommandResult execute() {
        if (!isCorrectFormat) {
            return new CommandResult("Unable to mark as done or undo items. ");
        }
        try {
            if (action.equalsIgnoreCase(DONE)) {
                String listOfItems = done();
                System.out.print("Nice! I have marked these items as done: \n" + listOfItems);
            } else if (action.equalsIgnoreCase(UNDO)) {
                String listOfItems = undo();
                System.out.print("Okay, I have unmarked these items: \n" + listOfItems);
            }
            return new CommandResult("--------LIST UPDATED-----------");
        } catch (DukeException e) {
            return new CommandResult(e.getMessage() + "Unable to mark as done or undo items. ");
        }
    }
}
