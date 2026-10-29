package oop_design.assigment_problems.elective;

public class ElectiveDemo {

    public static void main(String[] args) {
        EnrollmentService service = new EnrollmentService();
        service.createElective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", new RegularPolicy(), 20);
        Student ravi = new Student("Ravi", new HonorsPolicy(), 22);
        Student neha = new Student("Neha", new ExchangePolicy(), 12);
        Student kiran = new Student("Kiran", new RegularPolicy(), 22);

        service.enroll(asha, "Cloud Computing");
        service.enroll(ravi, "Cloud Computing");
        service.enroll(neha, "Cloud Computing");
        service.enroll(kiran, "Cloud Computing");
        service.drop(asha, "Cloud Computing");
    }
}
