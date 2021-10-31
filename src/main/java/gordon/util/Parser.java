package gordon.util;

import gordon.command.Command;
import gordon.command.basic.AddCommand;
import gordon.command.basic.CheckCommand;
import gordon.command.basic.DeleteRecipeCommand;
import gordon.command.basic.ListRecipesCommand;
import gordon.command.basic.NullCommand;
import gordon.command.find.FindTimeCommand;
import gordon.command.find.FindCaloriesCommand;
import gordon.command.find.FindPriceCommand;
import gordon.command.find.FindTagsCommand;
import gordon.command.find.FindDifficultyCommand;
import gordon.command.find.FindIngredientsCommand;
import gordon.command.set.SetCaloriesCommand;
import gordon.command.set.SetDifficultyCommand;
import gordon.command.set.SetIngredientsCommand;
import gordon.command.set.SetPriceCommand;
import gordon.command.set.SetStepsCommand;
import gordon.command.set.SetTimeCommand;
import gordon.command.tag.TagAddCommand;
import gordon.command.tag.TagDeleteCommand;
import gordon.command.tag.TagUntagCommand;
import gordon.exception.GordonException;
import gordon.kitchen.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**.
 * <h1>Parser class</h1>
 *
 * <p>Reads in user input by implementing the {@code Scanner} class.</p>
 *
 * <p>The {@code parseMaster()} method parses through the user's input to find the best match for the user's intended
 * command.</p>
 *
 * <p>The corresponding command is then returned to the {@code Gordon} class.</p>
 *
 * <p>Prints responses to the console by implementing the {@code UI} class.</p>
 */
public class Parser {
    protected String nameRecipe;
    protected String line;
    protected Scanner in;

    public static final int NAME_INDEX = 0;
    public static final int INGREDIENTS_INDEX = 1;
    public static final int STEPS_INDEX = 2;
    public static final int INGREDIENTS_WORD_LENGTH = 12;
    public static final int STEPS_WORD_LENGTH = 6;
    
    public static final String ADD_RECIPE_PROMPT = "addRecipe";
    public static final String DELETE_RECIPE_PROMPT = "deleteRecipe";
    public static final String DELETE_TAG_PROMPT = "deleteTag";
    public static final String CHECK_PROMPT = "check";
    public static final String LIST_RECIPES_PROMPT = "listRecipes";
    public static final String LIST_TAGS_PROMPT = "listTags";
    public static final String SET_PROMPT = "set";
    public static final String FIND_PROMPT = "find";
    public static final String HELP_PROMPT = "help";
    public static final String TAG_PROMPT = "tag";
    public static final String UNTAG_PROMPT = "untag";
    public static final String EXIT_PROMPT = "exit";

    public static final String SET_FIND_INGREDIENTS_PROMPT = "ingredients";
    public static final String SET_FIND_STEPS_PROMPT = "steps";
    public static final String SET_FIND_CALORIES_PROMPT = "calories";
    public static final String SET_FIND_DIFF_PROMPT = "difficulty";
    public static final String SET_FIND_PRICE_PROMPT = "price";
    public static final String SET_FIND_TIME_PROMPT = "time";

    UI message = new UI();

    public Parser() {
        in = new Scanner(System.in);
    }

