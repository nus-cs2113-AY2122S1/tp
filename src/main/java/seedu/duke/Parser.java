package seedu.duke;

import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.clientpackages.AddClientPackageCommand;
import seedu.duke.commands.clientpackages.CutClientPackageCommand;
import seedu.duke.commands.clientpackages.ListClientPackageCommand;

import seedu.duke.commands.clients.AddClientCommand;
import seedu.duke.commands.clients.CutClientCommand;
import seedu.duke.commands.clients.FindClientCommand;
import seedu.duke.commands.clients.ListClientCommand;
import seedu.duke.commands.clients.SortClientCommand;

import seedu.duke.commands.flights.AddFlightCommand;
import seedu.duke.commands.flights.CutFlightCommand;
import seedu.duke.commands.flights.FindFlightCommand;
import seedu.duke.commands.flights.SortFlightCommand;
import seedu.duke.commands.flights.ListFlightCommand;

import seedu.duke.commands.tours.SortTourCommand;
import seedu.duke.commands.tours.CutTourCommand;
import seedu.duke.commands.tours.AddTourCommand;
import seedu.duke.commands.tours.FindTourCommand;
import seedu.duke.commands.tours.ListTourCommand;

import seedu.duke.data.Client;
import seedu.duke.data.Flight;
import seedu.duke.data.Tour;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * Sense-makes the inputs given and distributes the information to other parts of the program.
 */
public class Parser {

    private static final String CLIENT_IDENTIFIER = "-c";
    private static final String TOUR_IDENTIFIER = "-t";
    private static final String FLIGHT_IDENTIFIER = "-f";
    private static final String PACKAGE_IDENTIFIER = "-p";
    private static final String CLIENT_PREFIX = "/c";
    private static final String TOUR_PREFIX = "/t";
    private static final String FLIGHT_PREFIX = "/f";
    private static final String FLIGHT_DEPARTURE_PREFIX = "/d";
    private static final String FLIGHT_RETURN_PREFIX = "/r";
    private static final String FLIGHT_DEPARTURE_DATE_PREFIX = "/dd";
    private static final String FLIGHT_RETURN_DATE_PREFIX = "/rd";
    public static final String ERROR_INVALID_INPUT = "Invalid input! Please enter a valid command.";
    public static final String ERROR_MISSING_IDENTIFIER =
            "Missing command filter! Please enter a command with this format: COMMAND -FILTER DATA \n"
                    + "Example: find -t TOUR_ID";
    public static final String ERROR_EXTRA_INPUT = "Extra input! Refrain from doing so.";
    public static final String ERROR_DUPLICATE_PREFIXES = "Duplicate prefixes! Please try again.";
    public static final String ERROR_MISSING_PREFIXES
            = "Missing prefixes! Did you miss out some fields? Please try again.";
    public static final String ERROR_MISSING_NAME_ID = "Missing name/id! Please try again.";
    private static final String ERROR_MISSING_FIELDS = "Please do not leave your fields empty!";
    public static final String ERROR_EMAIL_FORMAT_WRONG = "TourPlanner has detected possible erroneous email! "
            + "Are you sure about this entry? \n";
    public static final String ERROR_CONTACT_NUMBER_WRONG =
            "TourPlanner has detected possible erroneous contact number! (characters other than numbers)"
                    + "Are you sure about this entry? \n";
    public static final String ERROR_SHORT_CONTACT_NUMBER =
            "TourPlanner detected that the given contact number is too short (< 8)! Are you sure about this entry? \n";
    private static final String ERROR_LONG_CONTACT_NUMBER =
            "TourPlanner detected that the given contact number is too long (> 8)! Are you sure about this entry? \n";
    public static final String ERROR_FLIGHT_TIME_FORMAT = "TourPlanner detected wrong date time entry formatting! \n"
            + "Please input your date-times with the following format: d/M/yy HH:mm";
    public static final String ERROR_FLIGHT_TIME_INVERT = "TourPlanner detected erroneous flight time entry!";
    public static final String ERROR_PRICE_FORMAT = "Invalid Price";
    public static final String TOUR_NAME_PREFIX = "/n";
    public static final String TOUR_PRICE_PREFIX = "/p";
    public static final String CLIENT_NAME_PREFIX = "/n";
    public static final String CLIENT_CONTACT_NUMBER_PREFIX = "/cn";
    public static final String CLIENT_EMAIL_PREFIX = "/m";
    public static final int IDENTIFIER_INDEX = 0;
    public static final int ARGS_INDEX = 1;
    public static final int COMMAND_INDEX = 0;
    public static final int PARAMS_INDEX = 1;
    public static final int MAX_VALUE_ARRAY_SIZE = 5;
    public static final String EMPTY_STRING = "";

