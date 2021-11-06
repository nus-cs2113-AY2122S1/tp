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

public class HelpCommandTest {
    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    @Test
    public void helpCommand_validUserInput_showMessage() throws TourPlannerException {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        System.setOut(new PrintStream(newConsole));
        Command helpCommand = new HelpCommand();
        helpCommand.setData(clients, flights, tours, clientPackages, ui);
        helpCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "add: Add information of all data types into the database.\n"
                + "Prefixes can be input in any order.\n"
                + "   Add client: add -c CLIENT_ID /n NAME /cn CONTACT_NUM /m EMAIL\n"
                + "   Add flight: add -f FLIGHT_ID /d DEPART_DESTINATION /r RETURN_DESTINATION\n"
                + "               /dd DEPARTURE_DATETIME /rd RETURN_DATETIME\n"
                + "   Add tour: add -t TOUR_ID /n DEPART_DESTINATION /p TOUR_PRICE\n"
                + "   Add client package: add -p PACKAGE_ID /c CLIENT_ID /t TOUR_ID /f FLIGHT_ID\n\n"
                + "list: Shows a list of all available entries of a specific data type, along with their "
                + "respective fields.\n"
                + "   List client: list -c\n"
                + "   List flight: list -f\n"
                + "   List tour: list -t\n"
                + "   List client package: list -p\n\n"
                + "cut: Deletes entry of a certain data type and all client packages corresponding to the entry.\n"
                + "   Cut client: cut -c CLIENT_ID\n"
                + "   Cut flight: cut -f FLIGHT_ID\n"
                + "   Cut tour: cut -t TOUR_ID\n"
                + "   Cut client package: cut -p PACKAGE_ID\n\n"
                + "find: Finds specific entry of data type, returns the entry and other relevant information.\n"
                + "   Find client: find -c CLIENT_NAME\n"
                + "   Find flight: find -f FLIGHT_ID\n"
                + "   Find tour: find -t TOUR_ID\n\n"
                + "sort: Sorts entries in the data type based on the criteria.\n "
                + "   Sort client:\n"
                + "      Sort by id: sort -c /id\n"
                + "      Sort by name: sort -c /n\n"
                + "   Sort flight:\n"
                + "      Sort by id: sort -f /id\n"
                + "      Sort by departure date: sort -f /d\n"
                + "      Sort by return date: sort -f /r\n"
                + "   Sort tour:\n"
                + "      Sort by id: sort -t /id\n"
                + "      Sort by name: sort -t /n\n"
                + "      Sort by price: sort -t /p\n\n"
                + "bye: Exits the program.";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }
}
