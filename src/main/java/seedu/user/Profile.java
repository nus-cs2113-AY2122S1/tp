package seedu.user;

import seedu.timetable.Timetable;
import seedu.ui.TranscriptUi;

public class Profile {

    private final String username;
    private final String major;
    private final String year;
    private final Timetable timetable;
    private final ModuleRecord record = new ModuleRecord();
    private double cap;


    public Profile(String username, String major, String year) {
        this.username = username;
        this.major = major;
        this.year = year;
        this.timetable = null;
        this.cap = record.calculateCap();
    }

    public String getYear() {
        return year;
    }

    public double getCap() {
        cap = record.calculateCap();
        return cap;
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

    public void showTranscript() {
        TranscriptUi.printIntroduction();
        TranscriptUi.printHeadings();
        record.printGradedModuleInfo();
        record.printUngradedModuleInfo();
        TranscriptUi.printConclusion();
    }
}
