
package seedu.situs.command;

public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "These are the commands I can currently carry out:\n"
            + "\t(intended action - command format)\n"
            + "\t1. add an ingredient - add n/INGREDIENT_NAME a/AMOUNT e/EXPIRY\n"
            + "\t2. subtract an ingredient - subtract n/INGREDIENT_NAME a/AMOUNT\n"
            + "\t3. list all ingredients - list\n"
            + "\t4. update an ingredient - update n/INGREDIENT_NAME a/AMOUNT e/EXPIRY\n"
            + "\t5. delete an ingredient - delete INDEX\n"
            + "\t6. view SITUS's current date - date\n"
            + "\t7. edit SITUS's current date - date yyyy-mm-dd\n"
            + "\t8. exit SITUS - exit";

    @Override
    public String run() {
        return HELP_MESSAGE;
    }
}