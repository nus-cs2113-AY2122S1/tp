package parser;

import inventory.Medicine;
import inventory.Order;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderValidatorTest {
    private Ui ui = new Ui();

    @Test
    public void checkValidOrderId_validId_expectTrue() {
        ArrayList<Medicine> tempOrder = new ArrayList<>();
        try {
            Order.setOrderCount(0);
            tempOrder.add(new Order("SIMVASTATIN", 20, DateParser.stringToDate("11-10-2021")));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
        boolean isValid = OrderValidator.isValidOrderId(ui, "1", tempOrder);
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
        boolean isInvalid = OrderValidator.isValidOrderId(ui, "5", tempOrder);
        assertFalse(isInvalid);
    }
}
