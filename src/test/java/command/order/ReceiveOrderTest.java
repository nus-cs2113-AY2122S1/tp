package command.order;

import command.Data;
import inventory.Medicine;
import inventory.Order;
import inventory.Stock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.parser.DateParser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author alvintan01

public class ReceiveOrderTest {
    private static ArrayList<Medicine> medicines = Medicine.getInstance();

    @BeforeEach
    @AfterEach
    public void clearData() {
        Data.clearTestData();
    }

    @Test
    public void receiveOrder_newOrder_expectOrderCompleted() {
        try {
            Order order = new Order("PANADOL", 100, DateParser.stringToDate("3-11-2021"));
            medicines.add(order);

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put("i", "1");
            parameters.put("p", "20");
            parameters.put("e", "11-12-2022");
            parameters.put("d", "FEVER");
            parameters.put("m", "1000");

            new ReceiveOrderCommand(parameters).execute();

            final int orderIndex = 0;
            final int stockIndex = 1;

            Order completedOrder = (Order) medicines.get(orderIndex);
            Stock addedStock = (Stock) medicines.get(stockIndex);

            assertEquals(completedOrder.getStatus(), "DELIVERED");
            assertEquals(addedStock.getMedicineName(), "PANADOL");
            assertEquals(addedStock.getQuantity(), 100);
            assertEquals(DateParser.dateToString(addedStock.getExpiry()), "11-12-2022");
            assertEquals(addedStock.getDescription(), "FEVER");
            assertEquals(addedStock.getMaxQuantity(), 1000);

            assertEquals(addedStock.getMaxQuantity(), 1000);
        } catch (ParseException e) {
            System.out.println("Unable to parse date!");
        }
    }

    @Test
    public void receiveOrder_newOrderStockExists_expectDescriptionMaxQuantityIgnoredOrderCompleted() {
        try {
            Stock stock = new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2022"),
                    "HEADACHES", 1000);
            Order order = new Order("PANADOL", 100, DateParser.stringToDate("3-11-2021"));
            medicines.add(stock);
            medicines.add(order);

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put("i", "1");
            parameters.put("p", "20");
            parameters.put("e", "11-12-2022");
            parameters.put("d", "ANOTHER DESCRIPTION");
            parameters.put("m", "10000");

            new ReceiveOrderCommand(parameters).execute();

            final int orderIndex = 1;
            final int stockIndex = 2;

            Order completedOrder = (Order) medicines.get(orderIndex);
            Stock addedStock = (Stock) medicines.get(stockIndex);

            assertEquals(completedOrder.getStatus(), "DELIVERED");
            assertEquals(addedStock.getDescription(), "HEADACHES");
            assertEquals(addedStock.getMaxQuantity(), 1000);
        } catch (ParseException e) {
            System.out.println("Unable to parse date!");
        }
    }

    @Test
    public void receiveOrder_newOrderStockExists_expectMaxQuantityErrorOrderPendingSameExistingQuantity() {
        try {
            Stock stock = new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2022"),
                    "HEADACHES", 100);
            Order order = new Order("PANADOL", 100, DateParser.stringToDate("3-11-2021"));
            medicines.add(stock);
            medicines.add(order);
            final int numberOfMedicines = medicines.size();

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put("i", "1");
            parameters.put("p", "20");
            parameters.put("e", "11-12-2022");

            new ReceiveOrderCommand(parameters).execute();

            final int orderIndex = 1;
            final int stockIndex = 0;

            Order completedOrder = (Order) medicines.get(orderIndex);
            Stock currentStock = (Stock) medicines.get(stockIndex);

            assertEquals(medicines.size(), numberOfMedicines);
            assertEquals(completedOrder.getStatus(), "PENDING");
            assertEquals(currentStock.getQuantity(), 20);

        } catch (ParseException e) {
            System.out.println("Unable to parse date!");
        }
    }

    @Test
    public void receiveOrder_newOrderStockExistsSameExpiry_expectPriceIgnoredOrderCompletedStockQuantityIncreased() {
        try {
            Stock stock = new Stock("PANADOL", 20, 20, DateParser.stringToDate("13-9-2022"),
                    "HEADACHES", 1000);
            Order order = new Order("PANADOL", 100, DateParser.stringToDate("3-11-2021"));
            medicines.add(stock);
            medicines.add(order);

            LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
            parameters.put("i", "1");
            parameters.put("p", "30");
            parameters.put("e", "13-9-2022");

            new ReceiveOrderCommand(parameters).execute();

            final int orderIndex = 1;
            final int stockIndex = 0;

            Order completedOrder = (Order) medicines.get(orderIndex);
            Stock addedStock = (Stock) medicines.get(stockIndex);

            assertEquals(completedOrder.getStatus(), "DELIVERED");
            assertEquals(addedStock.getQuantity(), 120);
        } catch (ParseException e) {
            System.out.println("Unable to parse date!");
        }
    }
}
