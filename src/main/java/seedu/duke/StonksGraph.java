package seedu.duke;


public class StonksGraph {
    private static final int ROWS = 20;
    private static final int COLS = 100;
    private static final int ROWS_OFFSET = ROWS - 1;
    private static final int COLS_OFFSET = COLS - 1;
    private char[][] grid = new char [ROWS][COLS];


    /**
     * It will call all the differnet methods here like balance, date(which mth), a bar in the middle(How many% full).
     * need to rmb to key in case where input is damm long(troll input).
     *
     * @param symbol the border's char
     */
    public StonksGraph(char symbol) {
        setBorder(symbol);
        setTotalAmount(25.71);
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
    
    private void setTotalAmount(double amount) {
        String stringAmount = Double.toString(amount);
        
        writeToGraph(2,4,"TOTAL AMOUNT: ");
        writeToGraph(2,19,stringAmount);
    }
    
    private void writeToGraph(int coordinateX,int coordinateY, String toAdd) {
        int stringLength = toAdd.length();
        int i = 0;
        while (i < stringLength) {
            grid[coordinateX][coordinateY] = toAdd.charAt(i);
            coordinateY++;
            i++;
        }
    }
    
    
    public String convertGridToString() {
        String convertedString = "";
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                convertedString += (String.format("%c",grid[x][y]));
            }
            convertedString += (System.lineSeparator());
        }
        return convertedString;
    }
    
    
    @Override
    public String toString() {
        return convertGridToString();
    }
}
