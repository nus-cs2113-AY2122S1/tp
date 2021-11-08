package seedu.duke.common;

public class NextOfKin {

    private final String nokName;
    private final String nokPhoneNumber;
    private final String nokEmail;
    private final String nokAddress;
    private final String nokRelationship;

    public NextOfKin(String nokName, String nokPhoneNumber, String nokEmail, String nokAddress,
                     String nokRelationship) {
        this.nokName = nokName;
        this.nokPhoneNumber = nokPhoneNumber;
        this.nokEmail = nokEmail;
        this.nokAddress = nokAddress;
        this.nokRelationship = nokRelationship;
    }

    public String getNokName() {
        return nokName;
    }

    public String getNokPhoneNumber() {
        return nokPhoneNumber;
    }

    public String getNokEmail() {
        return nokEmail;
    }

    public String getNokAddress() {
        return nokAddress;
    }

    public String getNokRelationship() {
        return nokRelationship;
    }

    @Override
    public String toString() {
        return String.format("NOK Name: %s, NOK HP: %s, NOK Email: %s, NOK Address: %s, Relationship: %s",
                getNokName(), getNokPhoneNumber(), getNokEmail(),
                getNokAddress(), getNokRelationship());
    }

}
