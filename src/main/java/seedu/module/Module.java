package seedu.module;

import com.google.gson.annotations.JsonAdapter;
import seedu.command.flags.SearchFlags;
import seedu.exceptions.UniModsException;
import seedu.online.PrerequisiteTreeAdapterFactory;
import seedu.ui.TextUi;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.text.WordUtils.wrap;

public class Module extends BaseModule {
    private static final int FINALISE_INDEX = 1;

    private String department;
    private String faculty;
    private String prerequisite;
    private String preclusion;
    private String corequisite;
    private String acadYear;
    private Attributes attributes;
    private ArrayList<Semester> semesterData;
    private static final String[] GRADES = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D+", "D", "F"};
    private static final String[] SU_GRADES = {"S", "U", "CS", "CU"};


    @JsonAdapter(PrerequisiteTreeAdapterFactory.class)
    private PrerequisiteTree prereqTree;

    private static final int CODE_LENGTH = 4;
    private static final int THOUSAND = 1000;

    public Module(String moduleCode) {
        super(moduleCode);
    }

    public GradedModule toGradedModule(String grade) {
        return new GradedModule(this, grade);
    }

    public UngradedModule toUngradedModule(String grade) {
        return new UngradedModule(this, grade);
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

    public PrerequisiteTree getPrerequisiteTree() {
        return prereqTree;
    }

    public String getPrerequisite() {
        return prerequisite;
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

    /**
     * Returns a string containing the following information from the module: Title, MCs, Department, Description,
     * Prereqs, S/Uable, Semester Availability.
     * @return String containing the full information of the module.
     */
    public String getFullInfo() {
        String isSUable = "No data";
        if (attributes != null) {
            isSUable = attributes.isSUable();
        }
        String fullInfo = "Title: " + title + "\n\n"
                + "MCs: " + (int) moduleCredit + "\n\n"
                + "Department: " + department + "\n\n"
                + wrap(description, 70) + "\n\n"
                + "Prerequisites: " + prerequisite + "\n\n"
                + "S/U able: " + isSUable + "\n\n"
                + "Semester Availability: " + semesterData + "\n\n"
                + "Exam Date(s): " + getAllExams() + "\n\n";
        return fullInfo;
    }

    /**
     * First checks if the module matches the level specified in searchFlags, and if it does, then checks if the
     * module code contains the searchTerm. These two conditions are classified as preliminary as they can be checked
     * with the information from the moduleList that contains all mods. Further checks require pulling the moduleInfo
     * json for each specific module, and will not be done should this check fail.
     *
     * @param searchTerm search term that module code is checked to contain.
     * @param searchFlags search flags containing additional conditions.
     * @return true if conditions are met, false otherwise
     */
    public boolean meetsPreliminaryConditions(String searchTerm, SearchFlags searchFlags) {
        if (searchFlags.getHasLevelFlag()) {
            if (!isSameLevel(searchFlags.getLevel())) {
                return false;
            }
        }
        return codeContains(searchTerm);
    }

    /**
     * Checks if the module code contains the specified search term.
     *
     * @param searchTerm search term that the module code is checked to contain.
     * @return true if module code contains searchTerm, false otherwise.
     */
    private boolean codeContains(String searchTerm) {
        return moduleCode.toLowerCase().contains(searchTerm.toLowerCase());
    }

    /**
     * Checks if the module matches the specified level. Does this by removing all non-numeric characters from the
     * code and dividing by 1000, then checking it against the level divided by 1000.
     *
     * @param level Level that module is checked against.
     * @return true if module is of the same level specified, false otherwise.
     */
    private boolean isSameLevel(int level) {
        String modNumericCode = moduleCode.replaceAll("[^0-9]", "");
        int modLevel = Integer.parseInt(modNumericCode) / THOUSAND;
        return modLevel == level / THOUSAND;
    }

    /**
     * Checks all remaining conditions which are MCs, Faculty, Department, Exam and Semester, in that order. If any
     * of these do not match, immediately returns false. Returns true otherwise.
     *
     * @param searchFlags SearchFlags object containing all flags and information to check against.
     * @return true if all secondary conditions are matched, false otherwise.
     */
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

    /**
     * Checks if the module has exams, and returns true if the result matches hasExams and false otherwise.
     *
     * @param hasExams input to check for, if the module has (true) or does not (false) have exams.
     * @return true if module matches specified input (e.g. hasExams = true, returns true if module has exams.)
     */
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

    public String getAllExams() {
        String examdates = "";
        boolean hasExam = false;
        for (Semester semester : semesterData) {
            if (semester.getExamDate() != null) {
                examdates += "Sem " + semester.getSemester() + ": " + semester.getExamInfo()
                        + "\n" + "              "; //placeholder
                hasExam = true;
            }
        }
        if (!hasExam) {
            return "No Exam";
        }
        return examdates;
    }

    public String getExam(int sem) {
        for (Semester semester : semesterData) {
            if (semester.getSemester() == sem) {
                if (semester.getExamDate() != null) {
                    return String.format("(Exam: %s)", semester.getExamInfo());
                }
                break;
            }
        }
        return "(No Exam)";
    }

    /**
     * Checks if the module is offered in the specified semester.
     *
     * @param semester semester to be specified.
     * @return true if module is offered in specified semester, false otherwise.
     */
    private boolean hasSemester(int semester) {
        for (Semester s : semesterData) {
            if (s.getSemester() == semester) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for the type and validity of the grade.
     *
     * @param gradeToCheck GradeToCheck stores the grade that is to be checked
     * @return gradeType which is the type of the grade
     * @throws UniModsException If grade is invalid
     */
    public static String checkGradeType(String gradeToCheck) throws UniModsException {
        String gradeType = "";
        for (int i = 0; i < GRADES.length; i++) {
            if (gradeToCheck.equals(GRADES[i])) {
                gradeType = TextUi.GRADED;
                return gradeType;
            }
        }
        for (int i = 0; i < SU_GRADES.length; i++) {
            if (gradeToCheck.equals(SU_GRADES[i])) {
                gradeType = TextUi.UNGRADED;
                return gradeType;
            }
        }
        throw new UniModsException(TextUi.ERROR_INVALID_GRADE);

    }
}
