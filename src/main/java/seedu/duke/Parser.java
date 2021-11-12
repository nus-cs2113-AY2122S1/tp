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
import seedu.duke.commands.flights.ListFlightCommand;
import seedu.duke.commands.flights.SortFlightCommand;
import seedu.duke.commands.tours.AddTourCommand;
import seedu.duke.commands.tours.CutTourCommand;
import seedu.duke.commands.tours.FindTourCommand;
import seedu.duke.commands.tours.ListTourCommand;
import seedu.duke.commands.tours.SortTourCommand;
import seedu.duke.data.Client;
import seedu.duke.data.Flight;
import seedu.duke.data.Tour;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Sense-makes the inputs given and distributes the information to other parts of the program.
 */
public class Parser {

    private static final String CLIENT_IDENTIFIER = "-c";
    private static final String TOUR_IDENTIFIER = "-t";
    private static final String FLIGHT_IDENTIFIER = "-f";
    private static final String PACKAGE_IDENTIFIER = "-p";

    private static final String PACKAGE_CLIENT_PREFIX = "/c";
    private static final String PACKAGE_TOUR_PREFIX = "/t";
    private static final String PACKAGE_FLIGHT_PREFIX = "/f";

    private static final String FLIGHT_DEPARTURE_PREFIX = "/d";
    private static final String FLIGHT_RETURN_PREFIX = "/r";
    private static final String FLIGHT_DEPARTURE_DATE_PREFIX = "/dd";
    private static final String FLIGHT_RETURN_DATE_PREFIX = "/rd";
    public static final String TOUR_NAME_PREFIX = "/n";
    public static final String TOUR_PRICE_PREFIX = "/p";

    public static final String CLIENT_NAME_PREFIX = "/n";
    public static final String CLIENT_CONTACT_NUMBER_PREFIX = "/cn";
    public static final String CLIENT_EMAIL_PREFIX = "/m";

    public static final String ERROR_INVALID_INPUT = "ERROR: TourPlanner cannot understand the command! "
            + "Please enter a valid command.\n"
            + "Type 'help' to view a list of commands available for use.";
    public static final String ERROR_MISSING_IDENTIFIER =
            "ERROR: Missing/wrong command filter used! Please enter a command with this format: COMMAND -FILTER DATA \n"
                    + "Example: find -t TOUR_ID";
    public static final String ERROR_DUPLICATE_PREFIXES = "ERROR: TourPlanner has detected duplicate prefixes!";
    public static final String ERROR_MISSING_PREFIXES
            = "ERROR: TourPlanner has detected missing/wrong prefixes! Did you miss out some fields?";
    public static final String ERROR_MISSING_ID = "Missing id that you wish to cut from! Please try again.";
    public static final String ERROR_MISSING_FIELDS = "ERROR: TourPlanner has detected empty fields! "
            + "Please enter all fields!";
    public static final String ERROR_FLIGHT_TIME_FORMAT = "ERROR: TourPlanner detected wrong date-time entry "
            + "formatting! \n Please input your date-times with the following format: d/M/yy HH:mm";
    public static final String ERROR_FLIGHT_TIME_INVERT = "ERROR: TourPlanner detected erroneous flight time entry! \n";
    public static final String ERROR_PRICE_FORMAT = "ERROR: TourPlanner has detected erroneous price entry! "
            + "Only include numbers (includes decimal)/one decimal point!";

    public static final String WARNING_EXTRA_INPUT = "WARNING: Extra input! Refrain from doing so.";
    public static final String WARNING_EMAIL_FORMAT_WRONG = "WARNING: TourPlanner has detected possible "
            + "erroneous email! \n";
    public static final String WARNING_CONTACT_NUMBER_WRONG =
            "WARNING: TourPlanner has detected possible erroneous contact number! (characters other than numbers) \n";
    public static final String WARNING_SHORT_CONTACT_NUMBER =
            "WARNING: TourPlanner detected that the given contact number is too short (< 8)! \n";
    private static final String WARNING_LONG_CONTACT_NUMBER =
            "WARNING: TourPlanner detected that the given contact number is too long (> 8)! \n";
    private static final String WARNING_PRICE_TOO_MANY_DECIMAL = "WARNING: TourPlanner has detected "
            + "erroneous price entry! Price has too many decimal places!";

    public static final int IDENTIFIER_INDEX = 0;
    public static final int ARGS_INDEX = 1;
    public static final int COMMAND_INDEX = 0;
    public static final int PARAMS_INDEX = 1;
    public static final int MAX_VALUE_ARRAY_SIZE = 5;

    //Client has three prefixes: /n, /cn, /m
    private static final int CLIENT_PREFIX_NUM = 4;

    //Flight has four prefixes: /d, /r, /dd, /rd
    private static final int FLIGHT_PREFIX_NUM = 5;

    //Tour has three prefixes: /n, /p
    private static final int TOUR_PREFIX_NUM = 3;

