package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiModule;

import java.io.IOException;

public class AddModCommand extends Command {
    private final Module moduleToAdd;
    private final int moduleIndexToAdd;

    public AddModCommand(Module moduleToAdd, ModuleList moduleMasterList,
                         ModuleList moduleSelectedList) throws IOException {

        this.moduleToAdd = moduleToAdd;
        this.moduleIndexToAdd = moduleToAdd.getMasterListIndex(moduleMasterList);
        assert moduleToAdd.getModuleCode() != null;
        moduleSelectedList.addModule(moduleToAdd);
        assert moduleSelectedList.getSize() != 0;
        assert moduleSelectedList.isModuleExist(moduleToAdd.getModuleCode());
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1)
                .getModuleName().equals(moduleToAdd.getModuleName());
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1)
                .getModuleCode().equals(moduleToAdd.getModuleCode());
        storage.updateSelectedModuleList(moduleSelectedList);
        System.out.println("New module added: ");
        UiModule.printModule(moduleToAdd, moduleIndexToAdd, true);
    }
}