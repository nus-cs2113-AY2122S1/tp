package seedu.online;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import seedu.module.Module;
import seedu.module.PrerequisiteTree;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Adapter Class for GSON Parsing.
 * This adapter assists GSON to parse the recursive pre-requisite trees that vary
 * from module to module.
 *
 * Some modules have deeply nested trees for their pre-requisites (such as CS4243), while
 * others have simple pre-requisites (such as CS2040)
 */
public class PrerequisiteTreeAdapter extends TypeAdapter<PrerequisiteTree> {

    private final Gson gson;

    public PrerequisiteTreeAdapter(Gson gson) {
        this.gson = gson;
    }

    /**
     * Writes the Prerequisite tree component into json as an object or string depending how it has
     * been created.
     *
     * E.g. CS2040's Prerequisite Tree is simply "CS1010" so it will be written as a string.
     * But other modules like CS4243 have recursive trees for their prerequisite tree and are
     * written as an object.
     *
     * @param jsonWriter the jsonWriter by GSON
     * @param tree the prerequisite tree to be written into json format
     * @throws IOException if an error occurs during jsonWriting.
     */
    @Override
    public void write(JsonWriter jsonWriter, PrerequisiteTree tree) throws IOException {

        if (tree.getCondition() == null) {
            writeAsString(jsonWriter, tree.getModules().get(0));
        } else {
            writeAsObject(jsonWriter,tree);
        }
    }

    private void writeAsString(JsonWriter jsonWriter, String module) throws IOException {
        jsonWriter.value(module);
    }

    private void writeAsObject(JsonWriter jsonWriter, PrerequisiteTree tree) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name(tree.getCondition());
        jsonWriter.beginArray();
        writeModules(jsonWriter, tree.getModules());
        writeTree(jsonWriter,tree.getTrees());
        jsonWriter.endArray();
        jsonWriter.endObject();
    }

    private void writeModules(JsonWriter jsonWriter, ArrayList<String> modules) throws IOException {
        for (String module : modules) {
            jsonWriter.value(module);
        }
    }

    private void writeTree(JsonWriter jsonWriter, ArrayList<PrerequisiteTree> trees) throws IOException {
        for (PrerequisiteTree tree : trees) {
            writeAsObject(jsonWriter, tree);
        }
    }

    /**
     * Reads the json at the line and if parses it differently if the value is an object
     * or if its a simple String.
     * @param jsonReader jsonReader by GSON
     * @return A PrerequisiteTree that has been parsed
     * @throws IOException during json reading if the value is not a tree or string
     */
    @Override
    public PrerequisiteTree read(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
        case STRING:
            return getAsArray(jsonReader);
        case BEGIN_OBJECT:
            return getAsTree(jsonReader);
        default:
            throw new IOException();
        }
    }

    private PrerequisiteTree getAsArray(JsonReader jsonReader) throws IOException {
        PrerequisiteTree tree = new PrerequisiteTree();
        tree.addModule(jsonReader.nextString());
        return tree;
    }

    private PrerequisiteTree getAsTree(JsonReader jsonReader) throws IOException {
        PrerequisiteTree tree = new PrerequisiteTree();
        jsonReader.beginObject();
        tree.setCondition(jsonReader.nextName());
        if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
            recursiveArrayParse(jsonReader, tree);
        } else {
            throw new IOException();
        }
        jsonReader.endObject();
        return tree;
    }

    private void recursiveArrayParse(JsonReader jsonReader, PrerequisiteTree tree) throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            switch (jsonReader.peek()) {
            case STRING:
                tree.addModule(jsonReader.nextString());
                break;
            case BEGIN_OBJECT:
                tree.addTree(getAsTree(jsonReader));
                break;
            default:
                throw new IOException();
            }
        }
        jsonReader.endArray();
    }






}
