package seedu.duke;


import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.ClearCommand;
import seedu.duke.commands.Command;
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
import java.util.*;


/**
 * Sense-makes the inputs given and distributes the information to other parts of the program.
 */
public class Parser {
    public static final String ERROR_INVALID_INPUT = "Invalid input! Please enter a valid command.";
    public static final String ERROR_EXTRA_INPUT = "Extra input! Refrain from doing so.";
    public static final String ERROR_INVALID_CUT_INDEX = "Invalid cut index!";
    public static final String ERROR_INVALID_CLIENT_INDEX = "Invalid client index!";
    public static final String ERROR_DUPLICATE_PREFIXES = "Duplicate prefixes! Please try again.";
    public static final String ERROR_MISSING_PREFIXES
            = "Missing prefixes! Did you miss out some fields? Please try again.";
    public static final String ERROR_MISSING_NAME = "Missing name/id! Please try again.";
    public static final String ERROR_EMAIL_FORMAT_WRONG = "Invalid Email!";
    public static final String ERROR_CONTACT_NUMBER_WRONG = "Invalid Contact Number";
    public static final String ERROR_TOUR_TIME_WRONG = "Invalid Tour Time";
    public static final String ERROR_FLIGHT_TIME_INVERT = "Invalid Flight Time";
    public static final String ERROR_PRICE_FORMAT = "Invalid Price";

