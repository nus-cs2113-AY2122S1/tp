package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import taa.Parser;
import taa.attendance.Attendance;
import taa.attendance.AttendanceList;
import taa.student.Student;

import java.lang.reflect.Type;
import java.util.HashMap;

public class StudentDeserializer extends StorageDeserializer implements JsonDeserializer<Student> {
    private static final String MEMBER_ID = "id";
    private static final String MEMBER_NAME = "name";
    private static final String MEMBER_COMMENT = "comment";
    private static final String MEMBER_ATTENDANCELIST = "attendanceList";
    private static final String MEMBER_RESULTS = "results";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_ID, MEMBER_NAME};

    @Override
    public Student deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement idJson = jsonObject.get(MEMBER_ID);
        String id = getJsonElementAsString(idJson);

        JsonElement nameJson = jsonObject.get(MEMBER_NAME);
        String name = getJsonElementAsString(nameJson);

        if (!Parser.isValueValid(id) || !Parser.isValueValid(name)) {
            return null;
        }

        Student student = new Student(id, name);

        JsonElement commentJson = jsonObject.get(MEMBER_COMMENT);
        if (commentJson != null) {
            String comment = getJsonElementAsString(commentJson);
            if (Parser.isValueValid(comment)) {
                student.setComment(comment);
            }
        }

        JsonElement attendanceListJson = jsonObject.get(MEMBER_ATTENDANCELIST);
        deserializeAttendanceList(attendanceListJson, student);

        JsonElement resultsJson = jsonObject.get(MEMBER_RESULTS);
        deserializeResults(resultsJson, student);

        if (!student.verify()) {
            return null;
        }

        return student;
    }

    private void deserializeAttendanceList(JsonElement attendanceListJson, Student student)
            throws JsonParseException {
        if (attendanceListJson == null || !attendanceListJson.isJsonObject()) {
            return;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AttendanceList.class, new AttendanceListDeserializer());
        Gson gson = gsonBuilder.create();

        AttendanceList deserializedAttendanceList = gson.fromJson(attendanceListJson, AttendanceList.class);
        if (deserializedAttendanceList == null) {
            return;
        }

        AttendanceList attendanceList = student.getAttendanceList();
        for (Attendance attendance : deserializedAttendanceList.getAttendances()) {
            attendanceList.addAttendance(attendance);
        }
    }

    private void deserializeResults(JsonElement resultsJson, Student student) {
        if (resultsJson == null || !resultsJson.isJsonObject()) {
            return;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        HashMap<String, Double> results;
        try {
            Type resultsType = new TypeToken<HashMap<String, Double>>() {}.getType();
            results = gson.fromJson(resultsJson, resultsType);
        } catch (JsonParseException | NumberFormatException e) {
            results = null;
        }

        if (results == null) {
            return;
        }

        for (String key : results.keySet()) {
            double value = results.get(key);
            student.setMarks(key, value);
        }
    }
}
