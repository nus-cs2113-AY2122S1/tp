package seedu.module;

public abstract class BaseModule {
    protected String moduleCode;
    protected String title;
    protected String description;
    protected double moduleCredit;

    public BaseModule(String moduleCode) {

        this.moduleCode = moduleCode;
    }

    public double getModuleCredit() {
        return moduleCredit;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCredit(double moduleCredit) {
        this.moduleCredit = moduleCredit;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return moduleCode + " " + title + " " + (int) moduleCredit + "MC";
    }
}
