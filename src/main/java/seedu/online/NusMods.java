package seedu.online;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import seedu.command.flags.SearchFlags;
import seedu.module.Module;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NusMods {

    private static Logger logger = Logger.getLogger("");

    private static final String MODULE_API = "https://api.nusmods.com/v2/2021-2022/modules/";

    public static void searchModsOnline(String searchTerm, SearchFlags searchFlags) throws IOException {
        InputStream inputStream = getOnlineModList();
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        int count = 0;
        reader.beginArray();
        while (reader.hasNext()) {
            Module module = new Gson().fromJson(reader, Module.class);
            if (isMatch(module, searchTerm, searchFlags)) {
                count++;
            }
        }
        reader.endArray();
        TextUi.printModsFound(count);
    }

    public static boolean isMatch(Module module, String searchTerm, SearchFlags searchFlags) throws IOException {
        if (module.meetsPreliminaryConditions(searchTerm, searchFlags)) {
            String moduleCode = module.getModuleCode();
            module = fetchModOnline(moduleCode);
            if (module.meetsSecondaryConditions(searchFlags)) {
                TextUi.printModBriefDescription(module);
                return true;
            }
        }
        return false;
    }

    public static Module fetchModOnline(String moduleCode) throws IOException {
        try {
            InputStream inputStream = getOnlineModInfo(moduleCode);
            ModStorage.saveModInfo(moduleCode, inputStream);
            return ModStorage.loadModInfo(moduleCode);
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private static InputStream getOnlineModList() throws IOException {
        String url = "https://api.nusmods.com/v2/2021-2022/moduleInfo.json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        return con.getInputStream();
    }

    private static InputStream getOnlineModInfo(String moduleCode) throws IOException {
        String url = MODULE_API + moduleCode + ".json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        return con.getInputStream();
    }

    public static void showModOnline(String moduleCode) throws IOException {
        Module module = fetchModOnline(moduleCode);
        TextUi.printModFullDescription(module);
    }

    public static void update() throws IOException, ModStorage.FileErrorException {
        TextUi.printUpdateStartMessage();
        InputStream inputStream = getOnlineModList();
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        int count = 0;
        reader.beginArray();
        while (reader.hasNext()) {
            Module module = new Gson().fromJson(reader, Module.class);
            String moduleCode = module.getModuleCode();
            try {
                InputStream modStream = getOnlineModInfo(moduleCode);
                ModStorage.saveModInfo(moduleCode, modStream);
                count++;
                System.out.println(count);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Failed to save mod" + moduleCode);
                TextUi.printErrorMessage();
            }
        }
        reader.endArray();
        TextUi.printUpdateSuccessMessage();
    }

}
