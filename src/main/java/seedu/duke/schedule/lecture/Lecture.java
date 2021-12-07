package seedu.duke.schedule.lecture;

//@@author swatimahadevan

/**
 * Represents the Lecture.
 */
public class Lecture {
    protected String moduleName;
    protected String dateFrom;
    protected String dateTo;

    /**
     * Constructor of Lecture class.
     *
     * @param moduleName the name of module for the lecture.
     * @param dateFrom the start date of the lecture.
     * @param dateTo the end date of the lecture.
     */
    public Lecture(String moduleName, String dateFrom, String dateTo) {
        this.moduleName = moduleName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    /**
     * Get module name.
     *
     * @return module name.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Get start date of lecture.
     *
     * @return start date of lecture.
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * Get end date of lecture.
     *
     * @return end date of lecture.
     */
    public String getDateTo() {
        return dateTo;
    }

    public String toSaveFileFormat() {
        return moduleName + "|" + dateFrom + "|" + dateTo;
    }

    /**
     * Returns lecture description.
     *
     * @return description of lecture.
     */
    public String toString() {
        return moduleName + " (from: " + dateFrom + ") " + "(to: " + dateTo + ") ";
    }
}