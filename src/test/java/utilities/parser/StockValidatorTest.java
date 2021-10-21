package utilities.parser;

import inventory.Medicine;
import inventory.Stock;
import org.junit.jupiter.api.Test;
import utilities.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StockValidatorTest {
    private Ui ui = new Ui();
    StockValidator stockValidator = new StockValidator();

    @Test
    public void checkValidStockId_validId_expectTrue() {
        ArrayList<Medicine> tempMedicines = new ArrayList<>();
        try {
            Stock.setStockCount(0);
            tempMedicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
        boolean isValid = stockValidator.isValidStockId(ui, "1", tempMedicines);
        assertTrue(isValid);
    }

    @Test
    public void checkValidStockId_validId_expectFalse() {
        ArrayList<Medicine> tempMedicines = new ArrayList<>();
        try {
            tempMedicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
        boolean isInvalid = stockValidator.isValidStockId(ui, "5", tempMedicines);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidPrice_validPrice_expectTrue() {
        String inputPrice = "20";
        boolean isValid = stockValidator.isValidPrice(ui, inputPrice);
        assertTrue(isValid);
    }

    @Test
    public void checkValidPrice_validPrice_expectFalse() {
        String inputPrice = "-1";
        boolean isInvalid = stockValidator.isValidPrice(ui, inputPrice);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidExpiry_validDate_expectTrue() {
        String inputDate = "08-10-2021";
        boolean isValid = stockValidator.isValidExpiry(ui, inputDate);
        assertTrue(isValid);
    }

    @Test
    public void checkValidExpiry_validDate_expectFalse() {
        String inputDate = "8 Oct 2021";
        boolean isInvalid = stockValidator.isValidExpiry(ui, inputDate);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidDescription_validDescription_expectTrue() {
        String inputDescription = "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS";
        boolean isValid = stockValidator.isValidDescription(ui, inputDescription);
        assertTrue(isValid);
    }

    @Test
    public void checkValidDescription_validDescription_expectFalse() {
        String inputDescription = "";
        boolean isInvalid = stockValidator.isValidDescription(ui, inputDescription);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidMax_validQuantity_expectTrue() {
        String inputMaxQuantity = "100";
        boolean isValid = stockValidator.isValidMaxQuantity(ui, inputMaxQuantity);
        assertTrue(isValid);
    }

    @Test
    public void checkValidMax_validQuantity_expectFalse() {
        String inputMaxQuantity = "";
        boolean isInvalid = stockValidator.isValidMaxQuantity(ui, inputMaxQuantity);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidColumn_validColumn_expectTrue() {
        String inputColumnName = "NAME";
        boolean isValid = stockValidator.isValidColumn(ui, inputColumnName);
        assertTrue(isValid);
    }

    @Test
    public void checkValidColumn_validColumn_expectFalse() {
        String inputColumnName = "panadol";
        boolean isInvalid = stockValidator.isValidColumn(ui, inputColumnName);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidity_checkQuantity_expectTrue() {
        int quantity = 10;
        int maxQuantity = 100;
        boolean isValid = stockValidator.quantityValidityChecker(ui, quantity, maxQuantity);
        assertTrue(isValid);
    }

    @Test
    public void checkValidity_checkQuantity_expectFalse() {
        int quantity = 10;
        int maxQuantity = 1;
        boolean isInvalid = stockValidator.quantityValidityChecker(ui, quantity, maxQuantity);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidity_checkDate_expectTrue() {
        ArrayList<Medicine> tempMedicines = new ArrayList<>();
        try {
            tempMedicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));

            final SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            final Date expiryDate = DateFor.parse("10-10-2021");
            String inputName = "panadol";

            boolean isValid = stockValidator.dateValidityChecker(ui, tempMedicines, expiryDate, inputName);
            assertTrue(isValid);
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
    }

    @Test
    public void checkValidity_checkDate_expectFalse() {
        ArrayList<Medicine> tempMedicines = new ArrayList<>();
        try {
            tempMedicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));

            final SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            final Date expiryDate = DateFor.parse("13-9-2021");
            String inputName = "panadol";

            boolean isInvalid = stockValidator.dateValidityChecker(ui, tempMedicines, expiryDate, inputName);
            assertFalse(isInvalid);
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
    }
}
