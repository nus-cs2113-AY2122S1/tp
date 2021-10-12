package taa.storage.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import taa.student.Attendance;
import taa.student.Student;

import java.lang.reflect.Type;
import java.util.HashMap;

public class StudentDeserializer extends StorageDeserializer implements JsonDeserializer<Student> {
    private static final String MEMBER_ID = "id";
    private static final String MEMBER_NAME = "name";
    private static final String MEMBER_ATTENDANCES = "attendances";
    private static final String MEMBER_RESULTS = "results";
    private static final String[] MEMBERS = {
        MEMBER_ID,
        MEMBER_NAME,
        MEMBER_ATTENDANCES,
        MEMBER_RESULTS
    };

    @Override
    public Student deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, MEMBERS)) {
            return null;
        }

        JsonElement idJson = jsonObject.get(MEMBER_ID);
        String id = idJson.getAsString();

        JsonElement nameJson = jsonObject.get(MEMBER_NAME);
        String name = nameJson.getAsString();

        Student student = new Student(id, name);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        JsonElement attendancesJson = jsonObject.get(MEMBER_ATTENDANCES);
        JsonArray attendancesJsonArray = attendancesJson.getAsJsonArray();
        for (JsonElement attendanceJson : attendancesJsonArray) {
            Attendance attendance = gson.fromJson(attendanceJson, Attendance.class);
            // TODO Add attendance to student after parsing
        }

        JsonElement resultsJson = jsonObject.get(MEMBER_RESULTS);
        Type resultsType = new TypeToken<HashMap<String, Double>>(){}.getType();
        HashMap<String, Double> results = gson.fromJson(resultsJson, resultsType);
        for (String key : results.keySet()) {
            double value = results.get(key);
            student.setMarks(key, value);
        }

        if (!student.verify()) {
            return null;
        }

        return student;
    }
}
