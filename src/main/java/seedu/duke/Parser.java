package seedu.duke;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    private static final String CONTACT_NUMBER_PREFIX = "/cn";
    private static final String FLIGHT_PREFIX = "/f";
    private static final String ACCOMMS_PREFIX = "/a";
    private static final String TOUR_PREFIX = "/t";

    /**
     * Enum to show availability of prefix in string during add client command.
     */
    private enum Prefix {
        HAS_CONTACT,
        HAS_FLIGHT,
        HAS_ACCOMMS,
        HAS_TOUR,
        NO_PREFIX
    }

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
        switch (command) {
        case "bye":
            if (!params.equals("")) {
                throw new TourPlannerException("INVALID INPUT");
            }
            return new ByeCommand();
        case "add":
            return parseAdd(params);
        case "list":
            if (!params.equals("")) {
                throw new TourPlannerException("INVALID INPUT");
            }
            return new ListCommand();
        case "clear":
            if (!params.equals("")) {
                throw new TourPlannerException("INVALID INPUT");
            }
            return new ClearCommand();
        case "cut":
            try {
                return parseCut(params);
            } catch (NullPointerException | NumberFormatException e) {
                throw new TourPlannerException("INVALID: Empty 'cut' index");
            }
        default:
            throw new TourPlannerException("INVALID INPUT");
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
     * Parses arguments with respect to the add client command.
     *
     * @param params full user's argument string
     * @return the prepared command
     * @throws TourPlannerException if there are missing fields,duplicated or missing prefixes
     */
    private static AddCommand parseAdd(String params) throws TourPlannerException {
        TreeMap<Integer, String> prefixIndexes = extractPrefixIndexes(params);
        assert containAllPrefixes(params);
        String[] values = extractValuesIntoArray(prefixIndexes, params);
        Client client = new Client(values);
        return new AddCommand(client);
    }

    /**
     * Extracts the indexes for prefixes and put into a map that sorts the list by the natural ordering of the keys.
     *
     * @param argString full user's argument string
     * @return the treemap with prefix index as the key and the corresponding prefix as the value
     * @throws TourPlannerException if there are missing fields or missing prefixes
     */
    private static TreeMap<Integer, String> extractPrefixIndexes(String argString) throws TourPlannerException {
        if (!containAllPrefixes(argString)) {
            throw new TourPlannerException("Missing prefixes! Did you miss out some fields?");
        }

        TreeMap<Integer, String> prefixIndexes = new TreeMap<>();
        int nameIndex = 0;
        int contactIndex = argString.indexOf(CONTACT_NUMBER_PREFIX);
        int flightIndex = argString.indexOf(FLIGHT_PREFIX);
        int accommsIndex = argString.indexOf(ACCOMMS_PREFIX);
        int tourIndex = argString.indexOf(TOUR_PREFIX);

        prefixIndexes.put(nameIndex, "");
        prefixIndexes.put(contactIndex, CONTACT_NUMBER_PREFIX);
        prefixIndexes.put(flightIndex, FLIGHT_PREFIX);
        prefixIndexes.put(accommsIndex, ACCOMMS_PREFIX);
        prefixIndexes.put(tourIndex, TOUR_PREFIX);

        boolean isUniqueIndex = prefixIndexes.size() == 5;

        if (!isUniqueIndex) {
            throw new TourPlannerException("You missed out your name!");
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
    private static String[] extractValuesIntoArray(TreeMap<Integer, String> prefixIndexes, String argString)
            throws TourPlannerException {
        String[] extractedValues = new String[5];
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
            int inputIndex = obtainArrayIndex(prefix);
            extractedValues[inputIndex] = value;
        }

        String finalPrefix = prefixes.get(4);
        int finalIndex = indexes.get(4);

        int inputIndex = obtainArrayIndex(finalPrefix);
        String value = extractValue(argString, finalPrefix, finalIndex, argString.length());
        extractedValues[inputIndex] = value;
        return extractedValues;
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
        String value = unformattedSubstring.replace(prefix, "").trim();
        if (value.contains(CONTACT_NUMBER_PREFIX) || value.contains(FLIGHT_PREFIX)
                || value.contains(ACCOMMS_PREFIX) || value.contains(TOUR_PREFIX)) {
            throw new TourPlannerException("There are duplicate prefixes!");
        }
        return value;
    }

    /**
     * Obtains array index that corresponds to the prefix given.
     *
     * @param prefix prefix of value extracted
     * @return array index of values according to prefix
     */
    private static int obtainArrayIndex(String prefix) {
        int index = 0;
        switch (prefix) {
        case CONTACT_NUMBER_PREFIX:
            index = 1;
            break;
        case FLIGHT_PREFIX:
            index = 2;
            break;
        case ACCOMMS_PREFIX:
            index = 3;
            break;
        case TOUR_PREFIX:
            index = 4;
            break;
        default:
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
    private static boolean containAllPrefixes(String argString) {
        String[] splitBySpaces = argString.trim().split("\\s+");
        boolean containsContact = false;
        boolean containsFlight = false;
        boolean containsAccomms = false;
        boolean containsTour = false;
        for (String substring : splitBySpaces) {
            Prefix prefix = checkPrefix(substring);
            switch (prefix) {
            case HAS_CONTACT:
                containsContact = true;
                break;
            case HAS_ACCOMMS:
                containsAccomms = true;
                break;
            case HAS_TOUR:
                containsTour = true;
                break;
            case HAS_FLIGHT:
                containsFlight = true;
                break;
            default:
                break;
            }
        }
        return containsContact && containsFlight && containsAccomms && containsTour;
    }

    /**
     * Checks whether substring equals to prefixes.
     *
     * @param substring strings obtained from argument string after splitting by spaces
     * @return the enum Prefix that shows availability of prefix
     */
    private static Prefix checkPrefix(String substring) {
        if (substring.equals(CONTACT_NUMBER_PREFIX)) {
            return Prefix.HAS_CONTACT;
        } else if (substring.equals(FLIGHT_PREFIX)) {
            return Prefix.HAS_FLIGHT;
        } else if (substring.equals(ACCOMMS_PREFIX)) {
            return Prefix.HAS_ACCOMMS;
        } else if (substring.equals(TOUR_PREFIX)) {
            return Prefix.HAS_TOUR;
        } else {
            return Prefix.NO_PREFIX;
        }
    }

    /**
     * Parses arguments with respect to the cut client command.
     *
     * @param params full user's argument/params string after splitting
     * @return the prepared command
     */
    private static Command parseCut(String params) {
        int clientIndex = stringToInt(params) - 1;
        return new CutCommand(clientIndex);
    }

    /**
     * Parses string containing an integer value to int.
     *
     * @param params full user's argument/params string after splitting
     * @return the client index from argument string
     */
    private static int stringToInt(String params) {
        int clientIndex = Integer.parseInt(params);
        return clientIndex;
    }

}

   
