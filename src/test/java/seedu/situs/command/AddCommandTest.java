
package seedu.situs.command;


import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /*@Test
    public void addCommandTest_sampleIngredient() throws DukeException {
        LocalDate expiryDate = LocalDate.parse("10/08/2021", DATE_FORMATTER);
        Ingredient sampleIngredient = new Ingredient("onion", 15.0, "kg", expiryDate);
        String resultMsg = new AddCommand(sampleIngredient).run();
        String expected = "Got it. This ingredient has been added to the inventory:\n"
                + "\t" + sampleIngredient.toString() + '\n'
                + "Current inventory has " + IngredientList.getInstance().getInventoryStock()
                + " items.\n"
                + "This ingredient will expire in " + ChronoUnit.DAYS.between(CurrentDate.getCurrentDate(), expiryDate)
                + " days.";

        assertEquals(expected, resultMsg);
    }*/

}
