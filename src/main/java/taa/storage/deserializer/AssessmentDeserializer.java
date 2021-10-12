package taa.storage.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.assessment.Assessment;
import taa.util.Util;

import java.lang.reflect.Type;

public class AssessmentDeserializer extends StorageDeserializer implements JsonDeserializer<Assessment> {
    private static final String MEMBER_NAME = "name";
    private static final String MEMBER_WEIGHTAGE = "weightage";
    private static final String[] MEMBERS = {MEMBER_NAME, MEMBER_WEIGHTAGE};

    @Override
    public Assessment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, MEMBERS)) {
            return null;
        }

        JsonElement nameJson = jsonObject.get(MEMBER_NAME);
        String name = nameJson.getAsString();

        JsonElement weightageJson = jsonObject.get(MEMBER_WEIGHTAGE);
        if (!Util.isStringDouble(weightageJson.getAsString())) {
            return null;
        }
        double weightage = weightageJson.getAsDouble();

        Assessment assessment = new Assessment(name, weightage);
        if (!assessment.verify()) {
            return null;
        }

        return assessment;
    }
}
