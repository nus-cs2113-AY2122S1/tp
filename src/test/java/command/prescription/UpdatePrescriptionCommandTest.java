package command.prescription;

import command.Command;
import command.Data;
import inventory.Medicine;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author a-tph

class UpdatePrescriptionCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        Data.generateTestData();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @AfterAll
    public static void clearData() {
        Medicine.getInstance().clear();
    }

    private void executeUpdatePrescriptionCommandNameAndQty(String id, String name, String qty) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("n", name);
        parameters.put("q", qty);
        Command command = new UpdatePrescriptionCommand(parameters);
        command.execute();
    }

    private void executeUpdatePrescriptionCommandName(String id, String name) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("n", name);
        Command command = new UpdatePrescriptionCommand(parameters);
        command.execute();
    }

    private void executeUpdatePrescriptionCommandQty(String id, String qty) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("q", qty);
        Command command = new UpdatePrescriptionCommand(parameters);
        command.execute();
    }

    private void executeUpdatePrescriptionCommand(String id, String customerId, String staff, String date) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("c", customerId);
        parameters.put("s", staff);
        parameters.put("d", date);
        Command command = new UpdatePrescriptionCommand(parameters);
        command.execute();
    }

    @Test
    public void updatePrescription_ValidNameAndQty_expectValidUpdate() {
        String expectedOutput = "Restored 10 PANADOL\n"
                + "Updated prescription information!\n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 6  | AZITHROMYCIN |    5     |  S1234567A  | 09-10-2021 | JANE  |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeUpdatePrescriptionCommandNameAndQty("1", "azithromycin", "5");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updatePrescription_InvalidNameValidQty_expectInvalidUpdate() {
        String expectedOutput = "Action aborted! Either medication not found or stock has expired.";
        executeUpdatePrescriptionCommandNameAndQty("1", "notFound", "10000");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updatePrescription_ValidNameInvalidQty_expectInvalidUpdate() {
        String expectedOutput = "Quantity cannot be more than maximum quantity!\n"
                + "Quantity: 9999, Max Quantity: 35\n"
                + "Prescription of medication aborted!";
        executeUpdatePrescriptionCommandNameAndQty("1", "azithromycin", "9999");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updatePrescription_validName_expectValidUpdate() {
        String expectedOutput = "Restored 5 AZITHROMYCIN\n"
                + "Updated prescription information!\n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| 6  | PANADOL |    5     |  S2345678B  | 13-10-2021 | PETER |    1     | \n"
                + "+----+---------+----------+-------------+------------+-------+----------+";
        executeUpdatePrescriptionCommandName("5", "panadol");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updatePrescription_invalidName_expectInvalidUpdate() {
        String expectedOutput = "Action aborted! Either medication not found or stock has expired.";
        executeUpdatePrescriptionCommandName("5", "notFound");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updatePrescription_validQty_expectValidUpdate() {
        String expectedOutput = "Restored 10 VICODIN\n"
                + "Updated prescription information!\n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| 6  | VICODIN |    5     |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+---------+----------+-------------+------------+-------+----------+";
        executeUpdatePrescriptionCommandQty("2", "5");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updatePrescription_invalidQty_expectInvalidUpdate() {
        String expectedOutput = "Quantity cannot be more than maximum quantity!\n"
                + "Quantity: 9984, Max Quantity: 20\n"
                + "Prescription of medication aborted!";
        executeUpdatePrescriptionCommandQty("2", "9999");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updatePrescription_validOtherFields_expectValidUpdate() {
        String expectedOutput = "Updated prescription information!\n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| 2  | VICODIN |    15    |    321B     | 01-01-2021 | ALICE |    3     | \n"
                + "+----+---------+----------+-------------+------------+-------+----------+";
        executeUpdatePrescriptionCommand("2", "321B", "Alice", "01-01-2021");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
