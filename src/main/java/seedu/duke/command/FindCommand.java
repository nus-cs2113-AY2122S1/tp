package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.util.List;

public class FindCommand implements Command {

    private static final String FIND_MESSAGE_START = "I found these ingredients for \"";
    private static final String FIND_MESSAGE_END = "\":";
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";

    private final String keyword;
    private final List<Ingredient> searchResults;

    public FindCommand(String keyword, List<Ingredient> searchResults) {
        this.keyword = keyword;
        this.searchResults = searchResults;
    }

    @Override
    public String run() throws DukeException {
        String resultMsg = "";
        resultMsg += FIND_MESSAGE_START + keyword + FIND_MESSAGE_END;
        for (Ingredient ingredient : searchResults) {
            resultMsg += LIST_NEWLINE_INDENT + (IngredientList.getInstance().getIngredientList()
                    .indexOf(ingredient) + 1) + ". " + ingredient;
        }
        return resultMsg;
    }
}
