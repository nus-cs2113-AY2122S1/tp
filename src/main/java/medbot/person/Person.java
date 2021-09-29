package medbot.person;

public abstract class Person {
    protected String nricNumber = "UNKNOWN";
    protected String name = "UNKNOWN";
    protected int phoneNumber = 0;
    protected String emailAddress = "UNKNOWN";
    protected String residentialAddress = "UNKNOWN";
    protected PersonType personType;

    public String toString() {
        return "IC: " + nricNumber + " Name: " + name + " H/P: " + phoneNumber +
                " Email: " + emailAddress + " Address: " + residentialAddress;
    }

    public String getNricNumber() {
        return nricNumber;
    }

    public void setNricNumber(String nricNumber) {
        this.nricNumber = nricNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }
}
