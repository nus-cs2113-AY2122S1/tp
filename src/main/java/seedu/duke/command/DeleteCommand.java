package seedu.duke.command;


import seedu.duke.Menu;
import seedu.duke.exceptions.LotsException;
import seedu.duke.PeopleManager;
import seedu.duke.Person;
import seedu.duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int personIndex;
    private int foodIndex;

    /**
     * Splits the input given the regular expression of a whitespace and
     * initialise the personIndex and orderIndex.
     *
     * @param input The entire line of command entered by the user.
     * @throws LotsException If there is no input after the delete command or when the personIndex is not an integer.
     */
    public DeleteCommand(String input) throws LotsException {
        String[] splitInput = input.split(" ");
        if (!checkUserInput(input)) {
            throw new LotsException("Please enter a valid person's index followed by the order index! i.e. delete 1/2"
                + System.lineSeparator() + "Enter \"list\" to view person's and order index.");
        }
        assert checkUserInput(input) == true : "Invalid delete input command";
        try {
            personIndex = getPersonIndex(splitInput[1]);
            foodIndex = getFoodIndex(splitInput[1]);
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            throw new LotsException("Please enter a valid person's index followed by the order index! i.e. delete 1/2"
                + System.lineSeparator() + "Enter \"list\" to view person's and order index.");
        }
    }

    /**
     * Regex to check User Input before passing onto the class.
     *
     * @param input user input.
     * @return a boolean true if the user input passes the regex.
     * @throws IllegalArgumentException when the pattern for Regex is not able to be interpreted.
     */
    private boolean checkUserInput(String input) throws IllegalArgumentException {
        try {
            Pattern pattern = Pattern.compile(
                "^delete [1-9][0-9]?/[1-9][0-9]?$", //max 99 food orders per pax
                Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.find();
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Get the index of the person whose order is to be deleted.
     *
     * @param deleteParams String from the user input after the delete command word.
     * @return Returns the index of the person in the form of an integer.
     * @throws IndexOutOfBoundsException When "/" is not found in the string.
     * @throws NumberFormatException     When the person index can't be parsed into an integer.
     */
    private int getPersonIndex(String deleteParams) throws IndexOutOfBoundsException, NumberFormatException {
        int slashIndex = deleteParams.indexOf('/');
        String personIndexInString = deleteParams.substring(0, slashIndex);
        return Integer.parseInt(personIndexInString) - 1;
    }

    /**
     * Get the index of the order to be deleted.
     *
     * @param deleteParams String from the user input after the delete command word.
     * @return Returns the index of the order to be deleted in the form of an integer.
     * @throws IndexOutOfBoundsException When "/" is not found in the string.
     * @throws LotsException             When the order index is out of range.
     */
    private int getFoodIndex(String deleteParams) throws IndexOutOfBoundsException, LotsException {
        int slashIndex = deleteParams.indexOf('/');
        String foodIndexInString = deleteParams.substring(slashIndex + 1);
        int foodIndexInInteger = Integer.parseInt(foodIndexInString) - 1;
        if (foodIndexInInteger < 0) {
            throw new LotsException("Please enter a valid order index i.e 1 to " + Menu.TOTAL_MENU_ITEMS);
        }
        assert foodIndexInInteger >= 0 : "Order index cannot be negative.";
        return foodIndexInInteger;
    }

    /**
     * Deletes a specific order from a particular person.
     *
     * @throws LotsException If the order or person's index to be deleted is out of bounds.
     */
    @Override
    public void execute() throws LotsException {
        try {
            deleteOrder(super.peopleManager);
        } catch (IndexOutOfBoundsException e) {
            throw new LotsException("Please enter a valid person's index followed by the order index! i.e. delete 1/2"
                + System.lineSeparator() + "Enter \"list\" to view person's and order index.");
        }
    }

    /**
     * Change the quantity of that particular order index to 0.
     *
     * @param manager The list of people that are ordering.
     * @throws IndexOutOfBoundsException Throws when personIndex given is larger than the number of people.
     */
    private void deleteOrder(PeopleManager manager) throws IndexOutOfBoundsException, LotsException {
        if (manager.isEmpty()) {
            Ui.printEmptyMessage();
        } else {
            Person personToDeleteFrom = manager.getPerson(personIndex);
            assert personToDeleteFrom != null : "Person does not exists.";
            personToDeleteFrom.deleteParticularOrder(foodIndex);
            Ui.printDeleteMessage(personToDeleteFrom, foodIndex);
            deletePersonIfEmpty(manager, personToDeleteFrom);
        }
    }

    /**
     * Removes the person the list if his individual order list is empty.
     *
     * @param manager            The list of people that are ordering.
     * @param personToDeleteFrom Person whose order is to be deleted from.
     * @throws LotsException When there is an error in removing the person from the people manager.
     */
    private void deletePersonIfEmpty(PeopleManager manager, Person personToDeleteFrom) throws LotsException {
        if (personToDeleteFrom.isEmpty()) {
            manager.deletePerson(personIndex);
        }
    }
}
