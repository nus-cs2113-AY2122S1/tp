package seedu.duke.commands;

import seedu.duke.universities.University;

public class SearchMapCommand extends Command {
    public static final String COMMAND_WORD = "searchmap";

    private final University universityToMap;

    public SearchMapCommand(University universityToMap) {
        this.universityToMap = universityToMap;
        System.out.println("Potential mappings for " + universityToMap.getName() + ": ");
        universityToMap.listMappings();
    }

    public University getUniversityToMap() {
        return universityToMap;
    }
}