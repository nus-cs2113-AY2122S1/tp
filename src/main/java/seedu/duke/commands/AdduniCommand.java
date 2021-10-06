package seedu.duke.commands;

import seedu.duke.classes.University;

public class AdduniCommand extends Command {
    public static final String COMMAND_WORD = "adduni";

    private final University universityToAdd;

    public AdduniCommand(University universityToAdd) {
        this.universityToAdd = universityToAdd;
        universityList.addUniversity(universityToAdd);
        System.out.println("New university added: " + universityToAdd.getName());
    }
}