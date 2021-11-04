package command.order;

import command.Command;
import inventory.Medicine;
import inventory.Order;
import inventory.Prescription;
import inventory.Stock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import utilities.parser.DateParser;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddOrderCommandTest {
    public static final String NAME = "Panadol";
    public static final String QUANTITY = "50";
    public static final String DATE = "10-10-2022";

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
    public void addOrderCommand_validAddOrder_expectValid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", QUANTITY);
        parameters.put("d", DATE);

        Command command = new AddOrderCommand(parameters);
        command.execute();

        String expectedOutput = "Order added: Panadol\n"
               + "+====+=========+==========+============+=========+\n"
               + "| ID |  NAME   | QUANTITY |    DATE    | STATUS  | \n"
               + "+====+=========+==========+============+=========+\n"
               + "| 1  | PANADOL |    50    | 10-10-2022 | PENDING | \n"
               + "+----+---------+----------+------------+---------+\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void addOrderCommand_invalidDate_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", QUANTITY);
        parameters.put("d", "10 Oct 2022");

        Command command = new AddOrderCommand(parameters);
        command.execute();

        String expectedOutput = "Invalid date! Ensure date is in dd-MM-yyyy.";
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }

    /**
     * If medicine name exists in Order and in Stock.
     */
    @Test
    public void addOrderCommand_exceedMaxQuantity_expectInvalid() {
        try {
            medicines.add(new Stock("PANADOL", 10, 50,
                    DateParser.stringToDate("10-10-2022"), "For Fever", 100));
            medicines.add(new Order("PANADOL", 50, DateParser.stringToDate("9-10-2022")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", "1000");
        parameters.put("d", DATE);

        Command command = new AddOrderCommand(parameters);
        command.execute();

        String expectedOutput = "Order for Panadol exists.\n"
                + "Unable to add order as total order quantity exceeds maximum stock quantity of 100.\n"
                + "Existing quantity in stock: 50\n" + "Pending order quantity: 50";
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }

    /**
     * If medicine name exists in Order but not in Stock.
     * There is no max quantity to limit user input quantity.
     */
    @Test
    public void addOrderCommand_validQuantity_expectValid() {
        try {
            medicines.add(new Order("PANADOL", 50, DateParser.stringToDate("9-10-2022")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", "1000");
        parameters.put("d", DATE);

        Command command = new AddOrderCommand(parameters);
        command.execute();

        String expectedOutput = "Order added: Panadol\n"
                + "+====+=========+==========+============+=========+\n"
                + "| ID |  NAME   | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+=========+==========+============+=========+\n"
                + "| 2  | PANADOL |   1000   | 10-10-2022 | PENDING | \n"
                + "+----+---------+----------+------------+---------+\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void addOrderCommand_invalidQuantity_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", "0");
        parameters.put("d", DATE);

        Command command = new AddOrderCommand(parameters);
        command.execute();

        String expectedOutput = "Order Quantity cannot be 0.";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }
}
