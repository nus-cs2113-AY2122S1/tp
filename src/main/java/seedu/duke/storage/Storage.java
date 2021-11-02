package seedu.duke.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import seedu.duke.data.*;
import seedu.duke.ui.TextUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    protected String dataDir;
    protected String dataFile;
    private File data;
    private JsonFactory jsonFactory;
    private static final String DEFAULT_PATH = "data/data.json";
    private static final String ERR_IOEXCEPTION = "(!) Critical Error: IO Exception";
    private static final String ERR_JSON_FORMAT = "  (!) Critical Error: Malformed JSON data";
    private static final String INFO_NO_EXISTING_FILE = "(*) No existing data found, creating new one: %s ...";
    private static final String SUCCESS_DATA_FOUND = "(+) Data file found %s, loading ...";
    private static final String SUCCESS_DATA_LOADED = "(+) Loaded %d records";

    public Storage(String dataPath) {
        this.dataDir = dataPath.split("/")[0];
        this.dataFile = dataPath.split("/")[1];
        data = validatePath();
        this.jsonFactory = new JsonFactory();
    }

    public Storage() {
        this.dataDir = DEFAULT_PATH.split("/")[0];
        this.dataFile = DEFAULT_PATH.split("/")[1];
        data = validatePath();
        this.jsonFactory = new JsonFactory();
    }

    private File validatePath() {
        File directory = new File(dataDir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(dataDir + "/" + dataFile);
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println(ERR_IOEXCEPTION);
            }
            System.out.println(String.format(INFO_NO_EXISTING_FILE, data.getAbsolutePath()));
        } else {
            System.out.println(String.format(SUCCESS_DATA_FOUND, data.getAbsoluteFile()));
        }
        return data;
    }

    public void write(TextUI ui, Catalogue catalogue) {
        try {
            FileWriter fw = new FileWriter(data);
            fw.write(jsonFactory.toJson(catalogue));
            fw.close();
        } catch (JsonProcessingException e) {
            ui.print(ERR_JSON_FORMAT);
        } catch (IOException e) {
            ui.print(ERR_IOEXCEPTION);
        }
    }

    public void read(Catalogue catalogue) {
        try {
//            ObjectNode allItems = mapper.readValue(data, ObjectNode.class);
//
//            ArrayNode audioArray = (ArrayNode) allItems.get("audio");
            System.out.println("test");
            //System.out.println(audioArray.get(1));
            //List<Audio> audioList = allItems.get("audio");
            //List<Audio> audioList = Arrays.asList(mapper.convertValue(allItems.get("audio"), Audio[].class));
            //System.out.println(allItems.get("audio"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
