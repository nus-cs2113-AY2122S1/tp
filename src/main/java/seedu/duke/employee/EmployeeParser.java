package seedu.duke.employee;

public class EmployeeParser {

    public void addEmployee(EmployeeList masterList, Employee employee) {
        masterList.employeeList.add(employee);
        masterList.totalEmployee += 1;
    }

    public void deleteEmployee(EmployeeList masterList, int employeeIndex) {
        if (masterList.totalEmployee < 1) {
            return;
        }
        masterList.employeeList.remove(employeeIndex);
        masterList.totalEmployee -= 1;
    }

    public void listEmployee(EmployeeList masterList) {
        if (masterList.totalEmployee < 1) {
            return;
        }
        System.out.println("--------------------");
        System.out.println("Here are the employees in your list:");
        for (int i = 0; i < masterList.totalEmployee; i += 1) {
            System.out.println(i + masterList.employeeList.get(i).getName());
        }
        System.out.println("--------------------");
    }

}
