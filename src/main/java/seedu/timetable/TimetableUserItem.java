package seedu.timetable;

import seedu.ui.TimetableUI.LineType;

public class TimetableUserItem extends TimetableItem {

    private String description;

    public TimetableUserItem(String title, String day, String startTime, String endTime,
                             String description, TaskType type) {
        super(title, day, startTime, endTime);
        this.description = description;
        this.type = type.name();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimetableUserItem) {
            TimetableUserItem userItem = (TimetableUserItem) obj;
            return this.getTitle().equals(userItem.getTitle()) && this.getType().equals(userItem.getType());
        }
        return false;
    }


}
