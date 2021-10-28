package taa.storage.deserializer;

//@@author leyondlee
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.attendance.Attendance;
import taa.attendance.AttendanceList;

import java.lang.reflect.Type;

public class AttendanceListDeserializer extends StorageDeserializer implements JsonDeserializer<AttendanceList> {
    private static final String MEMBER_ATTENDANCES = "attendances";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_ATTENDANCES};

    @Override
    public AttendanceList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement attendancesJson = jsonObject.get(MEMBER_ATTENDANCES);
        if (!attendancesJson.isJsonArray()) {
            return null;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Attendance.class, new AttendanceDeserializer());
        Gson gson = gsonBuilder.create();

        AttendanceList attendanceList = new AttendanceList();
        JsonArray attendancesJsonArray = attendancesJson.getAsJsonArray();
        for (JsonElement attendanceJson : attendancesJsonArray) {
            Attendance attendance = gson.fromJson(attendanceJson, Attendance.class);
            if (attendance != null) {
                attendanceList.addAttendance(attendance);
            }
        }

        if (!attendanceList.verify()) {
            return null;
        }

        return attendanceList;
    }
}
