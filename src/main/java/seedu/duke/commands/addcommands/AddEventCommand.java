package seedu.duke.commands.addcommands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.items.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {

    private final String title;
    private final String description;
    private final LocalDateTime dateTime;
    private final String venue;
    private final double budget;

    public AddEventCommand(String title, String description, LocalDateTime dateTime, String venue, double budget) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.venue = venue;
        this.budget = budget;
    }

    private void addToEventCatalog(Event event) {
        Duke.eventCatalog.add(event);
        Duke.eventCatalog.sortCatalog();
    }

    public CommandResult execute() {
        Event event = new Event(title, description, dateTime, venue, budget);
        addToEventCatalog(event);
        return new CommandResult(Ui.getEventAddedMessage(event));
    }
}
