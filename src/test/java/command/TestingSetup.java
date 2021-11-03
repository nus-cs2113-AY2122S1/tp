package command;

import inventory.Medicine;
import inventory.Order;
import inventory.Prescription;
import inventory.Stock;
import utilities.parser.DateParser;
import utilities.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;

public class TestingSetup {
    public static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    public final static PrintStream originalOut = System.out;
    public final static PrintStream originalErr = System.err;

    public static void generateData() {
        try {
            ArrayList<Medicine> medicines = Medicine.getInstance();
            medicines.clear();
            Stock.setStockCount(0);
            Prescription.setPrescriptionCount(0);
            Order.setOrderCount(0);
            medicines.add(new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
            medicines.add(new Stock("PANADOL", 20, 10, DateParser.stringToDate("14-9-2021"),
                    "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
            medicines.add(new Stock("VICODIN", 10, 20, DateParser.stringToDate("30-9-2021"),
                    "POPULAR DRUG FOR TREATING ACUTE OR CHRONIC MODERATE TO MODERATELY SEVERE PAIN",
                    500));
            medicines.add(new Stock("SIMVASTATIN", 20, 25, DateParser.stringToDate("10-10-2021"),
                    "TREATS HIGH CHOLESTEROL AND REDUCES THE RISK OF STROKE", 800));
            medicines.add(new Stock("LISINOPRIL", 20, 25, DateParser.stringToDate("15-10-2021"),
                    "USED FOR TREATING HYPOTHYROIDISM", 800));
            medicines.add(new Stock("AZITHROMYCIN", 20, 35, DateParser.stringToDate("15-10-2021"),
                    "USED FOR TREATING EAR, THROAT, AND SINUS INFECTIONS", 100));
            medicines.add(new Order("PANADOL", 100, DateParser.stringToDate("9-10-2021")));
            medicines.add(new Order("VICODIN", 30, DateParser.stringToDate("9-10-2021")));
            Order order = new Order("VICODIN", 50, DateParser.stringToDate("10-10-2021"));
            order.setDelivered();
            medicines.add(order);
            medicines.add(new Order("SIMVASTATIN", 20, DateParser.stringToDate("11-10-2021")));
            medicines.add(new Order("LISINOPRIL", 200, DateParser.stringToDate("12-10-2021")));
            medicines.add(new Order("AZITHROMYCIN", 100, DateParser.stringToDate("13-10-2021")));
            medicines.add(new Order("PANADOL", 50, DateParser.stringToDate("13-10-2021")));
            medicines.add(new Prescription("PANADOL", 10, "S1234567A",
                    DateParser.stringToDate("9-10-2021"), "Jane", 1));
            medicines.add(new Prescription("VICODIN", 10, "S2345678B",
                    DateParser.stringToDate("10-10-2021"), "Peter", 3));
            medicines.add(new Prescription("SIMVASTATIN", 10, "S1234567A",
                    DateParser.stringToDate("11-10-2021"), "Sam", 4));
            medicines.add(new Prescription("LISINOPRIL", 10, "S3456789C",
                    DateParser.stringToDate("12-10-2021"), "Jane", 5));
            medicines.add(new Prescription("AZITHROMYCIN", 10, "S2345678B",
                    DateParser.stringToDate("13-10-2021"), "Peter", 6));
        } catch (ParseException e) {
            Ui ui = Ui.getInstance();
            ui.print("Unable to parse date!");
        }
    }
}
