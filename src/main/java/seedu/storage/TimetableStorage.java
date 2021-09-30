package seedu.storage;

import seedu.timetable.TimetableDto;
import seedu.timetable.TimetableInfo;
import seedu.timetable.TimetableItem;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TimetableStorage {
    private final File file;

    /**
     * Initialize TimetableStorage.
     */
    public TimetableStorage(String path) {
        this.file = new File(path);
    }

    /**
     * Gets timetable schedule from storage.
     * 
     * @return
     */
    public TimetableDto loadSchedule() throws FileNotFoundException {
        FileReader timetableSaveReader = new FileReader(file);
        TimetableDto timetable = new Gson().fromJson(timetableSaveReader, TimetableDto.class);
        return timetable;
    }

    /**
     * Save timetable.
     * 
     * @param timetable timetable
     */
    public void save(TimetableInfo timetable) throws IOException {
        Gson gson = new Gson();
        gson.toJson(timetable.toDto(), new FileWriter(file));
    }
}
