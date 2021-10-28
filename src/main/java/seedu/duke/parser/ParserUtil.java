package seedu.duke.parser;

import seedu.duke.data.records.Category;
import seedu.duke.exception.EmptyDescriptionException;

import java.time.LocalDate;

public class ParserUtil {

    public static int parseMonth(String monthString) throws NumberFormatException {
        if (monthString.equals("all")) {
            return 0;
        }
        return Integer.parseInt(monthString.trim());
    }

    public static int parseMonth(String monthString, boolean isCompulsory) throws NumberFormatException {
        if (!isCompulsory && monthString.equals("")) {
            return LocalDate.now().getMonthValue();
        }
        return Integer.parseInt(monthString.trim());
    }

    public static int parseIndex(String indexString) throws NumberFormatException {
        return Integer.parseInt(indexString.trim()) - 1;
    }

    public static int[] parseMultipleIndexes(String indexString) throws NumberFormatException {
        String[] indexSplit = indexString.trim().split("-", 2);
        String index1 = indexSplit[0].trim();
        String index2 = indexSplit[1].trim();
        int startIndex = Integer.parseInt(index1);
        int endIndex = Integer.parseInt(index2);

        int[] indexArray = {startIndex, endIndex};
        return indexArray;
    }

    public static String parseDescription(String descString, boolean isCompulsory) throws EmptyDescriptionException {
        String description = descString.trim();
        if (description.equals("") && isCompulsory) {
            throw new EmptyDescriptionException();
        }
        return description;
    }

    public static double parseAmount(String amountString, boolean isCompulsory) {
        try {
            return Double.parseDouble(amountString.trim());
        } catch (NumberFormatException e) {
            if (isCompulsory) {
                throw new NumberFormatException();
            }
            return 0.00;
        }
    }

    public static LocalDate parseDate(String dateString) {
        if (!dateString.equals("")) {
            return LocalDate.parse(dateString.trim());
        }
        return LocalDate.now();
    }

    public static String parseName(String nameString, boolean isCompulsory) throws EmptyDescriptionException {
        String name = nameString.trim();
        if (name.equals("") && isCompulsory) {
            throw new EmptyDescriptionException();
        }
        return name;
    }

    public static Category parseCategory(String categoryString) {
        if (categoryString.equals("")) {
            return Category.GENERAL;
        }
        return Category.valueOf(categoryString);
    }

    public static Category parseListCategory(String categoryString) {
        if (categoryString.equals("")) {
            return Category.ALL;
        }
        return Category.valueOf(categoryString);
    }

    public static int parseType(String typeString, boolean isCompulsory) {
        if (isCompulsory) {
            return Integer.parseInt(typeString.trim());
        } else {
            return 0;
        }
    }
}
