package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.universities.UniversityList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class AddModCommand extends Command {
    public static final String COMMAND_WORD = "addmod";

    private final Module moduleToAdd;

    public AddModCommand(Module moduleToAdd, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws IOException {
        super(universitySelectedList, moduleSelectedList);
        this.moduleToAdd = moduleToAdd;
        assert moduleToAdd.getModuleCode() != null;
        moduleSelectedList.addModule(moduleToAdd);
        assert moduleSelectedList.getSize() != 0;
        assert moduleSelectedList.searchModule(moduleToAdd.getModuleCode());
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1)
                .getModuleName().equals(moduleToAdd.getModuleName());
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1)
                .getModuleCode().equals(moduleToAdd.getModuleCode());
        SelectedModuleStorage.write(moduleSelectedList);
        System.out.println("New module added: ");
        Ui.printIndex(indexToAdd, 0);
        Ui.printModule(moduleToAdd, indexToAdd);
    }
}