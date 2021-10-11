package seedu.duke.ingredients;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InsufficientParametersException;

/**
 * Class for methods of adding ingredients
 */
public class AddIngredient {

    /**
     * Reads in all parameters and extracts ingredient name.
     *
     * @param userInput String of all parameters
     * @return Ingredient name
     * @throws InsufficientParametersException gives error when there are missing parameters
     */

    public static String getIngredientName(String userInput) throws InsufficientParametersException {
        int startIndex = userInput.indexOf("n/") + 2;
        int endIndex = userInput.indexOf("a/", startIndex);
        if (startIndex == 1 || endIndex == -1) { //indexOf method returns -1 if not found
            throw new InsufficientParametersException();
        }
        return userInput.substring(startIndex, endIndex).trim();
    }

    /**
     * Reads in all parameters and extracts ingredient amount.
     *
     * @param userInput String of all parameters
     * @return Ingredient amount
     * @throws InsufficientParametersException gives error when there are missing parameters
     */
    public static Double getIngredientAmount(String userInput) throws InsufficientParametersException, DukeException {
        int startIndex = userInput.indexOf("a/") + 2;
        int endIndex = userInput.indexOf("u/", startIndex);
        if (startIndex == 1 || endIndex == -1) { //indexOf method returns -1 if not found
            throw new InsufficientParametersException();
        }

        String amountString = userInput.substring(startIndex, endIndex).trim();
        try {
            return Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            throw new DukeException("Amount is not a number");
        }
    }

    /**
     * Reads in all parameters and extract ingredient units.
     *
     * @param userInput String of all parameters
     * @return Ingredient units
     * @throws InsufficientParametersException gives error when there are missing parameters
     */
    public static String getIngredientUnit(String userInput) throws InsufficientParametersException {
        int startIndex = userInput.indexOf("u/") + 2;
        int endIndex = userInput.indexOf("e/", startIndex);
        if (startIndex == 1 || endIndex == -1) { //indexOf method returns -1 if not found
            throw new InsufficientParametersException();
        }

        return userInput.substring(startIndex, endIndex).trim();
    }

    /**
     * Reads in all parameters and extract ingredient expiry.
     *
     * @param userInput String of all parameters
     * @return Ingredient expiry
     * @throws InsufficientParametersException gives error when there are missing parameters
     */
    public static String getIngredientExpiry(String userInput) throws InsufficientParametersException {
        int startIndex = userInput.indexOf("e/") + 2;
        int endIndex = userInput.length();
        if (startIndex == 1) { //indexOf method returns -1 if not found
            throw new InsufficientParametersException();
        }

        return userInput.substring(startIndex, endIndex);
    }
}
