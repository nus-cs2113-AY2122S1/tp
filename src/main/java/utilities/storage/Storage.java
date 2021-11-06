package utilities.storage;

import errors.InvalidDataException;
import inventory.Prescription;
import inventory.Medicine;
import inventory.Order;
import utilities.parser.FileParser;
import inventory.Stock;
import utilities.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//@@author RemusTeo

/**
 * Storage class handles all saving and loading of data.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String STOCK_FILE_PATH = "data/stock.txt";
    private static final String ORDER_FILE_PATH = "data/order.txt";
    private static final String PRESCRIPTION_FILE_PATH = "data/prescription.txt";
    private static final String ORDER_ARCHIVE_FILE_PATH = "data/order_archive.txt";
    private static final String PRESCRIPTION_ARCHIVE_FILE_PATH = "data/prescription_archive.txt";
    private static final String DELIMITER = "\\|";
    private static final int NUMBER_OF_STOCK_DATA_FIELDS = 8;
    private static final int NUMBER_OF_ORDER_DATA_FIELDS = 5;
    private static final int NUMBER_OF_PRESCRIPTION_DATA_FIELDS = 7;
    private static File stockFile;
    private static File orderFile;
    private static File prescriptionFile;
    private static File orderArchiveFile;
    private static File prescriptionArchiveFile;
    private static Storage storage = null;
    int highestStockId = 0;
    int highestOrderId = 0;
    int highestPrescriptionId = 0;


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
     * Initializes all files objects.
     */
    public Storage() {
        stockFile = new File(STOCK_FILE_PATH);
        orderFile = new File(ORDER_FILE_PATH);
        prescriptionFile = new File(PRESCRIPTION_FILE_PATH);
        orderArchiveFile = new File(ORDER_ARCHIVE_FILE_PATH);
        prescriptionArchiveFile = new File(PRESCRIPTION_ARCHIVE_FILE_PATH);
    }

    /**
     * Save data into specific files after categorising them into Stock, Order and Prescription.
     */
    public void saveData(ArrayList<Medicine> medicines) {
        String stockData = "";
        String orderData = "";
        String prescriptionData = "";
        for (Medicine medicine : medicines) {
            String data = medicine.toFileFormat() + System.lineSeparator();
            if (medicine instanceof Stock) {
                stockData += data;
            } else if (medicine instanceof Order) {
                orderData += data;
            } else if (medicine instanceof Prescription) {
                prescriptionData += data;
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
            prescriptionFile.createNewFile();
            writeToFile(prescriptionData, PRESCRIPTION_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Archive data into specific files after categorising them into Order and Prescription.
     */
    public void archiveData(ArrayList<Medicine> medicines) {
        String orderArchiveData = "";
        String prescriptionData = "";
        for (Medicine medicine : medicines) {
            String data = medicine.toArchiveFormat() + System.lineSeparator();
            if (medicine instanceof Stock) {
                continue; // No archive for stock
            } else if (medicine instanceof Order) {
                orderArchiveData += data;
            } else if (medicine instanceof Prescription) {
                prescriptionData += data;
            }
        }

        File dataDirectory = new File(DIRECTORY_PATH);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        try {
            orderArchiveFile.createNewFile();
            appendToFile(orderArchiveData, ORDER_ARCHIVE_FILE_PATH);
            prescriptionArchiveFile.createNewFile();
            appendToFile(prescriptionData, PRESCRIPTION_ARCHIVE_FILE_PATH);
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
     * Append data into file corresponding files.
     *
     * @param data Data to be appended into the file.
     * @throws IOException If unable to append into file.
     */
    private void appendToFile(String data, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(data);
        fw.close();
    }

    /**
     * Load saved data from data/stock.txt, data/order.txt, data/prescription.txt.
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
        if (prescriptionFile.exists()) {
            try {
                medicines.addAll(readFromFile("PRESCRIPTION", prescriptionFile));
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        return medicines;
    }

    /**
     * Read and process medicine details from corresponding file to restore medicine state in program.
     *
     * @param file File object of data/stock.txt, data/order.txt, or data/prescription.txt
     * @throws FileNotFoundException If file is not found.
     */
    private ArrayList<Medicine> readFromFile(String fileType, File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Medicine> medicines = new ArrayList<>();
        int stockRow = 1;
        int orderRow = 1;
        int prescriptionRow = 1;
        while (sc.hasNextLine()) {
            String medicineDetails = sc.nextLine();
            try {
                switch (fileType) {
                case "STOCK":
                    Medicine parsedStock = parseStockData(medicineDetails, stockRow);
                    medicines.add(parsedStock);
                    stockRow++;
                    break;
                case "ORDER":
                    Medicine parsedOrder = parseOrderData(medicineDetails, orderRow);
                    medicines.add(parsedOrder);
                    orderRow++;
                    break;
                case "PRESCRIPTION":
                    Medicine parsedPrescription = parsePrescriptionData(medicineDetails, prescriptionRow);
                    medicines.add(parsedPrescription);
                    prescriptionRow++;
                    break;
                default:
                    break;
                }
            } catch (InvalidDataException e) {
                Ui ui = Ui.getInstance();
                ui.print(e.getMessage());
                ui.print("Corrupted data detected in file. Please fix error in file. Quitting MediVault now.");
                System.exit(0);
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
    private Medicine parseStockData(String stockDetails, int stockRow) throws InvalidDataException {
        String[] splitStockDetails = stockDetails.split(DELIMITER);
        if (splitStockDetails.length != NUMBER_OF_STOCK_DATA_FIELDS) {
            throw new InvalidDataException("[ROW: " + stockRow + "] INVALID NUMBER OF DELIMITER OR FIELDS"
                    + " [data/stock.txt]");
        }
        int stockId = FileParser.parseStockId(splitStockDetails, stockRow);
        String stockName = FileParser.parseStockName(splitStockDetails, stockRow);
        double stockPrice = FileParser.parseStockPrice(splitStockDetails, stockRow);
        int stockQuantity = FileParser.parseStockQuantity(splitStockDetails, stockRow);
        Date stockExpiry = FileParser.parseStockExpiry(splitStockDetails, stockRow);
        String stockDescription = FileParser.parseStockDescription(splitStockDetails, stockRow);
        int stockMaxQuantity = FileParser.parseStockMaxQuantity(splitStockDetails, stockRow);
        boolean stockIsDeleted = FileParser.parseStockIsDeleted(splitStockDetails, stockRow);

        Stock stock = new Stock(stockName, stockPrice, stockQuantity, stockExpiry, stockDescription, stockMaxQuantity);
        if (stockId > highestStockId) {
            stock.setStockId(stockId);
            stock.setStockCount(stockId);
            highestStockId = stockId;
        } else {
            stock.setStockId(stockId);
            stock.setStockCount(highestStockId);
        }
        stock.setDeleted(stockIsDeleted);
        return stock;
    }

    /**
     * Parse order data and create an order object based on it.
     *
     * @param orderDetails String of data of specific order from file data/order.txt.
     * @return Order object for adding into medicines.
     */
    private Medicine parseOrderData(String orderDetails, int orderRow) throws InvalidDataException {
        String[] orderStockDetails = orderDetails.split(DELIMITER);
        if (orderStockDetails.length != NUMBER_OF_ORDER_DATA_FIELDS) {
            throw new InvalidDataException("[ROW: " + orderRow + "] INVALID NUMBER OF DELIMITER OR FIELDS"
                    + " [data/order.txt]");
        }
        int orderId = FileParser.parseOrderId(orderStockDetails, orderRow);
        String orderName = FileParser.parseOrderName(orderStockDetails, orderRow);
        int orderQuantity = FileParser.parseOrderQuantity(orderStockDetails, orderRow);
        Date orderDate = FileParser.parseOrderDate(orderStockDetails, orderRow);
        boolean orderStatus = FileParser.parseOrderStatus(orderStockDetails, orderRow);

        Order order = new Order(orderName, orderQuantity, orderDate);
        if (orderStatus) {
            order.setDelivered();
        }
        if (orderId > highestOrderId) {
            order.setOrderId(orderId);
            order.setOrderCount(orderId);
            highestOrderId = orderId;
        } else {
            order.setOrderId(orderId);
            order.setOrderCount(highestOrderId);
        }
        return order;
    }

    /**
     * Parse prescription data and create a prescription object based on it.
     *
     * @param prescriptionDetails String of data of specific stock from file data/prescription.txt.
     * @param prescriptionRow     Row ID of the prescription.
     * @return Prescription object for adding into medicines.
     */
    private Medicine parsePrescriptionData(String prescriptionDetails, int prescriptionRow)
            throws InvalidDataException {
        String[] splitPrescriptionDetails = prescriptionDetails.split(DELIMITER);
        if (splitPrescriptionDetails.length != NUMBER_OF_PRESCRIPTION_DATA_FIELDS) {
            throw new InvalidDataException("[ROW: " + prescriptionRow + "] INVALID NUMBER OF DELIMITER OR FIELDS"
                    + " [data/prescription.txt]");
        }
        int prescriptionId = FileParser.parsePrescriptionId(splitPrescriptionDetails, prescriptionRow);
        String prescriptionName = FileParser.parsePrescriptionName(splitPrescriptionDetails, prescriptionRow);
        int prescriptionQuantity = FileParser.parsePrescriptionQuantity(splitPrescriptionDetails, prescriptionRow);
        String prescriptionCustomerId = FileParser.parsePrescriptionCustomerId(splitPrescriptionDetails,
                prescriptionRow);
        Date prescriptionDate = FileParser.parsePrescriptionDate(splitPrescriptionDetails, prescriptionRow);
        String prescriptionStaff = FileParser.parsePrescriptionStaff(splitPrescriptionDetails, prescriptionRow);
        int prescriptionStockId = FileParser.parsePrescriptionStockId(splitPrescriptionDetails, prescriptionRow);

        Prescription prescription = new Prescription(prescriptionName, prescriptionQuantity, prescriptionCustomerId,
                prescriptionDate, prescriptionStaff, prescriptionStockId);
        if (prescriptionId > highestPrescriptionId) {
            prescription.setPrescriptionId(prescriptionId);
            prescription.setPrescriptionCount(prescriptionId);
            highestPrescriptionId = prescriptionId;
        } else {
            prescription.setPrescriptionId(prescriptionId);
            prescription.setPrescriptionCount(highestPrescriptionId);
        }
        return prescription;
    }
}
