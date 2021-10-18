package seedu.duke;


import seedu.utility.FinancialTracker;

import java.util.ArrayList;

public class StonksGraph {
    private static final int ROWS = 20;
    private static final int COLS = 100;
    private static final int ROWS_OFFSET = ROWS - 1;
    private static final int COLS_OFFSET = COLS - 1;
    private static final int BAR_VALUE = 200;
    private final char[][] grid = new char [ROWS][COLS];
    private static final char BORDER_CHAR = 'x';

    /**
     * It will call all the differnet methods here like balance, date(which mth), a bar in the middle(How many% full).
     * need to rmb to key in case where input is damm long(troll input).
     *
     */
    public StonksGraph(FinancialTracker finances) {
        setBorder(BORDER_CHAR);
        setBalance(finances.getBalance());
        setBar(finances);
    }
    
    private void setBorder(char symbol) {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (x == 0 || y == 0 || x == ROWS_OFFSET || y == COLS_OFFSET) {
                    grid[x][y] = symbol;
                } else {
                    grid[x][y] = ' ';
                }
            }
        }
    }
    
    private void setBalance(double amount) {
        String stringAmount = Double.toString(amount);
        
        writeToGraph(2,4,"TOTAL AMOUNT: ");
        writeToGraph(2,19, stringAmount);
    }


    private void writeToGraph(int rowCounnt,int colCount, String toAdd) {
        int stringLength = toAdd.length();
        int i = 0;
        while (i < stringLength) {
            grid[rowCounnt][colCount] = toAdd.charAt(i);
            colCount++;
            i++;
        }
    }
    
    
    public String convertGridToString() {
        String convertedString = "";
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                convertedString += (String.format("%c", grid[x][y]));
            }
            convertedString += (System.lineSeparator());
        }
        return convertedString;
    }
    
    
    @Override
    public String toString() {
        return convertGridToString();
    }

    /**
     * Returns month as a int base on which columm it is at.
     *
     * @param colCount the y - coordinate of the grid
     * @return Returns an integer that represents the month
     */
    private int getMonth(int colCount) {
        if (colCount >= 4 && colCount <= 6) {
            return 1;
        } else if (colCount >= 12 && colCount <= 14) {
            return 2;
        }  else if (colCount >= 20 && colCount <= 22) {
            return 3;
        }  else if (colCount >= 28 && colCount <= 30) {
            return 4;
        }  else if (colCount >= 36 && colCount <= 38) {
            return 5;
        }  else if (colCount >= 44 && colCount <= 46) {
            return 6;
        }  else if (colCount >= 52 && colCount <= 54) {
            return 7;
        }  else if (colCount >= 60 && colCount <= 62) {
            return 8;
        }  else if (colCount >= 68 && colCount <= 70) {
            return 9;
        }  else if (colCount >= 76 && colCount <= 78) {
            return 10;
        }  else if (colCount >= 84 && colCount <= 86) {
            return 11;
        } else {
            return 12;
        }
    }


    private void setBar(FinancialTracker finances) {
        writeToGraph(4,4, "Your Yearly Report");
        drawSeparator();
        drawLegend();
        drawXAxisLabels();
        drawXAxis();
        ArrayList<Double> monthlyIncomeBreakdowns = finances.getMonthlyIncomeBreakdown(2021);
        ArrayList<Double> monthlyExpenseBreakdowns = finances.getMonthlyExpenseBreakdown(2021);

        System.out.println("October total expense: " + monthlyExpenseBreakdowns.get(9));
        System.out.println("October total income: " + monthlyIncomeBreakdowns.get(9));
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {

                int monthIndex = getMonth(y) - 1;
                //Example totalincome 800/200 = 4 units of bar
                int incomeBar = (int)(monthlyIncomeBreakdowns.get(monthIndex) / BAR_VALUE);
                int expenseBar = (int)(monthlyExpenseBreakdowns.get(monthIndex) / BAR_VALUE);

                if (x >= 7 && x < 17) {
                    if (isExpenseBar(y)) {
                        setExpenseBar(x, y, expenseBar);
                    }
                    if (isIncomeBar(y)) {
                        setIncomeBar(x, y, incomeBar);
                    }
                }
            }
        }
    }

    private void drawLegend() {
        writeToGraph(2, 75, "Legend:");
        writeToGraph(3, 80, " # is Expense");
        writeToGraph(4, 80, " o is Expense");
    }

    private boolean isExpenseBar(int y) {
        return y == 4 || y == 12 || y == 20 || y == 28 || y == 36 || y == 44 || y == 52 || y == 60
                || y == 68 || y == 76 || y == 84 || y == 92;
    }

    private boolean isIncomeBar(int y) {
        return y == 5 || y == 13 || y == 21 || y == 29 || y == 37 || y == 45 || y == 53 || y == 61
                || y == 69 || y == 77 || y == 85 || y == 93;
    }

    private void drawXAxisLabels() {
        writeToGraph(18,4,"Jan");
        writeToGraph(18,12,"Feb");
        writeToGraph(18,20,"Mar");
        writeToGraph(18,28,"Apr");
        writeToGraph(18,36,"May");
        writeToGraph(18,44,"Jun");
        writeToGraph(18,52,"Jul");
        writeToGraph(18,60,"Aug");
        writeToGraph(18,68,"Sept");
        writeToGraph(18,76,"Oct");
        writeToGraph(18,84,"Nov");
        writeToGraph(18,92,"Dec");
    }

    private void drawXAxis() {
        for (int i = 2;i < 98; i++) {
            grid[17][i] = '~';
        }
    }

    private void drawSeparator() {
        for (int i = 2;i < 98; i++) {
            grid[5][i] = '-';
        }
    }

    private void setIncomeBar(int x, int y, int incomeBar) {
        if (x >= 17 - incomeBar && x < 17) {
            grid[x][y] = 'o';
        }
    }

    private void setExpenseBar(int x, int y, int expenseBar) {
        if (x >= 17 - expenseBar && x < 17) {
            grid[x][y] = '#';
        }
    }

}
