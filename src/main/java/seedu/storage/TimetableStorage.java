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
    public Timetable loadSchedule() {
        try {
            FileReader timetableSaveReader = new FileReader(file);
            Timetable timetable = new Gson().fromJson(timetableSaveReader, Timetable.class);
            return timetable;
        } catch (FileNotFoundException e) {
            return new Timetable(1);
        }
    }

    /**
     * Save timetable.
     * 
     * @param timetable timetable
     */
    public void save(Timetable timetable) {
        try {
            FileWriter fw = new FileWriter(file);
            Gson gson = new Gson();
            gson.toJson(timetable, fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("failed to save timetable");
        }
    }
}
