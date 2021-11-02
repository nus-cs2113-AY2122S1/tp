package seedu.duke.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import seedu.duke.data.*;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class JsonFactory {
    public static final String KEY_AUDIO = "audio";
    public static final String KEY_BOOK = "book";
    public static final String KEY_MAGAZINE = "magazine";
    public static final String KEY_VIDEO = "video";
    private static final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode allItems;

    public JsonFactory() {
        this.allItems = mapper.createObjectNode();
    }

    public String toJson(Catalogue catalogue) throws JsonProcessingException {
        allItems.set(KEY_AUDIO, audioToJson(catalogue));
        allItems.set(KEY_BOOK, bookToJson(catalogue));
        allItems.set(KEY_MAGAZINE, magazineToJson(catalogue));
        allItems.set(KEY_VIDEO, videoToJson(catalogue));
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allItems);
    }

    public void fromJson(File data) {

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
