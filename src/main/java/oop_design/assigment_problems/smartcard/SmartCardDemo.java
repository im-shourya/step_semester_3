package oop_design.assigment_problems.smartcard;

public class SmartCardDemo {

    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500);
        Transaction thali = card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Snacks", 400);
        card.refund(thali);
        card.refund(thali);
        System.out.println(card.miniStatement());

        card.block();
        card.topUp(200);
    }
}
