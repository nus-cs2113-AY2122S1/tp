//@@author kairoskoh

package seedu.duke.employee;

/**
 * Represents each individual employee.
 * Contains important information such as name, phone number, employment status and salary.
 */
public class Employee {

    /**
     * Contains three possible values for employment status of employees.
     * PERM represents permanent employees.
     * TEMP represents temporary or part-time employees.
     * ADHOC represents adhoc employees, usually a one-time basis.
     */
    public enum EmploymentStatus {
        ADHOC,
        PERM,
        TEMP
    }

    private String name;
    private int phoneNum;
    private EmploymentStatus status;
    private int salary;

    public Employee(String name, int phoneNum, EmploymentStatus status, int salary) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.status = status;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - " + phoneNum + " - " + status + " STAFF - $" + salary;
    }

    public String toStringStorage() {
        return "add-employee/" + name + "/" + phoneNum + "/" + status + "/" + salary;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public int getSalary() {
        return salary;
    }
}
