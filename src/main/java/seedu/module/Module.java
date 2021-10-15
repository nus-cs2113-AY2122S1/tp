package seedu.module;

import seedu.command.flags.SearchFlags;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.text.WordUtils.wrap;

public class Module {
    private static final int FINALISE_INDEX = 1;

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

    private static final int CODE_LENGTH = 4;
    private static final int THOUSAND = 1000;

    public Module(String name) {
        this.moduleCode = name;
    }

    public double getModuleCredit() {
        return moduleCredit;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCredit(double moduleCredit) {
        this.moduleCredit = moduleCredit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Lesson getLesson(int sem, int idx) {
        for (Semester s : semesterData) {
            if (s.getSemester() == sem) {
                return s.getLesson(idx);
            }
        }
        return null;
    }

    public List<Integer> getSemesters() {
        return null;
    }

    public Semester getSemester(int sem) {
        for (Semester s : semesterData) {
            if (s.getSemester() == sem) {
                return s;
            }
        }
        //What is the point of this? Shouldn't we just print out an error
        return new Semester();
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
        String fullInfo = "Title: " + title + "\n"
                + "MCs: " + (int)moduleCredit + "\n"
                + "Department: " + department + "\n"
                + wrap(description, 70) + "\n"
                + "Prerequisites: " + prerequisite + "\n"
                + "S/U able: " + isSUable + "\n"
                + "Semester Availability: " + semesterData + "\n";
        //uncomment and test this with mods with special sems eg CS1010E, CS2040, CS3243. It should print the
        //weeks for two lessons, one from sem 1 and one from special term 2.
        //String test = getSemester(1).getLesson(0).getWeeks() + getSemester(4).getLesson(0).getWeeks();
        //return fullInfo + test;
        return fullInfo;
    }

    public boolean meetsPreliminaryConditions(String searchTerm, SearchFlags searchFlags) {
        if (searchFlags.getHasLevelFlag()) {
            if (!isSameLevel(searchFlags.getLevel())) {
                return false;
            }
        }
        return codeContains(searchTerm);
    }

    private boolean codeContains(String searchTerm) {
        return moduleCode.toLowerCase().contains(searchTerm.toLowerCase());
    }

    private boolean isSameLevel(int level) {
        String modNumericCode = moduleCode.replaceAll("[^0-9]", "");
        int modLevel = Integer.parseInt(modNumericCode) / THOUSAND;
        return modLevel == level / THOUSAND;
    }

    public boolean meetsSecondaryConditions(SearchFlags searchFlags) {
        if (searchFlags.getHasMcFlag()) {
            if (moduleCredit != searchFlags.getMcs()) {
                return false;
            }
        }
        if (searchFlags.getHasFacultyFlag()) {
            if (!faculty.equalsIgnoreCase(searchFlags.getFaculty())) {
                return false;
            }
        }
        if (searchFlags.getHasDepartmentFlag()) {
            if (!department.equalsIgnoreCase(searchFlags.getDepartment())) {
                return false;
            }
        }
        if (searchFlags.getHasExamFlag()) {
            if (!checkExams(searchFlags.getHasExam())) {
                return false;
            }
        }
        if (searchFlags.getHasSemesterFlag()) {
            if (!hasSemester(searchFlags.getSemester())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkExams(boolean hasExams) {
        if (hasExams) {
            for (Semester s : semesterData) {
                if (s.getExamDate() != null) {
                    return true;
                }
            }
            return false;
        } else {
            for (Semester s : semesterData) {
                if (s.getExamDate() != null) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean hasSemester(int semester) {
        for (Semester s : semesterData) {
            if (s.getSemester() == semester) {
                return true;
            }
        }
        return false;
    }

}
