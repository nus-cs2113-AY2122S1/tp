package seedu.foodorama.command;

import org.junit.jupiter.api.Test;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomDishCommandTest {

    @Test
    void execute() throws FoodoramaException {

        //set up testCommand
        RandomDishCommand testCommand = new RandomDishCommand();

        // set up test ArrayLists
        ArrayList<String> testCarbohydrates;
        ArrayList<String> testProteins;
        ArrayList<String> testSauces;
        ArrayList<String> testCookingMethods;

        //assign test ArrayLists
        testCarbohydrates = testCommand.getCarbohydrates();
        testProteins = testCommand.getProteins();
        testSauces = testCommand.getSauces();
        testCookingMethods = testCommand.getCookingMethods();

        //checking
        assertEquals(testCommand.getCarbohydrates().size(), testCarbohydrates.size());
        assertEquals(testCommand.getProteins().size(), testProteins.size());
        assertEquals(testCommand.getSauces().size(), testSauces.size());
        assertEquals(testCommand.getCookingMethods().size(), testCookingMethods.size());

    }
}
