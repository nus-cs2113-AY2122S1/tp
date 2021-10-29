package entity.invest;

public class Investment {
    private String name;
    private Double totalValue;

    public Investment(String name, Double totalValue) {
        this.name = name;
        this.totalValue = totalValue;
    }

    public String getName() {
        return name;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double newValue) {
        totalValue = newValue;
    }

    public String toString() {
        return "";
    }

    public String toFileString() {
        return name;
    }
}
