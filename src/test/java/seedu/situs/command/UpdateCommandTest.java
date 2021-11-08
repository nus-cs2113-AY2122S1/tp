package seedu.situs.command;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author AayushMathur7
class UpdateCommandTest {
    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void updateCommandTest_SitusExceptionThrown_success() throws SitusException, IOException {
        LocalDate expiryDate1 = LocalDate.parse("11/11/2021", DATE_FORMATTER);
        LocalDate expiryDate2 = LocalDate.parse("01/12/2021", DATE_FORMATTER);
        LocalDate expiryDate3 = LocalDate.parse("02/01/2022", DATE_FORMATTER);

        Ingredient ingredient1 = new Ingredient("Avocado", 300, expiryDate1);
        Ingredient ingredient2 = new Ingredient("Avocado", 200, expiryDate2);
        Ingredient ingredient3 = new Ingredient("Avocado", 1.5, expiryDate3);

        IngredientList.getInstance().add(ingredient1);
        IngredientList.getInstance().add(ingredient2);
        IngredientList.getInstance().add(ingredient3);

        Ingredient updatedIngredient = new Ingredient("Avocado", 450.0, expiryDate1);
        String resultMsg = new UpdateCommand(1, 1, 450.0).run();
        String expected = UPDATE_MESSAGE + updatedIngredient.getName() + " | Amount Left: 450.000 kg | Expiry Date: 11/11/2021";
        assertEquals(expected, resultMsg);
    }
}