package seedu.duke.commands.tours;

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

public class CutTourCommandTest {
    public static final String TOUR_ID_2 = "KOR";

    public static final String[] TOUR_JPN = {"JPN", "Japan Basic Tour", "1500.00"};
    public static final String[] TOUR_KOR = {TOUR_ID_2, "Korea Cultural Tour", "3000.00"};
    public static final String[] TOUR_ZWM = {"ZWM", "Zimbabwe Tour", "1700.00"};

    public static final String[] FLIGHT_SQJPN = {"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"};
    public static final String[] FLIGHT_SQKOR = {"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"};

    public static final String[] CLIENT_BOTUAN = {"c001", "Bo Tuan", "93338333", "bt@mail.com"};
    public static final String[] CLIENT_WAYNE = {"c002", "Wayne", "56667888", "wen@mail.com"};

    public static final String CLIENT_PACKAGE_ID_1 = "p001";
    public static final String CLIENT_PACKAGE_ID_2 = "p002";
    public static final String TOUR_ID_WRONG = "1234po0o";

    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    private TourList toursWithoutKor;

    @BeforeEach
    public void setUp() {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        Tour jpn = new Tour(TOUR_JPN);
        Tour kor = new Tour(TOUR_KOR);
        Tour zwm = new Tour(TOUR_ZWM);

        Flight sqjpn = new Flight(FLIGHT_SQJPN);
        Flight sqkor = new Flight(FLIGHT_SQKOR);

        Client botuan = new Client(CLIENT_BOTUAN);
        Client wayne = new Client(CLIENT_WAYNE);

        ClientPackage korPack1 = new ClientPackage(CLIENT_PACKAGE_ID_1, botuan, kor, sqjpn);
        ClientPackage korPack2 = new ClientPackage(CLIENT_PACKAGE_ID_2, wayne, kor, sqkor);

        tours = createTourList(jpn, kor, zwm);
        toursWithoutKor = createTourList(jpn, zwm);

        clientPackages = createClientPackageList(korPack1, korPack2);
    }

    @Test
    public void cutTourCommand_validTourId_tourDeleted() throws TourPlannerException {
        assertEquals(tours.getTourCount(), 3);
        Command cutTourCommand = new CutTourCommand(TOUR_ID_2);
        cutTourCommand.setData(clients, flights, tours, clientPackages, ui);
        cutTourCommand.execute();
        assertEquals(tours.getTourCount(), toursWithoutKor.getTourCount());
        for (int i = 0; i < toursWithoutKor.getTourCount(); i++) {
            assertEquals(tours.getTourByIndex(i), toursWithoutKor.getTourByIndex(i));
        }
    }

    @Test
    public void cutTourCommand_validTourId_clientPackagesDeleted() throws TourPlannerException {
        Command cutTourCommand = new CutTourCommand(TOUR_ID_2);
        cutTourCommand.setData(clients, flights, tours, clientPackages, ui);
        cutTourCommand.execute();
        assertEquals(clientPackages.getClientPackageCount(), 0);
    }

    @Test
    public void cutTourCommand_invalidTourId_tourNotFoundMessage() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command cutTourCommand = new CutTourCommand(TOUR_ID_WRONG);
        cutTourCommand.setData(clients, flights, tours, clientPackages, ui);
        cutTourCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = TourList.TOUR_NOT_FOUND_MESSAGE;
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    private TourList createTourList(Tour... tourList) {
        TourList newTourList = new TourList();
        for (Tour tour : tourList) {
            newTourList.add(tour);
        }
        return newTourList;
    }

    private ClientPackageList createClientPackageList(ClientPackage...clientPackageList) {
        ClientPackageList newClientPackageList = new ClientPackageList();
        for (ClientPackage clientPackage: clientPackageList) {
            newClientPackageList.add(clientPackage);
        }
        return newClientPackageList;
    }
}