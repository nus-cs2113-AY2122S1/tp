package seedu.duke.model.module;

import seedu.duke.DukeException;
import seedu.duke.commons.core.Messages;
import seedu.duke.model.module.exceptions.ModuleIndexException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

//@@author ptejasv
/**
 * This class is used to store and manipulate the user's list of modules that they can modify using command line
 * commands.
 */
public class ModuleList {
    private final List<Module> moduleList;

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

    public Module getModule(String moduleCode) throws DukeException {
        for (Module module : moduleList) {
            if (module.getModuleCode().equals(moduleCode)) {
                return module;
            }
        }
        throw new DukeException("The module is not in the list.");
    }

    public boolean isEmpty() {
        return moduleList.isEmpty();
    }

    public void addModule(Module newModule) throws DukeException {
        for (Module module : moduleList) {
            if (module.getModuleCode().equals(newModule.getModuleCode())) {
                throw new DukeException("You have already added that module.");
            }
        }
        newModule.setGrade("NONE");
        moduleList.add(newModule);
    }

    public void deleteModule(String moduleCode) throws DukeException {
        try {
            Module module = getModule(moduleCode);
            moduleList.remove(module);
        } catch (NullPointerException e) {
            throw new DukeException("The module is not in the list.");
        }
    }

    public void deleteAllModules() {
        moduleList.clear();
    }

    //@@author Roycius
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

    public int getTotalMcs() {
        int totalMcs = 0;
        for (Module module : moduleList) {
            totalMcs += module.getModuleCredit();
        }
        return totalMcs;
    }

    //@@author rebchua39
    public double calculateCap() {
        int totalModuleCredit = 0;
        double totalWeightedGradePoint = 0;
        for (Module module : moduleList) {
            String actualGrade = module.getGrade();
            double gradePoint = module.getGradePoint(actualGrade);
            boolean isGradePointValid = (gradePoint >= 0);
            if (isGradePointValid) {
                int moduleCredit = module.getModuleCredit();
                totalModuleCredit += moduleCredit;
                totalWeightedGradePoint += moduleCredit * gradePoint;
            }
        }
        if (totalModuleCredit == 0) {
            return -1;
        }
        return totalWeightedGradePoint / totalModuleCredit;
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
