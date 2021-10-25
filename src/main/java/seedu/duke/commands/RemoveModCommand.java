package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class RemoveModCommand extends Command {
    private final Module moduleToRemove;
    private final int moduleIndexToRemove;

    public RemoveModCommand(int moduleIndexToRemove, ModuleList moduleMasterList,
                            ModuleList moduleSelectedList) throws IOException {
        this.moduleIndexToRemove = moduleIndexToRemove;
        this.moduleToRemove = moduleMasterList.get(moduleIndexToRemove - 1);
        if (moduleSelectedList.getSize() == 0) {
            assert moduleSelectedList.getSize() == 0;
            System.out.println("The module list is empty!");
        } else {
            assert moduleToRemove.getModuleName() != null;
            assert moduleSelectedList.getSize() != 0;
            moduleSelectedList.removeModule(moduleToRemove.getModuleCode());
            assert !moduleSelectedList.isModuleExist(moduleToRemove.getModuleCode());
            storage.updateSelectedModuleList(moduleSelectedList);
            System.out.println("This module is removed: ");
            Ui.printModule(moduleToRemove, moduleIndexToRemove);
        }
    }

    public RemoveModCommand(Module moduleToRemove, ModuleList moduleMasterList,
                            ModuleList moduleSelectedList) throws IOException {
        this.moduleToRemove = moduleToRemove;
        this.moduleIndexToRemove = moduleToRemove.getModuleIndex(moduleMasterList);
        assert moduleToRemove.getModuleCode() != null;
        moduleSelectedList.removeModule(moduleToRemove.getModuleCode());
        assert moduleSelectedList.getSize() != 0;
        storage.updateSelectedModuleList(moduleSelectedList);
        System.out.println("This module is removed: ");
        Ui.printModule(moduleToRemove, moduleIndexToRemove);
    }
}