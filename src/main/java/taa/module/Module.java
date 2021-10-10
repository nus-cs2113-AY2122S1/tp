package taa.module;

import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.student.Student;

import java.util.ArrayList;

public class Module {

    private final String code;
    private final String name;
    private final ArrayList<Student> students;
    private final AssessmentList assessments;
    private int lessonCount;

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
        this.assessments = new AssessmentList();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getStudent(int studentIndex) {
        return students.get(studentIndex);
    }

    public AssessmentList getAssessments() {
        return assessments;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Add an assessment to the module.
     *
     * @param assessment      Assessment object to be added.
     */
    public void addAssessment(Assessment assessment) {
        assessments.addAssessment(assessment);
    }

    public int getAssessmentsSize() {
        return assessments.getSize();
    }

    @Override
    public String toString() {
        if (name.isEmpty()) {
            return code;
        }

        return String.format("%s - %s", code, name);
    }


}
