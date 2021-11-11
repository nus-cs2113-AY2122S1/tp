package seedu.foodorama;

import org.junit.jupiter.api.Test;
import seedu.foodorama.command.CommandNames;
import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParserTest {

    @Test
    void getCommandName() {
        Ui ui = new Ui();
        InputParser inputParser = new InputParser();
        String input1 = "list dish";
        String input2 = "not a command";
        try {
            CommandNames command = inputParser.getCommandName(input1);
            assertEquals("list", command.getName());
            command = inputParser.getCommandName(input2); //Should fail and throw exception
        } catch (FoodoramaException e) {
            assertEquals(ui.getInvalidCommandMsg(), e.getMessage());
        }

        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }

    @Test
    void getParameters() throws FoodoramaException {
        InputParser inputParser = new InputParser();
        //Define inputs
        //0 param command input
        String input0 = "help";
        //1 param command input
        String input1 = "add ingr chicken";
        //2 param command input
        //Split by slash
        String input2 = "link chicken rice / rice";
        //Split by space
        String input3 = "find dish chicken rice";
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add(input0);
        inputs.add(input1);
        inputs.add(input2);
        inputs.add(input3);


        //Define expected outputs
        ArrayList<ArrayList<String>> outputs = new ArrayList<>();
        //Zero
        ArrayList<String> expectedParamArrayList0 = new ArrayList<>();
        outputs.add(expectedParamArrayList0);
        //One
        ArrayList<String> expectedParamArrayList1 = new ArrayList<>();
        expectedParamArrayList1.add("chicken");
        outputs.add(expectedParamArrayList1);
        //Two slash
        String [] expectedParams2 = {"chicken rice", "rice"};
        ArrayList<String> expectedParamArrayList2 = new ArrayList<>();
        expectedParamArrayList2.addAll(List.of(expectedParams2));
        outputs.add(expectedParamArrayList2);
        //Two space
        String [] expectedParams3 = {"dish", "chicken rice"};
        ArrayList<String> expectedParamArrayList3 = new ArrayList<>();
        expectedParamArrayList3.addAll(List.of(expectedParams3));
        outputs.add(expectedParamArrayList3);

        for (int i = 0; i < inputs.size(); i++) {
            CommandNames command = inputParser.getCommandName(inputs.get(i));
            ArrayList parameters = inputParser.getParameters(inputs.get(i), command);
            assertEquals(outputs.get(i), parameters);
        }

        DishList.dishList.clear();
        IngredientList.ingredientList.clear();
    }
}