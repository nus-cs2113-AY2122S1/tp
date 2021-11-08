package seedu.duke.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

//@@author jonathanmui4 - reused

/**
 * Handles functions required to convert Java Objects to JSON objects and vice versa using the Jackson Library.
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    /**
     * Creates a Default object mapper class which converts data in Java classes into JSON format and vice versa.
     *
     * @return defaultObjectMapper used in the app's methods
     */
    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    /**
     * Converts a json string into a json object.
     *
     * @param src Json String
     * @return JsonNode
     * @throws IOException Exception thrown when object mapper fails to convert given string into a json object
     */
    public static JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }

    /**
     * Converts JSON node into a class.
     *
     * @param node           JSON node
     * @param convertedClass Class to convert into
     * @param <A>            Generic return type
     * @return Object of convertedClass
     * @throws JsonProcessingException Exception thrown when JSON cannot be processed
     */
    public static <A> A fromJson(JsonNode node, Class<A> convertedClass) throws JsonProcessingException {
        return objectMapper.treeToValue(node, convertedClass);
    }

    /**
     * Converts Java Object (Either instance of class or String) into a JSON node (JSON object).
     *
     * @param obj Json String to be converted
     * @return JSON Node
     */
    public static JsonNode toJson(Object obj) {
        return objectMapper.valueToTree(obj);
    }

    /**
     * Turns entire JSON object into string (including {} ).
     *
     * @param node     JSON object
     * @param isPretty Toggle to control if you want to format the string output
     * @return Json object in the form of a String
     * @throws JsonProcessingException Exception thrown when JSON object cannot be turned into a string
     */
    public static String stringify(JsonNode node, boolean isPretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if (isPretty) {
            return objectWriter.with(SerializationFeature.INDENT_OUTPUT).writeValueAsString(node);
        }
        return objectWriter.writeValueAsString(node);
    }
}
