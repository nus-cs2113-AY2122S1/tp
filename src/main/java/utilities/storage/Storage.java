package utilities.storage;

import errors.InvalidDataException;
import inventory.Dispense;
import inventory.Medicine;
import inventory.Order;
import utilities.parser.FileParser;
import inventory.Stock;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Storage class handles all saving and loading of data.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String STOCK_FILE_PATH = "data/stock.txt";
    private static final String ORDER_FILE_PATH = "data/order.txt";
    private static final String DISPENSE_FILE_PATH = "data/dispense.txt";
    private static final int NUMBER_OF_STOCK_DATA_FIELDS = 8;
    private static final int NUMBER_OF_ORDER_DATA_FIELDS = 5;
    private static final int NUMBER_OF_DISPENSE_DATA_FIELDS = 7;
    private static File stockFile;
    private static File orderFile;
    private static File dispenseFile;
    private static Storage storage = null;

    /**
     * Helps to create the Storage instance or returns the Storage instance if it exists.
     *
     * @return The Storage instance.
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * Constructor of Storage class.
     * Initializes myFile variable with file data/duke.txt.
     */
    public Storage() {
        stockFile = new File(STOCK_FILE_PATH);
        orderFile = new File(ORDER_FILE_PATH);
        dispenseFile = new File(DISPENSE_FILE_PATH);
    }

    /**
     * Save data into specific files after categorising them into Stock, Order and Dispense.
     */
    public void saveData(ArrayList<Medicine> medicines) {
        String stockData = "";
        String orderData = "";
        String dispenseData = "";
        for (Medicine medicine : medicines) {
            String data = medicine.toFileFormat() + System.lineSeparator();
            if (medicine instanceof Stock) {
                stockData += data;
            } else if (medicine instanceof Order) {
                orderData += data;
            } else if (medicine instanceof Dispense) {
                dispenseData += data;
            }
        }

        File dataDirectory = new File(DIRECTORY_PATH);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        try {
            stockFile.createNewFile();
            writeToFile(stockData, STOCK_FILE_PATH);
            orderFile.createNewFile();
            writeToFile(orderData, ORDER_FILE_PATH);
            dispenseFile.createNewFile();
            writeToFile(dispenseData, DISPENSE_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Write data into file corresponding files.
     *
     * @param data Data to be written into the file.
     * @throws IOException If unable to write into file.
     */
    private void writeToFile(String data, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }

    /**
     * Load saved data from data/stock.txt, data/order.txt, data/dispense.txt.
     */
    public ArrayList<Medicine> loadData() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        if (stockFile.exists()) {
            try {
                medicines.addAll(readFromFile("STOCK", stockFile));
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        if (orderFile.exists()) {
            try {
                medicines.addAll(readFromFile("ORDER", orderFile));
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        if (dispenseFile.exists()) {
            try {
                medicines.addAll(readFromFile("DISPENSE", dispenseFile));
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        return medicines;
    }

    /**
     * Read and process medicine details from corresponding file to restore medicine state in program.
     *
     * @param file File object of data/stock.txt, data/order.txt, or data/dispense.txt
     * @throws FileNotFoundException If file is not found.
     */
    private ArrayList<Medicine> readFromFile(String fileType, File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Medicine> medicines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String stockDetails = sc.nextLine();
            try {
                switch (fileType) {
                case "STOCK":
                    Medicine parsedStock = parseStockData(stockDetails);
                    medicines.add(parsedStock);
                    break;
                case "ORDER":
                    Medicine parsedOrder = parseOrderData(stockDetails);
                    medicines.add(parsedOrder);
                    break;
                case "DISPENSE":
                    Medicine parsedDispense = parseDispenseData(stockDetails);
                    medicines.add(parsedDispense);
                    break;
                default:
                    break;
                }
            } catch (InvalidDataException e) {
                System.out.println("Corrupted data detected in file"); // Maybe just log it instead of displaying?
            }
        }
        return medicines;
    }

    /**
     * Parse stock data and create a stock object based on it.
     *
     * @param stockDetails String of data of specific stock from file data/stock.txt.
     * @return Stock object for adding into medicines.
     */
    private Medicine parseStockData(String stockDetails) throws InvalidDataException {
        String[] splitStockDetails = stockDetails.split("\\|");
        if (splitStockDetails.length != NUMBER_OF_STOCK_DATA_FIELDS) { // If not all fields present.
            throw new InvalidDataException();
        }
        int stockID = FileParser.parseStockID(splitStockDetails);
        String stockName = FileParser.parseStockName(splitStockDetails);
        double stockPrice = FileParser.parseStockPrice(splitStockDetails);
        int stockQuantity = FileParser.parseStockQuantity(splitStockDetails);
        Date stockExpiry = FileParser.parseStockExpiry(splitStockDetails);
        String stockDescription = FileParser.parseStockDescription(splitStockDetails);
        int stockMaxQuantity = FileParser.parseStockMaxQuantity(splitStockDetails);
        boolean stockIsDeleted = FileParser.parseStockIsDeleted(splitStockDetails);

        Stock stock = new Stock(stockName, stockPrice, stockQuantity, stockExpiry, stockDescription, stockMaxQuantity);
        stock.setStockID(stockID);
        stock.setStockCount(stockID);
        stock.setDeleted(stockIsDeleted);
        return stock;
    }

    /**
     * Parse order data and create an order object based on it.
     *
     * @param orderDetails String of data of specific order from file data/order.txt.
     * @return Order object for adding into medicines.
     */
    private Medicine parseOrderData(String orderDetails) throws InvalidDataException {
        String[] orderStockDetails = orderDetails.split("\\|"); //what if call split when no delimiter
        if (orderStockDetails.length != NUMBER_OF_ORDER_DATA_FIELDS) { // If not all fields present.
            throw new InvalidDataException();
        }
        int orderId = FileParser.parseOrderId(orderStockDetails);
        String orderName = FileParser.parseOrderName(orderStockDetails);
        int orderQuantity = FileParser.parseOrderQuantity(orderStockDetails);
        Date orderDate = FileParser.parseOrderDate(orderStockDetails);
        boolean orderStatus = FileParser.parseOrderStatus(orderStockDetails);

        Order order = new Order(orderName, orderQuantity, orderDate);
        if (orderStatus) {
            order.setDelivered();
        }
        order.setOrderId(orderId);
        order.setOrderCount(orderId);
        return order;
    }

    /**
     * Parse dispense data and create a dispense object based on it.
     *
     * @param dispenseDetails String of data of specific stock from file data/dispense.txt.
     * @return Dispense object for adding into medicines.
     */
    private Medicine parseDispenseData(String dispenseDetails) throws InvalidDataException {
        String[] splitDispenseDetails = dispenseDetails.split("\\|");
        if (splitDispenseDetails.length != NUMBER_OF_DISPENSE_DATA_FIELDS) { // If not all fields present.
            throw new InvalidDataException();
        }
        int dispenseId = FileParser.parseDispenseId(splitDispenseDetails);
        String dispenseName = FileParser.parseDispenseName(splitDispenseDetails);
        int dispenseQuantity = FileParser.parseDispenseQuantity(splitDispenseDetails);
        String dispenseCustomerId = FileParser.parseDispenseCustomerId(splitDispenseDetails);
        Date dispenseDate = FileParser.parseDispenseDate(splitDispenseDetails);
        String dispenseStaff = FileParser.parseDispenseStaff(splitDispenseDetails);
        int dispenseStockId = FileParser.parseDispenseStockId(splitDispenseDetails);

        Dispense dispense = new Dispense(dispenseName, dispenseQuantity, dispenseCustomerId, dispenseDate,
                dispenseStaff, dispenseStockId);
        dispense.setDispenseId(dispenseId);
        dispense.setDispenseCount(dispenseId);
        return dispense;
    }
}
