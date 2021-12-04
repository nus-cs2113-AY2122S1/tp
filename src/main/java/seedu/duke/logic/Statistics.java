package seedu.duke.logic;

import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.exceptions.InvalidCharacteristicException;
import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/** A class that manage the statistics of the calories. */
public class Statistics {
    private static final String FULL_BLOCK = "█";
    private static final String LS = System.lineSeparator();
    private static final String MESSAGE_CALORIE_GAIN = "Your calorie gained from food is: %d";
    private static final String MESSAGE_CALORIE_LOST = "Your calorie lost from exercise is: %d";
    private static final String MESSAGE_CALORIE_NET = "Your net calorie intake is: %d";
    private static final String MESSAGE_CALORIE_GOAL = "Your calorie goal is: %d";
    private static final String MESSAGE_CALORIE_EXACT = "You have reached your calorie goal exactly. Good job!";
    private static final String MESSAGE_CALORIE_LESS_THAN = "You are %s cal away from your goal!";
    private static final String MESSAGE_CALORIE_MORE_THAN = "You have exceeded your calorie goal by %s cal! ";
    private static final String OVERVIEW_HEADER = "-*WEEKLY OVERVIEW*-" + LS
            + "Hi %s, this is your calorie summary for the week." + LS;
    private static final String FOOD_HEADER = "Food:" + LS
            + "You have consumed %1$s cal this week from %2$s to %3$s.";
    private static final String EXERCISE_HEADER = "Exercise:" + LS
            + "You have burnt %1$s cal this week from %2$s to %3$s.";
    private static final String FOOD_GRAPH_HEADER = "Calorie gained from food (Daily)" + LS + "%s";
    private static final String EXERCISE_GRAPH_HEADER = "Calorie burnt from exercise (Daily)" + LS + "%s";
    private static final String MESSAGE_CAUTION = LS + "** Net calories = Food consumed - Exercise output - "
            + "your basal metabolic rate, where " + LS
            + "your basal metabolic rate is a factor of your age, gender, "
            + "height and weight retrieved from your profile." + LS
            + "All calculations are done in calories.";

    private static final String GRAPH_BUILDER = "%1$s   %2$s    %3$s";
    private static final int MAX_DATE_OFFSET = 6;
    private static final int NO_OFFSET = 0;

    private static final String MESSAGE_NET_CALORIES_INTRO = "Daily net calories**:" + LS;
    private static final String MESSAGE_SUPPER_COUNT_INTRO = "Number of supper meals this week: %s";
    private static final int MAX_BAR_LENGTH = 30;
    private static final int EMPTY_CALORIES = 0;
    private static final String MESSAGE_DAILY_OVERVIEW = "This is your calorie overview for today:" + LS;


    private FoodList foodItems;
    private ExerciseList exerciseItems;
    private Profile profile;

    public Statistics(FoodList foodItems, ExerciseList exerciseItems, Profile profile) {
        this.foodItems = foodItems;
        this.exerciseItems = exerciseItems;
        this.profile = profile;
    }

    private static Logger logger = Logger.getLogger(Statistics.class.getName());
    private LocalDate date;

    /**
     * Calculate netCalories and format exerciseCalories, foodCalories, calorieGoal
     * into strings.
     *
     * @param exerciseCalories is the total calories lost by exercising
     * @param foodCalories     is the total calories gained by consuming food
     * @param calorieGoal      is the goal set by the user
     * @return formatted strings.
     */
    String[] getCaloriesReport(int exerciseCalories, int foodCalories, int calorieGoal) {
        int netCalories = calculateNetCalories(foodCalories, exerciseCalories);
        return new String[]{String.format(MESSAGE_CALORIE_GAIN, foodCalories),
                String.format(MESSAGE_CALORIE_LOST, exerciseCalories),
                String.format(MESSAGE_CALORIE_NET, netCalories),
                String.format(MESSAGE_CALORIE_GOAL, calorieGoal),
                printCaloriesMessage(netCalories, calorieGoal)};
    }

    private int calculateNetCalories(int foodCalories, int exerciseCalories) {
        try {
            return ProfileUtils.calculateNetCalories(foodCalories, exerciseCalories, this.profile);
        } catch (InvalidCharacteristicException e) {
            return 0;
        }
    }

    private String getCurrentDayOverview() {
        int foodCalories = foodItems.getTotalCaloriesWithDate(date);
        int exerciseCalories = exerciseItems.getTotalCaloriesWithDate(date);
        int calorieGoal = profile.getProfileCalorieGoal().getCalorieGoal();
        logger.log(Level.FINE, String.valueOf(calorieGoal));
        String[] messages = getCaloriesReport(exerciseCalories, foodCalories, calorieGoal);
        StringBuilder currentDayOverview = new StringBuilder(MESSAGE_DAILY_OVERVIEW);
        for (String message : messages) {
            currentDayOverview.append(message).append(Ui.LS);
        }
        return currentDayOverview.toString().trim();
    }

    public String printCaloriesMessage(int netCalories, int calorieGoal) {
        logger.log(Level.FINE, "preparing calories message");
        int calorieDifference = calorieGoal - netCalories;
        String message;
        if (calorieGoal > netCalories) {
            message = String.format(MESSAGE_CALORIE_LESS_THAN, calorieDifference);
        } else if (calorieGoal < netCalories) {
            message = String.format(MESSAGE_CALORIE_MORE_THAN, -calorieDifference);
        } else {
            assert calorieDifference == 0 : "calorieDifference should be 0";

            message = MESSAGE_CALORIE_EXACT;
        }
        return message;
    }

