package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import taa.assessment.Assessment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AssessmentDeserializerTest {
    Assessment jsonToAssessment(String jsonString) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Assessment.class, new AssessmentDeserializer());
        Gson gson = gsonBuilder.create();

        return gson.fromJson(jsonString, Assessment.class);
    }

    @Test
    void deserialize_validMembers_expectNotNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNotNull(assessment);
    }

    @Test
    void deserialize_missingNameMember_expectNull() {
        String input = "{\"maximumMarks\":30.0,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_nullNameMember_expectNull() {
        String input = "{\"name\":null,\"maximumMarks\":30.0,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_emptyNameMember_expectNull() {
        String input = "{\"name\":\"\",\"maximumMarks\":30.0,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_invalidNameMember_expectNull() {
        String input = "{\"name\":\"hello-world$\",\"maximumMarks\":30.0,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_missingMaximumMarksMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_nullMaximumMarksMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":null,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_emptyStringMaximumMarksMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":\"\",\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_intMaximumMarksMember_expectNotNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNotNull(assessment);
    }

    @Test
    void deserialize_invalidMaximumMarksMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":\"hello\",\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_zeroMaximumMarksMember_expectNotNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":0,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNotNull(assessment);
    }

    @Test
    void deserialize_underLimitMaximumMarksMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":-0.1,\"weightage\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_missingWeightageMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_nullWeightageMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":null}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_emptyStringWeightageMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":\"\"}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_intWeightageMember_expectNotNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":30}";

        Assessment assessment = jsonToAssessment(input);
        assertNotNull(assessment);
    }

    @Test
    void deserialize_invalidWeightageMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":\"hello\"}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_zeroWeightageMember_expectNotNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":0}";

        Assessment assessment = jsonToAssessment(input);
        assertNotNull(assessment);
    }

    @Test
    void deserialize_underLimitWeightageMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":-0.1}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_aboveLimitWeightageMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":100.1}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }

    @Test
    void deserialize_negativeZeroWeightageMember_expectNull() {
        String input = "{\"name\":\"Midterm\",\"maximumMarks\":30.0,\"weightage\":-0.0}";

        Assessment assessment = jsonToAssessment(input);
        assertNull(assessment);
    }
}