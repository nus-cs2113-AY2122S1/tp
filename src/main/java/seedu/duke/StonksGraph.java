package seedu.duke;


public class StonksGraph {
    private static final int ROWS = 20;
    private static final int COLS = 100;
    private char[][] grid = new char [ROWS][COLS];



    public StonksGraph (char symbol) {
        setBorder(symbol);
        setTotalAmount(25.71);
        
        //It will call all the financial tracker methods here and whereever to print it
        //Need to account for case where user trolls and type long inputs (havent added)
    }
    
    private void setBorder(char symbol) {
        for(int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if(x == 0 || y == 0 || x == ROWS-1 || y == COLS-1) {
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
    
    private void writeToGraph(int xStart,int yStart, String toAdd) {
        int stringLength = toAdd.length();
        int i = 0;
        while(i < stringLength) {
            grid[xStart][yStart] = toAdd.charAt(i);
            yStart++;
            i++;
        }
    }
    
    
    public String convertGridToString() {
        String convertedString = "";
        for(int x = 0; x < ROWS; x++) {
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
