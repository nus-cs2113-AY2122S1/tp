package seedu.duke;

import seedu.duke.exceptions.fluid.NoCaloriesEntered;
import seedu.duke.exceptions.fluid.NoVolumeEntered;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.fluid.DeleteEmptyFluidListException;
import seedu.duke.exceptions.fluid.FluidExceptions;
import seedu.duke.exceptions.fluid.InvalidFluidDescription;
import seedu.duke.exceptions.fluid.NoDeleteFluidIndexException;
import seedu.duke.exceptions.fluid.NoFluidToDelete;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author pragyan01
/**
 * Class responsible for handling fluid entries.
 *
 * @author pragyan01
 */
public class Fluid extends Tracker {

    protected ArrayList<String> fluidArray;
    protected int fluidNumber;
    protected String description;
    protected int calories;
    protected int volume;
    protected String date;
    protected String time;
    protected int totalCalories;
    protected int totalVolume;
    private static final Logger logr = Logger.getLogger("FluidLogger");

    /**
     * Constructor of fluid class.
     *
     */
    public Fluid() {
        this.fluidArray = new ArrayList<>();
        this.fluidNumber = 0;
        this.totalCalories = 0;
        logr.setLevel(Level.SEVERE);
    }

    /**
     * This method splits user input to extract parameters such as description, calories, volume, date & time.
     *
     * @param inputArguments user input provided
     * @throws FoodBankException if calories are not provided
     * @throws FluidExceptions if description not provided
     *
     * @author pragyan01
     */
    public void generateFluidParameters(String inputArguments) throws FoodBankException, FluidExceptions {
        logr.entering(getClass().getName(), "generateFluidParameters");
        logr.info("start of generating fluid parameters");
        description = Parser.getDescription(inputArguments);
        calories = Parser.getCalories(inputArguments);
        volume = Parser.getVolume(inputArguments);
        date = Parser.getDate(inputArguments);
        time = Parser.getTime(inputArguments);
        logr.info("end of process-generateFluidParameters");
        logr.exiting(getClass().getName(), "generateFluidParameters");
    }

