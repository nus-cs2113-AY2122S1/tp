package taa.storage.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.module.ModuleList;
import taa.module.Module;

import java.lang.reflect.Type;

public class ModuleListDeserializer extends StorageDeserializer implements JsonDeserializer<ModuleList> {
    private static final String MEMBER_MODULES = "modules";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_MODULES};

    @Override
    public ModuleList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement modulesJson = jsonObject.get(MEMBER_MODULES);
        if (modulesJson == null || !modulesJson.isJsonArray()) {
            return null;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Module.class, new ModuleDeserializer());
        Gson gson = gsonBuilder.create();

        ModuleList moduleList = new ModuleList();
        JsonArray modulesJsonArray = modulesJson.getAsJsonArray();
        for (JsonElement moduleJson : modulesJsonArray) {
            Module module = gson.fromJson(moduleJson, Module.class);
            if (module != null) {
                moduleList.addModule(module);
            }
        }

        if (!moduleList.verify()) {
            return null;
        }

        return moduleList;
    }
}
