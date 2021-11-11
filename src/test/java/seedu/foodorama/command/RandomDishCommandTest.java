package seedu.foodorama.command;

import org.junit.jupiter.api.Test;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomDishCommandTest {

    @Test
    void execute() throws FoodoramaException {

        //initialise RandomDishCommand
        RandomDishCommand testCommand = new RandomDishCommand();

        //implement testing Carbohydrates ArrayList, execute test
        ArrayList<String> testCarbohydrates;
        testCarbohydrates = testCommand.getCarbohydrates();
        assertEquals(testCommand.getCarbohydrates().size(), testCarbohydrates.size());

        //implement testing Proteins ArrayList, execute test
        ArrayList<String> testProteins;
        testProteins = testCommand.getProteins();
        assertEquals(testCommand.getProteins().size(), testProteins.size());

        //implement testingSauces ArrayList, execute test
        ArrayList<String> testSauces;
        testSauces = testCommand.getSauces();
        assertEquals(testCommand.getSauces().size(), testSauces.size());

        //implement testing CookingMethods ArrayList, execute test
        ArrayList<String> testCookingMethods;
        testCookingMethods = testCommand.getCookingMethods();
        assertEquals(testCommand.getCookingMethods().size(), testCookingMethods.size());

    }
}
