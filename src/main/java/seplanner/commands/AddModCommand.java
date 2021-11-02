package seplanner.commands;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.ui.UiModule;

import java.io.IOException;

public class AddModCommand extends Command {

    public AddModCommand(Module moduleToAdd, ModuleList moduleMasterList,
                         ModuleList moduleSelectedList) throws IOException {

        int moduleIndexToAdd = moduleToAdd.getMasterListIndex(moduleMasterList);
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