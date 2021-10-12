package seedu.duke;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Parser {
    public static Command parse(String input) {
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
                    System.out.println("INVALID: Empty 'cut' index");
                }
                break;
            default:
                System.out.println("INVALID INPUT");
                break;
        }
        return null;
    }

    public static String[] splitCommandString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[]{split[0], ""};
    }

    private static AddCommand executeAddCommand(String params) {
        SortedMap<Integer, String> valueIndex = extractValueIndexes(params);
        String[] values = extractValues(valueIndex, params);
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

    private static SortedMap<Integer, String> extractValueIndexes(String argString) {
        SortedMap<Integer, String> valueIndexes = new TreeMap<Integer, String>();
        if (containAllFields(argString)) {
            int contactIndex = argString.indexOf("/cn");
            int flightIndex = argString.indexOf("/f");
            int accommsIndex = argString.indexOf("/a");
            int tourIndex = argString.indexOf("/t");
            valueIndexes.put(contactIndex, "/cn");
            valueIndexes.put(flightIndex, "/f");
            valueIndexes.put(accommsIndex, "/a");
            valueIndexes.put(tourIndex, "/t");
            return valueIndexes;
        } else {
            return null;
        }
    }

    private static String[] extractValues(SortedMap<Integer, String> valueIndexes, String argString) {
        String[] extractValues = new String[5];
        int previousIndex = 0;
        for (Map.Entry<Integer, String> valueIndex : valueIndexes.entrySet()) {
            int nextIndex = valueIndex.getKey();
            String prefix = valueIndex.getValue();
            String unformattedSubstring = argString.substring(previousIndex, nextIndex).trim();
            String value = unformattedSubstring.replace(prefix, "").trim();
            previousIndex = nextIndex;
            switch (prefix) {
                case "/cn":
                    extractValues[1] = value;
                    break;
                case "/f":
                    extractValues[2] = value;
                    break;
                case "/a":
                    extractValues[3] = value;
                    break;
                case "/t":
                    extractValues[4] = value;
                    break;
                default:
                    extractValues[0] = value;
            }
        }
        return extractValues;
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
        if (substring.startsWith("/cn")) {
            return Prefix.HAS_CONTACT;
        } else if (substring.startsWith("/f")) {
            return Prefix.HAS_FLIGHT;
        } else if (substring.startsWith("/a")) {
            return Prefix.HAS_ACCOMMS;
        } else if (substring.startsWith("/t")) {
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

   
