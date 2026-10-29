package oop_design.assigment_problems.parcel;

public class ExpressShipping implements ShippingType {

    @Override
    public String getName() {
        return "Express";
    }

    @Override
    public double charge(double weightKg) {
        return 80 + 15 * weightKg;
    }
}
