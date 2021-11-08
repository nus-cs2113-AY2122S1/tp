
package seedu.situs.command;


import org.junit.jupiter.api.Test;
import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.Ingredient;
import seedu.situs.ingredients.IngredientList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author ngoivanessa
public class AddCommandTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void addCommandTest_sampleIngredient_success() throws SitusException {
        LocalDate expiryDate = LocalDate.parse("10/08/2022", DATE_FORMATTER);
        Ingredient sampleIngredient = new Ingredient("onion", 15.0, expiryDate);
        String resultMsg = new AddCommand(sampleIngredient).run();
        String expectedMsg = "Got it. This ingredient has been added to the inventory:\n\t"
                + sampleIngredient.getName() + " | " + sampleIngredient.toString() + '\n'
                + "Current inventory has " + IngredientList.getInstance().getSize()
                + " items." + '\n' + "This ingredient will expire in "
                + Ingredient.daysFromCurrentDate(sampleIngredient.getExpiry()) + " days.";

        assertEquals(expectedMsg, resultMsg);
    }

}
