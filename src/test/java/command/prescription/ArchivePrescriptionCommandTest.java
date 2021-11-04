package command.prescription;

import command.Command;
import command.Data;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author RemusTeo

public class ArchivePrescriptionCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        Data.generateTestData();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @AfterAll
    public static void clearData() {
        Data.clearTestData();
    }

    private void executeArchivePrescriptionCommand(String parameter, String parameterValue) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put(parameter, parameterValue);
        Command command = new ArchivePrescriptionCommand(parameters);
        command.execute();
    }

    private void executeListPrescriptionCommand() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        Command command = new ListPrescriptionCommand(parameters);
        command.execute();
    }

    @Test
    public void archivePrescriptionCommand_prescriptionsDoesNotExist_expectZeroArchived() {
        executeArchivePrescriptionCommand("d", "08-10-2021");
        assertEquals("Archived 0 prescriptions from 08-10-2021", outContent.toString().trim());
    }

    @Test
    public void archivePrescriptionCommand_prescriptionsExist_expectTwoArchived() {
        executeArchivePrescriptionCommand("d", "10-10-2021");
        assertEquals("Archived 2 prescriptions from 10-10-2021", outContent.toString().trim());
    }

    @Test
    public void archivePrescriptionCommand_prescriptionsRemoved_expectTwoLessPrescriptions() {
        archivePrescriptionCommand_prescriptionsExist_expectTwoArchived();
        executeListPrescriptionCommand();
        String expectedOutput = "Archived 2 prescriptions from 10-10-2021\n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void archivePrescriptionCommand_noPrescriptionsToRemove_expectSamePrescriptions() {
        archivePrescriptionCommand_prescriptionsDoesNotExist_expectZeroArchived();
        executeListPrescriptionCommand();
        String expectedOutput = "Archived 0 prescriptions from 08-10-2021\n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| ID |     NAME     | QUANTITY | CUSTOMER_ID |    DATE    | STAFF | STOCK_ID | \n"
                + "+====+==============+==========+=============+============+=======+==========+\n"
                + "| 1  |   PANADOL    |    10    |  S1234567A  | 09-10-2021 | JANE  |    1     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 2  |   VICODIN    |    15    |  S2345678B  | 10-10-2021 | PETER |    3     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 3  | SIMVASTATIN  |    20    |  S1234567A  | 11-10-2021 |  SAM  |    4     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 4  |  LISINOPRIL  |    10    |  S3456789C  | 12-10-2021 | JANE  |    5     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+\n"
                + "| 5  | AZITHROMYCIN |    5     |  S2345678B  | 13-10-2021 | PETER |    6     | \n"
                + "+----+--------------+----------+-------------+------------+-------+----------+";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }
}
