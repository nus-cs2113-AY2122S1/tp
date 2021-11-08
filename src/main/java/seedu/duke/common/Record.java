package seedu.duke.common;

public class Record {

    private final String elderlyPhoneNumber;
    private final String elderlyAddress;

    public Record(String elderlyPhoneNumber, String elderlyAddress) {
        this.elderlyPhoneNumber = elderlyPhoneNumber;
        this.elderlyAddress = elderlyAddress;
    }

    public String getElderlyPhoneNumber() {
        return elderlyPhoneNumber;
    }

    public String getElderlyAddress() {
        return elderlyAddress;
    }

    @Override
    public String toString() {
        return String.format("Elderly HP: %s, Elderly Address: %s", getElderlyPhoneNumber(), getElderlyAddress());
    }

}
