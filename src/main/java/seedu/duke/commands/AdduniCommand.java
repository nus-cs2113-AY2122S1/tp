package seedu.duke.commands;

import seedu.duke.Classes.*;

public class AdduniCommand extends Command {
    public static final String COMMAND_WORD = "adduni";

    private final University universityToAdd;

    //public AdduniCommand(String moduleCode) {
    //  String university Name = ;
    //  this.moduleToAdd = new module (moduleCode, moduleName, moduleCredits);
    //}

    public AdduniCommand(University universityToAdd) {
        this.universityToAdd = universityToAdd;
//        try {
        universityList.addUniversity(universityToAdd);
        System.out.println("New university added: " + universityToAdd.getName());
        //or with getName();
        //} catch (UniversityList.DuplicateUniversityException e) {
//            System.out.println("This university already exists in the university list!");
//    }
    }
}