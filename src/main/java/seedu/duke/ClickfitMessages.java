package seedu.duke;

import seedu.duke.schedule.ScheduledWorkout;

//@@author { ALL}
public class ClickfitMessages {

    public static final String INITIAL_PROMPT = "What would you like to start with?";

    public static final String MESSAGE_A = System.lineSeparator() + "Welcome! Here's to your fitness journey! "
            + "Type in any commands to get started! Type \"help\" to get started!"
            + System.lineSeparator()
            + "Lets work hard together in your fitness journey!";

    public static final String CREDITS = System.lineSeparator() + "Thank you for the hardwork today. "
            + "CLI.ckFit wishes you a good day" + System.lineSeparator() + "Team CLI.ckFit is proudly "
            + "brought to you by Jiewen, Vishal, Pragyan, Ivy and Edward."
            + System.lineSeparator() + "See you soon!";

    public static final String MEMORY_STARTUP_PROMPT = System.lineSeparator() + "Would you "
            + "like to clear all records of your fitness journey?" + System.lineSeparator()
            + "Key in \"y\" to clear your records, or press enter keystroke to load in data from your "
            + "previous session(s)" + System.lineSeparator()
            + "Note: Keying in \"y\" will result in the previous session's data being deleted!";

    public static final String INCORRECT_INPUT = System.lineSeparator() + "CLI.ckFit has detected a "
            + "wrong input, kindly check your inputs or type \"help commands\" for input examples.";

    public static final String MEMORY_STARTUP_N_INPUT = System.lineSeparator() + "Understood! CLI.ckFit "
            + "wishes you all the best for the rest of the day";

    public static final String MEMORY_STARTUP_Y_INPUT = System.lineSeparator() + "Understood! "
            + "CLI.ckFit is accessing your storage...";

    public static final String MEMORY_STARTUP_INCORRECT_INPUT = System.lineSeparator() + "CLI.ckFit "
            + "has detected a wrong input, kindly type in either a \"Y\" or a \"N\".";

    public static final String HELP_COMMANDS =
            "parameters encapsulated by angle brackets \"< >\" are optional" + System.lineSeparator()
                    + "NOTE: You can only omit putting MEAL_CALORIES if you have saved the meal in your meal library."
                    + System.lineSeparator()
                    + System.lineSeparator() + "[Add meal] | add meal MEAL_NAME </c MEAL_CALORIES /d DATE /t TIME>"
                    + System.lineSeparator() + "[Add fluid] | add fluid FLUID_NAME </c FLUID_CALORIES "
                    + "/v VOLUME /d DATE /t TIME>"
                    + System.lineSeparator() + "[Add weight] | add weight WEIGHT /d <DATE>"
                    + System.lineSeparator() + "[Add workout] | add workout WORKOUT_NAME /c "
                    + "CALORIES_BURNED </d DATE /t TIME>"
                    + System.lineSeparator() + "[Add scheduled workout] | add schedule WORKOUT_NAME /d "
                    + "DATE /t TIME </a ACTIVTY_NAME:ACTIVITY_QUANITIFER, ...> </r>"
                    + System.lineSeparator() + "[Add meal to library] | library addmeal MEAL_NAME /c MEAL_CALORIES"
                    + System.lineSeparator() + "[Add fluid to library] | library addfluid "
                    + "FLUID_NAME /c FLUID_CALORIES"
                    + System.lineSeparator()
                    + System.lineSeparator() + "[Remove meal] | delete meal INDEX"
                    + System.lineSeparator() + "[Remove fluid] | delete fluid INDEX"
                    + System.lineSeparator() + "[Remove weight] | delete weight INDEX"
                    + System.lineSeparator() + "[Remove workout] | delete workout INDEX"
                    + System.lineSeparator() + "[Remove scheduled workout] | delete schedule INDEX"
                    + System.lineSeparator() + "[Remove meal from library] | library deletemeal INDEX"
                    + System.lineSeparator() + "[Remove fluid from library] | library deletefluid INDEX"
                    + System.lineSeparator()
                    + System.lineSeparator() + "[List meals] | list meals <DATE>"
                    + System.lineSeparator() + "[List fluids] | list fluids <DATE>"
                    + System.lineSeparator() + "[List weights] | list weights <DATE>"
                    + System.lineSeparator() + "[List workouts] | list workouts <DATE>"
                    + System.lineSeparator() + "[List calories] | list calories <DATE>"
                    + System.lineSeparator() + "[List volume] | list volumes <DATE>"
                    + System.lineSeparator() + "[List scheduled workouts] | list schedule <DATE>"
                    + System.lineSeparator() + "[List meals from library] | library listmeals"
                    + System.lineSeparator() + "[List fluids from library] | library listfluids"
                    + System.lineSeparator()
                    + System.lineSeparator() + "[Access user help] | help commands"
                    + System.lineSeparator() + "[Access user guide] | help UG" + System.lineSeparator()
                    + System.lineSeparator() + "Here is the link to our User Guide! "
                    + "https://ay2122s1-cs2113t-f14-3.github.io/tp/UserGuide.html";

