package taa.storage.deserializer;

//@@author leyondlee
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
}
