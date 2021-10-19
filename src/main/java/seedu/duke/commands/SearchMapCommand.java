package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

public class SearchMapCommand extends Command {
    public static final String COMMAND_WORD = "searchmap";

    private final University selectedUniversity;

    public SearchMapCommand(University selectedUniversity, UniversityList universitySelectedList,
                            ModuleList moduleSelectedList) {
        super(universitySelectedList, moduleSelectedList);
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        this.selectedUniversity = selectedUniversity;
        System.out.println("Potential mappings for " + selectedUniversity.getName() + ": ");
        selectedUniversity.listSelectedMappings(moduleSelectedList);
    }

    public University getSelectedUniversity() {
        assert selectedUniversity.getName() != null;
        assert selectedUniversity.getClass() != null;
        return selectedUniversity;
    }

}