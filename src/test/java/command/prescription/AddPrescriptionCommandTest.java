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
import java.util.Date;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author deonchung

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
            Stock stock = new Stock(NAME, PRICE, 50,
                    DateParser.stringToDate("11-11-2025"), DESCRIPTION, MAX_QUANTITY);
            medicines.add(stock);

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

            parameters.put("n", NAME);
            parameters.put("q", QUANTITY);
            parameters.put("c", CUSTOMER_ID);
            parameters.put("s", STAFF_NAME);

            final int prescriptionIndex = 1;
            Command command = new AddPrescriptionCommand(parameters);
            command.execute();
            Prescription prescription = (Prescription) medicines.get(prescriptionIndex);

            assertEquals(prescription.getPrescriptionId(), 1);
            assertEquals(prescription.getStockId(), 1);
            assertEquals(prescription.getQuantity(), 30);
            assertEquals(prescription.getStaff(), "JOHN");
            assertEquals(prescription.getCustomerId(), "123");
            Date today = new Date();
            assertEquals(DateParser.dateToString(prescription.getDate()), DateParser.dateToString(today));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addPrescriptionCommand_exceedQuantity_expectInvalid() {
        try {
            Stock stock = new Stock(NAME, PRICE, 5,
                    DateParser.stringToDate("11-11-2025"), DESCRIPTION, MAX_QUANTITY);
            medicines.add(stock);
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

        String expectedOutput = "Unable to Prescribe! Prescription quantity is more than stock available!\n"
                + "Prescription quantity: 30 Stock available: 5";

        // Output stream will include \r for each line break
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }

    @Test
    void addPrescriptionCommand_invalidExpiryDate_expectInvalid() {
        try {
            Stock stock = new Stock(NAME, PRICE, 5,
                    DateParser.stringToDate("11-11-2020"), DESCRIPTION, MAX_QUANTITY);
            medicines.add(stock);
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

        String expectedOutput = "Unable to Prescribe! Medication has expired!";

        // Output stream will include \r for each line break
        assertEquals(expectedOutput.trim(), outContent.toString().trim().replace("\r", ""));

    }
}
