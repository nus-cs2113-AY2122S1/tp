package seedu.duke.logic;


import org.junit.jupiter.api.Test;
import seedu.duke.data.item.exercise.Exercise;
import seedu.duke.data.item.exercise.ExerciseList;
import seedu.duke.data.item.food.Food;
import seedu.duke.data.item.food.FoodList;
import seedu.duke.data.profile.Profile;
import seedu.duke.data.profile.attributes.ActivityFactor;
import seedu.duke.data.profile.attributes.Age;
import seedu.duke.data.profile.attributes.CalorieGoal;
import seedu.duke.data.profile.attributes.Gender;
import seedu.duke.data.profile.attributes.Height;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsTest {
    private final String ls = System.lineSeparator();
    private FoodList foodItems;
    private ExerciseList exerciseItems;
    private Profile profile;
    Statistics stats = new Statistics(new FoodList(), new ExerciseList(), new Profile());

    @Test
    void printCalorieResult_netCaloriesAndCalorieGoal_messages() {
        int caloriesGoal = 5000;
        int foodCalories = 3000;
        int exerciseCalories = 2000;
        int netCalories = foodCalories - exerciseCalories;
        String message = stats.printCaloriesMessage(netCalories, caloriesGoal);
        assertEquals("You are 4000 cal away from your goal!", message);
    }

    @Test
    void printCalories_foodCaloriesExerciseCaloriesAndCalorieGoal_messageArray() {
        this.profile = new Profile();
        profile.setProfileAge(new Age(12));
        profile.setProfileHeight(new Height(170.0));
        profile.setProfileGender(new Gender('M'));
        profile.setProfileActivityFactor(new ActivityFactor(3));
        int caloriesGoal = 5000;
        int foodCalories = 3000;
        int exerciseCalories = 2000;
        String expectedResult = "You are 6448 cal away from your goal!";
        assertEquals(expectedResult, stats.getCaloriesReport(foodCalories, exerciseCalories, caloriesGoal)[4]);
    }

    @Test
    void getCurrentDayOverview_foodAndExerciseInput_messageArray()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.profile = new Profile();
        profile.setProfileAge(new Age(21));
        profile.setProfileHeight(new Height(170.0));
        profile.setProfileGender(new Gender('M'));
        profile.getProfileCalorieGoal().setCalorieGoal(0);
        this.foodItems = new FoodList();
        this.exerciseItems = new ExerciseList();
        profile.setProfileActivityFactor(new ActivityFactor(3));
        this.foodItems.addItem(new Food("food1", 21));
        this.exerciseItems.addItem(new Exercise("wwe", 12));
        Method method = stats.getClass().getDeclaredMethod("getCurrentDayOverview");
        method.setAccessible(true);
        String result = (String) method.invoke(stats);
        String expected = "This is your calorie overview for today:" + ls
                + "Your calorie gained from food is: 0" + ls
                + "Your calorie lost from exercise is: 0" + ls
                + "Your net calorie intake is: -448" + ls
                + "Your calorie goal is: 0" + ls
                + "You are 448 cal away from your goal!";
        assertEquals(expected, result);
    }

}