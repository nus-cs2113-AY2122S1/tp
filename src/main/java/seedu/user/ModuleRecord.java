package seedu.user;

import seedu.exceptions.UniModsException;
import seedu.module.GradedModule;
import seedu.module.Module;
import seedu.module.PrerequisiteTree;
import seedu.module.UngradedModule;
import seedu.ui.TextUi;
import seedu.ui.TranscriptUi;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;


public class ModuleRecord {
    private final ArrayList<GradedModule> gradedModules = new ArrayList<>();
    private final ArrayList<UngradedModule> ungradedModules = new ArrayList<>();
    private final SortedSet<String> moduleCodes = new TreeSet<>();
    private double cap;


    public ModuleRecord() {
        cap = 0;
    }

    /**
     * Adds a graded Module to the Transcript.
     *
     * @param module Module contains the graded module to be stored in the Transcript
     */
    public void addModuleToRecord(GradedModule module) throws UniModsException {
        boolean isModInRecords = checkModuleExists(module.getModuleCode());
        if (isModInRecords) {
            throw new UniModsException(TextUi.ERROR_MODULE_FOUND);
        } else {
            gradedModules.add(module);
            moduleCodes.add(module.getModuleCode());
            cap = calculateCapFromList();
            TextUi.printAddedGrade(module.getModuleCode(), module.getGrade());
        }
    }

    /**
     * Adds an ungraded Module to the Transcript.
     *
     * @param module Module contains the ungraded module to be stored in the Transcript
     */
    public void addModuleToRecord(UngradedModule module) throws UniModsException {
        boolean isModInRecords = checkModuleExists(module.getModuleCode());
        if (isModInRecords) {
            throw new UniModsException(TextUi.ERROR_MODULE_FOUND);
        } else {
            ungradedModules.add(module);
            moduleCodes.add(module.getModuleCode());
            TextUi.printAddedGrade(module.getModuleCode(), module.getGrade());
        }
    }


    /**
     * Checks if the grade is valid and then calls the function to store module and grade in the transcript.
     *
     * @param module Module stores the Module to be stored in the Transcript
     * @param grade  Grade stores the grade scored in the module which is to be stored in the Transcript
     */
    public void addModuleToRecord(Module module, String grade) {
        String gradeType = "";
        try {
            gradeType = Module.checkGradeType(grade);
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
        }
        try {
            if (gradeType.equals(TextUi.GRADED)) {
                this.addModuleToRecord(module.toGradedModule(grade));
            } else {
                this.addModuleToRecord(module.toUngradedModule(grade));
            }
        } catch (UniModsException e) {
            System.out.print("");
        }
    }

    /**
     * Removes the module from the Transcript records.
     *
     * @param moduleCode ModuleCode is the module code which is to be removed from the Transcript.
     * @throws UniModsException IF the module is not found in the transcript records.
     */
    public void removeModuleFromTranscript(String moduleCode) throws UniModsException {
        moduleCodes.remove(moduleCode);
        for (int i = 0; i < gradedModules.size(); i++) {
            if (gradedModules.get(i).getModuleCode().equals(moduleCode)) {
                gradedModules.remove(gradedModules.get(i));
                TextUi.printModuleRemoved(moduleCode);
                return;
            }
        }
        for (int i = 0; i < ungradedModules.size(); i++) {
            if (ungradedModules.get(i).getModuleCode().equals(moduleCode)) {
                ungradedModules.remove(ungradedModules.get(i));
                TextUi.printModuleRemoved(moduleCode);
                return;
            }
        }
        throw new UniModsException(TextUi.ERROR_MODULE_NOT_IN_TRANSCRIPT);
    }


    /**
     * Calculates and returns the CAP of the user.
     *
     * @return cap CAP according to the user's grades in the transcript's records.
     */
    private double calculateCapFromList() {
        double sumModGradePoints = 0;
        double sumModCredits = 0;
        double moduleCredits;
        double moduleGradePoint;
        for (int i = 0; i < gradedModules.size(); i++) {
            moduleCredits = (gradedModules.get(i)).getModuleCredit();
            moduleGradePoint = gradedModules.get(i).getEquivalentCap(gradedModules.get(i).getGrade());
            sumModGradePoints = (moduleCredits * moduleGradePoint) + sumModGradePoints;
            sumModCredits = sumModCredits + moduleCredits;
        }
        if (sumModCredits != 0) {
            cap = sumModGradePoints / sumModCredits;
            cap = Math.round(cap * 100) / 100.0;
            return cap;
        }
        return 0.0;
    }

    public double calculateCap() {
        return calculateCapFromList();
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

    public void printGradedModuleInfo() {
        for (int i = 0; i < gradedModules.size(); i++) {
            TranscriptUi.printGradedModules(gradedModules.get(i));
        }
    }

    public void printUngradedModuleInfo() {
        for (int i = 0; i < ungradedModules.size(); i++) {
            TranscriptUi.printUngradedModules(ungradedModules.get(i));
        }
    }

    public void clearTranscript() throws UniModsException {
        if (gradedModules.size() == TextUi.ZERO && ungradedModules.size() == TextUi.ZERO) {
            throw new UniModsException(TextUi.ERROR_EMPTY_TRANSCRIPT);
        }
        gradedModules.clear();
        ungradedModules.clear();
        TextUi.printTranscriptCleared();
    }

    public boolean checkModuleExists(String moduleCode) {
        for (int i = 0; i < gradedModules.size(); i++) {
            if (gradedModules.get(i).getModuleCode().equals(moduleCode)) {
                return true;
            }
        }
        for (int i = 0; i < ungradedModules.size(); i++) {
            if (ungradedModules.get(i).getModuleCode().equals(moduleCode)) {
                return true;
            }
        }
        return false;
    }
}


