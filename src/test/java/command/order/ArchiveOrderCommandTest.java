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

//@@author RemusTeo

public class ArchiveOrderCommandTest {
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

    @Test
    public void archiveOrderCommand_deliveredOrderDoesNotExist_expectZeroArchived() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("d", "10-10-2021");
        Command command = new ArchiveOrderCommand(parameters);
        command.execute();
        assertEquals("Archived 0 delivered orders from 10-10-2021", outContent.toString().trim());
    }

    @Test
    public void archiveOrderCommand_deliveredOrdersExist_expectOneArchived() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("d", "11-10-2021");
        Command command = new ArchiveOrderCommand(parameters);
        command.execute();
        assertEquals("Archived 1 delivered orders from 11-10-2021", outContent.toString().trim());
    }

    @Test
    public void archiveOrderCommand_deliveredOrdersRemoved_expectOneLessOrder() {
        archiveOrderCommand_deliveredOrdersExist_expectOneArchived();

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        Command command = new ListOrderCommand(parameters);
        command.execute();
        String expectedOutput = "Archived 1 delivered orders from 11-10-2021\n"
                + "+====+==============+==========+============+=========+\n"
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
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    public void archiveOrderCommand_noDeliveredOrdersToRemove_expectSameOrder() {
        archiveOrderCommand_deliveredOrderDoesNotExist_expectZeroArchived();

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        Command command = new ListOrderCommand(parameters);
        command.execute();
        String expectedOutput = "Archived 0 delivered orders from 10-10-2021\n"
                + "+====+==============+==========+============+===========+\n"
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
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }
}
