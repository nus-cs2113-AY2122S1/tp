package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.tours.CutTourCommand;
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

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
        Tour zwm = new Tour(new String[]{"ZWM", "Zimbabwe Tour", "1700.00"});

        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"});
        Flight sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"});

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});

        ClientPackage korPack1 = new ClientPackage("p001", botuan, kor, sqjpn);
        ClientPackage korPack2 = new ClientPackage("p002", wayne, kor, sqkor);

        tours = createTourList(jpn, kor, zwm);
        toursWithoutKor = createTourList(jpn, zwm);

        clientPackages = createClientPackageList(korPack1, korPack2);
    }

    @Test
    public void cutTourCommand_validTourId_tourDeleted() throws TourPlannerException {
        assertEquals(tours.getTourCount(), 3);
        Command cutTourCommand = new CutTourCommand("KOR");
        cutTourCommand.setData(clients, flights, tours, clientPackages, ui);
        cutTourCommand.execute();
        assertEquals(tours.getTourCount(), toursWithoutKor.getTourCount());
        for (int i = 0; i < toursWithoutKor.getTourCount(); i++) {
            assertEquals(tours.getTourByIndex(i), toursWithoutKor.getTourByIndex(i));
        }
    }

    @Test
    public void cutTourCommand_validTourId_clientPackagesDeleted() throws TourPlannerException {
        Command cutTourCommand = new CutTourCommand("KOR");
        cutTourCommand.setData(clients, flights, tours, clientPackages, ui);
        cutTourCommand.execute();
        assertEquals(clientPackages.getClientPackageCount(), 0);
    }

    @Test
    public void cutTourCommand_invalidTourId_tourNotFoundMessage() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command cutTourCommand = new CutTourCommand("1234po0o");
        cutTourCommand.setData(clients, flights, tours, clientPackages, ui);
        cutTourCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Tour cannot be found. Please try another tour ID";
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