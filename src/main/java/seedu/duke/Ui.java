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
    public static final String ADD_CLIENT_MESSAGE = "Client has been added:";
    public static final String ADD_FLIGHT_MESSAGE = "Flight has been added:";
    public static final String ADD_TOUR_MESSAGE = "Tour has been added:";
    public static final String ADD_CLIENT_PACKAGE_MESSAGE = "Client package has been added:";

    public static final String CUT_CLIENT_MESSAGE = "Client has been deleted:";
    public static final String CUT_FLIGHT_MESSAGE = "Flight has been deleted:";
    public static final String CUT_TOUR_MESSAGE = "Tour has been deleted:";
    public static final String CUT_CLIENT_PACKAGE_MESSAGE = "Client package has been deleted:";

    public static final String LIST_NO_MESSAGE = "I'm sorry, there seems to be no ";
    public static final String LIST_MESSAGE = "Here is a list of all ";
    public static final String FIND_FAIL_MESSAGE_LEFT = "I'm sorry, there seems to be no ";
    public static final String FIND_SUCCESS_MESSAGE_LEFT = "This is the ";
    public static final String FIND_MESSAGE_RIGHT = "that matches your search";
    public static final String BYE_MESSAGE = "Thanks for using TourPlanner. Goodbye!";

    public static final String SORT_TOUR_ID_MESSAGE = "Sorted by tour id alphabetically";
    public static final String SORT_TOUR_NAME_MESSAGE = "Sorted by tour name alphabetically";
    public static final String SORT_TOUR_PRICE_MESSAGE = "Sorted by price in ascending order";
    public static final String SORT_CLIENT_ID_MESSAGE = "Sorted by client id alphabetically";
    public static final String SORT_CLIENT_NAME_MESSAGE = "Sorted by client name alphabetically";
    public static final String SORT_FLIGHT_BY_DEPARTURE_MESSAGE = "Sorted by departing flight times";
    public static final String SORT_FLIGHT_BY_ARRIVAL_MESSAGE = "Sorted by returning flight times";
    public static final String SORT_FLIGHT_ID_MESSAGE = "Sorted by flight id alphabetically";

    private static final String FILE_CORRUPT = "TourPlanner can't read your corrupted file.\n"
            + "Please delete your 'data' folder and try again.";

    private static final Scanner in = new Scanner(System.in);

    /**
     * Empty Ui class constructor.
     */
    public Ui() {
    }

    /**
     * Shows a divider to the user.
     */
    public void showLine() {
        show("____________________________________________________________");
    }

    /**
     * Shows a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        String logo = "  _____                  _____  _\n"
                + " |_   _| ___  _  _  ___ |  _  || | ___   _____  _____  ___  ___\n"
                + "   | |  |   || || ||  _||  ___|| ||   \\ |  _  ||  _  || -_||  _|\n"
                + "   | |  | | || || || |  | |    | || |  || | | || | | || |_ | |\n"
                + "   |_|  |___||____||_|  |_|    |_||___|||_| |_||_| |_||___||_|\n";
        String greet = "Hello, Welcome to TourPlanner!\n"
                + "What can I do for you?";
        show(logo);
        show(greet);
        showLine();
    }

    /**
     * Shows a help message to the user, which includes all available commands in TourPlanner.
     */
    public void showHelp() {
        String add = "add: Add information of all data types into the database.\n"
                + "Prefixes can be input in any order.\n"
                + "   Add client: add -c CLIENT_ID /n NAME /cn CONTACT_NUM /m EMAIL\n"
                + "   Add flight: add -f FLIGHT_ID /d DEPART_DESTINATION /r RETURN_DESTINATION\n"
                + "               /dd DEPARTURE_DATETIME /rd RETURN_DATETIME\n"
                + "   Add tour: add -t TOUR_ID /n DEPART_DESTINATION /p TOUR_PRICE\n"
                + "   Add client package: add -p PACKAGE_ID /c CLIENT_ID /t TOUR_ID /f FLIGHT_ID\n\n";
        String list = "list: Shows a list of all available entries of a specific data type, along with their "
                + "respective fields.\n"
                + "   List client: list -c\n"
                + "   List flight: list -f\n"
                + "   List tour: list -t\n"
                + "   List client package: list -p\n\n";
        String cut = "cut: Deletes entry of a certain data type and all client packages corresponding to the entry.\n"
                + "   Cut client: cut -c CLIENT_ID\n"
                + "   Cut flight: cut -f FLIGHT_ID\n"
                + "   Cut tour: cut -t TOUR_ID\n"
                + "   Cut client package: cut -p PACKAGE_ID\n\n";
        String find = "find: Finds specific entry of data type, returns the entry and other relevant information.\n"
                + "   Find client: find -c CLIENT_NAME\n"
                + "   Find flight: find -f FLIGHT_ID\n"
                + "   Find tour: find -t TOUR_ID\n\n";
        String sort = "sort: Sorts entries in the data type based on the criteria.\n "
                + "   Sort client:\n"
                + "      Sort by id: sort -c /id\n"
                + "      Sort by name: sort -c /n\n"
                + "   Sort flight:\n"
                + "      Sort by id: sort -f /id\n"
                + "      Sort by departure date: sort -f /d\n"
                + "      Sort by return date: sort -f /r\n"
                + "   Sort tour:\n"
                + "      Sort by id: sort -t /id\n"
                + "      Sort by name: sort -t /n\n"
                + "      Sort by price: sort -t /p\n\n";
        String bye = "bye: Exits the program.";
        show(add + list + cut + find + sort + bye);
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
     * Ui response to cut command.
     * Checks the type of object before showing a customised message for deleting the specific object.
     *
     * @param object the object that was cut
     */
    public void showCut(Object object) {
        if (object instanceof Client) {
            show(CUT_CLIENT_MESSAGE + "\n" + object);
        } else if (object instanceof Flight) {
            show(CUT_FLIGHT_MESSAGE + "\n" + object);
        } else if (object instanceof Tour) {
            show(CUT_TOUR_MESSAGE + "\n" + object);
        } else if (object instanceof ClientPackage) {
            show(CUT_CLIENT_PACKAGE_MESSAGE + "\n" + object);
        }
    }

    /**
     * Ui response to add command.
     * Checks the type of object before showing a customised message for deleting the specific object.
     *
     * @param object the object that was cut
     */
    public void showAdd(Object object) {
        if (object instanceof Client) {
            show(ADD_CLIENT_MESSAGE + "\n" + object);
        } else if (object instanceof Flight) {
            show(ADD_FLIGHT_MESSAGE + "\n" + object);
        } else if (object instanceof Tour) {
            show(ADD_TOUR_MESSAGE + "\n" + object);
        } else if (object instanceof ClientPackage) {
            show(ADD_CLIENT_PACKAGE_MESSAGE + "\n" + object);
        }
    }

    /**
     * Ui response to ListClientCommand.
     * Shows all clients in the clientList on the terminal.
     *
     * @param clients the ClientList from which the clients are obtained from.
     */
    public void showListClient(ClientList clients) {
        int count = clients.getClientCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "clients");
            return;
        }
        show(LIST_MESSAGE + "clients:");
        for (int i = 1; i <= count; i++) {
            Client currClient = clients.getClientByIndex(i - 1);
            show(i + ". " + currClient + "\n");
        }
        show("Total Clients: " + count);
    }

    /**
     * Ui response to FindClientCommand.
     * Shows specific client(s) based on a susbtring, as well as the client packages they are a part of.
     *
     * @param clients        the ClientList from which the clients are obtained from.
     * @param clientPackages the ClientPackageList from which the client packages of the client are obtained from.
     * @param substring      the substring used to obtain the specific client(s).
     */
    public void showFindClient(ClientList clients, ClientPackageList clientPackages, String substring) {
        String lowercaseName = substring.toLowerCase();
        int foundClients = 0;
        int count = clients.getClientCount();
        for (int i = 0; i < count; i++) {
            Client currClient = clients.getClientByIndex(i);
            if (currClient.getName().toLowerCase().contains(lowercaseName)) {
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

    /**
     * Shows clientPackages that a certain client is a part of.
     *
     * @param currClient     the client which the shown clientPackages must contain.
     * @param clientPackages the ClientPackageList from which the client packages of the client are obtained from.
     */
    private void showFlightTourOfClient(Client currClient, ClientPackageList clientPackages) {
        ArrayList<ClientPackage> clientPackagesWithClient;
        clientPackagesWithClient = clientPackages.getClientPackageByClient(currClient);
        for (ClientPackage clientPackage : clientPackagesWithClient) {
            System.out.println(clientPackage + "\n");
        }
    }

    /**
     * Ui response to ListTourCommand.
     * Shows all tours in the tourList on the terminal.
     *
     * @param tours the TourList from which the tours are obtained from.
     */
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

    /**
     * Ui response to FindTourCommand.
     * Shows specific tour based on an ID, as well as the subscribed clients for said tour.
     *
     * @param tours          the TourList from which the tours are obtained from.
     * @param clientPackages the ClientPackageList from which the subscribers of the tour are obtained from.
     * @param id             the ID used to find the specific tour.
     */
    public void showFindTour(TourList tours, ClientPackageList clientPackages, String id)
            throws TourPlannerException {
        Tour foundTour = tours.getTourById(id);
        if (foundTour != null) {
            show(FIND_SUCCESS_MESSAGE_LEFT + "tour " + FIND_MESSAGE_RIGHT);
            show(foundTour + "\n" + "\n");
            int subbedClients = 0;
            int count = clientPackages.getClientPackageCount();
            show("Subscribed Clients:");
            for (int i = 0; i < count; i++) {
                Tour currTour = clientPackages.getClientPackageByIndex(i).getTour();
                if (currTour.equals(foundTour)) {
                    String currClientName = clientPackages.getClientPackageByIndex(i).getClient().getName();
                    String currClientId = clientPackages.getClientPackageByIndex(i).getClient().getId();
                    show((subbedClients + 1) + ". " + currClientName + " (ID: " + currClientId + ")");
                    subbedClients++;
                }
            }
            show("\n" + "Total Subscribed Clients: " + subbedClients);
        } else {
            show(FIND_FAIL_MESSAGE_LEFT + "tours " + FIND_MESSAGE_RIGHT);
        }
    }

    private boolean checkEmptyTours(TourList tours) {
        int count = tours.getTourCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "tours");
            return true;
        }
        return false;
    }

    /**
     * Ui response to sort tour by id.
     *
     * @param tours         the current list of tours in the database
     * @param sortedTourIds the list of sorted tour codes/ids (by alphabetical order)
     * @throws TourPlannerException if there is no tours that can be found given the tour code
     */
    public void showSortedTourById(TourList tours, ArrayList<String> sortedTourIds)
            throws TourPlannerException {
        if (checkEmptyTours(tours)) {
            return;
        }
        show(SORT_TOUR_ID_MESSAGE);
        int listIndex = 1;
        for (String tourId : sortedTourIds) {
            Tour currTour = tours.getTourById(tourId);
            show(listIndex + ". " + currTour + System.lineSeparator());
            listIndex++;
        }
    }

    /**
     * Ui response to sort tour by price.
     *
     * @param tours            the current list of tours in the database
     * @param sortedTourPrices the list of sorted tour prices (by ascending order)
     * @throws TourPlannerException if there is no tours that can be found given the tour price
     */
    public void showSortedTourByPrice(TourList tours, ArrayList<Float> sortedTourPrices) throws TourPlannerException {
        if (checkEmptyTours(tours)) {
            return;
        }
        tours.initTempArray();
        show(SORT_TOUR_PRICE_MESSAGE);
        int listIndex = 1;
        for (Float tourPrice : sortedTourPrices) {
            Tour currTour = tours.getTourByPrice(tourPrice);
            show(listIndex + ". " + currTour + System.lineSeparator());
            listIndex++;
        }
    }

    /**
     * Ui response to sort tour by name.
     *
     * @param tours           the current list of tours in the database
     * @param sortedTourNames the list of sorted tour names (by alphabetical order)
     * @throws TourPlannerException if there is no tours that can be found given the tour name
     */
    public void showSortedTourByName(TourList tours, ArrayList<String> sortedTourNames) throws TourPlannerException {
        if (checkEmptyTours(tours)) {
            return;
        }
        tours.initTempArray();
        show(SORT_TOUR_NAME_MESSAGE);
        int listIndex = 1;
        for (String tourName : sortedTourNames) {
            Tour currTour = tours.getTourByName(tourName);
            show(listIndex + ". " + currTour + System.lineSeparator());
            listIndex++;
        }
    }

    private boolean checkEmptyClients(ClientList clients) {
        int count = clients.getClientCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "flights");
            return true;
        }
        return false;
    }

    /**
     * Ui response to sort client by id.
     *
     * @param clients         the current list of clients in the database
     * @param sortedClientIds the list of sorted client IDs (by alphabetical order)
     * @throws TourPlannerException if there is no clients that can be found given the client ID
     */
    public void showSortedClientById(ClientList clients, ArrayList<String> sortedClientIds)
            throws TourPlannerException {
        if (checkEmptyClients(clients)) {
            return;
        }
        show(SORT_CLIENT_ID_MESSAGE);
        int listIndex = 1;
        for (String clientId : sortedClientIds) {
            Client currClient = clients.getClientById(clientId);
            show(listIndex + ". " + currClient + System.lineSeparator());
            listIndex++;
        }
    }

    /**
     * Ui response to sort client by name.
     *
     * @param clients           the current list of clients in the database
     * @param sortedClientNames the list of sorted client names (by alphabetical order)
     * @throws TourPlannerException if there is no clients that can be found given the client name
     */
    public void showSortedClientByName(ClientList clients, ArrayList<String> sortedClientNames)
            throws TourPlannerException {
        if (checkEmptyClients(clients)) {
            return;
        }
        clients.initTempArray();
        show(SORT_CLIENT_NAME_MESSAGE);
        int listIndex = 1;
        for (String clientName : sortedClientNames) {
            Client currClient = clients.getClientByName(clientName);
            show(listIndex + ". " + currClient + System.lineSeparator());
            listIndex++;
        }
    }

    private boolean checkEmptyFlights(FlightList flights) {
        int count = flights.getFlightCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "flights");
            return true;
        }
        return false;
    }

    /**
     * Ui response to sort flight by ID.
     *
     * @param flights   the current list of flights in the database
     * @param sortedIds the list of sorted flight IDs (by alphabetical order)
     * @throws TourPlannerException if there is no flights that can be found given the flight ID
     */
    public void showSortedFlightById(FlightList flights, ArrayList<String> sortedIds)
            throws TourPlannerException {
        if (checkEmptyFlights(flights)) {
            return;
        }
        show(SORT_FLIGHT_ID_MESSAGE);
        int listIndex = 1;
        for (String flightId : sortedIds) {
            Flight currFlight = flights.getFlightById(flightId);
            show(listIndex + ". " + currFlight + System.lineSeparator());
            listIndex++;
        }
    }

    /**
     * Ui response to sort flight by return date.
     *
     * @param flights                   the current list of flights in the database
     * @param sortedFlightByArriveDates the list of sorted return dates (by natural order of time)
     * @throws TourPlannerException if there is no flights that can be found given the return date
     */
    public void showSortedFlightByReturn(FlightList flights, ArrayList<String> sortedFlightByArriveDates)
            throws TourPlannerException {
        if (checkEmptyFlights(flights)) {
            return;
        }
        show(SORT_FLIGHT_BY_ARRIVAL_MESSAGE);
        flights.initTempArray();
        int listIndex = 1;
        for (String flightArriveDate : sortedFlightByArriveDates) {
            Flight currFlight = flights.getFlightByReturnDate(flightArriveDate);
            show(listIndex + ". " + currFlight + System.lineSeparator());
            listIndex++;
        }
    }

    /**
     * Ui response to sort flight by depart date.
     *
     * @param flights                      the current list of flights in the database
     * @param sortedFlightByDepartureDates the list of sorted departure dates (by natural order of time)
     * @throws TourPlannerException if there is no flights that can be found given the departure date
     */
    public void showSortedFlightByDeparture(FlightList flights, ArrayList<String> sortedFlightByDepartureDates)
            throws TourPlannerException {
        if (checkEmptyFlights(flights)) {
            return;
        }
        show(SORT_FLIGHT_BY_DEPARTURE_MESSAGE);
        flights.initTempArray();
        int listIndex = 1;
        for (String flightDepartDate : sortedFlightByDepartureDates) {
            Flight currFlight = flights.getFlightByDepartDate(flightDepartDate);
            show(listIndex + ". " + currFlight + System.lineSeparator());
            listIndex++;
        }
    }

    /**
     * Ui response to ListFlightCommand.
     * Shows all flights in the flightList on the terminal.
     *
     * @param flights the FlightList from which the flights are obtained from.
     */
    public void showListFlight(FlightList flights) {
        int count = flights.getFlightCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "flights");
            return;
        }
        show(LIST_MESSAGE + "flights:");
        for (int i = 1; i <= count; i++) {
            Flight currFlight = flights.getFlightByIndex(i - 1);
            show(i + ". " + currFlight + "\n");
        }
        show("Total Flights: " + count);
    }


    /**
     * Ui response to FindFlightCommand.
     * Shows specific flight based on an ID, as well as the passengers for said flight.
     *
     * @param flights        the FlightList from which the flights are obtained from.
     * @param clientPackages the ClientPackageList from which the passengers for the flight are obtained from.
     * @param id             the ID used to find the specific flight.
     */
    public void showFindFlight(FlightList flights, ClientPackageList clientPackages, String id)
            throws TourPlannerException {
        Flight foundFlight = flights.getFlightById(id);
        if (foundFlight != null) {
            show(FIND_SUCCESS_MESSAGE_LEFT + "flight " + FIND_MESSAGE_RIGHT);
            show(foundFlight + "\n" + "\n");
            int passengers = 0;
            int count = clientPackages.getClientPackageCount();
            show("Passengers:");
            for (int i = 0; i < count; i++) {
                Flight currFlight = clientPackages.getClientPackageByIndex(i).getFlight();
                if (currFlight.equals(foundFlight)) {
                    String currClientName = clientPackages.getClientPackageByIndex(i).getClient().getName();
                    String currClientId = clientPackages.getClientPackageByIndex(i).getClient().getId();
                    show((passengers + 1) + ". " + currClientName + " (ID: " + currClientId + ")");
                    passengers++;
                }
            }
            show("\n" + "Total Passengers: " + passengers);
        } else {
            show(FIND_FAIL_MESSAGE_LEFT + "flights " + FIND_MESSAGE_RIGHT);
        }
    }

    /**
     * Ui response to ListClientPackageCommand.
     * Shows all client packages in the clientPackageList on the terminal.
     *
     * @param clientPackages the ClientPackageList from which the client packages are obtained from.
     */
    public void showListClientPackage(ClientPackageList clientPackages) {
        int count = clientPackages.getClientPackageCount();
        if (count == 0) {
            show(LIST_NO_MESSAGE + "packages");
            return;
        }
        show(LIST_MESSAGE + "packages:");
        for (int i = 0; i < count; i++) {
            ClientPackage currPackage = clientPackages.getClientPackageByIndex(i);
            show((i + 1) + ". " + currPackage + "\n" + "\n");
        }
        show("Total Packages: " + count);
    }

    /**
     * Shows an exit message to the user to acknowledge exiting the application.
     */
    public void showBye() {
        show(BYE_MESSAGE);
        showLine();
    }

    /**
     * Shows error message for corrupted storage file.
     */
    public void showFileError() {
        showLine();
        show(FILE_CORRUPT);
        showLine();
    }
}

