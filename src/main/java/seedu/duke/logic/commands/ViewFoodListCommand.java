package seedu.duke.logic.commands;

/**
 * Represents the command that when executed, lists all the items in the FoodList.
 */
public class ViewFoodListCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Here is a summary of all the food items you have consumed "
            + "in the past week:" + CommandMessages.LS + "%1$s";


    @Override
    public CommandResult execute() {
        if (super.foodItems.getSize() == 0) {
            return new CommandResult(CommandMessages.MESSAGE_EMPTY_FOOD_LIST);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, super.foodItems.convertToString()));
    }
}
