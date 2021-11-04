package command.prescription;

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

//@@author alvintan01

public class ListPrescriptionCommandTest {
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

    public void executeListPrescriptionCommand(String parameter, String parameterValue) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put(parameter, parameterValue);
        new ListPrescriptionCommand(parameters).execute();
    }

    @Test
    public void listPrescription_filterByIdTwo_expectPrescriptionsWithIdTwo() {
        String expectedOutput = "+====+=========+==========+=============+============+=======+==========+\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| 2  | VICODIN |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+---------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("i", "2");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_filterByNamePanadol_expectPrescriptionsWithPanadol() {
        String expectedOutput = "+====+=========+==========+=============+============+=======+==========+\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| 1  | PANADOL |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+---------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("n", "panadol");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_filterByQuantityTen_expectPrescriptionsWithTenQuantity() {
        String expectedOutput = "+====+============+==========+=============+============+=======+==========+\n"
                + "| ID |    NAME    | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+============+==========+=============+============+=======+==========+\n"
                + "| 1  |  PANADOL   |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  | LISINOPRIL |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("q", "10");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_filterByCustomerIdS1234567A_expectPrescriptionsWithCustomerIdS1234567A() {
        String expectedOutput = "+====+=============+==========+=============+============+=======+==========+\n"
                + "| ID |    NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+=============+==========+=============+============+=======+==========+\n"
                + "| 1  |   PANADOL   |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+-------------+----------+-------------+------------+-------+----------+\n"
                + "| 3  | SIMVASTATIN |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+-------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("c", "S1234567A");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_filterByDate13October2021_expectPrescriptionsWithDate13October2021() {
        String expectedOutput = "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("d", "13-10-2021");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_filterByStaffJane_expectPrescriptionsWithStaffJane() {
        String expectedOutput = "+====+============+==========+=============+============+=======+==========+\n"
                + "| ID |    NAME    | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+============+==========+=============+============+=======+==========+\n"
                + "| 1  |  PANADOL   |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  | LISINOPRIL |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("s", "jane");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_filterByStockId1_expectPrescriptionsWithStockId1() {
        String expectedOutput = "+====+=========+==========+=============+============+=======+==========+\n"
                + "| ID |  NAME   | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+=========+==========+=============+============+=======+==========+\n"
                + "| 1  | PANADOL |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+---------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("sid", "1");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_sortByIdDescending_expectPrescriptionsWithSortedIdDescending() {
        String expectedOutput = "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 2  |   VICODIN    |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("rsort", "i");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_sortByNameAscending_expectPrescriptionsWithSortedNameAscending() {
        String expectedOutput = "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 2  |   VICODIN    |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("sort", "n");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_sortByQuantityAscending_expectPrescriptionsWithSortedQuantityAscending() {
        String expectedOutput = "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 2  |   VICODIN    |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("sort", "q");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_sortByDateDescending_expectPrescriptionsWithSortedDateDescending() {
        String expectedOutput = "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 2  |   VICODIN    |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("rsort", "d");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_sortByStaffDescending_expectPrescriptionsWithSortedStaffDescending() {
        String expectedOutput = "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 2  |   VICODIN    |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("rsort", "s");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_sortByStockIdDescending_expectPrescriptionsWithSortedStockIdDescending() {
        String expectedOutput = "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 2  |   VICODIN    |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        executeListPrescriptionCommand("rsort", "sid");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_invalidParameter_expectError() {
        String expectedOutput = "Please enter a valid optional parameter!\n"
                + "COMMAND SYNTAX: listprescription {i/ID q/QUANTITY c/CUSTOMER_ID d/DATE s/STAFF_NAME sid/STOCK_ID "
                + "sort/COLUMN_NAME rsort/COLUMN_NAME}";
        executeListPrescriptionCommand("a", "1");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_nameDoesNotExist_expectNoResultsFound() {
        String expectedOutput = "There are no records of medicines prescribed.";
        executeListPrescriptionCommand("n", "abcd");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void listPrescription_columnDoesNotExist_expectError() {
        String expectedOutput = "Invalid column name/alias! Column names can only be [ID, NAME, QUANTITY, CUSTOMER_"
                + "ID, DATE, STAFF, STOCK_ID] and the respective aliases are [i, n, q, c, d, s, sid].";
        executeListPrescriptionCommand("sort", "a");
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
