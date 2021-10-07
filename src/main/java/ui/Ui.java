package ui;

import command.CommandSyntax;
import inventory.Stock;
import inventory.Medicine;
import parser.DateParser;

import java.util.ArrayList;

/**
 * Handles printing all messages in the application to the console.
 */

public class Ui {
    private static final int TABLE_PADDING = 2;

    /**
     * Prints the welcome command message.
     */
    public void printWelcomeMessage() {
        String logo = "|  \\/  |          | |(_)| | | |              | || |  \n"
                + "| .  . |  ___   __| | _ | | | |  __ _  _   _ | || |_ \n"
                + "| |\\/| | / _ \\ / _` || || | | | / _` || | | || || __|\n"
                + "| |  | ||  __/| (_| || |\\ \\_/ /| (_| || |_| || || |_ \n"
                + "\\_|  |_/ \\___| \\__,_||_| \\___/  \\__,_| \\__,_||_| \\__|\n";
        print(logo + "Welcome to MediVault!");
    }

    /**
     * Prints the invalid command message.
     */
    public void printInvalidCommandMessage() {
        print("Sorry! I did not understand your command.");
    }

    /**
     * Prints the statements.
     *
     * @param statement Statement to be printed.
     */
    public void print(String statement) {
        System.out.println(statement);
    }

    /**
     * Prints the invalid parameter message.
     *
     * @param parameter     The invalid parameter found.
     * @param commandSyntax The command syntax of the command.
     */
    public void printInvalidParameter(String parameter, String commandSyntax) {
        if (parameter.equals("")) {
            print("Please provide all the required parameters!");
        } else {
            print("An invalid parameter " + parameter + " is provided!");
        }
        print("COMMAND SYNTAX: " + commandSyntax);
    }

    /**
     * Prints the missing parameter message.
     *
     * @param parameter     The parameter that is not found.
     * @param commandSyntax The command syntax of the command.
     */
    public void printRequiredParameter(String parameter, String commandSyntax) {
        print("Required parameter " + parameter + " is missing!");
        print("COMMAND SYNTAX: " + commandSyntax);
    }

    /**
     * Prints out the medicine in a table format.
     *
     * @param stock Medicine to be printed.
     */
    public void printStock(Stock stock) {
        ArrayList<Medicine> medicines = new ArrayList<>();
        medicines.add(stock);
        printStocks(medicines);
    }

    /**
     * Prints out all the stocks in the Arraylist in a table format.
     *
     * @param medicines Arraylist of the medicines.
     */
    public void printStocks(ArrayList<Medicine> medicines) {
        if (medicines.size() == 0) {
            print("There are no medicines found.");
            return;
        }
        int idWidth = Stock.COLUMNS[0].length();
        int nameWidth = Stock.COLUMNS[1].length();
        int priceWidth = Stock.COLUMNS[2].length();
        int quantityWidth = Stock.COLUMNS[3].length();
        int expiryWidth = Stock.COLUMNS[4].length();
        int descriptionWidth = Stock.COLUMNS[5].length();
        int maxQuantityWidth = Stock.COLUMNS[6].length();

        // Find the longest width of each column
        for (Medicine medicine : medicines) {
            Stock stock = (Stock) medicine;
            idWidth = Math.max(String.valueOf(stock.getStockID()).length(), idWidth);
            nameWidth = Math.max(stock.getMedicineName().length(), nameWidth);
            priceWidth = Math.max(String.format("$%.2f", stock.getPrice()).length(), priceWidth);
            quantityWidth = Math.max(String.valueOf(stock.getQuantity()).length(), quantityWidth);
            expiryWidth = Math.max(DateParser.dateToString(stock.getExpiry()).length(), expiryWidth);
            descriptionWidth = Math.max(stock.getDescription().length(), descriptionWidth);
            maxQuantityWidth = Math.max(String.valueOf(stock.getMaxQuantity()).length(), maxQuantityWidth);
        }

        int[] columnWidths = {idWidth, nameWidth, priceWidth, quantityWidth, expiryWidth, descriptionWidth,
                              maxQuantityWidth};

        // Pad the data in the columns
        String idFormat = "| %1$-" + idWidth + "s | ";
        String nameFormat = "%1$-" + nameWidth + "s | ";
        String priceFormat = "%1$-" + priceWidth + "s | ";
        String quantityFormat = "%1$-" + quantityWidth + "s | ";
        String expiryFormat = "%1$-" + expiryWidth + "s | ";
        String descriptionFormat = "%1$-" + descriptionWidth + "s | ";
        String maxQuantityFormat = "%1$-" + maxQuantityWidth + "s | ";

        String[] formats = {idFormat, nameFormat, priceFormat, quantityFormat, expiryFormat, descriptionFormat,
                            maxQuantityFormat};
        StringBuilder headers = new StringBuilder();
        for (int i = 0; i < Stock.NO_OF_COLUMNS; i++) {
            headers.append(String.format(formats[i], centerString(columnWidths[i], Stock.COLUMNS[i])));
        }

        printHeaderBorder(Stock.NO_OF_COLUMNS, columnWidths);
        System.out.println(headers);
        printHeaderBorder(Stock.NO_OF_COLUMNS, columnWidths);

        for (Medicine medicine : medicines) {
            Stock stock = (Stock) medicine;
            String row = String.format(idFormat, centerString(idWidth, String.valueOf(stock.getStockID())))
                    + String.format(nameFormat, centerString(nameWidth, stock.getMedicineName()))
                    + String.format(priceFormat, centerString(priceWidth, String.format("$%.2f", stock.getPrice())))
                    + String.format(quantityFormat, centerString(quantityWidth, String.valueOf(stock.getQuantity())))
                    + String.format(expiryFormat, centerString(expiryWidth,
                    DateParser.dateToString(stock.getExpiry())))
                    + String.format(descriptionFormat, centerString(descriptionWidth, stock.getDescription()))
                    + String.format(maxQuantityFormat, centerString(maxQuantityWidth,
                    String.valueOf(stock.getMaxQuantity())));
            System.out.println(row);
            printRowBorder(Stock.NO_OF_COLUMNS, columnWidths);
        }
    }

