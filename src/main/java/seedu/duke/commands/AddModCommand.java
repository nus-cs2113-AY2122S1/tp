package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

public class AddModCommand extends Command {
    public static final String COMMAND_WORD = "addmod";

    private final Module moduleToAdd;

    public AddModCommand(Module moduleToAdd, UniversityList universitySelectedList, ModuleList moduleSelectedList) {
        super(universitySelectedList, moduleSelectedList);
        this.moduleToAdd = moduleToAdd;
        moduleSelectedList.addModule(moduleToAdd);
        System.out.println("New module added: " + moduleToAdd.getModuleCode());
    }
}