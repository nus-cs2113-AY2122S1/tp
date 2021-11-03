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

public class DeletePrescriptionCommandTest {
    public static final String ID = "1";
    public static final String MEDICATION_NAME = "PANADOL";
    public static final int PRICE = 10;
    public static final String CUSTOMER_ID = "123";
    public static final String PRESCRIPTION_DATE = "12-12-2025";
    public static final String STAFF_NAME = "JOHN";
    public static final String DESCRIPTION = "For Fever";
    public static final int MAX_QUANTITY = 50;
    public static final int STOCK_ID = 1;
    public static final int QUANTITY = 45;
    public static final int PRESCRIPTION_QUANTITY = 10;
    public static final int STOCK_QUANTITY = 45;

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
    void deletePrescriptionCommand_validDeletePrescription_expectValid() {
        try {
            medicines.add(new Prescription(MEDICATION_NAME, PRICE, CUSTOMER_ID,
                    DateParser.stringToDate(PRESCRIPTION_DATE), STAFF_NAME, STOCK_ID));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("i",ID);

        Command command = new DeletePrescriptionCommand(parameters);
        command.execute();

        String error = "Prescription deleted for Prescription Id 1";

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void deletePrescriptionCommand_invalidPrescriptionId_expectInvalid() {
        String error = "Invalid prescription id provided!";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("i",ID);

        Command command = new DeletePrescriptionCommand(parameters);
        command.execute();

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void deletePrescriptionCommand_invalidPrescriptionQuantity_expectInvalid() {
        try {
            medicines.add(new Stock(MEDICATION_NAME, PRICE, STOCK_QUANTITY,
                    DateParser.stringToDate("11-11-2025"), DESCRIPTION, MAX_QUANTITY));
            medicines.add(new Prescription(MEDICATION_NAME, 10, CUSTOMER_ID,
                    DateParser.stringToDate(PRESCRIPTION_DATE), STAFF_NAME, STOCK_ID));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("i",ID);

        Command command = new DeletePrescriptionCommand(parameters);
        command.execute();

        String error = "Unable to delete prescription. Quantity added will exceed maximum quantity.\r\n"
                + "Maximum quantity: 50 Total Quantity after deletion: 55";

        assertEquals(error.trim(), outContent.toString().trim());

    }

    @Test
    void deletePrescriptionCommand_expiredMedication_expectInvalid() {
        try {
            medicines.add(new Stock(MEDICATION_NAME, PRICE, QUANTITY,
                    DateParser.stringToDate("12-12-2020"), DESCRIPTION, MAX_QUANTITY));
            medicines.add(new Prescription(MEDICATION_NAME, PRESCRIPTION_QUANTITY, CUSTOMER_ID,
                    DateParser.stringToDate("12-12-2021"), STAFF_NAME, STOCK_ID));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();

        parameters.put("i",ID);

        Command command = new DeletePrescriptionCommand(parameters);
        command.execute();

        String error = "Medication has expired. Unable to delete prescription.";

        assertEquals(error.trim(), outContent.toString().trim());

    }

}
