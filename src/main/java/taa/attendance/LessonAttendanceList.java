package taa.attendance;

import taa.student.Student;
import taa.student.StudentList;

import java.util.ArrayList;

public class LessonAttendanceList {
    private final ArrayList<String> lessonAttendances;

    private static final String MESSAGE_FORMAT_LESSON_ATTENDANCE = "%s [%s]";
    private static final String MESSAGE_HEAD_COUNT = "Total Attendance for this lesson: %d/%d";
    private int presentCount = 0;

    public LessonAttendanceList() {
        this.lessonAttendances = new ArrayList<>();
    }

    public int getSize() {
        return lessonAttendances.size();
    }

    /**
     * Returns a String containing student name and student attendance for a lesson.
     *
     * @param student    The student in the LessonAttendanceList.
     * @param attendance The attendance object.
     * @return student name and student attendance.
     */
    public String getLessonAttendance(Student student, Attendance attendance) {
        String presentString = (attendance.getIsPresent()) ? "Present" : "Absent";
        if (attendance.getIsPresent()) {
            presentCount++;
        }
        return String.format(MESSAGE_FORMAT_LESSON_ATTENDANCE, student, presentString);
    }

    /**
     * Adds the LessonAttendance into the LessonAttendanceList.
     *
     * @param studentList  The list of students in the module.
     * @param lessonNumber The lesson number.
     */
    public void setLessonAttendances(StudentList studentList, int lessonNumber) {
        for (int i = 0; i < studentList.getSize(); i++) {
            Student student = studentList.getStudentAt(i);
            Attendance attendance = student.getAttendanceList().getAttendance(lessonNumber);
            if (attendance != null) {
                lessonAttendances.add(getLessonAttendance(student, attendance));
            }
        }
    }

    /**
     * Gets the fraction of students present for the lesson.
     *
     * @return Fraction of students present for the lesson.
     */
    private String getHeadCount() {
        return String.format(MESSAGE_HEAD_COUNT, presentCount, getSize());
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getSize(); i += 1) {
            if (i > 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(lessonAttendances.get(i));
        }
        stringBuilder.append("\n");
        stringBuilder.append(getHeadCount());

        return stringBuilder.toString();
    }
}
