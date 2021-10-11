package parser;

import org.junit.jupiter.api.Test;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DispenseValidatorTest {
    private Ui ui = new Ui();

    @Test
    public void checkValidCustomerId_validCustomerId_expectTrue() {
        String inputString = "123";
        boolean isValid = DispenseValidator.isValidCustomerId(ui, inputString);
        assertTrue(isValid);
    }

    @Test
    public void checkValidCustomerId_emptyInput_expectFalse() {
        String inputString = "";
        boolean isValid = DispenseValidator.isValidCustomerId(ui, inputString);
        assertFalse(isValid);
    }

    @Test
    public void checkValidStaffName_validStaffName_expectTrue() {
        String inputString = "Mary";
        boolean isValid = DispenseValidator.isValidCustomerId(ui, inputString);
        assertTrue(isValid);
    }

    @Test
    public void checkValidStaffName_emptyInput_expectFalse() {
        String inputString = "";
        boolean isValid = DispenseValidator.isValidCustomerId(ui, inputString);
        assertFalse(isValid);
    }

}