    public static final String DATE_TIME_ERROR = "Please enter your date and time in the right format. "
            + "It should be \"DD/MM/YYYY\" and \"HH:MM\" respectively.";

    public static final String NUMBER_ERROR = "Please enter a positive integer.";

    public static final String CALCULATOR_PROMPT = System.lineSeparator() + "Would you "
            + "like to check your BMI and recommended caloric intake?" + System.lineSeparator()
            + "Key in \"y\" to proceed, or press enter keystroke to skip!";

    public static final String MEAL_PRINT_FORMAT = System.lineSeparator() + "[Meal Summary:]"
            + System.lineSeparator() + "======================";

    public static final String FLUID_PRINT_FORMAT = System.lineSeparator() + "[Fluid Summary:]"
            + System.lineSeparator() + "======================";

    public static final String WORKOUT_PRINT_FORMAT = System.lineSeparator() + "[Workout Summary:]"
            + System.lineSeparator() + "======================";

    public static final String WEIGHT_PRINT_FORMAT = System.lineSeparator() + "[Weight Summary:]"
            + System.lineSeparator() + "======================";

    public static final String ENDLINE_PRINT_FORMAT = "======================";

    public static final String SCHEDULE_DATA_NOT_FOUND = "Unable to locate ScheduleTracker data file.";

    public static final String INCORRECT_LOADING_SCHEDULE_DATA =
            "There were some errors during loading of ScheduleTracker data, some data may have been lost.";

    public static final String IO_EXCEPTION_MESSAGE = "Error when loading data from files";

    public static final String FOOD_BANK_EXCEPTION_MESSAGE = "No such food or fluid with "
            + "its associated calories is stored "
            + "within your library. Please enter calories.";

    public static final String DELETE_OR_UPDATE_SCHEDULE_MESSAGE = "CLI.ckFit has detected some overdue scheduled "
            + "workouts and has deleted/rescheduled them!";

    public static final String WEIGHT_ADD_FORMAT_ERROR = "CLI.ckFit encountered a problem adding your weight."
            + System.lineSeparator() + "Please follow the format: add weight WEIGHT_IN_KG </d DD/MM/YYYY>";

    public static final String WEIGHT_DELETE_FORMAT_ERROR = "CLI.ckFit encountered a problem deleting your weight."
            + System.lineSeparator() + "Please follow the format: delete weight INDEX";

    public static final String WEIGHT_DELETE_INDEX_ERROR = "CLI.ckFit encountered a problem deleting your weight."
            + System.lineSeparator() + "Please ensure the index is within the list.";

    public static final String WEIGHT_EMPTY_ERROR = "Your weight list is empty!";

    public static final String EMPTY_SCHEDULE_LIST_MESSAGE = "Your workout schedule is empty!";

