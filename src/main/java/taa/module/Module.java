package taa.module;

import taa.student.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class Module {
    private static final double MAX_ASSESSMENT_WEIGHTAGE = 100;

    private final String code;
    private final String name;
    private final ArrayList<Student> students;
    private final HashMap<String, Double> assessments;
    private int lessonCount;

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
        return students;
    }

    public HashMap<String, Double> getAssessments() {
        return new HashMap<>(assessments);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Add an assessment to the module. Does not add if assessment name already exists
     * or total weightage exceed MAX_ASSESSMENT_WEIGHTAGE after adding.
     *
     * @param name Name of assessment.
     * @param weightage Assessment weightage.
     * @return true if success, else false.
     */
    public boolean addAssessment(String name, double weightage) {
        if (assessments.containsKey(name)) {
            return false;
        }

        double totalWeightage = 0;
        for (String key : assessments.keySet()) {
            totalWeightage += assessments.get(key);
        }
        if ((totalWeightage + weightage) > MAX_ASSESSMENT_WEIGHTAGE) {
            return false;
        }

        assessments.put(name, weightage);
        return true;
    }

    @Override
    public String toString() {
        if (name.isEmpty()) {
            return code;
        }

        return String.format("%s - %s", code, name);
    }
}
