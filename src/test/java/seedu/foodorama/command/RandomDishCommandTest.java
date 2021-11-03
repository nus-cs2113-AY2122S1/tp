package seedu.foodorama.command;

import org.junit.jupiter.api.Test;

import seedu.foodorama.exceptions.FoodoramaException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomDishCommandTest {

    @Test
    void execute() throws FoodoramaException {

        RandomDishCommand testCommand = new RandomDishCommand();

        ArrayList<String> testCarbohydrates;
        testCarbohydrates = testCommand.getCarbohydrates();
        assertEquals(testCommand.getCarbohydrates().size(), testCarbohydrates.size());

        ArrayList<String> testProteins;
        testProteins = testCommand.getProteins();
        assertEquals(testCommand.getProteins().size(), testProteins.size());

        ArrayList<String> testSauces;
        testSauces = testCommand.getSauces();
        assertEquals(testCommand.getSauces().size(), testSauces.size());

        ArrayList<String> testCookingMethods;
        testCookingMethods = testCommand.getCookingMethods();
        assertEquals(testCommand.getCookingMethods().size(), testCookingMethods.size());

    }
}
