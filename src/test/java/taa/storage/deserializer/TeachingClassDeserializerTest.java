package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.student.Student;
import taa.teachingclass.TeachingClass;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TeachingClassDeserializerTest {
    TeachingClass jsonToTeachingClass(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TeachingClass.class, new TeachingClassDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, TeachingClass.class);
    }

    @Test
    void deserialize_validMembers_expectCorrectTeachingClass() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_missingIdMember_expectNull() {
        String input = "{"
                + "\"name\":\"\","
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        assertNull(teachingClass);
    }

    @Test
    void deserialize_nullIdMember_expectNull() {
        String input = "{"
                + "\"id\":null,"
                + "\"name\":\"\","
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        assertNull(teachingClass);
    }

    @Test
    void deserialize_emptyIdMember_expectNull() {
        String input = "{"
                + "\"id\":\"\","
                + "\"name\":\"\","
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        assertNull(teachingClass);
    }

    @Test
    void deserialize_invalidIdMember_expectNull() {
        String input = "{"
                + "\"id\":\"CS2113T-#F12\","
                + "\"name\":\"\","
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        assertNull(teachingClass);
    }

    @Test
    void deserialize_missingNameMember_expectNull() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        assertNull(teachingClass);
    }

    @Test
    void deserialize_nullNameMember_expectEmptyName() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":null,"
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_emptyNameMember_expectEmptyName() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_invalidNameMember_expectNull() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"#\","
                + "\"studentList\":{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        assertNull(teachingClass);
    }

    @Test
    void deserialize_missingStudentListMember_expectEmptyStudentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 0;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_nullStudentListMember_expectEmptyStudentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":null,"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 0;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_emptyStudentListMember_expectEmptyStudentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":{},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 0;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_emptyStringStudentListMember_expectEmptyStudentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":\"\","
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 0;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_invalidStudentListMember_expectEmptyStudentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":[],"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);
        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 0;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_missingAssessmentList_expectEmptyAssessmentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 0;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_nullAssessmentList_expectEmptyAssessmentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":null"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 0;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_emptyAssessmentList_expectEmptyAssessmentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 0;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_emptyStringAssessmentList_expectEmptyAssessmentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":\"\""
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 0;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_invalidAssessmentList_expectEmptyAssessmentList() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":[]"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 0;
        assertTrue(hasId && hasName && hasStudents && hasAssessments);
    }

    @Test
    void deserialize_invalidResultAssessmentNames_expectEmptyResults() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":25.0,\"Quiz 1\":5.56}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":[]"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 0;

        boolean hasResults = false;
        for (Student student : teachingClass.getStudentList().getStudents()) {
            if (!student.getResults().isEmpty()) {
                hasResults = true;
                break;
            }
        }

        assertTrue(hasId && hasName && hasStudents && hasAssessments && !hasResults);
    }

    @Test
    void deserialize_invalidMarks_expectEmptyResults() {
        String input = "{"
                + "\"id\":\"CS2113T-F12\","
                + "\"name\":\"\","
                + "\"studentList\":"
                + "{\"students\":["
                + "{\"id\":\"A1234567B\",\"name\":\"Jon Lim\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":["
                + "{\"lessonNumber\":1,\"isPresent\":true},{\"lessonNumber\":4,\"isPresent\":false}]},"
                + "\"results\":{\"Midterm\":30.1,\"Quiz 1\":10.01}},"
                + "{\"id\":\"A7654321Z\",\"name\":\"Hello-world_123 (Nothing)\",\"comment\":\"\","
                + "\"attendanceList\":{\"attendances\":[]},\"results\":{}}]},"
                + "\"assessmentList\":{\"assessments\":["
                + "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}"
                + "}";

        TeachingClass teachingClass = jsonToTeachingClass(input);

        boolean hasId = teachingClass.getId().equals("CS2113T-F12");
        boolean hasName = teachingClass.getName().equals("");
        boolean hasStudents = teachingClass.getStudentList().getSize() == 2;
        boolean hasAssessments = teachingClass.getAssessmentList().getSize() == 2;

        boolean hasResults = false;
        for (Student student : teachingClass.getStudentList().getStudents()) {
            if (!student.getResults().isEmpty()) {
                hasResults = true;
                break;
            }
        }

        assertTrue(hasId && hasName && hasStudents && hasAssessments && !hasResults);
    }
}