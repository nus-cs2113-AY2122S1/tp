package seedu.duke.commands.flights;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CutFlightCommandTest {
    public static final String FLIGHT_ID_2 = "SQ-KOR";

    public static final String[] FLIGHT_SQJPN = {"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"};
    public static final String[] FLIGHT_SQKOR = {FLIGHT_ID_2, "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"};
    public static final String[] FLIGHT_SQZWM = {"SQ-ZWM", "ZWM", "SG", "5/11/21 09:00", "7/11/21 15:00"};

    public static final String[] CLIENT_BOTUAN = {"c001", "Bo Tuan", "93338333", "bt@mail.com"};
    public static final String[] CLIENT_WAYNE = {"c002", "Wayne", "56667888", "wen@mail.com"};

    public static final String[] TOUR_JPN = {"JPN", "Japan Basic Tour", "1500.00"};
    public static final String[] TOUR_KOR = {"KOR", "Korea Cultural Tour", "3000.00"};

    public static final String CLIENT_PACKAGE_ID_1 = "p001";
    public static final String CLIENT_PACKAGE_ID_2 = "p002";
    public static final String FLIGHT_ID_WRONG = "1234po0o";

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

        Flight sqjpn = new Flight(FLIGHT_SQJPN);
        Flight sqkor = new Flight(FLIGHT_SQKOR);
        Flight sqzwm = new Flight(FLIGHT_SQZWM);

        Client botuan = new Client(CLIENT_BOTUAN);
        Client wayne = new Client(CLIENT_WAYNE);

        Tour jpn = new Tour(TOUR_JPN);
        Tour kor = new Tour(TOUR_KOR);

        ClientPackage korPack1 = new ClientPackage(CLIENT_PACKAGE_ID_1, botuan, jpn, sqkor);
        ClientPackage korPack2 = new ClientPackage(CLIENT_PACKAGE_ID_2, wayne, kor, sqkor);

        flights = createFlightList(sqjpn, sqkor, sqzwm);
        flightsWithoutKor = createFlightList(sqjpn, sqzwm);

        clientPackages = createClientPackageList(korPack1, korPack2);
    }

    @Test
    public void cutFlightCommand_validFlightId_flightDeleted() throws TourPlannerException {
        assertEquals(flights.getFlightCount(), 3);
        Command cutFlightCommand = new CutFlightCommand(FLIGHT_ID_2);
        cutFlightCommand.setData(clients, flights, tours, clientPackages, ui);
        cutFlightCommand.execute();
        assertEquals(flights.getFlightCount(), flightsWithoutKor.getFlightCount());
        for (int i = 0; i < flightsWithoutKor.getFlightCount(); i++) {
            assertEquals(flights.getFlightByIndex(i), flightsWithoutKor.getFlightByIndex(i));
        }
    }

    @Test
    public void cutFlightCommand_validFlightId_clientPackagesDeleted() throws TourPlannerException {
        Command cutFlightCommand = new CutFlightCommand(FLIGHT_ID_2);
        cutFlightCommand.setData(clients, flights, tours, clientPackages, ui);
        cutFlightCommand.execute();
        assertEquals(clientPackages.getClientPackageCount(), 0);
    }

    @Test
    public void cutFlightCommand_invalidFlightId_flightNotFoundMessage() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command cutFlightCommand = new CutFlightCommand(FLIGHT_ID_WRONG);
        cutFlightCommand.setData(clients, flights, tours, clientPackages, ui);
        cutFlightCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = FlightList.FLIGHT_NOT_FOUND_MESSAGE;
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