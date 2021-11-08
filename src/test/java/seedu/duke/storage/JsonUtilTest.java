package seedu.duke.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import seedu.duke.storage.models.ExerciseModel;
import seedu.duke.storage.models.WorkoutListModel;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonUtilTest {
    private final String buggyJsonString = "{ buggy json }";

    @Test
    void parse_exerciseJson_expectStringToParseToJsonNode() throws IOException {
        String exerciseJson = "{\"description\":\"Push ups\",\"sets\":\"3\",\"reps\":\"10\",\"isDone\":\"false\"}";
        JsonNode node = JsonUtil.parse(exerciseJson);
        assertEquals("Push ups", node.get("description").asText());
    }

    @Test
    void parse_buggyJsonString_expectException() {
        assertThrows(IOException.class, () -> JsonUtil.parse(buggyJsonString));
    }

    @Test
    void fromJson_testJsonString_expectCorrectExtractedWorkoutNames() throws IOException {
        String testJsonString = "{\n"
                + "  \"workouts\" : [ {\n"
                + "    \"exercises\" : [ ],\n"
                + "    \"workoutName\" : \"Abs for Days\"\n"
                + "  }, {\n"
                + "    \"exercises\" : [ ],\n"
                + "    \"workoutName\" : \"Arms like Guns\"\n"
                + "  } ]\n"
                + "}";
        JsonNode node = JsonUtil.parse(testJsonString);
        WorkoutListModel workoutListModel = JsonUtil.fromJson(node, WorkoutListModel.class);
        assertEquals("Abs for Days", workoutListModel.getWorkouts().get(0).getWorkoutName());
        assertEquals("Arms like Guns", workoutListModel.getWorkouts().get(1).getWorkoutName());
    }

    @Test
    void toJson_exerciseModel_expectClassAttributesInJsonNode() {
        ExerciseModel exerciseModel = new ExerciseModel("Push ups", "3", "10", "false");
        JsonNode node = JsonUtil.toJson(exerciseModel);
        assertEquals("Push ups", node.get("description").asText());
        assertEquals("false", node.get("isDone").asText());
    }

    @Test
    void stringify_exerciseModel_expectJsonString() throws JsonProcessingException {
        ExerciseModel exerciseModel = new ExerciseModel("Push ups", "3", "10", "false");
        JsonNode node = JsonUtil.toJson(exerciseModel);
        String expectedJsonString =
                "{\"description\":\"Push ups\",\"sets\":\"3\",\"reps\":\"10\",\"isDone\":\"false\"}";
        assertEquals(expectedJsonString, JsonUtil.stringify(node, false));
    }
}
