package oop.assigment_problems;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee plain = new Employee("E01", "Alice", 40000);
        Employee manager = new ManagerEmployee("M01", "Bob", 70000, 8000);
        Employee intern = new InternEmployee("I01", "Charlie", 12000, 10000);

        System.out.printf("Plain employee pay: Rs %.1f\n", plain.getSalary());

        if (manager instanceof ManagerEmployee) {
            System.out.printf("Manager effective pay: Rs %.1f\n", ((ManagerEmployee) manager).effectiveSalary());
        }

        if (intern instanceof InternEmployee) {
            System.out.printf("Intern effective pay: Rs %.1f\n", ((InternEmployee) intern).effectiveSalary());
        }
    }
}
