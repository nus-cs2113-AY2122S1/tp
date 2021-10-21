package command;

/**
 * Contains all the valid command syntax accepted by the application. Also contains methods to validate if the
 * parameter and its value is valid for a given command.
 */

public class CommandSyntax {
    private String commandName;
    private String commandSyntax;
    public static final String COMMAND = "COMMAND";
    public static final String COMMAND_SYNTAX = "COMMAND SYNTAX";
    public static final String[] COLUMNS = {COMMAND, COMMAND_SYNTAX};
    public static final int NO_OF_COLUMNS = 2;

    public static final String ADD_DISPENSE_COMMAND = "adddispense n/NAME q/QUANTITY c/CUSTOMER_ID s/STAFF_NAME";
    public static final String ADD_STOCK_COMMAND = "addstock n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE "
            + "d/DESCRIPTION m/MAX_QUANTITY";
    public static final String ADD_ORDER_COMMAND = "addorder n/NAME q/QUANTITY {d/DATE}";
    public static final String DELETE_STOCK_COMMAND = "deletestock [i/ID e/EXPIRY_DATE]";
    public static final String DELETE_ORDER_COMMAND = "deleteorder i/ID";
    public static final String DELETE_DISPENSE_COMMAND = "deletedispense i/ID";
    public static final String EXIT_COMMAND = "exit";
    public static final String HELP_COMMAND = "help";
    public static final String LIST_DISPENSE_COMMAND = "listdispense {i/ID q/QUANTITY c/CUSTOMER_ID d/DATE "
            + "s/STAFF_NAME sid/STOCK_ID sort/COLUMN_NAME rsort/COLUMN NAME}";
    public static final String LIST_ORDER_COMMAND = "listorder {i/ID n/NAME q/QUANTITY d/DATE s/STATUS "
            + "sort/COLUMN_NAME rsort/COLUMN NAME}";
    public static final String LIST_STOCK_COMMAND = "liststock {i/ID p/PRICE q/QUANTITY e/EXPIRY_DATE "
            + "d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME rsort/COLUMN NAME}";
    public static final String PURGE_COMMAND = "purge";
    public static final String UPDATE_DISPENSE_COMMAND = "updatedispense i/ID [n/name q/QUANTITY c/CUSTOMER_ID "
            + "d/DATE s/STAFF_NAME]";
    public static final String UPDATE_ORDER_COMMAND = "updateorder i/ID [n/NAME q/QUANTITY d/DATE]";
    public static final String UPDATE_STOCK_COMMAND = "updatestock i/ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE "
            + "d/DESCRIPTION m/MAX_QUANTITY]";

    public CommandSyntax(String commandName, String commandSyntax) {
        this.commandName = commandName;
        this.commandSyntax = commandSyntax;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandSyntax() {
        return commandSyntax;
    }

    public void setCommandSyntax(String commandSyntax) {
        this.commandSyntax = commandSyntax;
    }

}
