package happybit.ui;

public class PrintTable {

    private static final String LS = System.lineSeparator();
    private static final String BAR_AND_SPACE = "| ";
    private static final String HORIZONTAL_SYMBOL = "-";
    private static final String BLANK = " ";

    private static final int FRONT_1_BACK_2_PADDING = 3;
    private static final int BACK_2_PADDING = 2;
    private static final int START_SINGLE_BAR = 1;

    /**
     * Prints data in a tabular format.
     *
     * @param headers 1D string array containing names of headers.
     * @param data    2D string array containing data.
     */
    public static void printTable(String[] headers, String[][] data) {
        int numOfRows = data.length;
        int numOfColumns = headers.length;
        int[] columnLengths = getColumnLengths(numOfRows, numOfColumns, headers, data);
        int totalLength = getTotalLength(columnLengths);
        String lineSeparator = HORIZONTAL_SYMBOL.repeat(totalLength);
        printHeaders(lineSeparator, headers, columnLengths, numOfColumns);
        printData(numOfRows, numOfColumns, columnLengths, data, lineSeparator);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Get the column lengths for the table.
     *
     * @param numOfRows    Number of data rows.
     * @param numOfColumns Number of data columns.
     * @param headers      1D string array containing names of headers.
     * @param data         2D string array containing data.
     * @return Integer array containing the column lengths.
     */
    private static int[] getColumnLengths(int numOfRows, int numOfColumns, String[] headers, String[][] data) {
        int[] columnLengths = new int[numOfColumns];
        int minimumLength;
        for (int columnIndex = 0; columnIndex < numOfColumns; columnIndex++) {
            minimumLength = headers[columnIndex].length() + FRONT_1_BACK_2_PADDING;
            columnLengths[columnIndex] = getMinimumLength(minimumLength, columnIndex, numOfRows, data);
        }
        return columnLengths;
    }

    /**
     * Get the minimum length that a column can be from the size of each data entry.
     *
     * @param minimumLength Minimum column length based off the column header.
     * @param columnIndex   Column index of the data.
     * @param numOfRows     Number of data rows.
     * @param data          2D string array containing data.
     * @return Minimum length of a column.
     */
    private static int getMinimumLength(int minimumLength, int columnIndex, int numOfRows, String[][] data) {
        for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
            int comparedLength = data[rowIndex][columnIndex].length() + BACK_2_PADDING;
            if (comparedLength > minimumLength) {
                minimumLength = comparedLength;
            }
        }
        return minimumLength;
    }

    /**
     * Get the total length for a row.
     *
     * @param columnLengths 1D array containing all column lengths.
     * @return Total length for a row.
     */
    private static int getTotalLength(int[] columnLengths) {
        int totalLength = START_SINGLE_BAR;
        for (int length : columnLengths) {
            totalLength += length + BACK_2_PADDING;
        }
        return totalLength;
    }

    /**
     * Prints the headers of the table.
     *
     * @param lineSeparator Line that separates the rows of the table.
     * @param headers       1D string array containing names of headers.
     * @param columnLengths 1D array containing all column lengths.
     * @param numOfColumns  Number of data columns.
     */
    private static void printHeaders(String lineSeparator, String[] headers, int[] columnLengths, int numOfColumns) {
        System.out.println(lineSeparator);
        System.out.print(BAR_AND_SPACE);
        for (int columnIndex = 0; columnIndex < numOfColumns; columnIndex++) {
            System.out.print(headers[columnIndex]);
            System.out.print(BLANK.repeat(columnLengths[columnIndex] - headers[columnIndex].length()));
            System.out.print(BAR_AND_SPACE);
        }
        System.out.println(LS + lineSeparator);
    }

    /**
     * Prints the data entries of the table.
     *
     * @param numOfRows     Number of data rows.
     * @param numOfColumns  Number of data columns.
     * @param columnLengths 1D array containing all column lengths.
     * @param data          2D string array containing data.
     * @param lineSeparator Line that separates the rows of the table.
     */
    private static void printData(int numOfRows, int numOfColumns, int[] columnLengths, String[][] data,
                                  String lineSeparator) {
        for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
            printRow(rowIndex, numOfColumns, columnLengths, data, lineSeparator);
        }
    }

    /**
     * Prints a row of the data entries.
     *
     * @param rowIndex      Column index of the data.
     * @param numOfColumns  Number of data columns.
     * @param columnLengths 1D array containing all column lengths.
     * @param data          2D string array containing data.
     * @param lineSeparator Line that separates the rows of the table.
     */
    private static void printRow(int rowIndex, int numOfColumns, int[] columnLengths, String[][] data,
                                 String lineSeparator) {
        System.out.print(BAR_AND_SPACE);
        for (int columnIndex = 0; columnIndex < numOfColumns; columnIndex++) {
            System.out.print(data[rowIndex][columnIndex]);
            System.out.print(BLANK.repeat(columnLengths[columnIndex] - data[rowIndex][columnIndex].length()));
            System.out.print(BAR_AND_SPACE);
        }
        System.out.println(LS + lineSeparator);
    }

}
