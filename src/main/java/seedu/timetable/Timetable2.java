package seedu.timetable;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import seedu.storage.TimetableStorage;

public class Timetable2 {
    // TimetableDto scheduleInfo;
    private TimetableInfo timetableInfo;

    /**
     * Get timetable from save or create new if not exist.
     * 
     * @param path path to save
     */
    public Timetable2(String path) {
        try {
            TimetableDto dto = new TimetableStorage(path).loadSchedule();
            timetableInfo = new TimetableInfo(dto);
        } catch (FileNotFoundException e) {
            // scheduleInfo = new TimetableDto();
            timetableInfo = new TimetableInfo();
        }
    }

    /**
     * Add item to scheule.
     * 
     * @param item timetable item to add
     */
    public void add(TimetableItem item) {
        ArrayList<TimetableItem> daySchedule = timetableInfo.getSchedule().get(item.getDay());
        daySchedule.add(item);
        Collections.sort(daySchedule, (item1, item2) -> item1.compareTo(item2));
    }

    /**
     * TODO remove item from schedule.
     */
    public void remove() {

    }
}
