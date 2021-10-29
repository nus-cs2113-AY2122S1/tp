package entity.invest;

import java.util.ArrayList;

public class StockList {
    private static ArrayList<Stock> stocks = new ArrayList<>();

    public static void addStock(Stock stock) {

        String stockToAddName = stock.getName();

        for (int i = 0; i < stocks.size(); i++) {
            Stock s = stocks.get(i);
            String currName = s.getName();

            if (currName.equals(stockToAddName)) {
                s.add(stock);
                return;
            }
        }

        stocks.add(stock);
    }

    public static void deleteStock(int stockIndex) throws IndexOutOfBoundsException {
        stocks.remove(stockIndex);
    }

    public static ArrayList<Stock> getAllStocks() {
        return stocks;
    }
}
