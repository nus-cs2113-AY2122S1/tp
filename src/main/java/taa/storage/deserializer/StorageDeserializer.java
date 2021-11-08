package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class StorageDeserializer {
    protected boolean hasMembers(JsonObject jsonObject, String[] members) {
        for (String member : members) {
            if (!jsonObject.has(member)) {
                return false;
            }
        }

        return true;
    }

    protected String getJsonElementAsString(JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return null;
        }

        return jsonElement.getAsString();
    }
}
