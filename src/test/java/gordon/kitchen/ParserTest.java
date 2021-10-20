package gordon.kitchen;

import gordon.command.Command;
import gordon.util.Parser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    public void inputOutputTest(String input, String expected) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));

        Cookbook cookbook = new Cookbook();
        Parser parser = new Parser();

        while (parser.parseNextLine()) {
            Command command = parser.parseMaster();
            command.execute(cookbook);
        }

        assertEquals(expected, actual.toString());
    }

    @Test
    public void testAdd() {
        String input = "add Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix "
                + "/calories 200\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testDelete() {
        String input = "add Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix "
                + "/calories 200\n"
                + "add Ice Lemon Tea "
                + "/ingredients Ice+Lemon+Tea "
                + "/steps Add ingredients to glass+Mix "
                + "/calories 50\n"
                + "deleteRecipe 50\n"
                + "deleteRecipe xyz\n"
                + "deleteRecipe 1\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "Added Ice Lemon Tea recipe! Yum!" + System.lineSeparator()
                + "Ice Lemon Tea" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "50" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Ice" + System.lineSeparator()
                + "2. Lemon" + System.lineSeparator()
                + "3. Tea" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add ingredients to glass" + System.lineSeparator()
                + "2. Mix" + System.lineSeparator()
                +"GordonException: Index outside range." + System.lineSeparator()
                +"GordonException: Index is not a valid integer." + System.lineSeparator()
                +"OK! The recipe has been deleted from your cookbook." + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testList() {
        String input = "add Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix "
                + "/calories 200\n"
                + "add Ice Lemon Tea "
                + "/ingredients Ice+Lemon+Tea "
                + "/steps Add ingredients to glass+Mix "
                + "/calories 50\n"
                + "listRecipes\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "Added Ice Lemon Tea recipe! Yum!" + System.lineSeparator()
                + "Ice Lemon Tea" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "50" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Ice" + System.lineSeparator()
                + "2. Lemon" + System.lineSeparator()
                + "3. Tea" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add ingredients to glass" + System.lineSeparator()
                + "2. Mix" + System.lineSeparator()
                + "1. Chicken Rice" + System.lineSeparator()
                + "2. Ice Lemon Tea" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testCheck() {
        String input = "add Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix "
                + "/calories 200\n"
                + "check xyz\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "Finding recipes called xyz....." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "Finding recipes called chi....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testSetCalories() {
        String input = "add Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix "
                + "/calories 200\n"
                + "set chicken // / // 900\n"
                + "set cookies /calories 680\n"
                + "set chicken /calories xyz\n"
                + "set chicken /calories 400\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "GordonException: Command given cannot be recognized." + System.lineSeparator()
                + "Setting calories..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "GordonException: Index is not a valid integer." + System.lineSeparator()
                + "Setting calories..." + System.lineSeparator()
                + "Calories set successfully." + System.lineSeparator()
                + "Finding recipes called chi....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "400" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testSetDifficulty() {
        String input = "add Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix "
                + "/calories 200\n"
                + "set cookies /difficulty hard\n"
                + "set chicken /difficulty insane\n"
                + "set chicken /difficulty medium\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "Setting difficulty..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "GordonException: Please input a valid difficulty." + System.lineSeparator()
                + "Setting difficulty..." + System.lineSeparator()
                + "Difficulty set successfully." + System.lineSeparator()
                + "Finding recipes called chi....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Difficulty: " + System.lineSeparator()
                + "Medium" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testSetPrice() {
        String input = "add Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix "
                + "/calories 200\n"
                + "set cookies /price 12.80\n"
                + "set chicken /price a lot\n"
                + "set chicken /price 1.99999999\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "Setting price..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "GordonException: Index is not a valid float." + System.lineSeparator()
                + "Setting price..." + System.lineSeparator()
                + "Price set successfully." + System.lineSeparator()
                + "Finding recipes called chi....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "200" + System.lineSeparator()
                + "Total price of ingredients: " + System.lineSeparator()
                + "$2.00" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }
}
