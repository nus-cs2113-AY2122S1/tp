package gordon.util;

import gordon.command.Command;
import gordon.exception.GordonException;
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

    public void inputOutputTestException(String input, String expected) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));

        Parser parser = new Parser();
        Cookbook cookbook = new Cookbook();

        while (parser.parserHasNextLine()) {
            actual.reset();
            parser.parseNextLine();
            Command command = parser.parseMaster();
            command.execute(cookbook);
        }

        assertEquals(expected, actual.toString());
    }

    public String formatException(String exceptionMessage) {
        return ("GordonException: "
                + exceptionMessage
                + System.lineSeparator());
    }

    @Test
    public void testHelp() {
        String input = "help\n"
                + "exit\n";
        String expected = "1. Add a recipe: addRecipe \"recipe name\" \"/ingredients\" 1+2 \"/steps\" 1+2"
                + System.lineSeparator()
                + "2. Delete a recipe: deleteRecipe \"Index of recipe\"" + System.lineSeparator()
                + "3. List all your recipes: listRecipes" + System.lineSeparator()
                + "4. Find a recipe: find \"keyword\" \"number/item name\","
                + "where keyword is either /calories, /difficulty, /ingredients, /price, /tags or /time"
                + System.lineSeparator()
                + "5. Check a specific recipe: check \"Name of Recipe\"" + System.lineSeparator()
                + "6. Add calories to recipe: set \"recipe name\" \"/calories\" numberOfCalories"
                + System.lineSeparator()
                + "7. Add difficulty levels to recipe: set \"recipe name\" \"/difficulty\" difficultyLevel"
                + System.lineSeparator()
                + "8. Add cooking and preparation time to recipe: "
                + "set \"recipe name\" \"/time\" cookingTime \",\" preparationTime" + System.lineSeparator()
                + "9. Add price to recipe: set \"recipe name\" \"/price\" recipePrice" + System.lineSeparator()
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
        String input = "addRecipe Duck Rice "
                + "/ingredients Duck+Rice+Oil "
                + "/steps Cook duck+Cook rice+Serve\n"
                + "addRecipe duck rice "
                + "/ingredients Duck+Rice "
                + "/steps Cook duck+Cook rice+Serve\n"
                + "exit\n";
        String expected = "Added Duck Rice recipe! Yum!" + System.lineSeparator()
                + "Duck Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Duck" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "3. Oil" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook duck" + System.lineSeparator()
                + "2. Cook rice" + System.lineSeparator()
                + "3. Serve" + System.lineSeparator()
                + "GordonException: No duplicate recipe names allowed." + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testAdd_noRecipeName() {
        String input = "addRecipe "
                + "/ingredients Duck+Rice+Oil "
                + "/steps Cook duck+Cook rice+Serve\n";

        String expected = formatException(GordonException.EMPTY_RECIPE_NAME);
        inputOutputTestException(input, expected);
    }

    @Test
    public void testAdd_noIngredientEntered() {
        String input = "addRecipe Duck Rice"
                + "/ingredients"
                + "/steps Cook duck+Cook rice+Serve\n";

        String expected = formatException(GordonException.EMPTY_INGREDIENT);
        inputOutputTestException(input, expected);
    }

    @Test
    public void testAdd_IngredientFieldEmpty() {
        String input = "addRecipe Duck Rice"
                + "/ingredients Duck+ "
                + "/steps Cook duck+Cook rice+Serve\n";

        String expected = formatException(GordonException.EMPTY_INGREDIENT);
        inputOutputTestException(input, expected);
    }

    @Test
    public void testAdd_noStepsEntered() {
        String input = "addRecipe Duck Rice"
                + "/ingredients Duck+Rice"
                + "/steps\n";

        String expected = formatException(GordonException.EMPTY_STEP);
        inputOutputTestException(input, expected);
    }

    @Test
    public void testAdd_StepFieldEmpty() {
        String input = "addRecipe Duck Rice"
                + "/ingredients Duck+Rice"
                + "/steps Roast Duck+ \n";

        String expected = formatException(GordonException.EMPTY_STEP);
        inputOutputTestException(input, expected);
    }

    @Test
    public void testDelete() {
        String input = "addRecipe Covfefe "
                + "/ingredients Water + Coffee Powder"
                + "/steps Boil water + Add powder to water\n"
                + "addRecipe Milo"
                + "/ingredients Water + Milo Powder"
                + "/steps Boil water + Add Milo powder to water\n"
                + "deleteRecipe 69\n"
                + "deleteRecipe goblin\n"
                + "deleteRecipe 1\n"
                + "exit\n";
        String expected = "Added Covfefe recipe! Yum!" + System.lineSeparator()
                + "Covfefe" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Water" + System.lineSeparator()
                + "2. Coffee Powder" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Boil water" + System.lineSeparator()
                + "2. Add powder to water" + System.lineSeparator()
                + "Added Milo recipe! Yum!" + System.lineSeparator()
                + "Milo" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Water" + System.lineSeparator()
                + "2. Milo Powder" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Boil water" + System.lineSeparator()
                + "2. Add Milo powder to water" + System.lineSeparator()
                + "GordonException: Index outside range." + System.lineSeparator()
                + "GordonException: Index is not a valid integer." + System.lineSeparator()
                + "OK! The recipe has been deleted from your cookbook." + System.lineSeparator();
        inputOutputTest(input, expected);
    }


    @Test
    public void testList() {
        String input = "addRecipe Fried Rice "
                + "/ingredients Eggs + Rice + MSG"
                + "/steps Cook rice + Fry Rice + Serve\n"
                + "addRecipe Mee Rebus"
                + "/ingredients Mee + Sauce + Half-Boiled Egg"
                + "/steps Cook egg + Boil noodles + Serve\n"
                + "listRecipes\n"
                + "exit\n";
        String expected = "Added Fried Rice recipe! Yum!" + System.lineSeparator()
                + "Fried Rice" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Eggs" + System.lineSeparator()
                + "2. Rice" + System.lineSeparator()
                + "3. MSG" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook rice" + System.lineSeparator()
                + "2. Fry Rice" + System.lineSeparator()
                + "3. Serve" + System.lineSeparator()
                + "Added Mee Rebus recipe! Yum!" + System.lineSeparator()
                + "Mee Rebus" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Mee" + System.lineSeparator()
                + "2. Sauce" + System.lineSeparator()
                + "3. Half-Boiled Egg" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook egg" + System.lineSeparator()
                + "2. Boil noodles" + System.lineSeparator()
                + "3. Serve" + System.lineSeparator()
                + "1. Fried Rice" + System.lineSeparator()
                + "2. Mee Rebus" + System.lineSeparator();
        inputOutputTest(input, expected);
    }


    @Test
    public void testCheck() {
        String input = "addRecipe Roti Prata "
                + "/ingredients Dough + Oil + Good chef "
                + "/steps Spread dough + Cook prata\n"
                + "check dogecoin\n"
                + "check Roti Prata\n"
                + "exit\n";
        String expected = "Added Roti Prata recipe! Yum!" + System.lineSeparator()
                + "Roti Prata" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Dough" + System.lineSeparator()
                + "2. Oil" + System.lineSeparator()
                + "3. Good chef" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Spread dough" + System.lineSeparator()
                + "2. Cook prata" + System.lineSeparator()
                + "Finding recipes called dogecoin....." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "Finding recipes called Roti Prata....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Roti Prata" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Dough" + System.lineSeparator()
                + "2. Oil" + System.lineSeparator()
                + "3. Good chef" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Spread dough" + System.lineSeparator()
                + "2. Cook prata" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testSetIngredients() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeZ /Ingredients x+y+z\n"
                + "set recipeA /Ingredients x + y +z     \n"
                + "check recipeA\n"
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
                + "Setting ingredients..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "Setting ingredients..." + System.lineSeparator()
                + "Ingredients set successfully." + System.lineSeparator()
                + "Finding recipes called recipeA....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "recipeA" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. x" + System.lineSeparator()
                + "2. y" + System.lineSeparator()
                + "3. z" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testSetSteps() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+c "
                + "/steps a+b+c\n"
                + "set recipeZ /Steps x+y+z\n"
                + "set recipeA /Steps x +y + z\n"
                + "check recipeA\n"
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
                + "Setting steps..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "Setting steps..." + System.lineSeparator()
                + "Steps set successfully." + System.lineSeparator()
                + "Finding recipes called recipeA....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "recipeA" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. a" + System.lineSeparator()
                + "2. b" + System.lineSeparator()
                + "3. c" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. x" + System.lineSeparator()
                + "2. y" + System.lineSeparator()
                + "3. z" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }


    @Test
    public void testSetCalories() {
        String input = "addRecipe Pasta Carbonara "
                + "/ingredients Pasta + Cream sauce + Cheese "
                + "/steps Cook pasta + Cook sauce + Mix + Grate cheese on top and serve\n"
                + "set Pasta Carbonara calories\n"
                + "set oreos /calories 420\n"
                + "set Pasta Carbonara /calories xyz\n"
                + "set Pasta Carbonara /calories 69420\n"
                + "check Pasta\n"
                + "exit\n";
        String expected = "Added Pasta Carbonara recipe! Yum!" + System.lineSeparator()
                + "Pasta Carbonara" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Pasta" + System.lineSeparator()
                + "2. Cream sauce" + System.lineSeparator()
                + "3. Cheese" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook pasta" + System.lineSeparator()
                + "2. Cook sauce" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "4. Grate cheese on top and serve" + System.lineSeparator()
                + "GordonException: Please use the format \"set RECIPE_NAME /ATTRIBUTE VALUE\"" + System.lineSeparator()
                + "Setting calories..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "GordonException: Index is not a valid integer." + System.lineSeparator()
                + "Setting calories..." + System.lineSeparator()
                + "Calories set successfully." + System.lineSeparator()
                + "Finding recipes called Pasta....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Pasta Carbonara" + System.lineSeparator()
                + "Calories (kcal): " + System.lineSeparator()
                + "69420" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Pasta" + System.lineSeparator()
                + "2. Cream sauce" + System.lineSeparator()
                + "3. Cheese" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook pasta" + System.lineSeparator()
                + "2. Cook sauce" + System.lineSeparator()
                + "3. Mix" + System.lineSeparator()
                + "4. Grate cheese on top and serve" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }



    @Test
    public void testSetDifficulty() {
        String input = "addRecipe Aglio Olio "
                + "/ingredients Pasta + Oil "
                + "/steps Cook pasta + Stir fry "
                + "/calories 666\n"
                + "set bolognese /difficulty easy\n"
                + "set Aglio Olio /difficulty gordon\n"
                + "set Aglio Olio /difficulty medium\n"
                + "check Aglio\n"
                + "exit\n";
        String expected = "Added Aglio Olio recipe! Yum!" + System.lineSeparator()
                + "Aglio Olio" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Pasta" + System.lineSeparator()
                + "2. Oil" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook pasta" + System.lineSeparator()
                + "2. Stir fry" + System.lineSeparator()
                + "Setting difficulty..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "GordonException: Please input a valid difficulty." + System.lineSeparator()
                + "Setting difficulty..." + System.lineSeparator()
                + "Difficulty set successfully." + System.lineSeparator()
                + "Finding recipes called Aglio....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Aglio Olio" + System.lineSeparator()
                + "Difficulty: " + System.lineSeparator()
                + "Medium" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Pasta" + System.lineSeparator()
                + "2. Oil" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook pasta" + System.lineSeparator()
                + "2. Stir fry" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }


    @Test
    public void testSetPrice() {
        String input = "addRecipe Myojo Noodles "
                + "/ingredients Hot water + Noodles + Seasoning "
                + "/steps Boil water + Add noodles + Add seasoning and serve\n"
                + "set salami /price 69.69\n"
                + "set Myojo Noodles/price dirt cheap\n"
                + "set Myojo Noodles/price 0.99999999\n"
                + "check Myojo\n"
                + "exit\n";
        String expected = "Added Myojo Noodles recipe! Yum!" + System.lineSeparator()
                + "Myojo Noodles" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Hot water" + System.lineSeparator()
                + "2. Noodles" + System.lineSeparator()
                + "3. Seasoning" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Boil water" + System.lineSeparator()
                + "2. Add noodles" + System.lineSeparator()
                + "3. Add seasoning and serve" + System.lineSeparator()
                + "Setting price..." + System.lineSeparator()
                + "GordonException: Search returns no result." + System.lineSeparator()
                + "GordonException: Index is not a valid float." + System.lineSeparator()
                + "Setting price..." + System.lineSeparator()
                + "Price set successfully." + System.lineSeparator()
                + "Finding recipes called Myojo....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Myojo Noodles" + System.lineSeparator()
                + "Total price of ingredients: " + System.lineSeparator()
                + "$1.00" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Hot water" + System.lineSeparator()
                + "2. Noodles" + System.lineSeparator()
                + "3. Seasoning" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Boil water" + System.lineSeparator()
                + "2. Add noodles" + System.lineSeparator()
                + "3. Add seasoning and serve" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }


    @Test
    public void testSetTimes() {
        String input = "addRecipe Bee Hoon Goreng "
                + "/ingredients Bee hoon + Eggs + Sauce "
                + "/steps Cook bee hoon + stir fry + serve\n"
                + "set salami /time 05 55\n"
                + "set Bee Hoon Goreng /time 10, 20, 30, 40, 50, 60, 70\n"
                + "set Bee Hoon Goreng /time too long\n"
                + "set Bee Hoon Goreng /time 69, 420\n"
                + "check Bee Hoon\n"
                + "exit\n";
        String expected = "Added Bee Hoon Goreng recipe! Yum!" + System.lineSeparator()
                + "Bee Hoon Goreng" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Bee hoon" + System.lineSeparator()
                + "2. Eggs" + System.lineSeparator()
                + "3. Sauce" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook bee hoon" + System.lineSeparator()
                + "2. stir fry" + System.lineSeparator()
                + "3. serve" + System.lineSeparator()
                + "GordonException: Please use the format \"set RECIPE_NAME /time PREP_TIME, COOK_TIME\""
                + System.lineSeparator()
                + "GordonException: Please use the format \"set RECIPE_NAME /time PREP_TIME, COOK_TIME\""
                + System.lineSeparator()
                + "GordonException: Please use the format \"set RECIPE_NAME /time PREP_TIME, COOK_TIME\""
                + System.lineSeparator()
                + "Setting times..." + System.lineSeparator()
                + "Times set successfully." + System.lineSeparator()
                + "Finding recipes called Bee Hoon....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Bee Hoon Goreng" + System.lineSeparator()
                + "Preparation time: " + System.lineSeparator()
                + "69 minute(s)" + System.lineSeparator()
                + "Cooking time: " + System.lineSeparator()
                + "420 minute(s)" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Bee hoon" + System.lineSeparator()
                + "2. Eggs" + System.lineSeparator()
                + "3. Sauce" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Cook bee hoon" + System.lineSeparator()
                + "2. stir fry" + System.lineSeparator()
                + "3. serve" + System.lineSeparator()
                + "====================" + System.lineSeparator();
        inputOutputTest(input, expected);
    }

    @Test
    public void testSetGranularity() {
        String input = "addRecipe Peanut Butter Sandwich"
                + "/ingredients Peanut+Butter+Bread"
                + "/steps Mix them\n"
                + "addRecipe Peanut Butter"
                + "/ingredients Peanut+Butter"
                + "/steps Mix them\n"
                + "set Peanut Butter/calories 400\n"
                + "find /calories 400\n"
                + "exit\n";

        String expected = "Added Peanut Butter Sandwich recipe! Yum!" + System.lineSeparator()
                + "Peanut Butter Sandwich" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Peanut" + System.lineSeparator()
                + "2. Butter" + System.lineSeparator()
                + "3. Bread" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Mix them" + System.lineSeparator()
                + "Added Peanut Butter recipe! Yum!" + System.lineSeparator()
                + "Peanut Butter" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Peanut" + System.lineSeparator()
                + "2. Butter" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Mix them" + System.lineSeparator()
                + "Setting calories..." + System.lineSeparator()
                + "Calories set successfully." + System.lineSeparator()
                + "Searching by calories..." + System.lineSeparator()
                + "1. Peanut Butter (Calories (kcal): 400)" + System.lineSeparator()
                + "2. Peanut Butter Sandwich (Calories (kcal): Not set)" + System.lineSeparator();

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

    @Test
    public void testNoDuplicateIngredients() {
        String input = "addRecipe recipeA "
                + "/ingredients a+b+a "
                + "/steps a+b+c\n";
        String expected = formatException(GordonException.DUPLICATE_INGREDIENT_NAME);
        inputOutputTestException(input, expected);
    }
}
