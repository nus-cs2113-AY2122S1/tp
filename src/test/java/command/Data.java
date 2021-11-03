package command;

import inventory.Medicine;
import inventory.Order;
import inventory.Prescription;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;

public class Data {
    private static Ui ui = Ui.getInstance();

    /**
     * Function to clear all the data and reset the IDs.
     */
    public static void clearTestData() {
        ArrayList<Medicine> medicines = Medicine.getInstance();
        // Clear all data first and reset count
        medicines.clear();
        Stock.setStockCount(0);
        Prescription.setPrescriptionCount(0);
        Order.setOrderCount(0);
    }


    /**
     * Function to generate test data to test list commands.
     */
    public static void generateTestData() {
        clearTestData();
        ArrayList<Medicine> medicines = Medicine.getInstance();
        generateTestStocks(medicines);
        generateTestPrescriptions(medicines);
        generateTestOrders(medicines);
    }

    /**
     * Function to generate stock data to test list commands.
     *
     * @param medicines Arraylist of medicines.
     */
    public static void generateTestStocks(ArrayList<Medicine> medicines) {
        try {
            medicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2022"),
                    "HEADACHES", 1000));
            medicines.add(new Stock("PANADOL", 20, 10, DateParser.stringToDate("14-9-2022"),
                    "HEADACHES", 1000));
            medicines.add(new Stock("VICODIN", 10, 20, DateParser.stringToDate("30-9-2022"),
                    "SEVERE PAIN",
                    500));
            medicines.add(new Stock("SIMVASTATIN", 20, 25, DateParser.stringToDate("10-10-2023"),
                    "HIGH CHOLESTEROL", 800));
            medicines.add(new Stock("LISINOPRIL", 20, 25, DateParser.stringToDate("15-10-2023"),
                    "HYPOTHYROIDISM", 800));
            medicines.add(new Stock("AZITHROMYCIN", 20, 35, DateParser.stringToDate("15-10-2023"),
                    "INFECTIONS", 100));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }

    }

    /**
     * Function to generate prescription data to test list commands.
     *
     * @param medicines Arraylist of medicines.
     */
    public static void generateTestPrescriptions(ArrayList<Medicine> medicines) {
        try {
            medicines.add(new Prescription("PANADOL", 10, "S1234567A",
                    DateParser.stringToDate("9-10-2021"), "Jane", 1));
            medicines.add(new Prescription("VICODIN", 15, "S2345678B",
                    DateParser.stringToDate("10-10-2021"), "Peter", 3));
            medicines.add(new Prescription("SIMVASTATIN", 20, "S1234567A",
                    DateParser.stringToDate("11-10-2021"), "Sam", 4));
            medicines.add(new Prescription("LISINOPRIL", 10, "S3456789C",
                    DateParser.stringToDate("12-10-2021"), "Jane", 5));
            medicines.add(new Prescription("AZITHROMYCIN", 5, "S2345678B",
                    DateParser.stringToDate("13-10-2021"), "Peter", 6));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
    }

    /**
     * Function to generate order data to test list commands.
     *
     * @param medicines Arraylist of medicines.
     */
    public static void generateTestOrders(ArrayList<Medicine> medicines) {
        try {
            medicines.add(new Order("PANADOL", 100, DateParser.stringToDate("9-10-2021")));
            medicines.add(new Order("VICODIN", 30, DateParser.stringToDate("10-10-2021")));
            Order order = new Order("VICODIN", 50, DateParser.stringToDate("11-10-2021"));
            order.setDelivered();
            medicines.add(order);
            medicines.add(new Order("SIMVASTATIN", 20, DateParser.stringToDate("11-10-2021")));
            medicines.add(new Order("LISINOPRIL", 200, DateParser.stringToDate("12-12-2021")));
            medicines.add(new Order("AZITHROMYCIN", 100, DateParser.stringToDate("13-12-2021")));
            medicines.add(new Order("PANADOL", 50, DateParser.stringToDate("13-12-2021")));
        } catch (ParseException e) {
            ui.print("Unable to parse date!");
        }
    }
}
