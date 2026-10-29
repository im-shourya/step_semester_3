package oop_design.assigment_problems.parcel;

public interface ShippingType {
    String getName();

    double charge(double weightKg);
}
