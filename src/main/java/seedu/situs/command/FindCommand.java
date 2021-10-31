package seedu.situs.command;

import seedu.situs.exceptions.SitusException;
import seedu.situs.ingredients.IngredientGroup;
import seedu.situs.ingredients.IngredientList;



import java.util.List;
import java.util.stream.Collectors;

//@@author nishantrai-nus

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
    public String run() throws SitusException {
        String resultMsg = "";
        List<IngredientGroup> searchResults = IngredientList.getIngredientList().stream()
                .filter(result -> result.getIngredientGroupName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        if (searchResults.isEmpty()) {
            resultMsg += NO_RESULTS_MESSAGE_START + keyword + NO_RESULTS_MESSAGE_END;
        } else {
            resultMsg += FIND_MESSAGE_START + keyword + FIND_MESSAGE_END;
            for (IngredientGroup group : searchResults) {
                resultMsg += LIST_NEWLINE_INDENT + (IngredientList.getIngredientList()
                        .indexOf(group) + 1) + ". " + group;
            }
        }
        return resultMsg;
    }
}

