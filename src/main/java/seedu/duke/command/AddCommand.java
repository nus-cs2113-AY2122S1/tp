package seedu.duke.command;

import seedu.duke.exceptions.LotsException;
import seedu.duke.Menu;
import seedu.duke.Person;
import seedu.duke.Ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private static final int MAX_FOOD_QUANTITY = 1000;
    private static final int MIN_FOOD_QUANTITY = 0;
    private static final int MAX_PEOPLE_IN_LIST = 99;
    private final String personName;
    private final int foodIndex;
    private final int foodQuantity;

    /**
     * Constructor with Regex checking input. If it does not match desirable input, then
     * variables will be initialised with a fixed input.
     *
     * @param input will be returned by functions if it passes regex.
     *              will return fixed variables if it does not pass regex.
     */
    public AddCommand(String input) throws LotsException {
        if (checkUserInput(input)) {
            int[] indexOfSlashes = indexOfSlashes(input);
            personName = getPersonName(input, indexOfSlashes[0], indexOfSlashes[1]);
            foodIndex = getFoodIndex(input, indexOfSlashes[1], indexOfSlashes[2]);
            foodQuantity = getFoodQuantity(input, indexOfSlashes[2]);
        } else {
            throw new LotsException("Invalid Command!" + System.lineSeparator()
                    + "Please check your range of values and the format." + System.lineSeparator()
                    + "Name: Shorter than 50 characters. Index: Range of menu. Quantity: 1 to 999."
                    + System.lineSeparator() + "Refer to the UG for more details.");
        }
    }

    /**
     * Adds a new person with the given attributes into the list of people. If person
     * already exists in the list of people, the order will be added to the existing
     * entries' attributes instead.
     * If it did not pass regex, it will throw new exception and no new addition of
     * person to the list of people will happen.
     *
     * @throws LotsException Throws a new LotsException when the command is invalid.
     * @throws LotsException Throws a new LotsException when exceeding limit of total people.
     */
    @Override
    public void execute() throws LotsException {
        checkNumOfPeopleOutOfLimit();
        boolean isPrint = true;
        boolean isNewPerson = getMatchedIndex(personName) > peopleManager.getSize();
        if (checkValidParams() && isNewPerson) {
            addNewPerson(isPrint);
        } else if (!isNewPerson) {
            updateExistingPerson(isPrint);
        } else {
            throw new LotsException("Invalid Command!" + System.lineSeparator()
                    + "Please check your range of values and the format." + System.lineSeparator()
                    + "Name: Shorter than 50 characters. Index: Range of menu. Quantity: 1 to 999."
                    + System.lineSeparator() + "Refer to the UG for more details.");
        }
    }

    /**
     * Updates an existing person orders with the new inputs.
     *
     * @param isPrint checks to see if add message is to be printed.
     * @throws LotsException if food quantity or index is out of bounds.
     */
    private void updateExistingPerson(boolean isPrint) throws LotsException {
        int currIndex = getMatchedIndex(personName);
        peopleManager.getPerson(currIndex).addFoodToIndividualFoodOrders(foodIndex, foodQuantity);
        if (isPrint) {
            Ui.printAddedOrderMessage(peopleManager.getPerson(currIndex));
        }
    }

    /**
     * Creates a new person and adds the orders to the person.
     *
     * @param isPrint checks to see if add message is to be printed.
     * @throws LotsException if food quantity or index is out of bounds.
     */
    private void addNewPerson(boolean isPrint) throws LotsException {
        Person person = new Person(personName);
        person.addFoodToIndividualFoodOrders(foodIndex, foodQuantity);
        super.peopleManager.addPerson(person);
        if (isPrint) {
            Ui.printAddedOrderMessage(person);
        }
    }

    /**
     * Checks if the parameters for adding an order is valid.
     *
     * @return a boolean true if parameters are valid.
     */
    private boolean checkValidParams() {
        boolean isValidName = personName != "";
        boolean isValidIndex = foodIndex != -1;
        boolean isValidQuantity = foodQuantity != -1;
        boolean isValidParams = isValidIndex || isValidName || isValidQuantity;
        return isValidParams;
    }

    /**
     * Adds file data into program.
     *
     * @throws LotsException if data is corrupted.
     */
    @Override
    public void executeFromFile() throws LotsException {
        checkNumOfPeopleOutOfLimit();
        boolean isPrint = false;
        boolean isNewPerson = getMatchedIndex(personName) > peopleManager.getSize();
        if (checkValidParams() && isNewPerson) {
            addNewPerson(isPrint);
        } else if (!isNewPerson) {
            updateExistingPerson(isPrint);
        } else {
            throw new LotsException("File is corrupted! New file will be created.");
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
                    "^add \\/n [a-zA-Z0-9][\\w \\d]{0,50} \\/i \\d{1,2} \\/q \\d{1,3}$",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();
            return matchFound;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Obtain index of "/" in the string to be used to split input strings into substrings.
     *
     * @param input User input.
     * @return an array of index of "/".
     */
    private static int[] indexOfSlashes(String input) {

        int[] indexOfSlashes = new int[3];

        //Index firstSlashIndex to secondSlashIndex holds Person Name.
        int firstSlashIndex = input.indexOf("/");
        //Index secondSlashIndex to thirdSlashIndex holds index of Food.
        int secondSlashIndex = input.indexOf("/", firstSlashIndex + 1);
        //Index thirdSlashIndex to last index holds food quantity.
        int thirdSlashIndex = input.indexOf("/", secondSlashIndex + 1);

        indexOfSlashes[0] = firstSlashIndex;
        indexOfSlashes[1] = secondSlashIndex;
        indexOfSlashes[2] = thirdSlashIndex;

        return indexOfSlashes;
    }

    /**
     * Function used to parse the user input to get person name.
     *
     * @param input              User input.
     * @param indexOfFirstSlash  First "/" index.
     * @param indexOfSecondSlash Second "/" index.
     * @return Person Name in String type.
     */
    private static String getPersonName(String input, int indexOfFirstSlash, int indexOfSecondSlash) {
        String tempPersonName = input.substring(indexOfFirstSlash + 2, indexOfSecondSlash - 1);
        assert tempPersonName != null : "Input to Person Name cannot be NULL!";
        return tempPersonName.trim().toUpperCase();
    }

    /**
     * Function used to parse the user input to get food index.
     *
     * @param input              User input.
     * @param indexOfSecondSlash Second "/" index.
     * @param indexOfThirdSlash  Third "/" index.
     * @return Food index in int type.
     * @throw NumberFormatException when the index is out of range.
     */
    private static int getFoodIndex(String input, int indexOfSecondSlash, int indexOfThirdSlash)
            throws NumberFormatException, LotsException {
        String subStringFoodIndex = input.substring(indexOfSecondSlash + 2, indexOfThirdSlash - 1).trim();
        assert subStringFoodIndex != "" : "Input to AddCommand Cannot be NULL!";
        try {
            int foodIndex = Integer.parseInt(subStringFoodIndex);
            if (foodIndex > Menu.TOTAL_MENU_ITEMS || foodIndex <= 0) {
                throw new LotsException("Index out of range. Please try again!");
            } else {
                return foodIndex;
            }
        } catch (NumberFormatException e) {
            String errorMsg = "Please make sure the Food Index is keyed in correctly.";
            throw new LotsException(errorMsg);
        }
    }

    /**
     * Function used to parse the user input to get food index.
     *
     * @param input             User input.
     * @param indexOfThirdSlash Third "/" index.
     * @return Food Quantity in int type.
     * @throws NumberFormatException when the quantity is out of range.
     */
    private static int getFoodQuantity(String input, int indexOfThirdSlash)
            throws NumberFormatException, LotsException {
        String subStringFoodQuantity = input.substring(indexOfThirdSlash + 2).trim();
        assert subStringFoodQuantity != "" : "Input to AddCommand Cannot be NULL!";
        try {
            int foodQuantity = Integer.parseInt(subStringFoodQuantity);
            if (foodQuantity > MAX_FOOD_QUANTITY || foodQuantity <= MIN_FOOD_QUANTITY) {
                throw new LotsException("Quantity out of range(1 to 999) , please try again!");
            } else {
                return foodQuantity;
            }
        } catch (NumberFormatException e) {
            String errorMsg = "Please make sure the Food Quantity is keyed in correctly.";
            throw new LotsException(errorMsg);
        }
    }


    /**
     * Function to get the index of existing name in the list if a match is found.
     *
     * @param currName Name of person to be found in list.
     * @return Index of matched entry.
     */
    private int getMatchedIndex(String currName) {
        int currNumOfPeople = peopleManager.getSize();
        for (int i = 0; i < currNumOfPeople; i++) {
            if (currName.equals(peopleManager.getName(i))) {
                return i;
            }
        }
        return currNumOfPeople + 1;
    }

    /**
     * Function to check if the total number of people in the list will exceed the limit of 99 after adding.
     *
     * @throws LotsException when the total number of people exceeds the limit after adding.
     */
    private void checkNumOfPeopleOutOfLimit() throws LotsException {
        int currTotalPeople = peopleManager.getSize();
        if (currTotalPeople + 1 > MAX_PEOPLE_IN_LIST) {
            throw new LotsException("Maximum number of people reached! Please make sure the total number of people"
                    + "ordering is less than 100.");
        }
    }

}
