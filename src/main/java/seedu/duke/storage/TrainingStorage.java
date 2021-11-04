/*@@author xingyuan123*/
package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import seedu.duke.Ui;
import seedu.duke.command.exception.InvalidAddMemberException;
import seedu.duke.command.exception.InvalidAddTrainingException;
import seedu.duke.training.TrainingList;
import seedu.duke.training.TrainingSchedule;

public class TrainingStorage {

    static String duplicateTrainingErrorExcelMessage = "Duplicates training name found in CCA Trainings CSV.Please fix this before running the program again.";

    /**
     * This method sets up the cca trainings csv file. It firsts tries to find if the file exists in the current
     * directory. If the file exists, it will call the loadTrainingFile method. If not it will create a new training
     * csv file in the current directory.
     *
     * @param trainings the list of current trainings
     */
    public static void setupTrainingFile(TrainingList trainings) {
        File ccaTrainingFile = new File("CCATrainings.csv");
        if (!ccaTrainingFile.exists()) {
            try {
                ccaTrainingFile.createNewFile();
                initializeTrainingFile(ccaTrainingFile);
                System.out.println("Training file not detected. Creating.");
                assert ccaTrainingFile != null : "duke member file should be created";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            System.out.println("CCA Training file found & loaded");
            verifyTrainingDetails(ccaTrainingFile);
            loadTrainingsFile(ccaTrainingFile, trainings);

        }
    }

    /**
     * Verifies the training schedule file details are valid.
     *
     * @param ccaTrainingFile the list of current trainings
     */
    private static void verifyTrainingDetails(File ccaTrainingFile) {
        try {
            verifyTrainingDuplicates(ccaTrainingFile);
        } catch (InvalidAddTrainingException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Verifies that there are o duplicate names in training schedule.
     *
     * @param ccaTrainingFile the list of current trainings
     */
    private static void verifyTrainingDuplicates(File ccaTrainingFile) throws InvalidAddTrainingException {
        String trainingName;
        List<String> pendingTrainingName = new ArrayList<String>();
        try {
            Scanner memberScanner = new Scanner(ccaTrainingFile);
            memberScanner.nextLine();
            while (memberScanner.hasNextLine()) {
                String fullMemberDetails = memberScanner.nextLine();
                String[] memberDetails = fullMemberDetails.split("\\,", 4);
                trainingName = memberDetails[1];
                pendingTrainingName.add(trainingName);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyTrainingFile();
        }
        checkDuplicates(pendingTrainingName);

    }

    /**
     * Checks for any duplicates in the list.
     *
     * @param pendingList current list of Strings to check.
     * @throws InvalidAddMemberException if there are any duplicates.
     */
    private static void checkDuplicates(List<String> pendingList) throws InvalidAddTrainingException {
        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i + 1; j < pendingList.size(); j++) {
                if (pendingList.get(i).equals(pendingList.get(j))) {
                    throw new InvalidAddTrainingException(duplicateTrainingErrorExcelMessage);
                }
            }
        }
    }

    /**
     * This method loads the duke member file and writes to the current member list. Only happens on start-up.
     *
     * @param trainingFile CSV file to read data from.
     * @param trainings    TrainingList to write to.
     */
    public static void loadTrainingsFile(File trainingFile, TrainingList trainings) {
        String name;
        String venue;
        String time;
        try {
            Scanner trainingScanner = new Scanner(trainingFile);
            trainingScanner.nextLine(); //skips the first header row
            int index = 1;
            while (trainingScanner.hasNextLine()) {
                String fullTrainingDetails = trainingScanner.nextLine();
                assert fullTrainingDetails != null : "fullTrainingDetails should not be empty";
                //System.out.println(fullTrainingDetails);
                String[] trainingDetails = fullTrainingDetails.split("\\,", 3);

                name = trainingDetails[0]; //used this to prevent magic numbers
                venue = trainingDetails[1];
                time = trainingDetails[2];
                TrainingSchedule training = new TrainingSchedule(name, venue, time);
                training.setTrainingIndex(index);
                trainings.addTrainingSchedule(training);
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }

    /**
     * This method initializes the headers of training file.
     *
     * @param trainingFile the training file
     */
    public static void initializeTrainingFile(File trainingFile) {
        try (PrintWriter dukeMemberWriter = new PrintWriter(trainingFile)) {
            dukeMemberWriter.write("name");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("venue");
            dukeMemberWriter.write(',');
            dukeMemberWriter.write("time");
            dukeMemberWriter.write('\n');
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }

    /**
     * This method rewrites the entire duke member file.
     *
     * @param trainingFile the training file
     * @param trainingList the current training list
     */
    public static void writeTrainingFile(File trainingFile, TrainingList trainingList) {
        int trainingListSize = trainingList.getTrainingListSize();
        try (PrintWriter dukeTrainingWriter = new PrintWriter(trainingFile)) {
            dukeTrainingWriter.write("name");
            dukeTrainingWriter.write(',');
            dukeTrainingWriter.write("venue");
            dukeTrainingWriter.write(',');
            dukeTrainingWriter.write("time");
            dukeTrainingWriter.write('\n');
            for (int i = 1; i <= trainingListSize; i++) {
                dukeTrainingWriter.write(trainingList.getTrainingName(i));
                dukeTrainingWriter.write(',');
                dukeTrainingWriter.write(trainingList.getTrainingVenue(i));
                dukeTrainingWriter.write(',');
                dukeTrainingWriter.write(trainingList.getTrainingTime(i));
                dukeTrainingWriter.write('\n');
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }

}
