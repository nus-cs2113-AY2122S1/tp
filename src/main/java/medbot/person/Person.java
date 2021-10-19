package medbot.person;


import static medbot.Ui.VERTICAL_LINE_SPACED;


public abstract class Person {
    private static final String PARAMETER_NAME = "n/";
    private static final String PARAMETER_PHONE = "p/";
    private static final String PARAMETER_EMAIL = "e/";
    private static final String PARAMETER_IC = "i/";
    private static final String PARAMETER_ADDRESS = "a/";
    private static final int PARAMETER_BUFFER = 2;

    private int personId = 0;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

        return setAsStorageParameterOrNull(icNumber) + VERTICAL_LINE_SPACED
                + setAsStorageParameterOrNull(name) + VERTICAL_LINE_SPACED
                + setAsStorageParameterOrNull(phoneNumber) + VERTICAL_LINE_SPACED
                + setAsStorageParameterOrNull(emailAddress) + VERTICAL_LINE_SPACED
                + setAsStorageParameterOrNull(residentialAddress);
    }

    /**
     * Checks whether this person contains all the parameters specified.
     *
     * @param parameters the attributes to check.
     * @return true if all parameters are found, false otherwise.
     */
    public boolean containsAllParameters(String[] parameters) {
        for (String parameter : parameters) {
            if (!containsParameter(parameter)) {
                return false;
            }
        }
        return true;
    }

    private boolean containsParameter(String parameter) {
        String trimmedParameter = parameter.substring(PARAMETER_BUFFER).trim().toLowerCase();
        if (parameter.startsWith(PARAMETER_NAME)) {
            return getName().toLowerCase().contains(trimmedParameter);
        }
        if (parameter.startsWith(PARAMETER_IC)) {
            return getIcNumber().toLowerCase().contains(trimmedParameter);
        }
        if (parameter.startsWith(PARAMETER_PHONE)) {
            return getPhoneNumber().toLowerCase().contains(trimmedParameter);
        }
        if (parameter.startsWith(PARAMETER_EMAIL)) {
            return getEmailAddress().toLowerCase().contains(trimmedParameter);
        }
        if (parameter.startsWith(PARAMETER_ADDRESS)) {
            return getResidentialAddress().toLowerCase().contains(trimmedParameter);
        }

        return false;
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
