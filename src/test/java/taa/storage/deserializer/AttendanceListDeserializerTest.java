package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.attendance.AttendanceList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AttendanceListDeserializerTest {
    AttendanceList jsonToAttendanceList(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AttendanceList.class, new AttendanceListDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, AttendanceList.class);
    }

    @Test
    void deserialize_validMembers_expectTwoItems() {
        String input = "{\"attendances\":[{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertEquals(attendanceList.getSize(), 2);
    }

    @Test
    void deserialize_nullAttendancesMember_expectNull() {
        String input = "{\"attendances\":null}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertNull(attendanceList);
    }

    @Test
    void deserialize_emptyAttendancesMember_expectZeroItem() {
        String input = "{\"attendances\":[]}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertEquals(attendanceList.getSize(), 0);
    }

    @Test
    void deserialize_emptyStringAttendancesMember_expectNull() {
        String input = "{\"attendances\":\"\"}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertNull(attendanceList);
    }

    @Test
    void deserialize_missingAttendancesMember_expectNull() {
        String input = "{}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertNull(attendanceList);
    }

    @Test
    void deserialize_duplicatedAttendancesMember_expectOneItem() {
        String input = "{\"attendances\":[{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":1,\"isPresent\":false}]}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertEquals(attendanceList.getSize(), 1);
    }

    @Test
    void deserialize_oneNullLessonNumber_expectOneItem() {
        String input = "{\"attendances\":[{\"lessonNumber\":null,\"isPresent\":true},"
                + "{\"lessonNumber\":1,\"isPresent\":false}]}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertEquals(attendanceList.getSize(), 1);
    }

    @Test
    void deserialize_oneNullAttendance_expectOneItem() {
        String input = "{\"attendances\":[null,"
                + "{\"lessonNumber\":1,\"isPresent\":false}]}";

        AttendanceList attendanceList = jsonToAttendanceList(input);
        assertEquals(attendanceList.getSize(), 1);
    }
}