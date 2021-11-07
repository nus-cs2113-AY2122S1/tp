package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.student.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentDeserializerTest {
    Student jsonToStudent(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Student.class, new StudentDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, Student.class);
    }

    @Test
    void deserialize_validMembers_expectCorrectStudent() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}"
                + "}";

        Student student = jsonToStudent(input);

        boolean hasId = student.getId().equals("A1234567B");
        boolean hasName = student.getName().equals("Jon Lim");
        boolean hasComment = student.getComment().equals("Hello-World");
        boolean hasAttendances = student.getAttendanceList().getSize() == 2;
        boolean hasResults = student.getResults().size() == 2;
        assertTrue(hasId && hasName && hasComment && hasAttendances && hasResults);
    }

    @Test
    void deserialize_missingIdMember_expectNull() {
        String input = "{"
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_nullIdMember_expectNull() {
        String input = "{"
                + "\"id\":null,"
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_emptyIdMember_expectNull() {
        String input = "{"
                + "\"id\":\"\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_invalidIdMember_expectNull() {
        String input = "{"
                + "\"id\":\"#\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_missingNameMember_expectNull() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_nullNameMember_expectNull() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":null,"
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_emptyNameMember_expectNull() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"\","
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_invalidNameMember_expectNull() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"$\","
                + "\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNull(student);
    }

    @Test
    void deserialize_missingCommentMember_expectNotNull() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNotNull(student);
    }

    @Test
    void deserialize_nullCommentMember_expectNotNull() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":null,"
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertNotNull(student);
    }

    @Test
    void deserialize_invalidCommentMember_expectEmptyComment() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"hello%world\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertTrue(student.getComment().isEmpty());
    }

    @Test
    void deserialize_missingAttendanceListMember_emptyAttendanceList() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\"}";

        Student student = jsonToStudent(input);
        assertEquals(student.getAttendanceList().getSize(), 0);
    }

    @Test
    void deserialize_nullAttendanceListMember_emptyAttendanceList() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":null}";

        Student student = jsonToStudent(input);
        assertEquals(student.getAttendanceList().getSize(), 0);
    }

    @Test
    void deserialize_emptyAttendanceListMember_emptyAttendanceList() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{}}";

        Student student = jsonToStudent(input);
        assertEquals(student.getAttendanceList().getSize(), 0);
    }

    @Test
    void deserialize_invalidAttendanceListMember_emptyAttendanceList() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":[]}";

        Student student = jsonToStudent(input);
        assertEquals(student.getAttendanceList().getSize(), 0);
    }

    @Test
    void deserialize_emptyStringAttendanceListMember_emptyAttendanceList() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":\"\"}";

        Student student = jsonToStudent(input);
        assertEquals(student.getAttendanceList().getSize(), 0);
    }

    @Test
    void deserialize_duplicatedAttendances_expectOneAttendance() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"hello%world\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":1,\"isPresent\":false}]},\"results\":{}}";

        Student student = jsonToStudent(input);
        assertEquals(student.getAttendanceList().getSize(), 1);
    }

    @Test
    void deserialize_missingResultsMember_exceptEmptyResults() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]}"
                + "}";

        Student student = jsonToStudent(input);
        assertTrue(student.getResults().isEmpty());
    }

    @Test
    void deserialize_nullResultsMember_exceptEmptyResults() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":null"
                + "}";

        Student student = jsonToStudent(input);
        assertTrue(student.getResults().isEmpty());
    }

    @Test
    void deserialize_emptyResultsMember_exceptEmptyResults() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{}"
                + "}";

        Student student = jsonToStudent(input);
        assertTrue(student.getResults().isEmpty());
    }

    @Test
    void deserialize_emptyStringResultsMember_exceptEmptyResults() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":\"\""
                + "}";

        Student student = jsonToStudent(input);
        assertTrue(student.getResults().isEmpty());
    }

    @Test
    void deserialize_invalidResultsMember_exceptEmptyResults() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"hello\":\"world\"}"
                + "}";

        Student student = jsonToStudent(input);
        assertTrue(student.getResults().isEmpty());
    }

    @Test
    void deserialize_duplicatedResults_exceptEmptyResults() {
        String input = "{"
                + "\"id\":\"A1234567B\","
                + "\"name\":\"Jon Lim\","
                + "\"comment\":\"Hello-World\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Midterm\":5.56}"
                + "}";

        Student student = jsonToStudent(input);
        assertEquals(student.getResults().size(), 1);
    }
}