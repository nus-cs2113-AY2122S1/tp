package seedu.duke;


import java.util.*;

public class Parser {
    private static final String CONTACT_NUMBER_PREFIX = "/cn";
    private static final String FLIGHT_PREFIX = "/f";
    private static final String ACCOMMS_PREFIX = "/a";
    private static final String TOUR_PREFIX = "/t";

    public static Command parse(String input) throws TourPlannerException {
        String[] commandAndParams = splitCommandString(input, " ");
        String command = commandAndParams[0];
        String params = commandAndParams[1];
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "add":
            return executeAddCommand(params);
        case "list":
            return new ListCommand();
        case "clear":
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

    public static String[] splitCommandString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    private static AddCommand executeAddCommand(String params) throws TourPlannerException {
        TreeMap<Integer, String> prefixIndexes = extractPrefixIndexes(params);
        String[] values = extractValuesIntoArray(prefixIndexes, params);
        Client client = new Client(values);
        return new AddCommand(client);
    }

    private enum Prefix {
        HAS_CONTACT,
        HAS_FLIGHT,
        HAS_ACCOMMS,
        HAS_TOUR,
        NO_PREFIX
    }

    private static TreeMap<Integer, String> extractPrefixIndexes(String argString) throws TourPlannerException {
        TreeMap<Integer, String> prefixIndexes = new TreeMap<>();
        if (containAllFields(argString)) {
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
        } else {
            throw new TourPlannerException("Missing prefixes! Did you miss out some fields?");
        }
    }

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
            int inputIndex = obtainExtractedValueIndex(prefix);
            extractedValues[inputIndex] = value;
        }

        String finalPrefix = prefixes.get(4);
        int finalIndex = indexes.get(4);

        int inputIndex = obtainExtractedValueIndex(finalPrefix);
        String value = extractValue(argString, finalPrefix, finalIndex, argString.length());
        extractedValues[inputIndex] = value;
        return extractedValues;
    }

    private static String extractValue(String argString, String prefix, int startIndex, int endIndex)
            throws TourPlannerException {
        String unformattedSubstring = argString.substring(startIndex, endIndex).trim();
        String value = unformattedSubstring.replace(prefix, "").trim();
        if (value.contains("/cn") || value.contains("/f") || value.contains("/a") || value.contains("/t")) {
            throw new TourPlannerException("There are duplicate prefixes!");
        }
        return value;
    }

    private static int obtainExtractedValueIndex(String prefix) {
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

    private static boolean containAllFields(String argString) {
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

    public static Command parseCut(String params) {
        int clientIndex = stringToInt(params) - 1;
        return new CutCommand(clientIndex);
    }

    private static int stringToInt(String params) {
        int clientIndex = Integer.parseInt(params);
        return clientIndex;
    }

}

   
