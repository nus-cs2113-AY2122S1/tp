package seedu.duke;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileTest {

    @BeforeAll
    static void setUp() throws IOException {
        ArrayList<Trip> listOfTrips = new ArrayList<>();
        listOfTrips.add(new Trip());
        listOfTrips.add(new Trip());
        Storage.setListOfTrips(listOfTrips);
        FileStorage.initializeGson();
        Storage.setLogger(Logger.getLogger("logger"));
        Storage.createNewFile();
        Storage.writeToFile();
    }

    @Test
    void testRegularFile() throws FileNotFoundException {
        File file = new File("trips.json");
        assertTrue(file.canRead());
        String jsonString = FileStorage.readFromFile("trips.json");
        assertFalse(jsonString.isEmpty());
        Type tripType = new TypeToken<ArrayList<Trip>>(){}.getType();
        FileStorage.initializeGson();
        assertNotNull(FileStorage.getGson());
        ArrayList<Trip> listOfTrips = FileStorage.getGson().fromJson(jsonString, tripType);
        assertNotNull(listOfTrips);
        assertFalse(listOfTrips.isEmpty());
    }

    @Test
    void testNoFile() {
        assertThrows(FileNotFoundException.class, () ->
                FileStorage.readFromFile("randomfile.json"));
    }

    @Test
    void testEmptyFile() {
        try {
            FileStorage.newBlankFile("tripsempty.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThrows(NoSuchElementException.class, () ->
                FileStorage.readFromFile("tripsempty.json"));
    }

    //@Test
    //void testCorruptedFile() {
        //assertThrows(JsonParseException.class, () ->
                //FileStorage.readFromFile("tripscorrupted.json"));
    //}
}
