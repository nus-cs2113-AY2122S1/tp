package seedu.duke.command;

public class FindCommand extends Command {
    private String findType;
    private String keyword;

    public FindCommand(String findType, String keyword) {
        this.findType = findType;
        this.keyword = keyword;
    }
}