    // Package has four prefixes: /c, /f, /t
    private static final int PACKAGE_PREFIX_NUM = 4;

    public static final String EMPTY_STRING = "";

    /**
     * Parses user's input into command to execute.
     *
     * @param input full user's input string
     * @return the command parsed from user's input
     * @throws TourPlannerException if there are missing fields, duplicated prefixes, missing prefixes and other general
     *                              parsing errors
     */
    public static Command parse(String input) throws TourPlannerException {
        String[] commandAndParams = splitCommandString(input);
        String command = commandAndParams[COMMAND_INDEX];
        String params = commandAndParams[PARAMS_INDEX];

        switch (command) {
        case "add":
            return parseAdd(params);
        case "bye":
            return parseBye(params);
        case "cut":
            return parseCut(params);
        case "find":
            return parseFind(params);
        case "help":
            return parseHelp(params);
        case "list":
            return parseList(params);
        case "sort":
            return parseSort(params);
        default:
            throw new TourPlannerException(ERROR_INVALID_INPUT);
        }
    }

    /**
     * Separates user input into 2 based on the separator.
     * If the value of the String after the separator is null,
     * the second value of the String array will be returned as "".
     *
     * @param input full user's input string
     * @return the array containing command and argument/params strings
     */
    private static String[] splitCommandString(String input) {
        String[] split = input.trim().split(" ", 2);
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
            throw new TourPlannerException(ERROR_MISSING_FIELDS);
        }
        return prefixIndexes;
    }

    /**
     * Returns the number of prefixes for the specific data type, determined by the identifier.
     *
     * @param identifier specific identifier to determine specific data type (client, flight, tour, package)
     * @return the number of prefixes determined by the identifier
     */
    private static int generateExpectedNumberOfPrefixIndexes(String identifier) {
        int numberOfPrefixIndexes = 0;
        switch (identifier) {
        case CLIENT_IDENTIFIER:
            numberOfPrefixIndexes = CLIENT_PREFIX_NUM;
            break;
        case PACKAGE_IDENTIFIER:
            numberOfPrefixIndexes = PACKAGE_PREFIX_NUM;
            break;
        case TOUR_IDENTIFIER:
            numberOfPrefixIndexes = TOUR_PREFIX_NUM;
            break;
        case FLIGHT_IDENTIFIER:
            numberOfPrefixIndexes = FLIGHT_PREFIX_NUM;
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

    /**
     * Return the list of prefixes for each data type.
     *
     * @param identifier specific identifier to determine specific data type (client, flight, tour, package)
     * @return the list of prefixes, as specified by the identifier
     * @throws TourPlannerException if the identifier is invalid
     */
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
            prefixes = Arrays.asList(PACKAGE_CLIENT_PREFIX, PACKAGE_TOUR_PREFIX, PACKAGE_FLIGHT_PREFIX);
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
        case PACKAGE_CLIENT_PREFIX:
            index = 1;
            break;
        case PACKAGE_TOUR_PREFIX:
            index = 2;
            break;
        case PACKAGE_FLIGHT_PREFIX:
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
        String[] prefixes = prefixList.toArray(new String[0]);
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

    /**
     * Handles all exceptions regarding inputs for add tour command.
     * Throws TourPlannerException when there are duplicate decimal points (.), or additional characters in price
     * that prevent parsing.
     * Prints a warning if price has too many divisions, i.e. decimal places
     *
     * @param values the array of values extracted from user's input after parsing
     * @throws TourPlannerException if there are duplicate decimal points, additional characters in price
     */
    private static void handleTourException(String[] values) throws TourPlannerException {
        String price = values[2];
        int decimalCount = (int) price.chars().filter(ch -> ch == '.').count();
        String priceAfterParseString = price.replaceAll("[^0-9.]", "");
        boolean containsAdditionalDecimalPoints = decimalCount > 1;
        if (containsAdditionalDecimalPoints) {
            throw new TourPlannerException(ERROR_PRICE_FORMAT);
        }

        boolean containsAdditionalCharacters = !price.equals(priceAfterParseString);

        if (containsAdditionalCharacters) {
            throw new TourPlannerException(ERROR_PRICE_FORMAT);
        }

        if (decimalCount == 1) {
            int decimalIndex = price.indexOf(".");
            int numberOfDecimalPlaces = price.length() - decimalIndex - 1;

            if (numberOfDecimalPlaces > 2) {
                System.out.println(WARNING_PRICE_TOO_MANY_DECIMAL);
            }
        }
    }

    /**
     * Handles all exceptions regarding inputs for add flight command.
     *
     * @param values the array of values extracted from user's input after parsing
     * @throws TourPlannerException if there are logical errors with date-time input (i.e. date-time of flight to
     *                              destination location is after date-time of return flight)
     *                              or date-time parsing/format errors
     */
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

    /**
     * Handles all exceptions regarding inputs for add client command.
     * Prints warnings for erroneous contact number (inclusive of characters other than numbers, length != 8)
     * Also prints warnings for erroneous email (lacking/more than one '@' symbol or period '.', that are commonly
     * found in emails).
     *
     * @param values the array of values extracted from user's input after parsing
     */
    private static void handleClientException(String[] values) {
        String contactNum = values[2];
        String email = values[3];
        int adSymbolCount = (int) email.chars().filter(ch -> ch == '@').count();
        int periodCount = (int) email.chars().filter(ch -> ch == '.').count();

        if (adSymbolCount != 1 || periodCount != 1) {
            System.out.println(WARNING_EMAIL_FORMAT_WRONG);
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
            System.out.println(WARNING_CONTACT_NUMBER_WRONG);
        } else if (contactNumberLength < 8) {
            System.out.println(WARNING_SHORT_CONTACT_NUMBER);
        } else if (contactNumberLength > 8) {
            System.out.println(WARNING_LONG_CONTACT_NUMBER);
        }
    }

    /**
     * Parses arguments with respect to the add command.
     * Extract values from user's input and passes it as an argument to construct Client/Flight/Tour object
     * depending on the identifier.
     * Passes the created object to the specific AddCommand, determined by the identifier.
     *
     * @param params full user's argument string
     * @return the specific AddCommand object determined by the command's identifier
     * @throws TourPlannerException if there are missing fields,duplicated or missing prefixes
     */
    private static Command parseAdd(String params) throws TourPlannerException {
        String[] identifierAndArgs = splitCommandString(params);
        String identifier = identifierAndArgs[IDENTIFIER_INDEX];
        String args = identifierAndArgs[ARGS_INDEX];

        TreeMap<Integer, String> prefixIndexes = extractPrefixIndexes(args, identifier);
        ArrayList<String> valuesList = extractValuesIntoArray(prefixIndexes, args, identifier);
        String[] values = valuesList.toArray(new String[0]);

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

    /**
     * Parses arguments with respect to the bye command.
     *
     * @param params full user's argument string
     * @return ByeCommand object
     */
    private static Command parseBye(String params) {
        if (!params.equals(EMPTY_STRING)) {
            System.out.println(WARNING_EXTRA_INPUT);
        }
        return new ByeCommand();
    }

    /**
     * Parses user input, identifies data type to be cutting from based on the command filter,
     * and returns the corresponding subclass of Command.
     *
     * @param params user input excluding "cut"
     * @return command corresponding to the data type of the command filter
     * @throws TourPlannerException if command filter is wrong/missing or id to cut is wrong/missing
     */
    private static Command parseCut(String params) throws TourPlannerException {
        String[] identifierAndArgs = splitCommandString(params);
        String identifier = identifierAndArgs[IDENTIFIER_INDEX];
        String args = identifierAndArgs[ARGS_INDEX];

        switch (identifier) {
        case CLIENT_IDENTIFIER:
            handleCutException(args);
            return new CutClientCommand(args);
        case TOUR_IDENTIFIER:
            handleCutException(args);
            return new CutTourCommand(args);
        case FLIGHT_IDENTIFIER:
            handleCutException(args);
            return new CutFlightCommand(args);
        case PACKAGE_IDENTIFIER:
            handleCutException(args);
            return new CutClientPackageCommand(args);
        default:
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
        }
    }

    private static void handleCutException(String args) throws TourPlannerException {
        if (args.equals(EMPTY_STRING)) {
            throw new TourPlannerException(ERROR_MISSING_ID);
        }
    }

    /**
     * Parses the parameters given to determine which FindXYZCommand to be called for.
     *
     * @param params full user's argument string
     * @return a FindXYZCommand of a specific data type (client, tour, flight, client package)
     * @throws TourPlannerException if there are missing fields, duplicated or missing prefixes
     */
    private static Command parseFind(String params) throws TourPlannerException {
        String[] prefixSuffix = params.split(" ", 2);
        if (prefixSuffix.length < 2) {
            throw new TourPlannerException(ERROR_MISSING_IDENTIFIER);
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
     * Parses arguments with respect to the help command.
     *
     * @param params full user's argument string
     * @return HelpCommand object
     */
    private static Command parseHelp(String params) {
        if (!params.equals(EMPTY_STRING)) {
            System.out.println(WARNING_EXTRA_INPUT);
        }
        return new HelpCommand();
    }

    /**
     * Parses the parameters given to determine which ListXYZCommand to be called for.
     *
     * @param params full user's argument string
     * @return a ListXYZCommand of a specific data type (client, tour, flight, client package)
     * @throws TourPlannerException if there are missing fields, duplicated or missing prefixes
     */
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

    /**
     * Parses arguments with respect to the sort command.
     *
     * @param params full user's argument string
     * @return the specific SortCommand object determined by the command's identifier
     * @throws TourPlannerException if there are missing fields,duplicated or missing prefixes
     */
    private static Command parseSort(String params) throws TourPlannerException {
        String[] identifierAndFilter = splitCommandString(params);
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

   
