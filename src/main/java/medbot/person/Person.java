package medbot.person;


import static medbot.Ui.DATA_SEPARATOR_UNESCAPED;


public abstract class Person {

    protected String icNumber = "";
    protected String name = "";
    protected String phoneNumber = "";
    protected String emailAddress = "";
    protected String residentialAddress = "";
    protected PersonType personType;

    public String toString() {
        return "IC: " + icNumber + " Name: " + name + " H/P: " + phoneNumber
                + " Email: " + emailAddress + " Address: " + residentialAddress;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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

    public void setNull() {
        icNumber = null;
        name = null;
        phoneNumber = null;
        emailAddress = null;
        residentialAddress = null;
    }

    /**
     * Text to be written to storage/data.txt of a person
     *
     * @return storageString of a person
     */
    public String getStorageString() {

        return setAsStorageParameterOrNull(icNumber) + DATA_SEPARATOR_UNESCAPED
                + setAsStorageParameterOrNull(name) + DATA_SEPARATOR_UNESCAPED
                + setAsStorageParameterOrNull(phoneNumber) + DATA_SEPARATOR_UNESCAPED
                + setAsStorageParameterOrNull(emailAddress) + DATA_SEPARATOR_UNESCAPED
                + setAsStorageParameterOrNull(residentialAddress);
    }

    /**
     * Return "X" if parameter == null || parameter.isBlank(), otherwise return parameter itself
     *
     * @param parameter an attribute of a person
     * @return "X" if parameter == null || parameter.isBlank(), otherwise return parameter itself
     */
    protected String setAsStorageParameterOrNull(String parameter) {
        return (parameter == null || parameter.isBlank()) ? "X" : parameter;
    }
}