    /**.
     * <h2>Command parseMaster().</h2>
     *
     * <p>This method checks for keywords in the user's input, then returns the appropriate {@code Command}
     * to be executed.</p>
     *
     * <p>If the user's input is invalid, the {@code GordonException} will be thrown.</p>
     *
     * @return Command The corresponding command to be executed
     * @exception GordonException On invalid input
     * @see GordonException
     */
    public Command parseMaster() {
        try {
            String parsedCommand = parseCommand(line);
            if (parsedCommand.equalsIgnoreCase(ADD_RECIPE_PROMPT)) {
                return addRecipeParse();
            } else if (parsedCommand.equalsIgnoreCase(DELETE_RECIPE_PROMPT)) {
                return deleteRecipeParse();
            } else if (parsedCommand.equalsIgnoreCase(DELETE_TAG_PROMPT)) {
                return deleteTagParse();
            } else if (parsedCommand.equalsIgnoreCase(CHECK_PROMPT)) {
                return new CheckCommand(parseName(line));
            } else if (parsedCommand.equalsIgnoreCase(LIST_RECIPES_PROMPT)) {
                return new ListRecipesCommand(LIST_RECIPES_PROMPT);
            } else if (parsedCommand.equalsIgnoreCase(LIST_TAGS_PROMPT)) {
                return new ListRecipesCommand(LIST_TAGS_PROMPT);
            } else if (parsedCommand.equalsIgnoreCase(SET_PROMPT)) {
                return setParse();
            } else if (parsedCommand.equalsIgnoreCase(FIND_PROMPT)) {
                return findParse();
            } else if (parsedCommand.equalsIgnoreCase(HELP_PROMPT)) {
                message.printHelp();
            } else if (parsedCommand.equalsIgnoreCase(TAG_PROMPT)) {
                return addTagParse();
            } else if (parsedCommand.equalsIgnoreCase(UNTAG_PROMPT)) {
                return untagParse();
            } else {
                throw new GordonException(GordonException.COMMAND_INVALID);
            }
        } catch (GordonException e) {
            System.out.println("GordonException: " + e.getMessage());
        }
        return new NullCommand();
    }

    // used for JUnit purposes
    /**.
     * <h2>boolean parserHasNextLine().</h2>
     *
     * <p>This method checks whether the user's input has another line.</p>
     *
     * <p>It is used solely for JUnit testing.</p>
     *
     * @return boolean Whether the user's input has another line or not
     */
    public boolean parserHasNextLine() {
        return in.hasNextLine();
    }

    public boolean parseNextLine() {
        line = in.nextLine();
        return !line.trim().equalsIgnoreCase(EXIT_PROMPT);
    }

    public String parseCommand(String line) throws GordonException {
        int spaceIndex = line.indexOf(" ");
        if (spaceIndex == -1) {
            return line;
        } else {
            return line.substring(0, spaceIndex);
        }
    }

    public String parseName(String line) throws GordonException {
        int spaceIndex = line.indexOf(" ");

        if (spaceIndex == -1) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }

