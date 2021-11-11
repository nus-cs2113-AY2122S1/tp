package seedu.module;

import java.util.ArrayList;

public class PrerequisiteTree {

    private ArrayList<PrerequisiteTree> trees = new ArrayList<>();
    private ArrayList<String> modules = new ArrayList<>();
    private String condition;

    public PrerequisiteTree() {
    }

    public String getCondition() {
        return condition;
    }

    public ArrayList<PrerequisiteTree> getTrees() {
        return trees;
    }

    public ArrayList<String> getModules() {
        return modules;
    }

    public void addModule(String moduleCode) {
        modules.add(moduleCode);
    }

    public void addTree(PrerequisiteTree tree) {
        trees.add(tree);
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
