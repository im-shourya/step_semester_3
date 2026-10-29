package oop_design.assigment_problems.smartcard;

public class DayScholarPlan implements PricingPlan {

    @Override
    public String getName() {
        return "Day Scholar";
    }

    @Override
    public double priceFor(double listPrice) {
        return Math.round(listPrice * (100 - 0)) / 100.0;
    }
}
