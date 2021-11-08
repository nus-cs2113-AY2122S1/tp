package seedu.duke.storage;

import seedu.duke.utility.MintException;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@@author Yitching
public class DataManagerActions {
    public static final String TEXT_DELIMITER = "|";
    public static final String NORMAL_FILE_PATH = "data" + File.separator + "Mint.txt";
    public static final String RECURRING_FILE_PATH = "data" + File.separator + "MintRecurring.txt";
    public static final String BUDGET_FILE_PATH = "data" + File.separator + "MintBudget.txt";

    /**
     * Creates the necessary data directory. This function also checks for erroneous "data" file types and deletes
     * any if found. It would then create the necessary directory.
     */
    public void createDirectory() {
        Path path = Paths.get("data" + File.separator + "Mint.txt");
        File myObj = new File(String.valueOf(path.getParent()));
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            if (!myObj.isDirectory()) {
                Ui.printRetryFileCreationMessage();
                myObj.delete();
                createDirectory();
                try {
                    throw new MintException("Essential files created!");
                } catch (MintException mintException) {
                    System.out.println(mintException.getMessage());
                }
            }
        }
    }

    /**
     * This function stores all the necessary file names and calls the function to create the necessary files. It
     * catches any exceptions thrown.
     */
    public void createFiles() {
        try {
            String[] filesToCreate = {RECURRING_FILE_PATH, NORMAL_FILE_PATH, BUDGET_FILE_PATH};
            createNewFiles(filesToCreate);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (MintException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This is the main function that creates all the necessary files. It also checks for any erroneous files of the
     * same name but incorrect type and deletes them when found. After deletion, it creates the necessary files.
     *
     * @param filesToCreate arrayList containing all the paths of the files that need to be created.
     */
    private void createNewFiles(String[] filesToCreate) throws IOException, MintException {
        boolean isPrinted = false;
        for (String file: filesToCreate) {
            assert (file != null);
            File myObj = new File(file);
            if (myObj.createNewFile()) {
                if (!isPrinted) {
                    Ui.printFirstTimeUserMessage();
                    isPrinted = true;
                }
            } else {
                if (myObj.isDirectory()) {
                    Ui.printRetryFileCreationMessage();
                    myObj.delete();
                    createNewFiles(filesToCreate);
                    throw new MintException("Essential files created!");
                }
            }
        }
    }
}
//@@author Yitching