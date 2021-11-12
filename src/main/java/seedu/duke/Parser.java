package seedu.duke;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.fluid.FluidExceptions;
import seedu.duke.exceptions.fluid.NegativeVolumeException;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.FoodBankException;
import seedu.duke.exceptions.foodbank.NegativeCaloriesException;
import seedu.duke.exceptions.foodbank.NoFoodFoundException;
import seedu.duke.exceptions.schedule.GetActivityException;
import seedu.duke.exceptions.schedule.InvalidActivityFormatException;
import seedu.duke.exceptions.schedule.InvalidScheduleDescriptionException;
import seedu.duke.exceptions.schedule.MissingActivityQuantifierException;
import seedu.duke.exceptions.schedule.MissingActivitySplitterException;
import seedu.duke.exceptions.schedule.UnnecessaryQuantifierException;
import seedu.duke.exceptions.workout.MissingWorkoutCalorieSeparatorException;
import seedu.duke.exceptions.workout.NegativeWorkoutCalorieException;
import seedu.duke.exceptions.schedule.ScheduleException;
import seedu.duke.exceptions.workout.WorkoutException;
import seedu.duke.schedule.WorkoutActivity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Parser {

    public static final String DATE_SEPARATOR = " /d ";
    public static final String TIME_SEPARATOR = " /t ";
    public static final String CALORIE_SEPARATOR = " /c ";
    public static final String RECURRING_FLAG = " /r";
    public static final String VOLUME_SEPARATOR = " /v ";
    public static final String ACTIVITY_SEPARATOR = " /a ";
    public static final String MULTIPLE_ACTIVITY_MARKER = ",";
    public static final String ACTIVITY_SPLITTER = ":";
    public static final String QUANTIFIER_SPLITTER = "x";
    public static final String EMPTY_STRING = "";

    //@@author teoziyiivy
    /**
     * Checks if the user's input contains the date separator ` /d `.
     *
     * @param inputArguments User's input.
     * @return true, if input contains date separator, and false, if input does not contain date separator.
     */
    public static boolean containsDateSeparator(String inputArguments) {
        return inputArguments.contains(DATE_SEPARATOR);
    }

    //@@author teoziyiivy
    /**
     * Checks if the user's input contains time separator ` /t `.
     *
     * @param inputArguments User's input.
     * @return true, if input contains time separator, and false, if input does not contain time separator.
     */
    public static boolean containsTimeSeparator(String inputArguments) {
        return inputArguments.contains(TIME_SEPARATOR);
    }

    //@@author teoziyiivy
    /**
     * Checks if the user's input contains calorie separator ' /c '.
     *
     * @param inputArguments User's input.
     * @return true, if input contains calorie separator, and false, if input does not contain calorie separator.
     */
    public static boolean containsCalorieSeparator(String inputArguments) {
        return inputArguments.contains(CALORIE_SEPARATOR);
    }

    //@@author pragyan01
    /**
     * Checks if the user's input contains volume separator ' /v '.
     *
     * @param inputArguments User's input.
     * @return true, if input contains calorie separator, and false, if input does not contain calorie separator.
     */
    public static boolean containsVolumeSeparator(String inputArguments) {
        return inputArguments.contains(VOLUME_SEPARATOR);
    }

    //@@author EdwardZYWang
    /**
     * Checks if the user's input contains recurring workout flag ' /r'.
     *
     * @param inputArguments User's input.
     * @return true, if input contains recurring workout flag, and false otherwise.
     */
    public static boolean isRecurringWorkout(String inputArguments) {
        String[] splitResults = inputArguments.split(RECURRING_FLAG, 2);
        if (splitResults.length == 1) {
            return false;
        }
        return splitResults[1].isEmpty(); // true if /r flag is at the end of the string
    }

    //@@author teoziyiivy
    /**
     * Converts user's String input to Integer.
     *
     * @param inputArguments User's input.
     * @return Integer.parseInt(inputArguments) User's input as Integer.
     * @throws NumberFormatException If user's input is not a valid Integer.
     */
    public static int parseStringToInteger(String inputArguments) throws NumberFormatException {
        return Integer.parseInt(inputArguments);
    }

    //@@author teoziyiivy
    /**
     * Converts user's String input to Double.
     *
     * @param inputArguments User's input.
     * @return Double.parseDouble(inputArguments) User's input as Double.
     * @throws NumberFormatException If user's input is not a valid Double.
     */
    public static double parseStringToDouble(String inputArguments) throws NumberFormatException {
        return Double.parseDouble(inputArguments);
    }

    //@@author VishalJeyaram
    /**
     * Checks if the user's input contains separators.
     *
     * @param inputArguments User's input.
     * @return true, if input contains separators, and false, if input does not contain separators.
     */
    public static boolean containsSeparators(String inputArguments) {
        if (inputArguments.contains(CALORIE_SEPARATOR.trim())) {
            return true;
        } else if (inputArguments.contains(DATE_SEPARATOR.trim())) {
            return true;
        } else if (inputArguments.contains(TIME_SEPARATOR.trim())) {
            return true;
        } else {
            return false;
        }
    }

    //@@author VishalJeyaram
    /**
     * Returns calories extracted from user's input or from meal or fluid library.
     *
     * @param inputArguments User's input.
     * @return Calories.
     * @throws DukeException If the user's description is empty.
     * @throws NumberFormatException If calories is not an integer value.
     * @throws FoodBankException If food already exists within either meal or fluid library.
     */
    public static int getCalories(String inputArguments)
            throws NumberFormatException, FoodBankException {
        String description = getDescription(inputArguments);
        if (!containsCalorieSeparator(inputArguments)) {
            return findCaloriesInLibrary(description);
        } else {
            return extractCalories(inputArguments);
        }
    }

    //@@author VishalJeyaram
    /**
     * Returns calories from user's input.
     *
     * @param inputArguments User's input.
     * @return Calories.
     * @throws NumberFormatException If calories is not an integer value.
     * @throws FoodBankException If the user has keyed in negative calories.
     */
    private static int extractCalories(String inputArguments) throws FoodBankException {
        int calories = 0;
        String[] userInput = inputArguments.split("\\s+");
        int length = userInput.length;
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(CALORIE_SEPARATOR.trim())) {
                calories = parseStringToInteger(userInput[i + 1]);
            }
        }
        if (calories < 0) {
            throw new NegativeCaloriesException();
        } else {
            return calories;
        }
    }

    //@@author VishalJeyaram
    /**
     * Returns calories from meal or fluid library.
     *
     * @param description Name of Food
     * @return Calories.
     * @throws FoodBankException If the user hasn't keyed in a description for their food, or if their
     *                           meal or fluid is not stored in the meal or fluid library.
     */
    private static int findCaloriesInLibrary(String description) throws FoodBankException {
        int calories;
        if (Parser.containsSeparators(description)) {
            throw new EmptyFoodDescription();
        } else if (FoodBank.isFoodFound(description)) {
            calories = FoodBank.findCalories(description);
            return calories;
        } else {
            throw new NoFoodFoundException();
        }
    }

    //@@author EdwardZYWang
    /**
     * gets calories burned through workout from the user's inputs.
     *
     * @param inputArguments arguments of the user's inputs.
     * @return calories burned for workout parameter
     * @throws WorkoutException if there is an unknown error encountered.
     */
    public static int getCaloriesBurnedForWorkout(String inputArguments) throws WorkoutException {
        int calories = 0;
        boolean isCaloriesParsed = false;
        String[] userInput = inputArguments.split("\\s+");
        int length = userInput.length;
        for (int i = 0; i < length; i++) {
            if (userInput[i].equals(CALORIE_SEPARATOR.trim())) {
                try {
                    calories = parseStringToInteger(userInput[i + 1]);
                    isCaloriesParsed = true;
                    break;
                } catch (IndexOutOfBoundsException e) {
                    throw new NumberFormatException();
                }
            }
        }
        if (!isCaloriesParsed) {
            throw new MissingWorkoutCalorieSeparatorException();
        }
        if (calories < 0) {
            throw new NegativeWorkoutCalorieException();
        } else {
            return calories;
        }
    }

    //@@author pragyan01
    /**
     * This method extracts volume parameter from user input.
     *
     *@param inputArguments user input provided
     *@return volume parameter
     *@throws DukeException if volume entered is negative
     */
    public static int getVolume(String inputArguments) throws FluidExceptions {
        String[] userInput = inputArguments.split("\\s+");
        int length = userInput.length;
        int volume = 0;
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(VOLUME_SEPARATOR.trim())) {
                volume = parseStringToInteger(userInput[i + 1]);
                break;
            }
        }
        if (volume < 0) {
            throw new NegativeVolumeException();
        }
        return volume;
    }

    //@@author pragyan01
    /**
     * This method extracts description parameter from user input.
     *
     *@param inputArguments user input provided
     *@return description parameter
     *
     *@author pragyan01
     */
    public static String getDescription(String inputArguments) {
        String[] userInput;
        if (containsCalorieSeparator(inputArguments)) {
            userInput = inputArguments.split(CALORIE_SEPARATOR);
        } else if (containsDateSeparator(inputArguments)) {
            userInput = inputArguments.split(DATE_SEPARATOR);
        } else if (containsTimeSeparator(inputArguments)) {
            userInput = inputArguments.split(TIME_SEPARATOR);
        } else if (containsVolumeSeparator(inputArguments)) {
            userInput = inputArguments.split(VOLUME_SEPARATOR);
        } else {
            return inputArguments;
        }
        String description = userInput[0].trim();
        return description;
    }

    //@@author VishalJeyaram
    /**
     * Returns date extracted from user's input.
     *
     * @param inputArguments User's input.
     * @return Date.
     * @throws DateTimeParseException If the date is not entered properly.
     */
    public static String getDate(String inputArguments) throws DateTimeParseException {
        String[] userInput = inputArguments.split("\\s+");
        int length = userInput.length;
        String date = "";
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(DATE_SEPARATOR.trim())) {
                date = userInput[i + 1];
                break;
            }
        }
        if (date.equals("")) {
            String newDate = getSystemDate();
            DateTracker.checkIfDateExists(newDate);
            return newDate;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        DateTracker.checkIfDateExists(formatter.format(localDate));
        return formatter.format(localDate);
    }

    //@@author teoziyiivy
    /**
     * Returns date extracted from user's input to be used for comparisons without the need
     * for tracking.
     *
     * @param inputArguments User's input.
     * @return String Date that user input.
     * @throws DateTimeParseException If the date is not entered properly.
     */
    public static String getDateNoDateTracker(String inputArguments) throws DateTimeParseException {
        String[] userInput = inputArguments.split("\\s+");
        int length = userInput.length;
        String date = EMPTY_STRING;
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(DATE_SEPARATOR.trim())) {
                date = userInput[i + 1];
                break;
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return formatter.format(localDate);
    }

    //@@author teoziyiivy
    /**
     * Extracts the time from the user input.
     *
     * @param inputArguments User input.
     * @return String Time that user input.
     * @throws DateTimeParseException If time is not valid.
     */
    public static String getTime(String inputArguments) throws DateTimeParseException {
        String[] userInput = inputArguments.split("\\s+");
        int length = userInput.length;
        String time = "";
        for (int i = 1; i < length; i++) {
            if (userInput[i].equals(TIME_SEPARATOR.trim())) {
                time = userInput[i + 1];
                break;
            }
        }
        if (time.equals("")) {
            time = getSystemTime();
        }
        LocalTime localTime = LocalTime.parse(time);
        String properTime = localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        return properTime;
    }

    //@@author teoziyiivy
    /**
     * Extracts the weight from the user input.
     *
     * @param inputArguments User input.
     * @return double Weight that user input.
     * @throws DukeException If input does not have a weight or (weight < 0) or (weight > 1000).
     */
    public static double getWeight(String inputArguments) throws DukeException {
        String[] userInput = inputArguments.split(DATE_SEPARATOR);
        if (!userInput[0].matches("^\\d+(\\.\\d+)?")) {
            throw new DukeException("Invalid weight!");
        }
        double weight = parseStringToDouble(userInput[0]);
        if (weight < 0) {
            throw new DukeException("Negative weight");
        }

        if (weight > 1000) {
            throw new DukeException("Exceeded maximum weight");
        }
        return weight;
    }

    //@@author arvejw
    /**
     * Returns the description of the scheduled workout.
     *
     * @param inputArguments Arguments input by the user that come after the command word.
     * @return String Description of the workout.
     * @throws ScheduleException If unable to extract description.
     */
    public static String getScheduleDescription(String inputArguments) throws ScheduleException {
        String[] userInput = inputArguments.split(DATE_SEPARATOR);
        if (userInput.length == 1) {
            throw new InvalidScheduleDescriptionException();
        }
        String description = userInput[0].trim();
        return description;
    }

    //@@author arvejw
    /**
     * Returns the parsed breakdown of the workout activities.
     *
     * @param inputArguments Arguments input by the user that come after the command word.
     * @return Map of activity description and activity quantifier pairs.
     * @throws ScheduleException If there is missing activity splitter, quantifier or invalid format.
     */
    public static Map<String, ArrayList<Integer>> getActivities(String inputArguments) throws ScheduleException {
        int indexOfActivitySeparator = inputArguments.indexOf(Parser.ACTIVITY_SEPARATOR);
        String subSubstringAfterActivitySeparator = EMPTY_STRING;
        if (indexOfActivitySeparator != -1) {
            subSubstringAfterActivitySeparator = inputArguments.substring(
                    indexOfActivitySeparator).trim();
            if (isRecurringWorkout(inputArguments)) {
                subSubstringAfterActivitySeparator = subSubstringAfterActivitySeparator
                        .replace(RECURRING_FLAG, "").replace(ACTIVITY_SEPARATOR.trim(), "").trim();
            } else {
                subSubstringAfterActivitySeparator = subSubstringAfterActivitySeparator
                        .replace(ACTIVITY_SEPARATOR.trim(), "").trim();
            }
        }
        if (subSubstringAfterActivitySeparator.isEmpty()) {
            return new HashMap<>();
        } else {
            return getActivityArguments(subSubstringAfterActivitySeparator.split(MULTIPLE_ACTIVITY_MARKER));
        }
    }

    //@@author arvejw
    /**
     * Returns the description and arguments for the workout activity.
     *
     * @param nonParsedActivities The activities which have not been parsed.
     * @return Map of activity description and activity quantifier pairs.
     * @throws ScheduleException If there is missing activity splitter, quantifier or invalid format.
     */
    public static Map<String, ArrayList<Integer>> getActivityArguments(String[] nonParsedActivities)
            throws ScheduleException {
        Map<String, ArrayList<Integer>> outputMap = new HashMap<>();
        for (String activity : nonParsedActivities) {
            String[] splitResults = activity.split(ACTIVITY_SPLITTER, 2);
            if (splitResults.length == 1) {
                throw new MissingActivitySplitterException();
            }
            String[] quantifierSplitResults = splitResults[1].split(QUANTIFIER_SPLITTER, 2);
            if (quantifierSplitResults.length == 1 && !WorkoutActivity.isDistanceActivity(splitResults[0])) {
                throw new MissingActivityQuantifierException();
            }
            if (quantifierSplitResults.length == 2 && WorkoutActivity.isDistanceActivity(splitResults[0])) {
                throw new UnnecessaryQuantifierException();
            }
            ArrayList<Integer> activityQuantifiers = new ArrayList<Integer>();
            if (WorkoutActivity.isDistanceActivity(splitResults[0])) {
                parseDistanceActivityQuantifiers(quantifierSplitResults, activityQuantifiers);
            } else if (quantifierSplitResults.length == 2) {
                parseNonDistanceActivityQuantifiers(quantifierSplitResults, activityQuantifiers);
            } else {
                throw new GetActivityException();
            }
            outputMap.put(splitResults[0].trim(), activityQuantifiers);
        }
        return outputMap;
    }

    //@@author arvejw
    /**
     * Adds distance activity quantifiers to array list of activity quantifiers.
     *
     * @param quantifierSplitResults Array of quantifier split results.
     * @param activityQuantifiers ArrayList of activity quantifiers.
     * @throws InvalidActivityFormatException If non-integer or integer less than equal to 0 detected.
     */
    private static void parseDistanceActivityQuantifiers(
            String[] quantifierSplitResults, ArrayList<Integer> activityQuantifiers)
            throws InvalidActivityFormatException {
        try {
            if (parseStringToInteger(quantifierSplitResults[0].trim()) <= 0) {
                throw new NumberFormatException();
            }
            activityQuantifiers.add(parseStringToInteger(quantifierSplitResults[0].trim()));
        } catch (NumberFormatException e) {
            throw new InvalidActivityFormatException();
        }
    }

    //@@author arvejw
    /**
     * Adds non-distance activity quantifiers to array list of activity quantifiers.
     *
     * @param quantifierSplitResults Array of quantifier split results.
     * @param activityQuantifiers ArrayList of activity quantifiers.
     * @throws InvalidActivityFormatException If non-integer or integer less than equal to 0 detected.
     */
    private static void parseNonDistanceActivityQuantifiers(
            String[] quantifierSplitResults, ArrayList<Integer> activityQuantifiers)
            throws InvalidActivityFormatException {
        try {
            if (parseStringToInteger(quantifierSplitResults[0].trim()) <= 0
                    || parseStringToInteger(quantifierSplitResults[1].trim()) <= 0) {
                throw new NumberFormatException();
            }
            activityQuantifiers.add(parseStringToInteger(quantifierSplitResults[0].trim()));
            activityQuantifiers.add(parseStringToInteger(quantifierSplitResults[1].trim()));
        } catch (NumberFormatException e) {
            throw new InvalidActivityFormatException();
        }
    }

    //@@author pragyan01
    /**
     * This method obtains current system date of the user.
     *
     *@return current system date
     *
     *@author pragyan01
     */
    public static String getSystemDate() {
        String systemDate = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());
        LocalDate now = LocalDate.now();
        systemDate = now.format(dtf);

        return systemDate;
    }

    //@@author pragyan01
    /**
     * This method obtains current system time of the user.
     *
     *@return current system time
     *
     *@author pragyan01
     */
    public static String getSystemTime() {
        String systemTime = "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
        LocalTime now = LocalTime.now();
        systemTime = now.format(dtf);
        return systemTime;
    }
}
