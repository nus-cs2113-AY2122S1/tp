package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.items.Event;


public class FindCommand extends Command {

    private final Event[] findResults;


    public FindCommand(Event[] findResults) {
        this.findResults = findResults;
    }

    public CommandResult execute() {
        String foundEvents;
        foundEvents = filteredEventsAsString(findResults);
        return new CommandResult(foundEvents);
    }

    private static String filteredEventsAsString(Event[] findResults) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < findResults.length; i++) {
            if (findResults[i] == null) {
                continue;
            }
            result.append((i + 1)).append(". ").append(findResults[i].getTitle());
            result.append("\n");
        }
        return result.toString();
    }
}
