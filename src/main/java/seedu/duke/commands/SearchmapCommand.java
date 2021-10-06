package seedu.duke.commands;

import seedu.duke.classes.University;

public class SearchmapCommand extends Command {
    public static final String COMMAND_WORD = "searchmap";

    private final University universityToMap;

    public SearchmapCommand(University universityToMap) {
        this.universityToMap = universityToMap;
        System.out.println("Potential mappings for " + universityToMap.getName() + ": ");
        universityToMap.listMappings();
    }
}