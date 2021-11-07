package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.student.StudentList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StudentListDeserializerTest {
    StudentList jsonToStudentList(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StudentList.class, new StudentListDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, StudentList.class);
    }

    @Test
    void deserialize_validMembers_expectTwoItems() {
        String input = "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{"
                + "\"attendances\":[{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{"
                + "\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]}";

        StudentList studentList = jsonToStudentList(input);
        assertEquals(studentList.getSize(), 2);
    }

    @Test
    void deserialize_missingStudentsMember_expectNull() {
        String input = "{}";

        StudentList studentList = jsonToStudentList(input);
        assertNull(studentList);
    }

    @Test
    void deserialize_nullStudentMember_expectNull() {
        String input = "{\"students\":null}";

        StudentList studentList = jsonToStudentList(input);
        assertNull(studentList);
    }

    @Test
    void deserialize_emptyStudentMember_expectZeroItem() {
        String input = "{\"students\":[]}";

        StudentList studentList = jsonToStudentList(input);
        assertEquals(studentList.getSize(), 0);
    }

    @Test
    void deserialize_emptyStringStudentMember_expectNull() {
        String input = "{\"students\":\"\"}";

        StudentList studentList = jsonToStudentList(input);
        assertNull(studentList);
    }

    @Test
    void deserialize_duplicatedStudents_expectOneItem() {
        String input = "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{"
                + "\"attendances\":[{\"lessonNumber\":1,\"isPresent\":true},"
                + "{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{"
                + "\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A1234567B\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]}";

        StudentList studentList = jsonToStudentList(input);
        assertEquals(studentList.getSize(), 1);
    }

    @Test
    void deserialize_oneNullStudent_expectOneItem() {
        String input = "{\"students\":["
                + "null,"
                + "{\"id\":\"A1234567B\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]}";

        StudentList studentList = jsonToStudentList(input);
        assertEquals(studentList.getSize(), 1);
    }
}