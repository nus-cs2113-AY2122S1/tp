package seedu.duke.commands;

import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidBudgetException;
import seedu.duke.items.Event;

import java.time.LocalDateTime;


public class UpdateEventCommand extends Command{

    private static final int INDEX_OF_TITLE = 0;
    private static final int INDEX_OF_VENUE = 2;
    private static final int INDEX_OF_DESCRIPTION = 1;

    private final String[] updates;
    private final Event eventToBeUpdated;
    private final LocalDateTime dateTime;
    private final double budget;

    public UpdateEventCommand(Event event, String[] parsedAttributes, LocalDateTime newDateTime, double newBudget) {
        this.updates = parsedAttributes;
        this.eventToBeUpdated = event;
        this.dateTime = newDateTime;
        this.budget = newBudget;
    }

    public CommandResult execute() throws DukeException, InvalidBudgetException {
        if (!updates[INDEX_OF_TITLE].equalsIgnoreCase("")) {
            eventToBeUpdated.setTitle(updates[INDEX_OF_TITLE]);
        } else if (dateTime != null) {
            eventToBeUpdated.setDateTime(dateTime);
        } else if (!updates[INDEX_OF_VENUE].equalsIgnoreCase("")) {
            eventToBeUpdated.setVenue(updates[INDEX_OF_VENUE]);
        } else if (budget != 0) {
            eventToBeUpdated.setBudget(budget);
        } else if (!updates[INDEX_OF_DESCRIPTION].equalsIgnoreCase("")) {
            eventToBeUpdated.setDescription(updates[INDEX_OF_DESCRIPTION]);
        }
        Ui.postUpdateMessage(eventToBeUpdated);
        return new CommandResult(Ui.updateExitMessage());
    }
}
