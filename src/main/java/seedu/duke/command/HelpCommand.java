package seedu.duke.command;

public class HelpCommand implements Command {

    private static final String HELP_MESSAGE = "These are the commands I can currently carry out:\n"
            + "\t(intended action - command format)\n"
            + "\t1. add an ingredient - add n/INGREDIENT_NAME a/AMOUNT u/UNITS e/EXPIRY\n"
            + "\t2. list all ingredients - list\n"
            + "\t3. update an ingredient - update n/INGREDIENT_NAME a/AMOUNT u/UNITS e/EXPIRY\n"
            + "\t4. delete an ingredient - delete INDEX\n"
            + "\t5. view SITUS's current date - date\n"
            + "\t6. edit SITUS's current date - date yyyy-mm-dd\n"
            + "\t7. exit SITUS - exit";

    @Override
    public String run() {
        return HELP_MESSAGE;
    }
}
