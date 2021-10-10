package seedu.duke.commands;

import seedu.duke.universities.University;

public class AddUniCommand extends Command {
    public static final String COMMAND_WORD = "adduni";

    private final University universityToAdd;

    public AddUniCommand(University universityToAdd) {
        this.universityToAdd = universityToAdd;
        universityList.addUniversity(universityToAdd);
        System.out.println("New university added: " + universityToAdd.getName());
    }
}