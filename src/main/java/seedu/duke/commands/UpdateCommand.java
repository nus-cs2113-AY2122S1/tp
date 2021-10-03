package seedu.duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateCommand extends Command {
    protected String listType;
    protected String[] userCommand;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HHmm");


    public UpdateCommand(String[] command) {

    }

    public CommandResult execute() {
        return new CommandResult("updateCommand");
    }
}
