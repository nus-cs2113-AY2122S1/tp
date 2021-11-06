package seedu.duke.commands;


public class FindCommand extends Command {

    private final String findResults;


    public FindCommand(String findResults) {
        this.findResults = findResults;
    }

    public CommandResult execute() {
        return new CommandResult(findResults);
    }
}
