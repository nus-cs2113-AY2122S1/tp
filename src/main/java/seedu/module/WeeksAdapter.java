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
            jsonWriter.beginArray();
            for (Integer week : weeks.getWeeks()) {
                jsonWriter.value(week);
            }
            jsonWriter.endArray();
        } else {
            jsonWriter.beginObject();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            jsonWriter.name("start").value(dateFormat.format(weeks.getStart()));
            jsonWriter.name("end").value(dateFormat.format(weeks.getEnd()));
            jsonWriter.endObject();
        }
    }

    @Override
    public Weeks read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
        case BEGIN_ARRAY:
            ArrayList<Integer> array = new ArrayList<>();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                array.add(gson.fromJson(jsonReader, Integer.class));
            }
            jsonReader.endArray();
            return new Weeks(array);
        case BEGIN_OBJECT:
            return gson.fromJson(jsonReader, Weeks.class);
        default:
            //throw new UniModsException("Type was not array or object, but " + jsonReader.peek());
            throw new IOException();
        }
    }
}
