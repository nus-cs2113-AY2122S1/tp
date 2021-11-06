package entity.invest;

public class Stock extends Investment {

    private Integer numOfShares;
    private Double averagePrice;
    private final String type = "stock";

    public Stock(String name, Integer numOfShares, Double price) {
        super(name, numOfShares * price);
        this.numOfShares = numOfShares;
        this.averagePrice = price;
    }

    public void buy(Integer newNumOfShares, Double price) {
        Double boughtValue = newNumOfShares * price;
        Double initialValue = super.getTotalValue();
        Double totalValue = boughtValue + initialValue;

        super.setTotalValue(totalValue);
        numOfShares += newNumOfShares;

        averagePrice = totalValue / numOfShares;
    }

    public String toFileString() {
        String price = averagePrice.toString();
        String num =  numOfShares.toString();
        return String.format("%s;%s;%s;%s", type, super.toFileString(), num, price);
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-10.2f", super.getName(), numOfShares, super.getTotalValue());
    }

    public Integer getNumOfShares() {
        return numOfShares;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void add(Stock s) {
        Integer newNumOfShares = s.getNumOfShares();
        Double price = s.getAveragePrice();

        buy(newNumOfShares, price);
    }
}
