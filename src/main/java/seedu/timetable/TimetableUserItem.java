package seedu.timetable;

import seedu.ui.TimetableUI.LineType;

public class TimetableUserItem extends TimetableItem {

    private String description;

    public TimetableUserItem(String title, String day, String startTime, String endTime, String description) {
        super(title, day, startTime, endTime);
        this.description = description;
    }

    public String printTypeInfo(LineType type) {
        String str = "|   ";
        switch (type) {
        default:
            str += "";
        }
        return str;
    }
}
