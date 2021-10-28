package taa.module;

//@@author leyondlee
import taa.ClassChecker;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.student.Student;
import taa.student.StudentList;

import java.util.ArrayList;
import java.util.HashMap;

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

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
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

        return String.format("%s - %s", code, name);
    }

    /**
     * Checks if the variables in the class are valid.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        if (code.isEmpty()) {
            return false;
        }

        assert (assessmentList != null && studentList != null);

        HashMap<String, Assessment> assessmentMap = new HashMap<>();
        for (Assessment assessment : assessmentList.getAssessments()) {
            assessmentMap.put(assessment.getName(), assessment);
        }

        for (Student student : studentList.getStudents()) {
            ArrayList<String> invalidMarks = new ArrayList<>();
            HashMap<String, Double> results = student.getResults();
            for (String assessmentName : results.keySet()) {
                if (!assessmentMap.containsKey(assessmentName)) {
                    invalidMarks.add(assessmentName);
                    continue;
                }

                Assessment assessment = assessmentMap.get(assessmentName);
                double marks = results.get(assessmentName);
                if (!assessment.isMarksValid(marks)) {
                    invalidMarks.add(assessmentName);
                }
            }

            for (String assessmentName : invalidMarks) {
                student.deleteMark(assessmentName);
            }
        }

        return true;
    }
}
