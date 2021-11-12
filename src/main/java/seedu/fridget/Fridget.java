package seedu.fridget;

import seedu.commands.Command;
import seedu.data.exception.FridgetException;
import seedu.notification.Notification;
import seedu.parser.Parser;
import seedu.storage.ItemList;
import seedu.storage.ShoppingList;
import seedu.storage.Storage;
import seedu.ui.Ui;

import java.time.format.DateTimeParseException;

public class Fridget {
    private static final String FILE_PATH_ITEM = "fridgetData/savedItem.txt";
    private static final String FILE_PATH_LOGS = "fridgetData/savedLogs.txt";
    private static final String FILE_PATH_SHOP = "fridgetData/savedShop.txt";

    private final Ui ui;
    private final Parser parser;
    private final ItemList itemList;
    private final ShoppingList shoppingList;
    private final Notification notification;
    private final Storage storage;

    /**
     * Constructor for Fridget.
     *
     * @param itemFilePath The relative path of the txt file to store data.
     * @param logFilePath The relative path of the txt file to store logs.
     * @param shopFilePath The relative path of the txt file to store shoppingList.
     */
    public Fridget(String itemFilePath, String logFilePath, String shopFilePath) {
        ui = new Ui();
        parser = new Parser();
        itemList = new ItemList();
        shoppingList = new ShoppingList();
        notification = new Notification();
        storage = new Storage(itemList, shoppingList, notification, itemFilePath, logFilePath, shopFilePath);
    }

    public void run() {
        ui.printIntroduction();
        Command command = new Command();
        do {
            try {
                notification.printNotification(itemList.getItemList("r"));
                ui.printUserInputMessage();
                String userInput = ui.readUserInput();
                ui.printSeparatorLine();
                command = parser.parseCommand(userInput);
                command.execute(ui, parser, itemList, shoppingList);
                storage.updateFiles(itemList.getItemList("r"),
                        shoppingList.getShoppingList("r"), notification);
            } catch (FridgetException e) {
                ui.printLine(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.printDateTimeFormat();
            } finally {
                ui.printSeparatorLine();
            }
        } while (command.exitNotRequired());
    }

    /**
     * Main entry-point for the java.fridget.Fridget application.
     *
     * @param args Stores any arguments inputted by the user when running Fridget.
     */
    public static void main(String[] args) {
        new Fridget(FILE_PATH_ITEM, FILE_PATH_LOGS, FILE_PATH_SHOP).run();
    }
}
