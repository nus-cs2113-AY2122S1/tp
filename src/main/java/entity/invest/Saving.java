package entity.invest;

public class Saving extends Investment {

    private final String type = "saving";

    public Saving(String name, Double value) {
        super(name, value);
    }

    public void deposit(Double amount) {
        Double initialValue = super.getTotalValue();
        super.setTotalValue(initialValue + amount);
    }

    public String toFileString() {
        String value = super.getTotalValue().toString();
        return String.format("%s;%s;%s", type, super.toFileString(), value);
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10.2f", super.getName(), super.getTotalValue());
    }

    public void add(Saving s) {
        Double currValue = super.getTotalValue();
        Double moreValue = s.getTotalValue();

        super.setTotalValue(currValue + moreValue);
    }
}
