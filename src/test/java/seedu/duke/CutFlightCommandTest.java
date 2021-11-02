package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.flights.CutFlightCommand;
import seedu.duke.data.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CutFlightCommandTest {
    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    private FlightList flightsWithoutKor;

    @BeforeEach
    public void setUp() {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"});
        Flight sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"});
        Flight sqzwm = new Flight(new String[]{"SQ-ZWM", "ZWM", "SG", "5/11/21 09:00", "7/11/21 15:00"});

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});

        ClientPackage korPack1 = new ClientPackage("p001", botuan, jpn, sqkor);
        ClientPackage korPack2 = new ClientPackage("p002", wayne, kor, sqkor);

        flights = createFlightList(sqjpn, sqkor, sqzwm);
        flightsWithoutKor = createFlightList(sqjpn, sqzwm);

        clientPackages = createClientPackageList(korPack1, korPack2);
    }

    @Test
    public void cutFlightCommand_validFlightId_flightDeleted() throws TourPlannerException {
        assertEquals(flights.getFlightCount(), 3);
        Command cutFlightCommand = new CutFlightCommand("SQ-KOR");
        cutFlightCommand.setData(clients, flights, tours, clientPackages, ui);
        cutFlightCommand.execute();
        assertEquals(flights.getFlightCount(), flightsWithoutKor.getFlightCount());
        for (int i = 0; i < flightsWithoutKor.getFlightCount(); i++) {
            assertEquals(flights.getFlightByIndex(i), flightsWithoutKor.getFlightByIndex(i));
        }
    }

    @Test
    public void cutFlightCommand_validFlightId_clientPackagesDeleted() throws TourPlannerException {
        Command cutFlightCommand = new CutFlightCommand("SQ-KOR");
        cutFlightCommand.setData(clients, flights, tours, clientPackages, ui);
        cutFlightCommand.execute();
        assertEquals(clientPackages.getClientPackageCount(), 0);
    }

    @Test
    public void cutFlightCommand_invalidFlightId_flightNotFoundMessage() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command cutFlightCommand = new CutFlightCommand("1234po0o");
        cutFlightCommand.setData(clients, flights, tours, clientPackages, ui);
        cutFlightCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Flight cannot be found. Please try another flight ID";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    private FlightList createFlightList(Flight...flightList) {
        FlightList newFlightList = new FlightList();
        for (Flight flight : flightList) {
            newFlightList.add(flight);
        }
        return newFlightList;
    }

    private ClientPackageList createClientPackageList(ClientPackage...clientPackageList) {
        ClientPackageList newClientPackageList = new ClientPackageList();
        for (ClientPackage clientPackage: clientPackageList) {
            newClientPackageList.add(clientPackage);
        }
        return newClientPackageList;
    }
}