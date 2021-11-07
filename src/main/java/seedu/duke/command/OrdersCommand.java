package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.exceptions.LotsException;

public class OrdersCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Checks input for characters after list command.
     *
     * @param input Input entered by the user.
     * @throws LotsException If characters exist in the input after the list command.
     */
    public OrdersCommand(String input) throws LotsException {
        String[] splitInput = input.split(" ");
        if (splitInput.length > 1) {
            throw new LotsException("Please make sure there is no inputs after the list command!");
        }
    }

    /**
     * Prints all the current orders.
     */
    @Override
    public void execute() throws LotsException {
        Ui.printOrdersList(super.peopleManager);
    }
}
