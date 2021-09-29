package seedu.module;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.text.WordUtils.wrap;

public class Mod {

    private String moduleCode;
    private String title;
    private String description;
    private double moduleCredit;
    private String department;
    private String faculty;
    private String prerequisite;
    private String preclusion;
    private String corequisite;
    private String acadYear;
    private Attributes attributes;
    private ArrayList<Semester> semesterData;

    public Mod(String name) {
        this.moduleCode = name;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public List<Integer> getSemesters() {
        return null;
    }

    @Override
    public String toString() {
        return moduleCode + " " + title + " " + (int)moduleCredit + "MC";
    }

    public String getFullInfo() {
        String isSUable = "No data";
        if (attributes != null) {
            isSUable = attributes.isSUable();
        }
        String fullInfo = "Title: " + title + "\n" +
                "MCs: " + (int)moduleCredit + "\n" +
                "Department: " + department + "\n" +
                wrap(description, 70) + "\n" +
                "Prerequisites: " + prerequisite + "\n" +
                "S/U able: " + isSUable + "\n" +
                "Semester Availability: " + semesterData + "\n";
        return fullInfo;
    }

}
