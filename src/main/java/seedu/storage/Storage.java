package seedu.storage;

import seedu.data.exception.FridgetException;
import seedu.data.item.Item;
import seedu.notification.Notification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

//@@author BryanElmer
public class Storage {
    private final ItemList itemList;
    private final ShoppingList shoppingList;
    private final Notification notification;
    private final String fileDirectory;
    private final String itemFilePath;
    private final String logFilePath;
    private final String shopFilePath;

    private static final Logger logger = Logger.getLogger("logger");
    private static final String REGEX_DATA_SEPARATOR = " \\| ";

    /**
     * A constructor to save data into text file.
     *
     * @param itemList     The ItemList object.
     * @param shoppingList The ShoppingList object.
     * @param notification The notification object.
     * @param itemFilePath Pathway of item list file storage.
     * @param logFilePath  Pathway of user log file storage.
     * @param shopFilePath Pathway of shopping list file storage.
     */
    public Storage(ItemList itemList, ShoppingList shoppingList, Notification notification,
                   String itemFilePath, String logFilePath, String shopFilePath) {
        String[] fileComponents = itemFilePath.split("/");
        this.fileDirectory = fileComponents[0];
        this.itemList = itemList;
        this.shoppingList = shoppingList;
        this.notification = notification;
        this.itemFilePath = itemFilePath;
        this.logFilePath = logFilePath;
        this.shopFilePath = shopFilePath;

        try {
            loadFile();
        } catch (IOException | FridgetException e) {
            logger.log(Level.WARNING, "in storage, unable to load existing file");
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, "Seems like the files have been tampered with. "
                    + "We've deleted all items that may have been corrupted in savedItem.txt ");
        }
    }

    /**
     * Initialises text files if not present.
     * Loads all the data from the item list text file.
     *
     * @throws IOException The error thrown from file IO operations.
     * @throws FridgetException The error thrown when any item quantity exceeds INT_MAX.
     */
    protected void loadFile() throws IOException, FridgetException {
        File dataDirectory = new File(fileDirectory);
        File itemFile = new File(itemFilePath);
        File logFile = new File(logFilePath);
        File shopFile = new File(shopFilePath);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
            itemFile.createNewFile();
            logFile.createNewFile();
            shopFile.createNewFile();
            return;
        }

        if (!(itemFile.exists() && logFile.exists() && shopFile.exists())) {
            if (!itemFile.exists()) {
                itemFile.createNewFile();
            }

            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            if (!shopFile.exists()) {
                shopFile.createNewFile();
            }
            return;
        }

        readFromItemFile(itemFile);
        readFromLogFile(logFile);
        readFromShopFile(shopFile);
    }

    /**
     * Read saved items list from savedShop.txt to update item list.
     *
     * @param shopFile savedShop.txt used to store all the items from user input.
     * @throws FileNotFoundException thrown when file is not found.
     */
    private void readFromShopFile(File shopFile) throws FileNotFoundException {
        Scanner shopScanner = new Scanner(shopFile);
        if (shopScanner == null) {
            logger.log(Level.WARNING, "Please restart the program! Data storage has been corrupted.");
        }
        while (shopScanner.hasNext()) {
            String line = shopScanner.nextLine();
            String[] shopDataComponents = line.split(REGEX_DATA_SEPARATOR);
            addSavedShopItem(shopDataComponents);
        }
    }

    /**
     * Read saved items list from savedItem.txt to update item list.
     *
     * @param itemFile savedItem.txt used to store all the items from user input.
     * @throws FileNotFoundException thrown when file is not found.
     * @throws FridgetException if any item quantity exceeds INT_MAX.
     */
    private void readFromItemFile(File itemFile) throws FileNotFoundException, FridgetException {
        Scanner itemScanner = new Scanner(itemFile);
        if (itemScanner == null) {
            logger.log(Level.WARNING, "Please restart the program! Data storage has been corrupted.");
        }
        while (itemScanner.hasNext()) {
            String line = itemScanner.nextLine();
            String[] listDataComponents = line.split(REGEX_DATA_SEPARATOR);
            addSavedItem(listDataComponents);
        }
    }

    /**
     * Adds saved item into the item list.
     *
     * @param itemDataComponents The details of the item.
     * @throws FridgetException if the quantity of the item exceeds INT_MAX.
     */
    protected void addSavedItem(String[] itemDataComponents) throws FridgetException {
        int quantity = parseInt(itemDataComponents[1].substring(4).trim());
        LocalDate expiry = LocalDate.parse(itemDataComponents[2].trim());
        Item savedItem = new Item(itemDataComponents[0], expiry, quantity);
        itemList.addItem(savedItem);
    }
    //@@author

    //@@author zonglun99
    /**
     * Adds the log date and time, and notification on/off status.
     *
     * @param savedDateTimeAndStatus String containing date, time and status.
     */
    protected void addSavedNotification(String savedDateTimeAndStatus) {
        String[] splitString = savedDateTimeAndStatus.split(REGEX_DATA_SEPARATOR);
        notification.setDateAndTime(LocalDateTime.parse(splitString[0],
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        Notification.setNotificationStatus(!splitString[1].equals("no"));
    }

    /**
     * Updates the log text file.
     *
     * @param notification Notification object.
     * @throws IOException The error thrown from file IO operations.
     */
    private void updateLogFile(Notification notification) throws IOException {
        FileWriter fileWriter = new FileWriter(logFilePath);
        assert notification != null : "Notification must not be null!";
        fileWriter.write(notification.toString());
        fileWriter.close();
    }

    /**
     * Read saved logs from savedLogs.txt file to update notification status.
     *
     * @param logFile savedLogs.txt file used to store the logging.
     * @throws FileNotFoundException thrown if file is not found.
     */
    private void readFromLogFile(File logFile) throws FileNotFoundException {
        Scanner logScanner = new Scanner(logFile);
        if (logScanner == null) {
            logger.log(Level.WARNING, "Please restart the program! Data storage has been corrupted.");
        }
        if (logScanner.hasNext()) {
            addSavedNotification(logScanner.nextLine());
        }
    }
    //@@author

    //@@author BryanElmer
    /**
     * Adds the saved items in shopping list file into the shopping list.
     *
     * @param shopDataComponents The details of the item.
     */
    protected void addSavedShopItem(String[] shopDataComponents) {
        int quantity = parseInt(shopDataComponents[1].substring(4).trim());
        Item savedItem = new Item(shopDataComponents[0], quantity);
        shoppingList.addItem(savedItem, quantity);
    }

    /**
     * Updates the items list text file.
     *
     * @param items The current list of items.
     * @throws IOException The error thrown from file IO operations.
     */
    private void updateItemFile(ArrayList<Item> items) throws IOException {
        FileWriter fileWriter = new FileWriter(itemFilePath);
        for (Item item : items) {
            assert item != null : "Item must not be null!";
            fileWriter.write(item.saveFormat());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Updated the shop list text file.
     *
     * @param items The current list of items in the shopping list.
     * @throws IOException The error thrown from file IO operations.
     */
    private void updateShopFile(ArrayList<Item> items) throws IOException {
        FileWriter fileWriter = new FileWriter(shopFilePath);
        for (Item item : items) {
            fileWriter.write(item.toShopFormat());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Updates all the text files.
     *
     * @param storedItems   The current list of items.
     * @param shoppingItems The current shopping list of items.
     * @param notification  Notification object.
     */
    public void updateFiles(ArrayList<Item> storedItems, ArrayList<Item> shoppingItems,
                            Notification notification) {
        try {
            updateItemFile(storedItems);
            updateLogFile(notification);
            updateShopFile(shoppingItems);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error while trying to update item list file.");
        }
    }
}
