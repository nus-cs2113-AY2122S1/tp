package seedu.ui;

import org.junit.jupiter.api.Test;
import seedu.data.exception.FridgetException;
import seedu.data.item.Item;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class UiTest {
    Ui ui = new Ui();

    //@@author uosjapuelks
    @Test
    void sortTypeMessage_emptySortTypeInput_expectReturnDefault() {
        String sortTypeString = "";
        assertEquals("List sorted by item name:", ui.sortTypeMessage(sortTypeString));
    }

    @Test
    void sortTypeMessage_rSortTypeInput_expectByLeastRecentMessage() {
        String sortTypeString = "r";
        assertEquals("List sorted by earliest added:", ui.sortTypeMessage(sortTypeString));
    }

    @Test
    void sortTypeMessage_eSortTypeInput_expectByExpiryMessage() {
        String sortTypeString = "e";
        assertEquals("List sorted by expiry date:", ui.sortTypeMessage(sortTypeString));
    }

    //@@author zonglun99
    @Test
    void matchItem_itemNotInMatchingListInput_exceptionThrown() {
        ArrayList<Item> matchingItems = new ArrayList<>();
        String targetItem = "chicken";
        assertThrows(FridgetException.class, () -> {
            ui.matchItem(matchingItems, targetItem, Ui.CommandType.UPDATE);
        });
    }

    @Test
    void matchItem_oneItemInMatchingListInput_expectReturnItem() throws FridgetException {
        ArrayList<Item> matchingItems = new ArrayList<>();
        LocalDate expiryDate = LocalDate.parse("2022-12-12");
        Item itemToMatch = new Item("bacon", expiryDate, 1);
        matchingItems.add(itemToMatch);
        String targetItem = "bacon";
        assertEquals(itemToMatch, ui.matchItem(matchingItems, targetItem, Ui.CommandType.UPDATE));
    }


    @Test
    void getResetQuestion_resetCommandType_expectResetConfirmationMessage() {
        assertEquals("Are you sure you want to reset everything in the fridge? (Y/N)",
                ui.getResetQuestion(Ui.CommandType.RESET));
    }

    @Test
    void getResetQuestion_shopResetCommandType_expectShopResetConfirmationMessage() {
        assertEquals("Are you sure you want to reset everything in the shopping list? (Y/N)",
                ui.getResetQuestion(Ui.CommandType.SHOPRESET));
    }

    @Test
    void getResetQuestion_updateCommandType_expectEmptyStringMessage() {
        assertEquals("",
                ui.getResetQuestion(Ui.CommandType.UPDATE));
    }

    @Test
    void getQuantityToBeRemoved_singleQuantityInput_expectIntegerValueOne() {
        Item itemToAdd = new Item("bacon", LocalDate.parse("2022-12-12"), 1);
        try {
            int expectedQuantity = ui.getQuantityToBeRemoved(itemToAdd);
            assertEquals(expectedQuantity, 1);
        } catch (FridgetException e) {
            fail();
        }
    }
}
