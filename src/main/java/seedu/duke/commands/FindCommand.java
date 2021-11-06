package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import java.util.Arrays;


public class FindCommand extends Command {

    private static final int MAX_SIZE = Duke.eventCatalog.size();

    private static String keyword;
    private static Event[] filteredList = new Event[MAX_SIZE];
    private static int numberOfEvents;

    public FindCommand(String[] command) {

    }

    public CommandResult execute() {

    }
}
