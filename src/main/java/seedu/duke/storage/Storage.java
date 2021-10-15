package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.task.TaskList;
import seedu.duke.commons.core.Messages;
import seedu.duke.ui.Ui;

// Code reuse from https://github.com/richwill28/ip/blob/master/src/main/java/duke/storage/Storage.java
public class Storage {
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final String STORAGE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final Path PATH_TO_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, FILE_NAME);

    /**
     * Creates a new data file.
     *
     * @param ui user interface.
     */
    public void createNewData(Ui ui) {
        try {
            File file = new File(PATH_TO_FILE.toString());
            boolean isDirectoryCreated = file.getParentFile().mkdirs();
            boolean isFileCreated = file.createNewFile();

            if (!isDirectoryCreated || !isFileCreated) {
                throw new IOException(Messages.ERROR_CREATING_NEW_FILE);
            }
            assert file.isFile() : "file should have been created at this point";
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_CREATING_NEW_FILE);
        }
    }

    /**
     * Loads task data from a saved file.
     *
     * @return data stored in a list of strings
     * @throws IOException if an I/O error occurs
     */
    public List<String> loadData() throws IOException {
        try {
            FileReader fin = new FileReader(PATH_TO_FILE.toString());
            BufferedReader bin = new BufferedReader(fin);
            List<String> data = new ArrayList<>();
            String line;
            while ((line = bin.readLine()) != null) {
                data.add(line);
            }
            bin.close();
            return data;
        } catch (IOException e) {
            throw new IOException(Messages.ERROR_RETRIEVING_DATA);
        }
    }

    /**
     * Saves the data of the task list and lesson list into the data storage file.
     *
     * @param taskList the task list
     * @param lessonList the lesson list
     * @throws IOException if an I/O error occurs
     */
    public void saveData(TaskList taskList, LessonList lessonList) throws IOException {
        try {
            FileWriter fout = new FileWriter(PATH_TO_FILE.toString());
            BufferedWriter bout = new BufferedWriter(fout);
            bout.write(taskList.serialize() + lessonList.serialize());
            bout.close();
        } catch (IOException e) {
            throw new IOException(Messages.ERROR_SAVING_DATA);
        }
    }
}
