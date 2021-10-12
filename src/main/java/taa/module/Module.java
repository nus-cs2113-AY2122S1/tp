package taa.module;

import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.attendance.Attendance;
import taa.attendance.AttendanceList;
import taa.student.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class Module {
    private static final double MAX_ASSESSMENT_WEIGHTAGE = 1;
    private String code;
    private String name;
    private int lessonCount;
    private final ArrayList<Student> students;
    private final AssessmentList assessmentList;
    private final AttendanceList attendanceList;
    private static final int NUM_LESSONS = 13;
    private final int[] lessonArray = new int[NUM_LESSONS];

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.students = new ArrayList<>();
        this.assessmentList = new AssessmentList();
        this.attendanceList = new AttendanceList();
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
            Student student = students.get(i);
            String id = student.getId();
            String name = student.getName();

            if (id.toLowerCase().contains(keywordLower) || name.toLowerCase().contains(keywordLower)) {
                studentsFound.add(student);
            }
        }

        return studentsFound;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessmentList.getAssessments();
    }

    public boolean addAssessment(Assessment assessment) {
        return assessmentList.addAssessment(assessment);
    }

    public int getAssessmentsCount() {
        return assessmentList.getSize();
    }

    public int getAttendanceCount() {
        return attendanceList.getSize();
    }


    public ArrayList<String> getAttendances() {
        return attendanceList.getAttendances();
    }

    public AttendanceList getAttendanceList() {
        return attendanceList;
    }

    public void addAttendances(AttendanceList attendances, Student student){
        attendances.addIndividualAttendances(student.getIndividualAttendances());
    }

    private String getLessonArrayAsString(){
        buildLessonArray();
        return Arrays.toString(lessonArray);
    }

    private int[] buildLessonArray(){
        for (int i = 0; i < NUM_LESSONS; i++){
            lessonArray[i] = i + 1;
        }
        return lessonArray;
    }

    public String getFormattedLessons(){
        String lessons = getLessonArrayAsString();
        String singleDigitLessons = lessons.substring(0, 27);
        String doubleDigitLessons = lessons.substring(28);
        singleDigitLessons = singleDigitLessons.replace(",", " |");
        doubleDigitLessons = doubleDigitLessons.replace(", ", " |");
        lessons = singleDigitLessons + doubleDigitLessons;
        lessons = lessons.replace("[", " | ");
        lessons = lessons.replace("]", " |");
        return lessons;
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
