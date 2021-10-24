package seedu.duke;

import seedu.duke.data.*;

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

    private static final String SORT_TOUR_ALPHA_MESSAGE = "Sorted by code alphabetically";
    private static final String SORT_TOUR_PRICE_MESSAGE = "Sorted by price in ascending order";

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
     * @param client the client object that was cut
     */
    public void showCut(Client client) {
        show(CUT_MESSAGE + "\n" + client);
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

    public void showFindClient(ClientList clients, String name) {
        int foundClients = 0;
        int count = clients.getClientCount();
        for (int i = 0; i < count; i++) {
            Client currClient = clients.getClient(i);
            if (currClient.getName().equals(name)) {
                if (foundClients == 0) {
                    show(FIND_SUCCESS_MESSAGE_LEFT + "client(s) " + FIND_MESSAGE_RIGHT);
                }
                show((foundClients + 1) + ". " + currClient + "\n");
                foundClients++;
            }
        }
        if (foundClients == 0) {
            show(FIND_FAIL_MESSAGE_LEFT + "client(s) " + FIND_MESSAGE_RIGHT);
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

    public void showFindTour(TourList tours, ClientPackageList packages, String code) {
        Tour foundTour = tours.getTourByCode(code);
        if (foundTour != null) {
            show(FIND_SUCCESS_MESSAGE_LEFT + "tours " + FIND_MESSAGE_RIGHT);
            show(foundTour + "\n" + "\n");
            int subbedClients = 0;
            int count = packages.getPackageCount();
            show("Subscribed Clients:");
            for (int i = 0; i < count; i++) {
                Tour currTour = packages.get(i).getTour();
                if (currTour.equals(foundTour)) {
                    String currClientName = packages.get(i).getClient().getName();
                    show((i + 1) + ". " + currClientName + "\n");
                    subbedClients++;
                }
            }
            show("Total Subscribed Clients: " + subbedClients);
        } else {
            show(FIND_FAIL_MESSAGE_LEFT + "tours " + FIND_MESSAGE_RIGHT);
        }
    }

    public void showSortedTour(TourList tours, String[] sortedCodes) {
        int count = tours.getTourCount();
        show(SORT_TOUR_ALPHA_MESSAGE);
        for (int i = 1; i <= count; i++) {
            String currCode = sortedCodes[i - 1];
            Tour currTour = tours.getTourByCode(currCode);
            show(i + ". " + currTour + "\n");
        }
    }

    public void showSortedTour(TourList tours, Float[] sortedPrices) {
        int count = tours.getTourCount();
        show(SORT_TOUR_PRICE_MESSAGE);
        for (int i = 1; i <= count; i++) {
            float currPrice = sortedPrices[i - 1];
            for (int j = 1; j <= count; j++) {
                Tour currTour = tours.getTourByIndex(i - 1);
                if (currTour.getPrice() == currPrice) {
                    show(i + ". " + currTour + "\n");
                    break;
                }
            }
        }
    }

    //Flight Functions
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
            Flight currFlight = flights.getFlight(i - 1);
            show(i + ". " + currFlight + "\n");
        }
        show("Total Flights: " + count);
    }

    public void showFindFlight(FlightList flights, ClientPackageList packages, String code) {
        Flight foundFlight = flights.getFlight(code);
        if (foundFlight != null) {
            show(FIND_SUCCESS_MESSAGE_LEFT + "flights " + FIND_MESSAGE_RIGHT);
            show(foundFlight + "\n" + "\n");
            int passengers = 0;
            int count = packages.getPackageCount();
            show("Passengers:");
            for (int i = 0; i < count; i++) {
                Flight currFlight = packages.get(i).getFlight();
                if (currFlight.equals(foundFlight)) {
                    String currClientName = packages.get(i).getClient().getName();
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

    public void showListClientPackage(ClientPackageList clientPackageList) {
        int count = clientPackageList.getPackageCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "packages");
            return;
        }
        show(LIST_MESSAGE + "packages:");
        for (int i = 0; i < count; i++) {
            ClientPackage currPackage = clientPackageList.get(i);
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

