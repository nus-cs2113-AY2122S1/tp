package service;

import entity.invest.Saving;
import entity.invest.SavingList;
import entity.invest.Stock;
import entity.invest.StockList;
import entity.invest.Investment;
import terminal.Ui;

import java.util.ArrayList;

import static constants.ErrorMessage.invalidIndexMsg;

public class InvestManager implements LoadableManager {
    private static InvestManager investMgr;
    private final String fileLabel;

    private InvestManager() {
        fileLabel = "invest";
    }

    public static InvestManager getInvestManager() {
        if (investMgr == null) {
            investMgr = new InvestManager();
        }
        return investMgr;
    }

    @Override
    public void parse(String[] fileString) {
        for (String line : fileString) {
            String[] splitLine = line.split(";");

            String type = splitLine[0];
            String name = splitLine[1];

            if (type.equals("saving")) {
                Double amount = Double.parseDouble(splitLine[2]);
                Saving saving = new Saving(name, amount);
                SavingList.addSavings(saving);

            } else if (type.equals("stock")) {
                Integer numOfShares = Integer.parseInt(splitLine[2]);
                Double price = Double.parseDouble(splitLine[3]);
                Stock stock = new Stock(name, numOfShares, price);
                StockList.addStock(stock);
            }
        }
    }

    @Override
    public String toFileString() {
        String stockString = printToFile(StockList.getAllStocks());
        String savingString = printToFile(SavingList.getAllSavings());

        if (stockString.equals("")) {
            return savingString;
        } else {
            return stockString + "\n" + savingString;
        }
    }

    public String printToFile(ArrayList investments) {
        StringBuilder fileString = new StringBuilder();

        if (investments.size() == 0) {
            return fileString.toString();
        }

        for (Object invest : investments) {
            String investFileString = ((Investment)invest).toFileString();
            fileString.append(investFileString).append("\n");
        }

        return fileString.toString().strip();
    }

    @Override
    public String getFileLabel() {
        return fileLabel;
    }

    public void listAllStocks() {
        Ui ui = Ui.getUi();
        String stockListHeader = String.format("%-10s | %-10s | %-10s | %-10s", "Id.", "Name", "Number", "Value");

        ui.printMessage(stockListHeader);
        ArrayList<Stock> stocks = StockList.getAllStocks();
        for (int i = 0; i < stocks.size(); i++) {
            ui.printMessage(String.format("%-10s | %s", (i + 1) + ".", stocks.get(i)));
        }
    }

    public void listAllSavings() {
        Ui ui = Ui.getUi();
        String savingsListHeader = String.format("%-10s | %-10s | %-10s", "Id.", "Name", "Value");

        ui.printMessage(savingsListHeader);
        ArrayList<Saving> savings = SavingList.getAllSavings();
        for (int i = 0; i < savings.size(); i++) {
            ui.printMessage(String.format("%-10s | %s", (i + 1) + ".", savings.get(i)));
        }
    }

    public void addSaving(String name, Double value) {
        Saving saving = new Saving(name, value);
        SavingList.addSavings(saving);
    }

    public void addStock(String name, Integer num, Double price) {
        Stock stock = new Stock(name, num, price);
        StockList.addStock(stock);
    }

    public void deleteSaving(int index) {
        try {
            SavingList.deleteSavings(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(invalidIndexMsg);
        }
    }

    public void deleteStock(int index) {
        try {
            StockList.deleteStock(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(invalidIndexMsg);
        }
    }
}
