package seedu.duke.command;

import seedu.duke.model.Item;
import seedu.duke.model.Shelf;
import seedu.duke.command.exception.IllegalArgumentException;
import seedu.duke.command.exception.ItemNotExistException;
import seedu.duke.command.exception.NoPropertyFoundException;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The command that edits a selected item.
 */
public class EditCommand extends Command {
    private final String itemName;
    private final String selectedProperty;
    private final String newValue;
    private final Shelf shelf;
    private final String[] properties = {"cost", "price"};
    private static final String UPDATE_COMPLETE_MESSAGE = "This item has been updated.";
    //to be added to UI part later
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    /**
     * EditCommand constructor.
     * @param itemName the name of the selected item
     * @param property the property to be changed
     * @param newValue the new value of the property
     */
    public EditCommand(String itemName, String property, String newValue, Shelf shelf) {
        this.itemName = itemName;
        this.selectedProperty = property;
        this.newValue = newValue;
        this.shelf = shelf;
    }

    /**
     * Executes the update operation.
     *
     * @throws ItemNotExistException    when cannot find any item with the name
     * @throws NullPointerException     when the name specified is null
     * @throws IllegalArgumentException when the argument is invalid
     */
    public void execute() throws ItemNotExistException,
            NullPointerException, IllegalArgumentException, NoPropertyFoundException {
        boolean isProperty = Arrays.asList(properties).contains(selectedProperty);
        if (!isProperty) {
            logger.log(Level.WARNING, "EditCommand can't find item property.");
            throw new NoPropertyFoundException(selectedProperty);
        }
        try {
            int sizeBeforeEditing = shelf.getSize();
            if (selectedProperty.equals("cost")) {
                Item selectedItem = shelf.getItem(itemName);
                selectedItem.setPurchaseCost(newValue);

            } else {
                assert selectedProperty.equals("price") :
                        "All properties should have been listed";
                Item selectedItem = shelf.getItem(itemName);
                selectedItem.setSellingPrice(newValue);
            }
            int sizeAfterEditing = shelf.getSize();
            assert sizeBeforeEditing == sizeAfterEditing :
                    "After editing an item the list size should remain unchanged";
            System.out.println(UPDATE_COMPLETE_MESSAGE);
            logger.log(Level.INFO, "EditCommand successfully executed.");
        } catch (seedu.duke.model.exception.ItemNotExistException e) {
            logger.log(Level.WARNING, String.format("EditCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new ItemNotExistException(e.getMessage());
        } catch (seedu.duke.model.exception.IllegalArgumentException e) {
            logger.log(Level.WARNING, String.format("EditCommand failed to execute with error message %s",
                    e.getMessage()));
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
