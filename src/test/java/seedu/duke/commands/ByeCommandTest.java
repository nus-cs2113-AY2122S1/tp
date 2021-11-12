package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    @Test
    public void byeCommand_validUserInput_showMessage() throws TourPlannerException {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        System.setOut(new PrintStream(newConsole));
        Command byeCommand = new ByeCommand();
        byeCommand.setData(clients, flights, tours, clientPackages, ui);
        byeCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Thanks for using TourPlanner. Goodbye!\n"
                + "____________________________________________________________";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}
