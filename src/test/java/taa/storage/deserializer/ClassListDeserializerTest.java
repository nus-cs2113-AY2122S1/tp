package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.teachingclass.ClassList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClassListDeserializerTest {
    ClassList jsonToClassList(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ClassList.class, new ClassListDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, ClassList.class);
    }

    @Test
    void deserialize_validMembers_expectTwoTeachingClasses() {
        String input = "{\"classes\":["
                + "{\"id\":\"CS2113T-F12\",\"name\":\"\",\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}},"
                + "{\"id\":\"CS2101-C03\",\"name\":\"Sectional Group C03\","
                + "\"studentList\":{\"students\":[]},\"assessmentList\":{\"assessments\":[]}}]}";

        ClassList classList = jsonToClassList(input);
        assertEquals(classList.getSize(), 2);
    }

    @Test
    void deserialize_missingClassesMember_expectNull() {
        String input = "{}";

        ClassList classList = jsonToClassList(input);
        assertNull(classList);
    }

    @Test
    void deserialize_nullClassesMember_expectNull() {
        String input = "{\"classes\":null}";

        ClassList classList = jsonToClassList(input);
        assertNull(classList);
    }

    @Test
    void deserialize_emptyClassesMember_expectEmpty() {
        String input = "{\"classes\":[]}";

        ClassList classList = jsonToClassList(input);
        assertEquals(classList.getSize(), 0);
    }

    @Test
    void deserialize_emptyStringClassesMember_expectNull() {
        String input = "{\"classes\":\"\"}";

        ClassList classList = jsonToClassList(input);
        assertNull(classList);
    }

    @Test
    void deserialize_invalidClassesMember_expectNull() {
        String input = "{\"classes\":{}}";

        ClassList classList = jsonToClassList(input);
        assertNull(classList);
    }

    @Test
    void deserialize_duplicatedClasses_expectOneTeachingClasses() {
        String input = "{\"classes\":["
                + "{\"id\":\"CS2113T-F12\",\"name\":\"\",\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}},"
                + "{\"id\":\"CS2113T-F12\",\"name\":\"Sectional Group C03\","
                + "\"studentList\":{\"students\":[]},\"assessmentList\":{\"assessments\":[]}}]}";

        ClassList classList = jsonToClassList(input);
        assertEquals(classList.getSize(), 1);
    }

    @Test
    void deserialize_oneNullClass_expectOneTeachingClasses() {
        String input = "{\"classes\":["
                + "{\"id\":\"CS2113T-F12\",\"name\":\"\",\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}},"
                + "null]}";

        ClassList classList = jsonToClassList(input);
        assertEquals(classList.getSize(), 1);
    }
}