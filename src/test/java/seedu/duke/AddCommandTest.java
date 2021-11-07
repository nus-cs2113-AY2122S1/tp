package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.exceptions.LotsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    protected PeopleManager peopleManager = new PeopleManager();

    /**
     * This method sets up a list of 3 people with a food order each, before each test is carried out.
     */
    @BeforeEach
     void setUp() throws LotsException {
        String[] names = {"Adam", "Markus", "Andrew"};
        String[] foodIndex = {"2", "3", "4"};
        PeopleManager.clearListOfPeople();

        for (int i = 0; i < 3; i++) {
            String sampleInput = "add /n " + names[i] + " /i " + foodIndex[i] + " /q 1";
            Command command = new AddCommand(sampleInput);
            command.setData(peopleManager);
            command.execute();
        }
    }

    /**
     * Checks that same name will not be repeated.
     */
    @Test
    public void execute_sameNameInput_listSizeRemainsThree() throws LotsException {
        String input = "add /n Adam /i 3 /q 3";
        Command command = new AddCommand(input);
        command.setData(peopleManager);
        command.execute();
        assertEquals(3, peopleManager.getSize());
    }

    /**
     * Checks that when food index is negative, it will not be accepted.
     */
    @Test
    public void execute_foodIndexIsNegative_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i -1 /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Food Index is Negative. \n");
        }
    }

    /**
     * Checks that when food quantity is negative, it will not be accepted.
     */
    @Test
    public void execute_quantityIsNegative_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 2 /q -10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Quantity is Negative. \n");
        }
    }

    /**
     * Checks that when food index is zero, it will not be accepted.
     */
    @Test
    public void execute_foodIndexIsZero_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 0 /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Food Index is zero. \n");
        }
    }

    /**
     * Checks that when food quantity is zero, it will not be accepted.
     */
    @Test
    public void execute_quantityIsZero_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 2 /q 0");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Quantity is zero. \n");
        }
    }

    /**
     * Checks that when food index is not positively in range, it will not be accepted.
     */
    @Test
    public void execute_foodIndexPositiveButOutOfRange_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 133 /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Food Index Positive but out of range. \n");
        }
    }

    /**
     * Checks that when food quantity is not positively in range, it will not be accepted.
     */
    @Test
    public void execute_quantityPositiveButOutOfRange_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 2 /q 1000");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Quantity Positive but out of range. \n");
        }
    }

    /**
     * Checks that when format is wrong, exception will be thrown.
     */
    @Test
    public void execute_wrongNumberOfSlashes_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 2 ");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Missing Slash. \n");
        }
    }

    /**
     * Checks that when person name is not alphanumeric, it will throw an exception.
     */
    @Test
    public void execute_alphaNumericNamesOnly_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n J@cob! /i 2 /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("There are other characters in Names. \n");
        }
    }

    /**
     * Checks that when food index is not positively in range, it will not be accepted.
     */
    @Test
    public void execute_inputForNameIsSpaces_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n          /i 2 /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Name cannot be spaces. \n");
        }
    }

    /**
     * Checks that when food index is not positively in range, it will not be accepted.
     */
    @Test
    public void execute_inputForFoodIndexIsSpaces_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n Jenson /i     2    /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Food Index cannot be spaces. \n");
        }
    }

    /**
     * Checks that when quantity is spaces, it will not be accepted.
     */
    @Test
    public void execute_inputForQuantityIsSpaces_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n Jenson /i 3 /q        ");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Quantity cannot be spaces. \n");
        }
    }

    /**
     * Checks that when index is not integer, it will not be accepted.
     */
    @Test
    public void execute_foodIndexCannotBeInWord_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i Zero /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Food Index is in Words. \n");
        }
    }

    /**
     * Checks that when quantity is not index, it will not be accepted.
     */
    @Test
    public void execute_quantityCannotBeInWord_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 3 /q two hundred");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Quantity is in Words. \n");
        }
    }

    /**
     * Checks that when index contains special characters, it will not be accepted.
     */
    @Test
    public void execute_foodIndexContainsCharacters_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 2@1 /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Food Index contains characters. \n");
        }
    }

    /**
     * Checks that when quantity contains special characters, it will not be accepted.
     */
    @Test
    public void execute_quantityContainsCharacters_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n jacob /i 9 /q 2!4");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Quantity contains characters. \n");
        }
    }

    /**
     * Checks that when name contains more than 50 characters, it will not be accepted.
     */
    @Test
    public void execute_inputForNameIsMoreThan50Characters_throwsLotsException()
            throws Exception {
        try {
            Command command;
            command = Parser.getCommand("add /n Jenson Jacob Jenson Jacob Jenson Jacob Jenson Jacob Jenson Jacob "
                    + "Jenson Jacob Jenson Jacob Jenson Jacob Jenson /i 2 /q 10");
            command.execute();
        } catch (LotsException | NumberFormatException e) {
            System.out.println("Name cannot be more than 50 spaces. \n");
        }
    }

    /**
     * Checks that when number of people in the list will be exceeded, it will not be accepted.
     */
    @Test
    public void execute_numberOfPeopleExceedsLimit_throwsLotsException() throws Exception {
        try {
            PeopleManager.clearListOfPeople();
            for (int i = 0; i < 100; i++) {
                String sampleInput = "add /n " + i + " /i 1 /q 1";
                Command command = new AddCommand(sampleInput);
                command.setData(peopleManager);
                command.execute();
            }
        } catch (LotsException e) {
            System.out.println("Total number of people cannot be more than 99. \n");
        }
    }

    /**
     * Checks that when number of orders in the order list will be exceeded, it will not be accepted.
     */
    @Test
    public void execute_numberOfTotalOrdersExceedsLimit_throwsLotsException() throws Exception {
        try {
            Command command = new AddCommand("add /n Adam /i 1 /q 999");
            command.setData(peopleManager);
            command.execute();
        } catch (LotsException e) {
            System.out.println("Total number of orders cannot be more than 999. \n");
        }
    }
}
