package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

public class SearchMapCommand extends Command {
    public static final String COMMAND_WORD = "searchmap";

    private final University universityToMap;

    public SearchMapCommand(University universityToMap, UniversityList universitySelectedList,
                            ModuleList moduleSelectedList) {
        super(universitySelectedList, moduleSelectedList);
        assert universityToMap.getName() != null;
        assert universityToMap.getClass() != null;
        this.universityToMap = universityToMap;
        System.out.println("Potential mappings for " + universityToMap.getName() + ": ");
        universityToMap.listMappings();
    }

    public University getUniversityToMap() {
        assert universityToMap.getName() != null;
        assert universityToMap.getClass() != null;
        return universityToMap;
    }
}