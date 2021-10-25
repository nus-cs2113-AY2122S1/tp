package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiModule;

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