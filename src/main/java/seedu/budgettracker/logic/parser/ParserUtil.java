package seedu.budgettracker.logic.parser;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ParserUtil {

    public static int parseMonth(String monthString) throws NumberFormatException {
        if (monthString.equals("all")) {
            return 0;
        }
        return Integer.parseInt(monthString.trim());
    }

    public static int parseMonth(String monthString, boolean isCompulsory) throws ParserException {
        if (!isCompulsory && monthString.equals("")) {
            return LocalDate.now().getMonthValue();
        }
        try {
            return Integer.parseInt(monthString.trim());
        } catch (NumberFormatException e) {
            throw new ParserException("Please ensure your month is a valid integer!");
        }
    }

    public static int parseIndex(String indexString) throws ParserException {
        try {
            return Integer.parseInt(indexString.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ParserException("Please ensure your index is a valid integer!");
        }
    }

    public static int[] parseMultipleIndexes(String indexString) throws ParserException {
        String[] indexSplit = indexString.trim().split("-", 2);
        String index1 = indexSplit[0].trim();
        String index2 = indexSplit[1].trim();

        int startIndex = 0;
        int endIndex = 0;
        try {
            startIndex = Integer.parseInt(index1);
            endIndex = Integer.parseInt(index2);
        } catch (NumberFormatException e) {
            throw new ParserException("Please ensure your indexes are valid integers!");
        }

        int[] indexArray = {startIndex, endIndex};
        return indexArray;
    }

    public static String parseDescription(String descString, boolean isCompulsory) throws ParserException {
        String description = descString.trim();
        if (description.equals("") && isCompulsory) {
            throw new ParserException("Your description is empty!");
        }
        return description;
    }

    public static double parseAmount(String amountString, boolean isCompulsory) throws ParserException {
        try {
            return Double.parseDouble(amountString.trim());
        } catch (NumberFormatException e) {
            if (isCompulsory) {
                throw new ParserException("Please enter a valid number into amount!");
            }
            return 0.00;
        }
    }

    public static LocalDate parseDate(String dateString) throws ParserException {
        try {
            if (!dateString.equals("")) {
                return LocalDate.parse(dateString.trim());
            }
        } catch (DateTimeParseException e) {
            throw new ParserException("Your date is in the wrong format! Correct format: YYYY-MM-DD");
        }
        return LocalDate.now();
    }

    public static String parseName(String nameString, boolean isCompulsory) throws ParserException {
        String name = nameString.trim();
        if (name.equals("") && isCompulsory) {
            throw new ParserException("Your name is empty!");
        }
        return name;
    }

    public static Category parseCategory(String categoryString) throws ParserException {
        if (categoryString.equals("")) {
            return Category.GENERAL;
        }
        try {
            return Category.valueOf(categoryString);
        } catch (IllegalArgumentException e) {
            throw new ParserException("Specified category name does not exist!");
        }
    }

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

    public static int parseType(String typeString, boolean isCompulsory) throws ParserException {
        if (isCompulsory) {
            try {
                return Integer.parseInt(typeString.trim());
            } catch (NumberFormatException e) {
                throw new ParserException("Type specified is incorrect!");
            }
        } else {
            return 0;
        }
    }
}
