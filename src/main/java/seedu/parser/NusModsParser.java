package seedu.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import seedu.module.Module;
import seedu.module.ModList;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class NusModsParser {

    private static final String MODULE_API = "https://api.nusmods.com/v2/2021-2022/modules/";

    public static void setup(ModList modList) {
        TextUi.printLoadStartMessage();
        if (!createModListFromSave(modList)) {
            TextUi.printLoadError();
        }
    }

    private static boolean createModListFromSave(ModList modList) {
        try {
            modList.clearMods();
            File dir = new File("data/Modules/");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    InputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(child.toPath()));
                    JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
                    Module module = new Gson().fromJson(reader, Module.class);
                    modList.addMod(module);
                }
                TextUi.printLoadSuccessMessage(modList.getSize());
            } else {
                // Handle the case where dir is not really a directory.
                // Checking dir.isDirectory() above would not be sufficient
                // to avoid race conditions with another process that deletes
                // directories.
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void update(ModList modList) throws IOException, InterruptedException {
        TextUi.printUpdateStartMessage();
        getModList();
        createModListFromSave(modList);
        TextUi.printUpdateSuccessMessage();
    }

    private static void getModList() throws IOException {
        String url = "https://api.nusmods.com/v2/2021-2022/moduleInfo.json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int count = 0;
        try {
            InputStream inputStream = con.getInputStream();
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            reader.beginArray();
            while (reader.hasNext()) {
                Module module = new Gson().fromJson(reader, Module.class);
                downloadModInfo(module);
                System.out.println("[" + count + "] " + module.getModuleCode());
                count++;
            }
            reader.endArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downloadModInfo(Module module) {
        String moduleCode = module.getModuleCode();
        try {
            String url = MODULE_API + moduleCode + ".json";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                String path = "data/Modules/" + moduleCode + ".json";
                ModStorage.createModJson(path);
                addToFile(inputLine, path);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    private static void addToFile(String input, String path) throws IOException {
        FileWriter fw = new FileWriter(path, false);
        String output = input + "\n";
        fw.append(output);
        fw.close();
    }


    public static Module fetchModFromAPI(String moduleCode) {
        try {
            String url = MODULE_API + moduleCode + ".json";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream()));
            return new Gson().fromJson(reader, Module.class);
        } catch (Exception e) {
            System.out.println("error");
        }
        return null;
    }

    public static Module fetchModFromFile(String path) {
        //TODO Maybe add a method to fetch from the files we downloaded?
        return null;
    }

}
