package oop_design.assigment_problems.parcel;

public enum ParcelStatus {
    BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, CANCELLED;

    // Only the immediate next step in the delivery chain is allowed.
    boolean canMoveTo(ParcelStatus target) {
        return this != DELIVERED && this != CANCELLED && target.ordinal() == ordinal() + 1 && target != CANCELLED;
    }
}