    /**
     * Set the date to current date.
     * Date to be updated upon calling the overview command.
     */
    private void setLocalDate() {
        date = LocalDateTime.now().toLocalDate();
    }

    private LocalDate dateOffset(int offset) {
        return date.minusDays(offset);
    }

    private ArrayList<Integer> getDailyFoodCalories() {
        ArrayList<Integer> dailyFoodCalories = new ArrayList<>();
        for (int i = MAX_DATE_OFFSET; i >= NO_OFFSET; i--) {
            dailyFoodCalories.add(foodItems.getTotalCaloriesWithDate(date.minusDays(i)));
        }
        return dailyFoodCalories;
    }

    private String getGraph(ArrayList<Integer> dailyCalories) {
        int maxCalories = Collections.max(dailyCalories);
        StringBuilder graph = new StringBuilder();
        int dateOffset = MAX_DATE_OFFSET;

        for (int calories : dailyCalories) {
            String progressBar = "";
            int numberOfBars;
            numberOfBars = (int)(((double) calories / maxCalories) * MAX_BAR_LENGTH);
            assert numberOfBars <= MAX_BAR_LENGTH : "30 is the max progress bar limit";
            for (int i = NO_OFFSET; i < numberOfBars; i++) {
                progressBar = progressBar + FULL_BLOCK;
            }
            logger.log(Level.FINE, String.valueOf(numberOfBars));
            String formattedDate = getFormatDate(dateOffset);
            graph.append(String.format(GRAPH_BUILDER, formattedDate, progressBar, calories)).append(Ui.LS);
            dateOffset--;
        }
        return graph.toString();
    }

    private String getFormatDate(int dateOffset) {
        String formattedDate = dateOffset(dateOffset).format(DateTimeFormatter.ofPattern("dd-MMM"));
        return formattedDate;
    }

    private ArrayList<Integer> getDailyExerciseCalories() {
        ArrayList<Integer> dailyExerciseCalories = new ArrayList<>();
        for (int i = MAX_DATE_OFFSET; i >= NO_OFFSET; i--) {
            dailyExerciseCalories.add(exerciseItems.getTotalCaloriesWithDate(date.minusDays(i)));
        }
        return dailyExerciseCalories;
    }

    private int getTotalWeeklyCalories(ArrayList<Integer> getCalories) {
        return getCalories.stream().mapToInt(i -> i).sum();
    }

    private String getSupperCountMessage() {
        int supperCount = foodItems.getSupperCount();
        return String.format(MESSAGE_SUPPER_COUNT_INTRO, supperCount);
    }

    private String getNetCaloriesMessage() {
        ArrayList<Integer> dailyExerciseCalories = getDailyExerciseCalories();
        ArrayList<Integer> dailyFoodCalories = getDailyFoodCalories();
        StringBuilder netCaloriesMessage = new StringBuilder(MESSAGE_NET_CALORIES_INTRO);
        for (int i = 0; i <= MAX_DATE_OFFSET; i++) {
            int exerciseCalories = dailyExerciseCalories.get(i) == null ? EMPTY_CALORIES : dailyExerciseCalories.get(i);
            int foodCalories = dailyFoodCalories.get(i) == null ? EMPTY_CALORIES : dailyFoodCalories.get(i);
            int netCalories = getNetCalories(foodCalories, exerciseCalories);
            String formattedDate = getFormatDate(MAX_DATE_OFFSET - i);
            netCaloriesMessage.append(formattedDate + " :   " + netCalories).append(Ui.LS);
        }
        return netCaloriesMessage.toString();
    }

    private int getNetCalories(int foodCalories, int exerciseCalories) {
        try {
            return ProfileUtils.calculateNetCalories(foodCalories, exerciseCalories, profile);
        } catch (InvalidCharacteristicException e) {
            return EMPTY_CALORIES;
        }
    }

    /**
     * An overview on user calorie intake and calorie burnt.
     *
     * @return String that contains summary of calories for the week.
     */
    public String overviewSummary() {
        setLocalDate(); // need ensure that the date is on the time of query
        logger.log(Level.FINE, String.valueOf(date));
        StringBuilder overviewSummary = new StringBuilder();
        overviewSummary.append(String.format(OVERVIEW_HEADER, profile.getProfileName().getName())).append(Ui.LS)
                .append(String.format(FOOD_HEADER, getTotalWeeklyCalories(getDailyFoodCalories()),
                        getFormatDate(MAX_DATE_OFFSET), getFormatDate(NO_OFFSET))).append(Ui.LS)
                .append(String.format(FOOD_GRAPH_HEADER, getGraph(getDailyFoodCalories()))).append(Ui.LS)
                .append(String.format(EXERCISE_HEADER, getTotalWeeklyCalories(getDailyExerciseCalories()),
                        getFormatDate(MAX_DATE_OFFSET), getFormatDate(NO_OFFSET))).append(Ui.LS)
                .append(String.format(EXERCISE_GRAPH_HEADER, getGraph(getDailyExerciseCalories()))).append(Ui.LS)
                .append(getNetCaloriesMessage()).append(Ui.LS)
                .append(getSupperCountMessage()).append(Ui.LS)
                .append(MESSAGE_CAUTION).append(Ui.LS)
                .append(Ui.DIVIDER).append(Ui.LS)
                .append(getCurrentDayOverview());
        return overviewSummary.toString().trim();
    }


}
