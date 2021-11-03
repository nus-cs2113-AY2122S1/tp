package command.prescription;

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

public class AddPrescriptionCommandTest {

    public static final String NAME = "Panadol";
    public static final String QUANTITY = "30";
    public static final String CUSTOMER_ID = "123";
    public static final String STAFF_NAME = "JOHN";
    public static final String DESCRIPTION = "FEVER";
    public static final int MAX_QUANTITY = 1000;
    public static final int PRICE = 10;

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
    void addPrescriptionCommand_validPrescription_expectValid() {
        try {
            medicines.add(new Stock(NAME, PRICE, 50,
                    DateParser.stringToDate("11-11-2025"), DESCRIPTION, MAX_QUANTITY));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", QUANTITY);
        parameters.put("c", CUSTOMER_ID);
        parameters.put("s", STAFF_NAME);

        Command command = new AddPrescriptionCommand(parameters);
        command.execute();

        String error = "Prescribed:Panadol Quantity:30 Expiry Date:11-11-2025\r\n"
                + "+====+=========+==========+=============+============+=======+==========+\r\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | \r\n"
                + "+====+=========+==========+=============+============+=======+==========+\r\n"
                + "| 1  | PANADOL |    30    |     123     | 03-11-2021 | JOHN  |    1     | \r\n"
                + "+----+---------+----------+-------------+------------+-------+----------+";

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void addPrescriptionCommand_validMultiplePrescription_expectValid() {
        try {
            medicines.add(new Stock(NAME, PRICE, 10,
                    DateParser.stringToDate("11-11-2022"), DESCRIPTION, MAX_QUANTITY));
            medicines.add(new Stock(NAME, PRICE, 50,
                    DateParser.stringToDate("01-05-2025"), DESCRIPTION, MAX_QUANTITY));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", QUANTITY);
        parameters.put("c", CUSTOMER_ID);
        parameters.put("s", STAFF_NAME);

        Command command = new AddPrescriptionCommand(parameters);
        command.execute();

        String error = "Prescribed:Panadol Quantity:10 Expiry Date:11-11-2022\r\n"
                + "+====+=========+==========+=============+============+=======+==========+\r\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | \r\n"
                + "+====+=========+==========+=============+============+=======+==========+\r\n"
                + "| 1  | PANADOL |    10    |     123     | 03-11-2021 | JOHN  |    1     | \r\n"
                + "+----+---------+----------+-------------+------------+-------+----------+\r\n"
                + "Prescribed:Panadol Quantity:20 Expiry Date:01-05-2025\r\n"
                + "+====+=========+==========+=============+============+=======+==========+\r\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER ID |    DATE    | STAFF | STOCK ID | \r\n"
                + "+====+=========+==========+=============+============+=======+==========+\r\n"
                + "| 2  | PANADOL |    20    |     123     | 03-11-2021 | JOHN  |    2     | \r\n"
                + "+----+---------+----------+-------------+------------+-------+----------+";

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void addPrescriptionCommand_exceedQuantity_expectInvalid() {
        try {
            medicines.add(new Stock(NAME, PRICE, 5,
                    DateParser.stringToDate("11-11-2025"), DESCRIPTION, MAX_QUANTITY));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", QUANTITY);
        parameters.put("c", CUSTOMER_ID);
        parameters.put("s", STAFF_NAME);

        Command command = new AddPrescriptionCommand(parameters);
        command.execute();

        String error = "Unable to Prescribe! Prescription quantity is more than stock available!\r\n"
                + "Prescription quantity: 30 Stock available: 5";

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void addPrescriptionCommand_invalidExpiryDate_expectInvalid() {
        try {
            medicines.add(new Stock(NAME, PRICE, 5,
                    DateParser.stringToDate("11-11-2020"), DESCRIPTION, MAX_QUANTITY));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("n", NAME);
        parameters.put("q", QUANTITY);
        parameters.put("c", CUSTOMER_ID);
        parameters.put("s", STAFF_NAME);

        Command command = new AddPrescriptionCommand(parameters);
        command.execute();

        String error = "Unable to Prescribe! Medication has expired!";

        assertEquals(error.trim(), outContent.toString().trim());

    }
}
