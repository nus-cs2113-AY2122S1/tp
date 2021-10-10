package seedu.duke.nusmods;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import seedu.duke.task.type.Event;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Set;
import java.util.stream.StreamSupport;

import static org.apache.commons.io.FileUtils.copyURLToFile;

public class NusModsParser {
    public static final String CACHEDIR = "cache/";
    private enum Semester {
        S1, S2, ST1, ST2;

        public static Semester fromInt(int n) {
            // n is 1-based, as in NUSMods data
            return Semester.values()[n - 1];
        }
    }

    private static Semester getSemester() {
        // FIXME
        return Semester.S1;
    }

    /**
     * Get calendar events of the given classes in the current semester
     * @param moduleCode The module code of the module in question, for example CS2113T
     * @param classNos A set of class numbers to be queried, for example T01
     * @return An array of Events denoting all class occurrences
     * @throws IOException If there is neither network connection nor local cache
     */
    public Event[] getModuleEvents(String moduleCode, Set<String> classNos)
            throws IOException {
        JsonObject obj = JsonParser.parseReader(getModuleReader(moduleCode)).getAsJsonObject();
        JsonArray semesterData = obj.getAsJsonArray("semesterData");
        Event[] retval = new Event[0];
        for (JsonElement e : semesterData) {
            JsonObject o = e.getAsJsonObject();
            if (Semester.fromInt(o.get("semester").getAsInt()) == getSemester()) {
                JsonArray timetable = o.get("timetable").getAsJsonArray();
                retval = StreamSupport.stream(timetable.spliterator(), true)
                        .map(JsonElement::getAsJsonObject)
                        .filter(lesson -> classNos.contains(lesson.get("classNo").getAsString()))
                        .map(lesson -> new Event(moduleCode + lesson.get("lessonType")))
                        .toArray(Event[]::new);
            }
        }
        return retval;
    }

    private Reader getModuleReader(String moduleCode) throws IOException {
        if (!(new File(getModuleFilename(moduleCode)).isFile())) {
            // file not existing
            getModuleJson(moduleCode);
        }
        return new FileReader(getModuleFilename(moduleCode));
    }

    private String getModuleFilename(String moduleCode) {
        return CACHEDIR + moduleCode + ".json";
    }

    private static String getModuleUrl(String moduleCode) {
        final String ENDPOINT = "https://api.nusmods.com/v2/";
        return ENDPOINT + formatAcadYear(getAcadYear()) + "/modules/" + moduleCode + ".json";
    }

    private void getModuleJson(String moduleCode) throws IOException {
        final int TIMEOUT = 5000;
        copyURLToFile(new URL(getModuleUrl(moduleCode)), new File(getModuleFilename(moduleCode)), TIMEOUT, TIMEOUT);
    }

    private static Year getAcadYear() {
        YearMonth current = YearMonth.now();
        Year year = Year.of(current.getYear());
        if (current.getMonth().compareTo(Month.AUGUST) < 0) {
            year = year.minusYears(1);
        }
        return year;
    }

    private static String formatAcadYear(Year year) {
        return String.format("%tY-%tY", year, year.plusYears(1));
    }
}
