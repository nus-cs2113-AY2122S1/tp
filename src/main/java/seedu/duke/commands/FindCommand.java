package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import java.util.ArrayList;
import java.util.Arrays;


public class FindCommand extends Command {

    private static String keyword;
    private static final ArrayList<Event> filteredList = new ArrayList<>();
    private static int numberOfEvents;

    public FindCommand(String[] command) {
        try {
            if (command.length == 1) {
                throw new DukeException("Please specify what Events you wish to find.");
            }
            keyword = getKeywordFromCommand(command);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Please enter at least one keyword!");
        }
    }

    public CommandResult execute() {
        filterEventsByString(keyword);
        System.out.println("Here are the events you wished to find:");
        printFilteredEvents();
        if (filteredList.isEmpty()) {
            return new CommandResult("No matching events were found.");
        }
        return new CommandResult(numberOfEvents + " events found.");
    }

    private static String convertArrayToString(String[] command) {
        return Arrays.toString(command);
    }

    private static String getKeywordFromCommand(String[] command) {
        String commandAsString = convertArrayToString(command);
        int startOfKeyword = commandAsString.trim().indexOf(" ") + 1;
        return commandAsString.substring(startOfKeyword).trim();
    }

    private static void filterEventsByString(String keyword) {
        for (int i = 0; i < Duke.eventCatalog.size(); i++) {
            Event event = Duke.eventCatalog.get(i);
            if (event.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(i, event);
            }
        }
    }

    private static void printFilteredEvents() {
        for (int i = 0; i < filteredList.size(); i++) {
            if (filteredList.get(i) == null) {
                continue;
            }
            System.out.println((i + 1) + filteredList.get(i).getTitle());
            numberOfEvents++;
        }
    }
}
