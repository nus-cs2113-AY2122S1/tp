package seedu.duke.commands;

import seedu.duke.enumerations.ListType;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.ModuleStorage;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiModule;

import java.io.IOException;
import java.util.ArrayList;

public class ListModCommand extends Command {
    public ListModCommand(ModuleList moduleList, ListType type) {
        if (moduleList.getSize() == 0) {
            assert moduleList.getSize() == 0;
            System.out.println("The module list is empty!");
        } else {
            assert moduleList.getSize() > 0;
            System.out.println("Here are the modules in the list:");
            for (int i = 0; i < moduleList.getSize(); i++) {
                Module currentModule = moduleList.get(i);
                assert currentModule.getModuleCode() != null;
                UiModule.printModule(currentModule, currentModule.getIndex(), true);
            }
        }
    }
}
