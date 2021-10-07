package seedu.duke.ingredients;

/**
 * Class for methods of adding ingredients
 */
public class AddIngredient {

    /**
     * Reads in all parameters and extracts ingredient name
     *
     * @param userInput String of all parameters
     * @return Ingredient name
     */
    public static String getIngredientName(String userInput){
        int startIndex = userInput.indexOf("n/") + 2;
        int endIndex = userInput.indexOf(" ", startIndex);

        return userInput.substring(startIndex, endIndex);
    }

    /**
     * Reads in all parameters and extracts ingredient amount
     * @param userInput String of all parameters
     * @return Ingredient amount
     */
    public static Double getIngredientAmount(String userInput) {
        int startIndex = userInput.indexOf("a/") + 2;
        int endIndex = userInput.indexOf(" ", startIndex);
        String amountString = userInput.substring(startIndex, endIndex);

        return Double.parseDouble(amountString);
    }

    /**
     * Reads in all parameters and extract ingredient units
     *
     * @param userInput String of all parameters
     * @return Ingredient units
     */
    public static String getIngredientUnit(String userInput) {
        int startIndex = userInput.indexOf("u/") + 2;
        int endIndex = userInput.indexOf(" ", startIndex);

        return userInput.substring(startIndex, endIndex);
    }

    /**
     * Reads in all parameters and extract ingredient expiry
     * @param userInput String of all parameters
     * @return Ingredient expiry
     */
    public static String getIngredientExpiry(String userInput) {
        int startIndex = userInput.indexOf("e/") + 2;
        int endIndex = userInput.length();

        return userInput.substring(startIndex, endIndex);
    }
}
