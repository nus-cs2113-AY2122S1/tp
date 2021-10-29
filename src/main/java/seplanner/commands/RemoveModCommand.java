package seplanner.commands;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.ui.UiModule;

import java.io.IOException;

public class RemoveModCommand extends Command {
    private final Module moduleToRemove;
    private final int moduleIndexToRemove;

    public RemoveModCommand(Module moduleToRemove, ModuleList moduleMasterList,
                            ModuleList moduleSelectedList) throws IOException {
        this.moduleToRemove = moduleToRemove;
        this.moduleIndexToRemove = moduleToRemove.getMasterListIndex(moduleMasterList);
        assert moduleToRemove.getModuleCode() != null;
        moduleSelectedList.removeModule(moduleToRemove.getModuleCode());
        assert moduleSelectedList.getSize() != 0;
        storage.updateSelectedModuleList(moduleSelectedList);
        System.out.println("This module is removed: ");
        UiModule.printModule(moduleToRemove, moduleIndexToRemove, false);
    }
}