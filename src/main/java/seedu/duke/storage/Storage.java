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
import seedu.duke.storage.exceptions.StorageException;
import seedu.duke.ui.Ui;

// Code reuse from https://github.com/richwill28/ip/blob/master/src/main/java/duke/storage/Storage.java
public class Storage {
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final String STORAGE_DIRECTORY = "data";
    private static final String TASK_FILE_NAME = "tasks.txt";
    private static final String LESSON_FILE_NAME = "lessons.txt";
    private static final String MODULE_FILE_NAME = "modules.txt";
    private static final Path PATH_TO_TASK_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, TASK_FILE_NAME);
    private static final Path PATH_TO_LESSON_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, LESSON_FILE_NAME);
    private static final Path PATH_TO_MODULE_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, MODULE_FILE_NAME);

    /**
     * Creates a new data file.
     *
     * @param ui user interface.
     */
    public void createNewData(Ui ui) {
        try {
            File taskFile = new File(PATH_TO_TASK_FILE.toString());
            File lessonFile = new File(PATH_TO_LESSON_FILE.toString());
            File moduleFile = new File(PATH_TO_MODULE_FILE.toString());
            boolean isDirectoryCreated = taskFile.getParentFile().mkdirs();
            boolean isTaskFileCreated = taskFile.createNewFile();
            boolean isLessonFileCreated = lessonFile.createNewFile();
            boolean isModuleFileCreated = moduleFile.createNewFile();

            if (!isDirectoryCreated || !isTaskFileCreated || !isLessonFileCreated || !isModuleFileCreated) {
                throw new IOException(Messages.ERROR_CREATING_NEW_FILE);
            }
            assert taskFile.isFile() : "task file should have been created at this point";
            assert lessonFile.isFile() : "lesson file should have been created at this point";
            assert moduleFile.isFile() : "module file should have been created at this point";
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_CREATING_NEW_FILE);
        }
    }

    /**
     * Loads task data from a saved file.
     *
     * @param fileName the name of the file to be loaded from
     * @return data stored in a list of strings
     * @throws IOException if an I/O error occurs
     * @throws StorageException if the given file name is invalid
     */
    public List<String> loadData(String fileName) throws IOException, StorageException {
        try {
            Path loadPath = getPath(fileName);
            FileReader fin = new FileReader(loadPath.toString());
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
        } catch (StorageException e) {
            throw new StorageException(Messages.ERROR_RETRIEVING_DATA);
        }
    }

    /**
     * Saves the data of the task list and lesson list into the data storage file.
     *
     * @param taskList the task list
     * @param lessonList the lesson list
     * @param fileName the name of the file to be stored
     * @throws IOException if an I/O error occurs
     * @throws StorageException if the given file name is invalid
     */
    public void saveData(TaskList taskList, LessonList lessonList, String fileName)
            throws IOException, StorageException {
        try {
            Path savePath = getPath(fileName);
            FileWriter fout = new FileWriter(savePath.toString());
            BufferedWriter bout = new BufferedWriter(fout);
            switch (fileName) {
            case TASK_FILE_NAME:
                bout.write(taskList.serialize());
                break;
            case LESSON_FILE_NAME:
                bout.write(lessonList.serialize());
                break;
            case MODULE_FILE_NAME:
                //TODO: add moduleList saving
                break;
            default:
                throw new StorageException("Invalid file name"); //should be caught when calling getPath
            }
            bout.close();
        } catch (IOException e) {
            throw new IOException(Messages.ERROR_SAVING_DATA);
        } catch (StorageException e) {
            throw new StorageException(Messages.ERROR_SAVING_DATA);
        }
    }

    /**
     * Returns the path corresponding to the file name given.
     *
     * @param fileName the file name
     * @return the path for the file
     * @throws StorageException the given file name is invalid
     */
    public Path getPath(String fileName) throws StorageException {
        switch (fileName) {
        case TASK_FILE_NAME:
            return PATH_TO_TASK_FILE;
        case LESSON_FILE_NAME:
            return PATH_TO_LESSON_FILE;
        case MODULE_FILE_NAME:
            return PATH_TO_MODULE_FILE;
        default:
            throw new StorageException("Invalid file name");
        }
    }
}
