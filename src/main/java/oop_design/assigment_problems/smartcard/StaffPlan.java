package oop_design.assigment_problems.smartcard;

public class StaffPlan implements PricingPlan {

    @Override
    public String getName() {
        return "Staff";
    }

    @Override
    public double priceFor(double listPrice) {
        return Math.round(listPrice * (100 - 20)) / 100.0;
    }
}
