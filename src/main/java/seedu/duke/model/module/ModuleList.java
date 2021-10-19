package seedu.duke.model.module;

import seedu.duke.DukeException;
import seedu.duke.commons.core.Messages;
import seedu.duke.model.module.exceptions.ModuleIndexException;
import seedu.duke.model.task.Task;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store and manipulate the user's list of modules that they can modify using command line
 * commands.
 */
public class ModuleList {
    private List<Module> moduleList;

    public ModuleList() {
        moduleList = new ArrayList<>();
    }

    public ModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public int getSize() {
        return moduleList.size();
    }

    public Module getModule(int moduleIndex) throws DukeException {
        try {
            return moduleList.get(moduleIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.ERROR_INVALID_INDEX);
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.ERROR_INVALID_NUMBER);
        }
    }

    public boolean isEmpty() {
        return moduleList.isEmpty();
    }

    public void addModule(Module newModule) {
        moduleList.add(newModule);
    }

    public void deleteModule(int moduleIndex) throws ModuleIndexException {
        try {
            moduleList.remove(moduleIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ModuleIndexException(Messages.ERROR_INVALID_INDEX);
        } catch (NumberFormatException e) {
            throw new ModuleIndexException(Messages.ERROR_INVALID_NUMBER);
        }
    }

    public String serialize() {
        StringBuilder data = new StringBuilder();
        for (Module module : moduleList) {
            data.append(module.serialize()).append(System.lineSeparator());
        }
        return data.toString();
    }

    public static List<Module> deserialize(Ui ui, List<String> data) {
        List<Module> moduleList = new ArrayList<>();
        for (String line : data) {
            Module module = Module.deserialize(ui, line);
            if (module != null) {
                moduleList.add(module);
            }
        }
        return moduleList;
    }

    public void deleteAllModules() {
        moduleList.clear();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < moduleList.size(); i++) {
            Module module = moduleList.get(i);
            s.append(Ui.PADDING).append(i + 1).append(". ").append(module).append(System.lineSeparator());
        }
        return s.toString();
    }
}
