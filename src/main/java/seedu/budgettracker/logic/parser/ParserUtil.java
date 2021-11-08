package seedu.budgettracker.logic.parser;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import static seedu.budgettracker.common.Messages.MESSAGE_DATE_INVALID_FORMAT;
import static seedu.budgettracker.common.Messages.MESSAGE_INCORRECT_CATEGORY;
import static seedu.budgettracker.common.Messages.MESSAGE_INCORRECT_TYPE;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_DATE;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_DESCRIPTION;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEX;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_INDEXES;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_MONTH;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_NAME;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_NUMBER_AMOUNT;
import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_YEAR;

//@@author jyxhazcake
/**
 * Utility Parser class that helps to parse specific arguments.
 */
public class ParserUtil {

    /**
     * A date that represents an empty date.
     */
    public static final LocalDate DATE_EMPTY = LocalDate.of(9898, 1, 1);

    //@@author LS-Young
    public static int parseMonth(String monthString) throws NumberFormatException {
        if (monthString.equals("all")) {
            return 0;
        }
        return Integer.parseInt(monthString.trim());
    }

    //@@author jyxhazcake

    /**
     * Parses user input into an int representing the month.
     * If month is not compulsory and empty, the current month will be returned instead.
     *
     * @param monthString a string that represents the month
     * @param isCompulsory a boolean on whether the month value is compulsory or not
     * @return an int representing the month
     * @throws ParserException if the month value is invalid
     */
    public static int parseMonth(String monthString, boolean isCompulsory) throws ParserException {
        if (!isCompulsory && monthString.equals("")) {
            return LocalDate.now().getMonthValue();
        }
        try {
            return Integer.parseInt(monthString.trim());
        } catch (NumberFormatException e) {
            throw new ParserException(MESSAGE_INVALID_MONTH);
        }
    }

    /**
     * Parses user input into an int representing the index.
     *
     * @param indexString a string that represents the index
     * @return an int representing the index
     * @throws ParserException if the index value is invalid
     */
    public static int parseIndex(String indexString) throws ParserException {
        try {
            return Integer.parseInt(indexString.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException(MESSAGE_INVALID_INDEX);
        }
    }

    /**
     *Parses user input into an array with the start and end index as its first and second indexes.
     *
     * @param indexString a string representing a range of indexes.
     * @return a String[] with the start index in first position, and end index in second position
     * @throws ParserException if the indexes are not valid
     */
    public static int[] parseMultipleIndexes(String indexString) throws ParserException {
        String[] indexSplit = indexString.trim().split("-", 2);
        String index1 = indexSplit[0].trim();
        String index2 = indexSplit[1].trim();

        int startIndex;
        int endIndex;
        try {
            startIndex = Integer.parseInt(index1);
            endIndex = Integer.parseInt(index2);
        } catch (NumberFormatException e) {
            throw new ParserException(MESSAGE_INVALID_INDEXES);
        }
        return new int[]{startIndex, endIndex};
    }

    /**
     * Parses user input into a string representing the description.
     *
     * @param descString a string that represents the description
     * @param isCompulsory a boolean on whether the description value is compulsory or not
     * @return a String representing the description
     * @throws ParserException if the description is empty and is compulsory
     */
    public static String parseDescription(String descString, boolean isCompulsory) throws ParserException {
        String description = descString.trim();
        if (description.equals("") && isCompulsory) {
            throw new ParserException(MESSAGE_INVALID_DESCRIPTION);
        }
        return description;
    }

    /**
     * Parses user input into a double representing the amount.
     *
     * @param amountString a string that represents the amount
     * @param isCompulsory a boolean on whether the amount value is compulsory or not
     * @return a double representing the amount
     * @throws ParserException if the double amount is invalid
     */
    public static double parseAmount(String amountString, boolean isCompulsory) throws ParserException {
        try {
            return Double.parseDouble(amountString.trim());
        } catch (NumberFormatException e) {
            if (isCompulsory) {
                throw new ParserException(MESSAGE_INVALID_NUMBER_AMOUNT);
            }
            return 0.00;
        }
    }

    /**
     * Parses user input into a LocalDate value.
     * If date is not specified and is not during an edit command, the current date will be returned instead.
     *
     * @param dateString a string that represents the date
     * @param isEdit a boolean on whether an edit command is calling this method
     * @return a LocalDate object
     * @throws ParserException if the date is not in proper YYYY-MM-DD format
     */
    public static LocalDate parseDate(String dateString, boolean isEdit) throws ParserException {
        try {
            if (dateString.equals("") && !isEdit) {
                return LocalDate.now();
            } else if (dateString.equals("")) {
                return DATE_EMPTY;
            }
        } catch (DateTimeParseException e) {
            throw new ParserException(MESSAGE_DATE_INVALID_FORMAT);
        }
        return LocalDate.parse(dateString.trim());
    }

    /**
     * Parses user input into a string representing the name.
     *
     * @param nameString a string that represents the name
     * @param isCompulsory a boolean on whether the name value is compulsory or not
     * @return a string representing the name
     * @throws ParserException if the name is empty when compulsory
     */
    public static String parseName(String nameString, boolean isCompulsory) throws ParserException {
        String name = nameString.trim();
        if (name.equals("") && isCompulsory) {
            throw new ParserException(MESSAGE_INVALID_NAME);
        }
        return name;
    }

    /**
     * Parses user input into a Category.
     *
     * @param categoryString a string that represents the category
     * @param isEdit a boolean on whether an edit command is calling this method
     * @return a Category object
     * @throws ParserException if the category specified does not exist
     */
    public static Category parseCategory(String categoryString, boolean isEdit) throws ParserException {
        if (categoryString.equals("") && !isEdit) {
            return Category.GENERAL;
        } else if (categoryString.equals("")) {
            return Category.INVALID;
        }
        try {
            return Category.valueOf(categoryString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParserException(MESSAGE_INCORRECT_CATEGORY);
        }
    }

    //@@author LS-Young
    public static Category parseListCategory(String categoryString) throws ParserException {
        if (categoryString.equals("")) {
            return Category.ALL;
        }
        try {
            return Category.valueOf(categoryString);
        } catch (IllegalArgumentException e) {
            throw new ParserException("Specified category name does not exist!");
        }
    }
    //@@author jyxhazcake
    /**
     * Parses user input into a Category.
     *
     * @param typeString a string that represents the type
     * @param isCompulsory a boolean on whether the type value is compulsory or not
     * @return an int representing the type
     * @throws ParserException if the type provided is invalid
     */

    public static int parseType(String typeString, boolean isCompulsory) throws ParserException {
        if (isCompulsory) {
            try {
                return Integer.parseInt(typeString.trim());
            } catch (NumberFormatException e) {
                throw new ParserException(MESSAGE_INCORRECT_TYPE);
            }
        } else {
            return 0;
        }
    }
}
