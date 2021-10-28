package taa.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import taa.exception.TaaException;
import taa.module.ModuleList;
import taa.storage.deserializer.ModuleListDeserializer;
import taa.util.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String MESSAGE_FORMAT_FAIL_CREATE_FILE = "Fail to create file/folder - %s";
    private static final String MESSAGE_FORMAT_UNABLE_TO_OPEN_READ = "Unable to open file to read - %s";
    private static final String MESSAGE_FORMAT_UNABLE_TO_OPEN_WRITE = "Unable to open file to write - %s";
    private static final String MESSAGE_FORMAT_UNABLE_TO_READ_JSON = "Unable to read from JSON file - %s";
    private static final String MESSAGE_FORMAT_UNABLE_TO_WRITE_JSON = "Unable to write to JSON file - %s";
    private static final String MESSAGE_FORMAT_JSON_SYNTAX_ERROR = "JSON file syntax error - %s";

    private final String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    /**
     * Load the module list from the file.
     *
     * @return A ModuleList object after parsing from the file.
     * @throws TaaException if fail to open, read, or parse the file.
     */
    public ModuleList load() throws TaaException {
        if (!Util.fileExists(filename)) {
            return null;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ModuleList.class, new ModuleListDeserializer());
        Gson gson = gsonBuilder.create();

        ModuleList moduleList;
        try {
            FileReader fileReader = new FileReader(filename);
            JsonReader jsonReader = new JsonReader(fileReader);
            moduleList = gson.fromJson(jsonReader, ModuleList.class);
            jsonReader.close();
        } catch (FileNotFoundException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_OPEN_READ, Util.getAbsolutePath(filename)));
        } catch (JsonIOException | IOException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_READ_JSON, Util.getAbsolutePath(filename)));
        } catch (JsonSyntaxException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_JSON_SYNTAX_ERROR, Util.getAbsolutePath(filename)));
        }

        return moduleList;
    }

    /**
     * Saves a ModuleList object to the file.
     *
     * @param moduleList The ModuleList object to save.
     * @throws TaaException if fail to create, open, or write to the file.
     */
    public void save(ModuleList moduleList) throws TaaException {
        if (!Util.createFile(filename)) {
            throw new TaaException(String.format(MESSAGE_FORMAT_FAIL_CREATE_FILE, Util.getAbsolutePath(filename)));
        }

        assert Util.fileExists(filename);

        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter(filename);
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            gson.toJson(moduleList, ModuleList.class, jsonWriter);
            jsonWriter.close();
        } catch (IOException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_OPEN_WRITE, Util.getAbsolutePath(filename)));
        } catch (JsonIOException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_WRITE_JSON, Util.getAbsolutePath(filename)));
        }
    }
}
