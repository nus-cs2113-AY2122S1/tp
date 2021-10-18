package ui;

import command.CommandSyntax;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Order;
import inventory.Stock;
import parser.DateParser;
import parser.MedicineManager;

import java.util.ArrayList;

/**
 * Handles printing all messages in the application to the console.
 */

public class Ui {
    private static final int TABLE_PADDING = 2;
    private static final int DESCRIPTION_MAX_WIDTH = 45;

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
        printCommandSyntax(commandSyntax);
    }

    /**
     * Prints the missing parameter message.
     *
     * @param parameter     The parameter that is not found.
     * @param commandSyntax The command syntax of the command.
     */
    public void printRequiredParameter(String parameter, String commandSyntax) {
        print("Required parameter " + parameter + " is missing!");
        printCommandSyntax(commandSyntax);
    }

    /**
     * Prints the command syntax.
     *
     * @param commandSyntax The command syntax of the command.
     */
    public void printCommandSyntax(String commandSyntax) {
        print("COMMAND SYNTAX: " + commandSyntax);
    }

    /**
     * Prints out the medicine in a table format.
     *
     * @param stock Stock to be printed.
     */
    public void printStock(Stock stock) {
        ArrayList<Stock> stocks = new ArrayList<>();
        ArrayList<Medicine> medicines = new ArrayList<>();
        stocks.add(stock);
        printStocks(stocks, medicines);
    }

    /**
     * Prints out all the stocks in the Arraylist in a table format.
     *
     * @param stocks Arraylist of the stocks.
     * @param medicines Arraylist of the medicines.
     */
    public void printStocks(ArrayList<Stock> stocks, ArrayList<Medicine> medicines) {
        if (stocks.size() == 0) {
            print("There are no stocks found.");
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
        for (Stock stock : stocks) {
            idWidth = Math.max(String.valueOf(stock.getStockID()).length(), idWidth);
            nameWidth = Math.max(stock.getMedicineName().length(), nameWidth);
            priceWidth = Math.max(String.format("$%.2f", stock.getPrice()).length(), priceWidth);
            quantityWidth = Math.max(String.valueOf(stock.getQuantity()).length(), quantityWidth);
            int orderQuantity = MedicineManager.getTotalOrderQuantity(medicines, stock.getMedicineName());
            if (orderQuantity != 0) {
                quantityWidth = Math.max(("PENDING: " + orderQuantity).length(), quantityWidth);
            }
            expiryWidth = Math.max(DateParser.dateToString(stock.getExpiry()).length(), expiryWidth);
            descriptionWidth = Math.min(Math.max(stock.getDescription().length(), descriptionWidth),
                    DESCRIPTION_MAX_WIDTH);
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
        for (int i = 0; i < columnWidths.length; i++) {
            headers.append(String.format(formats[i], centerString(columnWidths[i], Stock.COLUMNS[i])));
        }

        printHeaderBorder(columnWidths);
        System.out.println(headers);
        printHeaderBorder(columnWidths);

        for (Stock stock : stocks) {
            String description = stock.getDescription();
            String truncatedDescription = truncateDescription(description, 0);
            int orderQuantity = MedicineManager.getTotalOrderQuantity(medicines, stock.getMedicineName());
            int descriptionIndex = truncatedDescription.length();

            String row = String.format(idFormat, centerString(idWidth, String.valueOf(stock.getStockID())))
                    + String.format(nameFormat, centerString(nameWidth, stock.getMedicineName()))
                    + String.format(priceFormat, centerString(priceWidth, String.format("$%.2f", stock.getPrice())))
                    + String.format(quantityFormat, centerString(quantityWidth, String.valueOf(stock.getQuantity())))
                    + String.format(expiryFormat, centerString(expiryWidth,
                    DateParser.dateToString(stock.getExpiry())))
                    + String.format(descriptionFormat, centerString(descriptionWidth, truncatedDescription))
                    + String.format(maxQuantityFormat, centerString(maxQuantityWidth,
                    String.valueOf(stock.getMaxQuantity())));

            while (descriptionIndex < description.length() || orderQuantity != 0) {
                truncatedDescription = truncateDescription(description, descriptionIndex);
                descriptionIndex += truncatedDescription.length();

                row += "\n" + String.format(idFormat, "") + String.format(nameFormat, "")
                        + String.format(priceFormat, "")
                        + String.format(quantityFormat, (orderQuantity == 0) ? "" : ("PENDING: " + orderQuantity))
                        + String.format(expiryFormat, "")
                        + String.format(descriptionFormat, centerString(descriptionWidth, truncatedDescription))
                        + String.format(maxQuantityFormat, "");
                orderQuantity = 0; // Reset the quantity count to prevent looping
            }
            System.out.println(row);
            printRowBorder(columnWidths);
        }
    }

    /**
     * Helps to truncate the description of the Stock and ensure that it does not break a word in the middle.
     * It will ensure that the truncated description returned contains valid words.
     *
     * @param description   Description of the Stock.
     * @param startingIndex The starting index to truncate the description.
     */
    private String truncateDescription(String description, int startingIndex) {
        String truncatedDescription = "";
        int descriptionIndex = Math.min(description.length(), startingIndex + DESCRIPTION_MAX_WIDTH);
        truncatedDescription = description.substring(startingIndex, descriptionIndex);

        String[] descriptionSplit = truncatedDescription.split("\\s"); // Split by spaces
        // Ensure that last word is not a space
        if (descriptionIndex < description.length()) {
            if (description.charAt(descriptionIndex) != ' ' && descriptionSplit.length > 1) {
                descriptionSplit[descriptionSplit.length - 1] = ""; // Remove the last partial word
            }
            truncatedDescription = String.join(" ", descriptionSplit);
        }
        return truncatedDescription;
    }

    /**
     * Prints the header borders for a table.
     *
     * @param columnWidths Array of the columns widths in the table.
     */
    private void printHeaderBorder(int[] columnWidths) {
        StringBuilder headerBorder = new StringBuilder();
        for (int columnWidth : columnWidths) {
            headerBorder.append("+");
            int width = columnWidth + TABLE_PADDING;
            headerBorder.append("=".repeat(width));
        }
        headerBorder.append("+");
        System.out.println(headerBorder);
    }

    /**
     * Prints the row borders for a table.
     *
     * @param columnWidths Array of the columns widths in the table.
     */
    private void printRowBorder(int[] columnWidths) {
        StringBuilder rowBorder = new StringBuilder();
        for (int columnWidth : columnWidths) {
            rowBorder.append("+");
            int width = columnWidth + TABLE_PADDING;
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
        System.out.println("Your current mode is indicated in the square brackets. It allows you to type add, list, "
                + "update, delete without typing in the full command.");
        System.out.println("Type stock, dispense or order to change to respective modes.");
        System.out.println("Note that parameters in {curly braces} are optional.");
        System.out.println("Parameters in [square braces] indicate that at least one of the parameter(s) must be "
                + "provided.");
        printHeaderBorder(columnWidths);
        System.out.println(headers);
        printHeaderBorder(columnWidths);

        for (CommandSyntax commandSyntax : commandSyntaxes) {
            String row = String.format(commandFormat, centerString(commandWidth, commandSyntax.getCommandName()))
                    + String.format(commandSyntaxFormat, commandSyntax.getCommandSyntax());
            System.out.println(row);
            printRowBorder(columnWidths);
        }
        System.out.println("For more information, refer to User Guide: https://ay2122s1-cs2113t-t10-1.github.io/tp/");
    }


    public void printOrder(Order order) {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        printOrders(orders);
    }

    /**
     * Prints out all the orders in the Arraylist in a table format.
     *
     * @param orders Arraylist of the orders.
     */
    public void printOrders(ArrayList<Order> orders) {
        if (orders.size() == 0) {
            print("There are no orders found.");
            return;
        }

        int idWidth = Order.COLUMNS[0].length();
        int nameWidth = Order.COLUMNS[1].length();
        int quantityWidth = Order.COLUMNS[2].length();
        int dateWidth = Order.COLUMNS[3].length();
        int statusWidth = Order.COLUMNS[4].length();

        // Find the longest width of each column
        for (Order order : orders) {
            idWidth = Math.max(String.valueOf(order.getOrderId()).length(), idWidth);
            nameWidth = Math.max(order.getMedicineName().length(), nameWidth);
            quantityWidth = Math.max(String.valueOf(order.getQuantity()).length(), quantityWidth);
            dateWidth = Math.max(DateParser.dateToString(order.getDate()).length(), dateWidth);
            statusWidth = Math.max(order.getStatus().length(), statusWidth);
        }

        int[] columnWidths = {idWidth, nameWidth, quantityWidth, dateWidth, statusWidth};

        // Pad the data in the columns
        String idFormat = "| %1$-" + idWidth + "s | ";
        String nameFormat = "%1$-" + nameWidth + "s | ";
        String quantityFormat = "%1$-" + quantityWidth + "s | ";
        String dateFormat = "%1$-" + dateWidth + "s | ";
        String statusFormat = "%1$-" + statusWidth + "s | ";

        String[] formats = {idFormat, nameFormat, quantityFormat, dateFormat, statusFormat};

        StringBuilder headers = new StringBuilder();
        for (int i = 0; i < columnWidths.length; i++) {
            headers.append(String.format(formats[i], centerString(columnWidths[i], Order.COLUMNS[i])));
        }

        printHeaderBorder(columnWidths);
        System.out.println(headers);
        printHeaderBorder(columnWidths);

        for (Order order : orders) {
            String row = String.format(idFormat, centerString(idWidth, String.valueOf(order.getOrderId())))
                    + String.format(nameFormat, centerString(nameWidth, order.getMedicineName()))
                    + String.format(quantityFormat, centerString(quantityWidth, String.valueOf(order.getQuantity())))
                    + String.format(dateFormat, centerString(dateWidth, DateParser.dateToString(order.getDate())))
                    + String.format(statusFormat, centerString(statusWidth, String.valueOf(order.getStatus())));
            System.out.println(row);
            printRowBorder(columnWidths);
        }
    }

    /**
     * Prints out all the medicines dispensed in a table format.
     *
     * @param dispenses Arraylist of the dispenses.
     */
    public void printDispenses(ArrayList<Dispense> dispenses) {
        if (dispenses.size() == 0) {
            print("There are no records of medicines dispensed.");
            return;
        }

        int idWidth = Dispense.COLUMNS[0].length();
        int nameWidth = Dispense.COLUMNS[1].length();
        int quantityWidth = Dispense.COLUMNS[2].length();
        int customerIdWidth = Dispense.COLUMNS[3].length();
        int dateWidth = Dispense.COLUMNS[4].length();
        int staffWidth = Dispense.COLUMNS[5].length();
        int stockIdWidth = Dispense.COLUMNS[6].length();

        // Find the longest width of each column
        for (Dispense dispense : dispenses) {
            idWidth = Math.max(String.valueOf(dispense.getDispenseId()).length(), idWidth);
            nameWidth = Math.max(dispense.getMedicineName().length(), nameWidth);
            quantityWidth = Math.max(String.valueOf(dispense.getQuantity()).length(), quantityWidth);
            customerIdWidth = Math.max(dispense.getCustomerId().length(), customerIdWidth);
            dateWidth = Math.max(DateParser.dateToString(dispense.getDate()).length(), dateWidth);
            staffWidth = Math.max(dispense.getStaff().length(), staffWidth);
            stockIdWidth = Math.max(String.valueOf(dispense.getStockId()).length(), stockIdWidth);
        }

        int[] columnWidths = {idWidth, nameWidth, quantityWidth, customerIdWidth, dateWidth, staffWidth, stockIdWidth};

        // Pad the data in the columns
        String idFormat = "| %1$-" + idWidth + "s | ";
        String nameFormat = "%1$-" + nameWidth + "s | ";
        String quantityFormat = "%1$-" + quantityWidth + "s | ";
        String customerIdFormat = "%1$-" + customerIdWidth + "s | ";
        String dateFormat = "%1$-" + dateWidth + "s | ";
        String staffFormat = "%1$-" + staffWidth + "s | ";
        String stockIdFormat = "%1$-" + stockIdWidth + "s | ";

        String[] formats = {idFormat, nameFormat, quantityFormat, customerIdFormat, dateFormat, staffFormat,
            stockIdFormat};

        StringBuilder headers = new StringBuilder();
        for (int i = 0; i < columnWidths.length; i++) {
            headers.append(String.format(formats[i], centerString(columnWidths[i], Dispense.COLUMNS[i])));
        }

        printHeaderBorder(columnWidths);
        System.out.println(headers);
        printHeaderBorder(columnWidths);

        for (Dispense dispense : dispenses) {
            String row = String.format(idFormat, centerString(idWidth, String.valueOf(dispense.getDispenseId())))
                    + String.format(nameFormat, centerString(nameWidth, dispense.getMedicineName()))
                    + String.format(quantityFormat, centerString(quantityWidth, String.valueOf(dispense.getQuantity())))
                    + String.format(customerIdFormat, centerString(customerIdWidth,
                    String.valueOf(dispense.getCustomerId())))
                    + String.format(dateFormat, centerString(dateWidth, DateParser.dateToString(dispense.getDate())))
                    + String.format(staffFormat, centerString(staffWidth, dispense.getStaff()))
                    + String.format(stockIdFormat, centerString(stockIdWidth, String.valueOf(dispense.getStockId())));
            System.out.println(row);
            printRowBorder(columnWidths);
        }
    }

    /**
     * Prints the exit message.
     */
    public void printExit() {
        print("Quitting Medivault...");
    }
}
