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
import seedu.duke.model.module.ModuleList;
import seedu.duke.model.task.TaskList;
import seedu.duke.commons.core.Message;
import seedu.duke.ui.Ui;

//@@author richwill28
public class Storage {
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final String STORAGE_DIRECTORY = "data";

    private static final String TASK_FILE = "task.txt";
    private static final String LESSON_FILE = "lesson.txt";
    private static final String MODULE_FILE = "module.txt";

    public static final Path PATH_TO_TASK_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, TASK_FILE);
    public static final Path PATH_TO_LESSON_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, LESSON_FILE);
    public static final Path PATH_TO_MODULE_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, MODULE_FILE);

    public void createNewDataFile(Ui ui, String model) {
        try {
            File newFile = null;
            if (model.equals("TASK")) {
                newFile = new File(PATH_TO_TASK_FILE.toString());
            } else if (model.equals("LESSON")) {
                newFile = new File(PATH_TO_LESSON_FILE.toString());
            } else if (model.equals("MODULE")) {
                newFile = new File(PATH_TO_MODULE_FILE.toString());
            }
            assert newFile != null : Ui.PADDING + "The new file should not be null.";
            boolean isDirectoryCreated = newFile.getParentFile().mkdirs();
            boolean isNewFileCreated = newFile.createNewFile();
        } catch (IOException e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Loads task data from a saved file.
     *
     * @param pathToFile the name of the file to be loaded from
     * @return data stored in a list of strings
     * @throws IOException if an I/O error occurs
     */
    public List<String> loadData(Path pathToFile) throws IOException {
        try {
            FileReader fin = new FileReader(pathToFile.toString());
            BufferedReader bin = new BufferedReader(fin);
            List<String> data = new ArrayList<>();
            String line;
            while ((line = bin.readLine()) != null) {
                data.add(line);
            }
            bin.close();
            return data;
        } catch (IOException e) {
            if (PATH_TO_TASK_FILE.equals(pathToFile)) {
                throw new IOException(Message.ERROR_RETRIEVING_TASK_DATA);
            } else if (PATH_TO_LESSON_FILE.equals(pathToFile)) {
                throw new IOException(Message.ERROR_RETRIEVING_LESSON_DATA);
            } else if (PATH_TO_MODULE_FILE.equals(pathToFile)) {
                throw new IOException(Message.ERROR_RETRIEVING_MODULE_DATA);
            }
            throw new IOException(Message.ERROR_RETRIEVING_DATA);
        }
    }

    /**
     * Saves the data of the task list.
     *
     * @param taskList task list
     * @throws IOException if an I/O error occurs
     */
    public void saveData(TaskList taskList) throws IOException {
        try {
            FileWriter fout = new FileWriter(PATH_TO_TASK_FILE.toString());
            BufferedWriter bout = new BufferedWriter(fout);
            bout.write(taskList.serialize());
            bout.close();
        } catch (IOException e) {
            throw new IOException(Message.ERROR_SAVING_DATA);
        }
    }

    /**
     * Saves the data of the lesson list.
     *
     * @param lessonList lesson list
     * @throws IOException if an I/O error occurs
     */
    public void saveData(LessonList lessonList) throws IOException {
        try {
            FileWriter fout = new FileWriter(PATH_TO_LESSON_FILE.toString());
            BufferedWriter bout = new BufferedWriter(fout);
            bout.write(lessonList.serialize());
            bout.close();
        } catch (IOException e) {
            throw new IOException(Message.ERROR_SAVING_DATA);
        }
    }

    /**
     * Saves the data of the module list.
     *
     * @param moduleList module list
     * @throws IOException if an I/O error occurs
     */
    public void saveData(ModuleList moduleList) throws IOException {
        try {
            FileWriter fout = new FileWriter(PATH_TO_MODULE_FILE.toString());
            BufferedWriter bout = new BufferedWriter(fout);
            bout.write(moduleList.serialize());
            bout.close();
        } catch (IOException e) {
            throw new IOException(Message.ERROR_SAVING_DATA);
        }
    }
}
