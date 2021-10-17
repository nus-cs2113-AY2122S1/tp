package seedu.user;

import seedu.module.GradedModule;
import seedu.module.Module;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;


public class ModuleRecord implements Comparator<GradedModule> {

    private final SortedSet<GradedModule> modules = new TreeSet<>();
    private double cap;

    public ModuleRecord() {
        cap = 0;
    }

    public void addModuleToRecord(GradedModule module) {
        modules.add(module);
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

    @Override
    public int compare(GradedModule o1, GradedModule o2) {
        return o1.getModuleCode().compareTo(o2.getModuleCode());
    }

    public boolean isModulePrereqMet(Module module) {
        return true;
    }

    private boolean isModuleInRecord(String moduleCode) {
        return modules.contains(moduleCode);
    }

}
