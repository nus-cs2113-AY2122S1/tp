package seedu.duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    public final static String MESSAGE_INVALID_FILE = "File cannot be found.";
    public final static String MESSAGE_INVALID_WRITE = "File cannot be written";
    public final String path;

    /** Constructor of the Storage class.
     *
     * @param filePath the file contains tasks.
     */
    public Storage (String filePath) {
        this.path = filePath;
    }

    /**
     * Load the tasks from the file.
     *
     * @return the tasksList of tasks in file.
     * @throws DukeException if the file is not found.
     */
    public TaskList load() throws DukeException {
        final TaskList taskLists = new TaskList();
        File file = new File(path);
        if (!file.exists()) {
            return new TaskList();
        }
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = decodeTask(line);
                taskLists.add(task);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(MESSAGE_INVALID_FILE);
        }
        return taskLists;
    }

    /**
     * Write all tasks into the file.
     *
     * @param tasksList the tasks need to be written.
     * @throws DukeException if the write operation goes wrong.
     */
    public void write(TaskList tasksList) throws DukeException{
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tasksList.size(); i += 1) {
                Task task = tasksList.get(i);
                fileWriter.write(String.format("%s\n", encodeTask(task.toString())));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(MESSAGE_INVALID_WRITE);
        }
    }

    /**
     * Encode the task to write into file.
     *
     * @param taskToWrite the task need to be written.
     * @return the encoded task.
     */
    public String encodeTask (String taskToWrite) {
        String task = null;
        if(taskToWrite.startsWith("[T]")) {
            task = "todo" + taskToWrite.substring(4,5) + taskToWrite.substring(7);
            return task;
        } else if (taskToWrite.startsWith("[E]")) {
            task = "event" + taskToWrite.substring(4,5) + taskToWrite.substring(7, taskToWrite.indexOf("(")) + " /at "
                    + taskToWrite.substring(taskToWrite.indexOf(":") + 1, taskToWrite.indexOf(")"));
        } else if (taskToWrite.startsWith("[D]")) {
            task = "deadline" + taskToWrite.substring(4,5) + taskToWrite.substring(7, taskToWrite.indexOf("(")) + " /by "
                    + taskToWrite.substring(taskToWrite.indexOf(":") + 1, taskToWrite.indexOf(")"));
        }
        return task;
    }

    /**
     * Decode the task stored in the file.
     *
     * @param taskToRead the task need to be read
     * @return the decoded task.
     */
    public Task decodeTask (String taskToRead) {
        if(taskToRead.startsWith("todo")) {
            Todo task = new Todo(taskToRead);
            if(taskToRead.substring(4,5).equals("X")) {
                task.setDone();
            }
            return task;
        } else if(taskToRead.startsWith("event")) {
            Event task = new Event(taskToRead);
            if(taskToRead.substring(5,6).equals("X")) {
                task.setDone();
            }
            return task;
        } else if (taskToRead.startsWith("deadline")) {
            Deadline task = new Deadline(taskToRead);
            if(taskToRead.substring(8,9).equals("X")) {
                task.setDone();
            }
            return task;
        } else {
            Task task = null;
            return task;
        }
    }

    public static class DukeException extends Exception {

        /**
         * Constructor for DukeException class.
         *
         * @param message The exception message.
         */
        public DukeException(String message) {
            super(message);
        }

    }

}