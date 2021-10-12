package seedu.duke;

import java.util.ArrayList;
import java.util.Map;
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
        TreeMap<Integer, String> valueIndex = extractValueIndexes(params);
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

    private static TreeMap<Integer, String> extractValueIndexes(String argString) {
        TreeMap<Integer, String> valueIndexes = new TreeMap<Integer, String>();
        if (containAllFields(argString)) {
            int nameIndex = 0;
            int contactIndex = argString.indexOf("/cn");
            int flightIndex = argString.indexOf("/f");
            int accommsIndex = argString.indexOf("/a");
            int tourIndex = argString.indexOf("/t");
            valueIndexes.put(nameIndex, "");
            valueIndexes.put(contactIndex, "/cn");
            valueIndexes.put(flightIndex, "/f");
            valueIndexes.put(accommsIndex, "/a");
            valueIndexes.put(tourIndex, "/t");
            return valueIndexes;
        } else {
            return null;
        }
    }

    private static String[] extractValues(TreeMap<Integer, String> valueIndexes, String argString) {
        String[] extractedValues = new String[5];
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<String> prefixes = new ArrayList<>();
        for (Map.Entry<Integer, String> valueIndex : valueIndexes.entrySet()) {
            System.out.println(valueIndex);
            indexes.add(valueIndex.getKey());
            prefixes.add(valueIndex.getValue());
        }

        int nextIndex = 0;
        int previousIndex;
        String finalPrefix = prefixes.get(4);

        for (int i = 0; i < indexes.size() - 1; i++) {
            previousIndex = indexes.get(i);
            nextIndex = indexes.get(i + 1);
            String previousPrefix = prefixes.get(i);

            String unformattedSubstring = argString.substring(previousIndex, nextIndex).trim();
            String value = unformattedSubstring.replace(previousPrefix, "").trim();
            int inputIndex = obtainExtractedValueIndex(previousPrefix);
            extractedValues[inputIndex] = value;
        }

        int inputIndex = obtainExtractedValueIndex(finalPrefix);
        String unformattedSubstring = argString.substring(nextIndex).trim();
        String value = unformattedSubstring.replace(finalPrefix, "").trim();
        extractedValues[inputIndex] = value;
        return extractedValues;
    }


    private static int obtainExtractedValueIndex(String prefix) {
        int index = 0;
        switch (prefix) {
        case "/cn":
            index = 1;
            break;
        case "/f":
            index = 2;
            break;
        case "/a":
            index = 3;
            break;
        case "/t":
            index = 4;
            break;
        case "":
            index = 0;
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

   
