package seedu.module;

import java.util.Date;

public class Mod {

    private String moduleCode;
    private String title;
    private String description;
    private double moduleCredit;
    private String department;
    private String faculty;
    private String prerequisite;
    private String preclusion;
    private String corequisite;
    public class semesterData {
        private int semester;
        private Date examDate;
        private int examDuration;
        public class timetable {
            private String classNo;
            private int startTime;
            private int endTime;
            private String venue;
            private String day;
            private String lessonType; //placeholder
            private int size;
        }
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return moduleCode + " " + title + " " + (int)moduleCredit + "MC";
    }

}
