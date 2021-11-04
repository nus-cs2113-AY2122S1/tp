package seedu.duke.commands.clientpackages;

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

public class AddClientPackageCommandTest {
    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    private Client botuan;
    private Tour kor;
    private Flight sqkor;
    private ClientPackageList populatedClientPackages;

    @BeforeEach
    public void setUp() {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
        clients = createClientList(botuan, wayne);

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});
        tours = createTourList(jpn, kor);

        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"});
        sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"});
        flights = createFlightList(sqjpn, sqkor);

        ClientPackage botuanPack = new ClientPackage("p001", botuan, kor, sqkor);
        populatedClientPackages = createClientPackageList(botuanPack);

        clientPackages = createClientPackageList();
    }

    @Test
    void addClientPackageCommand_validData_correctlyConstructed() throws TourPlannerException {
        String[] rawClientPackage = new String[] {"p001", "c001", "KOR", "SQ-KOR"};
        Command addClientPackage = new AddClientPackageCommand(rawClientPackage);
        addClientPackage.setData(clients, flights, tours, clientPackages, ui);
        addClientPackage.execute();

        ClientPackage retrieveClientPackage = ((AddClientPackageCommand) addClientPackage).getClientPackage();
        assertEquals("p001", retrieveClientPackage.getId());
        assertEquals(botuan, retrieveClientPackage.getClient());
        assertEquals(kor, retrieveClientPackage.getTour());
        assertEquals(sqkor, retrieveClientPackage.getFlight());
    }

    @Test
    void addClientPackageCommand_emptyClientPackageList_populatedClientPackageList() throws TourPlannerException {
        String[] rawClientPackage = new String[] {"p001", "c001", "KOR", "SQ-KOR"};
        Command addClientPackage = new AddClientPackageCommand(rawClientPackage);
        addClientPackage.setData(clients, flights, tours, clientPackages, ui);
        addClientPackage.execute();

        assertEquals(populatedClientPackages.getClientPackageCount(), clientPackages.getClientPackageCount());
        for (int i = 0; i < populatedClientPackages.getClientPackageCount(); i++) {
            ClientPackage expected = populatedClientPackages.getClientPackageByIndex(i);
            ClientPackage actual = clientPackages.getClientPackageByIndex(i);
            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getClient(), actual.getClient());
            assertEquals(expected.getTour(), actual.getTour());
            assertEquals(expected.getFlight(), actual.getFlight());
        }
    }

    private ClientList createClientList(Client...clientList) {
        ClientList newClientList = new ClientList();
        for (Client client : clientList) {
            newClientList.add(client);
        }
        return newClientList;
    }

    private FlightList createFlightList(Flight...flightList) {
        FlightList newFlightList = new FlightList();
        for (Flight flight : flightList) {
            newFlightList.add(flight);
        }
        return newFlightList;
    }

    private TourList createTourList(Tour...tourList) {
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
