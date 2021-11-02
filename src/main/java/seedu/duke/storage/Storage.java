package seedu.duke.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import seedu.duke.data.*;
import seedu.duke.ui.TextUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected String dataDir;
    protected String dataFile;
    private File data;
    private TextUI ui;
    private JsonFactory jsonFactory;
    private static final String DEFAULT_PATH = "data/data.json";
    private static final String ERR_IOEXCEPTION = "(!) Critical Error: IO Exception";
    private static final String ERR_JSON_FORMAT = "(!) Critical Error: Malformed JSON data, no data will be loaded";
    private static final String WARN_MISSING_CONTENT = "(*) Warning: Missing fields found, ignoring";
    private static final String INFO_NO_EXISTING_FILE = "(*) No existing data found, creating new one: %s ...";
    private static final String SUCCESS_DATA_FOUND = "(+) Data file found %s, loading ...";
    private static final String SUCCESS_DATA_LOADED = "(+) Loaded %d records";
    private static final String JSON_SKELETON = "{\"audio\":[],\"book\":[],\"item\":[],\"magazine\":[],\"video\":[]}";

    public Storage(TextUI ui) {
        this.ui = ui;
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
                FileWriter fw = new FileWriter(data);
                fw.write(JSON_SKELETON);
                fw.close();
            } catch (IOException e) {
                System.out.println(ERR_IOEXCEPTION);
            }
            System.out.println(String.format(INFO_NO_EXISTING_FILE, data.getAbsolutePath()));
        } else {
            System.out.println(String.format(SUCCESS_DATA_FOUND, data.getAbsoluteFile()));
        }
        return data;
    }

    public void write(Catalogue catalogue) {
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
            ArrayList<Item> itemsArrayList = new ArrayList<Item>(jsonFactory.fromJson(data));
            catalogue.setItemsArrayList(itemsArrayList);
            ui.print(String.format(SUCCESS_DATA_LOADED, itemsArrayList.size()));
        } catch (JsonProcessingException e) {
            //catalogue.setItemsArrayList(new ArrayList<Item>());
            ui.print(ERR_JSON_FORMAT);
            System.out.println(e);
        } catch (IOException e) {
            ui.print(ERR_IOEXCEPTION);
        } catch (NullPointerException e) {
            ui.print(WARN_MISSING_CONTENT);
        }
    }


}
