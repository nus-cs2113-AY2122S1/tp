package seedu.storage;

import org.junit.jupiter.api.Test;
import seedu.data.exception.FridgetException;
import seedu.notification.Notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author BryanElmer
public class StorageTest {
    private static final String FILE_PATH_LIST = "fridgetTestData/testSavedList.txt";
    private static final String FILE_PATH_LOGS = "fridgetTestData/testSavedLogs.txt";
    private static final String FILE_PATH_SHOP = "fridgetTestData/testSavedShop.txt";
    private static final String REGEX_DATA_SEPARATOR = " \\| ";

    ItemList itemList = new ItemList();
    ShoppingList shoppingList = new ShoppingList();
    Notification notification = new Notification();
    Storage storage = new Storage(itemList, shoppingList, notification, FILE_PATH_LIST,
            FILE_PATH_LOGS, FILE_PATH_SHOP);

    @Test
    void addSavedItem_correctSavedItemFormat_expectCorrectFormatInItemList() throws FridgetException {
        String savedItem = "chicken | Qty: 1 | 2021-10-15";
        String[] dataComponents = savedItem.split(REGEX_DATA_SEPARATOR);
        storage.addSavedItem(dataComponents);
        try {
            String actualSaveFormat = itemList.getItemList("default").get(0).saveFormat();
            assertEquals(actualSaveFormat, savedItem);
        } catch (FridgetException e) {
            fail();
        }
    }

    @Test
    void addSavedNotification_correctSavedLogFormat_expectNotificationOn() {
        String savedNotification = "2021-10-11 14:38 | yes";
        storage.addSavedNotification(savedNotification);

        assertTrue(Notification.isNotificationOn());
    }

    //@@ author zonglun99
    @Test
    void addSavedShopItem_correctSavedShopItemInput_expectCorrectFormatInShopList() {
        String savedItem = "bacon | Qty: 1";
        String[] dataComponents = savedItem.split(REGEX_DATA_SEPARATOR);
        storage.addSavedShopItem(dataComponents);
        try {
            String actualSaveFormat = shoppingList.getShoppingList("default").get(0).toShopFormat();
            assertEquals(actualSaveFormat, savedItem);
        } catch (FridgetException e) {
            fail();
        }
    }
}
