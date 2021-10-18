package seedu.duke.storage;

import seedu.duke.items.Event;
import seedu.duke.items.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageFile {

    private static final String DEFAULT_FILE_PATH = "data/slamData.txt";
    private static final String DEFAULT_DIRECTORY = "data";

    private static final String EVENT_TASK_SEPARATOR = "Lzh5L9Cyy2qsmTWKy4gu2NwD57gjdEwmIZ";

    public void save(ArrayList<Event> eventsList) {

        File saveFile = new File(DEFAULT_FILE_PATH);
        checkFileIsValid(saveFile);
        List<String> encodedEventsList = EventEncoder.encodeEventsList(eventsList);
        try {
            writeToFile(saveFile, encodedEventsList);
        } catch (IOException e) {
            System.out.println("Error writing to file at path: " + DEFAULT_FILE_PATH);
        }
    }

    private static void checkFileIsValid(File saveFile) {

        try {
            if (!saveFile.exists()) {
                Files.createDirectories(Path.of(DEFAULT_DIRECTORY));
                Files.createFile(Path.of(DEFAULT_FILE_PATH));
            }
        } catch (IOException e) {
            System.out.println("Error with default directory/file_path.");
        }
    }

    private void writeToFile(File saveFile, List<String> encodedEventsList)
            throws IOException {

        FileWriter eventsWriter = new FileWriter(saveFile);
        for (String item : encodedEventsList) {
            eventsWriter.write(item + "\n");
        }
        eventsWriter.close();
    }

    public void load(ArrayList<Event> eventsList, ArrayList<Task> tasksList) throws FileNotFoundException {
        File saveFile = new File(DEFAULT_FILE_PATH);
        List<String> encodedEvents = new ArrayList<>();
        List<String> encodedTasks = new ArrayList<>();

        separateEventAndTaskData(saveFile, encodedEvents, encodedTasks);
        eventsList.addAll(EventDecoder.decodeEventsList(encodedEvents));
        tasksList.addAll(TaskDecoder.decodeTasksList(encodedTasks));
    }

    private void separateEventAndTaskData(File saveFile, List<String> encodedEvents, List<String> encodedTasks)
            throws FileNotFoundException {
        Scanner myScanner = new Scanner(saveFile);

        boolean hasFinishedScanningEvents = false;

        while (myScanner.hasNextLine()) {
            String data = myScanner.nextLine();
            if (data.equals(EVENT_TASK_SEPARATOR)) {
                hasFinishedScanningEvents = true;
                continue;
            }
            if (!hasFinishedScanningEvents) {
                encodedEvents.add(data);
            } else {
                encodedTasks.add(data);
            }
        }
    }
}
