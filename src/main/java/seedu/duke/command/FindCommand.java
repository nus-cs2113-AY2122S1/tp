package seedu.duke.command;

import seedu.duke.Person;
import seedu.duke.Ui;
import seedu.duke.exceptions.LotsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String stringToFind;
    private static final String REGEX = "^find /n [a-zA-Z0-9][\\w ]{0,50}$";

    public FindCommand(String input) throws LotsException {
        if (checkUserInput(input)) {
            stringToFind = getSearchString(input).toLowerCase();
        } else {
            throw new LotsException("Wrong format for command Find. E.g) find /n <String to find>");
        }
    }

    /**
     * Checks if the search is contained in any of the names
     * of the current order. If yes, prints it out.
     * If not, prints the no names matched line.
     *
     * @throws LotsException If there's any error that only an admin can resolve.
     */
    @Override
    public void execute() throws LotsException {
        boolean hasMatch;
        hasMatch = checkIfMatchAndPrint();
        if (!hasMatch) {
            Ui.printWithBorder("No names matched your input.");
        }
    }

    /**
     * Returns true if there was a match with the search input given,
     * false otherwise.
     *
     * @return True if there was a match, else false.
     * @throws LotsException LotsException If there is an error in the indexing.
     */
    private boolean checkIfMatchAndPrint() throws LotsException {
        int totalPeople = super.peopleManager.getSize();
        Person currentPerson;
        boolean hasMatch = false;
        assert stringToFind != null : "stringToFind Cannot be null";
        try {
            for (int i = 0; i < totalPeople; i++) {
                currentPerson = super.peopleManager.getPerson(i);
                String name = currentPerson.getPersonName();

                if (name.toLowerCase().contains(stringToFind)) {
                    Ui.printWithoutBorder((i + 1) + ") " + name + ":");
                    Ui.printIndividualPersonOrder(currentPerson);
                    hasMatch = true;
                }
            }
            Ui.printWithBorder("");
        } catch (IndexOutOfBoundsException e) {
            throw new LotsException("Find command error! Please contact an admin.");
        }
        return hasMatch;
    }

    /**
     * Returns the string that the user wishes to search for.
     *
     * @param input The user input in String.
     * @return The substring that the user wants to search.
     * @throws LotsException If unable to find
     */
    private String getSearchString(String input) throws LotsException {
        String stringToReturn;
        assert input != null : "Input string cannot be null";
        int indexOfSlash = getIndexOfSlash(input);
        try {
            stringToReturn = input.substring(indexOfSlash).strip();
            return stringToReturn;
        } catch (IndexOutOfBoundsException e) {
            throw new LotsException("Error in Find command. Please contact an Admin.");
        }
    }

    /**
     * Returns the index + 2 of the first "/"  in the input.
     * else, throws a LotsException.
     *
     * @param input The String containing whole user input.
     * @return Returns the index + 2 of the first "/"
     * @throws LotsException Thrown if unable to find a "/" in the input.
     */
    private int getIndexOfSlash(String input) throws LotsException {
        int index;
        index = input.indexOf("/") + 2; // +2 to skip over /n
        assert index >= 1 : "/ index in find command cannot be less than 1 (-1 + 2).";
        if (index == 1) {
            throw new LotsException("Wrong format for command Find! /n is not found in input.");
        }
        return index;
    }

    /**
     * Returns true if input matches the regex provided,
     * else false.
     *
     * @param input The user input in String.
     * @return True if the user input matches the regex. Else false.
     * @throws IllegalArgumentException Thrown when there is a pattern error.
     */
    private boolean checkUserInput(String input) {
        try {
            Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            boolean matchFound = matcher.find();
            return matchFound;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
