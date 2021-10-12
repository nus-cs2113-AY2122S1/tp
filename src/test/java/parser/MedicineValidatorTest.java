package parser;

import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicineValidatorTest {
    private Ui ui = new Ui();

    @Test
    public void checkValidName_validName_expectTrue() {
        String inputString = "panadol";
        boolean isValid = MedicineValidator.isValidName(ui, inputString);
        assertTrue(isValid);
    }

    @Test
    public void checkValidName_validName_expectFalse() {
        String inputString = "";
        boolean isInvalid = MedicineValidator.isValidName(ui, inputString);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidQuantity_validQuantity_expectTrue() {
        String inputString = "0";
        boolean isValid = MedicineValidator.isValidQuantity(ui, inputString);
        assertTrue(isValid);
    }

    @Test
    public void checkValidQuantity_validQuantity_expectFalse() {
        String inputString = "-1";
        boolean isInvalid = MedicineValidator.isValidQuantity(ui, inputString);
        assertFalse(isInvalid);
    }
}
