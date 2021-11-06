package taa.teachingclass;

//@@author leyondlee
import taa.ClassChecker;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.student.Student;
import taa.student.StudentList;

import java.util.ArrayList;
import java.util.HashMap;

public class TeachingClass implements ClassChecker {
    private String id;
    private String name;
    private final StudentList studentList;
    private final AssessmentList assessmentList;

    public TeachingClass(String id, String name) {
        setId(id);
        setName(name);
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
        this.id = id.toLowerCase();
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

        return String.format("[%s] %s", id, name);
    }

    /**
     * Checks if the variables in the class are valid. Filters out invalid marks for each Student.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        if (!isValidId(id)) {
            return false;
        }

        assert (assessmentList != null && studentList != null);

        for (Student student : studentList.getStudents()) {
            ArrayList<String> invalidMarks = new ArrayList<>();
            HashMap<String, Double> results = student.getResults();
            for (String assessmentName : results.keySet()) {
                Assessment assessment = assessmentList.getAssessment(assessmentName);
                if (assessment == null) {
                    invalidMarks.add(assessmentName);
                    continue;
                }

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

    /**
     * Checks if the ID is valid. ID is valid if it is not empty and does not contain any spaces.
     *
     * @param id The ID to check.
     * @return true if ID is valid, else false.
     */
    public static boolean isValidId(String id) {
        return !id.isEmpty() && !id.contains(" ");
    }
}
