package seedu.duke;

import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.ClientPackageList;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String ADD_MESSAGE = " has been added:";
    private static final String LIST_NO_MESSAGE = "I'm sorry, there seems to be no ";
    private static final String LIST_MESSAGE = "Here is a list of all ";
    private static final String FIND_FAIL_MESSAGE_LEFT = "I'm sorry, there seems to be no ";
    private static final String FIND_SUCCESS_MESSAGE_LEFT = "This is the ";
    private static final String FIND_MESSAGE_RIGHT = "that matches your search";
    private static final String BYE_MESSAGE = "Thanks for using TourPlanner. Goodbye!";

    private static final String CLEAR_MESSAGE = "All clients have been deleted";
    private static final String CUT_MESSAGE = "Client has been deleted:";

    private static final String SORT_TOUR_ID_MESSAGE = "Sorted by tour id alphabetically";
    private static final String SORT_TOUR_PRICE_MESSAGE = "Sorted by price in ascending order";
    private static final String SORT_CLIENT_ID_MESSAGE = "Sorted by client id alphabetically";

    private static Scanner in = new Scanner(System.in);

    /**
     * Empty Ui class constructor.
     */
    public Ui() {
        ;
    }

    /**
     * Prints a divider to the text Ui.
     */
    public void showLine() {
        show("____________________________________________________________");
    }

    /**
     * Prints a welcome message to the user on the text Ui.
     */
    public void showWelcome() {
        showLine();
        String logo = " █████  ███  █   █ ████  ████  █      ████  █    █ █    █ █████ ████\n"
                + "   █   █   █ █   █ █   █ █   █ █     █    █ ██   █ ██   █ █     █   █\n"
                + "   █   █   █ █   █ █████ ████  █     ██████ █ █  █ █ █  █ █████ █████\n"
                + "   █   █   █ █   █ █  █  █     █     █    █ █  █ █ █  █ █ █     █  █\n"
                + "   █    ███   ███  █   █ █     █████ █    █ █   ██ █   ██ █████ █   █\n";
        String greet = "Hello, Welcome to TourPlanner!\n"
                + "What can I do for you?";
        show(logo);
        show(greet);
        showLine();
    }

    /**
     * Prompts for user's input and read's the text entered by the user.
     *
     * @return full input entered by the user
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Outputs to the CLI the text to show the user.
     * Creates a newline as well.
     *
     * @param textToShow the output text intended for the user
     */
    public void show(String textToShow) {
        System.out.print(textToShow + System.lineSeparator());
    }

    /**
     * Ui response to clear client list command.
     */
    public void showClear() {
        show(CLEAR_MESSAGE);
    }

    /**
     * Ui response to cut client command.
     *
     * @param object the object that was cut
     */
    public void showCut(Object object) {
        show(CUT_MESSAGE + "\n" + object);
    }

    /**
     * Ui response to add client command.
     *
     * @param client the client that was just added
     */

    //Client Functions
    public void showAddClient(Client client) {
        show("Client" + ADD_MESSAGE + "\n" + client);
    }

    public void showListClient(ClientList clients) {
        int count = clients.getClientCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "clients");
            return;
        }
        show(LIST_MESSAGE + "clients:");
        for (int i = 1; i <= count; i++) {
            Client currClient = clients.getClient(i - 1);
            show(i + ". " + currClient + "\n");
        }
        show("Total Clients: " + count);
    }

    public void showFindClient(ClientList clients, ClientPackageList clientPackages, String name) {
        int foundClients = 0;
        int count = clients.getClientCount();
        for (int i = 0; i < count; i++) {
            Client currClient = clients.getClient(i);
            if (currClient.getName().equals(name)) {
                if (foundClients == 0) {
                    show(FIND_SUCCESS_MESSAGE_LEFT + "client(s) " + FIND_MESSAGE_RIGHT);
                }
                show((foundClients + 1) + ". " + currClient + "\n");
                showFlightTourOfClient(currClient, clientPackages);
                foundClients++;
            }
        }
        if (foundClients == 0) {
            show(FIND_FAIL_MESSAGE_LEFT + "client(s) " + FIND_MESSAGE_RIGHT);
        }
    }

    private void showFlightTourOfClient(Client currClient, ClientPackageList clientPackages) {
        ArrayList<ClientPackage> clientPackagesWithClient;
        clientPackagesWithClient = clientPackages.getClientPackageByClient(currClient);
        for (ClientPackage clientPackage: clientPackagesWithClient) {
            System.out.println(clientPackage + "\n");
        }
    }


    //Tour Functions
    public void showAddTour(Tour tour) {
        show("Tour" + ADD_MESSAGE + "\n" + tour);
    }

    public void showListTour(TourList tours) {
        int count = tours.getTourCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "tours");
            return;
        }
        show(LIST_MESSAGE + "tours:");
        for (int i = 1; i <= count; i++) {
            Tour currTour = tours.getTourByIndex(i - 1);
            show(i + ". " + currTour + "\n");
        }
        show("Total Tours: " + count);
    }

    public void showFindTour(TourList tours, ClientPackageList clientPackages, String code)
            throws TourPlannerException {
        Tour foundTour = tours.getTourByCode(code);
        if (foundTour != null) {
            show(FIND_SUCCESS_MESSAGE_LEFT + "tours " + FIND_MESSAGE_RIGHT);
            show(foundTour + "\n" + "\n");
            int subbedClients = 0;
            int count = clientPackages.getClientPackageCount();
            show("Subscribed Clients:");
            for (int i = 0; i < count; i++) {
                Tour currTour = clientPackages.get(i).getTour();
                if (currTour.equals(foundTour)) {
                    String currClientName = clientPackages.get(i).getClient().getName();
                    show((i + 1) + ". " + currClientName + "\n");
                    subbedClients++;
                }
            }
            show("Total Subscribed Clients: " + subbedClients);
        } else {
            show(FIND_FAIL_MESSAGE_LEFT + "tours " + FIND_MESSAGE_RIGHT);
        }
    }

    public void showSortedTourById(TourList tours, ArrayList<String> sortedTourCodes)
            throws TourPlannerException {
        show(SORT_TOUR_ID_MESSAGE);
        int listIndex = 1;
        for (String tourCode : sortedTourCodes) {
            Tour currTour = tours.getTourByCode(tourCode);
            show(listIndex + ". " + currTour + System.lineSeparator());
            listIndex++;
        }
    }

    public void showSortedTourByPrice(TourList tours, ArrayList<Float> sortedTourPrices) {
        tours.initTempArray();
        show(SORT_TOUR_PRICE_MESSAGE);
        int listIndex = 1;
        for (Float tourPrice : sortedTourPrices) {
            Tour currTour = tours.getTourByPrice(tourPrice);
            show(listIndex + ". " + currTour + System.lineSeparator());
            listIndex++;
        }
    }


    public void showSortedClientById(ClientList clients, ArrayList<String> sortedClientIds)
            throws TourPlannerException {
        show(SORT_CLIENT_ID_MESSAGE);
        int listIndex = 1;
        for (String clientId : sortedClientIds) {
            Client currClient = clients.getClientById(clientId);
            show(listIndex + ". " + currClient + System.lineSeparator());
            listIndex++;
        }
    }

    public void showSortedClientByName(ClientList clients, ArrayList<String> sortedClientNames)
            throws TourPlannerException {
        clients.initTempArray();
        show(SORT_CLIENT_ID_MESSAGE);
        int listIndex = 1;
        for (String clientName : sortedClientNames) {
            Client currClient = clients.getClientByName(clientName);
            show(listIndex + ". " + currClient + System.lineSeparator());
            listIndex++;
        }
    }

    public void showAddFlight(Flight flight) {
        show("Flight" + ADD_MESSAGE + "\n" + flight);
    }

    public void showListFlight(FlightList flights) {
        int count = flights.getFlightCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "flights");
            return;
        }
        show(LIST_MESSAGE + "flights:");
        for (int i = 1; i <= count; i++) {
            Flight currFlight = flights.getFlightById(i - 1);
            show(i + ". " + currFlight + "\n");
        }
        show("Total Flights: " + count);
    }

    public void showFindFlight(FlightList flights, ClientPackageList clientPackages, String code)
            throws TourPlannerException {
        Flight foundFlight = flights.getFlightById(code);
        if (foundFlight != null) {
            show(FIND_SUCCESS_MESSAGE_LEFT + "flights " + FIND_MESSAGE_RIGHT);
            show(foundFlight + "\n" + "\n");
            int passengers = 0;
            int count = clientPackages.getClientPackageCount();
            show("Passengers:");
            for (int i = 0; i < count; i++) {
                Flight currFlight = clientPackages.get(i).getFlight();
                if (currFlight.equals(foundFlight)) {
                    String currClientName = clientPackages.get(i).getClient().getName();
                    show((i + 1) + ". " + currClientName + "\n");
                    passengers++;
                }
            }
            show("Total Passengers: " + passengers);
        } else {
            show(FIND_FAIL_MESSAGE_LEFT + "flights " + FIND_MESSAGE_RIGHT);
        }
    }

    //ClientPackage Functions
    public void showAddClientPackage(ClientPackage clientPackage) {
        show("Client Package" + ADD_MESSAGE + "\n" + clientPackage);
    }

    public void showListClientPackage(ClientPackageList clientPackages) {
        int count = clientPackages.getClientPackageCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "packages");
            return;
        }
        show(LIST_MESSAGE + "packages:");
        for (int i = 0; i < count; i++) {
            ClientPackage currPackage = clientPackages.get(i);
            show((i + 1) + ". " + currPackage + "\n" + "\n");
        }
        show("Total Packages:" + count);
    }

    public void showFindClientPackage(ClientPackageList packages, int index) {
        ClientPackage foundPackage = packages.get(index - 1);
        if (foundPackage != null) {
            show(FIND_SUCCESS_MESSAGE_LEFT + "packages " + FIND_MESSAGE_RIGHT);
            show(foundPackage + "\n" + "\n");
        } else {
            show(FIND_FAIL_MESSAGE_LEFT + "packages " + FIND_MESSAGE_RIGHT);
        }
    }

    /**
     * Prints an exit message to the text Ui to acknowledge exiting the application.
     */

    public void showBye() {
        show(BYE_MESSAGE);
        showLine();
    }


}

