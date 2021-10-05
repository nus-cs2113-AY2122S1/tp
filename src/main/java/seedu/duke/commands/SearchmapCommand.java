package seedu.duke.commands;

import duke.Classes.*;

public class SearchmapCommand extends Command {
    public static final String COMMAND_WORD = "searchmap";

    private final University universityToMap;

    public SearchmapCommand(University universityToMap) {
        this.universityToMap = universityToMap;
        System.out.println("Potential mappings for " + universityToMap.name + ": ");
        listMappings();
    }
}