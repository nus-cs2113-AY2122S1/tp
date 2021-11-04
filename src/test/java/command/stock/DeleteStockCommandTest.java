package command.stock;

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

public class DeleteStockCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setup() {
        Data.generateTestData();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @AfterAll
    public static void clearData() {
        Data.clearTestData();
    }

    private void executeDeleteStockCommand(String parameter, String parameterValue) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put(parameter, parameterValue);
        Command command = new DeleteStockCommand(parameters);
        command.execute();
    }

    private void executeListStockCommand() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        Command command = new ListStockCommand(parameters);
        command.execute();
    }

    @Test
    void deleteStockCommand_invalidStockId_expectInvalid() {
        String[] invalidParams = {"-1", "0", "7"};
        for (String param : invalidParams) {
            executeDeleteStockCommand("i", param);
            String expectedOutput = "Invalid stock id provided!";
            assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
            outContent.reset();
        }
    }

    @Test
    void deleteStockCommand_validStockId_expectValid() {
        executeDeleteStockCommand("i", "1");
        assertEquals("Deleted row with Stock Id: 1", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_sameStockIdTwice_expectInvalid() {
        deleteStockCommand_validStockId_expectValid();
        executeDeleteStockCommand("i", "1");
        String expectedOutput = "Deleted row with Stock Id: 1\n" + "Invalid stock id provided!";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    void deleteStockCommand_validStockId_expectOneLessStock() {
        deleteStockCommand_validStockId_expectValid();
        executeListStockCommand();
        String expectedOutput = "Deleted row with Stock Id: 1\n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
                + "| ID |     NAME     | PRICE  |   QUANTITY   | EXPIRY_DATE |   DESCRIPTION    | MAX_QUANTITY | \n"
                + "+====+==============+========+==============+=============+==================+==============+\n"
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
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }

    @Test
    void deleteStockCommand_invalidStockId_expectSameStocks() {
        deleteStockCommand_invalidStockId_expectInvalid();
        executeListStockCommand();
        String expectedOutput = ""
                + "+====+==============+========+==============+=============+==================+==============+\n"
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
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }
}