    /**
     * Parses user's input into command to execute.
     *
     * @param input full user's input string
     * @return the command parsed from user's input
     * @throws TourPlannerException if there are missing fields, duplicated prefixes, missing prefixes and other general
     *                              parsing errors.
     */
    public static Command parse(String input) throws TourPlannerException {
        String[] commandAndParams = splitCommandString(input, " ");
        String command = commandAndParams[COMMAND_INDEX];
        String params = commandAndParams[PARAMS_INDEX];

        switch (command) {
        case "bye":
            if (!params.equals(EMPTY_STRING)) {
                throw new TourPlannerException(ERROR_EXTRA_INPUT);
            }
            return new ByeCommand();
        case "add":
            return parseAdd(params);
        case "cut":
            return parseCut(params);
        case "list":
            return parseList(params);
        case "find":
            return parseFind(params);
        case "sort":
            return parseSort(params);
        case "help":
            if (!params.equals(EMPTY_STRING)) {
                throw new TourPlannerException(ERROR_EXTRA_INPUT);
            }
            return new HelpCommand();
        default:
            throw new TourPlannerException(ERROR_INVALID_INPUT);
        }
    }

    /**
     * Separates command word and arguments.
     *
     * @param input     full user's input string
     * @param separator separator between command and argument/params strings
     * @return the array containing command and argument/params strings
     */
    private static String[] splitCommandString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    /**
     * Extracts the indexes for prefixes and put into a map that sorts the list by the natural ordering of the keys.
     *
     * @param argString full user's argument string
     * @return the treemap with prefix index as the key and the corresponding prefix as the value
     * @throws TourPlannerException if there are missing fields or missing prefixes
     */
    private static TreeMap<Integer, String> extractPrefixIndexes(String argString, String identifier)
            throws TourPlannerException {

        List<String> prefixes = generatePrefixesFromIdentifier(identifier);
        if (!containAllPrefixes(argString, prefixes)) {
            throw new TourPlannerException(ERROR_MISSING_PREFIXES);
        }

        TreeMap<Integer, String> prefixIndexes = new TreeMap<>();
        prefixIndexes.put(0, "");
        prefixes.forEach((prefix) -> {
            int prefixIndex = argString.indexOf(prefix);
            prefixIndexes.put(prefixIndex, prefix);
        });

        int expectedNumberOfPrefixIndexes = generateExpectedNumberOfPrefixIndexes(identifier);
        boolean hasUniquePrefixIndexes = prefixIndexes.size() == expectedNumberOfPrefixIndexes;

        if (!hasUniquePrefixIndexes) {
            throw new TourPlannerException(ERROR_MISSING_NAME_ID);
        }
        return prefixIndexes;
    }

    private static int generateExpectedNumberOfPrefixIndexes(String identifier) {
        int numberOfPrefixIndexes = 0;
        switch (identifier) {
        case CLIENT_IDENTIFIER:
            numberOfPrefixIndexes = 4;
            break;
        case PACKAGE_IDENTIFIER:
            numberOfPrefixIndexes = 4;
            break;
        case TOUR_IDENTIFIER:
            numberOfPrefixIndexes = 3;
            break;
        case FLIGHT_IDENTIFIER:
            numberOfPrefixIndexes = 5;
            break;
        default:
            break;
        }
        return numberOfPrefixIndexes;
    }

