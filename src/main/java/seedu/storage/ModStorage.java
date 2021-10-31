package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import seedu.command.flags.SearchFlags;
import seedu.module.Module;
import seedu.ui.TextUi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;

public class ModStorage {

    /**
     * Saves the given mod into a json.
     * @param moduleCode Code of mod to save.
     * @param inputStream InputStream of the mod from NUSMods.
     * @throws IOException if there is an IO operation error.
     * @throws FileErrorException if file is unable to be created.
     */
    public static void saveModInfo(String moduleCode, InputStream inputStream) throws IOException, FileErrorException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            String path = "data/Modules/" + moduleCode + ".json";
            createModJson(path);
            addToFile(inputLine, path);
        }
        in.close();
    }

    /**
     * Adds a line to file.
     * @param input Line to input.
     * @param path Path of file.
     * @throws IOException if there is an IO operation error.
     */
    private static void addToFile(String input, String path) throws IOException {
        FileWriter fw = new FileWriter(path, false);
        String output = input + "\n";
        fw.append(output);
        fw.close();
    }

    /**
     * Creates a mod json at the path.
     * @param savePath Path to create file at.
     * @return true if file is created, false otherwise.
     * @throws FileErrorException if file was unable to be created.
     */
    public static boolean createModJson(String savePath) throws FileErrorException {
        try {
            File modFile = new File(savePath);
            if (!modFile.exists()) {
                modFile.getParentFile().mkdirs();
            }
            if (modFile.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            throw new FileErrorException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
        return false;
    }

    /**
     * Searches all files in the data/Modules directory, prints matching mods and the number of matching mods.
     * @param searchTerm Search term to search for.
     * @param searchFlags Flags to check mods against.
     */
    public static void searchModsOffline(String searchTerm, SearchFlags searchFlags) {
        File dir = new File("data/Modules/");
        File[] fileList = dir.listFiles();
        if (fileList != null) {
            try {
                searchFiles(fileList, searchTerm, searchFlags);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        TextUi.printLocalSearchMessage();
    }

    /**
     * Searches all files in the given file list and prints matching mods.
     * @param fileList list of Files to search in.
     * @param searchTerm Search term to search for.
     * @param searchFlags Flags to check mods against.
     * @throws IOException if there is an IO operation error.
     */
    private static void searchFiles(File[] fileList, String searchTerm, SearchFlags searchFlags) throws IOException {
        int count = 0;
        for (File file : fileList) {
            String extension = ".json";
            int extensionIndex = file.getName().indexOf(extension);
            String modCode = file.getName().substring(0, extensionIndex);
            Module module = new Module(modCode);
            if (module.meetsPreliminaryConditions(searchTerm, searchFlags)) {
                module = loadModInfo(modCode);
                if (module.meetsSecondaryConditions(searchFlags)) {
                    TextUi.printModBriefDescription(module);
                    count++;
                }
            }
        }
        TextUi.printModsFound(count);
    }

    /**
     * Loads module info from local data.
     * @param moduleCode mod to load.
     * @return module that was loaded.
     * @throws IOException if there is an IO operation error.
     */
    public static Module loadModInfo(String moduleCode) throws IOException {
        File file = new File("./data/Modules/" + moduleCode + ".json");
        try {
            InputStream inputStream = new ByteArrayInputStream(Files.readAllBytes(file.toPath()));
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            return new Gson().fromJson(reader, Module.class);
        } catch (InvalidPathException e) {
            throw new IOException();
        }
    }

    public static class FileErrorException extends Exception {
    }

}