    public static final String EMPTY_WORKOUT_LIST_MESSAGE = "Your workout list is empty!";

    public static final String WEIGHT_PARAMETERS_ERROR = "CLI.ckFit could not generate your parameters.";

    public static final String DONT_UNDERSTAND = "OOPS!!! I'm sorry, but I don't know what that means";

    public static final String EMPTY_WORKOUT_LIST_TODAY_MESSAGE = "No workouts recorded for today!";

    public static final String EMPTY_SCHEDULE_LIST_TODAY_MESSAGE = "No workouts scheduled for today!";

    public static final String WORKOUT_SCHEDULE_TODAY_MESSAGE = "Today's workout schedule:" + System.lineSeparator()
            + Ui.HORIZONTAL_BAR_SHORT;

    public static final String FULL_SCHEDULE_LIST_MESSAGE = "Full Workout Schedule:" + System.lineSeparator()
            + Ui.HORIZONTAL_BAR_SHORT;

    public static final String FULL_WORKOUT_LIST_MESSAGE = "All recorded workouts:" + System.lineSeparator()
            + Ui.HORIZONTAL_BAR_SHORT;

    public static final String WORKOUTS_RECORDED_TODAY_MESSAGE = "Workouts recorded today:"
            + System.lineSeparator() + Ui.HORIZONTAL_BAR_SHORT;

    public static final String UNKNOWN_ERROR_MESSAGE = "Unknown error detected, please contact the devs of CLI.ckFit";

    public static final String EMPTY_FLUID_LIBRARY = "Your fluids library is empty!";

    public static final String EMPTY_FLUID_LIST = "Your fluids list is empty!";

    public static String getScheduledWorkoutCountMessage(int workoutCount) {
        return "You have " + workoutCount + " scheduled workouts on that day!";
    }

    public static String getWorkoutScheduleOnDateMessage(String date) {
        return "Workout schedule on " + date + ":" + System.lineSeparator()
                + Ui.HORIZONTAL_BAR_SHORT;
    }

    public static String getEmptyScheduleOnDateMessage(String date) {
        return "Workout schedule is empty on the date: " + date;
    }

    public static String getAddScheduleSuccessMessage(ScheduledWorkout workoutToAdd) {
        return "Noted! CLI.ckFit has scheduled your " + workoutToAdd.isRecurringStatusAsText()
                + "workout of description \"" + workoutToAdd.getWorkoutDescription() + "\" on "
                + workoutToAdd.getWorkoutDate() + " at " + workoutToAdd.getWorkoutTime() + ".";
    }

    public static String getDeleteScheduleSuccessMessage(ScheduledWorkout workoutToDelete) {
        return "Noted! CLI.ckFit has successfully deleted your "
                + workoutToDelete.isRecurringStatusAsText() + "scheduled workout of description \""
                + workoutToDelete.getWorkoutDescription() + "\" on " + workoutToDelete.getWorkoutDate()
                + " at " + workoutToDelete.getWorkoutTime() + "!";
    }

    public static String getAddWorkoutSuccessMessage(String workoutDescription, String workoutDate,
                                                     String workoutTime, int caloriesBurned) {
        return "Noted! CLI.ckFit has recorded your workout of description \"" + workoutDescription
                + "\" on " + workoutDate + " at " + workoutTime + " where you burned "
                + caloriesBurned + " calories!";
    }

    public static String getDeleteWorkoutSuccessMessage(String workoutDescription, String workoutDate,
                                                        String workoutTime, int caloriesBurned) {
        return "Noted! CLI.ckFit has successfully deleted your recorded workout of description \""
                + workoutDescription + "\" on " + workoutDate + " at " + workoutTime + System.lineSeparator()
                + "where you burned " + caloriesBurned + " calories!";
    }

    public static String getEmptyWorkoutListOnDateMessage(String date) {
        return "No workouts recorded on the date: " + date;
    }

    public static String getWorkoutsOnDateMessage(String date) {
        return "Workouts recorded on " + date + ":"
                + System.lineSeparator() + Ui.HORIZONTAL_BAR_SHORT;
    }

