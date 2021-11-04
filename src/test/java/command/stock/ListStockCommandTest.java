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

public class ListStockCommandTest {
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

    public void executeListStockCommand(String parameter, String parameterValue){
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put(parameter, parameterValue);
        new ListStockCommand(parameters).execute();
    }

    @Test
    public void listStockCommand_filterByIdOne_expectStocksWithIdOne() {
        String expectedOutput =
                  "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |      20      | 13-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "1");
        new ListStockCommand(parameters).execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByNamePanadol_expectStocksWithNamePanadol() {
        String expectedOutput =
                  "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |      20      | 13-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+\n"
                + "| 2  | PANADOL | $20.00 |      10      | 14-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("n", "panadol");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByPriceTen_expectStocksWithPriceTen() {
        String expectedOutput =
                   "+====+=========+========+=============+=============+=============+==============+\n"
                 + "| ID |  NAME   | PRICE  |  QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                 + "+====+=========+========+=============+=============+=============+==============+\n"
                 + "| 3  | VICODIN | $10.00 |     20      | 30-09-2022  | SEVERE PAIN |     500      | \n"
                 + "|    |         |        | PENDING: 30 |             |             |              | \n"
                 + "+----+---------+--------+-------------+-------------+-------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("p", "10");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByQuantityTen_expectStocksWithQuantityTen() {
        String expectedOutput =
                  "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 2  | PANADOL | $20.00 |      10      | 14-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("q", "10");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByQuantityLessThan30_expectStocksWithQuantityLessThan30() {
        String expectedOutput =
                  "+====+=============+========+==============+=============+==================+==============+\n"
                + "| ID |    NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+=============+========+==============+=============+==================+==============+\n"
                + "| 1  |   PANADOL   | $20.00 |      20      | 13-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |             |        | PENDING: 150 |             |                  |              | \n"
                + "+----+-------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 2  |   PANADOL   | $20.00 |      10      | 14-09-2022  |    HEADACHES     |     1000     | \n"
                + "|    |             |        | PENDING: 150 |             |                  |              | \n"
                + "+----+-------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 3  |   VICODIN   | $10.00 |      20      | 30-09-2022  |   SEVERE PAIN    |     500      | \n"
                + "|    |             |        | PENDING: 30  |             |                  |              | \n"
                + "+----+-------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 4  | SIMVASTATIN | $20.00 |      25      | 10-10-2023  | HIGH CHOLESTEROL |     800      | \n"
                + "|    |             |        | PENDING: 20  |             |                  |              | \n"
                + "+----+-------------+--------+--------------+-------------+------------------+--------------+\n"
                + "| 5  | LISINOPRIL  | $20.00 |      25      | 15-10-2023  |  HYPOTHYROIDISM  |     800      | \n"
                + "|    |             |        | PENDING: 200 |             |                  |              | \n"
                + "+----+-------------+--------+--------------+-------------+------------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("low", "30");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByExpiry13Sep2022_expectStocksWithExpiry13Sep2022() {
        String expectedOutput =
                  "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |      20      | 13-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("e", "13-09-2022");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByExpiryBefore31Dec2022_expectStocksWithExpiryBefore31Dec2022() {
        String expectedOutput =
                "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |      20      | 13-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+\n"
                + "| 2  | PANADOL | $20.00 |      10      | 14-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+\n"
                + "| 3  | VICODIN | $10.00 |      20      | 30-09-2022  | SEVERE PAIN |     500      | \n"
                + "|    |         |        | PENDING: 30  |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("expiring", "31-12-2022");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByDescription_expectStocksWithDescription() {
        String expectedOutput =
                  "+====+==============+========+==============+=============+=============+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+=============+==============+\n"
                + "| 6  | AZITHROMYCIN | $20.00 |      35      | 15-10-2023  | INFECTIONS  |     100      | \n"
                + "|    |              |        | PENDING: 100 |             |             |              | \n"
                + "+----+--------------+--------+--------------+-------------+-------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("d", "INFECTIONS");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStockCommand_filterByMaxQuantity1000_expectStocksWithMaxQuantity1000() {
        String expectedOutput =
                  "+====+=========+========+==============+=============+=============+==============+\n"
                + "| ID |  NAME   | PRICE  |   QUANTITY   | EXPIRY_DATE | DESCRIPTION | MAX_QUANTITY | \n"
                + "+====+=========+========+==============+=============+=============+==============+\n"
                + "| 1  | PANADOL | $20.00 |      20      | 13-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+\n"
                + "| 2  | PANADOL | $20.00 |      10      | 14-09-2022  |  HEADACHES  |     1000     | \n"
                + "|    |         |        | PENDING: 150 |             |             |              | \n"
                + "+----+---------+--------+--------------+-------------+-------------+--------------+";

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("m", "1000");
        new ListStockCommand(parameters).execute();
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    //@@author alvintan01

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
        executeListStockCommand("sort", "i");
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
        executeListStockCommand("sort", "n");
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
        executeListStockCommand("sort", "p");
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
        executeListStockCommand("rsort", "e");
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
        executeListStockCommand("rsort", "d");
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
        executeListStockCommand("rsort", "m");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listStock_columnDoesNotExist_expectError() {
        String expectedOutput = "Invalid column name/alias! Column names can only be [ID, NAME, PRICE, QUANTITY, "
                + "EXPIRY_DATE, DESCRIPTION, MAX_QUANTITY] and the respective aliases are [i, n, p, q, e, d, m].";
        executeListStockCommand("sort", "a");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
