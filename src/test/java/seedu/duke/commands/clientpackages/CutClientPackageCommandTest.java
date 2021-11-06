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

public class CutClientPackageCommandTest {
    PrintStream previousConsole;
    ByteArrayOutputStream newConsole;

    private ClientList clients;
    private FlightList flights;
    private TourList tours;
    private ClientPackageList clientPackages;
    private Ui ui = new Ui();

    private ClientPackageList allClientPackages;
    private ClientPackageList clientPackagesWithoutP002;

    @BeforeEach
    public void setUp() {
        previousConsole = System.out;
        newConsole = new ByteArrayOutputStream();

        Client botuan = new Client(new String[]{"c001", "Bo Tuan", "93338333", "bt@mail.com"});
        Client wayne = new Client(new String[]{"c002", "Wayne", "56667888", "wen@mail.com"});

        Tour jpn = new Tour(new String[]{"JPN", "Japan Basic Tour", "1500.00"});
        Tour kor = new Tour(new String[]{"KOR", "Korea Cultural Tour", "3000.00"});

        Flight sqjpn = new Flight(new String[]{"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"});
        Flight sqkor = new Flight(new String[]{"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"});

        ClientPackage botuanPack1 = new ClientPackage("p001", botuan, jpn, sqjpn);
        ClientPackage botuanPack2 = new ClientPackage("p002", botuan, kor, sqkor);
        ClientPackage waynePack = new ClientPackage("p003", wayne, jpn, sqkor);
        clientPackages = createClientPackageList(botuanPack1, botuanPack2, waynePack);

        allClientPackages = createClientPackageList(botuanPack1, botuanPack2, waynePack);
        clientPackagesWithoutP002 = createClientPackageList(botuanPack1, waynePack);
    }

    @Test
    public void cutClientPackageCommand_validClientPackageId_clientPackageDeleted() throws TourPlannerException {
        assertEquals(clientPackages.getClientPackageCount(), 3);
        Command cutClientPackageCommand = new CutClientPackageCommand("p002");
        cutClientPackageCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientPackageCommand.execute();
        assertEquals(clientPackages.getClientPackageCount(), clientPackagesWithoutP002.getClientPackageCount());
        for (int i = 0; i < clientPackagesWithoutP002.getClientPackageCount(); i++) {
            assertEquals(clientPackages.getClientPackageByIndex(i),
                    clientPackagesWithoutP002.getClientPackageByIndex(i));
        }
    }

    @Test
    public void cutClientPackageCommand_invalidClientPackageId_clientPackageNotFoundMessage()
            throws TourPlannerException {
        System.setOut(new PrintStream(newConsole));

        Command cutClientPackageCommand = new CutClientPackageCommand("1234po0o");
        cutClientPackageCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientPackageCommand.execute();

        for (int i = 0; i < allClientPackages.getClientPackageCount(); i++) {
            assertEquals(clientPackages.getClientPackageByIndex(i),
                    allClientPackages.getClientPackageByIndex(i));
        }

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = "Client Package cannot be found. Please try another client package ID";
        String actualString = newConsole.toString().trim().replace("\r\n", "\n");
        assertEquals(expectedString, actualString);
    }

    private ClientPackageList createClientPackageList(ClientPackage...clientPackageList) {
        ClientPackageList newClientPackageList = new ClientPackageList();
        for (ClientPackage clientPackage: clientPackageList) {
            newClientPackageList.add(clientPackage);
        }
        return newClientPackageList;
    }
}
