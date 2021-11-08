package seedu.duke.storage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Item;
import seedu.duke.data.Audio;
import seedu.duke.data.Book;
import seedu.duke.data.Magazine;
import seedu.duke.data.Miscellaneous;
import seedu.duke.data.Video;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@@author exetr

/**
 * The JsonFactory class handles all logic pertaining to serialization and deserialization of the catalogue.
 */
public class JsonFactory {
    private static final String KEY_AUDIO = "audio";
    private static final String KEY_BOOK = "book";
    private static final String KEY_ITEM = "item";
    private static final String KEY_MAGAZINE = "magazine";
    private static final String KEY_VIDEO = "video";
    private static final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode allItems;

    /**
     * Default constructor, configures relevant parameters of the Object Mapper.
     */
    public JsonFactory() {
        //Disable serializing dates as timestamp
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //Exclude null values
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //Specify time module
        JavaTimeModule timeModule = new JavaTimeModule();
        mapper.registerModule(timeModule);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        this.allItems = mapper.createObjectNode();
    }

    /**
     * Serializes the current state of the catalogue into JSON format.
     * @param catalogue Current state of the catalogue
     * @return String Serialized content of the catalogue in JSON.
     * @throws JsonProcessingException Thrown when JSON is invalid/malformed
     */
    public String toJson(Catalogue catalogue) throws JsonProcessingException {
        // Converts all items into JSON format by category, then add as value to relevant key in overall ObjectNode
        allItems.set(KEY_AUDIO, audioToJson(catalogue));
        allItems.set(KEY_BOOK, bookToJson(catalogue));
        allItems.set(KEY_ITEM, itemToJson(catalogue));
        allItems.set(KEY_MAGAZINE, magazineToJson(catalogue));
        allItems.set(KEY_VIDEO, videoToJson(catalogue));
        // Convert overall ObjectNode into String format (JSON)
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allItems);
    }

    /**
     * Deserializes content from JSON into a catalogue object.
     * @param data Content from JSON read from file
     * @return ArrayList<Item> of all items specified within catalogue
     * @throws IOException Error raised when trying to read from file
     * @throws NullPointerException Thrown when missin gkey is detected
     */
    public ArrayList<Item> fromJson(File data) throws IOException, NullPointerException {
        // Create container for all items
        ArrayList<Item> itemArrayList = new ArrayList<>();
        // Parse content into ObjectNode
        ObjectNode json = mapper.readValue(data, ObjectNode.class);
        // Deserialize content of JSON by category, and add to array list
        itemArrayList.addAll(jsonToAudio((ArrayNode) json.get(KEY_AUDIO)));
        itemArrayList.addAll(jsonToBook((ArrayNode) json.get(KEY_BOOK)));
        itemArrayList.addAll(jsonToItem((ArrayNode) json.get(KEY_ITEM)));
        itemArrayList.addAll(jsonToMagazine((ArrayNode) json.get(KEY_MAGAZINE)));
        itemArrayList.addAll(jsonToVideo((ArrayNode) json.get(KEY_VIDEO)));
        return itemArrayList;
    }

    /**
     * Converts audio items to JSON.
     * @param catalogue Current state of catalogue
     * @return ArrayNode Object with "audio" as key and array of audio items as value.
     */
    private ArrayNode audioToJson(Catalogue catalogue) {
        ArrayNode audioArray = mapper.createArrayNode();
        List<Audio> audioObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Audio)
                .map(p -> (Audio) p).collect(Collectors.toList());
        for (Audio oneItem : audioObjects) {
            audioArray.add(mapper.convertValue(oneItem, ObjectNode.class));
        }
        return audioArray;
    }

    /**
     * Converts JSON values with key of "audio" into Audio objects.
     * @param arrayNode Deserialized arrayNode containing list of audio objects in JSON
     * @return ArrayList containing all audio items specified within JSON file
     * @throws JsonProcessingException Thrown when invalid characters found within JSON
     */
    private ArrayList<Audio> jsonToAudio(ArrayNode arrayNode) throws JsonProcessingException {
        ArrayList<Audio> itemList = new ArrayList<>();
        for (JsonNode item : arrayNode) {
            itemList.add(mapper.treeToValue(item, Audio.class));
        }
        return itemList;
    }

    /**
     * Converts book items to JSON.
     * @param catalogue Current state of catalogue
     * @return ArrayNode Object with "book" as key and array of audio items as value.
     */
    private ArrayNode bookToJson(Catalogue catalogue) {
        ArrayNode bookArray = mapper.createArrayNode();
        List<Book> bookObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Book)
                .map(p -> (Book) p).collect(Collectors.toList());
        for (Book oneItem : bookObjects) {
            bookArray.add(mapper.convertValue(oneItem, ObjectNode.class));
        }
        return bookArray;
    }

    /**
     * Converts JSON values with key of "book" into Book objects.
     * @param arrayNode Deserialized arrayNode containing list of book objects in JSON
     * @return ArrayList containing all book items specified within JSON file
     * @throws JsonProcessingException Thrown when invalid characters found within JSON
     */
    private ArrayList<Book> jsonToBook(ArrayNode arrayNode) throws JsonProcessingException {
        ArrayList<Book> itemList = new ArrayList<>();
        for (JsonNode item : arrayNode) {
            itemList.add(mapper.treeToValue(item, Book.class));
        }
        return itemList;
    }

    /**
     * Converts miscellaneous items to JSON.
     * @param catalogue Current state of catalogue
     * @return ArrayNode Object with "miscellaneous" as key and array of audio items as value.
     */
    private ArrayNode itemToJson(Catalogue catalogue) {
        ArrayNode itemArray = mapper.createArrayNode();
        List<Miscellaneous> itemObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Miscellaneous)
                .map(p -> (Miscellaneous) p).collect(Collectors.toList());
        for (Miscellaneous oneItem : itemObjects) {
            itemArray.add(mapper.convertValue(oneItem, ObjectNode.class));
        }
        return itemArray;
    }

    /**
     * Converts JSON values with key of "item" into Miscellaneous objects.
     * @param arrayNode Deserialized arrayNode containing list of miscellaneous objects in JSON
     * @return ArrayList containing all miscellaneous items specified within JSON file
     * @throws JsonProcessingException Thrown when invalid characters found within JSON
     */
    private ArrayList<Miscellaneous> jsonToItem(ArrayNode arrayNode) throws JsonProcessingException {
        ArrayList<Miscellaneous> itemList = new ArrayList<>();
        for (JsonNode item : arrayNode) {
            itemList.add(mapper.treeToValue(item, Miscellaneous.class));
        }
        return itemList;
    }

    /**
     * Converts magazine items to JSON.
     * @param catalogue Current state of catalogue
     * @return ArrayNode Object with "magazine" as key and array of audio items as value.
     */
    private ArrayNode magazineToJson(Catalogue catalogue) {
        ArrayNode magazineArray = mapper.createArrayNode();
        List<Magazine> magazineObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Magazine)
                .map(p -> (Magazine) p).collect(Collectors.toList());
        for (Magazine oneItem : magazineObjects) {
            magazineArray.add(mapper.convertValue(oneItem, ObjectNode.class));
        }
        return magazineArray;
    }

    /**
     * Converts JSON values with key of "magazine" into Magazine objects.
     * @param arrayNode Deserialized arrayNode containing list of magazine objects in JSON
     * @return ArrayList containing all magazine items specified within JSON file
     * @throws JsonProcessingException Thrown when invalid characters found within JSON
     */
    private ArrayList<Magazine> jsonToMagazine(ArrayNode arrayNode) throws JsonProcessingException {
        ArrayList<Magazine> itemList = new ArrayList<>();
        for (JsonNode item : arrayNode) {
            itemList.add(mapper.treeToValue(item, Magazine.class));
        }
        return itemList;
    }

    /**
     * Converts video items to JSON.
     * @param catalogue Current state of catalogue
     * @return ArrayNode Object with "video" as key and array of audio items as value.
     */
    private ArrayNode videoToJson(Catalogue catalogue) {
        ArrayNode videoArray = mapper.createArrayNode();
        List<Video> videoObjects = catalogue.getAllItems().stream().filter(p -> p instanceof Video)
                .map(p -> (Video) p).collect(Collectors.toList());
        for (Video oneItem : videoObjects) {
            videoArray.add(mapper.convertValue(oneItem, ObjectNode.class));
        }
        return videoArray;
    }

    /**
     * Converts JSON values with key of "video" into Video objects.
     * @param arrayNode Deserialized arrayNode containing list of video objects in JSON
     * @return ArrayList containing all video items specified within JSON file
     * @throws JsonProcessingException Thrown when invalid characters found within JSON
     */
    private ArrayList<Video> jsonToVideo(ArrayNode arrayNode) throws JsonProcessingException {
        ArrayList<Video> itemList = new ArrayList<>();
        for (JsonNode item : arrayNode) {
            itemList.add(mapper.treeToValue(item, Video.class));
        }
        return itemList;
    }
}
