package command.order;

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

class UpdateOrderCommandTest {
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
        Data.clearTestData();
    }

    private void executeUpdateOrderCommand(String id, String date, String qty) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", id);
        parameters.put("d", date);
        parameters.put("q", qty);
        Command command = new UpdateOrderCommand(parameters);
        command.execute();
    }

    @Test
    public void updateOrder_validFields_expectValidUpdate() {
        String expectedOutput = "Updated! Number of rows affected: 1\n"
                + "+====+=============+==========+============+=========+\n"
                + "| ID |    NAME     | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+=============+==========+============+=========+\n"
                + "| 4  | SIMVASTATIN |    20    | 01-01-2021 | PENDING | \n"
                + "+----+-------------+----------+------------+---------+";
        executeUpdateOrderCommand("4", "01-01-2021", "20");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void updateOrder_validFieldsAndInvalidDate_expectInvalidUpdate() {
        String expectedOutput = "Invalid date! Input date cannot be after today's date.";
        executeUpdateOrderCommand("4", "01-01-2099", "20");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
    
    @Test
    public void updateOrder_validFieldsAndInvalidQuantity_expectInvalidUpdate() {
        String expectedOutput = "Quantity cannot be more than maximum quantity!\n"
                + "Quantity: 99999, Max Quantity: 800";
        executeUpdateOrderCommand("4", "01-01-2021", "99999");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

}
