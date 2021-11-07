package taa.storage.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.attendance.Attendance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AttendanceDeserializerTest {
    Attendance jsonToAttendance(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Attendance.class, new AttendanceDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, Attendance.class);
    }

    @Test
    void deserialize_validMembers_expectNotNull() {
        String input = "{\"lessonNumber\":1,\"isPresent\":true}";

        Attendance attendance = jsonToAttendance(input);
        assertNotNull(attendance);
    }

    @Test
    void deserialize_missingLessonNumberMember_expectNull() {
        String input = "{\"isPresent\":true}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }

    @Test
    void deserialize_nullLessonNumberMember_expectNull() {
        String input = "{\"lessonNumber\":null,\"isPresent\":true}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }

    @Test
    void deserialize_emptyStringLessonNumberMember_expectNull() {
        String input = "{\"lessonNumber\":\"\",\"isPresent\":true}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }

    @Test
    void deserialize_underMinLessonNumberMember_expectNull() {
        String input = "{\"lessonNumber\":0,\"isPresent\":true}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }

    @Test
    void deserialize_missingIsPresentMember_expectNull() {
        String input = "{\"lessonNumber\":1}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }

    @Test
    void deserialize_nullIsPresentMember_expectNull() {
        String input = "{\"lessonNumber\":1,\"isPresent\":null}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }

    @Test
    void deserialize_emptyStringIsPresentMember_expectNull() {
        String input = "{\"lessonNumber\":1,\"isPresent\":\"\"}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }

    @Test
    void deserialize_nonBooleanIsPresentMember_expectNull() {
        String input = "{\"lessonNumber\":1,\"isPresent\":\"hello\"}";

        Attendance attendance = jsonToAttendance(input);
        assertNull(attendance);
    }
}