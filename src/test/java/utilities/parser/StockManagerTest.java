package utilities.parser;

import inventory.Medicine;
import inventory.Stock;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StockManagerTest {

    @Test
    void getTotalStockQuantity_validStock_expectCorrectQuantity() throws ParseException {
        // Add dummy medicine values for testing
        ArrayList<Medicine> medicines = new ArrayList<>();
        medicines.add(new Stock("PANADOL", 10, 20, DateParser.stringToDate("13-9-2021"),
                "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        medicines.add(new Stock("PANADOL", 30, 20, DateParser.stringToDate("14-9-2021"),
                "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        medicines.add(new Stock("AZITHROMYCIN", 20, 20, DateParser.stringToDate("14-9-2021"),
                "USED FOR TREATING EAR, THROAT, AND SINUS INFECTIONS", 2000));

        int panadolStockQuantity = StockManager.getTotalStockQuantity(medicines, "PANADOL");
        int azithromycinStockQuantity = StockManager.getTotalStockQuantity(medicines, "AZITHROMYCIN");
        assertEquals(40, panadolStockQuantity);
        assertEquals(20, azithromycinStockQuantity);
    }

    @Test
    void getTotalStockQuantity_emptyStock_expectNoQuantity() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        int totalStockQuantity = StockManager.getTotalStockQuantity(medicines, "PANADOL");
        assertEquals(0, totalStockQuantity);
    }

    @Test
    void getMaxStockQuantity_validStock_expectCorrectMaxQuantity() throws ParseException {
        // Add dummy medicine values for testing
        ArrayList<Medicine> medicines = new ArrayList<>();
        medicines.add(new Stock("PANADOL", 10, 20, DateParser.stringToDate("13-9-2021"),
                "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        medicines.add(new Stock("PANADOL", 30, 20, DateParser.stringToDate("14-9-2021"),
                "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        medicines.add(new Stock("AZITHROMYCIN", 20, 20, DateParser.stringToDate("14-9-2021"),
                "USED FOR TREATING EAR, THROAT, AND SINUS INFECTIONS", 2000));

        int panadolMaxStockQuantity = StockManager.getMaxStockQuantity(medicines, "PANADOL");
        int azithromycinMaxStockQuantity = StockManager.getMaxStockQuantity(medicines, "AZITHROMYCIN");
        assertEquals(1000, panadolMaxStockQuantity);
        assertEquals(2000, azithromycinMaxStockQuantity);
    }

    @Test
    void getMaxStockQuantity_emptyStock_assertionError() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        assertThrows(AssertionError.class, () -> StockManager.getMaxStockQuantity(medicines, "PANADOL"));
    }

    @Test
    void extractStockObject_noStock_assertionError() {
        ArrayList<Medicine> medicines = new ArrayList<>();
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("i", "1");
        assertThrows(AssertionError.class, () -> StockManager.extractStockObject(parameters, medicines));
    }

    @Test
    void extractStockObject_IdNotFound_assertionError() throws ParseException {
        ArrayList<Medicine> medicines = new ArrayList<>();
        medicines.add(new Stock("PANADOL", 10, 20, DateParser.stringToDate("13-9-2021"),
                "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        medicines.add(new Stock("PANADOL", 30, 20, DateParser.stringToDate("14-9-2021"),
                "BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS", 1000));
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("i", "3");
        assertThrows(AssertionError.class, () -> StockManager.extractStockObject(parameters, medicines));
    }


}