        return line.substring(spaceIndex).trim();
    }

    public void parseIngredients(String line, Recipe r) throws GordonException {
        int ingredientsIndex = line.indexOf(SET_FIND_INGREDIENTS_PROMPT);
        if (ingredientsIndex == -1) {
            throw new GordonException(GordonException.INGREDIENTS_FORMAT);
        }
        String newLine = line.substring(ingredientsIndex + INGREDIENTS_WORD_LENGTH);
        String[] ingredientsList = newLine.split("\\+");
        for (String s : ingredientsList) {
            r.addIngredient(s.trim());
        }
    }

    public void parseSteps(String line, Recipe r) throws GordonException {
        int stepsIndex = line.indexOf(SET_FIND_STEPS_PROMPT);
        if (stepsIndex == -1) {
            throw new GordonException(GordonException.STEPS_FORMAT);
        }
        String newLine = line.substring(stepsIndex + STEPS_WORD_LENGTH);
        String[] stepsList = newLine.split("\\+");
        for (String s : stepsList) {
            r.addStep(s.trim());
        }
    }

    public AddCommand addRecipeParse() throws GordonException {
        String[] splitContent = line.split("/");
        if (splitContent.length < 3) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }

        Recipe r = new Recipe(parseName(splitContent[NAME_INDEX]));
        parseIngredients(splitContent[INGREDIENTS_INDEX], r);
        parseSteps(splitContent[STEPS_INDEX], r);
        return new AddCommand(r);
    }

    public DeleteRecipeCommand deleteRecipeParse() throws GordonException {
        nameRecipe = parseName(line);
        String inputIndex = line.contains(" ") ? line.substring(line.indexOf(" ") + 1) : " ";
        if (inputIndex.isEmpty() || inputIndex.equals(" ")) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }
        try {
            int index = Integer.parseInt(inputIndex);
            assert index > 0 : "Your input should be a number greater than 0";
            return new DeleteRecipeCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new GordonException(GordonException.INDEX_INVALID);
        }
    }

    public Command setParse() throws GordonException {
        String[] splitContent = line.split("/");
        if (splitContent.length < 2) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }
        String recipeName = parseName(splitContent[0]);
        int spaceIndex = splitContent[1].indexOf(' ');
        if (spaceIndex < 0) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }
        String target = splitContent[1].substring(0, spaceIndex);
        switch (target.toLowerCase()) {
        case SET_FIND_INGREDIENTS_PROMPT:
            String newLine = splitContent[1].substring(spaceIndex + 1).trim();
            ArrayList<String> newIngredients = new ArrayList<>();
            Collections.addAll(newIngredients, newLine.split("\\+"));
            for (int i = 0; i < newIngredients.size(); i++) {
                newIngredients.set(i, newIngredients.get(i).trim());
            }
            return new SetIngredientsCommand(recipeName, newIngredients);
        case SET_FIND_STEPS_PROMPT:
            newLine = splitContent[1].substring(spaceIndex + 1).trim();
            ArrayList<String> newSteps = new ArrayList<>();
            Collections.addAll(newSteps, newLine.split("\\+"));
            for (int i = 0; i < newSteps.size(); i++) {
                newSteps.set(i, newSteps.get(i).trim());
            }
            return new SetStepsCommand(recipeName, newSteps);
        case SET_FIND_CALORIES_PROMPT:
            try {
                int cal = Integer.parseInt(splitContent[1].substring(spaceIndex + 1).trim());
                if (cal < -1) {
                    throw new GordonException(GordonException.INDEX_OOB);
                }
                return new SetCaloriesCommand(recipeName, cal);
            } catch (NumberFormatException e) {
                throw new GordonException(GordonException.INDEX_INVALID);
            }
        case SET_FIND_DIFF_PROMPT:
            Difficulty newDifficulty = null;
            String difficultyString = splitContent[1].substring(spaceIndex + 1).trim();
            for (Difficulty d : Difficulty.values()) {
                if (d.name().equalsIgnoreCase(difficultyString)) {
                    newDifficulty = d;
                }
            }
            if (newDifficulty == null) {
                throw new GordonException(GordonException.INVALID_DIFFICULTY);
            } else {
                return new SetDifficultyCommand(recipeName, newDifficulty);
            }
        case SET_FIND_PRICE_PROMPT:
            try {
                float price = Float.parseFloat(splitContent[1].substring(spaceIndex + 1).trim());
                if (price < -1 || (price > -1 && price < 0)) {
                    throw new GordonException(GordonException.INDEX_OOB);
                }
                return new SetPriceCommand(recipeName, price);
            } catch (NumberFormatException e) {
                throw new GordonException(GordonException.FLOAT_INVALID);
            }
        case SET_FIND_TIME_PROMPT:
            try {
                String[] splitTime = splitContent[1].substring(spaceIndex + 1).split(",");
                if (splitTime.length != 2) {
                    throw new GordonException(GordonException.COMMAND_INVALID);
                }
                int prepTime = Integer.parseInt(splitTime[0].trim());
                int cookTime = Integer.parseInt(splitTime[1].trim());
                if (prepTime < -1 || cookTime < -1) {
                    throw new GordonException(GordonException.INDEX_OOB);
                }
                return new SetTimeCommand(recipeName, prepTime, cookTime);
            } catch (NumberFormatException e) {
                throw new GordonException(GordonException.INDEX_INVALID);
            }
        default:
            throw new GordonException(GordonException.COMMAND_INVALID);
        }
    }

    public Command findParse() throws GordonException {
        String[] splitContent = line.split("/");
        assert (splitContent.length != 0);
        if (splitContent.length < 2) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }
        int spaceIndex = splitContent[1].indexOf(' ');
        if (spaceIndex < 0) {
            throw new GordonException(GordonException.COMMAND_INVALID);
        }
        String target = splitContent[1].substring(0, spaceIndex);
        switch (target) {
        case SET_FIND_INGREDIENTS_PROMPT:
            ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(splitContent[1]
                    .substring(spaceIndex + 1).split("\\+")));
            return new FindIngredientsCommand(ingredients);
        case SET_FIND_CALORIES_PROMPT:
            try {
                int cal = Integer.parseInt(splitContent[1].substring(spaceIndex + 1).trim());
                return new FindCaloriesCommand(cal);
            } catch (NumberFormatException e) {
                throw new GordonException(GordonException.INDEX_INVALID);
            }
        case SET_FIND_DIFF_PROMPT:
            Difficulty diff = null;
            String difficultyString = splitContent[1].substring(spaceIndex + 1).trim();
            for (Difficulty d : Difficulty.values()) {
                if (d.name().equalsIgnoreCase(difficultyString)) {
                    diff = d;
                }
            }
            if (diff == null) {
                throw new GordonException(GordonException.INVALID_DIFFICULTY);
            } else {
                return new FindDifficultyCommand(diff);
            }
        case SET_FIND_PRICE_PROMPT:
            try {
                float price = Float.parseFloat(splitContent[1].substring(spaceIndex + 1).trim());
                return new FindPriceCommand(price);
            } catch (NumberFormatException e) {
                throw new GordonException(GordonException.FLOAT_INVALID);
            }
        case TAG_PROMPT:
            ArrayList<String> tags = new ArrayList<>(Arrays.asList(splitContent[1]
                    .substring(spaceIndex + 1).split("\\+")));
            return new FindTagsCommand(tags);
        case SET_FIND_TIME_PROMPT:
            try {
                int time = Integer.parseInt(splitContent[1].substring(spaceIndex + 1).trim());
                return new FindTimeCommand(time);
            } catch (NumberFormatException e) {
                throw new GordonException(GordonException.INDEX_INVALID);
            }
        default:
            throw new GordonException(GordonException.COMMAND_INVALID);
        }
    }

    public Command addTagParse() throws GordonException {
        String[] splitContent = line.split("/");

        if (splitContent.length < 3) {
            throw new GordonException(GordonException.TAG_FORMAT_TOOSHORT);
        }

        String recipeName = splitContent[1].trim();
        String tagNames = splitContent[2].trim();
        String[] splitTagNames = tagNames.split("\\+");

        try {
            if (splitTagNames[0].trim().equals("")) {
                throw new GordonException(GordonException.TAG_FORMAT_NOTAGS);
            }

            if (recipeName.equals("")) {
                throw new GordonException(GordonException.EMPTY_RECIPE_NAME);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GordonException(GordonException.TAG_NONE_DETECTED);
        }

        return new TagAddCommand(recipeName, splitTagNames);
    }

    public Command deleteTagParse() throws GordonException {
        String[] splitContent = line.split("/");

        if (splitContent.length < 2) {
            throw new GordonException(GordonException.DELETETAG_FORMAT_TOOSHORT);
        }

        String tagNames = splitContent[1].trim();
        String[] splitTagNames = tagNames.split("\\+");

        try {
            if (splitTagNames[0].trim().equals("")) {
                throw new GordonException(GordonException.DELETETAG_FORMAT_NOTAGS);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GordonException(GordonException.TAG_NONE_DETECTED);
        }

        return new TagDeleteCommand(splitTagNames);
    }

    public Command untagParse() throws GordonException {
        String[] splitContent = line.split("/");

        if (splitContent.length < 3) {
            throw new GordonException(GordonException.UNTAG_FORMAT_TOOSHORT);
        }

        String recipeName = splitContent[1].trim();
        String tagNames = splitContent[2].trim();
        String[] splitTagNames = tagNames.split("\\+");

        try {
            if (splitTagNames[0].trim().equals("")) {
                throw new GordonException(GordonException.UNTAG_FORMAT_NOTAGS);
            }

            if (recipeName.equals("")) {
                throw new GordonException(GordonException.EMPTY_RECIPE_NAME);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GordonException(GordonException.TAG_NONE_DETECTED);
        }

        return new TagUntagCommand(recipeName, splitTagNames);
    }
}