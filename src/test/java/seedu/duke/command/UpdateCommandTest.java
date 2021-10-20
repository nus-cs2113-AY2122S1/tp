package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateCommandTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void updateCommandTest_dukeExceptionThrown() throws DukeException, IOException {
        LocalDate expiryDate1 = LocalDate.parse("21/10/2021", DATE_FORMATTER);
        LocalDate expiryDate2 = LocalDate.parse("01/11/2021", DATE_FORMATTER);
        LocalDate expiryDate3 = LocalDate.parse("02/10/2021", DATE_FORMATTER);
        LocalDate expiryDate4 = LocalDate.parse("04/11/2021", DATE_FORMATTER);

        Ingredient ingredient1 = new Ingredient("Carrot", 300, "grams", expiryDate1);
        Ingredient ingredient2 = new Ingredient("Tomato", 200, "ounces", expiryDate2);
        Ingredient ingredient3 = new Ingredient("Carrot", 1.5, "kilograms", expiryDate3);

        IngredientList.getInstance().add(ingredient1);
        IngredientList.getInstance().add(ingredient2);
        IngredientList.getInstance().add(ingredient3);

        Ingredient updatedIngredient = new Ingredient("Carrot", 450.0, "grams", expiryDate4);
        String resultMsg = new UpdateCommand(updatedIngredient).run();
        String expected = "Got it. This ingredient has been updated:\n" + "\t" + updatedIngredient.toString();

        assertEquals(expected, resultMsg);
    }
}