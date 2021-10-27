
package seedu.situs.command;

public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "These are the commands I can currently carry out:\n"
            + "\t(intended action - command format)\n"
            + "\t1. add an ingredient - add n/INGREDIENT_NAME a/AMOUNT e/EXPIRY\n"
            + "\t2. list all ingredients - list\n"
            + "\t3. update an ingredient - update GROUP_INDEX.INGREDIENT_INDEX a/AMOUNT\n"
            + "\t4. subtract an ingredient's stock - subtract GROUP_INDEX a/AMOUNT\n"
            + "\t5. delete an ingredient - delete GROUP_INDEX.INGREDIENT_INDEX\n"
            + "\t6. search ingredients by expiry - expire DATE\n"
            + "\t7. search ingredients by name - find INGREDIENT_NAMES\n"
            + "\t8. view alerts - alert ALERT_TYPE\n"
            + "\t9. set alert thresholds - set ALERT_TYPE VALUE\n"
            + "\t10. view SITUS's current date - date\n"
            + "\t11. edit SITUS's current date - date DATE (in dd/mm/yyyy format)\n"
            + "\t12. exit SITUS - exit";

    @Override
    public String run() {
        return HELP_MESSAGE;
    }
}