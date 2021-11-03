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
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("p", PRICE);
        parameters.put("q", QUANTITY);
        parameters.put("e", "20/12/2021");
        parameters.put("d", DESCRIPTION);
        parameters.put("m", MAX_QUANTITY);

        Command command = new AddStockCommand(parameters);
        command.execute();

        String error = "Invalid expiry date! Ensure date is in dd-MM-yyyy.";

        assertEquals(error.trim(), outContent.toString().trim());
    }

    @Test
    void addStockCommand_exceedMaxQuantity_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("p", PRICE);
        parameters.put("q", QUANTITY);
        parameters.put("e", EXPIRY_DATE);
        parameters.put("d", DESCRIPTION);
        parameters.put("m", "5");

        Command command = new AddStockCommand(parameters);
        command.execute();

        String error = "Quantity cannot be more than maximum quantity!\r\nQuantity: 50, Max Quantity: 5";

        assertEquals(error.trim(), outContent.toString().trim());
    }

    @Test
    void addStockCommand_expiredMedication_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("p", PRICE);
        parameters.put("q", QUANTITY);
        parameters.put("e", "12-12-2020");
        parameters.put("d", DESCRIPTION);
        parameters.put("m", MAX_QUANTITY);

        Command command = new AddStockCommand(parameters);
        command.execute();

        String error = "Unable to add medicine. Medicine is expired.";

        assertEquals(error.trim(), outContent.toString().trim());
    }

    @Test
    void addStockCommand_validAddStock_expectValid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("p", PRICE);
        parameters.put("q", QUANTITY);
        parameters.put("e", EXPIRY_DATE);
        parameters.put("d", DESCRIPTION);
        parameters.put("m", MAX_QUANTITY);

        Command command = new AddStockCommand(parameters);
        command.execute();

        String error = "Medication added: Panadol\r\n"
                + "+====+=========+=======+==========+=============+=============+==============+\r\n"
                + "| ID |  NAME   | PRICE | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \r\n"
                + "+====+=========+=======+==========+=============+=============+==============+\r\n"
                + "| 1  | PANADOL | $5.00 |    50    | 12-12-2025  |    FEVER    |     100      | \r\n"
                + "+----+---------+-------+----------+-------------+-------------+--------------+";

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void addStockCommand_sameStockName_expectValid() {
        try {
            medicines.add(new Stock("PANADOL", 10, 50,
                    DateParser.stringToDate("11-11-2025"), "For Fever", 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("p", PRICE);
        parameters.put("q", QUANTITY);
        parameters.put("e", EXPIRY_DATE);
        parameters.put("d", DESCRIPTION);
        parameters.put("m", MAX_QUANTITY);

        Command command = new AddStockCommand(parameters);
        command.execute();

        String error = "Medicine exists. Using existing description and maximum quantity.\r\n"
                + "Medication added: Panadol\r\n"
                + "+====+=========+=======+==========+=============+=============+==============+\r\n"
                + "| ID |  NAME   | PRICE | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \r\n"
                + "+====+=========+=======+==========+=============+=============+==============+\r\n"
                + "| 2  | PANADOL | $5.00 |    50    | 12-12-2025  |  FOR FEVER  |     1000     | \r\n"
                + "+----+---------+-------+----------+-------------+-------------+--------------+";

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void addStockCommand_sameStockNameAndExpiry_expectValid() {
        try {
            medicines.add(new Stock("PANADOL", 10, 50,
                    DateParser.stringToDate("12-12-2025"), "For Fever", 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("p", PRICE);
        parameters.put("q", QUANTITY);
        parameters.put("e", EXPIRY_DATE);
        parameters.put("d", DESCRIPTION);
        parameters.put("m", MAX_QUANTITY);

        Command command = new AddStockCommand(parameters);
        command.execute();

        String error = "Same Medication and Expiry Date exist. Updating existing quantity.\r\n"
                + "+====+=========+========+==========+=============+=============+==============+\r\n"
                + "| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \r\n"
                + "+====+=========+========+==========+=============+=============+==============+\r\n"
                + "| 1  | PANADOL | $10.00 |   100    | 12-12-2025  |  FOR FEVER  |     1000     | \r\n"
                + "+----+---------+--------+----------+-------------+-------------+--------------+";

        assertEquals(error.trim(), outContent.toString().trim());

    }

}
