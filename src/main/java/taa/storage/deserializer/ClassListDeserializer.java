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

        JsonElement classesJson = jsonObject.get(MEMBER_CLASSES);
        if (!classesJson.isJsonArray()) {
            return null;
        }

        ClassList classList = new ClassList();
        deserializeClasses(classesJson, classList);
        if (!classList.verify()) {
            return null;
        }

        return classList;
    }

    private void deserializeClasses(JsonElement classesJson, ClassList classList) throws JsonParseException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TeachingClass.class, new TeachingClassDeserializer());
        Gson gson = gsonBuilder.create();

        JsonArray classesJsonArray = classesJson.getAsJsonArray();
        for (JsonElement teachingClassJson : classesJsonArray) {
            TeachingClass teachingClass = gson.fromJson(teachingClassJson, TeachingClass.class);
            if (teachingClass != null) {
                classList.addClass(teachingClass);
            }
        }
    }
}
