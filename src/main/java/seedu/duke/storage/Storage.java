package seedu.duke.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import seedu.duke.data.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    protected String dataDir;
    protected String dataFile;
    private File data;
    private static final String DEFAULT_PATH = "data/data.json";
    private static final String ERR_IOEXCEPTION = "(!) Critical Error: IO Exception";
    private static final String INFO_NO_EXISTING_FILE = "(*) No existing data found, creating new one: %s ...";
    private static final String SUCCESS_DATA_FOUND = "(+) Data file found %s, loading ...";
    private static final String SUCCESS_DATA_LOADED = "(+) Loaded %d records";
    private static final ObjectMapper mapper = new ObjectMapper();


    public Storage(String dataPath) {
        this.dataDir = dataPath.split("/")[0];
        this.dataFile = dataPath.split("/")[1];
        data = validatePath();
    }

    public Storage() {
        this.dataDir = DEFAULT_PATH.split("/")[0];
        this.dataFile = DEFAULT_PATH.split("/")[1];
        data = validatePath();
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
            System.out.println(String.format(INFO_NO_EXISTING_FILE,  data.getAbsolutePath()));
        } else {
            System.out.println(String.format(SUCCESS_DATA_FOUND, data.getAbsoluteFile()));
        }
        return data;
    }

    public void write(Catalogue catalogue) {
        try {
            ObjectNode allItems = mapper.createObjectNode();

            // Process all audio items
            allItems.set("audio", audioToJson(catalogue));
            allItems.set("book", bookToJson(catalogue));
            allItems.set("magazine", magazineToJson(catalogue));
            allItems.set("video", videoToJson(catalogue));

            FileWriter fw = new FileWriter(data);
            fw.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allItems));
            fw.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void read(Catalogue catalogue) {
        try {
            ObjectNode allItems = mapper.readValue(data, ObjectNode.class);

            ArrayNode audioArray = (ArrayNode) allItems.get("audio");
            System.out.println(audioArray.get(0));
            System.out.println(audioArray.get(1));
            //List<Audio> audioList = allItems.get("audio");
            //List<Audio> audioList = Arrays.asList(mapper.convertValue(allItems.get("audio"), Audio[].class));
            //System.out.println(allItems.get("audio"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayNode audioToJson(Catalogue catalogue) {
        ArrayNode audioArray = mapper.createArrayNode();
        List<Audio> audioObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Audio)
                .map(p -> (Audio) p).collect(Collectors.toList());
        for (Audio a : audioObjects) {
            audioArray.add(mapper.convertValue(a, ObjectNode.class));
        }
        return audioArray;
    }

    private ArrayNode bookToJson(Catalogue catalogue) {
        ArrayNode bookArray = mapper.createArrayNode();
        List<Book> bookObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Book)
                .map(p -> (Book) p).collect(Collectors.toList());
        for (Book a : bookObjects) {
            bookArray.add(mapper.convertValue(a, ObjectNode.class));
        }
        return bookArray;
    }

    private ArrayNode magazineToJson(Catalogue catalogue) {
        ArrayNode magazineArray = mapper.createArrayNode();
        List<Magazine> magazineObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Magazine)
                .map(p -> (Magazine) p).collect(Collectors.toList());
        for (Magazine a : magazineObjects) {
            magazineArray.add(mapper.convertValue(a, ObjectNode.class));
        }
        return magazineArray;
    }

    private ArrayNode videoToJson(Catalogue catalogue) {
        ArrayNode videoArray = mapper.createArrayNode();
        List<Video> videoObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Video)
                .map(p -> (Video) p).collect(Collectors.toList());
        for (Video a : videoObjects) {
            videoArray.add(mapper.convertValue(a, ObjectNode.class));
        }
        return videoArray;
    }
}
