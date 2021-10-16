//@@author jerrelllzw
package seedu.duke.dish;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DishParserTest {

    @Test
    void addDish_validMenu_success() {
        Menu menu = new Menu();
        DishParser dishParser = new DishParser();
        String[] addCommand = "add-dish|Pizza|5.75".trim().split("\\|");
        dishParser.addDish(addCommand, menu);
        assertEquals(1, menu.menu.size());
    }

    @Test
    void deleteDish_emptyMenu_expectNoChange() {
        Menu menu = new Menu();
        DishParser dishParser = new DishParser();
        String[] deleteCommand = "remove-dish|1".trim().split("\\|");
        dishParser.removeDish(deleteCommand, menu);
        assertEquals(0, menu.menu.size());
    }

    @Test
    void deleteDish_validMenu_success() {
        Menu menu = new Menu();
        DishParser dishParser = new DishParser();
        String[] addCommand = "add-dish|Pizza|5.75".trim().split("\\|");
        String[] deleteCommand = "remove-dish|1".trim().split("\\|");
        dishParser.addDish(addCommand, menu);
        assertEquals(1, menu.menu.size());
        dishParser.removeDish(deleteCommand, menu);
        assertEquals(0, menu.menu.size());
    }

    @Test
    void editDish_validMenu_success() {
        Menu menu = new Menu();
        DishParser dishParser = new DishParser();
        String[] addCommand = "add-dish|Pizza|5.75".trim().split("\\|");
        String[] editCommand = "edit-dish|1|2".trim().split("\\|");
        dishParser.addDish(addCommand, menu);
        assertEquals(1, menu.menu.size());
        dishParser.editDish(editCommand, menu);
        assertEquals(2, menu.menu.get(0).getPrice());
    }

    @Test
    void discountDish_validMenu_success() {
        Menu menu = new Menu();
        DishParser dishParser = new DishParser();
        String[] addCommand = "add-dish|Pizza|6".trim().split("\\|");
        String[] discountCommand = "discount-dish|1|50".trim().split("\\|");
        dishParser.addDish(addCommand, menu);
        assertEquals(1, menu.menu.size());
        dishParser.discountDish(discountCommand, menu);
        assertEquals(3, menu.menu.get(0).getDiscountedPrice());
    }

}