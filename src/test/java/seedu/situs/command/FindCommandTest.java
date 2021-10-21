package seedu.situs.command;

import org.junit.jupiter.api.Test;
import seedu.situs.exceptions.DukeException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void findCommandTest_ingredientNotFound_success() throws DukeException, IOException {
        LocalDate expiryDate1 = LocalDate.parse("21/10/2021", DATE_FORMATTER);
        Ingredient ingredient1 = new Ingredient("Carrot", 300, "grams", expiryDate1);
        IngredientList.getInstance().add(ingredient1);
        String resultMsg = new FindCommand("xyz").run();
        assertEquals("I could not find any results for \"xyz\"!", resultMsg);
    }
}