    /**
     * Extract values from user's input command in a sorted fashion.
     *
     * @param prefixIndexes the treemap with prefix index as the key and the corresponding prefix as the value
     * @param argString     full user's argument string
     * @param identifier    specific identifier to determine specific data type (client, flight, tour, package)
     * @return the array containing all extracted values in a sorted fashion
     * @throws TourPlannerException if there are duplicate prefixes found
     */
    private static ArrayList<String> extractValuesIntoArray(TreeMap<Integer, String> prefixIndexes,
                                                            String argString, String identifier)
            throws TourPlannerException {
        ArrayList<String> extractedValues = new ArrayList<>();
        initialiseArrayList(extractedValues);
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<String> prefixes = new ArrayList<>();
        for (Map.Entry<Integer, String> prefixIndex : prefixIndexes.entrySet()) {
            indexes.add(prefixIndex.getKey());
            prefixes.add(prefixIndex.getValue());
        }

        for (int i = 0; i < indexes.size() - 1; i++) {
            int previousIndex = indexes.get(i);
            int nextIndex = indexes.get(i + 1);
            String prefix = prefixes.get(i);
            String value = extractValue(argString, prefix, previousIndex, nextIndex, identifier);
            int inputIndex = obtainArrayIndex(prefix, identifier);
            extractedValues.set(inputIndex, value);
        }

        String finalPrefix = prefixes.get(indexes.size() - 1);
        int finalIndex = indexes.get(indexes.size() - 1);

        int inputIndex = obtainArrayIndex(finalPrefix, identifier);
        String value = extractValue(argString, finalPrefix, finalIndex, argString.length(), identifier);
        extractedValues.set(inputIndex, value);
        return extractedValues;
    }

    /**
     * Initialise an empty array list of size 5 - max array size for values.
     *
     * @param extractedValues empty extracted values array to be initialised with empty string values
     */
    private static void initialiseArrayList(ArrayList<String> extractedValues) {
        for (int i = 0; i < MAX_VALUE_ARRAY_SIZE; i++) {
            extractedValues.add("");
        }
    }

    /**
     * Extract value from a substring of the user's argument string, according to prefix.
     *
     * @param argString  full user's argument string
     * @param prefix     prefix of value to be extracted
     * @param startIndex start index of substring
     * @param endIndex   end index of substring
     * @param identifier specific identifier to determine specific data type (client, flight, tour, package)
     * @return value corresponding to prefix given
     * @throws TourPlannerException if there are duplicate prefixes found
     */
    private static String extractValue(String argString, String prefix, int startIndex, int endIndex, String identifier)
            throws TourPlannerException {
        List<String> prefixes = generatePrefixesFromIdentifier(identifier);
        String unformattedSubstring = argString.substring(startIndex, endIndex).trim();
        String value = unformattedSubstring.replaceFirst(prefix, "").trim();

        if (value.equals(EMPTY_STRING)) {
            throw new TourPlannerException(ERROR_MISSING_FIELDS);
        }
        for (String pf : prefixes) {
            boolean hasDuplicatePrefix = value.contains(pf);
            if (hasDuplicatePrefix) {
                throw new TourPlannerException(ERROR_DUPLICATE_PREFIXES);
            }
        }
        return value;
    }

    private static List<String> generatePrefixesFromIdentifier(String identifier) throws TourPlannerException {
        List<String> prefixes;
        switch (identifier) {
        case CLIENT_IDENTIFIER:
            prefixes = Arrays.asList(CLIENT_NAME_PREFIX, CLIENT_CONTACT_NUMBER_PREFIX, CLIENT_EMAIL_PREFIX);
            break;
        case TOUR_IDENTIFIER:
            prefixes = Arrays.asList(TOUR_NAME_PREFIX, TOUR_PRICE_PREFIX);
            break;
        case FLIGHT_IDENTIFIER:
            prefixes = Arrays.asList(FLIGHT_DEPARTURE_PREFIX, FLIGHT_RETURN_PREFIX,
                    FLIGHT_DEPARTURE_DATE_PREFIX, FLIGHT_RETURN_DATE_PREFIX);
            break;
        case PACKAGE_IDENTIFIER:
            prefixes = Arrays.asList(CLIENT_PREFIX, TOUR_PREFIX, FLIGHT_PREFIX);
            break;
        default:
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
        }
        return prefixes;
    }

