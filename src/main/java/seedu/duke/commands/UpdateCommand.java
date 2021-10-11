package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateCommand extends Command {
    protected String listType;
    protected String[] userCommand;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HHmm");
    protected static final String TITLE_FLAG = "n/";
    protected static final String DATE_FLAG = "d/";
    protected static final String VENUE_FLAG = "v/";
    protected static final String BUDGET_FLAG = "b/";
    protected static final String DESCRIPTION_FLAG = "p/";

    public UpdateCommand(String[] command) {
        this.listType = command[1];
        this.userCommand = command;
        ListCommand list = new ListCommand(userCommand);
        list.execute();
        System.out.println("Please type the index of the event you would like to update, "
                + System.lineSeparator() + "and the attribute you would like to update in the format"
                + System.lineSeparator() + "Index_num [n/TITLE] [d/dd-MM-yyyy HHmm] [p/DESCRIPTION] "
                + System.lineSeparator() + "Only for Events: [v/VENUE] [b/BUDGET]");
    }

    public CommandResult execute() {
        String userInput = Ui.readInput();
        String[] userUpdates = userInput.split(" ", 10);
        try {
            Integer index = Integer.parseInt(userUpdates[0]);
            if (userUpdates.length == 1) {
                throw new DukeException("Please specify what to update. ");
            }
            for (String update : userUpdates) {
                if (update.length() >= 2) {
                    String attribute = update.trim().substring(2);
                    if (update.contains(TITLE_FLAG)) {
                        updateTitle(index, userInput);
                    } else if (update.contains(DATE_FLAG)) {
                        updateDate(index, userInput);
                    } else if (update.contains(VENUE_FLAG)) {
                        updateVenue(index, userInput);
                    } else if (update.contains(BUDGET_FLAG)) {
                        updateBudget(index, userInput);
                    } else if (update.contains(DESCRIPTION_FLAG)) {
                        updateDescription(index, userInput);
                    }
                }
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("please follow the error closely");
        }
        return new CommandResult("Item Updated");
    }


    protected void updateDate(Integer index, String attribute) {
        try {
            if (this.listType.equalsIgnoreCase("-e")) {
                Event itemToBeUpdated = Duke.eventList.get(index);
                String newDate = retrieveItemAttribute(attribute, DATE_FLAG);
                itemToBeUpdated.setDateTime(Parser.convertDateTime(newDate));
            } else if (this.listType.equalsIgnoreCase("-t")) {
                Task itemToBeUpdated = Duke.taskList.get(index);
                String newDate = retrieveItemAttribute(attribute, DATE_FLAG);
                itemToBeUpdated.setDateTime(Parser.convertDateTime(newDate));
            }
        } catch (DateTimeParseException e) {
            System.out.println("incorrect format please ensure format for date is of [d/dd-MM-yyyy HHmm]");
        }

    }

    protected void updateTitle(Integer index, String attribute) {
        if (this.listType.equalsIgnoreCase("-e")) {
            Event itemToBeUpdated = Duke.eventList.get(index);
            String newTitle = retrieveItemAttribute(attribute, TITLE_FLAG);
            itemToBeUpdated.setTitle(newTitle);
        } else if (this.listType.equalsIgnoreCase("-t")) {
            Task itemToBeUpdated = Duke.taskList.get(index);
            String newTitle = retrieveItemAttribute(attribute, TITLE_FLAG);
            itemToBeUpdated.setTitle(newTitle);
        }
    }

    protected void updateVenue(Integer index, String attribute) {
        if (this.listType.equalsIgnoreCase("-e")) {
            Event itemToBeUpdated = Duke.eventList.get(index);
            String newVenue = retrieveItemAttribute(attribute, VENUE_FLAG);
            itemToBeUpdated.setVenue(newVenue);
        } else if (this.listType.equalsIgnoreCase("-t")) {
            System.out.println("such a field does not exist");
        }
    }

    protected void updateBudget(Integer index, String attribute) {
        try {
            if (this.listType.equalsIgnoreCase("-e")) {
                Event itemToBeUpdated = Duke.eventList.get(index);
                double newBudget = retrieveEventBudget(attribute);
                itemToBeUpdated.setBudget(newBudget);
            } else if (this.listType.equalsIgnoreCase("-t")) {
                System.out.println("such a field does not exist");
            }
        } catch (NullPointerException e) {
            System.out.println("please ensure budget is a double");
        }
    }

    protected void updateDescription(Integer index, String attribute) {
        if (this.listType.equalsIgnoreCase("-e")) {
            Event itemToBeUpdated = Duke.eventList.get(index);
            String newDescription = retrieveItemAttribute(attribute, DESCRIPTION_FLAG);
            itemToBeUpdated.setDescription(newDescription);
        } else if (this.listType.equalsIgnoreCase("-t")) {
            Task itemToBeUpdated = Duke.taskList.get(index);
            String newDescription = retrieveItemAttribute(attribute, DESCRIPTION_FLAG);
            itemToBeUpdated.setDescription(newDescription);
        }
    }

    private double retrieveEventBudget(String response) {
        int startOfEventBudget = response.indexOf(BUDGET_FLAG) + 2;
        int endOfEventBudget = response.indexOf("/", startOfEventBudget) - 2;
        try {
            if (endOfEventBudget < 0) {
                return Double.parseDouble(response.trim().substring(startOfEventBudget));
            }
            return Double.parseDouble(response.trim().substring(startOfEventBudget, endOfEventBudget));
        } catch (NumberFormatException e) {
            System.out.print("Budget needs to be an integer. ");
            // Returns -1 to signify that the budget entered was not a valid integer
            return -1;
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
}
