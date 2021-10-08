package parser;

import inventory.Medicine;
import inventory.Stock;
import ui.Ui;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StockValidatorTest {
    private Ui ui;

    @Test
    public void checkValidName_validName_expectTrue() {
        String inputString = "panadol";
        Boolean isValid = StockValidator.isValidName(ui, inputString);
        assertTrue(isValid);
    }

    @Test
    public void checkValidStockId_validId_expectTrue() {
        ArrayList<Medicine> tempMedicines = new ArrayList<>();
        try {
            tempMedicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
        Boolean isValid = StockValidator.isValidStockId(ui,"1", tempMedicines);
        assertTrue(isValid);
    }
    
}
