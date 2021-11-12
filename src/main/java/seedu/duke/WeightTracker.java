package seedu.duke;

import seedu.duke.exceptions.weight.WeightException;
import seedu.duke.exceptions.weight.AddWeightException;
import seedu.duke.exceptions.weight.DeleteWeightException;
import seedu.duke.exceptions.weight.FutureWeightException;
import seedu.duke.exceptions.weight.DeleteWeightIndexException;
import seedu.duke.exceptions.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author teoziyiivy

/**
 * This <code>WeightTracker</code> class contains all the functions related to weight.
 * Namely, generating parameters, adding, deleting and listing.
 */
public class WeightTracker extends Tracker {
    protected ArrayList<String> weightsArray;
    protected int numberOfWeights;
    protected double weight;
    protected String date;
    private static Logger logger = Logger.getLogger("WeightTrackerLogger");

    /**
     * Constructs a <code>WeightTracker</code> object.
     */
    public WeightTracker() {
        this.weightsArray = new ArrayList<>();
        this.numberOfWeights = 0;
        logger.setLevel(Level.SEVERE);
    }

    /**
     * Generates the parameters for <code>weight</code> and <code>date</code>
     * to construct a <code>WeightTracker</code> object.
     *
     * @param input User input.
     * @throws WeightException If DukeException is caught.
     */
    public void generateWeightParameters(String input) throws WeightException {
        try {
            logger.entering(getClass().getName(), "generateWeightParameters");
            logger.log(Level.INFO, "going to generate weight and date parameters from user input");
            weight = Parser.getWeight(input);
            date = Parser.getDate(input);
        } catch (NumberFormatException e) {
            System.out.println(ClickfitMessages.WEIGHT_PARAMETERS_ERROR);
        } catch (DukeException e) {
            throw new WeightException();
        }
        logger.exiting(getClass().getName(), "generateWeightParameters");
        logger.log(Level.INFO, "end of generating weight parameters");
    }

    /**
     * Adds a weight to a list of weights.
     *
     * @param input User input.
     * @throws WeightException If the input == null.
     * @throws DateTimeParseException If the date is not input correctly or valid.
     */
    public void addWeight(String input) throws WeightException, DateTimeParseException {
        logger.entering(getClass().getName(), "addWeight");
        logger.log(Level.INFO, "going to add a weight and date to the list");
        numberOfWeights = weightsArray.size();
        generateWeightParameters(input);
        String inputDate = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(inputDate, formatter);
        LocalDate now = LocalDate.now();
        if (localDate.isAfter(now)) {
            throw new FutureWeightException();
        }
        logger.log(Level.INFO, "weight parameters generated");
        if (input == null) {
            throw new AddWeightException();
        } else {
            ClickfitMessages.printAddWeightResponse(weight, date);
            input = weight + " /d " + date;
            weightsArray.add(input);
            numberOfWeights += 1;
            assert numberOfWeights > 0 : "number of logged weights should be more than zero";
        }
        logger.exiting(getClass().getName(), "addWeight");
        logger.log(Level.INFO, "end of processing addweight command");
    }

    /**
     * Deletes a weight from a list of weights using the index of the weight to be deleted
     * from the list as given by the user.
     *
     * @param input User input.
     * @throws WeightException If the (weightIndex < 0) or (weightIndex > numberOfWeights - 1).
     */
    public void deleteWeight(String input) throws WeightException {
        logger.entering(getClass().getName(), "deleteWeight");
        logger.log(Level.INFO, "going to remove a weight and date from the list");
        numberOfWeights = weightsArray.size();
        if (input.isEmpty() || input.matches("delete weight")) {
            throw new DeleteWeightException();
        }
        int weightIndex = Parser.parseStringToInteger(input) - 1;
        if ((weightIndex < 0) || (weightIndex > numberOfWeights - 1)) {
            throw new DeleteWeightIndexException();
        } else {
            generateWeightParameters(weightsArray.get(weightIndex));
            ClickfitMessages.printDeleteWeightResponse(weight, date);
            weightsArray.remove(weightIndex);
            numberOfWeights--;
            assert numberOfWeights >= 0 : "number of logged weights should be more than or equal to zero";
        }
        logger.exiting(getClass().getName(), "deleteWeight");
        logger.log(Level.INFO, "end of processing deleteweight command");
    }

    /**
     * Lists weights from the list of weights depending on user input.
     * Namely, if user inputs <code>all</code>, <code>listAllWeights()</code> will be called and all
     * weights in the <code>weightsArray</code> will be printed. If the user inputs a date (DD/MM/YYYY),
     * then <code>listSpecificWeights()</code> will be called instead and only the weights recorded
     * on that specific date will be printed.
     *
     * @param input User input.
     * @throws WeightException If the weight in weightsArray is not recorded properly.
     */
    public void listWeights(String input) throws WeightException {
        numberOfWeights = weightsArray.size();
        if (numberOfWeights == 0) {
            System.out.println(ClickfitMessages.WEIGHT_EMPTY_ERROR);
        } else if (input.equals("all")) {
            listAllWeights();
        } else {
            listSpecificWeights(input);
        }
    }

    /**
     * Lists weights from the list of weights for the specific date as specified by the user.
     * Namely, if the user inputs a date (DD/MM/YYYY), only the weights recorded on that
     * specific date will be printed.
     *
     * @param date Date input by the user.
     * @throws WeightException If the weight in weightsArray is not recorded properly.
     */
    public void listSpecificWeights(String date) throws WeightException {
        logger.entering(getClass().getName(), "listSpecificWeights");
        logger.log(Level.INFO, "going to list specific logged weights and dates");
        int index = 1;
        int numberOfSpecificWeights = 0;
        for (String weights : weightsArray) {
            if (weights.contains(date)) {
                logger.log(Level.INFO, "generating weight parameters");
                generateWeightParameters(weights);
                logger.log(Level.INFO, "weight parameters generated");
                System.out.print(index + ". ");
                System.out.println("Weight: " + weight + " kg");
                index++;
                numberOfSpecificWeights++;
            }
        }
        System.out.println("Total number of weights: " + numberOfSpecificWeights);
        logger.exiting(getClass().getName(), "listSpecificWeights");
        logger.log(Level.INFO, "end of processing listSpecificWeights command");
    }

    /**
     * Lists every weight in the list of weights.
     *
     * @throws WeightException If the weight in weightsArray is not recorded properly.
     */
    public void listAllWeights() throws WeightException {
        logger.entering(getClass().getName(), "listAllWeights");
        logger.log(Level.INFO, "going to list all logged weights and dates");
        int index = 1;
        System.out.println("Here are your recorded weights:");
        for (String weights : weightsArray) {
            generateWeightParameters(weights);
            System.out.print(index + ". ");
            System.out.print(" Weight: " + weight + " kg ");
            System.out.println(" Date: " + date + System.lineSeparator());
            index++;
        }
        System.out.println("Total number of weights: " + (index - 1));
        logger.exiting(getClass().getName(), "listAllWeights");
        logger.log(Level.INFO, "end of processing listAllWeights command");
    }
}
