package utilities.parser;

import org.junit.jupiter.api.Test;
import utilities.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicineValidatorTest {
    private Ui ui = new Ui();
    StockValidator stockValidator = new StockValidator();

    @Test
    public void checkValidName_validName_expectTrue() {
        String inputString = "panadol";
        boolean isValid = stockValidator.isValidName(ui, inputString);
        assertTrue(isValid);
    }

    @Test
    public void checkValidName_validName_expectFalse() {
        String inputString = "";
        boolean isInvalid = stockValidator.isValidName(ui, inputString);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidQuantity_validQuantity_expectTrue() {
        String inputString = "0";
        boolean isValid = stockValidator.isValidQuantity(ui, inputString);
        assertTrue(isValid);
    }

    @Test
    public void checkValidQuantity_validQuantity_expectFalse() {
        String inputString = "-1";
        boolean isInvalid = stockValidator.isValidQuantity(ui, inputString);
        assertFalse(isInvalid);
    }
}
