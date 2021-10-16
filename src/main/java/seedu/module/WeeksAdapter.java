package seedu.module;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import seedu.exceptions.UniModsException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WeeksAdapter extends TypeAdapter<Weeks> {

    private final Gson gson;

    public WeeksAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void write(JsonWriter jsonWriter, Weeks weeks) throws IOException {
        if (weeks.getWeeks() != null) {
            writeAsIntArray(jsonWriter, weeks);
        } else {
            writeAsObject(jsonWriter, weeks);
        }
    }

    private void writeAsObject(JsonWriter jsonWriter, Weeks weeks) throws IOException {
        jsonWriter.beginObject();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        jsonWriter.name("start").value(dateFormat.format(weeks.getStart()));
        jsonWriter.name("end").value(dateFormat.format(weeks.getEnd()));
        jsonWriter.endObject();
    }

    private void writeAsIntArray(JsonWriter jsonWriter, Weeks weeks) throws IOException {
        jsonWriter.beginArray();
        for (Integer week : weeks.getWeeks()) {
            jsonWriter.value(week);
        }
        jsonWriter.endArray();
    }

    @Override
    public Weeks read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
        case BEGIN_ARRAY:
            return getWeeksIntArray(jsonReader);
        case BEGIN_OBJECT:
            return getWeeksObject(jsonReader);
        default:
            //throw new UniModsException("Type was not array or object, but " + jsonReader.peek());
            throw new IOException();
        }
    }

    private Weeks getWeeksObject(JsonReader jsonReader) {
        return gson.fromJson(jsonReader, Weeks.class);
    }

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
