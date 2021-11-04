package command.stock;

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

class UpdateStockCommandTest {
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


    private void executeUpdateStockCommand(String id, String name) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("n", name);
        Command command = new UpdateStockCommand(parameters);
        command.execute();
    }

    private void executeUpdateStockCommandMaxQty(String id, String maxQty) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("m", maxQty);
        Command command = new UpdateStockCommand(parameters);
        command.execute();
    }

    private void executeUpdateStockCommandQty(String id, String qty) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("q", qty);
        Command command = new UpdateStockCommand(parameters);
        command.execute();
    }

    private void executeUpdateStockCommandQtyAndMaxQty(String id, String qty, String maxQty) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("q", qty);
        parameters.put("m", maxQty);
        Command command = new UpdateStockCommand(parameters);
        command.execute();
    }

    @Test
    public void updateStock_validName_expectValidUpdate() {
        String expectedOutput = "Updated! Number of rows affected: 1\n"
                + "Stock Id changed from:\n"
                + "5 -> 7\n"
                + "+====+======+========+==========+=============+================+==============+\n"
                + "| ID | NAME | PRICE  | QUANTITY | EXPIRY_DATE |  DESCRIPTION   | MAX_QUANTITY | \n"
                + "+====+======+========+==========+=============+================+==============+\n"
                + "| 7  | NEW  | $20.00 |    25    | 15-10-2023  | HYPOTHYROIDISM |     800      | \n"
                + "+----+------+--------+----------+-------------+----------------+--------------+";
        executeUpdateStockCommand("5", "new");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateStock_validFields_expectValidUpdate() {
        String expectedOutput = "Updated! Number of rows affected: 2\n"
                + "Stock Id changed from:\n"
                + "1 -> 7\n"
                + "2 -> 8\n"
                + "+====+========+========+==========+=============+=============+==============+\n"
                + "| ID |  NAME  | PRICE  | QUANTITY | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+========+========+==========+=============+=============+==============+\n"
                + "| 7  | NEWMED | $20.00 |    20    | 13-09-2022  |  HEADACHES  |     1000     | \n"
                + "+----+--------+--------+----------+-------------+-------------+--------------+\n"
                + "| 8  | NEWMED | $20.00 |    10    | 14-09-2022  |  HEADACHES  |     1000     | \n"
                + "+----+--------+--------+----------+-------------+-------------+--------------+";
        executeUpdateStockCommand("1", "newMed");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateStock_invalidQuantity_expectInvalidUpdate() {
        String expectedOutput = "Quantity cannot be more than maximum quantity!\n"
                + "Quantity: 10009, Max Quantity: 1000";
        executeUpdateStockCommandQty("1", "9999");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateStock_validQuantity_expectValidUpdate() {
        String expectedOutput = "Updated! Number of rows affected: 1\n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |      10      | 13-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";
        executeUpdateStockCommandQty("1", "10");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateStock_validMaxQty_expectValidUpdate() {
        String expectedOutput = "Updated! Number of rows affected: 2\n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |      20      | 13-09-2022  |  HEADACHES  |    99999     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+\n"
                + "| 2  | PANADOL | $20.00 |      10      | 14-09-2022  |  HEADACHES  |    99999     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";
        executeUpdateStockCommandMaxQty("1", "99999");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateStock_invalidMaxQty_expectInvalidUpdate() {
        String expectedOutput = "Quantity cannot be more than maximum quantity!\n"
                + "Quantity: 30, Max Quantity: 1";
        executeUpdateStockCommandMaxQty("1", "1");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateStock_InvalidQtyValidMax_expectInvalidUpdate() {
        String expectedOutput = "Quantity cannot be more than maximum quantity!\n"
                + "Quantity: 100009, Max Quantity: 100";
        executeUpdateStockCommandQtyAndMaxQty("1", "99999", "100");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateStock_validQtyAndMax_expectValidUpdate() {
        String expectedOutput = "Updated! Number of rows affected: 2\n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |     999      | 13-09-2022  |  HEADACHES  |     9999     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+\n"
                + "| 2  | PANADOL | $20.00 |      10      | 14-09-2022  |  HEADACHES  |     9999     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";
        executeUpdateStockCommandQtyAndMaxQty("1", "999", "9999");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }



}