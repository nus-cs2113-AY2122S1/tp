package seedu.duke.commands;

import seedu.duke.exceptions.KolinuxException;
import seedu.duke.planner.Event;
import seedu.duke.planner.Planner;

public class PlannerCommand extends Command {

    private String subCommand;
    private String[] parsedArguments;

    private static final String ADD_EVENT_MESSAGE = "An event has been added to your schedule successfully!";
    private static final String CLEAR_EVENT_MESSAGE = "All the events in your schedule has been cleared.";
    private static final String INVALID_ARGUMENT_MESSAGE =
            "This command is not recognised, you can try:\n"
                    + "planner add DESCRIPTION/DATE/START_TIME/END_TIME\n"
                    + "planner list DATE\n"
                    + "planner clear";

    public PlannerCommand(String subCommand, String[] parsedArguments) {
        this.subCommand = subCommand;
        this.parsedArguments = parsedArguments;
    }

    @Override
    public CommandResult executeCommand() throws KolinuxException {
        switch (subCommand) {
        case "add":
            Event event = new Event(parsedArguments);
            Planner.addEvent(event);
            return new CommandResult(ADD_EVENT_MESSAGE);
        case "list":
            String date = parsedArguments[0];
            String eventList = Planner.listEvents(date);
            return new CommandResult(date + eventList);
        case "clear":
            Planner.clearEvents();
            return new CommandResult(CLEAR_EVENT_MESSAGE);
        default:
            throw new KolinuxException(INVALID_ARGUMENT_MESSAGE);
        }
    }
}
