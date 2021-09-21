package seedu.module;

import java.util.List;

public class Mod {

    private String moduleCode;
    private String title;
    private List<Integer> semesters;

    public Mod(String moduleCode, String title, List<Integer> semesters) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.semesters = semesters;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getSemesters() {
        return semesters;
    }

    @Override
    public String toString() {
        return moduleCode + " " + title + " " + semesters;
    }

}
