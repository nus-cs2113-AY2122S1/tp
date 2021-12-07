package seedu.duke.commands.food;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ClickException;
import seedu.duke.exceptions.food.NegativeCaloriesException;
import seedu.duke.exceptions.food.NoCalorieCountKeywordException;
import seedu.duke.exceptions.food.NoCaloriesInputException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author ngnigel99
/**
 * Finds food items from reference list with target amount of calories.
 */
public class FindFoodByCalorieCount extends Command {
    public int calories = 0;
    public String keyword = "clt";

    public FindFoodByCalorieCount() {
        syntax = "food clt [Calories]"; //calories lower than
    }

    public FindFoodByCalorieCount(String input) throws NoCalorieCountKeywordException,
            NumberFormatException, NoCaloriesInputException, NegativeCaloriesException {
        getCalories(input);
    }

    /**
     * Extracts calories from a given string.
     * @param input full user input.
     * @throws NoCalorieCountKeywordException if keyword not found.
     * @throws NoCaloriesInputException if no calories are input.
     * @throws NegativeCaloriesException if calories are negative.
     */
    private void getCalories(String input) throws
            NoCalorieCountKeywordException,
            NoCaloriesInputException,
            NegativeCaloriesException {
        if (!input.contains(keyword)) {
            throw new NoCalorieCountKeywordException();
        }
        if (input.equals("food " + keyword)) {
            throw new NoCaloriesInputException();
        }
        int calorieIndex = input.indexOf(keyword);
        input = input.substring(calorieIndex + 4);
        calories = Integer.parseInt(input);
        if (calories < 0) {
            throw new NegativeCaloriesException();
        }
    }

    @Override
    public void execute(Ui ui, Storage storage) throws ClickException, Exception {
        Storage.reference.getTechnoEdge().printFoodRecordsWithLowerCalories(calories);
    }
}