    /**
     * Obtains array index that corresponds to the prefix given.
     *
     * @param prefix prefix of value extracted
     * @return array index of values according to prefix
     */
    private static int obtainArrayIndex(String prefix, String identifier) {
        int index;

        switch (identifier) {
        case CLIENT_IDENTIFIER:
            index = obtainClientArrayIndex(prefix);
            break;
        case TOUR_IDENTIFIER:
            index = obtainTourArrayIndex(prefix);
            break;
        case FLIGHT_IDENTIFIER:
            index = obtainFlightArrayIndex(prefix);
            break;
        case PACKAGE_IDENTIFIER:
            index = obtainPackageArrayIndex(prefix);
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    /**
     * Returns a value's insertion index into the ClientPackage input array, based on the given prefix.
     * ClientPackage array: (Package ID, Client id, Tour id, Flight id)
     *
     * @param prefix prefix of value extracted
     * @return respective insertion index
     */
    private static int obtainPackageArrayIndex(String prefix) {
        int index;

        switch (prefix) {
        case CLIENT_PREFIX:
            index = 1;
            break;
        case TOUR_PREFIX:
            index = 2;
            break;
        case FLIGHT_PREFIX:
            index = 3;
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    /**
     * Returns a value's insertion index into the Flight input array, based on the given prefix.
     * Flight array: (ID, Departure destination, Return destination, Departure Date,Return Date)
     *
     * @param prefix prefix of value extracted
     * @return respective insertion index
     */
    private static int obtainFlightArrayIndex(String prefix) {
        int index;

        switch (prefix) {
        case FLIGHT_DEPARTURE_PREFIX:
            index = 1;
            break;
        case FLIGHT_RETURN_PREFIX:
            index = 2;
            break;
        case FLIGHT_DEPARTURE_DATE_PREFIX:
            index = 3;
            break;
        case FLIGHT_RETURN_DATE_PREFIX:
            index = 4;
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    /**
     * Returns a value's insertion index into the Tour input array, based on the given prefix.
     * Tour array: (ID, Name, Price)
     *
     * @param prefix prefix of value extracted
     * @return respective insertion index
     */
    private static int obtainTourArrayIndex(String prefix) {
        int index;

        switch (prefix) {
        case TOUR_NAME_PREFIX:
            index = 1;
            break;
        case TOUR_PRICE_PREFIX:
            index = 2;
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    /**
     * Returns a value's insertion index into the Client input array, based on the given prefix.
     * Client array: (ID, Name, Contact Number, eMail)
     *
     * @param prefix prefix of value extracted
     * @return respective insertion index
     */
    private static int obtainClientArrayIndex(String prefix) {
        int index;

        switch (prefix) {
        case CLIENT_NAME_PREFIX:
            index = 1;
            break;
        case CLIENT_CONTACT_NUMBER_PREFIX:
            index = 2;
            break;
        case CLIENT_EMAIL_PREFIX:
            index = 3;
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    /**
     * Returns true if all prefixes are present in add command's argument string.
     *
     * @param argString full user's argument string
     * @return true if all prefixes are present in add command's argument string
     */
    private static boolean containAllPrefixes(String argString, List<String> prefixList) {

        String[] splitBySpaces = argString.trim().split("\\s+");
        String[] prefixes = prefixList.toArray(new String[prefixList.size()]);
        for (String prefix : prefixes) {
            boolean containPrefix = false;
            for (String substring : splitBySpaces) {
                if (prefix.equals(substring)) {
                    containPrefix = true;
                    break;
                }
            }
            if (!containPrefix) {
                return false;
            }
        }
        return true;
    }

    private static void handleTourException(String[] values) throws TourPlannerException {
        String price = values[2];
        for (int i = 0; i < price.length(); i++) {
            char ch = price.charAt(i);
            if (!(ch <= '9' && ch >= '0')) {
                throw new TourPlannerException(ERROR_PRICE_FORMAT);
            }
        }
    }

    private static void handleFlightException(String[] values) throws TourPlannerException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy HH:mm");
        LocalDateTime start;
        LocalDateTime end;
        String startDateString = values[3];
        String endDateString = values[4];

        try {
            start = LocalDateTime.parse(startDateString, formatter);
            end = LocalDateTime.parse(endDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new TourPlannerException(ERROR_FLIGHT_TIME_FORMAT);
        }
        if (end.isBefore(start) || end.equals(start)) {
            throw new TourPlannerException(ERROR_FLIGHT_TIME_INVERT);
        }
    }

    private static void handleClientException(String[] values) {
        String contactNum = values[2];
        String email = values[3];
        int adSymbolCount = (int) email.chars().filter(ch -> ch == '@').count();
        int periodCount = (int) email.chars().filter(ch -> ch == '.').count();

        if (adSymbolCount != 1 || periodCount != 1) {
            System.out.println(ERROR_EMAIL_FORMAT_WRONG);
        }

        boolean isWrongContactNumber = false;
        int contactNumberLength = contactNum.length();
        for (int i = 0; i < contactNumberLength; i++) {
            char ch = contactNum.charAt(i);
            if (!(ch <= '9' && ch >= '0')) {
                isWrongContactNumber = true;
                break;
            }
        }
        if (isWrongContactNumber) {
            System.out.println(ERROR_CONTACT_NUMBER_WRONG);
        } else if (contactNumberLength < 8) {
            System.out.println(ERROR_SHORT_CONTACT_NUMBER);
        } else if (contactNumberLength > 8) {
            System.out.println(ERROR_LONG_CONTACT_NUMBER);
        }
    }

    /**
     * Parses arguments with respect to the add client command.
     *
     * @param params full user's argument string
     * @throws TourPlannerException if there are missing fields,duplicated or missing prefixes
     */
    private static Command parseAdd(String params) throws TourPlannerException {
        String[] identifierAndArgs = splitCommandString(params, " ");
        String identifier = identifierAndArgs[IDENTIFIER_INDEX];
        String args = identifierAndArgs[ARGS_INDEX];

        TreeMap<Integer, String> prefixIndexes = extractPrefixIndexes(args, identifier);
        ArrayList<String> valuesList = extractValuesIntoArray(prefixIndexes, args, identifier);
        String[] values = valuesList.toArray(new String[valuesList.size()]);

        switch (identifier) {
        case CLIENT_IDENTIFIER:
            handleClientException(values);
            Client client = new Client(values);
            return new AddClientCommand(client);
        case FLIGHT_IDENTIFIER:
            handleFlightException(values);
            Flight flight = new Flight(values);
            return new AddFlightCommand(flight);
        case TOUR_IDENTIFIER:
            handleTourException(values);
            Tour tour = new Tour(values);
            return new AddTourCommand(tour);
        case PACKAGE_IDENTIFIER:
            return new AddClientPackageCommand(values);
        default:
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
        }
    }

    private static Command parseList(String params) throws TourPlannerException {
        switch (params) {
        case CLIENT_IDENTIFIER:
            return new ListClientCommand();
        case TOUR_IDENTIFIER:
            return new ListTourCommand();
        case FLIGHT_IDENTIFIER:
            return new ListFlightCommand();
        case PACKAGE_IDENTIFIER:
            return new ListClientPackageCommand();
        default:
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
        }
    }

    private static Command parseCut(String params) throws TourPlannerException {
        String[] identifierAndArgs = splitCommandString(params, " ");
        String identifier = identifierAndArgs[IDENTIFIER_INDEX];
        String args = identifierAndArgs[ARGS_INDEX];

        switch (identifier) {
        case CLIENT_IDENTIFIER:
            return new CutClientCommand(args);
        case TOUR_IDENTIFIER:
            return new CutTourCommand(args);
        case FLIGHT_IDENTIFIER:
            return new CutFlightCommand(args);
        case PACKAGE_IDENTIFIER:
            return new CutClientPackageCommand(args);
        default:
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
        }
    }

    private static Command parseFind(String params) throws TourPlannerException {
        String[] prefixSuffix = params.split(" ", 2);
        if (prefixSuffix.length < 2) {
            throw new TourPlannerException(ERROR_MISSING_NAME_ID);
        }
        String prefix = prefixSuffix[IDENTIFIER_INDEX];
        String suffix = prefixSuffix[ARGS_INDEX];
        switch (prefix) {
        case CLIENT_IDENTIFIER:
            return new FindClientCommand(suffix);
        case TOUR_IDENTIFIER:
            return new FindTourCommand(suffix);
        case FLIGHT_IDENTIFIER:
            return new FindFlightCommand(suffix);
        default:
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
        }
    }

    /**
     * Parses arguments with respect to the sort command.
     *
     * @param params full user's argument string
     * @throws TourPlannerException if there are missing fields,duplicated or missing prefixes
     */
    private static Command parseSort(String params) throws TourPlannerException {
        String[] identifierAndFilter = splitCommandString(params, " ");
        String identifier = identifierAndFilter[IDENTIFIER_INDEX];
        String filter = identifierAndFilter[ARGS_INDEX];
        switch (identifier) {
        case TOUR_IDENTIFIER:
            return new SortTourCommand(filter);
        case CLIENT_IDENTIFIER:
            return new SortClientCommand(filter);
        case FLIGHT_IDENTIFIER:
            return new SortFlightCommand(filter);
        default:
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
        }
    }
}

   
