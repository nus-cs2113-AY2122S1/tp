package happybit.storage;

import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILEPATH = "data/habits.txt";

    protected String filePath;
    protected String fileDir;

    public Storage() {
        this(DEFAULT_FILEPATH, DEFAULT_DIR);
    }

    public Storage(String filePath, String fileDir) {
        this.filePath = filePath;
        this.fileDir = fileDir;
    }

    public GoalList load() throws HaBitStorageException {
        createFile(this.filePath, this.fileDir);

        return Import.importStorage(this.filePath);
    }

    protected void createFile(String filePath, String fileDir) {
        File storageDir = new File(fileDir);
        File storageFile = new File(filePath);

        if (!storageDir.exists()) {
            boolean isDirCreated = storageDir.mkdirs();

            if (isDirCreated) {
                System.out.println("Directory created: " + fileDir);
                assert storageDir.exists() : "directory should have been created";
            } else {
                System.out.println("Directory not created");
            }
        }

        try {
            boolean isFileCreated = storageFile.createNewFile();

            if (isFileCreated) {
                System.out.println("File created: " + filePath);
                assert storageFile.exists() : "file should have been created";
            } else {
                System.out.println("File exists");
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating file: " + e);
        }
    }

    public void export(ArrayList<Goal> goalList) throws HaBitStorageException {
        Export.exportStorage(goalList, this.filePath);
    }
}
