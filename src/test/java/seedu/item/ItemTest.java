package seedu.item;

import org.junit.jupiter.api.Test;
import seedu.data.item.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author uosjapuelks
class ItemTest {

    public static final String DUMMY_NAME = "dummyItem";
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    public static final LocalDate FAR_FUTURE = LocalDate.now().plusYears(5);
    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

    @Test
    void isNearExpiry_expiringDummyItem_expectTrue() {
        Item expiringDummy = new Item(DUMMY_NAME, TOMORROW);
        assertTrue(expiringDummy.isNearExpiry());
    }

    @Test
    void isNearExpiry_notExpiringDummyItem_expectFalse() {
        Item expiringDummy = new Item(DUMMY_NAME, FAR_FUTURE);
        assertFalse(expiringDummy.isNearExpiry());
    }

    @Test
    void isPastExpiry_expiringDummyItem_expectFalse() {
        Item expiringDummy = new Item(DUMMY_NAME, TOMORROW);
        assertFalse(expiringDummy.isPastExpiry());
    }

    @Test
    void isPastExpiry_expiredDummyItem_expectTrue() {
        Item expiringDummy = new Item(DUMMY_NAME, YESTERDAY);
        assertTrue(expiringDummy.isPastExpiry());
    }

    @Test
    void getColoredExpiryDate_ExpiringItem_expectOrangeFormattedExpiry() {
        Item expiringDummy = new Item(DUMMY_NAME, TOMORROW);
        String date = TOMORROW.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        // String expected = String.format("\u001B[33m%s\u001B[0m", date);
        String expected = date;
        assertEquals(expected, expiringDummy.expiryToString());
    }

    @Test
    void toString_expiredDummyItem_expectCorrectStringFormat() {
        LocalDate expiredDate = LocalDate.parse("1212-12-12");
        Item expiredDummy = new Item(DUMMY_NAME, expiredDate);
        // assertEquals("dummyItem | Qty: 1 | \u001B[31m12 Dec 1212\u001B[0m", expiredDummy.toString());
        assertEquals("dummyItem | Qty: 1 | 12 Dec 1212", expiredDummy.toString());
    }

    @Test
    void saveFormat_dummyItem_expectCorrectSaveFormat() {
        Item dummy = new Item(DUMMY_NAME, YESTERDAY);
        String correctOutput = "dummyItem | Qty: 1 | " + YESTERDAY;
        assertEquals(correctOutput, dummy.saveFormat());
    }

    @Test
    void itemNameComparator_itemAB_expectAscendingTrue() {
        Item a = new Item("A", TOMORROW);
        Item b = new Item("B", YESTERDAY);
        int expected = "A".compareTo("B");
        int actual = Item.ItemNameComparator.compare(a, b);
        assertEquals(expected, actual);
    }

    @Test
    void itemExpiryComparator_compareFromAToB_expectDescendingTrue() {
        Item a = new Item("A", TOMORROW);
        Item b = new Item("B", YESTERDAY);
        int expected = TOMORROW.compareTo(YESTERDAY);
        int actual = Item.ItemExpiryComparator.compare(a, b);
        assertEquals(expected, actual);
    }

    //@@author zonglun99
    @Test
    void isExpired_expiredItem_expectTrue() {
        LocalDate expiredDate = LocalDate.parse("2020-12-12");
        Item expiredItem = new Item(DUMMY_NAME, expiredDate);
        assertTrue(expiredItem.isExpired());
    }

    @Test
    void isExpired_notExpiredItem_expectFalse() {
        LocalDate expiryDate = LocalDate.parse("2110-12-12");
        Item testItem = new Item(DUMMY_NAME, expiryDate);
        assertFalse(testItem.isExpired());
    }
}
