package seedu.duke.dish;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuItemParserTest {

    @Test
    void deleteMenuItem_emptyMasterList_expectNoChange() {
        Menu masterList = new Menu();
        DishParser dishParser = new DishParser();
        String[] deleteCommand = "remove-menu|1".trim().split("\\|", 2);
        dishParser.removeDish(deleteCommand, masterList);
        assertEquals(0, masterList.menu.size());
    }

    @Test
    void deleteMenuItem_validMasterList_success() {
        Menu masterList = new Menu();
        DishParser menuParser = new DishParser();
        String[] addCommand = "add-menu|Pizza|5.75".trim().split("\\|", 3);
        String[] deleteCommand = "remove-menu|1".trim().split("\\|", 2);
        menuParser.addDish(addCommand, masterList);
        assertEquals(1, masterList.menu.size());
        menuParser.removeDish(deleteCommand, masterList);
        assertEquals(0, masterList.menu.size());
    }

}
