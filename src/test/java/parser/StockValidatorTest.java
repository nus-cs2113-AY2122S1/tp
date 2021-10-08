package parser;

import ui.Ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class StockValidatorTest {
    private Ui ui;

    @Test
    public void checkValidName_validName_expectTrue() {
        String inputString = "panadol";
        Boolean isValid = StockValidator.isValidName(ui, inputString);
        assertTrue(isValid);
    }
}
