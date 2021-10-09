package seedu.duke;

public class Client {
    private String name;
    private String contactNum;
    private String flight;
    private String tour;

    public Client(String name, String contactNum, String flight, String tour) {
        this.name = name;
        this.contactNum = contactNum;
        this.flight = flight;
        this.tour = tour;
    }

    @Override
    public String toString() {
        return "Client's name: " + name + System.lineSeparator() + "Client's contactNum: " + contactNum + System.lineSeparator()
                + "Client's flight number: " + flight + System.lineSeparator() + "Client's tour" + tour + System.lineSeparator();
    }
}
