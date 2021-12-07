package seedu.duke.module;

//@@author nvbinh15

/**
 * A representation of a Module.
 */
public class Module {
    private String code;
    private String name;
    private int modularCredits;
    private String expectedGrade;

    private static final String DEFAULT_MODULE_NAME = "None";
    private static final int DEFAULT_MODULAR_CREDITS = 4;
    private static final String DEFAULT_EXPECTED_GRADE = "NA";

    /**
     * Class constructor specifying the module code.
     *
     * @param code The module code.
     */
    public Module(String code) {
        this.code = code;
        this.name = DEFAULT_MODULE_NAME;
        this.modularCredits = DEFAULT_MODULAR_CREDITS;
        this.expectedGrade = DEFAULT_EXPECTED_GRADE;
    }

    /**
     * Class constructor specifying the module code and module name.
     *
     * @param code The module code.
     * @param name The module name.
     */
    public Module(String code, String name) {
        this.code = code;
        this.name = name;
        this.modularCredits = DEFAULT_MODULAR_CREDITS;
        this.expectedGrade = DEFAULT_EXPECTED_GRADE;
    }

    /**
     * Class constructor specifying the module code, module name, and the expected grade.
     *
     * @param code The module code.
     * @param name The module name.
     * @param modularCredits The modular credits of the module.
     */
    public Module(String code, String name, int modularCredits) {
        this.code = code;
        this.name = name;
        this.modularCredits = modularCredits;
        this.expectedGrade = DEFAULT_EXPECTED_GRADE;
    }

    /**
     * Class constructor specifying the module code, module name, the expected grade,
     *     and the modular credits.
     *
     * @param code The module code.
     * @param name The module name.
     * @param expectedGrade The expected grade of the module.
     * @param modularCredits The modular credits of the module.
     */
    public Module(String code, String name, int modularCredits, String expectedGrade) {
        this.code = code;
        this.name = name;
        this.expectedGrade = expectedGrade;
        this.modularCredits = modularCredits;
    }

    /**
     * Returns the module code.
     *
     * @return the module code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the module name.
     *
     * @return the module name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the module expected grade.
     *
     * @return the module expected grade.
     */
    public String getExpectedGrade() {
        return expectedGrade;
    }

    /**
     * Returns the modular credits.
     *
     * @return the modular credits.
     */
    public int getModularCredits() {
        return modularCredits;
    }

    /**
     * Sets the code of the module.
     *
     * @param code The code to be set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets the name of the module.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the expected grade of the module.
     *
     * @param expectedGrade The grade to be set.
     */
    public void setExpectedGrade(String expectedGrade) {
        this.expectedGrade = expectedGrade;
    }

    /**
     * Sets the modular credits.
     *
     * @param modularCredits The modular credits to be set.
     */
    public void setModularCredits(int modularCredits) {
        this.modularCredits = modularCredits;
    }

    /**
     * Overrides toString method of class Object to get the string representation of Module.
     *
     * @return The string representation of the module.
     */
    @Override
    public String toString() {
        return this.code + " | " + this.name + " | MC: " + this.modularCredits
                + " | Expected grade: " + this.expectedGrade;
    }

}
