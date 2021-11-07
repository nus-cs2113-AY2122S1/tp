package seedu.duke.commands;


import seedu.duke.Duke;
import seedu.duke.items.Event;

public class FindCommand extends Command {

    private final String keywords;
    private static int numberOfEventsFound;


    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    public CommandResult execute() {
        String findResults = filterEvents(keywords);
        if (noEventsFound()) {
            return new CommandResult("No matching events found!");
        }
        return new CommandResult(findResults);
    }

    private static String filterEvents(String keyword) {
        StringBuilder result = new StringBuilder();
        numberOfEventsFound = 0;
        for (int i = 0; i < Duke.eventCatalog.size(); i++) {
            Event event = Duke.eventCatalog.get(i);
            if (event.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.append(i + 1).append(". ");
                result.append(event.getTitle()).append("\n");
                numberOfEventsFound++;
            }
        }
        return result.toString();
    }

    private static boolean noEventsFound() {
        return numberOfEventsFound == 0;
    }
}
