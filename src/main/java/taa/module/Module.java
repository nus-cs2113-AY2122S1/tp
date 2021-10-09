package taa.module;

import taa.exception.TaaException;
import taa.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Module {
    private static final double MAX_ASSESSMENT_WEIGHTAGE = 1;

    private String code;
    private String name;
    private int lessonCount;
    private final ArrayList<Student> students;
    private final HashMap<String, Double> assessments;

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
        this.assessments = new HashMap<>();
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

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
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
            Student student = getStudentAt(i);
            String id = student.getId();
            String name = student.getName();

            if (id.toLowerCase().contains(keywordLower) || name.toLowerCase().contains(keywordLower)) {
                studentsFound.add(student);
            }
        }

        return studentsFound;
    }

    public HashMap<String, Double> getAssessments() {
        return new HashMap<>(assessments);
    }

    /**
     * Add an assessment to the module. Does not add if assessment name already exists
     * or total weightage exceed MAX_ASSESSMENT_WEIGHTAGE after adding.
     *
     * @param name The name of the assessment.
     * @param weightage The assessment weightage.
     * @throws TaaException if weightage is invalid or total weightage exceeds max.
     */
    public void addAssessment(String name, double weightage) throws TaaException {
        if (assessments.containsKey(name)) {
            // TODO Assessment name already exist message
            throw new TaaException("");
        }

        if (weightage < 0 || weightage > MAX_ASSESSMENT_WEIGHTAGE) {
            // TODO Invalid weightage message
            throw new TaaException("");
        }

        double totalWeightage = 0;
        for (String key : assessments.keySet()) {
            totalWeightage += assessments.get(key);
        }
        if ((totalWeightage + weightage) > MAX_ASSESSMENT_WEIGHTAGE) {
            // TODO Invalid weightage message
            throw new TaaException("");
        }

        assessments.put(name, weightage);
    }

    /**
     * Deletes an assessment from the module.
     *
     * @param name The name of the assessment.
     * @throws TaaException if assessment does not exist.
     */
    public void deleteAssessment(String name) throws TaaException {
        if (!assessments.containsKey(name)) {
            // TODO Invalid assessment name message
            throw new TaaException("");
        }

        for (int i = 0; i < getStudentCount(); i += 1) {
            // TODO Delete Assessment in Student.
        }

        assessments.remove(name);
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
