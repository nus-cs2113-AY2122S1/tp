package seplanner.commands;

import seplanner.enumerations.FindModInputType;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.ui.UiInvalid;
import seplanner.ui.UiModule;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Command to find a module in the master module list.
*/
public class FindModCommand extends Command {

    /**
     * Find a module in the master module list, and print its information.
     *
     * @param userInput input from the user, which can contain the module code or the module name.
     * @param moduleMasterList The master module list which contains all modules.
     * @param type type of input, which is either module code or module name.
    */
    public FindModCommand(String userInput, ModuleList moduleMasterList, FindModInputType type) {
        ArrayList<Module> result = (ArrayList<Module>) moduleMasterList.getList().stream()
                .filter((module) ->
                (((type == FindModInputType.MODULECODE) ? module.getModuleCode() : module.getModuleName())
                .toLowerCase().contains(userInput.toLowerCase())))
                .collect(Collectors.toList());
        if (result.size() == 0) {
            UiInvalid.printFindModNull();
        } else {
            for (int i = 0; i < result.size(); i++) {
                UiModule.printModule(result.get(i), result.get(i).getMasterListIndex(moduleMasterList), false);
            }
        }
    }

}

