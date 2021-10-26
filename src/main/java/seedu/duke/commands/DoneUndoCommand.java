package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.parser.Parser;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.mainlists.EventCatalog;
import seedu.duke.items.Item;

import java.util.ArrayList;

public class DoneUndoCommand extends Command {

    protected static final String TASK_FLAG = "-t";
    protected static final String EVENT_FLAG = "-e";
    protected static final String DONE = "done";
    protected static final String UNDO = "undo";

    protected static String action;
    protected static String itemType;
    protected static int[] indexes;

    // Indicates if an error occurs due to the wrong format typed by the user
    protected static boolean isCorrectFormat;

    public DoneUndoCommand(String[] command) {
        isCorrectFormat = true;
        try {
            if (command.length == 1) {
                throw new DukeException("Please specify the indexes of the items you want to mark done or undo "
                        + "using the event '-e' or task '-t' flags. ");
            }
            action = command[0];
            itemType = command[1];
            if (!(itemType.equalsIgnoreCase(TASK_FLAG) || itemType.equalsIgnoreCase(EVENT_FLAG))) {
                throw new DukeException("Please specify the type of items you want to mark done or undo "
                        + "using the event '-e' or task '-t' flags. ");
            }
            if (command.length == 2) {
                throw new DukeException("Please specify the indexes of the tasks or events you want "
                        + "to mark done or undo. ");
            }
            extractInt(command.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            isCorrectFormat = false;
        }
    }

    private static void extractInt(String input) throws DukeException {
        String parsedInput = input.replaceAll("[^\\d]", " ").trim();
        if (parsedInput.isBlank()) {
            throw new DukeException("Indexes entered need to be valid numbers. ");
        }

        String[] stringIndexes = parsedInput.split(" +");
        indexes = new int[stringIndexes.length];

        for (int i = 0; i < stringIndexes.length; i++) {
            indexes[i] = Integer.parseInt(stringIndexes[i]);
        }
    }

    private String done() throws DukeException {
        try {
            StringBuilder listOfItems = new StringBuilder();
            if (itemType.equalsIgnoreCase(TASK_FLAG)) {
                if (Parser.noEventSelected()) {
                    throw new DukeException("Please select which event the task is under using the "
                            + "'select' command. ");
                }
                for (int index : indexes) {
                    Duke.eventCatalog.get(Parser.getIndexOfLastSelectedEvent())
                            .getFromTaskList(index - 1).markAsDone();
                    listOfItems.append(Duke.eventCatalog.get(Parser.getIndexOfLastSelectedEvent())
                            .getFromTaskList(index - 1)).append("\n");
                }
            }
            if (itemType.equalsIgnoreCase(EVENT_FLAG)) {
                for (int index : indexes) {
                    Duke.eventCatalog.get(index - 1).markAsDone();
                    listOfItems.append(Duke.eventCatalog.get(index - 1)).append("\n");
                }
            }
            return listOfItems.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("One or more of the items do not exist. ");
        }
    }

    private String undo() throws DukeException {
        try {
            StringBuilder listOfItems = new StringBuilder();
            if (itemType.equalsIgnoreCase(TASK_FLAG)) {
                if (Parser.noEventSelected()) {
                    throw new DukeException("Please select which event the task is under using the "
                            + "'select' command. ");
                }
                for (int index : indexes) {
                    Duke.eventCatalog.get(Parser.getIndexOfLastSelectedEvent())
                            .getFromTaskList(index - 1).undo();
                    listOfItems.append(Duke.eventCatalog.get(Parser.getIndexOfLastSelectedEvent())
                            .getFromTaskList(index - 1)).append("\n");
                }
            }
            if (itemType.equalsIgnoreCase(EVENT_FLAG)) {
                for (int index : indexes) {
                    Duke.eventCatalog.get(index - 1).undo();
                    listOfItems.append(Duke.eventCatalog.get(index - 1)).append("\n");
                }
            }
            return listOfItems.toString();
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
