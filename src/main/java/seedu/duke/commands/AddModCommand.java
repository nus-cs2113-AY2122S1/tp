package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddModCommand extends Command {
    public static final String COMMAND_WORD = "addmod";

    private Logger logger = Logger.getLogger("AddModCommandLog");

    private final Module moduleToAdd;

    public AddModCommand(Module moduleToAdd, UniversityList universitySelectedList, ModuleList moduleSelectedList) {
        super(universitySelectedList, moduleSelectedList);
        logger.log(Level.INFO, "Start parsing addmod command");
        this.moduleToAdd = moduleToAdd;
        assert moduleToAdd.getModuleCode() != null;
        moduleSelectedList.addModule(moduleToAdd);
        assert moduleSelectedList.getSize() != 0;
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1).getModuleName().equals(moduleToAdd.getModuleName());
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1).getModuleCode().equals(moduleToAdd.getModuleCode());
        System.out.println("New module added: " + moduleToAdd.getModuleCode());
    }
}