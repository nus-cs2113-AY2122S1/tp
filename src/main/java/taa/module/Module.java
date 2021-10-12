package taa.module;

import taa.assessment.AssessmentList;
import taa.attendance.Attendance;
import taa.attendance.AttendanceList;
import taa.student.Student;

import java.util.ArrayList;
import java.util.Arrays;

import taa.student.StudentList;

public class Module {
    private String code;
    private String name;
    private int lessonCount;
    private final StudentList studentList;
    private final AssessmentList assessmentList;
    private final AttendanceList attendanceList;
    private static final int NUM_LESSONS = 13;
    private final int[] lessonArray = new int[NUM_LESSONS];

    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.studentList = new StudentList();
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

    public StudentList getStudentList() {
        return studentList;
    }

    public AssessmentList getAssessmentList() {
        return assessmentList;
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
}
