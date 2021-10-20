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
    private static final int NUMBER_OF_ORDER_DATA_FIELDS = 7;
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
     * Write data into file corresponding files.
     *
     * @param data Data to be written into the file.
     * @throws IOException If unable to write into file.
     */
    private void writeToFile(String data) throws IOException {
        FileWriter fw = new FileWriter(STOCK_FILE_PATH);
        fw.write(data);
        fw.close();
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
            writeToFile(stockData);
            orderFile.createNewFile();
            writeToFile(stockData);
            dispenseFile.createNewFile();
            writeToFile(stockData);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
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

    //    /**
    //     * Read and process medicine stock details from file to restore medicine stock state in program.
    //     *
    //     * @param file File object of data/order.txt.
    //     * @throws FileNotFoundException If file is not found.
    //     */
    //    private ArrayList<Medicine> readFromOrderFile(File file) throws FileNotFoundException {
    //        Scanner sc = new Scanner(file);
    //        ArrayList<Medicine> medicines = new ArrayList<>();
    //        while (sc.hasNextLine()) {
    //            String orderDetails = sc.nextLine();
    //            try {
    //                Medicine parsedOrder = parseOrderData(orderDetails);
    //                medicines.add(parsedOrder);
    //            } catch (InvalidDataException e) {
    //                System.out.println("Corrupted data detected in file"); // Maybe just log it instead of displaying?
    //            }
    //        }
    //        return medicines;
    //    }

    //    /**
    //     * Parse order data and create an order object based on it.
    //     *
    //     * @param orderDetails String of data of specific order from file data/order.txt.
    //     * @return Order object for adding into medicines.
    //     */
    //    private Medicine parseOrderData(String orderDetails) throws InvalidDataException {
    //        String[] orderStockDetails = orderDetails.split("\\|");
    //        if (orderStockDetails.length != NUMBER_OF_ORDER_DATA_FIELDS) { // If not all fields present.
    //            throw new InvalidDataException();
    //        }
    //
    //        Order order = new Order();
    //        return order;
    //    }

    /**
     * Read and process medicine order details from file to restore medicine stock state in program.
     *
     * @param file File object of data/stock.txt.
     * @throws FileNotFoundException If file is not found.
     */
    private ArrayList<Medicine> readFromStockFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Medicine> medicines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String stockDetails = sc.nextLine();
            try {
                Medicine parsedStock = parseStockData(stockDetails);
                medicines.add(parsedStock);
            } catch (InvalidDataException e) {
                System.out.println("Corrupted data detected in file"); // Maybe just log it instead of displaying?
            }
        }
        return medicines;
    }

    /**
     * Load saved data from data/stock.txt, data/order.txt, data/dispense.txt.
     */
    public ArrayList<Medicine> loadData() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        if (stockFile.exists()) {
            try {
                medicines.addAll(readFromStockFile(stockFile));
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        //        if (orderFile.exists()) {
        //            try {
        //                medicines.addAll(readFromFile(orderFile));
        //            } catch (FileNotFoundException e) {
        //                System.out.println("Something went wrong: " + e.getMessage());
        //            }
        //        }
        //        if (dispenseFile.exists()) {
        //            try {
        //                medicines.addAll(readFromFile(stockFile));
        //            } catch (FileNotFoundException e) {
        //                System.out.println("Something went wrong: " + e.getMessage());
        //            }
        //        }
        return medicines;
    }
}
