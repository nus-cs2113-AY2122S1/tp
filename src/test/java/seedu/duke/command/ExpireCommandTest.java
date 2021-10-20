package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpireCommandTest {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void expireCommandTest_fiveInputs_expectThreeResults() throws DukeException {
        LocalDate expiryDate1 = LocalDate.parse("25/10/2000", DATE_FORMATTER);
        LocalDate expiryDate2 = LocalDate.parse("26/10/2000", DATE_FORMATTER);
        LocalDate expiryDate3 = LocalDate.parse("27/10/2000", DATE_FORMATTER);
        LocalDate expiryDate4 = LocalDate.parse("04/11/2000", DATE_FORMATTER);
        LocalDate expiryDate5 = LocalDate.parse("05/11/2000", DATE_FORMATTER);

        Ingredient ingredient1 = new Ingredient("apples", 15.0, "kg", expiryDate1);
        Ingredient ingredient2 = new Ingredient("onions", 10, "kg", expiryDate2);
        Ingredient ingredient3 = new Ingredient("beef stock", 5, "kg", expiryDate3);
        Ingredient ingredient4 = new Ingredient("pizza", 0.2, "kg", expiryDate4);
        Ingredient ingredient5 = new Ingredient("chicken", 5.2, "kg", expiryDate5);

        new AddCommand(ingredient1).run();
        new AddCommand(ingredient2).run();
        new AddCommand(ingredient3).run();
        new AddCommand(ingredient4).run();
        new AddCommand(ingredient5).run();

        String resultMsg = new ExpireCommand(LocalDate.parse("27/10/2000", DATE_FORMATTER)).run();
        String expected = "There are 3 ingredients expiring by: 2000-10-27\n"
                + "\tApples | Amount Left: 15.0 kg | Expiry Date: 25/10/2000\n"
                + "\tOnions | Amount Left: 10.0 kg | Expiry Date: 26/10/2000\n"
                + "\tBeef stock | Amount Left: 5.0 kg | Expiry Date: 27/10/2000"
                + "\n\t";

        assertEquals(expected, resultMsg);
    }
}
