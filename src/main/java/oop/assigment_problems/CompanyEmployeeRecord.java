package oop.assigment_problems;

public class CompanyEmployeeRecord {
    private String name;
    private String empId;
    private Employee employee;
    private ParkingSlot slot;

    public static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        double pay = employee.getSalary();
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        }

        String slotStr = (slot != null) ? slot.getSlotNo() : "no parking assigned";
        return name + " | Pay: Rs " + pay + " | Slot: " + slotStr;
    }

    public static void main(String[] args) {
        Employee manager = new ManagerEmployee("M01", "Divya", 70000, 8000);
        Employee plain = new Employee("E01", "Karan", 40000);
        Employee intern = new InternEmployee("I01", "Meera", 12000, 10000);

        ParkingSlot[] slots = {
                new ParkingSlot("A1", 1, 0),
                new ParkingSlot("A2", 1, 0)
        };

        // Allot parking
        ParkingSlot.safeAllot(slots, "TN09AB1111");
        ParkingSlot.safeAllot(slots, "TN09AB2222");

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "M01", manager, slots[0]);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E01", plain, slots[1]);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "I01", intern, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());

        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
