package utilities.parser;

import errors.InvalidDataException;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;

//@@author RemusTeo
/**
 * FileParser class handles all validation of input from data/stock.txt, data/order.txt, data/prescription.txt
 */
public class FileParser {
    private static HashSet<Integer> stockIds = new HashSet<>();
    private static HashSet<Integer> orderIds = new HashSet<>();
    private static HashSet<Integer> prescriptionIds = new HashSet<>();

    /**
     * Perform validation of Stock Id during parsing from file.
     *
     * @param splitStockDetails Stock details array fields split by delimiter '|'.
     * @param stockRow          Stock row identifier for use in error message.
     * @return Stock Id of integer data type for creation of Stock object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parseStockId(String[] splitStockDetails, int stockRow) throws InvalidDataException {
        try {
            int stockId = Integer.parseInt(splitStockDetails[0]);
            if (stockId <= 0) {
                throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK ID [data/stock.txt]");
            }
            if (stockIds.contains(stockId)) {
                throw new InvalidDataException("[ROW: " + stockRow + "] INVALID STOCK ID [data/stock.txt]");
            }
            stockIds.add(stockId);
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
            if (orderIds.contains(orderId)) {
                throw new InvalidDataException("[ROW: " + orderRow + "] INVALID ORDER ID [data/order.txt]");
            }
            orderIds.add(orderId);
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
     * Perform validation of Prescription Id during parsing from file.
     *
     * @param splitPrescriptionDetails Prescription details array fields split by delimiter '|'.
     * @param prescriptionRow          Prescription row identifier for use in error message.
     * @return Prescription Id of integer data type for creation of Prescription object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parsePrescriptionId(String[] splitPrescriptionDetails, int prescriptionRow)
            throws InvalidDataException {
        try {
            int prescriptionId = Integer.parseInt(splitPrescriptionDetails[0]);
            if (prescriptionId <= 0) {
                throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION "
                        + "ID [data/prescription.txt]");
            }
            if (prescriptionIds.contains(prescriptionId)) {
                throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION "
                        + "ID [data/prescription.txt]");
            }
            prescriptionIds.add(prescriptionId);
            return prescriptionId;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION "
                    + "ID [data/prescription.txt]");
        }
    }

    /**
     * Perform validation of Prescription medication name during parsing from file.
     *
     * @param splitPrescriptionDetails Prescription details array fields split by delimiter '|'.
     * @param prescriptionRow          Prescription row identifier for use in error message.
     * @return Prescription medication name of String data type for creation of Prescription object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parsePrescriptionName(String[] splitPrescriptionDetails, int prescriptionRow)
            throws InvalidDataException {
        String prescriptionName = splitPrescriptionDetails[1];
        if (prescriptionName.equals("")) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIBED MEDICATION NAME "
                    + "[data/prescription.txt]");
        }
        return prescriptionName;
    }

    /**
     * Perform validation of Prescription quantity during parsing from file.
     *
     * @param splitPrescriptionDetails Prescription details array fields split by delimiter '|'.
     * @param prescriptionRow          Prescription row identifier for use in error message.
     * @return Prescription quantity of integer data type for creation of Prescription object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parsePrescriptionQuantity(String[] splitPrescriptionDetails, int prescriptionRow) throws
            InvalidDataException {
        try {
            int prescriptionQuantity = Integer.parseInt(splitPrescriptionDetails[2]);
            if (prescriptionQuantity <= 0) {
                throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION QUANTITY"
                        + " [data/prescription.txt]");
            }
            return prescriptionQuantity;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION "
                    + "QUANTITY [data/prescription.txt]");
        }
    }

    /**
     * Perform validation of Prescription customer id during parsing from file.
     *
     * @param splitPrescriptionDetails Prescription details array fields split by delimiter '|'.
     * @param prescriptionRow          Prescription row identifier for use in error message.
     * @return Prescription customer id of String data type for creation of Prescription object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parsePrescriptionCustomerId(String[] splitPrescriptionDetails, int prescriptionRow) throws
            InvalidDataException {
        String prescriptionCustomerId = splitPrescriptionDetails[3];
        if (prescriptionCustomerId.equals("")) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION CUSTOMER ID "
                    + "[data/prescription.txt]");
        }
        return prescriptionCustomerId;
    }

    /**
     * Perform validation of Prescription date during parsing from file.
     *
     * @param splitPrescriptionDetails Prescription details array fields split by delimiter '|'.
     * @param prescriptionRow          Prescription row identifier for use in error message.
     * @return Prescription date of Date data type for creation of Prescription object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static Date parsePrescriptionDate(String[] splitPrescriptionDetails, int prescriptionRow)
            throws InvalidDataException {
        try {
            String prescriptionDateStr = splitPrescriptionDetails[4];
            Date prescriptionDate = DateParser.stringToDate(prescriptionDateStr);
            return prescriptionDate;
        } catch (ParseException e) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION DATE "
                    + "[data/prescription.txt]");
        }
    }

    /**
     * Perform validation of Prescription staff name during parsing from file.
     *
     * @param splitPrescriptionDetails Prescription details array fields split by delimiter '|'.
     * @param prescriptionRow          Prescription row identifier for use in error message.
     * @return Prescription staff name of String data type for creation of Prescription object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static String parsePrescriptionStaff(String[] splitPrescriptionDetails, int prescriptionRow) throws
            InvalidDataException {
        String prescriptionStaff = splitPrescriptionDetails[5];
        if (prescriptionStaff.equals("")) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION STAFF NAME "
                    + "[data/prescription.txt]");
        }
        return prescriptionStaff;
    }

    /**
     * Perform validation of Prescription stock id during parsing from file.
     *
     * @param splitPrescriptionDetails Prescription details array fields split by delimiter '|'.
     * @param prescriptionRow          Prescription row identifier for use in error message.
     * @return Prescription stock id of integer data type for creation of Prescription object.
     * @throws InvalidDataException Invalid data will trigger exception.
     */
    public static int parsePrescriptionStockId(String[] splitPrescriptionDetails, int prescriptionRow)
            throws InvalidDataException {
        try {
            int prescriptionStockId = Integer.parseInt(splitPrescriptionDetails[6]);
            if (prescriptionStockId <= 0) {
                throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION STOCK ID "
                        + "[data/prescription.txt]");
            }
            return prescriptionStockId;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID PRESCRIPTION STOCK ID "
                    + "[data/prescription.txt]");
        }
    }
}
