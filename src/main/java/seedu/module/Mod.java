package seedu.module;

import java.util.List;

public class Mod {

    private String moduleCode;
    private String title;
    private String description;
    private int moduleCredit;
    private String department;
    private String faculty;
    private String prerequisite;
    private String preclusion;
    private String corequisite;
    private List<Integer> semesters;


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
