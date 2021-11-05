package seedu.duke.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import seedu.duke.log.Log;
import seedu.duke.task.Task;
import seedu.duke.task.taskmanager.Observer;
import seedu.duke.task.type.Deadline;
import seedu.duke.task.type.Event;
import seedu.duke.task.type.Lesson;
import seedu.duke.task.type.Todo;

//@@author SeanRobertDH
/**
 * <code>DataManager</code> handles anything to do with loading and saving of Task data.
 */
public class DataManager implements Observer {

    private static final String LOAD_ERROR_MESSAGE = "Error occurred while loading task list."
        + "Data will be overwritten when you add a task.";
    private static final String READ_ACCESS_ERROR_MESSAGE = "No permission to read from save file."
        + " Ensure the program has read access to %s\nand restart the program to allow your tasks to be imported!";
    private static final String READ_IO_EXCEPTION = "IO Exception occurred when trying to read from save file."
        + " Ensure the file %s exists\nand you have enough disk space";
    private static final String WRITE_ACCESS_ERROR_MESSAGE = "No permission to write to save file."
        + " Ensure the program has write access to %s\nand restart the program to allow created tasks to be saved!";
    private static final String WRITE_IO_EXCEPTION = "IO Exception occurred when trying to read from save file."
        + " Ensure the file %s exists\nand you have enough disk space";

    private static final boolean DO_APPEND_TO_FILE = false;

    private final File taskFile;
    private final String taskFileName;
    private final Gson gson;

    private List<Task> taskList;

    private boolean hasPrintedFileNotFoundException = false;
    private boolean hasPrintedIoException = false;

    /**
     * Constructs a new <code>DataManager</code> with a <code>FileCreator</code>
     * that created the file is uses to load and save date from.
     * Also initialises the Gson that is used to parse data.
     *
     * @param fileCreator FileCreator that gets the Task file for loading and saving of data.
     */
    public DataManager(FileCreator fileCreator) {
        taskFile = fileCreator.getTaskFile();
        taskFileName = fileCreator.getTaskFileName();
        gson = buildGson();
    }

    private Gson buildGson() {
        return new GsonBuilder()
            .registerTypeAdapter(Task.class, new TaskTypeAdapter())
            .registerTypeAdapter(Todo.class, new TaskTypeAdapter())
            .registerTypeAdapter(Deadline.class, new TaskTypeAdapter())
            .registerTypeAdapter(Event.class, new TaskTypeAdapter())
            .registerTypeAdapter(Lesson.class, new TaskTypeAdapter())
            .setPrettyPrinting().create();
    }

    /**
     * Returns the loaded {@link #taskList} from {@link #taskFile}.
     * If there was no loaded {@link #taskList}, a new {@link #taskList} is returned.
     * @param startingSize the size the {@link #taskList} should be initialised to.
     */
    public List<Task> loadTaskList(int startingSize) {
        taskList = new ArrayList<>(startingSize);
        try {
            Reader taskFileReader = new FileReader(taskFile);
            List<Task> loadedTaskList = gson.fromJson(taskFileReader, new TypeToken<ArrayList<Task>>() {
            }.getType());
            taskFileReader.close();
            if (loadedTaskList != null) {
                taskList = loadedTaskList;
            }
        } catch (FileNotFoundException e) {
            Log.severe(String.format(READ_ACCESS_ERROR_MESSAGE, taskFileName));
        } catch (IOException e) {
            Log.severe(String.format(READ_IO_EXCEPTION, taskFileName));
        } catch (JsonSyntaxException e) {
            Log.severe(LOAD_ERROR_MESSAGE);
        } catch (NullPointerException e) {
            Log.severe(LOAD_ERROR_MESSAGE);
        }
        return taskList;
    }

    /**
     * Updates the {@link #taskFile} to reflect the newest {@link #taskList}.
     */
    @Override
    public void update() {
        String taskListJson = gson.toJson(taskList);
        writeToTaskFile(taskListJson);
    }

    private void writeToTaskFile(String taskListJson) {
        try {
            Writer taskFileWriter = new FileWriter(taskFile, DO_APPEND_TO_FILE);
            taskFileWriter.write(taskListJson);
            taskFileWriter.close();
        } catch (FileNotFoundException e) {
            logFileNotFoundException();
        } catch (IOException e) {
            logIoException();
        }
    }

    private void logFileNotFoundException() {
        if (!hasPrintedFileNotFoundException) {
            Log.severe(String.format(WRITE_ACCESS_ERROR_MESSAGE, taskFileName));
            hasPrintedFileNotFoundException = true;
        }
    }

    private void logIoException() {
        if (!hasPrintedIoException) {
            Log.severe(String.format(WRITE_IO_EXCEPTION, taskFileName));
            hasPrintedIoException = true;
        }
    }
}
