package utilities.parser;

import errors.InvalidDataException;

import java.text.ParseException;
import java.util.Date;

/**
 * FileParser class handles all validation of input from data/stock.txt, data/order.txt, data/dispense.txt
 */
public class FileParser {

    /**
     * Perform validation of Stock Id during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock Id of integer data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseStockID(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        try {
            int stockId = Integer.parseInt(splitStockDetails[0]);
            if (stockId <= 0) {
                throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK ID [data/stock.txt]");
            }
            return stockId;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK ID [data/stock.txt]");
        }
    }

    /**
     * Perform validation of Stock name during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock name of String data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parseStockName(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        String stockName = splitStockDetails[1];
        if (stockName.equals("")) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK MEDICINE NAME [data/stock.txt]");
        }
        return stockName;
    }

    /**
     * Perform validation of Stock price during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock price of double data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static Double parseStockPrice(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        try {
            double stockPrice = Double.parseDouble(splitStockDetails[2]);
            if (stockPrice < 0) {
                throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK PRICE [data/stock.txt]");
            }
            return stockPrice;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK PRICE [data/stock.txt]");
        }
    }

    /**
     * Perform validation of Stock quantity during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock quantity of integer data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseStockQuantity(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        try {
            int stockQuantity = Integer.parseInt(splitStockDetails[3]);
            if (stockQuantity < 0) {
                throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK QUANTITY [data/stock.txt]");
            }
            return stockQuantity;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK QUANTITY [data/stock.txt]");
        }
    }

    /**
     * Perform validation of Stock expiry date during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock expiry date of Date data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static Date parseStockExpiry(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        try {
            String dateExpiryStr = splitStockDetails[4];
            Date stockExpiry = DateParser.stringToDate(dateExpiryStr);
            return stockExpiry;
        } catch (ParseException e) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK EXPIRY DATE [data/stock.txt]");
        }
    }

    /**
     * Perform validation of Stock description during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock description of String data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parseStockDescription(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        String stockDescription = splitStockDetails[5];
        if (stockDescription.equals("")) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK DESCRIPTION [data/stock.txt]");
        }
        return stockDescription;
    }

    /**
     * Perform validation of Stock max quantity during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock max quantity of integer data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseStockMaxQuantity(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        try {
            int stockMaxQuantity = Integer.parseInt(splitStockDetails[6]);
            if (stockMaxQuantity < 0) {
                throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK MAX QUANTITY [data/stock.txt]");
            }
            return stockMaxQuantity;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK MAX QUANTITY [data/stock.txt]");
        }
    }

    /**
     * Perform validation of Stock isDeleted field during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock isDeleted of boolean data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static boolean parseStockIsDeleted(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        String isDeleted = splitStockDetails[7];
        if (isDeleted.equalsIgnoreCase("TRUE") || isDeleted.equalsIgnoreCase("FALSE")) {
            boolean stockIsDeleted = Boolean.parseBoolean(splitStockDetails[7]);
            return stockIsDeleted;
        } else {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK ISDELETED [data/stock.txt]");
        }
    }

    /**
     * Perform validation of Order Id during parsing from file.
     *
     * @param splitOrderDetails Order details array fields split by delimiter '|'.
     * @param orderRow          Order row identifier for use in error message.
     * @return Order Id of integer data type for creation of Order object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseOrderId(String[] splitOrderDetails, int orderRow) throws InvalidDataException {
        try {
            int orderId = Integer.parseInt(splitOrderDetails[0]);
            if (orderId <= 0) {
                throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER ID [data/order.txt]");
            }
            return orderId;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER ID [data/order.txt]");
        }
    }

    /**
     * Perform validation of Order medication name during parsing from file.
     *
     * @param splitOrderDetails Order details array fields split by delimiter '|'.
     * @param orderRow          Order row identifier for use in error message.
     * @return Order medication name of String data type for creation of Order object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parseOrderName(String[] splitOrderDetails, int orderRow) throws InvalidDataException {
        String orderName = splitOrderDetails[1];
        if (orderName.equals("")) {
            throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER MEDICINE NAME [data/order.txt]");
        }
        return orderName;
    }

    /**
     * Perform validation of Order quantity during parsing from file.
     *
     * @param splitOrderDetails Order details array fields split by delimiter '|'.
     * @param orderRow          Order row identifier for use in error message.
     * @return Order quantity of integer data type for creation of Order object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseOrderQuantity(String[] splitOrderDetails, int orderRow) throws InvalidDataException {
        try {
            int orderQuantity = Integer.parseInt(splitOrderDetails[2]);
            if (orderQuantity <= 0) {
                throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER QUANTITY [data/order.txt]");
            }
            return orderQuantity;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER QUANTITY [data/order.txt]");
        }
    }

    /**
     * Perform validation of Order date during parsing from file.
     *
     * @param splitOrderDetails Order details array fields split by delimiter '|'.
     * @param orderRow          Order row identifier for use in error message.
     * @return Order date of Date data type for creation of Order object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static Date parseOrderDate(String[] splitOrderDetails, int orderRow) throws InvalidDataException {
        try {
            String orderDateStr = splitOrderDetails[3];
            Date orderDate = DateParser.stringToDate(orderDateStr);
            return orderDate;
        } catch (ParseException e) {
            throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER DATE [data/order.txt]");
        }
    }

    /**
     * Perform validation of Order status during parsing from file.
     *
     * @param splitOrderDetails Order details array fields split by delimiter '|'.
     * @param orderRow          Order row identifier for use in error message.
     * @return Order status of boolean data type for creation of Order object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static boolean parseOrderStatus(String[] splitOrderDetails, int orderRow) throws InvalidDataException {
        String orderStatusStr = splitOrderDetails[4];
        boolean orderStatus;
        if (orderStatusStr.equalsIgnoreCase("DELIVERED")) {
            orderStatus = true;
            return orderStatus;
        } else if (orderStatusStr.equalsIgnoreCase("PENDING")) {
            orderStatus = false;
            return orderStatus;
        } else {
            throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER STATUS [data/order.txt]");
        }
    }

    /**
     * Perform validation of Dispense Id during parsing from file.
     *
     * @param splitDispenseDetails Dispense details array fields split by delimiter '|'.
     * @param dispenseRow          Dispense row identifier for use in error message.
     * @return Dispense Id of integer data type for creation of Dispense object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseDispenseId(String[] splitDispenseDetails, int dispenseRow) throws InvalidDataException {
        try {
            int dispenseId = Integer.parseInt(splitDispenseDetails[0]);
            if (dispenseId <= 0) {
                throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE ID [data/dispense.txt]");
            }
            return dispenseId;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE ID [data/dispense.txt]");
        }
    }

    /**
     * Perform validation of Dispense medication name during parsing from file.
     *
     * @param splitDispenseDetails Dispense details array fields split by delimiter '|'.
     * @param dispenseRow          Dispense row identifier for use in error message.
     * @return Dispense medication name of String data type for creation of Dispense object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parseDispenseName(String[] splitDispenseDetails, int dispenseRow) throws InvalidDataException {
        String dispenseName = splitDispenseDetails[1];
        if (dispenseName.equals("")) {
            throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE MEDICATION NAME "
                    + "[data/dispense.txt]");
        }
        return dispenseName;
    }

    /**
     * Perform validation of Dispense quantity during parsing from file.
     *
     * @param splitDispenseDetails Dispense details array fields split by delimiter '|'.
     * @param dispenseRow          Dispense row identifier for use in error message.
     * @return Dispense quantity of integer data type for creation of Dispense object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseDispenseQuantity(String[] splitDispenseDetails, int dispenseRow) throws
            InvalidDataException {
        try {
            int dispenseQuantity = Integer.parseInt(splitDispenseDetails[2]);
            if (dispenseQuantity <= 0) {
                throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE QUANTITY"
                        + " [data/dispense.txt]");
            }
            return dispenseQuantity;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE QUANTITY [data/dispense.txt]");
        }
    }

    /**
     * Perform validation of Dispense customer id during parsing from file.
     *
     * @param splitDispenseDetails Dispense details array fields split by delimiter '|'.
     * @param dispenseRow          Dispense row identifier for use in error message.
     * @return Dispense customer id of String data type for creation of Dispense object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parseDispenseCustomerId(String[] splitDispenseDetails, int dispenseRow) throws
            InvalidDataException {
        String dispenseCustomerId = splitDispenseDetails[3];
        if (dispenseCustomerId.equals("")) {
            throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE CUSTOMER ID "
                    + "[data/dispense.txt]");
        }
        return dispenseCustomerId;
    }

    /**
     * Perform validation of Dispense date during parsing from file.
     *
     * @param splitDispenseDetails Dispense details array fields split by delimiter '|'.
     * @param dispenseRow          Dispense row identifier for use in error message.
     * @return Dispense date of Date data type for creation of Dispense object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static Date parseDispenseDate(String[] splitDispenseDetails, int dispenseRow) throws InvalidDataException {
        try {
            String dispenseDateStr = splitDispenseDetails[4];
            Date dispenseDate = DateParser.stringToDate(dispenseDateStr);
            return dispenseDate;
        } catch (ParseException e) {
            throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE DATE [data/dispense.txt]");
        }
    }

    /**
     * Perform validation of Dispense staff name during parsing from file.
     *
     * @param splitDispenseDetails Dispense details array fields split by delimiter '|'.
     * @param dispenseRow          Dispense row identifier for use in error message.
     * @return Dispense staff name of String data type for creation of Dispense object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parseDispenseStaff(String[] splitDispenseDetails, int dispenseRow) throws
            InvalidDataException {
        String dispenseStaff = splitDispenseDetails[5];
        if (dispenseStaff.equals("")) {
            throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE STAFF NAME "
                    + "[data/dispense.txt]");
        }
        return dispenseStaff;
    }

    /**
     * Perform validation of Dispense stock id during parsing from file.
     *
     * @param splitDispenseDetails Dispense details array fields split by delimiter '|'.
     * @param dispenseRow          Dispense row identifier for use in error message.
     * @return Dispense stock id of integer data type for creation of Dispense object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseDispenseStockId(String[] splitDispenseDetails, int dispenseRow) throws InvalidDataException {
        try {
            int dispenseStockId = Integer.parseInt(splitDispenseDetails[6]);
            if (dispenseStockId <= 0) {
                throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE STOCK ID "
                        + "[data/dispense.txt]");
            }
            return dispenseStockId;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + dispenseRow + "] INVALID DISPENSE STOCK ID "
                    + "[data/dispense.txt]");
        }
    }
}
