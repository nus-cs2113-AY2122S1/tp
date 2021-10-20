package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.storage.SelectedUniversityStorage;
import seedu.duke.ui.Ui;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;

public class RemoveModCommand extends Command {
    public RemoveModCommand(int moduleIndexToRemove, ModuleList moduleMasterList,
                            ModuleList moduleSelectedList) throws IOException {
        if (moduleSelectedList.getSize() == 0) {
            assert moduleSelectedList.getSize() == 0;
            System.out.println("The module list is empty!");
        } else {
            Module moduleToRemove = moduleMasterList.get(moduleIndexToRemove - 1);
            assert moduleToRemove.getModuleName() != null;
            assert moduleSelectedList.getSize() != 0;
            moduleSelectedList.removeModule(moduleToRemove.getModuleCode());
            assert !moduleSelectedList.searchModule(moduleToRemove.getModuleCode());
            SelectedModuleStorage.write(moduleSelectedList);
            System.out.println("This module is removed: ");
            Ui.printModule(moduleToRemove, moduleIndexToRemove);
        }
    }
}
