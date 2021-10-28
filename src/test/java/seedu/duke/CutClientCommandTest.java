package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.commands.clients.CutClientCommand;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;
import seedu.duke.data.Client;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CutClientCommandTest {
    PrintStream previousConsole = System.out;
    ByteArrayOutputStream newConsole = new ByteArrayOutputStream();

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui;

    private ClientList allClients;
    private ClientList clientsWithoutBoTuan;

    @BeforeEach
    public void setUp() {

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});
        Client chengxu = new Client(new String[]{"c004", "ChengXu", "10101010", "demonshaha@mail.com"});

        clients = createClientList(botuan, wayne, chengxu);

        allClients = createClientList(botuan, wayne, chengxu);
        clientsWithoutBoTuan = createClientList(wayne, chengxu);
    }

    @Test
    public void cutClientCommand_validClientId_clientIsDeleted() throws TourPlannerException {
        Command cutClientCommand = new CutClientCommand("c001");
        cutClientCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientCommand.execute();
        assertEquals(clients.getClientCount(), clientsWithoutBoTuan.getClientCount());
        for (int i = 0; i < clientsWithoutBoTuan.getClientCount(); i++) {
            assertEquals(clients.getClient(i), clientsWithoutBoTuan.getClient(i));
        }
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

    private ClientList createClientList(Client... clientList) {
        ClientList newClientList = new ClientList();
        for (Client client : clientList) {
            newClientList.add(client);
        }
        return newClientList;
    }
}