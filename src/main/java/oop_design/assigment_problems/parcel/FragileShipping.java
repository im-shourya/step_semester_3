package oop_design.assigment_problems.parcel;

// Composition: reuses the Standard rule without being a kind of Standard shipping.
public class FragileShipping implements ShippingType {

    private static final double HANDLING_FEE = 50;

    private final ShippingType base = new StandardShipping();

    @Override
    public String getName() {
        return "Fragile";
    }

    @Override
    public double charge(double weightKg) {
        return base.charge(weightKg) + HANDLING_FEE;
    }
}
