package command.order;

import command.Data;
import inventory.Medicine;
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
        Medicine.getInstance().clear();
    }

    //@@author alvintan01

    @Test
    public void listOrder_sortByIdAscending_expectOrdersWithSortedIdAscending() {
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
    public void listOrder_sortByNameAscending_expectOrdersWithSortedNameAscending() {
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
    public void listOrder_sortByQuantityDescending_expectOrdersWithSortedQuantityDescending() {
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
    public void listOrder_sortByDateDescending_expectOrdersWithSortedDateDescending() {
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
    public void listOrder_sortByStatusDescending_expectOrdersWithSortedStatusDescending() {
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
    public void listOrder_columnDoesNotExist_expectError() {
        String expectedOutput = "Invalid column name/alias! Column names can only be [ID, NAME, QUANTITY, DATE, "
                + "STATUS] and the respective aliases are [i, n, q, d, s].";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "a");
        new ListOrderCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
