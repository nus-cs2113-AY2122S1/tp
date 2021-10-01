package ui;

import inventory.Medicine;
import inventory.Stock;
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
                + "\\_|  |_/ \\___| \\__,_||_| \\___/  \\__,_| \\__,_||_| \\__|";
        print(logo + "\nWelcome to MediVault!");
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
     * @param commandSyntax The commandSyntax of the command.
     */
    public void printInvalidParameter(String parameter, String commandSyntax) {
        if (parameter.equals("")) {
            print("No parameter is provided!");
        } else {
            print("An invalid parameter " + parameter + " is provided!");
        }
        print("COMMAND SYNTAX: " + commandSyntax);
    }

    /**
     * Prints out all the medicines in the Arraylist in a table format.
     *
     * @param medicines Arraylist of the medicines to be printed.
     */
    public void printMedicines(ArrayList<Stock> medicines) {
        if (medicines.size() == 0) {
            print("There are no medicines found.");
            return;
        }
        int idWidth = Medicine.COLUMNS[0].length();
        int nameWidth = Medicine.COLUMNS[1].length();
        int priceWidth = Medicine.COLUMNS[2].length();
        int quantityWidth = Medicine.COLUMNS[3].length();
        int expiryWidth = Medicine.COLUMNS[4].length();
        int descriptionWidth = Medicine.COLUMNS[5].length();
        int maxQuantityWidth = Medicine.COLUMNS[6].length();

        // Find the longest width of each column
        for (Stock stock : medicines) {
            Medicine medicine = (Medicine) stock;
            idWidth = Math.max(String.valueOf(medicine.getStockID()).length(), idWidth);
            nameWidth = Math.max(medicine.getMedicineName().length(), nameWidth);
            priceWidth = Math.max(String.format("$%.2f", medicine.getPrice()).length(), priceWidth);
            quantityWidth = Math.max(String.valueOf(medicine.getQuantity()).length(), quantityWidth);
            expiryWidth = Math.max(DateParser.dateToString(medicine.getExpiry()).length(), expiryWidth);
            descriptionWidth = Math.max(medicine.getDescription().length(), descriptionWidth);
            maxQuantityWidth = Math.max(String.valueOf(medicine.getMaxQuantity()).length(), maxQuantityWidth);
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
        for (int i = 0; i < Medicine.NO_OF_COLUMNS; i++) {
            headers.append(String.format(formats[i], centerString(columnWidths[i], Medicine.COLUMNS[i])));
        }

        printHeaderBorder(Medicine.NO_OF_COLUMNS, columnWidths);
        System.out.println(headers);
        printHeaderBorder(Medicine.NO_OF_COLUMNS, columnWidths);

        for (Stock stock : medicines) {
            Medicine medicine = (Medicine) stock;
            StringBuilder row = new StringBuilder();
            row.append(String.format(idFormat, centerString(idWidth, String.valueOf(medicine.getStockID()))));
            row.append(String.format(nameFormat, centerString(nameWidth, medicine.getMedicineName())));
            row.append(String.format(priceFormat, centerString(priceWidth,
                    String.format("$%.2f", medicine.getPrice()))));
            row.append(String.format(quantityFormat, centerString(quantityWidth,
                    String.valueOf(medicine.getQuantity()))));
            row.append(String.format(expiryFormat, centerString(expiryWidth,
                    DateParser.dateToString(medicine.getExpiry()))));
            row.append(String.format(descriptionFormat, centerString(descriptionWidth, medicine.getDescription())));
            row.append(String.format(maxQuantityFormat, centerString(maxQuantityWidth,
                    String.valueOf(medicine.getMaxQuantity()))));
            System.out.println(row);
            printRowBorder(Medicine.NO_OF_COLUMNS, columnWidths);
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
            int width = Math.max(0, columnWidths[i] + TABLE_PADDING);
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

}
