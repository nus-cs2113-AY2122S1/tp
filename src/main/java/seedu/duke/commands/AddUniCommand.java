package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

public class AddUniCommand extends Command {
    public static final String COMMAND_WORD = "adduni";

    private final University universityToAdd;

    public AddUniCommand(University universityToAdd, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) {
        super(universitySelectedList, moduleSelectedList);
        this.universityToAdd = universityToAdd;
        universitySelectedList.addUniversity(universityToAdd);
        System.out.println("New university added: " + universityToAdd.getName());
    }
}