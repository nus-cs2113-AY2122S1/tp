package seedu.duke.employee;

public class EmployeeParser {

    public void addEmployee(String[] command, EmployeeList masterList) {

        Employee employee = new Employee(command[1], command[2]);

        masterList.employeeList.add(employee);
        masterList.totalEmployee += 1;

        System.out.println("I have added: ");
        System.out.println(masterList.employeeList.get(masterList.totalEmployee - 1));

        System.out.println("You now have " + masterList.totalEmployee + " employees.");

    }

    public void loadEmployeeFromStorage(EmployeeList masterList, Employee employee) {
        masterList.employeeList.add(employee);
        masterList.totalEmployee += 1;
    }

    public void deleteEmployee(String[] command, EmployeeList masterList) {
        int employeeIndex = Integer.parseInt(command[1]) - 1;
        if (masterList.totalEmployee < 1) {
            return;
        }
        System.out.println("I have deleted: ");
        System.out.println(masterList.employeeList.get(employeeIndex));

        masterList.employeeList.remove(employeeIndex);
        masterList.totalEmployee -= 1;
    }

    public void listEmployee(EmployeeList masterList) {
        if (masterList.totalEmployee < 1) {
            System.out.println("--------------------");
            System.out.println("You have no employee.");
            System.out.println("--------------------");
            return;
        }
        System.out.println("--------------------");
        System.out.println("Here are the employees in your list:");
        for (int i = 1; i <= masterList.totalEmployee; i += 1) {
            System.out.println(i + " - " + masterList.employeeList.get(i - 1).getName()
                    + " - " + masterList.employeeList.get(i - 1).getPhoneNum());
        }
        System.out.println("--------------------");
    }

}
