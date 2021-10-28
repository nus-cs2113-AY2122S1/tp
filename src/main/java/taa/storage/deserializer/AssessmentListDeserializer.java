package taa.storage.deserializer;

//@@author leyondlee
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;

import java.lang.reflect.Type;

public class AssessmentListDeserializer extends StorageDeserializer implements JsonDeserializer<AssessmentList> {
    private static final String MEMBER_ASSESSMENTS = "assessments";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_ASSESSMENTS};

    @Override
    public AssessmentList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement assessmentsJson = jsonObject.get(MEMBER_ASSESSMENTS);
        if (!assessmentsJson.isJsonArray()) {
            return null;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Assessment.class, new AssessmentDeserializer());
        Gson gson = gsonBuilder.create();

        AssessmentList assessmentList = new AssessmentList();
        JsonArray assessmentsJsonArray = assessmentsJson.getAsJsonArray();
        for (JsonElement assessmentJson : assessmentsJsonArray) {
            Assessment assessment = gson.fromJson(assessmentJson, Assessment.class);
            if (assessment != null) {
                assessmentList.addAssessment(assessment);
            }
        }

        if (!assessmentList.verify()) {
            return null;
        }

        return assessmentList;
    }
}
