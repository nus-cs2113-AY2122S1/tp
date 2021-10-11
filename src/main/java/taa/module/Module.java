package taa.module;

import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.student.Student;

import java.util.ArrayList;

public class Module {
    private static final double MAX_ASSESSMENT_WEIGHTAGE = 1;
    private String code;
    private String name;
    private int lessonCount;
    private final ArrayList<Student> students;
    private final AssessmentList assessmentList;

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
        this.assessmentList = new AssessmentList();
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
        return new ArrayList<>(students);
    }

    public Student getStudent(int studentIndex) {
        return students.get(studentIndex);
    }

    public AssessmentList getAssessments() {
        return assessmentList;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public int getStudentCount() {
        return students.size();
    }

    public Student getStudentAt(int index) {
        if (isValidStudentIndex(index)) {
            return students.get(index);
        }

        return null;
    }

    /**
     * Deletes a student from the student list.
     *
     * @param index The index of the student.
     * @return A Student object if successfully deleted, else null.
     */
    public Student deleteStudent(int index) {
        if (isValidStudentIndex(index)) {
            return students.remove(index);
        }

        return null;
    }

    public ArrayList<Student> findStudents(String keyword) {
        String keywordLower = keyword.toLowerCase();

        ArrayList<Student> studentsFound = new ArrayList<>();
        for (int i = 0; i < getStudentCount(); i += 1) {
            Student student = students.get(i);
            String id = student.getId();
            String name = student.getName();

            if (id.toLowerCase().contains(keywordLower) || name.toLowerCase().contains(keywordLower)) {
                studentsFound.add(student);
            }
        }

        return studentsFound;
    }

    public boolean addAssessment(Assessment assessment) {
        return assessmentList.addAssessment(assessment);
    }

    public int getAssessmentsCount() {
        return assessmentList.getSize();
    }

    @Override
    public String toString() {
        if (name.isEmpty()) {
            return code;
        }

        return String.format("%s - %s", code, name);
    }


    private boolean isValidStudentIndex(int index) {
        return (index >= 0 && index < getStudentCount());
    }
}
