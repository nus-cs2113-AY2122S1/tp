package taa.classmodel;

//@@author leyondlee
import taa.ClassChecker;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.student.Student;
import taa.student.StudentList;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassObject implements ClassChecker {
    private String id;
    private String name;
    private final StudentList studentList;
    private final AssessmentList assessmentList;

    public ClassObject(String id, String name) {
        this.id = id;
        this.name = name;
        this.studentList = new StudentList();
        this.assessmentList = new AssessmentList();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the StudentList object.
     *
     * @return A StudentList object.
     */
    public StudentList getStudentList() {
        return studentList;
    }

    /**
     * Gets the AssessmentList object.
     *
     * @return A AssessmentList object.
     */
    public AssessmentList getAssessmentList() {
        return assessmentList;
    }

    @Override
    public String toString() {
        if (name.isEmpty()) {
            return id;
        }

        return String.format("%s - %s", id, name);
    }

    /**
     * Checks if the variables in the class are valid.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        if (id.isEmpty()) {
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
