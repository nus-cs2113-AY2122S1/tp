package taa.attendance;


import java.util.ArrayList;
import java.util.Comparator;

public class AttendanceList {
    private final ArrayList<Attendance> attendances;

    public AttendanceList() {
        this.attendances = new ArrayList<>();
    }

    public int getSize() {
        return attendances.size();
    }

    public ArrayList<Attendance> getAttendances() {
        return new ArrayList<>(attendances);
    }

    public Attendance getAttendance(int lessonNumber) {
        for (Attendance attendance : attendances) {
            if (attendance.getLessonNumber() == lessonNumber) {
                return attendance;
            }
        }

        return null;
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
        sortAttendances();
    }

    private void sortAttendances() {
        attendances.sort(new Comparator<Attendance>() {
            @Override
            public int compare(Attendance attendance1, Attendance attendance2) {
                return attendance1.getLessonNumber() - attendance2.getLessonNumber();
            }
        });
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
            stringBuilder.append(attendances.get(i));
        }

        return stringBuilder.toString();
    }

}