    /**
     * Parses user's input into command to execute.
     *
     * @param input full user's input string
     * @return the command parsed from user's input
     * @throws TourPlannerException if there are missing fields,duplicated prefixes, missing prefixes and erroneous
     *                              'cut' index given
     */
    public static Command parse(String input) throws TourPlannerException {
        String[] commandAndParams = splitCommandString(input, " ");
        String command = commandAndParams[0];
        String params = commandAndParams[1];
        String dummy;
        String contact_num;
        switch (command) {
            case "bye":
                if (!params.equals("")) {
                    throw new TourPlannerException(ERROR_EXTRA_INPUT);
                }
                return new ByeCommand();
            case "add":
                if(params.contains("-c")) {
                    if(!params.contains("@")) {
                        throw new TourPlannerException(ERROR_EMAIL_FORMAT_WRONG);
                    }
                    int index1 = params.indexOf("/cn");
                    int index2 = params.lastIndexOf("/");
                    if(index1 == index2) {
                        dummy = params.substring(index1 + 3);
                        contact_num = dummy.trim();
                        for(int i = 0; i < contact_num.length(); i++) {
                            char ch = contact_num.charAt(i);
                            if(!(ch <= '9' && ch >= '0')) {
                                throw new TourPlannerException(ERROR_CONTACT_NUMBER_WRONG);
                            }
                        }
                    }
                    else {
                        dummy = params.substring(index1 + 3, index2);
                        contact_num = dummy.trim();
                        for(int i = 0; i < contact_num.length(); i++) {
                            char ch = contact_num.charAt(i);
                            if(!(ch <= '9' && ch >= '0')) {
                                throw new TourPlannerException(ERROR_CONTACT_NUMBER_WRONG);
                            }
                        }
                    }
                }
                if(params.contains("-t")) {
                    int index1 = params.indexOf("/p");
                    int index2 = params.lastIndexOf("/");
                    String price;
                    if(index1 == index2) {
                        dummy = params.substring(index1 + 2);
                        price = dummy.trim();
                    }
                    else {
                        dummy = params.substring(index1 + 2, index2);
                        price = dummy.trim();
                    }
                    for(int i = 0; i < price.length(); i++) {
                        char ch = price.charAt(i);
                        if(!(ch <= '9' && ch >= '0')) {
                            throw new TourPlannerException(ERROR_PRICE_FORMAT);
                        }
                    }
                }
                if(params.contains("-f")) {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:MM");
                    int index1 = params.indexOf("/dt");
                    int index2 = params.indexOf("/df");
                    Date start = null, end = null;
                    try {
                        if (index1 > index2) {
                            dummy = params.substring(index1 + 3).trim();
                            start = formatter.parse(dummy);
                            dummy = params.substring(index2 + 3, index1);
                            end = formatter.parse(dummy);
                        } else {
                            dummy = params.substring(index2 + 3).trim();
                            end = formatter.parse(dummy);
                            dummy = params.substring(index1 + 3, index2);
                            start = formatter.parse(dummy);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (end.before(start)) {
                        throw new TourPlannerException(ERROR_FLIGHT_TIME_INVERT);
                    }
                }
                return parseAdd(params);
            case "list":
                return parseList(params);
            case "clear":
                if (!params.equals("")) {
                    throw new TourPlannerException(ERROR_EXTRA_INPUT);
                }
                return new ClearCommand();
            case "find":
                return parseFind(params);
            case "sort":
                return parseSort(params);
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


        List<String> prefixes = null;
        int repeatPrefixChecker = 0;

        switch (identifier) {
        case "-c":
            prefixes = Arrays.asList("/n", "/cn", "/m");
            repeatPrefixChecker = 4;
            break;
        case "-t":
            prefixes = Arrays.asList("/n", "/p");
            repeatPrefixChecker = 3;
            break;
        case "-f":
            prefixes = Arrays.asList("/t", "/f", "/dt", "/df");
            repeatPrefixChecker = 5;
            break;
        case "-p":
            prefixes = Arrays.asList("/c", "/t", "/f");
            repeatPrefixChecker = 4;
            break;
        default:
            break;
        }

        if (!containAllPrefixes(argString, prefixes)) {
            throw new TourPlannerException(ERROR_MISSING_PREFIXES);
        }

        TreeMap<Integer, String> prefixIndexes = new TreeMap<>();
        prefixIndexes.put(0, "");
        prefixes.forEach((prefix) -> {
            int prefixIndex = argString.indexOf(prefix);
            prefixIndexes.put(prefixIndex, prefix);
        });

        boolean hasUniquePrefixes = prefixIndexes.size() == repeatPrefixChecker;

        if (!hasUniquePrefixes) {
            throw new TourPlannerException(ERROR_MISSING_NAME);
        }
        return prefixIndexes;
    }

    /**
     * Extract values into an array in a sorted manner to prepare for execution of add.
     *
     * @param prefixIndexes the treemap with prefix index as the key and the corresponding prefix as the value
     * @param argString     full user's argument string
     * @return the array containing client's information in a sorted fashion
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
            String value = extractValue(argString, prefix, previousIndex, nextIndex);
            int inputIndex = obtainArrayIndex(prefix, identifier);
            extractedValues.set(inputIndex, value);
        }

        String finalPrefix = prefixes.get(indexes.size() - 1);
        int finalIndex = indexes.get(indexes.size() - 1);

        int inputIndex = obtainArrayIndex(finalPrefix, identifier);
        String value = extractValue(argString, finalPrefix, finalIndex, argString.length());
        extractedValues.set(inputIndex, value);
        return extractedValues;
    }

    private static void initialiseArrayList(ArrayList<String> extractedValues) {
        for (int i = 0; i < 5; i++) {
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
     * @return value corresponding to prefix given
     * @throws TourPlannerException if there are duplicate prefixes found
     */
    private static String extractValue(String argString, String prefix, int startIndex, int endIndex)
            throws TourPlannerException {
        String unformattedSubstring = argString.substring(startIndex, endIndex).trim();
        String value = unformattedSubstring.replaceFirst(prefix, "").trim();
        return value;
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
        case "-c":
            index = obtainClientArrayIndex(prefix);
            break;
        case "-t":
            index = obtainTourArrayIndex(prefix);
            break;
        case "-f":
            index = obtainFlightArrayIndex(prefix);
            break;
        case "-p":
            index = obtainPackageArrayIndex(prefix);
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    private static int obtainPackageArrayIndex(String prefix) {
        int index;
        switch (prefix) {
        case "/c":
            index = 1;
            break;
        case "/t":
            index = 2;
            break;
        case "/f":
            index = 3;
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    private static int obtainFlightArrayIndex(String prefix) {
        int index;

        switch (prefix) {
        case "/t":
            index = 1;
            break;
        case "/f":
            index = 2;
            break;
        case "/dt":
            index = 3;
            break;
        case "/df":
            index = 4;
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    private static int obtainTourArrayIndex(String prefix) {
        int index;

        switch (prefix) {
        case "/n":
            index = 1;
            break;
        case "/p":
            index = 2;
            break;
        default:
            index = 0;
            break;
        }
        return index;
    }

    private static int obtainClientArrayIndex(String prefix) {
        int index;

        switch (prefix) {
        case "/n":
            index = 1;
            break;
        case "/cn":
            index = 2;
            break;
        case "/m":
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

    /**
     * Parses string containing an integer value to int.
     *
     * @param params full user's argument/params string after splitting
     * @return the client index from argument string
     */
    private static int stringToInt(String params) {
        int index = Integer.parseInt(params);
        return index;
    }

    /**
     * Parses arguments with respect to the add client command.
     *
     * @param params full user's argument string
     * @throws TourPlannerException if there are missing fields,duplicated or missing prefixes
     */
    private static Command parseAdd(String params) throws TourPlannerException {
        String[] identifierAndArgs = splitCommandString(params, " ");
        String identifier = identifierAndArgs[0];
        String args = identifierAndArgs[1];

        TreeMap<Integer, String> prefixIndexes = extractPrefixIndexes(args, identifier);
        ArrayList<String> valuesList = extractValuesIntoArray(prefixIndexes, args, identifier);
        String[] values = valuesList.toArray(new String[valuesList.size()]);

        switch (identifier) {
        case "-c":
            Client client = new Client(values);
            return new AddClientCommand(client);
        case "-f":
            Flight flight = new Flight(values);
            return new AddFlightCommand(flight);
        case "-t":
            Tour tour = new Tour(values);
            return new AddTourCommand(tour);
        case "-p":
            return new AddClientPackageCommand(values);
        default:
            break;
        }
        return null;
    }

    private static Command parseList(String params) throws TourPlannerException {
        switch (params) {
        case "-c":
            return new ListClientCommand();
        case "-t":
            return new ListTourCommand();
        case "-f":
            return new ListFlightCommand();
        case "-p":
            return new ListClientPackageCommand();
        default:
            throw new TourPlannerException(ERROR_INVALID_INPUT);
        }
    }

    private static Command parseCut(String params) throws TourPlannerException {
        String[] identifierAndArgs = splitCommandString(params, " ");
        String identifier = identifierAndArgs[0];
        String args = identifierAndArgs[1];

        switch (identifier) {
        case "-c":
            return new CutClientCommand(args);
        case "-t":
            return new CutTourCommand(args);
        case "-f":
            return new CutFlightCommand(args);
        case "-p":
            return new CutClientPackageCommand(args);
        default:
            throw new TourPlannerException(ERROR_INVALID_INPUT);
        }
    }

    private static Command parseFind(String params) throws TourPlannerException {
        String prefix = params.split(" ")[0];
        String suffix = params.split(" ")[1];
        switch (prefix) {
        case "-c":
            return new FindClientCommand(suffix);
        case "-t":
            return new FindTourCommand(suffix);
        case "-f":
            return new FindFlightCommand(suffix);
        default:
            throw new TourPlannerException(ERROR_INVALID_INPUT);
        }
    }

    private static Command parseSort(String params) {
        String[] identifierAndFilter = splitCommandString(params, " ");
        String identifier = identifierAndFilter[0];
        String filter = identifierAndFilter[1];
        switch (identifier) {
        case "-t":
            return new SortTourCommand(filter);
        case "-c":
            return new SortClientCommand(filter);
        default:
            return null;
        }
    }
}

   
