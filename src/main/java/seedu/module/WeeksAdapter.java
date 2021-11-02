package seedu.module;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Adapter class for GSON parsing. This adapter allows GSON to parse the weeks field from jsons retrieved from the
 * NUSMods API, which can be either an int array or an object.
 */
public class WeeksAdapter extends TypeAdapter<Weeks> {

    private final Gson gson;

    public WeeksAdapter(Gson gson) {
        this.gson = gson;
    }

    /**
     * Writes a week as either an int array or an object, depending on its attributes.
     *
     * @param jsonWriter JsonWriter.
     * @param weeks Weeks object to convert into a json data type.
     * @throws IOException when Gson is unable to convert and save the Weeks object.
     */
    @Override
    public void write(JsonWriter jsonWriter, Weeks weeks) throws IOException {
        if (weeks.getWeeks() != null) {
            writeAsIntArray(jsonWriter, weeks);
        } else {
            writeAsObject(jsonWriter, weeks);
        }
    }

    /**
     * Writes a week as an object.
     *
     * @param jsonWriter JsonWriter.
     * @param weeks Weeks object to convert into json object.
     * @throws IOException when Gson is unable to convert and save the Weeks object.
     */
    private void writeAsObject(JsonWriter jsonWriter, Weeks weeks) throws IOException {
        jsonWriter.beginObject();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        jsonWriter.name("start").value(dateFormat.format(weeks.getStart()));
        jsonWriter.name("end").value(dateFormat.format(weeks.getEnd()));
        jsonWriter.endObject();
    }

    /**
     * Writes a week as an int array.
     *
     * @param jsonWriter JsonWriter.
     * @param weeks Weeks object to convert into int array.
     * @throws IOException when Gson is unable to convert and save the Weeks object.
     */
    private void writeAsIntArray(JsonWriter jsonWriter, Weeks weeks) throws IOException {
        jsonWriter.beginArray();
        for (Integer week : weeks.getWeeks()) {
            jsonWriter.value(week);
        }
        jsonWriter.endArray();
    }

    /**
     * Parses a weeks key in a json that can either be an int array or an object with start and end dates.
     *
     * @param jsonReader JsonReader.
     * @return weeks as an object with relevant attributes.
     * @throws IOException when weeks key is neither an int array nor an object.
     */
    @Override
    public Weeks read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
        case BEGIN_ARRAY:
            return getWeeksIntArray(jsonReader);
        case BEGIN_OBJECT:
            return getWeeksObject(jsonReader);
        default:
            throw new IOException();
        }
    }

    /**
     * Returns weeks as an object with start and end dates. weeks attribute in the weeks object will be null.
     *
     * @param jsonReader JsonReader.
     * @return weeks with start and end dates.
     */
    private Weeks getWeeksObject(JsonReader jsonReader) {
        return gson.fromJson(jsonReader, Weeks.class);
    }

    /**
     * Returns weeks as an object with weeks attribute int array. start and end dates will be null.
     *
     * @param jsonReader JsonReader.
     * @return weeks with an int array attribute named weeks.
     */
    private Weeks getWeeksIntArray(JsonReader jsonReader) throws IOException {
        ArrayList<Integer> array = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            array.add(gson.fromJson(jsonReader, Integer.class));
        }
        jsonReader.endArray();
        return new Weeks(array);
    }
}
