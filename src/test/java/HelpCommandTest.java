import command.HelpCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void helpCommand_noParameters_expectHelpTable() {
        String expectedOutput = "Welcome to the help page.\n"
                + "Your current mode is indicated in the square brackets at the bottom left of the console.\n"
                + "It allows you to type add, list, update, delete without typing in the full command.\n"
                + "Type stock, prescription or order to change to respective modes.\n"
                + "Note that parameters in {curly braces} are optional.\n"
                + "Parameters in [square braces] indicate that at least one of the parameter(s) must be provided.\n"
                + "Parameters enclosed in (round brackets) are conditional optional parameters. For example, the "
                + "parameters d/DESCRIPTION and m/MAX_QUANTITY in addstock and receiveorder will be optional only if "
                + "the stock exists.\n"
                + "+=====================+====================================================+\n"
                + "|       COMMAND       |                   COMMAND SYNTAX                   | \n"
                + "+=====================+====================================================+\n"
                + "|      addstock       | addstock n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE   | \n"
                + "|                     | (d/DESCRIPTION m/MAX_QUANTITY)                     | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|     deletestock     | deletestock [i/ID expiring/EXPIRY_DATE]            | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|     updatestock     | updatestock i/ID [n/NAME p/PRICE q/QUANTITY        | \n"
                + "|                     | e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY]        | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|      liststock      | liststock {i/ID p/PRICE q/QUANTITY                 | \n"
                + "|                     | low/LESS_THAN_OR_EQUAL_QUANTITY e/EXPIRY_DATE      | \n"
                + "|                     | expiring/LESS_THAN_OR_EQUAL_EXPIRY_DATE            | \n"
                + "|                     | d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME      | \n"
                + "|                     | rsort/COLUMN_NAME}                                 | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|   addprescription   | addprescription n/NAME q/QUANTITY c/CUSTOMER_ID    | \n"
                + "|                     | s/STAFF_NAME                                       | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "| deleteprescription  | deleteprescription i/ID                            | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "| updateprescription  | updateprescription i/ID [n/NAME q/QUANTITY         | \n"
                + "|                     | c/CUSTOMER_ID d/DATE s/STAFF_NAME]                 | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|  listprescription   | listprescription {i/ID q/QUANTITY c/CUSTOMER_ID    | \n"
                + "|                     | d/DATE s/STAFF_NAME sid/STOCK_ID                   | \n"
                + "|                     | sort/COLUMN_NAME rsort/COLUMN_NAME}                | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "| archiveprescription | archiveprescription d/DATE                         | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|      addorder       | addorder n/NAME q/QUANTITY {d/DATE}                | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|     deleteorder     | deleteorder i/ID                                   | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|     updateorder     | updateorder i/ID [n/NAME q/QUANTITY d/DATE]        | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|      listorder      | listorder {i/ID n/NAME q/QUANTITY d/DATE           | \n"
                + "|                     | s/STATUS sort/COLUMN_NAME rsort/COLUMN_NAME}       | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|    archiveorder     | archiveorder d/DATE                                | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|    receiveorder     | receiveorder i/ID p/PRICE e/EXPIRY_DATE            | \n"
                + "|                     | (d/DESCRIPTION m/MAX_QUANTITY)                     | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|        purge        | purge                                              | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|        help         | help                                               | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "|        exit         | exit                                               | \n"
                + "+---------------------+----------------------------------------------------+\n"
                + "For more information, refer to User Guide: https://ay2122s1-cs2113t-t10-1.github.io/tp/";

        new HelpCommand().execute();
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
