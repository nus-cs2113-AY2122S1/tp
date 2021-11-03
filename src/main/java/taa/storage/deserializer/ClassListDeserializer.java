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
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;

import java.lang.reflect.Type;

public class ClassListDeserializer extends StorageDeserializer implements JsonDeserializer<ClassList> {
    private static final String MEMBER_CLASSES = "classes";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_CLASSES};

    @Override
    public ClassList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement modulesJson = jsonObject.get(MEMBER_CLASSES);
        if (!modulesJson.isJsonArray()) {
            return null;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TeachingClass.class, new TeachingClassDeserializer());
        Gson gson = gsonBuilder.create();

        ClassList classList = new ClassList();
        JsonArray modulesJsonArray = modulesJson.getAsJsonArray();
        for (JsonElement moduleJson : modulesJsonArray) {
            TeachingClass teachingClass = gson.fromJson(moduleJson, TeachingClass.class);
            if (teachingClass != null) {
                classList.addClass(teachingClass);
            }
        }

        if (!classList.verify()) {
            return null;
        }

        return classList;
    }
}
