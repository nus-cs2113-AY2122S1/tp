package seedu.duke.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.ui.TextUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//@@author exetr

/**
 * The storage class handles all file input/output operations of Libmgr.
 */
public class Storage {
    protected String dataDir;
    protected String dataFile;
    private File data;
    private TextUI ui;
    private JsonFactory jsonFactory;
    private static final String DEFAULT_PATH = "data/data.json";
    private static final String ERR_IOEXCEPTION = "(!) Critical Error: IO Exception";
    private static final String ERR_JSON_FORMAT = "(!) Critical Error: Malformed JSON data, no data will be loaded"
            + System.lineSeparator()
            + "If you wish to retain the data file, exit the program immediately, do not execute any other commands";
    private static final String WARN_MISSING_CONTENT = "(*) Warning: Missing fields found, some data may be missing"
            + System.lineSeparator()
            + "To resolve manually, exit the program immediately, do not execute any other commands";
    private static final String INFO_NO_EXISTING_FILE = "(*) No existing data found, creating new one: %s ...";
    private static final String SUCCESS_DATA_FOUND = "(+) Data file found %s, loading ...";
    private static final String SUCCESS_DATA_LOADED = "(+) Loaded %d records";
    private static final String JSON_SKELETON = "{\"audio\":[],\"book\":[],\"item\":[],\"magazine\":[],\"video\":[]}";

    /**
     * Constructor for Storage class.
     * @param ui UI object that is used to print status messages.
     */
    public Storage(TextUI ui) {
        this.ui = ui;
        // Split path into segments and validate
        this.dataDir = DEFAULT_PATH.split("/")[0];
        this.dataFile = DEFAULT_PATH.split("/")[1];
        data = validatePath();
        this.jsonFactory = new JsonFactory();
    }

    /**
     * Validates that subdirectory exists, if not create one based on directory path specified.
     * Validates that file within subdirectory exists, if not create new file and insert skeleton JSON structure.
     * @return File Java file object pointing to data.json file
     */
    private File validatePath() {
        // Check for existence of specified directory
        File directory = new File(dataDir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(dataDir + "/" + dataFile);
        // Check for existence of specified file (data.json)
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

    /**
     * Outputs current state of catalogue to data.json.
     * @param catalogue List of items within the library
     */
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

    /**
     * Reads and parses data from existing data.json files.
     * @param catalogue Container for list of items within the library.
     */
    public void read(Catalogue catalogue) {
        try {
            // Parses all data within data.json
            ArrayList<Item> itemsArrayList = new ArrayList<Item>(jsonFactory.fromJson(data));
            catalogue.setItemsArrayList(itemsArrayList);
            ui.print(String.format(SUCCESS_DATA_LOADED, itemsArrayList.size()));
        } catch (JsonProcessingException e) {
            // If data.json is corrupted or malformed, ignore all data within and start with empty catalogue
            catalogue.setItemsArrayList(new ArrayList<Item>());
            ui.print(ERR_JSON_FORMAT);
        } catch (IOException e) {
            ui.print(ERR_IOEXCEPTION);
        } catch (NullPointerException e) {
            // If a single/many fields are missing, ignore any data within and continue to process the rest
            ui.print(WARN_MISSING_CONTENT);
        }
    }


}
