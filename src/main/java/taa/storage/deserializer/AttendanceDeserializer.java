package taa.storage.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.attendance.Attendance;
import taa.util.Util;

import java.lang.reflect.Type;

public class AttendanceDeserializer extends StorageDeserializer implements JsonDeserializer<Attendance> {
    private static final String MEMBER_LESSONNUMBER = "lessonNumber";
    private static final String MEMBER_ISPRESENT = "isPresent";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_LESSONNUMBER, MEMBER_ISPRESENT};

    @Override
    public Attendance deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement lessonNumberJson = jsonObject.get(MEMBER_LESSONNUMBER);
        if (!Util.isStringInteger(lessonNumberJson.getAsString())) {
            return null;
        }
        int lessonNumber = lessonNumberJson.getAsInt();

        JsonElement isPresentJson = jsonObject.get(MEMBER_ISPRESENT);
        if (!Util.isStringBoolean(isPresentJson.getAsString())) {
            return null;
        }
        boolean isPresent = isPresentJson.getAsBoolean();

        Attendance attendance = new Attendance(lessonNumber, isPresent);
        if (!attendance.verify()) {
            return null;
        }

        return attendance;
    }
}
