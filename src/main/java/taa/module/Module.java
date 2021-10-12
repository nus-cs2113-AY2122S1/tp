package taa.module;

import taa.ClassChecker;
import taa.assessment.AssessmentList;
import taa.student.StudentList;

public class Module implements ClassChecker {
    private String code;
    private String name;
    private final StudentList studentList;
    private final AssessmentList assessmentList;

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.studentList = new StudentList();
        this.assessmentList = new AssessmentList();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the StudentList object associated to the module.
     *
     * @return A StudentList object.
     */
    public StudentList getStudentList() {
        return studentList;
    }

    /**
     * Gets the AssessmentList object associated to the module.
     *
     * @return A AssessmentList object.
     */
    public AssessmentList getAssessmentList() {
        return assessmentList;
    }

    @Override
    public String toString() {
        if (name.isEmpty()) {
            return code;
        }

        return String.format("%s (%s)", code, name);
    }

    /**
     * Checks if the variables in the class are valid.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        if (!studentList.verify()) {
            return false;
        }

        if (!assessmentList.verify()) {
            return false;
        }

        return true;
    }
}