    public static String getTotalScheduledWorkoutMessage(int scheduleCount) {
        return "You have a total of " + scheduleCount + " workouts in your schedule.";
    }

    public static String getTotalCaloriesBurnedMessage(int caloriesBurned) {
        return "Total calories burned: " + caloriesBurned;
    }

    public static String getTotalWorkoutsDoneMessage(int totalWorkouts) {
        return "You have completed a total of " + totalWorkouts + " workouts. Amazing job!";
    }

    //@@author VishalJeyaram
    /**
     * To notify the user that a meal has been added to their list of meals.
     */
    public static void printAddedMealMessage(String description, String date, String time,
                                             int calories) {
        System.out.println("Noted! CLI.ckFit has recorded your meal of "
                + description + " on " + date + " and at " + time
                + ". " + calories + " calories has been added to the calorie count!\n");
    }

    //@@author VishalJeyaram
    /**
     * To notify the user that a meal has been deleted from their list of meals.
     */
    public static void printDeletedMealMessage(String description, String date) {
        System.out.println(description + " will be removed from your list of meals consumed!\n");
    }

    //@@author VishalJeyaram
    /**
     * To notify the user that their meal list is empty.
     */
    public static void printEmptyMealList() {
        System.out.println("Your meal list is empty!");
    }

    //@@author VishalJeyaram
    /**
     * To tell the user how many calories and meals they have consumed thus far.
     */
    public static void printMealListTotals(int mealNumber, int totalCalories) {
        System.out.println("Total number of meals: " + mealNumber);
        System.out.println("Total calories: " + totalCalories);
    }

    //@@author VishalJeyaram
    /**
     * To tell the user the details of a single meal such as its name, calories, and date and time of consumption.
     */
    public static void printSingleMealDetails(int i, String description, int calories, String date, String time) {
        System.out.println(i + ". " + description);
        System.out.println("Calories: " + calories);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time + "\n");
    }

    //@@author VishalJeyaram
    /**
     * To notify the user that a meal has been added to their library.
     */
    public static void printAddedLibraryMeal(String description, int calories, int totalMeals) {
        System.out.println(description + ", which has " + calories
                + " calories, will be added to your library of meals. You now have " + totalMeals + " meals!\n");
    }

    //@@author VishalJeyaram
    /**
     * To notify the user that a meal has been deleted from their library.
     */
    public static void printDeletedLibraryMeal(String description, int totalMeals) {
        System.out.println(description + " will be removed from your meal library. You now have "
                + totalMeals + " meals left!\n");
    }

    //@@author VishalJeyaram
    /**
     * To tell the user the details of a single meal in the library such as its name and calories.
     */
    public static void printSingleLibraryMeal(int i, String description, int calories) {
        System.out.println(i + ". " + description);
        System.out.println("Calories: " + calories + "\n");
    }

    //@@author VishalJeyaram
    /**
     * To tell the user how many meals are in their meal library.
     */
    public static void printMealLibraryTotals(int totalMeals) {
        System.out.println("Total number of meals in library: " + totalMeals);
    }

    //@@author teoziyiivy
    /**
     * Prints response for weight has been added to weight array successfully.
     *
     * @param weight Weight recorded in weight array.
     * @param date   Date recorded in weight array.
     */
    public static void printAddWeightResponse(double weight, String date) {
        System.out.println("Noted! CLI.ckFit has recorded your weight as "
                + weight + " kg on " + date + ". Check back for your progress!");
    }

    //@@author teoziyiivy
    /**
     * Prints response for weight has been deleted from weight array successfully.
     *
     * @param weight Weight recorded in weight array.
     * @param date   Date recorded in weight array.
     */
    public static void printDeleteWeightResponse(double weight, String date) {
        System.out.println("Noted! CLI.ckFit has successfully deleted your weight of "
                + weight + " kg on " + date + ".");
    }
}


