package seedu.storage;

import seedu.timetable.Timetable;

import com.google.gson.Gson;

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
    public Timetable loadSchedule() throws FileNotFoundException {
        FileReader timetableSaveReader = new FileReader(file);
        Timetable timetable = new Gson().fromJson(timetableSaveReader, Timetable.class);
        return timetable;
    }

    /**
     * Save timetable.
     * 
     * @param timetable timetable
     */
    public void save(Timetable timetable) throws IOException {
        Gson gson = new Gson();
        gson.toJson(timetable, new FileWriter(file));
    }
}
