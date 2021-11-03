package command.order;

import command.Command;
import inventory.Medicine;
import inventory.Order;
import inventory.Prescription;
import inventory.Stock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.parser.DateParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteOrderCommandTest {

    public static final String ID = "1";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    ArrayList<Medicine> medicines = Medicine.getInstance();

    @BeforeEach
    void setup() {
        medicines.clear();
        Stock.setStockCount(0);
        Order.setOrderCount(0);
        Prescription.setPrescriptionCount(0);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void deleteOrderCommand_validDeleteOrder_expectInvalid() {
        try {
            medicines.add(new Order("PANADOL", 10, DateParser.stringToDate("12-12-2025")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("i",ID);

        Command command = new DeleteOrderCommand(parameters);
        command.execute();

        String error = "Order deleted for Order ID 1";

        assertEquals(error.trim(), outContent.toString().trim());
    }

    @Test
    void deleteOrderCommand_invalidId_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("i",ID);

        Command command = new DeleteOrderCommand(parameters);
        command.execute();

        String error = "Invalid order id provided!";

        assertEquals(error.trim(), outContent.toString().trim());
    }

}

