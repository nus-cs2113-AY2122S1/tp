package seedu.commands;

import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.ui.Ui;

//@@author zonglun99
public class HelpCommand extends Command {
    private static final String COMMAND_LIST = "List of commands available:\n\n"
            + "add ITEM_NAME /EXPIRY_DATE(format: yyyy-mm-dd) [eg. add bacon /2022-11-11]\n"
            + "-> Adds an item and its expiry date to the item list. Input quantity after being prompt.\n\n"
            + "find KEYWORD [eg. find bacon]\n"
            + "-> Find and print all items associated with keyword.\n\n"
            + "remove ITEM_NAME [eg. delete bacon]\n"
            + "-> Removes the item from the item list.\n\n"
            + "list -e\n"
            + "-> List out all the items starting with earliest expiry date.\n\n"
            + "list -r\n"
            + "-> List out all the items based on the order added.\n\n"
            + "list\n"
            + "-> List out all the items in alphabetical order.\n\n"
            + "shoplist\n"
            + "-> List out all the items in the shopping list in alphabetical order.\n\n"
            + "expiring\n"
            + "-> Prints out items that have expired or are expiring within a week.\n\n"
            + "update ITEMS_NAME [eg. update egg]\n"
            + "-> Prompts a quantity change for the specified item name.\n\n"
            + "notifs\n"
            + "-> Toggle the notification on or off depending on previous state. Default mode is on.\n\n"
            + "reset\n"
            + "-> Deletes all the previous items entries from the reader.\n\n"
            + "shopreset\n"
            + "-> Deletes all the previous items entries in the shopping list.\n\n"
            + "exit\n"
            + "-> Exits the program.\n\n"
            + "For more information about each command please visit our User Guide.\n"
            + "Link: https://ay2122s1-cs2113t-w12-4.github.io/tp/UserGuide.html";

    /**
     * Constructor for HelpCommand.
     */
    public HelpCommand() {
    }

    /**
     * Executes the "help" command.
     *
     * @param ui           The ui object to interact with user.
     * @param parser       The parser object to parse user inputs.
     * @param itemList     The itemList object.
     * @param shoppingList The shoppingList object.
     */
    @Override
    public void execute(Ui ui, Parser parser, ItemList itemList, ShoppingList shoppingList) {
        ui.printLine(COMMAND_LIST);
    }
}
