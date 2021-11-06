package gordon.kitchen;

import gordon.command.Command;
import gordon.exception.GordonException;
import gordon.util.Parser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagTest {

    public void inputOutputTest(String input, String expected) {
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
    public void testParseTag_FormatWrong1() {
        String input = "tag ////";
        String expected = formatException(GordonException.TAG_FORMAT_TOOSHORT);
        inputOutputTest(input,expected);
    }

    @Test
    public void testParseTag_FormatWrong2() {
        String input = "tag / /";
        String expected = formatException(GordonException.TAG_FORMAT_TOOSHORT);
        inputOutputTest(input,expected);
    }

    @Test
    public void testParseTag_EmptyRecipeName() {
        String input = "tag / /Hawker Food";
        String expected = formatException(GordonException.EMPTY_RECIPE_NAME);
        inputOutputTest(input,expected);
    }

    @Test
    public void testParseTag_NoRecipeExists() {
        String input = "tag / NoExistsFood /Hawker Food";
        String expected = formatException(GordonException.NO_RECIPE_FOUND);
        inputOutputTest(input,expected);
    }

    @Test
    public void testTag_NoDuplicateTagsAllowed() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Favorite"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food";
        String expected = formatException(GordonException.DUPLICATE_TAG_NAME);
        inputOutputTest(input,expected);
    }

    @Test
    public void testTag_MultipleRecipes() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "addRecipe Duck Rice / ingredients Duck /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice + Duck Rice / Hawker Food";
        String expected = formatException(GordonException.NO_RECIPE_FOUND);
        inputOutputTest(input,expected);
    }

    @Test
    public void testTag_ListTags() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "addRecipe Duck Rice / ingredients Duck /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Poultry + Favorite"
                + System.lineSeparator()
                + "tag / Duck Rice / Hawker Food + Poultry"
                + System.lineSeparator()
                + "listTags";
        String expected = "1. Hawker Food"
                + System.lineSeparator()
                + "2. Poultry"
                + System.lineSeparator()
                + "3. Favorite"
                + System.lineSeparator();
        inputOutputTest(input,expected);
    }

    @Test
    public void testFindTag_FormatWrong1() {
        String input = "find /tag";
        String expected = formatException(GordonException.COMMAND_INVALID);
        inputOutputTest(input,expected);
    }

    @Test
    public void testFindTag_NoTagExists() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "find /tag NoExistsTag";
        String expected = "Searching by tags..." + System.lineSeparator()
                + formatException(GordonException.NO_RESULT_FOUND);
        inputOutputTest(input,expected);
    }

    @Test
    public void testFindTag_MultipleCriteria() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "addRecipe Duck Rice / ingredients Duck /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Poultry + Favorite"
                + System.lineSeparator()
                + "tag / Duck Rice / Hawker Food + Poultry"
                + System.lineSeparator()
                + "find /tag Hawker Food + Poultry";
        String expected = "Searching by tags..."
                + System.lineSeparator()
                + "1. Chicken Rice"
                + System.lineSeparator()
                + "2. Duck Rice"
                + System.lineSeparator();
        inputOutputTest(input,expected);
    }

    @Test
    public void testUnTag_FormatWrong1() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Poultry + Favorite"
                + System.lineSeparator()
                + "untag / Chicken Rice / ++";
        String expected = formatException(GordonException.TAG_NONE_DETECTED);
        inputOutputTest(input,expected);
    }

    @Test
    public void testUnTag_FormatWrong2() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food"
                + System.lineSeparator()
                + "untag /  / Hawker Food";
        String expected = formatException(GordonException.EMPTY_RECIPE_NAME);
        inputOutputTest(input,expected);
    }

    @Test
    public void testUnTag_MultipleCriteria1() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Favorite + Poultry"
                + System.lineSeparator()
                + "untag / Chicken Rice / Hawker Food + Poultry"
                + System.lineSeparator()
                + "check Chicken Rice";
        String expected = "Finding recipes called Chicken Rice....."
                + System.lineSeparator()
                + "===================="
                + System.lineSeparator()
                + "Chicken Rice"
                + System.lineSeparator()
                + "Ingredients needed: "
                + System.lineSeparator()
                + "1. Chicken"
                + System.lineSeparator()
                + "Method: "
                + System.lineSeparator()
                + "1. Cook"
                + System.lineSeparator()
                + "Tags: "
                + System.lineSeparator()
                + "1. Favorite"
                + System.lineSeparator()
                + "===================="
                + System.lineSeparator();
        inputOutputTest(input,expected);
    }

    @Test
    public void testUnTag_MultipleCriteria2() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Favorite + Poultry"
                + System.lineSeparator()
                + "untag / Chicken Rice / Hawker Food + Poultry"
                + System.lineSeparator()
                + "listTags";
        String expected = "1. Hawker Food"
                + System.lineSeparator()
                + "2. Favorite"
                + System.lineSeparator()
                + "3. Poultry"
                + System.lineSeparator();
        inputOutputTest(input,expected);
    }

    @Test
    public void testUnTag_DifferentRecipeSameCriteria() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "addRecipe Duck Rice / ingredients Duck /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Poultry + Favorite"
                + System.lineSeparator()
                + "tag / Duck Rice / Hawker Food + Poultry"
                + System.lineSeparator()
                + "untag / Chicken Rice / Hawker Food + Poultry"
                + System.lineSeparator()
                + "check Duck Rice";
        String expected = "Finding recipes called Duck Rice....."
                + System.lineSeparator()
                + "===================="
                + System.lineSeparator()
                + "Duck Rice"
                + System.lineSeparator()
                + "Ingredients needed: "
                + System.lineSeparator()
                + "1. Duck"
                + System.lineSeparator()
                + "Method: "
                + System.lineSeparator()
                + "1. Cook"
                + System.lineSeparator()
                + "Tags: "
                + System.lineSeparator()
                + "1. Hawker Food"
                + System.lineSeparator()
                + "2. Poultry"
                + System.lineSeparator()
                + "===================="
                + System.lineSeparator();
        inputOutputTest(input,expected);
    }

    @Test
    public void testUnTag_PrintMessage() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "addRecipe Duck Rice / ingredients Duck /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Poultry + Favorite"
                + System.lineSeparator()
                + "tag / Duck Rice / Hawker Food + Poultry"
                + System.lineSeparator()
                + "untag / Duck Rice / Favorite"
                + System.lineSeparator();

        String expected = "Duck Rice does not have Favorite as a Tag" + System.lineSeparator();

        inputOutputTest(input, expected);
    }

    @Test
    public void testDeleteTag_WrongFormat1() {
        String input = "deleteTag /";
        String expected = formatException(GordonException.DELETETAG_FORMAT_TOOSHORT);
        inputOutputTest(input,expected);
    }

    @Test
    public void testDeleteTag_WrongFormat2() {
        String input = "deleteTag /++";
        String expected = formatException(GordonException.TAG_NONE_DETECTED);
        inputOutputTest(input,expected);
    }

    @Test
    public void testDeleteTag_WrongFormat3() {
        String input = "deleteTag / + +";
        String expected = formatException(GordonException.DELETETAG_FORMAT_NOTAGS);
        inputOutputTest(input,expected);
    }

    @Test
    public void testDeleteTag_MultipleCriteria() {
        String input = "addRecipe Chicken Rice /ingredients Chicken /steps Cook"
                + System.lineSeparator()
                + "addRecipe Duck Rice / ingredients Duck /steps Cook"
                + System.lineSeparator()
                + "tag / Chicken Rice / Hawker Food + Favorite"
                + System.lineSeparator()
                + "tag / Duck Rice / Hawker Food + Favorite + Poultry"
                + System.lineSeparator()
                + "deleteTag / Hawker Food + Favorite" + System.lineSeparator()
                + "check Duck Rice";
        String expected = "Finding recipes called Duck Rice....."
                + System.lineSeparator()
                + "===================="
                + System.lineSeparator()
                + "Duck Rice"
                + System.lineSeparator()
                + "Ingredients needed: "
                + System.lineSeparator()
                + "1. Duck"
                + System.lineSeparator()
                + "Method: "
                + System.lineSeparator()
                + "1. Cook"
                + System.lineSeparator()
                + "Tags: "
                + System.lineSeparator()
                + "1. Poultry"
                + System.lineSeparator()
                + "===================="
                + System.lineSeparator();
        inputOutputTest(input,expected);
    }

    @Test
    public void testTag_CaseSensitivity() {
        String input = "addRecipe Coffee /ingredients Beans /steps Brew"  + System.lineSeparator()
                + "addRecipe Tea / ingredients Leaves /steps Brew" + System.lineSeparator()
                + "tag / Coffee / favorites" + System.lineSeparator()
                + "tag / Tea / FAVORITES" + System.lineSeparator()
                + "untag / Coffee / FAVORITES" + System.lineSeparator()
                + "deleteTag / FAVORITES" + System.lineSeparator()
                + "check Coffee";

        String expected = "Finding recipes called Coffee....." + System.lineSeparator()
                + "====================" + System.lineSeparator()
                + "Coffee" + System.lineSeparator()
                + "Ingredients needed: " + System.lineSeparator()
                + "1. Beans" + System.lineSeparator()
                + "Method: " + System.lineSeparator()
                + "1. Brew" + System.lineSeparator()
                + "Tags: " + System.lineSeparator()
                + "1. favorites" + System.lineSeparator()
                + "====================" + System.lineSeparator();

        inputOutputTest(input,expected);
    }

}
