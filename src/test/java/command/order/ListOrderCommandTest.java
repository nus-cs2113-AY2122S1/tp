package command.order;

import command.Command;
import command.Data;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOrderCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    public static void getData() {
        Data.generateTestData();
    }

    @BeforeEach
    public void setUp() {
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

    private void executeListOrderCommand() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        Command command = new ListOrderCommand(parameters);
        command.execute();
    }

    private void executeListOrderCommand(String parameter, String parameterValue) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put(parameter, parameterValue);
        Command command = new ListOrderCommand(parameters);
        command.execute();
    }

    //@@author alvintan01

    @Test
    public void listOrderCommand_sortByIdAscending_expectOrdersWithSortedIdAscending() {
        String expectedOutput = "+====+==============+==========+============+===========+\n"
                + "| ID |     NAME     | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+==============+==========+============+===========+\n"
                + "| 1  |   PANADOL    |   100    | 09-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 2  |   VICODIN    |    30    | 10-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 3  |   VICODIN    |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 4  | SIMVASTATIN  |    20    | 11-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 5  |  LISINOPRIL  |   200    | 12-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 6  | AZITHROMYCIN |   100    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 7  |   PANADOL    |    50    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "i");
        new ListOrderCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_sortByNameAscending_expectOrdersWithSortedNameAscending() {
        String expectedOutput = "+====+==============+==========+============+===========+\n"
                + "| ID |     NAME     | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+==============+==========+============+===========+\n"
                + "| 6  | AZITHROMYCIN |   100    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 5  |  LISINOPRIL  |   200    | 12-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 1  |   PANADOL    |   100    | 09-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 7  |   PANADOL    |    50    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 4  | SIMVASTATIN  |    20    | 11-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 2  |   VICODIN    |    30    | 10-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 3  |   VICODIN    |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+--------------+----------+------------+-----------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "n");
        new ListOrderCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_sortByQuantityDescending_expectOrdersWithSortedQuantityDescending() {
        String expectedOutput = "+====+==============+==========+============+===========+\n"
                + "| ID |     NAME     | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+==============+==========+============+===========+\n"
                + "| 5  |  LISINOPRIL  |   200    | 12-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 1  |   PANADOL    |   100    | 09-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 6  | AZITHROMYCIN |   100    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 3  |   VICODIN    |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 7  |   PANADOL    |    50    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 2  |   VICODIN    |    30    | 10-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 4  | SIMVASTATIN  |    20    | 11-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("rsort", "q");
        new ListOrderCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_sortByDateDescending_expectOrdersWithSortedDateDescending() {
        String expectedOutput = "+====+==============+==========+============+===========+\n"
                + "| ID |     NAME     | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+==============+==========+============+===========+\n"
                + "| 6  | AZITHROMYCIN |   100    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 7  |   PANADOL    |    50    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 5  |  LISINOPRIL  |   200    | 12-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 3  |   VICODIN    |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 4  | SIMVASTATIN  |    20    | 11-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 2  |   VICODIN    |    30    | 10-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 1  |   PANADOL    |   100    | 09-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("rsort", "d");
        new ListOrderCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_sortByStatusDescending_expectOrdersWithSortedStatusDescending() {
        String expectedOutput = "+====+==============+==========+============+===========+\n"
                + "| ID |     NAME     | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+==============+==========+============+===========+\n"
                + "| 1  |   PANADOL    |   100    | 09-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 2  |   VICODIN    |    30    | 10-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 4  | SIMVASTATIN  |    20    | 11-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 5  |  LISINOPRIL  |   200    | 12-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 6  | AZITHROMYCIN |   100    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 7  |   PANADOL    |    50    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 3  |   VICODIN    |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+--------------+----------+------------+-----------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("rsort", "s");
        new ListOrderCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_columnDoesNotExist_expectError() {
        String expectedOutput = "Invalid column name/alias! Column names can only be [ID, NAME, QUANTITY, DATE, "
                + "STATUS] and the respective aliases are [i, n, q, d, s].";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "a");
        new ListOrderCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    //@@author RemusTeo
    @Test
    public void listOrderCommand_withoutParameters_expectAllOrders() {
        executeListOrderCommand();
        String expectedOutput = "+====+==============+==========+============+===========+\n"
                + "| ID |     NAME     | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+==============+==========+============+===========+\n"
                + "| 1  |   PANADOL    |   100    | 09-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 2  |   VICODIN    |    30    | 10-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 3  |   VICODIN    |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 4  | SIMVASTATIN  |    20    | 11-10-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 5  |  LISINOPRIL  |   200    | 12-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 6  | AZITHROMYCIN |   100    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+\n"
                + "| 7  |   PANADOL    |    50    | 13-12-2021 |  PENDING  | \n"
                + "+----+--------------+----------+------------+-----------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidId_expectValidSingleOrder() {
        executeListOrderCommand("i", "7");
        String expectedOutput = "+====+=========+==========+============+=========+\n"
                + "| ID |  NAME   | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+=========+==========+============+=========+\n"
                + "| 7  | PANADOL |    50    | 13-12-2021 | PENDING | \n"
                + "+----+---------+----------+------------+---------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byInvalidId_expectInvalid() {
        String[] invalidParams = {"-1", "0", "8"};
        for (String param : invalidParams) {
            executeListOrderCommand("i", param);
            String expectedOutput = "Invalid order id provided!";
            assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
            outputStream.reset();
        }
    }

    @Test
    public void listOrderCommand_byValidName_expectOneMatchingOrder() {
        executeListOrderCommand("n", "SIMVASTATIN");
        String expectedOutput = "+====+=============+==========+============+=========+\n"
                + "| ID |    NAME     | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+=============+==========+============+=========+\n"
                + "| 4  | SIMVASTATIN |    20    | 11-10-2021 | PENDING | \n"
                + "+----+-------------+----------+------------+---------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidName_expectTwoMatchingOrders() {
        executeListOrderCommand("n", "VICODIN");
        String expectedOutput = "+====+=========+==========+============+===========+\n"
                + "| ID |  NAME   | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+=========+==========+============+===========+\n"
                + "| 2  | VICODIN |    30    | 10-10-2021 |  PENDING  | \n"
                + "+----+---------+----------+------------+-----------+\n"
                + "| 3  | VICODIN |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+---------+----------+------------+-----------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidName_expectNoMatch() {
        executeListOrderCommand("n", "ABC");
        String expectedOutput = "There are no orders found.";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidQuantity_expectOneMatchingOrder() {
        executeListOrderCommand("q", "200");
        String expectedOutput = "+====+============+==========+============+=========+\n"
                + "| ID |    NAME    | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+============+==========+============+=========+\n"
                + "| 5  | LISINOPRIL |   200    | 12-12-2021 | PENDING | \n"
                + "+----+------------+----------+------------+---------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidQuantity_expectNoMatch() {
        executeListOrderCommand("q", "300");
        String expectedOutput = "There are no orders found.";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidDate_expectOneMatchingOrder() {
        executeListOrderCommand("d", "9-10-2021");
        String expectedOutput = "+====+=========+==========+============+=========+\n"
                + "| ID |  NAME   | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+=========+==========+============+=========+\n"
                + "| 1  | PANADOL |   100    | 09-10-2021 | PENDING | \n"
                + "+----+---------+----------+------------+---------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidDate_expectTwoMatchingOrder() {
        executeListOrderCommand("d", "11-10-2021");
        String expectedOutput = "+====+=============+==========+============+===========+\n"
                + "| ID |    NAME     | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+=============+==========+============+===========+\n"
                + "| 3  |   VICODIN   |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+-------------+----------+------------+-----------+\n"
                + "| 4  | SIMVASTATIN |    20    | 11-10-2021 |  PENDING  | \n"
                + "+----+-------------+----------+------------+-----------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidDate_expectNoMatch() {
        executeListOrderCommand("d", "01-10-2021");
        String expectedOutput = "There are no orders found.";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidStatusPending_expectAllPendingOrders() {
        executeListOrderCommand("s", "PENDING");
        String expectedOutput = "+====+==============+==========+============+=========+\n"
                + "| ID |     NAME     | QUANTITY |    DATE    | STATUS  | \n"
                + "+====+==============+==========+============+=========+\n"
                + "| 1  |   PANADOL    |   100    | 09-10-2021 | PENDING | \n"
                + "+----+--------------+----------+------------+---------+\n"
                + "| 2  |   VICODIN    |    30    | 10-10-2021 | PENDING | \n"
                + "+----+--------------+----------+------------+---------+\n"
                + "| 4  | SIMVASTATIN  |    20    | 11-10-2021 | PENDING | \n"
                + "+----+--------------+----------+------------+---------+\n"
                + "| 5  |  LISINOPRIL  |   200    | 12-12-2021 | PENDING | \n"
                + "+----+--------------+----------+------------+---------+\n"
                + "| 6  | AZITHROMYCIN |   100    | 13-12-2021 | PENDING | \n"
                + "+----+--------------+----------+------------+---------+\n"
                + "| 7  |   PANADOL    |    50    | 13-12-2021 | PENDING | \n"
                + "+----+--------------+----------+------------+---------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byValidStatusDelivered_expectAllDeliveredOrders() {
        executeListOrderCommand("s", "DELIVERED");
        String expectedOutput = "+====+=========+==========+============+===========+\n"
                + "| ID |  NAME   | QUANTITY |    DATE    |  STATUS   | \n"
                + "+====+=========+==========+============+===========+\n"
                + "| 3  | VICODIN |    50    | 11-10-2021 | DELIVERED | \n"
                + "+----+---------+----------+------------+-----------+";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listOrderCommand_byInvalidStatus_expectInvalid() {
        executeListOrderCommand("s", "ABC");
        String expectedOutput = "Invalid status! Ensure status is either PENDING or DELIVERED";
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
