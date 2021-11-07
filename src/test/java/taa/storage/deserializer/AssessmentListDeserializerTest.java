package taa.storage.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.assessment.AssessmentList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AssessmentListDeserializerTest {
    AssessmentList jsonToAssessmentList(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AssessmentList.class, new AssessmentListDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, AssessmentList.class);
    }

    @Test
    void deserialize_validMembers_expectTwoItems() {
        String input = "{\"assessments\":[{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertEquals(assessmentList.getSize(), 2);
    }

    @Test
    void deserialize_missingAssessmentsMember_expectNull() {
        String input = "{}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertNull(assessmentList);
    }

    @Test
    void deserialize_nullAssessmentsMember_expectNull() {
        String input = "{\"assessments\":null}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertNull(assessmentList);
    }

    @Test
    void deserialize_emptyAssessmentsMember_expectZeroItem() {
        String input = "{\"assessments\":[]}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertEquals(assessmentList.getSize(), 0);
    }

    @Test
    void deserialize_emptyStringAssessmentsMember_expectNull() {
        String input = "{\"assessments\":\"\"}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertNull(assessmentList);
    }

    @Test
    void deserialize_oneNullAssessment_expectOneItem() {
        String input = "{\"assessments\":[{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "null]}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertEquals(assessmentList.getSize(), 1);
    }

    @Test
    void deserialize_oneInvalidAssessmentName_expectOneItem() {
        String input = "{\"assessments\":[{\"name\":\"#Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz 1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertEquals(assessmentList.getSize(), 1);
    }

    @Test
    void deserialize_twoInvalidAssessmentName_expectZeroItem() {
        String input = "{\"assessments\":[{\"name\":\"#Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Quiz $1\",\"maximumMarks\":10.0,\"weightage\":5.0}]}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertEquals(assessmentList.getSize(), 0);
    }

    @Test
    void deserialize_duplicatedAssessmentName_expectOneItem() {
        String input = "{\"assessments\":[{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0},"
                + "{\"name\":\"Midterm\",\"maximumMarks\":10.0,\"weightage\":5.0}]}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertEquals(assessmentList.getSize(), 1);
    }

    @Test
    void deserialize_exceedRange_expectNull() {
        String input = "{\"assessments\":[{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":50.0},"
                + "{\"name\":\"Midterm_2\",\"maximumMarks\":10.0,\"weightage\":51}]}";

        AssessmentList assessmentList = jsonToAssessmentList(input);
        assertNull(assessmentList);
    }
}