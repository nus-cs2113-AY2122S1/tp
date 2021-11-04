package command.stock;

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

//@@author deonchung

public class AddStockCommandTest {

    public static final String NAME = "Panadol";
    public static final String EXPIRY_DATE = "12-12-2025";
    public static final String DESCRIPTION = "Fever";
    public static final String PRICE = "5";
    public static final String QUANTITY = "50";
    public static final String MAX_QUANTITY = "100";

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
    void addStockCommand_invalidExpiryDateFormat_expectInvalid() {
        executeAddStockCommand("20/12/2021", MAX_QUANTITY);

        String expectedOutput = "Invalid expiry date! Ensure date is in dd-MM-yyyy.";

        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }

    @Test
    void addStockCommand_exceedMaxQuantity_expectInvalid() {
        executeAddStockCommand(EXPIRY_DATE, "5");

        String expectedOutput = "Quantity cannot be more than maximum quantity!\n"
                + "Quantity: 50, Max Quantity: 5";

        // Output stream will include \r for each line break
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }

    @Test
    void addStockCommand_expiredMedication_expectInvalid() {
        executeAddStockCommand("12-12-2020", MAX_QUANTITY);

        String expectedOutput = "Unable to add medicine. Medicine has expired.";

        // Output stream will include \r for each line break
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }

    @Test
    void addStockCommand_validAddStock_expectValid() {
        executeAddStockCommand(EXPIRY_DATE, MAX_QUANTITY);

        String expectedOutput = "Medication added: Panadol\n"
                + "+====+=========+=======+==========+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+=======+==========+=============+=============+==============+\n"
                + "| 1  | PANADOL | $5.00 |    50    | 12-12-2025  |    FEVER    |     100      | \n"
                + "+----+---------+-------+----------+-------------+-------------+--------------+";

        // Output stream will include \r for each line break
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }

    @Test
    void addStockCommand_sameStockName_expectValid() {
        try {
            Stock stock = new Stock("PANADOL", 10, 50,
                    DateParser.stringToDate("11-11-2025"), "For Fever", 1000);
            medicines.add(stock);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        executeAddStockCommand(EXPIRY_DATE, MAX_QUANTITY);

        String expectedOutput = "Medicine exists. Using existing description and maximum quantity.\n"
                + "Medication added: Panadol\n"
                + "+====+=========+=======+==========+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+=======+==========+=============+=============+==============+\n"
                + "| 2  | PANADOL | $5.00 |    50    | 12-12-2025  |  FOR FEVER  |     1000     | \n"
                + "+----+---------+-------+----------+-------------+-------------+--------------+";
        
        // Output stream will include \r for each line break
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }

    @Test
    void addStockCommand_sameStockNameAndExpiry_expectValid() {
        try {
            Stock stock = new Stock("PANADOL", 10, 50,
                    DateParser.stringToDate("12-12-2025"), "For Fever", 1000);
            medicines.add(stock);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        executeAddStockCommand(EXPIRY_DATE, MAX_QUANTITY);

        String expectedOutput = "Same Medication and Expiry Date exist. Using existing price, "
                + "description and maximum quantity"
                + ". Updating existing quantity.\n"
                + "+====+=========+========+==========+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==========+=============+=============+==============+\n"
                + "| 1  | PANADOL | $10.00 |   100    | 12-12-2025  |  FOR FEVER  |     1000     | \n"
                + "+----+---------+--------+----------+-------------+-------------+--------------+";

        // Output stream will include \r for each line break
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }

    private void executeAddStockCommand(String expiryDate, String maxQuantity) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("p", PRICE);
        parameters.put("q", QUANTITY);
        parameters.put("e", expiryDate);
        parameters.put("d", DESCRIPTION);
        parameters.put("m", maxQuantity);

        Command command = new AddStockCommand(parameters);
        command.execute();

    }

}
