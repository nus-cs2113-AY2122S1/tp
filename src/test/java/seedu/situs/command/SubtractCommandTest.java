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
class SubtractCommandTest {
    private static final String UPDATE_MESSAGE = "Got it. This ingredient has been updated:\n" + "\t";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void subtractCommandTest_SitusExceptionThrown_success() throws SitusException, IOException {
        LocalDate expiryDate1 = LocalDate.parse("21/11/2022", DATE_FORMATTER);

        Ingredient ingredient1 = new Ingredient("Pineapple", 500, expiryDate1);

        IngredientList.getInstance().add(ingredient1);

        Double subtractAmount = 7.5;
        String resultMsg = new SubtractCommand(ingredient1.getName(), subtractAmount).run();
        String expected = "Got it. " + subtractAmount + " kg has been subtracted from " + ingredient1.getName();
        assertEquals(expected, resultMsg);
    }
}