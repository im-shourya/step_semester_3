package oop_design.assigment_problems.parcel;

public class StandardShipping implements ShippingType {

    @Override
    public String getName() {
        return "Standard";
    }

    @Override
    public double charge(double weightKg) {
        return 40 + 10 * weightKg;
    }
}
