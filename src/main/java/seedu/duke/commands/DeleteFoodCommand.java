package seedu.duke.commands;

import seedu.duke.food.Food;
import seedu.duke.ui.Ui;

/**
 * Represents the command that when executed, deletes a Food item from the FoodList.
 */
public class DeleteFoodCommand extends Command {
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD_DELETE
            + " " + COMMAND_PREFIX_FOOD + COMMAND_PREFIX_DELIMITER + "X" + Ui.QUOTATION
            + ", where X is the item number in the food list";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid format! "
            + "Trying to delete a food item?"
            + Ui.INDENTED_LS + "Use this format:" + Ui.LS + MESSAGE_COMMAND_FORMAT;
    public static final String MESSAGE_SUCCESS = "A food item has been deleted:"
            + Ui.INDENTED_LS + "%1$s"
            + Ui.INDENTED_LS + "Number of food item(s) left: %2$d";

    private final int itemIndex;

    public DeleteFoodCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    @Override
    public CommandResult execute() {
        if (super.foodItems.getSize() == 0) {
            return new CommandResult(MESSAGE_EMPTY_FOOD_LIST);
        }
        try {
            Food deletedFood;
            deletedFood = super.foodItems.deleteFood(this.itemIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, deletedFood, super.foodItems.getSize()));
        } catch (IndexOutOfBoundsException e) {
            if (super.foodItems.getSize() == 1) {
                return new CommandResult(MESSAGE_ONLY_ONE_IN_LIST);
            }
            return new CommandResult(String.format(MESSAGE_LIST_OUT_OF_BOUNDS, super.foodItems.getSize()));
        }
    }
}
