package seedu.duke;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;

public class FileStorage {

    private static final String FILE_PATH = "trips.json";
    private static Gson gson;

    public static void writeToFile(String jsonString) throws IOException {
        Storage.getLogger().log(Level.INFO, "starting write to save file");
        FileWriter fileWriter = initializeFileWriter();
        fileWriter.write(jsonString);
        fileWriter.close();
    }

    public static String readFromFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        return scanner.nextLine();
    }

    protected static void newBlankFile() throws IOException {
        FileWriter fileWriter = initializeFileWriter();
        fileWriter.close();
    }

    public static FileWriter initializeFileWriter() throws IOException {
        return new FileWriter(FILE_PATH);
    }

    public static void initializeGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        FileStorage.gson = gsonBuilder.create();
    }

    public static Gson getGson() {
        return gson;
    }

    private static class LocalDateSerializer implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    private static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            String dateInString = json.getAsJsonPrimitive().getAsString();
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateInString, pattern);
        }
    }

}
