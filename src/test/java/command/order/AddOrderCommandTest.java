package command.order;

import command.Command;
import command.stock.ListStockCommand;
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


//@@author jiangweichen835
public class AddOrderCommandTest {
    public static final String NAME = "Panadol";
    public static final String QUANTITY = "50";
    public static final String DATE = "10-10-2020";

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

    private void executeAddOrderCommand(String quantity, String date) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", quantity);
        parameters.put("d", date);

        Command command = new AddOrderCommand(parameters);
        command.execute();
    }

    @Test
    public void addOrderCommand_validAddOrder_expectValid() {

        executeAddOrderCommand(QUANTITY, DATE);

        String expectedOutput = "Order added: Panadol\n"
               + "+====+=========+==========+============+=========+\n"
               + "| ID |  NAME   | QUANTITY |    DATE    | STATUS  | \n"
               + "+====+=========+==========+============+=========+\n"
               + "| 1  | PANADOL |    50    | 10-10-2020 | PENDING | \n"
               + "+----+---------+----------+------------+---------+\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void addOrderCommand_invalidDate_expectInvalid() {
        executeAddOrderCommand(QUANTITY, "10 Oct 2020");

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
                    DateParser.stringToDate("10-10-2020"), "For Fever", 100));
            medicines.add(new Order("PANADOL", 50, DateParser.stringToDate("9-10-2022")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        executeAddOrderCommand("1000", DATE);

        String expectedOutput = "Unable to add order as total order quantity exceeds maximum stock quantity of 100.\n"
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
            medicines.add(new Order("PANADOL", 50, DateParser.stringToDate("9-10-2020")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        executeAddOrderCommand("1000", DATE);

        String expectedOutput = "Order added: Panadol\n"
                + "+====+=========+==========+============+=========+\n"
                + "| ID |  NAME   | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+=========+==========+============+=========+\n"
                + "| 2  | PANADOL |   1000   | 10-10-2020 | PENDING | \n"
                + "+----+---------+----------+------------+---------+\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }

    /**
     * If medicine exist in Stock but not in Order.
     */
    @Test
    public void addOrderCommand_InvalidQuantity_expectInvalid() {
        try {
            medicines.add(new Stock("PANADOL", 10, 20,
                    DateParser.stringToDate("10-10-2020"), "Fever", 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        executeAddOrderCommand("1000", DATE);

        String expectedOutput = "Unable to add order as total order quantity exceeds "
                + "maximum stock quantity of 1000.\nExisting quantity in stock: 20";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void addOrderCommand_invalidQuantity_expectInvalid() {
        executeAddOrderCommand("0", DATE);

        String expectedOutput = "Order Quantity cannot be 0.";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));
    }
}
