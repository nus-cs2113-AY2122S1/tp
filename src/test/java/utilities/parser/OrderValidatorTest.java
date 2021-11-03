package utilities.parser;

import inventory.Medicine;
import inventory.Order;
import org.junit.jupiter.api.Test;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderValidatorTest {
    private Ui ui = new Ui();
    OrderValidator orderValidator = new OrderValidator();

    @Test
    public void checkValidOrderId_validId_expectTrue() {
        ArrayList<Medicine> tempOrder = new ArrayList<>();
        try {
            Order.setOrderCount(0);
            tempOrder.add(new Order("SIMVASTATIN", 20, DateParser.stringToDate("11-10-2021")));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
        boolean isValid = orderValidator.isValidOrderId(ui, "1", tempOrder);
        assertTrue(isValid);
    }

    @Test
    public void checkValidStockId_validId_expectFalse() {
        ArrayList<Medicine> tempOrder = new ArrayList<>();
        try {
            tempOrder.add(new Order("SIMVASTATIN", 20, DateParser.stringToDate("11-10-2021")));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
        boolean isInvalid = orderValidator.isValidOrderId(ui, "5", tempOrder);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidExpiry_validDate_expectTrue() {
        String inputDate = "08-10-2021";
        boolean isValid = orderValidator.isValidDate(ui, inputDate);
        assertTrue(isValid);
    }

    @Test
    public void checkValidExpiry_validDate_expectFalse() {
        String inputDate = "8 Oct 2021";
        boolean isInvalid = orderValidator.isValidDate(ui, inputDate);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidColumn_validColumn_expectTrue() {
        String inputColumnName = "NAME";
        boolean isValid = orderValidator.isValidColumn(ui, inputColumnName);
        assertTrue(isValid);
    }

    @Test
    public void checkValidColumn_validColumn_expectFalse() {
        String inputColumnName = "panadol";
        boolean isInvalid = orderValidator.isValidColumn(ui, inputColumnName);
        assertFalse(isInvalid);
    }

    @Test
    public void checkValidStatus_validStatus_expectTrue() {
        String inputColumnName = "PENDING";
        boolean isValid = orderValidator.isValidStatus(ui, inputColumnName);
        assertTrue(isValid);
    }

    @Test
    public void checkValidStatus_validColumn_expectFalse() {
        String inputColumnName = "panadol";
        boolean isInvalid = orderValidator.isValidStatus(ui, inputColumnName);
        assertFalse(isInvalid);
    }

}
