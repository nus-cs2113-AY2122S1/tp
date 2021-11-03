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
    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    private ClientList allClients;
    private ClientList clientsWithoutBoTuan;

    @BeforeEach
    public void setUp() {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
        Client chengxu = new Client(new String[]{"c003", "ChengXu", "10101010", "demonshaha@mail.com"});
        clients = createClientList(botuan, wayne, chengxu);
        allClients = createClientList(botuan, wayne, chengxu);
        clientsWithoutBoTuan = createClientList(wayne, chengxu);

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});

        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"});
        Flight sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"});

        ClientPackage botuanPack1 = new ClientPackage("p001", botuan, jpn, sqjpn);
        ClientPackage botuanPack2 = new ClientPackage("p002", botuan, kor, sqkor);
        clientPackages = createClientPackageList(botuanPack1, botuanPack2);
    }

    @Test
    public void cutClientCommand_validClientId_clientDeleted() throws TourPlannerException {
        assertEquals(clients.getClientCount(), 3);
        Command cutClientCommand = new CutClientCommand("c001");
        cutClientCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientCommand.execute();
        assertEquals(clients.getClientCount(), clientsWithoutBoTuan.getClientCount());
        for (int i = 0; i < clientsWithoutBoTuan.getClientCount(); i++) {
            assertEquals(clients.getClientByIndex(i), clientsWithoutBoTuan.getClientByIndex(i));
        }
    }

    @Test
    public void cutClientCommand_validClientId_clientPackagesDeleted() throws TourPlannerException {
        Command cutClientCommand = new CutClientCommand("c001");
        cutClientCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientCommand.execute();
        assertEquals(clientPackages.getClientPackageCount(), 0);
    }

    @Test
    public void cutClientCommand_invalidClientId_clientNotFoundMessage() throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command cutClientCommand = new CutClientCommand("1234po0o");
        cutClientCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientCommand.execute();

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Client cannot be found. Please try another client ID";
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