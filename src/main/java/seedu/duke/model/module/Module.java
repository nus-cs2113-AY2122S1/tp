package seedu.duke.model.module;

import seedu.duke.commons.core.Messages;
import seedu.duke.model.module.exceptions.DeserializeModuleException;
import seedu.duke.ui.Ui;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private String moduleCredit;
    private String department;
    private String faculty;
    private String preclusion;
    // todo add semester information
    private String prerequisite;
    private String corequisite;
    // todo add additional information

    public Module(String moduleCode, String title, String moduleCredit) {
        // subject to change
        this.moduleCode = moduleCode;
        this.title = title;
        this.moduleCredit = moduleCredit;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getModuleCredit() {
        return moduleCredit;
    }

    public String getDepartment() {
        return department;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getPreclusion() {
        return preclusion;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public String getCorequisite() {
        return corequisite;
    }

    public String serialize() {
        // subject to change
        return moduleCode + " | " + title + " | " + moduleCredit;
    }

    public static Module deserialize(Ui ui, String line) {
        try {
            String[] params = line.split("\\s*[|]\\s*");

            String moduleCode = params[0];
            String title = params[1];
            String moduleCredit = params[2];

            return new Module(moduleCode, title, moduleCredit);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Ignoring the particular line
            ui.printMessage(Messages.ERROR_DESERIALIZING_TASK);
            return null;
        }
    }

    /**
     * Returns the full module information as a string in a ready to print format.
     *
     * @return a string of the full module information
     */
    public String getFullInfo() {
        StringBuilder s = new StringBuilder();
        s.append(Ui.LINE).append(Ui.PADDING).append(this).append(System.lineSeparator());
        s.append(Ui.PADDING).append("Department: ").append(department).append(System.lineSeparator());
        s.append(Ui.PADDING).append("Faculty: ").append(faculty).append(System.lineSeparator());
        s.append(Ui.PADDING).append("Preclusion: ").append(preclusion).append(System.lineSeparator());
        s.append(Ui.PADDING).append("Pre-requisite: ").append(prerequisite).append(System.lineSeparator());
        s.append(Ui.PADDING).append("Core Requisites: ").append(corequisite).append(System.lineSeparator())
                .append(Ui.LINE);
        return s.toString();
    }

    /**
     * Returns a string of the basic module information only. (module code, title and module credits)
     *
     * @return a string of the basic module information
     */
    @Override
    public String toString() {
        return moduleCode + " " + title + " (" + moduleCredit + "MCs) ";
    }
}
