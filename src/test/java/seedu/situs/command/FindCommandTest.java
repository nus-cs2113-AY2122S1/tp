package seedu.situs.command;

import org.junit.jupiter.api.Test;
import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String EXPECTED_MESSAGE_START = "I found these ingredients for \"xyz\":\n\t";
    private static final String EXPECTED_MESSAGE_END = ". Testxyz | Total Amount: 3.0 kg\n\t\t"
            + "1.1. Amount Left: 3.0 kg | Expiry Date: 21/10/2021";

    @Test
    public void findCommandTest_ingredientNotFound_success() throws SitusException {
        String resultMsg = new FindCommand("nothing").run();
        assertEquals("I could not find any results for \"nothing\"!", resultMsg);
    }

    @Test
    public void findCommandTest_ingredientFound_success() throws SitusException, IOException {
        LocalDate expiryDate1 = LocalDate.parse("21/10/2021", DATE_FORMATTER);
        Ingredient ingredient1 = new Ingredient("testxyz", 3, expiryDate1);
        IngredientList.getInstance().add(ingredient1);
        int expectedIndex = IngredientList.getInstance().findIngredientIndexInList(ingredient1.getName());
        String resultMsg = new FindCommand("xyz").run();
        assertEquals(EXPECTED_MESSAGE_START + (expectedIndex + 1) + EXPECTED_MESSAGE_END, resultMsg);
    }
}
