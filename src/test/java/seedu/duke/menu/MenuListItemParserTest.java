package seedu.duke.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuListItemParserTest {

    @Test
    void deleteMenuItem_emptyMasterList_expectNoChange() {
        MenuList masterList = new MenuList();
        MenuParser menuParser = new MenuParser();
        String[] deleteCommand = "remove-menu|1".trim().split("\\|", 2);
        menuParser.removeMenu(deleteCommand, masterList);
        assertEquals(0, masterList.menuListSize);
    }

    @Test
    void deleteMenuItem_validMasterList_success() {
        MenuList masterList = new MenuList();
        MenuParser menuParser = new MenuParser();
        String[] addCommand = "add-menu|Pizza|5.75".trim().split("\\|", 3);
        String[] deleteCommand = "remove-menu|1".trim().split("\\|", 2);
        menuParser.addMenu(addCommand, masterList);
        assertEquals(1, masterList.menuListSize);
        menuParser.removeMenu(deleteCommand, masterList);
        assertEquals(0, masterList.menuListSize);
    }

}
