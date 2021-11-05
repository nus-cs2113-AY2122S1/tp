package seplanner.commands;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.ui.UiModule;

import java.io.IOException;

/**
 * Command to add a module to the selected module list.
*/
public class AddModCommand extends Command {
    
    /**
     * Add a module to the selected module list.
     *
     * @param moduleToAdd module to add to the selected module list.
     * @param moduleMasterList The master module list which contains all modules.
     * @param moduleSelectedList The selected module list which contains only the module selected by the user.
     * @throws IOException If input-output operation failed.
    */
    public AddModCommand(Module moduleToAdd, ModuleList moduleMasterList,
                         ModuleList moduleSelectedList) throws IOException {
        assert moduleToAdd.getModuleCode() != null;
        moduleSelectedList.addModule(moduleToAdd);
        assert moduleSelectedList.getSize() != 0;
        assert moduleSelectedList.isModuleExist(moduleToAdd.getModuleCode());
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1)
                .getModuleName().equals(moduleToAdd.getModuleName());
        assert moduleSelectedList.get(moduleSelectedList.getSize() - 1)
                .getModuleCode().equals(moduleToAdd.getModuleCode());
        int moduleIndexToAdd = moduleToAdd.getMasterListIndex(moduleMasterList);
        storage.updateSelectedModuleList(moduleSelectedList);
        System.out.println("New module added: ");
        UiModule.printModule(moduleToAdd, moduleIndexToAdd, true);
    }
}
