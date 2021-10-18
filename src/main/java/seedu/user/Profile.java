package seedu.user;

import seedu.timetable.Timetable;

public class Profile {

    private final String username;
    private final String major;
    private final String year;
    private final Timetable timetable;
    private final ModuleRecord record = new ModuleRecord();
    private final double cap;


    public Profile(String username, String major, String year) {
        this.username = username;
        this.major = major;
        this.year = year;
        this.timetable = null;
        this.cap = record.getCap();
    }

    public String getYear() {
        return year;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return username;
    }

    public ModuleRecord getRecord() {
        return record;
    }
}
