package seedu.duke;


import java.util.HashMap;

public class Person {
    private String name;
    private HashMap<Person, Double> moneyOwed = new HashMap<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMoneyOwed(Person person, double amount) {
        double originalAmount = moneyOwed.get(person);
        moneyOwed.put(person, originalAmount + amount);
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Person, Double> getMoneyOwed() {
        return this.moneyOwed;
    }

    public double getTotalExpenditure() {
        return moneyOwed.get(this);
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
