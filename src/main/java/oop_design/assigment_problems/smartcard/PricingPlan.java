package oop_design.assigment_problems.smartcard;

public interface PricingPlan {
    String getName();

    double priceFor(double listPrice);
}
