package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author EdwardZYWang
class CommandManagerTest {

    @Test
    void commandManager1() {
        String input = "workout leg day /at 23:59 /c 388";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_ADD_WORKOUT);
    }

    @Test
    void commandManager3() {
        String input = "addweight 30 12/12/2021";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_ADD_WEIGHT);
    }

    @Test
    void commandManager4() {
        String input = "bye";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_BYE);
    }

    @Test
    void commandManager5() {
        String input = "help";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.INPUT_HELP);
    }

    @Test
    void commandManager6() {
        String input = "deletemeal";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.DELETE_MEAL);
    }

    @Test
    void commandManager7() {
        String input = "listfluids";
        String[] result = input.trim().split(" ", 2);
        String command = result[0];
        assert command.equals(Keywords.LIST_DRINKS);
    }

    @Test
    void foodBankParser1() {
        String command = "";
        String inputArguments = "addfluid coke";
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        assert command.equals(Keywords.ADD_FLUID);
        assert inputArguments.equals("coke");
    }

    @Test
    void foodBankParser2() {
        String command = "";
        String inputArguments = "addfluid";
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        assert command.equals(Keywords.ADD_FLUID);
        assert inputArguments == null;
    }

    @Test
    void listParser() {
        String command = "";
        String date = "";
        String inputArguments = "meals 06/11/2022";
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        date = splitResults[1];
        assert command.equals(Keywords.MEALS);
        assert date.equals("06/11/2022");
    }

    @Test
    void listParser1() {
        String command = "";
        String date = "";
        String inputArguments = "meals";
        String[] splitResults = inputArguments.trim().split(" ", 2);
        command = splitResults[0];
        assert command.equals(Keywords.MEALS);
    }

    @Test
    void addParser1() {
        String command = "";
        String inputArguments = "";
        String input = "meals pasta /c 100 /d 06/11/2021 /t 23:59";
        String[] splitResults = input.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        assert command.equals(Keywords.MEALS);
    }

    @Test
    void addParser2() {
        String command = "";
        String inputArguments = "";
        String input = "meals";
        String[] splitResults = input.trim().split(" ", 2);
        command = splitResults[0];
        inputArguments = (splitResults.length == 2) ? splitResults[1] : null;
        assert command.equals(Keywords.MEALS);
        assert inputArguments == null;
    }

}