    /**
     * This method adds a fluid entry to the fluid array.
     *
     * @param inputArguments user input provided
     * @throws FluidExceptions if description or volume is not provided
     * @throws FoodBankException if calories are not provided
     *
     * @author pragyan01
     */
    public void addFluid(String inputArguments) throws FluidExceptions, FoodBankException {
        logr.entering(getClass().getName(), "addFluid");
        logr.info("going to check for fluid parameter errors");
        if (inputArguments == null) {
            logr.info("error adding fluid: invalidDescription");
            throw new InvalidFluidDescription();
        }
        generateFluidParameters(inputArguments);
        if (description.equals("") || Parser.containsSeparators(description)) {
            logr.info("error adding fluid: invalidDescription");
            throw new InvalidFluidDescription();
        }
        if ((volume < 0) || !inputArguments.contains("/v")) {
            logr.info("error adding fluid: no volume provided");
            throw new NoVolumeEntered();
        }
        if ((calories < 0)) {
            logr.info("error adding fluid: no calories provided");
            throw new NoCaloriesEntered();
        }
        logr.info("no fluid parameter errors found!");
        inputArguments = description + " /c " + calories + " /v " + volume + " /d " + date + " /t " + time;
        fluidArray.add(inputArguments);
        assert fluidArray.size() != 0 : "Fluid array should not be empty";
        logr.info("fluid intake has been added");
        fluidNumber += 1;
        totalCalories += calories;
        totalVolume += volume;
        logr.info("fluidNumber, totalCalories & totalVolume have been updated");
        System.out.println("Noted! CLI.ckFit has recorded your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
        logr.info("end of process-addFluid");
        logr.exiting(getClass().getName(), "addFluid");
    }

    /**
     * This method deletes a fluid entry from the fluid array.
     *
     * @param inputArguments user input provided
     * @throws FluidExceptions if fluid entry index is not provided
     * @throws FoodBankException if the fluid entry does not exist
     *
     * @author pragyan01
     */
    public void deleteFluid(String inputArguments) throws FoodBankException, FluidExceptions {
        logr.entering(getClass().getName(), "deleteFluid");
        logr.info("going to check for index errors");
        if (inputArguments == null) {
            logr.info("error deleting fluid: invalid index provided");
            throw new NoDeleteFluidIndexException();
        }
        if (fluidArray.size() == 0) {
            logr.info("error deleting fluid: no fluid entries exist");
            throw new DeleteEmptyFluidListException();
        }
        int taskNumber = Parser.parseStringToInteger(inputArguments) - 1;
        logr.info("taskNumber extracted from user input");
        if ((taskNumber < 0) || (taskNumber > (fluidNumber - 1))) {
            logr.info("error deleting fluid: specified fluid entry does not exist");
            throw new NoFluidToDelete();
        }
        generateFluidParameters(fluidArray.get(taskNumber));
        assert fluidArray.size() != 0 : "Fluid array should not be empty";
        fluidArray.remove(taskNumber);
        logr.info("fluid entry has been removed");
        fluidNumber -= 1;
        totalCalories -= calories;
        totalVolume -= volume;
        logr.info("fluidNumber, totalCalories & totalVolume have been updated");
        System.out.println("Noted! CLI.ckFit has deleted your drink of " + description + " of " + calories
                + " calories and " + volume + " ml on " + date + " " + time + "." + "\n");
        logr.info("end of process-deleteFluid");
        logr.exiting(getClass().getName(), "deleteFluid");
    }

    /**
     * This method lists out all fluid entries stored.
     *
     *@param userDate date provided by user
     *@throws FluidExceptions if description for a fluid entry is not found
     *@throws FoodBankException if calories for a fluid entry is not found
     *
     * @author pragyan01
     */
    public void listFluids(String userDate) throws FoodBankException, FluidExceptions {
        logr.entering(getClass().getName(), "listFluid");
        if (fluidArray.size() == 0) {
            logr.info("error listing fluids: fluid list is empty");
            System.out.println(ClickfitMessages.EMPTY_FLUID_LIST);
        }
        logr.info("checking if specific date is provided by user or all entries are to be printed");
        if (userDate.equals("all")) {
            logr.info("all entries are to be printed");
            int i = 1;
            totalCalories = 0;
            fluidNumber = 0;
            logr.info("totalCalories & fluidNumber have been reset");
            for (String fluid : fluidArray) {
                generateFluidParameters(fluid);
                System.out.println(i + ". " + description);
                System.out.println("Calories: " + calories);
                System.out.println("Volume: " + volume);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time + "\n");
                i += 1;
                totalCalories += calories;
                fluidNumber += 1;
            }
            logr.info("all entries have been listed");
        } else {
            logr.info("fluid entries are to be listed for a specific date");
            int i = 1;
            totalCalories = 0;
            fluidNumber = 0;
            logr.info("totalCalories & fluidNumber have been reset");
            for (String fluid : fluidArray) {
                if (fluid.contains(userDate)) {
                    logr.log(Level.INFO, "generating fluid parameters");
                    generateFluidParameters(fluid);
                    logr.log(Level.INFO, "fluid parameters generated");
                    System.out.println(i + ". " + description);
                    System.out.println("Calories: " + calories);
                    System.out.println("Volume: " + volume);
                    System.out.println("Date: " + date);
                    System.out.println("Time: " + time + "\n");
                    i += 1;
                    totalCalories += calories;
                    fluidNumber += 1;
                }
            }
            logr.info("fluid entries have been listed for a specific date");
        }
        System.out.println("Total number of fluids: " + fluidNumber);
        System.out.println("Total calories: " + totalCalories);
        logr.info("fluidNumber and totalCalories have been updated and printed");
        logr.log(Level.INFO, "end of process-listFluid");
        logr.exiting(getClass().getName(), "listFluids");
    }

    /**
     * This method sums up the calorie total for a specific date.
     *
     *@param date date provided by user
     *@return total calorie for the specific date
     *@throws FluidExceptions if description for a fluid entry is not found
     *@throws FoodBankException if calories for a fluid entry is not found
     */
    public int getCalories(String date) throws FoodBankException, FluidExceptions {
        logr.entering(getClass().getName(), "getCalories");
        int calorieTotal = 0;
        logr.log(Level.INFO, "calorieTotal has been reset");
        for (String fluid : fluidArray) {
            if (fluid.contains(date)) {
                generateFluidParameters(fluid);
                calorieTotal += calories;
                assert ((calorieTotal > 0) || (calorieTotal == 0));
            }
        }
        logr.log(Level.INFO, "calorieTotal for specific date has been updated and printed");
        logr.log(Level.INFO, "end of process-getCalories");
        logr.exiting(getClass().getName(), "getCalories");
        return calorieTotal;
    }

    /**
     * This method sums up the volume total for a specific date.
     *
     *@param date date provided by user
     *@return total volume for the specific date
     *@throws FluidExceptions if description for a fluid entry is not found
     *@throws FoodBankException if calories for a fluid entry is not found
     */
    public int getVolume(String date) throws FoodBankException, FluidExceptions {
        logr.entering(getClass().getName(), "getVolume");
        int volumeTotal = 0;
        logr.log(Level.INFO, "volumeTotal has been reset");
        for (String fluid : fluidArray) {
            if (fluid.contains(date)) {
                generateFluidParameters(fluid);
                volumeTotal += volume;
                assert ((volumeTotal > 0) || (volumeTotal == 0));
            }
        }
        logr.log(Level.INFO, "volumeTotal for specific date has been updated and printed");
        logr.log(Level.INFO, "end of process-getVolume");
        logr.exiting(getClass().getName(), "getVolume");
        return volumeTotal;
    }
}