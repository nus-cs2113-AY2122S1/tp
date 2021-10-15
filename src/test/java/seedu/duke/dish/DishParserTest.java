package seedu.duke.dish;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishParserTest {

    @Test
    void deleteDish_emptyMenu_expectNoChange() {
        Menu menu = new Menu();
        DishParser dishParser = new DishParser();
        String[] deleteCommand = "remove-dish|1".trim().split("\\|", 2);
        dishParser.removeDish(deleteCommand, menu);
        assertEquals(0, menu.menu.size());
    }

    @Test
    void deleteDish_validMenu_success() {
        Menu menu = new Menu();
        DishParser dishParser = new DishParser();
        String[] addCommand = "add-dish|Pizza|5.75".trim().split("\\|", 3);
        String[] deleteCommand = "remove-dish|1".trim().split("\\|", 2);
        dishParser.addDish(addCommand, menu);
        assertEquals(1, menu.menu.size());
        dishParser.removeDish(deleteCommand, menu);
        assertEquals(0, menu.menu.size());
    }

}