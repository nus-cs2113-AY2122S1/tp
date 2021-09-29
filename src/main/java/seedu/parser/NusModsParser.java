package seedu.parser;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import seedu.module.Mod;
import seedu.module.ModList;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

public class NusModsParser {

    public static void setup(ModList modList) {
        if (!createModListFromSave(modList)) {
            TextUi.printLoadError();
        }
    }

    private static boolean createModListFromSave(ModList modList) {
        try {
            File dir = new File("data/Modules/");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    InputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(child.toPath()));
                    JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
                    Mod mod = new Gson().fromJson(reader, Mod.class);
                    modList.addMod(mod);
                    System.out.println(mod.getModuleCode());
                }
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

    private static ModList getModInfo(ModList modList) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.nusmods.com/v2/2021-2022/moduleInfo.json"))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        try {
            InputStream inputStream = new ByteArrayInputStream(response.body().getBytes());
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            reader.beginArray();

            while (reader.hasNext()) {
                Mod mod = new Gson().fromJson(reader, Mod.class);
                modList.addMod(mod);
            }

            reader.endArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return modList;
    }

    private static void saveModList(ModList modList) throws IOException {
        Gson gson = new Gson();
        Writer writer = new FileWriter("data/Modules.json");
        writer.write("[");
        for (int i = 0; i < modList.getSize(); i++) {
            if (i != 0) {
                writer.write(",");
            }
            gson.toJson(modList.getMod(i), writer);
        }
        writer.write("]");
        writer.flush();
        writer.close();
    }

    private static void saveIndividualMods(ModList modList) throws IOException {
        for (int i = 0; i < modList.getSize(); i++) {
            try {
                saveMod(modList.getMod(i));
            } catch (ModStorage.FileErrorException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveMod(Mod mod) throws IOException, ModStorage.FileErrorException {
        Gson gson = new Gson();
        String path = "data/Modules/" + mod.getModuleCode() + ".json";
        ModStorage.createModJson(path);
        Writer writer = new FileWriter(path);
        gson.toJson(mod, writer);
        writer.flush();
        writer.close();
    }

    private static void fetchModData(ModList modList, Mod mod) {
        String modCode = mod.getModuleCode();
        try {
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.nusmods.com/v2/2021-2022/modules/" + modCode + ".json"))
                    .header("accept", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            mod = new Gson().fromJson(response.body(), Mod.class);
            modList.addMod(mod);
        } catch (Exception e) {
            System.out.println("error");
        }
        System.out.print(modList.getSize() + " / ");
    }

    public static void update(ModList modList) throws IOException, InterruptedException {
        TextUi.printUpdateStartMessage();
        ModList allModsList = getModInfo(new ModList());
        saveModList(allModsList);
        modList.clearMods();
        populateModList(modList, allModsList);
        saveIndividualMods(modList);
        TextUi.printUpdateSuccessMessage();
    }

    private static void populateModList(ModList modList, ModList allModsList) {
        int numberOfModules = allModsList.getSize();

        for (int i = 0; i < numberOfModules; i++) {
            fetchModData(modList, allModsList.getMod(i));
            System.out.println(numberOfModules);
        }
    }
}
