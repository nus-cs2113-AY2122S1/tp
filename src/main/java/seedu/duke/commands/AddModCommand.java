package seedu.duke.commands;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.SelectedModuleStorage;
import seedu.duke.universities.UniversityList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class AddModCommand extends AddCommand {

    private final Module moduleToAdd;
    private final int moduleIndexToAdd;

    public AddModCommand(int moduleIndexToAdd, ModuleList moduleSelectedList)
            throws IOException {
        this.moduleIndexToAdd = moduleIndexToAdd;
        this.moduleToAdd = moduleSelectedList.get(moduleIndexToAdd);
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
        Ui.printModule(moduleToAdd, moduleIndexToAdd);
    }

    public AddModCommand(Module moduleToAdd, UniversityList universitySelectedList,
                         ModuleList moduleSelectedList) throws IOException{
        this.moduleToAdd = moduleToAdd;
        this.moduleIndexToAdd = moduleToAdd.getModuleIndex(moduleSelectedList);
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
        Ui.printIndex(moduleIndexToAdd, false);
        Ui.printModule(moduleToAdd, moduleIndexToAdd);
    }
}