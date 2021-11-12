package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.foodbank.EmptyFoodDescription;
import seedu.duke.exceptions.foodbank.FoodBankException;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.exceptions.foodbank.NegativeCaloriesException;
import seedu.duke.exceptions.foodbank.NoFoodFoundException;
import seedu.duke.exceptions.meal.EmptyMealListException;
import seedu.duke.exceptions.meal.MealException;
import seedu.duke.exceptions.meal.NoDeleteMealIndexException;
import seedu.duke.exceptions.meal.NoMealDetailsException;
import seedu.duke.exceptions.meal.NoSuchMealIndexException;

//@@author VishalJeyaram
class MealTest {
    @Test
    void addMeal1() {
        Meal meal = new Meal();
        String input = "pasta /c 356 /d 14/09/2021 /t 7pm";
        assertThrows(DateTimeParseException.class, () -> {
            meal.addMeal(input);
        });
    }

    @Test
    void addMeal2() {
        Meal meal = new Meal();
        String input = "pasta /c 356 /d 14-09-2021 /t 17:45";
        assertThrows(DateTimeParseException.class, () -> {
            meal.addMeal(input);
        });
    }

    @Test
    void addMeal3() {
        Meal meal = new Meal();
        String input = "pasta /c sauce /d 14/09/2021 /t 17:45";
        assertThrows(NumberFormatException.class, () -> {
            meal.addMeal(input);
        });
    }

    @Test
    void addMeal4() {
        Meal meal = new Meal();
        String input = "pasta /c 30 /d 14/09/2021 /t 17:45";
        assertDoesNotThrow(() -> meal.addMeal(input));
    }

    @Test
    void addMeal5() {
        Meal meal = new Meal();
        String input = "pasta /c 30 /d 14/09/2021";
        assertDoesNotThrow(() -> meal.addMeal(input));
    }

    @Test
    void addMeal6() {
        Meal meal = new Meal();
        String input = "pasta /c 30 /t 15:07";
        assertDoesNotThrow(() -> meal.addMeal(input));
    }

    @Test
    void addMeal7() {
        Meal meal = new Meal();
        String input = "pasta /c 30";
        assertDoesNotThrow(() -> meal.addMeal(input));
    }

    @Test
    void addMeal8() throws FoodBankException {
        Meal meal = new Meal();
        new FoodBank();
        String mealLibraryInput = "pasta /c 13";
        FoodBank.addCustomMeal(mealLibraryInput);
        String input = "pasta";
        assertDoesNotThrow(() -> meal.addMeal(input));
    }

    @Test
    void addMeal9() throws FoodBankException {
        Meal meal = new Meal();
        new FoodBank();
        String mealLibraryInput = "pasta /c 13";
        FoodBank.addCustomMeal(mealLibraryInput);
        String input = "Risotto";
        assertThrows(NoFoodFoundException.class, () -> meal.addMeal(input));
    }

    @Test
    void addMeal10() {
        Meal meal = new Meal();
        String input = "/c 13";
        assertThrows(EmptyFoodDescription.class, () -> meal.addMeal(input));
    }

    @Test
    void addMeal11() {
        Meal meal = new Meal();
        String input = "pasta /c -13";
        assertThrows(NegativeCaloriesException.class, () -> meal.addMeal(input));
    }

    @Test
    void addMeal12() {
        Meal meal = new Meal();
        String input = null;
        assertThrows(NoMealDetailsException.class, () -> meal.addMeal(input));
    }

    @Test
    void addMeal13() {
        Meal meal = new Meal();
        String input = "pasta /c % /d 14/09/2021 /t 17:45";
        assertThrows(NumberFormatException.class, () -> {
            meal.addMeal(input);
        });
    }

    @Test
    void addMeal14() {
        Meal meal = new Meal();
        String input = "pasta /c -134 /d 14/09/2021 /t 17:45";
        assertThrows(NegativeCaloriesException.class, () -> {
            meal.addMeal(input);
        });
    }

    @Test
    void addMeal15() {
        Meal meal = new Meal();
        String input = "pasta /c134 /d 14/09/2021 /t 17:45";
        assertThrows(EmptyFoodDescription.class, () -> {
            meal.addMeal(input);
        });
    }

    @Test
    void deleteMeal1() throws MealException, FoodBankException {
        Meal meal = new Meal();
        String input = "pasta /c 300 /d 14/09/2021 /t 17:45";
        meal.addMeal(input);
        String deleteInput = "two";
        assertThrows(NumberFormatException.class, () -> {
            meal.deleteMeal(deleteInput);
        });
    }

    @Test
    void deleteMeal2() throws MealException, FoodBankException {
        Meal meal = new Meal();
        String input = "pasta /c 300 /d 14/09/2021 /t 17:45";
        meal.addMeal(input);
        String deleteInput = "2";
        assertThrows(NoSuchMealIndexException.class, () -> {
            meal.deleteMeal(deleteInput);
        });
    }

    @Test
    void deleteMeal3() {
        Meal meal = new Meal();
        String deleteInput = "1";
        assertThrows(EmptyMealListException.class, () -> {
            meal.deleteMeal(deleteInput);
        });
    }

    @Test
    void deleteMeal4() {
        Meal meal = new Meal();
        String deleteInput = null;
        assertThrows(NoDeleteMealIndexException.class, () -> {
            meal.deleteMeal(deleteInput);
        });
    }

    @Test
    void deleteMeal5() throws MealException, FoodBankException {
        Meal meal = new Meal();
        String input = "pasta /c 30 /d 14/09/2021";
        meal.addMeal(input);
        String deleteInput = "1";
        assertDoesNotThrow(() -> meal.deleteMeal(deleteInput));
    }

    @Test
    void deleteMeal6() throws MealException, FoodBankException {
        Meal meal = new Meal();
        String input = "pasta /c 31 /d 14/09/2021";
        meal.addMeal(input);
        String deleteInput = "&";
        assertThrows(NumberFormatException.class, () -> {
            meal.deleteMeal(deleteInput);
        });
    }

    @Test
    void listMeals1() throws MealException, FoodBankException {
        Meal meal = new Meal();
        String input = "pasta /c 30 /d 14/09/2021 /t 14:05";
        meal.addMeal(input);
        String date = "14/09/2021";
        assertDoesNotThrow(() -> meal.listMeals(date));
    }
}