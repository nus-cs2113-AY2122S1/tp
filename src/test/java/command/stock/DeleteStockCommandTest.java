package command.stock;

import command.Command;
import inventory.Medicine;
import inventory.Order;
import inventory.Prescription;
import inventory.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.parser.DateParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteStockCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    ArrayList<Medicine> medicines = Medicine.getInstance();

    @BeforeEach
    void setup() {
        medicines.clear();
        Stock.setStockCount(0);
        Prescription.setPrescriptionCount(0);
        Order.setOrderCount(0);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void deleteStockCommand_invalidStock_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "0");
        Command command = new DeleteStockCommand(parameters);
        command.execute();
        assertEquals("Invalid stock id provided!", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_validStock_expectValid() {
        try {
            medicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "1");
        Command command = new DeleteStockCommand(parameters);
        command.execute();
        assertEquals("Deleted row with Stock Id: 1", outContent.toString().trim());
    }
}
