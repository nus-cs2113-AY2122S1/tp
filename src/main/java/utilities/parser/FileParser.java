package utilities.parser;

import java.text.ParseException;
import java.util.Date;

public class FileParser {
    public static int parseStockID(String[] splitStockDetails) {
        int stockID = Integer.parseInt(splitStockDetails[0]);
        return stockID;
    }

    public static String parseStockName(String[] splitStockDetails) {
        String stockName = splitStockDetails[1];
        return stockName;
    }

    public static Double parseStockPrice(String[] splitStockDetails) {
        double stockPrice = Double.parseDouble(splitStockDetails[2]);
        return stockPrice;
    }

    public static int parseStockQuantity(String[] splitStockDetails) {
        int stockQuantity = Integer.parseInt(splitStockDetails[3]);
        return stockQuantity;
    }

    public static Date parseStockExpiry(String[] splitStockDetails) {
        try {
            String dateExpiryStr = splitStockDetails[4];
            Date stockExpiry = DateParser.stringToDate(dateExpiryStr);
            return stockExpiry;
        } catch (ParseException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return null;
    }

    public static String parseStockDescription(String[] splitStockDetails) {
        String stockDescription = splitStockDetails[5];
        return stockDescription;
    }

    public static int parseStockMaxQuantity(String[] splitStockDetails) {
        int stockMaxQuantity = Integer.parseInt(splitStockDetails[6]);
        return stockMaxQuantity;
    }
}
