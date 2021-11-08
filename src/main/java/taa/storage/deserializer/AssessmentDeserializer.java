package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.Parser;
import taa.Taa;
import taa.assessment.Assessment;
import taa.util.Util;

import java.lang.reflect.Type;

public class AssessmentDeserializer extends StorageDeserializer implements JsonDeserializer<Assessment> {
    private static final String MEMBER_NAME = "name";
    private static final String MEMBER_WEIGHTAGE = "weightage";
    private static final String MEMBER_MAXIMUM_MARKS = "maximumMarks";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_NAME, MEMBER_MAXIMUM_MARKS, MEMBER_WEIGHTAGE};

    @Override
    public Assessment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement nameJson = jsonObject.get(MEMBER_NAME);
        String name = getJsonElementAsString(nameJson);

        if (!Parser.isValueValid(name)) {
            return null;
        }

        JsonElement weightageJson = jsonObject.get(MEMBER_WEIGHTAGE);
        if (!Util.isStringDouble(getJsonElementAsString(weightageJson), Taa.DECIMAL_PLACES)) {
            return null;
        }
        double weightage = weightageJson.getAsDouble();

        JsonElement maximumMarksJson = jsonObject.get(MEMBER_MAXIMUM_MARKS);
        if (!Util.isStringDouble(getJsonElementAsString(maximumMarksJson), Taa.DECIMAL_PLACES)) {
            return null;
        }
        double maximumMarks = maximumMarksJson.getAsDouble();

        Assessment assessment = new Assessment(name, maximumMarks, weightage);
        if (!assessment.verify()) {
            return null;
        }

        return assessment;
    }
}
