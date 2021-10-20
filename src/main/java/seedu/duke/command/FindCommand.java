package seedu.duke.command;

import seedu.duke.exceptions.DukeException;
import seedu.duke.ingredients.Ingredient;
import seedu.duke.ingredients.IngredientList;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {

    private static final String FIND_MESSAGE_START = "I found these ingredients for \"";
    private static final String FIND_MESSAGE_END = "\":";
    private static final String LIST_NEWLINE_INDENT = "\n" + "\t";
    private static final String NO_RESULTS_MESSAGE_START = "I could not find any results for \"";
    private static final String NO_RESULTS_MESSAGE_END = "\"!";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String run() throws DukeException {
        String resultMsg = "";
        List<Ingredient> searchResults = IngredientList.getInstance().getIngredientList().stream()
                .filter(result -> result.getName().toLowerCase().contains(keyword)).collect(Collectors.toList());
        if (searchResults.isEmpty()) {
            resultMsg += NO_RESULTS_MESSAGE_START + keyword + NO_RESULTS_MESSAGE_END;
        } else {
            resultMsg += FIND_MESSAGE_START + keyword + FIND_MESSAGE_END;
            for (Ingredient ingredient : searchResults) {
                resultMsg += LIST_NEWLINE_INDENT + (IngredientList.getInstance().getIngredientList()
                        .indexOf(ingredient) + 1) + ". " + ingredient;
            }
        }
        return resultMsg;
    }
}
