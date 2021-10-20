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

    public static boolean parseStockIsDeleted(String[] splitStockDetails) {
        boolean stockIsDeleted = Boolean.parseBoolean(splitStockDetails[7]);
        return stockIsDeleted;
    }

    public static int parseOrderId(String[] splitOrderDetails) {
        int orderId = Integer.parseInt(splitOrderDetails[0]);
        return orderId;
    }

    public static String parseOrderName(String[] splitOrderDetails) {
        String orderName = splitOrderDetails[1];
        return orderName;
    }

    public static int parseOrderQuantity(String[] splitOrderDetails) {
        int orderQuantity = Integer.parseInt(splitOrderDetails[2]);
        return orderQuantity;
    }

    public static Date parseOrderDate(String[] splitOrderDetails) {
        try {
            String orderDateStr = splitOrderDetails[3];
            Date orderDate = DateParser.stringToDate(orderDateStr);
            return orderDate;
        } catch (ParseException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return null;
    }

    public static boolean parseOrderStatus(String[] splitOrderDetails) {
        String orderStatusStr = splitOrderDetails[4];
        boolean orderStatus = false;
        if (orderStatusStr.equalsIgnoreCase("DELIVERED")) {
            orderStatus = true;
        }
        return orderStatus;
    }

    public static int parseDispenseId(String[] splitDispenseDetails) {
        int dispenseId = Integer.parseInt(splitDispenseDetails[0]);
        return dispenseId;
    }

    public static String parseDispenseName(String[] splitDispenseDetails) {
        String dispenseName = splitDispenseDetails[1];
        return dispenseName;
    }

    public static int parseDispenseQuantity(String[] splitDispenseDetails) {
        int dispenseQuantity = Integer.parseInt(splitDispenseDetails[2]);
        return dispenseQuantity;
    }

    public static String parseDispenseCustomerId(String[] splitDispenseDetails) {
        String dispenseCustomerId = splitDispenseDetails[3];
        return dispenseCustomerId;
    }

    public static Date parseDispenseDate(String[] splitDispenseDetails) {
        try {
            String dispenseDateStr = splitDispenseDetails[4];
            Date dispenseDate = DateParser.stringToDate(dispenseDateStr);
            return dispenseDate;
        } catch (ParseException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return null;
    }

    public static String parseDispenseStaff(String[] splitDispenseDetails) {
        String dispenseStaff = splitDispenseDetails[5];
        return dispenseStaff;
    }

    public static int parseDispenseStockId(String[] splitDispenseDetails) {
        int dispenseStockId = Integer.parseInt(splitDispenseDetails[6]);
        return dispenseStockId;
    }
}
