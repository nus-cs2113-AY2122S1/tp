package seedu.user;

import seedu.module.GradedModule;
import seedu.module.Module;
import seedu.module.PrerequisiteTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;


public class ModuleRecord {

    private final ArrayList<GradedModule> modules = new ArrayList<>();
    private final SortedSet<String> moduleCodes = new TreeSet<>();
    private double cap;

    public ModuleRecord() {
        cap = 0;
    }

    public void addModuleToRecord(GradedModule module) {
        modules.add(module);
        moduleCodes.add(module.getModuleCode());
        cap = calculateCapFromList();
    }

    public void addModuleToRecord(Module module, char grade) {
        this.addModuleToRecord(module.toGradedModule(grade));
    }


    public double getCap() {
        return cap;
    }

    private double calculateCapFromList() {
        //TODO calculate CAP from the module arraylist
        return 0;
    }

    /**
     * Recursively goes through the prerequisite tree to check if the all the prerequisites are met.
     * Takes reference from the modules recorded.
     *
     * @param module module to check if all prerequisites are met
     * @return true if all the prerequisites have been met for the module, false otherwise
     */
    public boolean isModulePrereqMet(Module module) {
        PrerequisiteTree tree = module.getPrerequisiteTree();
        return (tree == null || isTreeSatisfied(tree));
    }

    /**
     * Checks if the tree's prerequisites are met.
     * If its only a single module, checks if that module is present in the record.
     * Otherwise, checks if all the conditions (and/or) are satisfied in underlying recursive trees.
     *
     * @param tree Prerequisite Tree to be checked if all prerequisites are met
     * @return true if all prerequisites are met, false otherwise
     */
    private boolean isTreeSatisfied(PrerequisiteTree tree) {
        if (tree.getCondition() == null) {
            for (String module : tree.getModules()) {
                return isModuleInRecord(module);
            }
            return true;
        } else if (tree.getCondition() == "and") {
            return isAndTreeSatisfied(tree);
        } else {
            return isOrTreeSatisfied(tree);
        }
    }

    /**
     * Iterates through the module array and tree array to check if ANY of the prerequisites are
     * met.
     * Check cuts short if ANY prerequisite is met.
     *
     * @param tree Tree with a condition of "or" to be checked if prerequisite is met
     * @return true if any prerequisite in tree is met.
     */
    private boolean isOrTreeSatisfied(PrerequisiteTree tree) {
        // Check all modules in module list first
        for (String module : tree.getModules()) {
            if (isModuleInRecord(module)) {
                return true;
            }
        }

        for (PrerequisiteTree t : tree.getTrees()) {
            if (t.getCondition() == "or") {
                if (isOrTreeSatisfied(t)) {
                    return true;
                }
            } else {
                if (isAndTreeSatisfied(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Iterates through the module array and tree array to check if ALL of the prerequisites are
     * met.
     *
     * @param tree Tree with a condition of "and" to be checked if prerequisite is met
     * @return true if all prerequisites in tree are met.
     */
    private boolean isAndTreeSatisfied(PrerequisiteTree tree) {
        // Check all modules in module list first
        for (String module : tree.getModules()) {
            if (!isModuleInRecord(module)) {
                return false;
            }
        }

        // recurse through the trees
        for (PrerequisiteTree t : tree.getTrees()) {
            if (t.getCondition() == "and") {
                if (!isAndTreeSatisfied(t)) {
                    return false;
                }
            } else {
                if (!isOrTreeSatisfied(t)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isModuleInRecord(String moduleCode) {
        return moduleCodes.contains(moduleCode);
    }

}
