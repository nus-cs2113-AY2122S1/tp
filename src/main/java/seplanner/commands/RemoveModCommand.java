package seplanner.commands;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.ui.UiModule;

import java.io.IOException;

/**
 * Command to remove a module from the selected module list.
*/
public class RemoveModCommand extends Command {
    private final Module moduleToRemove;
    private final int moduleIndexToRemove;

    /**
     * Remove a module to the selected module list.
     *
     * @param moduleToRemove module to remove from the selected module list.
     * @param moduleMasterList The master module list which contains all modules.
     * @param moduleSelectedList The selected module list which contains only the module selected by the user.
     * @throws IOException If input-output operation failed.
    */
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
