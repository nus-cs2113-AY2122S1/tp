package seedu.duke.commands.clients;

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

public class CutClientCommandTest {
    public static final String CLIENT_ID_1 = "c001";

    public static final String[] CLIENT_BOTUAN = {CLIENT_ID_1, "Bo Tuan", "93338333", "bt@mail.com"};
    public static final String[] CLIENT_WAYNE = {"c002", "Wayne", "56667888", "wen@mail.com"};
    public static final String[] CLIENT_CHENGXU = {"c003", "ChengXu", "10101010", "demonshaha@mail.com"};

    public static final String[] TOUR_JPN = {"JPN", "Japan Basic Tour", "1500.00"};
    public static final String[] TOUR_KOR = {"KOR", "Korea Cultural Tour", "3000.00"};

    public static final String[] FLIGHT_SQJPN = {"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"};
    public static final String[] FLIGHT_SQKOR = {"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"};

    public static final String PACKAGE_ID_1 = "p001";
    public static final String PACKAGE_ID_2 = "p002";
    public static final String CLIENT_ID_WRONG = "1234po0o";

    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    private ClientList allClients;
    private ClientList clientsWithoutBotuan;

    @BeforeEach
    public void setUp() {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        Client botuan = new Client(CLIENT_BOTUAN);
        Client wayne = new Client(CLIENT_WAYNE);
        Client chengxu = new Client(CLIENT_CHENGXU);
        clients = createClientList(botuan, wayne, chengxu);
        allClients = createClientList(botuan, wayne, chengxu);
        clientsWithoutBotuan = createClientList(wayne, chengxu);

        Tour jpn = new Tour(TOUR_JPN);
        Tour kor = new Tour(TOUR_KOR);

        Flight sqjpn = new Flight(FLIGHT_SQJPN);
        Flight sqkor = new Flight(FLIGHT_SQKOR);

        ClientPackage botuanPack1 = new ClientPackage(PACKAGE_ID_1, botuan, jpn, sqjpn);
        ClientPackage botuanPack2 = new ClientPackage(PACKAGE_ID_2, botuan, kor, sqkor);
        clientPackages = createClientPackageList(botuanPack1, botuanPack2);
    }

    @Test
    public void cutClientCommand_validClientId_clientDeleted() throws TourPlannerException {
        assertEquals(clients.getClientCount(), 3);
        Command cutClientCommand = new CutClientCommand(CLIENT_ID_1);
        cutClientCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientCommand.execute();
        assertEquals(clients.getClientCount(), clientsWithoutBotuan.getClientCount());
        for (int i = 0; i < clientsWithoutBotuan.getClientCount(); i++) {
            assertEquals(clients.getClientByIndex(i), clientsWithoutBotuan.getClientByIndex(i));
        }
    }

    @Test
    public void cutClientCommand_validClientId_clientPackagesDeleted() throws TourPlannerException {
        Command cutClientCommand = new CutClientCommand(CLIENT_ID_1);
        cutClientCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientCommand.execute();
        assertEquals(clientPackages.getClientPackageCount(), 0);
    }

    @Test
    public void cutClientCommand_invalidClientId_clientNotFoundMessage() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command cutClientCommand = new CutClientCommand(CLIENT_ID_WRONG);
        cutClientCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = ClientList.CLIENT_NOT_FOUND_MESSAGE;
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    private ClientList createClientList(Client...clientList) {
        ClientList newClientList = new ClientList();
        for (Client client : clientList) {
            newClientList.add(client);
        }
        return newClientList;
    }

    private ClientPackageList createClientPackageList(ClientPackage...clientPackageList) {
        ClientPackageList newClientPackageList = new ClientPackageList();
        for (ClientPackage clientPackage: clientPackageList) {
            newClientPackageList.add(clientPackage);
        }
        return newClientPackageList;
    }
}