    /**
     * Prints the header borders for a table.
     *
     * @param noOfColumns  Number of columns in the table.
     * @param columnWidths Array of the columns widths in the table.
     */
    private void printHeaderBorder(int noOfColumns, int[] columnWidths) {
        StringBuilder headerBorder = new StringBuilder();
        for (int i = 0; i < noOfColumns; i++) {
            headerBorder.append("+");
            int width = Math.max(0, columnWidths[i] + TABLE_PADDING);
            headerBorder.append("=".repeat(width));
        }
        headerBorder.append("+");
        System.out.println(headerBorder);
    }

    /**
     * Prints the row borders for a table.
     *
     * @param noOfColumns  Number of columns in the table.
     * @param columnWidths Array of the columns widths in the table.
     */
    private void printRowBorder(int noOfColumns, int[] columnWidths) {
        StringBuilder rowBorder = new StringBuilder();
        for (int i = 0; i < noOfColumns; i++) {
            rowBorder.append("+");
            int width = columnWidths[i] + TABLE_PADDING;
            rowBorder.append("-".repeat(width));
        }
        rowBorder.append("+");
        System.out.println(rowBorder);
    }

    /**
     * Centralise a string given a width.
     *
     * @param width Width of the column.
     * @param s     String to be centralised.
     * @return The centralised string.
     */
    private String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    /**
     * Prints out help table with the accepted commands and their respective syntaxes.
     *
     * @param commandSyntaxes Arraylist of the commandSyntax to be printed.
     */
    public void printHelpMessage(ArrayList<CommandSyntax> commandSyntaxes) {
        int commandWidth = CommandSyntax.COMMAND.length();
        int commandSyntaxWidth = CommandSyntax.COMMAND_SYNTAX.length();
        for (CommandSyntax commandSyntax : commandSyntaxes) {
            commandWidth = Math.max(commandWidth, commandSyntax.getCommandName().length());
            commandSyntaxWidth = Math.max(commandSyntaxWidth, commandSyntax.getCommandSyntax().length());
        }
        int[] columnWidths = {commandWidth, commandSyntaxWidth};

        String commandFormat = "| %1$-" + commandWidth + "s | ";
        String commandSyntaxFormat = "%1$-" + commandSyntaxWidth + "s | ";
        String[] formats = {commandFormat, commandSyntaxFormat};

        StringBuilder headers = new StringBuilder();
        for (int i = 0; i < CommandSyntax.NO_OF_COLUMNS; i++) {
            headers.append(String.format(formats[i], centerString(columnWidths[i], CommandSyntax.COLUMNS[i])));
        }
        System.out.println("Welcome to the help page.");
        System.out.println("Note that parameters in {curly braces} are optional.");
        System.out.println("Parameters in [square braces] indicate that at least one of the parameter(s) must be "
                + "provided.");
        printHeaderBorder(CommandSyntax.NO_OF_COLUMNS, columnWidths);
        System.out.println(headers);
        printHeaderBorder(CommandSyntax.NO_OF_COLUMNS, columnWidths);

        for (CommandSyntax commandSyntax : commandSyntaxes) {
            String row = String.format(commandFormat, centerString(commandWidth, commandSyntax.getCommandName()))
                    + String.format(commandSyntaxFormat, centerString(commandSyntaxWidth,
                    commandSyntax.getCommandSyntax()));
            System.out.println(row);
            printRowBorder(CommandSyntax.NO_OF_COLUMNS, columnWidths);
        }
        System.out.println("For more information, refer to User Guide: https://ay2122s1-cs2113t-t10-1.github.io/tp/");
    }
}
