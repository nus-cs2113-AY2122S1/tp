package seedu.command;

import com.google.gson.Gson;
import seedu.module.Module;
import seedu.timetable.Timetable;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddCommand extends Command{
    private final String ModuleCode;
    public Timetable timetable = new Timetable();

    public AddCommand(String ModuleCode) {
        this.ModuleCode = ModuleCode;
    }

    public void execute() {
        String path = "data/Modules/" + ModuleCode + ".json";
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(path));
            Module module = gson.fromJson(reader, Module.class);
            reader.close();
            timetable.add(module);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        timetable.print();
    }
}
