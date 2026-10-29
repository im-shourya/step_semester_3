package oop_design.assigment_problems.smartcard;

public class HostellerPlan implements PricingPlan {

    @Override
    public String getName() {
        return "Hosteller";
    }

    @Override
    public double priceFor(double listPrice) {
        return Math.round(listPrice * (100 - 10)) / 100.0;
    }
}
