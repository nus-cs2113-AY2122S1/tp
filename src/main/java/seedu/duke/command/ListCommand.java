package seedu.duke.command;

public class ListCommand extends Command {
    private String listType;
    private String period;

    public ListCommand(String listType, String period) {
        this.listType = listType;
        this.period = period;
    }
}
