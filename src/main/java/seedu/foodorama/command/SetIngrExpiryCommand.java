package seedu.foodorama.command;

import seedu.foodorama.IngredientList;
import seedu.foodorama.Ui;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

public class SetIngrExpiryCommand extends Command {
    private static final Ui UI = new Ui();

    @Override
    public void execute(ArrayList<String> parameters) throws FoodoramaException {
        int ingrIndex;
        if (isNumber(parameters.get(0))) {
            ingrIndex = Integer.parseInt(parameters.get(0)) - 1;
        } else {
            String ingrName = String.join(" ", parameters);
            if (ingrName.isBlank()) {
                throw new FoodoramaException(UI.getIngrIndexMissingMsg());
            } else {
                ingrIndex = IngredientList.find(ingrName);
            }
        }
        IngredientList.addExpiry(ingrIndex);
    }

    public boolean isNumber(String number) {
        try {
            int ingrIndex = Integer.parseInt(number) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

