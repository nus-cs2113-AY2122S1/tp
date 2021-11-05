package seedu.duke.command;

import seedu.duke.Menu;
import seedu.duke.exceptions.LotsException;
import seedu.duke.PeopleManager;
import seedu.duke.Person;
import seedu.duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    private int personIndex;
    private int foodIndex;
    private int quantity;

    //@@author thaddyyz
    /**
     * Splits the input given the regular expression of a whitespace, calls checkForCorrectInput() to
     * check input validity, and calls for the initialisation of the personIndex, orderIndex and
     * quantity.
     *
     * @param input The entire line of command entered by the user.
     * @throws LotsException If there is no input after the edit command or when the personIndex,
     *                       foodIndex or quantity is not a positive integer.
     */
    public EditCommand(String input) throws LotsException {
        String[] splitInput = input.split(" ");
        checkForCorrectInput(input);
        try {
            identifyInputParameters(splitInput);
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            throw new LotsException("Please enter a valid person's index followed by the order index"
                + " and order quantity! i.e. edit 1/1 /q 8"
                + System.lineSeparator() + "Enter \"list\" to view person's and order index.");
        }
    }

    /**
     * Calls checkUserInput() to checks validity of input, and if input is invalid, return an exception.
     *
     * @param input The entire line of command entered by the user.
     * @throws LotsException If there is no input after the edit command or when the personIndex,
     *                       foodIndex or quantity is not a positive integer.
     */
    private void checkForCorrectInput(String input) throws LotsException {
        if (!checkUserInput(input)) {
            throw new LotsException("Please enter a valid person's index followed by the order index"
                + " and order quantity! i.e. edit 1/1 /q 8"
                + System.lineSeparator() + "Enter \"list\" to view person's and order index.");
        }
        assert checkUserInput(input) == true : "Invalid edit input command";
    }

    /**
     * Calls various getter functions to identify various input parameters namely person index,
     * order index and quantity to change.
     *
     * @param splitInput The user sentence string input split into an array of words.
     * @throws LotsException If there is no input after the edit command or when the personIndex,
     *                       foodIndex or quantity is not a positive integer.
     */
    private void identifyInputParameters(String[] splitInput) throws LotsException {
        personIndex = getPersonIndex(splitInput[1]);
        foodIndex = getOrderIndex(splitInput[1]);
        quantity = getQuantity(splitInput[3]);
    }

    /**
     * Regex to check User Input before passing onto the class.
     *
     * @param input The entire line of command entered by the user.
     * @return a boolean true if the user input passes the regex.
     */
    private boolean checkUserInput(String input) {
        try {
            Pattern pattern = Pattern.compile(
                "^edit [1-9][0-9]?/[1-9][0-9]? /q \\d{1,3}?$", //max 99 food orders and quantity per pax
                Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.find();
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Get the index of the person whose order is to be edited.
     *
     * @param editParams String from the user input after the edit command word.
     * @return Returns the index of the person in the form of an integer.
     * @throws IndexOutOfBoundsException When "/" is not found in the string.
     * @throws NumberFormatException     When the person index can't be parsed into an integer.
     */
    private int getPersonIndex(String editParams) throws IndexOutOfBoundsException, NumberFormatException {
        int slashIndex = editParams.indexOf('/');
        String personIndexInString = editParams.substring(0, slashIndex);
        return Integer.parseInt(personIndexInString) - 1;
    }

    /**
     * Get the index of the order to be edited.
     *
     * @param editParams String from the user input after the edit command word.
     * @return Returns the index of the order to be edited in the form of an integer.
     * @throws IndexOutOfBoundsException When "/" is not found in the string.
     * @throws LotsException             When the order index is out of range.
     */
    private int getOrderIndex(String editParams) throws IndexOutOfBoundsException, LotsException {
        int slashIndex = editParams.indexOf('/');
        String orderIndexInString = editParams.substring(slashIndex + 1);
        int orderIndexInInteger = Integer.parseInt(orderIndexInString) - 1;
        if (orderIndexInInteger < 0) {
            throw new LotsException("Please enter a valid order index! i.e 1 to " + Menu.TOTAL_MENU_ITEMS);
        }
        assert orderIndexInInteger >= 0 : "Order index cannot be negative.";
        return orderIndexInInteger;
    }

    /**
     * Get the edited quantity.
     *
     * @param editParams String from the user input after /q.
     * @return Returns the edited quantity in the form of an integer.
     * @throws LotsException When the quantity is out of range.
     */
    private int getQuantity(String editParams) throws LotsException {
        int quantityInInteger = Integer.parseInt(editParams);
        if (quantityInInteger < 0) {
            throw new LotsException("Please enter a valid quantity number! i.e. 1 to 999");
        }
        assert quantityInInteger >= 0 : "Quantity number cannot be negative.";
        return quantityInInteger;
    }

    /**
     * Edits the quantity of a specific order from a particular person.
     *
     * @throws LotsException If the order or person's index to be deleted is out of bounds.
     */
    @Override
    public void execute() throws LotsException {
        try {
            editOrder(super.peopleManager);
        } catch (IndexOutOfBoundsException e) {
            throw new LotsException("Please enter a valid person's index followed by the order index"
                + " and order quantity! i.e. edit 1/1 /q 8"
                + System.lineSeparator() + "Enter \"list\" to view person's and order index.");
        }
    }

    /**
     * Change the quantity of that particular order index to the specified quantity.
     *
     * @param manager The list of people that are ordering.
     * @throws IndexOutOfBoundsException Throws when personIndex given is larger than the number of people.
     * @throws LotsException             When there is an error in editing the quanity in Person.
     */
    private void editOrder(PeopleManager manager) throws IndexOutOfBoundsException, LotsException {
        if (manager.isEmpty()) {
            Ui.printEmptyMessage();
        } else {
            Person personToEditFrom = manager.getPerson(personIndex);
            assert personToEditFrom != null : "Person does not exists.";
            personToEditFrom.editParticularOrder(foodIndex, quantity);
            printFeedbackMessage(personToEditFrom, foodIndex);
            //delete order if quantity 0
            deletePersonIfEmpty(manager, personToEditFrom);
        }
    }

    /**
     * Calls the appropriate print function based on quantity user whants to edit to.
     *
     * @param person    The Person class type containing details of person.
     * @param foodIndex The index of food based on menu.
     */
    private void printFeedbackMessage(Person person, int foodIndex) {
        if (quantity == 0) {
            Ui.printDeleteMessage(person, foodIndex);
        } else {
            Ui.printEditMessage(person, foodIndex);
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
    //@@author
}
