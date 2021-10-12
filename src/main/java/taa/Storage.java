package taa;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import taa.exception.TaaException;
import taa.module.ModuleList;
import taa.util.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static final String MESSAGE_FORMAT_FAIL_CREATE_FILE = "Fail to create file - %s";
    private static final String MESSAGE_FORMAT_UNABLE_TO_OPEN_FILE = "Unable to open file - %s";
    private static final String MESSAGE_FORMAT_UNABLE_TO_READ_JSON = "Unable to read from JSON file - %s";
    private static final String MESSAGE_FORMAT_UNABLE_TO_WRITE_JSON = "Unable to write to JSON file - %s";
    private static final String MESSAGE_FORMAT_JSON_SYNTAX_ERROR = "JSON file syntax error - %s";

    private final String filename;
    private boolean isEnabled;

    public Storage(String filename) {
        this.filename = filename;
        this.isEnabled = true;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public ModuleList load() throws TaaException {
        if (!isEnabled) {
            return null;
        }

        if (!Util.fileExists(filename)) {
            return null;
        }

        ModuleList moduleList;
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader(filename);
            JsonReader jsonReader = new JsonReader(fileReader);
            moduleList = gson.fromJson(jsonReader, ModuleList.class);
            jsonReader.close();
        } catch (FileNotFoundException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_OPEN_FILE, filename));
        } catch (JsonIOException | IOException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_READ_JSON, filename));
        } catch (JsonSyntaxException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_JSON_SYNTAX_ERROR, filename));
        }

        if (!moduleList.verify()) {
            moduleList = null;
        }

        return moduleList;
    }

    public void save(ModuleList moduleList) throws TaaException {
        if (!isEnabled) {
            return;
        }

        if (!Util.createFile(filename)) {
            throw new TaaException(String.format(MESSAGE_FORMAT_FAIL_CREATE_FILE, filename));
        }

        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter(filename);
            JsonWriter jsonWriter = new JsonWriter(fileWriter);
            gson.toJson(moduleList, ModuleList.class, jsonWriter);
            jsonWriter.close();
        } catch (IOException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_OPEN_FILE, filename));
        } catch (JsonIOException e) {
            throw new TaaException(String.format(MESSAGE_FORMAT_UNABLE_TO_WRITE_JSON, filename));
        }
    }
}
