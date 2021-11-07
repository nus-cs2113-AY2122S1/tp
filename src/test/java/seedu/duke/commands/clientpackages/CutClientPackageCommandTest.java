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
    public static final String[] CLIENT_BOTUAN = {"c001", "Bo Tuan", "93338333", "bt@mail.com"};
    public static final String[] CLIENT_WAYNE = {"c002", "Wayne", "56667888", "wen@mail.com"};

    public static final String[] TOUR_JPN = {"JPN", "Japan Basic Tour", "1500.00"};
    public static final String[] TOUR_KOR = {"KOR", "Korea Cultural Tour", "3000.00"};

    public static final String[] FLIGHT_SQJPN = {"SQ-JPN", "JPN", "SG", "20/10/21 18:00", "21/10/21 03:00"};
    public static final String[] FLIGHT_SQKOR = {"SQ-KOR", "KOR", "SG", "23/10/2021 18:00", "30/10/2021 03:00"};

    public static final String PACKAGE_ID_1 = "p001";
    public static final String PACKAGE_ID_2 = "p002";
    public static final String PACKAGE_ID_3 = "p003";
    public static final String PACKAGE_ID_WRONG = "1234po0o";

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

        Client botuan = new Client(CLIENT_BOTUAN);
        Client wayne = new Client(CLIENT_WAYNE);

        Tour jpn = new Tour(TOUR_JPN);
        Tour kor = new Tour(TOUR_KOR);

        Flight sqjpn = new Flight(FLIGHT_SQJPN);
        Flight sqkor = new Flight(FLIGHT_SQKOR);

        ClientPackage botuanPack1 = new ClientPackage(PACKAGE_ID_1, botuan, jpn, sqjpn);
        ClientPackage botuanPack2 = new ClientPackage(PACKAGE_ID_2, botuan, kor, sqkor);
        ClientPackage waynePack = new ClientPackage(PACKAGE_ID_3, wayne, jpn, sqkor);
        clientPackages = createClientPackageList(botuanPack1, botuanPack2, waynePack);

        allClientPackages = createClientPackageList(botuanPack1, botuanPack2, waynePack);
        clientPackagesWithoutP002 = createClientPackageList(botuanPack1, waynePack);
    }

    @Test
    public void cutClientPackageCommand_validClientPackageId_clientPackageDeleted() throws TourPlannerException {
        assertEquals(clientPackages.getClientPackageCount(), 3);
        Command cutClientPackageCommand = new CutClientPackageCommand(PACKAGE_ID_2);
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

        Command cutClientPackageCommand = new CutClientPackageCommand(PACKAGE_ID_WRONG);
        cutClientPackageCommand.setData(clients, flights, tours, clientPackages, ui);
        cutClientPackageCommand.execute();

        for (int i = 0; i < allClientPackages.getClientPackageCount(); i++) {
            assertEquals(clientPackages.getClientPackageByIndex(i),
                    allClientPackages.getClientPackageByIndex(i));
        }

        previousConsole.println(newConsole.toString());
        System.setOut(previousConsole);
        String expectedString = ClientPackageList.CLIENTPACKAGE_NOT_FOUND_MESSAGE;
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
