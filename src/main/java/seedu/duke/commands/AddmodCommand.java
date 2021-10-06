package seedu.duke.commands;

import seedu.duke.Classes.*;
import seedu.duke.Classes.Module;

public class AddmodCommand extends Command {
    public static final String COMMAND_WORD = "addmod";

    private final Module moduleToAdd;

    //public AddmodCommand(String moduleCode) {
    //  String moduleName = ;
    //  int moduleCredits = ;
    //  this.moduleToAdd = new module (moduleCode, moduleName, moduleCredits);
    //}

    public AddmodCommand(Module moduleToAdd) {
        this.moduleToAdd = moduleToAdd;
//        try {
        selectedModuleList.addModule(moduleToAdd);
        System.out.println("New module added: " + moduleToAdd.getModuleCode());
        //} catch (selectedModuleList.DuplicateModuleException e) {
//            System.out.println("This module already exists in the module list!");
//    }
    }
}