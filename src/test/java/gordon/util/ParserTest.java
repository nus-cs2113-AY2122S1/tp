package gordon.util;

import gordon.command.Command;
import gordon.kitchen.Cookbook;
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
    public void testHelp() {
        String input = "help\n"
                + "exit\n";
        String expected = "1. Add a recipe: addRecipe \"recipe name\" \"/ingredients\" 1+2 \"/steps\" 1+2"
                + System.lineSeparator()
                + "2. Delete a recipe: deleteRecipe \"Index of recipe\"" + System.lineSeparator()
                + "3. List all your recipes: listRecipes" + System.lineSeparator()
                + "4. Find a recipe: find \"keyword\" \"number/item name\",where keyword is either /calories, /difficulty, /ingredients, /price, /tags or /time" + System.lineSeparator()
                + "5. Check a specific recipe: check \"Name of Recipe\"" + System.lineSeparator()
                + "6. Add calories to recipe: set \"recipe name\" \"/calories\" numberOfCalories "
                + System.lineSeparator()
                + "7. Add difficulty levels to recipe: set \"recipe name\" \"/difficulty\" difficultyLevel "
                + System.lineSeparator()
                + "8. Add cooking and preparation time to recipe: "
                + "set \"recipe name\" \"/time\" cookingTime \",\" preparationTime" + System.lineSeparator()
                + "9. Add price to recipe: set \"recipe name\" \"/price\" recipePrice " + System.lineSeparator()
                + "10. Tag a recipe: tag \"/ recipe name\" \"/ tagName1 + tagName2 + ...\"" + System.lineSeparator()
                + "11. Untag a recipe: untag \"/ recipe name\" \"/ tagName1 + tagName2 + ...\""
                + System.lineSeparator()
                + "12. Delete tags from Cookbook: deleteTag \"/ tagName1 + tagName2 + ...\""
                + System.lineSeparator()
                + "13. List all tags: listTags" + System.lineSeparator()
                + "14. Help me: help" + System.lineSeparator()
                + "" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testAdd() {
        String input = "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "GordonException: No duplicate recipe names allowed." + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testDelete() {
        String input = "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "addRecipe Ice Lemon Tea "
                + "/ingredients Ice+Lemon+Tea "
                + "/steps Add ingredients to glass+Mix\n"
                + "deleteRecipe 50\n"
                + "deleteRecipe xyz\n"
                + "deleteRecipe 1\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "Added Ice Lemon Tea recipe! Yum!" + System.lineSeparator()
                + "Ice Lemon Tea" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Ice" + System.lineSeparator()
                + "2. Lemon" + System.lineSeparator()
                + "3. Tea" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add ingredients to glass" + System.lineSeparator()
                + "2. Mix" + System.lineSeparator()
                + "GordonException: Index outside range." + System.lineSeparator()
                + "GordonException: Index is not a valid integer." + System.lineSeparator()
                + "OK! The recipe has been deleted from your cookbook." + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testList() {
        String input = "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "addRecipe Ice Lemon Tea "
                + "/ingredients Ice+Lemon+Tea "
                + "/steps Add ingredients to glass+Mix\n"
                + "listRecipes\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "Added Ice Lemon Tea recipe! Yum!" + System.lineSeparator()
                + "Ice Lemon Tea" + System.lineSeparator()
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
        String input = "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "check xyz\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
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
        String input = "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "set chicken // / // 900\n"
                + "set cookies /calories 680\n"
                + "set chicken /calories xyz\n"
                + "set chicken /calories 400\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "GordonException: You donkey! What are you talking about?" + System.lineSeparator()
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
        String input = "addRecipe Chicken Rice "
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
        String input = "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "set cookies /price 12.80\n"
                + "set chicken /price a lot\n"
                + "set chicken /price 1.99999999\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
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

    @Test
    public void testSetTimes() {
        String input = "addRecipe Chicken Rice "
                + "/ingredients Chicken+Rice "
                + "/steps Add chicken+Add rice+Mix\n"
                + "set cookies /time 10 20\n"
                + "set chicken /time 10, 20, 30, 40, 50, 60, 70\n"
                + "set chicken /time too long\n"
                + "set chicken /time 10, 25\n"
                + "check chi\n"
                + "exit\n";
        String expected = "Added Chicken Rice recipe! Yum!" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Chicken" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Add chicken" + System.lineSeparator()
                + "2. Add rice" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "GordonException: You donkey! What are you talking about?" + System.lineSeparator()
                + "Setting times..." + System.lineSeparator()
                + "Times set successfully." + System.lineSeparator()
                + "GordonException: You donkey! What are you talking about?" + System.lineSeparator()
                + "Setting times..." + System.lineSeparator()
                + "Times set successfully." + System.lineSeparator()
                + "Finding recipes called chi....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Chicken Rice" + System.lineSeparator()
                + "Preparation time: " + System.lineSeparator()
                + "10 minute(s)" + System.lineSeparator()
                + "Cooking time: " + System.lineSeparator()
                + "25 minute(s)" + System.lineSeparator()
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
    public void testFindCalories() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeA /calories 100\n"
                + "addRecipe recipeB "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeB /calories 40\n"
                + "addRecipe recipeC "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "find // // // //\n"
                + "find /calories xyz\n"
                + "find /calories 50\n"
                + "exit\n";
        String expected = "Added recipeA recipe! Yum!" + System.lineSeparator()
                + "recipeA" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting calories..." + System.lineSeparator()
                + "Calories set successfully." + System.lineSeparator()
                + "Added recipeB recipe! Yum!" + System.lineSeparator()
                + "recipeB" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting calories..." + System.lineSeparator()
                + "Calories set successfully." + System.lineSeparator()
                + "Added recipeC recipe! Yum!" + System.lineSeparator()
                + "recipeC" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "GordonException: You donkey! What are you talking about?" + System.lineSeparator()
                + "GordonException: Index is not a valid integer." + System.lineSeparator()
                + "Searching by calories..." + System.lineSeparator()
                + "1. recipeB (Calories (kcal): 40)" + System.lineSeparator()
                + "2. recipeC (Calories (kcal): Not set)" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testFindDifficulty() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeA /difficulty Medium\n"
                + "addRecipe recipeB "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "addRecipe recipeC "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeC /difficulty medium \n"
                + "find /difficulty xyz\n"
                + "find /difficulty medium\n"
                + "exit\n";
        String expected = "Added recipeA recipe! Yum!" + System.lineSeparator()
                + "recipeA" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting difficulty..." + System.lineSeparator()
                + "Difficulty set successfully." + System.lineSeparator()
                + "Added recipeB recipe! Yum!" + System.lineSeparator()
                + "recipeB" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Added recipeC recipe! Yum!" + System.lineSeparator()
                + "recipeC" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting difficulty..." + System.lineSeparator()
                + "Difficulty set successfully." + System.lineSeparator()
                + "GordonException: Please input a valid difficulty." + System.lineSeparator()
                + "Searching by difficulty..." + System.lineSeparator()
                + "1. recipeA (Difficulty: Medium)" + System.lineSeparator()
                + "2. recipeC (Difficulty: Medium)" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testFindIngredients() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "addRecipe recipeB "
                + "/ingredients b+c+d "
                + "/steps a+b+c\n"
                + "addRecipe recipeC "
                + "/ingredients d+a+b "
                + "/steps a+b+c\n"
                + "find /ingredients xyz\n"
                + "find /ingredients b+c\n"
                + "exit\n";
        String expected = "Added recipeA recipe! Yum!" + System.lineSeparator()
                + "recipeA" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Added recipeB recipe! Yum!" + System.lineSeparator()
                + "recipeB" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. b" + System.lineSeparator()
                + "2. c" + System.lineSeparator()
                + "3. d" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Added recipeC recipe! Yum!" + System.lineSeparator()
                + "recipeC" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. d" + System.lineSeparator()
                + "2. a" + System.lineSeparator()
                + "3. b" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "Searching by ingredient..." + System.lineSeparator()
                + "1. recipeA" + System.lineSeparator()
                + "2. recipeB" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testFindPrice() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "addRecipe recipeB "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeB /price 4.56\n"
                + "addRecipe recipeC "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeC /price 6.99\n"
                + "find /price xyz\n"
                + "find /price 5\n"
                + "exit\n";
        String expected = "Added recipeA recipe! Yum!" + System.lineSeparator()
                + "recipeA" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Added recipeB recipe! Yum!" + System.lineSeparator()
                + "recipeB" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting price..." + System.lineSeparator()
                + "Price set successfully." + System.lineSeparator()
                + "Added recipeC recipe! Yum!" + System.lineSeparator()
                + "recipeC" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting price..." + System.lineSeparator()
                + "Price set successfully." + System.lineSeparator()
                + "GordonException: Index is not a valid float." + System.lineSeparator()
                + "Searching by price..." + System.lineSeparator()
                + "1. recipeB (Price: $4.56)" + System.lineSeparator()
                + "2. recipeA (Price: Not set)" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testFindTimes() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeA /time 20, 40\n"
                + "addRecipe recipeB "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeB /time 50, 5\n"
                + "addRecipe recipeC "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "find /time xyz\n"
                + "find /time 59\n"
                + "exit\n";
        String expected = "Added recipeA recipe! Yum!" + System.lineSeparator()
                + "recipeA" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting times..." + System.lineSeparator()
                + "Times set successfully." + System.lineSeparator()
                + "Added recipeB recipe! Yum!" + System.lineSeparator()
                + "recipeB" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Setting times..." + System.lineSeparator()
                + "Times set successfully." + System.lineSeparator()
                + "Added recipeC recipe! Yum!" + System.lineSeparator()
                + "recipeC" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "GordonException: Index is not a valid integer." + System.lineSeparator()
                + "Searching by total time..." + System.lineSeparator()
                + "1. recipeB (Total Time: 55)" + System.lineSeparator()
                + "2. recipeC (Total Time: Not set)" + System.lineSeparator();
        inputOutputTest(input, expected);
    }
}
