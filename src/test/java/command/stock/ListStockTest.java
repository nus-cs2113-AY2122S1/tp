package command.stock;

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

public class ListStockTest {
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

    @Test
    public void listStock_sortByIdAscending_expectStocksWithSortedIdAscending() {
        String expectedOutput =
                "+====+==============+========+==============+=============+==================+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
                + "| 1  |   PANADOL    | $20.00 |      20      | 13-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 2  |   PANADOL    | $20.00 |      10      | 14-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 3  |   VICODIN    | $10.00 |      20      | 30-09-2022  |   SEVERE PAIN    |     500      | \n"
                + "|    |              |        | PENDING: 30  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 4  | SIMVASTATIN  | $20.00 |      25      | 10-10-2023  | HIGH CHOLESTEROL |     800      | \n"
                + "|    |              |        | PENDING: 20  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 5  |  LISINOPRIL  | $20.00 |      25      | 15-10-2023  |  HYPOTHYROIDISM  |     800      | \n"
                + "|    |              |        | PENDING: 200 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2023  |    INFECTIONS    |     100      | \n"
                + "|    |              |        | PENDING: 100 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "i");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStock_sortByNameAscending_expectStocksWithSortedNameAscending() {
        String expectedOutput =
                "+====+==============+========+==============+=============+==================+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
                + "| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2023  |    INFECTIONS    |     100      | \n"
                + "|    |              |        | PENDING: 100 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 5  |  LISINOPRIL  | $20.00 |      25      | 15-10-2023  |  HYPOTHYROIDISM  |     800      | \n"
                + "|    |              |        | PENDING: 200 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 1  |   PANADOL    | $20.00 |      20      | 13-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 2  |   PANADOL    | $20.00 |      10      | 14-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 4  | SIMVASTATIN  | $20.00 |      25      | 10-10-2023  | HIGH CHOLESTEROL |     800      | \n"
                + "|    |              |        | PENDING: 20  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 3  |   VICODIN    | $10.00 |      20      | 30-09-2022  |   SEVERE PAIN    |     500      | \n"
                + "|    |              |        | PENDING: 30  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "n");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStock_sortByPriceAscending_expectStocksWithSortedPriceAscending() {
        String expectedOutput =
                "+====+==============+========+==============+=============+==================+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
                + "| 3  |   VICODIN    | $10.00 |      20      | 30-09-2022  |   SEVERE PAIN    |     500      | \n"
                + "|    |              |        | PENDING: 30  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 1  |   PANADOL    | $20.00 |      20      | 13-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 2  |   PANADOL    | $20.00 |      10      | 14-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 4  | SIMVASTATIN  | $20.00 |      25      | 10-10-2023  | HIGH CHOLESTEROL |     800      | \n"
                + "|    |              |        | PENDING: 20  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 5  |  LISINOPRIL  | $20.00 |      25      | 15-10-2023  |  HYPOTHYROIDISM  |     800      | \n"
                + "|    |              |        | PENDING: 200 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2023  |    INFECTIONS    |     100      | \n"
                + "|    |              |        | PENDING: 100 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "p");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStock_sortByExpiryDescending_expectStocksWithSortedExpiryDescending() {
        String expectedOutput =
                "+====+==============+========+==============+=============+==================+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
                + "| 5  |  LISINOPRIL  | $20.00 |      25      | 15-10-2023  |  HYPOTHYROIDISM  |     800      | \n"
                + "|    |              |        | PENDING: 200 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2023  |    INFECTIONS    |     100      | \n"
                + "|    |              |        | PENDING: 100 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 4  | SIMVASTATIN  | $20.00 |      25      | 10-10-2023  | HIGH CHOLESTEROL |     800      | \n"
                + "|    |              |        | PENDING: 20  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 3  |   VICODIN    | $10.00 |      20      | 30-09-2022  |   SEVERE PAIN    |     500      | \n"
                + "|    |              |        | PENDING: 30  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 2  |   PANADOL    | $20.00 |      10      | 14-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 1  |   PANADOL    | $20.00 |      20      | 13-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("rsort", "e");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStock_sortByDescriptionDescending_expectStocksWithSortedDescriptionDescending() {
        String expectedOutput =
                "+====+==============+========+==============+=============+==================+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
                + "| 3  |   VICODIN    | $10.00 |      20      | 30-09-2022  |   SEVERE PAIN    |     500      | \n"
                + "|    |              |        | PENDING: 30  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2023  |    INFECTIONS    |     100      | \n"
                + "|    |              |        | PENDING: 100 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 5  |  LISINOPRIL  | $20.00 |      25      | 15-10-2023  |  HYPOTHYROIDISM  |     800      | \n"
                + "|    |              |        | PENDING: 200 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 4  | SIMVASTATIN  | $20.00 |      25      | 10-10-2023  | HIGH CHOLESTEROL |     800      | \n"
                + "|    |              |        | PENDING: 20  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 1  |   PANADOL    | $20.00 |      20      | 13-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 2  |   PANADOL    | $20.00 |      10      | 14-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("rsort", "d");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStock_sortByMaxQuantityDescending_expectStocksWithSortedMaxQuantityDescending() {
        String expectedOutput =
                "+====+==============+========+==============+=============+==================+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
                + "| 1  |   PANADOL    | $20.00 |      20      | 13-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 2  |   PANADOL    | $20.00 |      10      | 14-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |              |        | PENDING: 150 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 4  | SIMVASTATIN  | $20.00 |      25      | 10-10-2023  | HIGH CHOLESTEROL |     800      | \n"
                + "|    |              |        | PENDING: 20  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 5  |  LISINOPRIL  | $20.00 |      25      | 15-10-2023  |  HYPOTHYROIDISM  |     800      | \n"
                + "|    |              |        | PENDING: 200 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 3  |   VICODIN    | $10.00 |      20      | 30-09-2022  |   SEVERE PAIN    |     500      | \n"
                + "|    |              |        | PENDING: 30  |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2023  |    INFECTIONS    |     100      | \n"
                + "|    |              |        | PENDING: 100 |             |                  |              | \n"
                + "+----+--------------+--------+--------------+-------------+------------------+--------------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("rsort", "m");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStock_columnDoesNotExist_expectError() {
        String expectedOutput = "Invalid column name/alias! Column names can only be [ID, NAME, PRICE, QUANTITY, "
                + "EXPIRY_DATE, DESCRIPTION, MAX_QUANTITY] and the respective aliases are [i, n, p, q, e, d, m].";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("sort", "a");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
